/*
 * Copyright 2016 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.bl.*;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.search.*;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "productosPreciosBean")
@ViewScoped
public class ProductosPreciosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final BigDecimal CIEN = new BigDecimal(100);

    private final ProductosSearchFilter filter = ProductosSearchFilter.builder().activo(true).build();
    private BigDecimal porcentajeCosto = BigDecimal.ZERO;
    private List<ProductosTiposProveeduriaDto> tiposProveeduriaList;
    private List<PersonasDto> proveedoresList;
    private List<ProductosRubrosDto> rubrosList;
    private List<ProductosSubRubrosDto> subRubrosList;
    private List<ProductosMarcasDto> marcasList;

    private boolean actualizarCosto = false;
    private boolean actualizarPorcentajes = false;

    private List<ProductosPorcentajesDto> porcentajesToDelete = new ArrayList<>();
    private List<ProductosPorcentajesDto> porcentajesToAdd = new ArrayList<>();

    private List<ProductosTiposPorcentajesDto> tiposPorcentajesList = new ArrayList<>();

    @EJB
    private ProductosService productosFacade;
    @EJB
    private ProductosRubrosService productosRubrosFacade;

    @EJB
    private ProductosSubRubrosService productosSubRubrosFacade;
    @EJB
    private ProductosTiposProveeduriaService productosTiposProveeduriaFacade;
    @EJB
    private ProductosMarcasService productosMarcasFacade;
    @EJB
    private PersonasService personasFacade;
    @EJB
    private ProductosTiposPorcentajesService tiposPorcentajesFacade;
    private ProductosTiposPorcentajesDto tipoPorcentajeSeleccionado;
    private int itemNro = 1;


    /**
     * Creates a new instance of ProductosPreciosBean
     */
    public ProductosPreciosBean() {
    }

    @PostConstruct
    private void init() {
        tiposPorcentajesList.addAll(tiposPorcentajesFacade.findAll());
    }

    public ProductosSearchFilter getFilter() {
        return filter;
    }

    public List<ProductosTiposProveeduriaDto> getTiposProveeduriaList() {
        if (tiposProveeduriaList == null) {
            tiposProveeduriaList = new ArrayList<>();
            tiposProveeduriaList.addAll(productosTiposProveeduriaFacade.findAll());
        }
        return tiposProveeduriaList;
    }

    public List<PersonasDto> getProveedoresList() {
        if (proveedoresList == null) {
            PersonasSearchFilter psf = PersonasSearchFilter.builder()
                    .proveedor(true)
                    .activo(true).build();
            psf.addSortField(new SortField("razonSocial", true));
            proveedoresList = new ArrayList<>();
            proveedoresList.addAll(personasFacade.findAllBySearchFilter(psf));
        }
        return proveedoresList;
    }

    public List<ProductosRubrosDto> getRubrosList() {
        if (rubrosList == null) {
            RubrosSearchFilter prsf = new RubrosSearchFilter();
            prsf.addSortField(new SortField("nombreRubro", true));
            rubrosList = new ArrayList<>();
            rubrosList.addAll(productosRubrosFacade.findAllBySearchFilter(prsf));
        }
        return rubrosList;
    }

    public List<ProductosSubRubrosDto> getSubRubrosList() {

        if (subRubrosList == null) {
            subRubrosList = new ArrayList<>();
        }
        subRubrosList.clear();
        if (filter.getIdRubro() != null) {
            SubRubroSearchFilter subRubroSearchFilter = new SubRubroSearchFilter();
            subRubroSearchFilter.setIdProductosRubros(filter.getIdRubro());
            subRubroSearchFilter.addSortField(new SortField("nombreSubRubro", true));
            subRubrosList.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));
        }
        return subRubrosList;
    }

    public List<ProductosMarcasDto> getMarcasList() {
        if (marcasList == null) {
            MarcasSearchFilter msf = new MarcasSearchFilter();
            msf.addSortField(new SortField("nombreMarca", true));
            marcasList = new ArrayList<>();
            marcasList.addAll(productosMarcasFacade.findAllBySearchFilter(msf));
        }
        return marcasList;
    }

    public int getCantidadProductosAEditar() {
        return productosFacade.countBySearchFilter(filter);
    }

    public void actualizarPrecios() {
        if (!actualizarCosto && !actualizarPorcentajes) {
            return;
        }

        List<ProductosDto> productos = productosFacade.findAllBySearchFilter(filter);
        for (ProductosDto producto : productos) {
            BigDecimal costoAdquisicionNeto = producto.getCostoAdquisicionNeto();
            if (actualizarCosto) {
                costoAdquisicionNeto = costoAdquisicionNeto.add(costoAdquisicionNeto.multiply(porcentajeCosto.divide(CIEN)));
                producto.setCostoAdquisicionNeto(costoAdquisicionNeto);
            }
            BigDecimal costoFinal = costoAdquisicionNeto;
            BigDecimal coeficienteIVA = producto.getIdAlicuotaIva().getValorAlicuota().divide(CIEN).add(BigDecimal.ONE);
            if (producto.getPorcentajes() != null) {
                if (actualizarPorcentajes) {
                    tratarPorcentajes(producto);
                }
                for (ProductosPorcentajesDto pp : producto.getPorcentajes()) {
                    if (pp.getIdTipoPorcentaje().isPorcentaje()) {
                        costoFinal = costoFinal.add(costoFinal.multiply(pp.getValor().divide(CIEN)));
                    } else {
                        costoFinal = costoFinal.add(pp.getValor());
                    }
                }
            }
            producto.setCostoFinal(costoFinal);
            if (producto.getPrecios() != null) {
                for (ProductosPreciosDto pp : producto.getPrecios()) {
                    BigDecimal utilidad = pp.getUtilidad().divide(CIEN);
                    pp.setNeto(costoFinal.add(costoFinal.multiply(utilidad)));
                    pp.setPrecio(pp.getNeto().multiply(coeficienteIVA).setScale(2, RoundingMode.HALF_UP));
                }
            }
            productosFacade.createOrEdit(producto);
        }
        JSFUtil.addInfoMessage("Productos actualizados exitosamente.");
    }

    private void tratarPorcentajes(ProductosDto producto) {
        List<ProductosPorcentajesDto> porcentajes = producto.getPorcentajes();
        List<ProductosPorcentajesDto> porcentajesAEliminar = new ArrayList<>(porcentajes.size());

        for (ProductosPorcentajesDto pToDelete : porcentajesToDelete) {
            for (ProductosPorcentajesDto pProd : porcentajes) {
                if (pProd.getIdTipoPorcentaje().getId().equals(pToDelete.getIdTipoPorcentaje().getId())
                        && pProd.getValor().compareTo(pToDelete.getValor()) == 0) {

                    pProd.setIdProducto(null);
                    porcentajesAEliminar.add(pProd);
                }
            }
        }
        porcentajes.removeAll(porcentajesAEliminar);
        producto.setPorcentajes(porcentajes);

        for (ProductosPorcentajesDto pToAdd : porcentajesToAdd) {
            ProductosPorcentajesDto p = ProductosPorcentajesDto.builder()
                    .idProducto(producto)
                    .valor(pToAdd.getValor())
                    .idTipoPorcentaje(pToAdd.getIdTipoPorcentaje())
                    .build();

            porcentajes.add(p);
        }
    }

    public BigDecimal getPorcentajeCosto() {
        return porcentajeCosto;
    }

    public void setPorcentajeCosto(BigDecimal porcentajeCosto) {
        this.porcentajeCosto = porcentajeCosto;
    }

    public boolean isActualizarCosto() {
        return actualizarCosto;
    }

    public void setActualizarCosto(boolean actualizarCosto) {
        this.actualizarCosto = actualizarCosto;
    }

    public boolean isActualizarPorcentajes() {
        return actualizarPorcentajes;
    }

    public void setActualizarPorcentajes(boolean actualizarPorcentajes) {
        this.actualizarPorcentajes = actualizarPorcentajes;
    }

    public List<ProductosPorcentajesDto> getPorcentajesToDelete() {
        return porcentajesToDelete;
    }

    public void setPorcentajesToDelete(List<ProductosPorcentajesDto> porcentajesToDelete) {
        this.porcentajesToDelete = porcentajesToDelete;
    }

    public List<ProductosPorcentajesDto> getPorcentajesToAdd() {
        return porcentajesToAdd;
    }

    public void setPorcentajesToAdd(List<ProductosPorcentajesDto> porcentajesToAdd) {
        this.porcentajesToAdd = porcentajesToAdd;
    }

    public List<ProductosTiposPorcentajesDto> getTiposPorcentajesList() {
        return tiposPorcentajesList;
    }

    public ProductosTiposPorcentajesDto getTipoPorcentajeSeleccionado() {
        return tipoPorcentajeSeleccionado;
    }

    public void setTipoPorcentajeSeleccionado(ProductosTiposPorcentajesDto tipoPorcentajeSeleccionado) {
        this.tipoPorcentajeSeleccionado = tipoPorcentajeSeleccionado;
    }

    public void addPorcentajeToDelete() {
        if (tipoPorcentajeSeleccionado != null) {
            ProductosPorcentajesDto pToDelete = createProductosPorcentajesDto();
            porcentajesToDelete.add(pToDelete);
        }
    }

    public void addPorcentajeToAdd() {
        if (tipoPorcentajeSeleccionado != null) {
            ProductosPorcentajesDto pToAdd = createProductosPorcentajesDto();
            porcentajesToAdd.add(pToAdd);
        }
    }

    private ProductosPorcentajesDto createProductosPorcentajesDto() {
        return ProductosPorcentajesDto.builder()
                .idTipoPorcentaje(tipoPorcentajeSeleccionado)
                .valor(BigDecimal.ZERO)
                .nroItem(itemNro++)
                .build();
    }

    public void removePorcentajeToDelete(ProductosPorcentajesDto porcentaje) {
        porcentajesToDelete.remove(porcentaje);
    }

    public void removePorcentajeToAdd(ProductosPorcentajesDto porcentaje) {
        porcentajesToAdd.remove(porcentaje);
    }
}
