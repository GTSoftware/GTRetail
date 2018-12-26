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


    /**
     * Creates a new instance of ProductosPreciosBean
     */
    public ProductosPreciosBean() {
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
        List<ProductosDto> productos = productosFacade.findAllBySearchFilter(filter);
        for (ProductosDto producto : productos) {
            BigDecimal costoAdquisicionNeto = producto.getCostoAdquisicionNeto();
            costoAdquisicionNeto = costoAdquisicionNeto.add(costoAdquisicionNeto.multiply(porcentajeCosto.divide(CIEN)));
            producto.setCostoAdquisicionNeto(costoAdquisicionNeto);
            BigDecimal costoFinal = costoAdquisicionNeto;
            BigDecimal coeficienteIVA = producto.getIdAlicuotaIva().getValorAlicuota().divide(CIEN).add(BigDecimal.ONE);
            if (producto.getPorcentajes() != null) {
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
        JSFUtil.addInfoMessage("ProductosDto actualizados exitosamente.");
    }

    public BigDecimal getPorcentajeCosto() {
        return porcentajeCosto;
    }

    public void setPorcentajeCosto(BigDecimal porcentajeCosto) {
        this.porcentajeCosto = porcentajeCosto;
    }

}
