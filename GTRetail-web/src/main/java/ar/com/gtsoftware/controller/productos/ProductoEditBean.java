/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "productoEditBean")
@ViewScoped
public class ProductoEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final BigDecimal CIEN = new BigDecimal(100);
    private static final Logger LOG = Logger.getLogger(ProductoEditBean.class.getName());
    private final SubRubroSearchFilter subRubroSearchFilter = new SubRubroSearchFilter();
    @EJB
    private ProductosService productosFacade;
    @EJB
    private FiscalAlicuotasIvaService fiscalAlicuotasIvaFacade;
    @EJB
    private ProductosRubrosService productosRubrosFacade;
    @EJB
    private ProductosSubRubrosService productosSubRubrosFacade;
    @EJB
    private ProductosTiposUnidadesService productosTiposUnidadesFacade;
    @EJB
    private PersonasService personasFacade;
    @EJB
    private ProductosTiposProveeduriaService productosTiposProveeduriaFacade;
    @EJB
    private ProductosMarcasService productosMarcasFacade;
    @EJB
    private ProductosTiposPorcentajesService tiposPorcentajesFacade;
    @EJB
    private ProductosListasPreciosService listasPreciosFacade;
    @EJB
    private ProductoXDepositoService prodXDepoFacade;
    private ProductosDto productoActual;
    private List<ProductosMarcasDto> listMarcas = new ArrayList<>();
    private List<FiscalAlicuotasIvaDto> listAlicuotaIVA = new ArrayList<>();
    private List<ProductosRubrosDto> listRubros = new ArrayList<>();
    private List<ProductosSubRubrosDto> listSubRubros = new ArrayList<>();
    private List<ProductosTiposUnidadesDto> listTipoUnidades = new ArrayList<>();
    private List<PersonasDto> listProveedores = new ArrayList<>();
    private List<ProductosTiposProveeduriaDto> listTipoProveeduria = new ArrayList<>();
    private List<ProductosTiposPorcentajesDto> tiposPorcentajesList = new ArrayList<>();
    private List<ProductosListasPreciosDto> listasPrecios = new ArrayList<>();
    private List<ProductoXDepositoDto> stockXDepo = null;
    private ProductosRubrosDto productosRubrosNuevo = new ProductosRubrosDto();
    private ProductosTiposProveeduriaDto tiposProveeduria = new ProductosTiposProveeduriaDto();
    private ProductosMarcasDto nuevaMarca = new ProductosMarcasDto();
    private ProductosListasPreciosDto listaSeleccionada;
    private ProductosTiposPorcentajesDto tipoPorcentajeSeleccionado;
    private ProductosSubRubrosDto productosSubRubrosNuevo = new ProductosSubRubrosDto();
    private boolean editandoNuevoProducto = false;

    @PostConstruct
    public void init() {

        String idProducto = JSFUtil.getRequestParameterMap().get("idProducto");
        String duplicar = JSFUtil.getRequestParameterMap().get("duplicar");
        if (idProducto == null) {
            nuevo();
        } else {

            productoActual = productosFacade.find(Long.parseLong(idProducto));

            if (productoActual == null) {
                LOG.log(Level.SEVERE, "Producto inexistente!");
                throw new RuntimeException("Producto inexistente!");

            }
            if (duplicar != null && duplicar.equals("1")) {
                productoActual.setId(null);
                editandoNuevoProducto = true;
                // TODO ver como duplicar todos los datos
            }
        }
        initDatos();

    }

    private void initDatos() {
        listAlicuotaIVA.addAll(fiscalAlicuotasIvaFacade.findAll());
        RubrosSearchFilter prsf = new RubrosSearchFilter();
        prsf.addSortField(new SortField("nombreRubro", true));
        listRubros.addAll(productosRubrosFacade.findAllBySearchFilter(prsf));

        MarcasSearchFilter msf = new MarcasSearchFilter();
        msf.addSortField(new SortField("nombreMarca", true));
        listMarcas.addAll(productosMarcasFacade.findAllBySearchFilter(msf));

        listTipoUnidades.addAll(productosTiposUnidadesFacade.findAll());
        PersonasSearchFilter psf = PersonasSearchFilter.builder()
                .proveedor(true)
                .activo(true).build();
        psf.addSortField(new SortField("razonSocial", true));
        listProveedores.addAll(personasFacade.findAllBySearchFilter(psf));

        listTipoProveeduria.addAll(productosTiposProveeduriaFacade.findAll());

        tiposPorcentajesList.addAll(tiposPorcentajesFacade.findAll());

        subRubroSearchFilter.addSortField("nombreSubRubro", true);

        if (productoActual != null && productoActual.getIdRubro() != null) {
            subRubroSearchFilter.setIdProductosRubros(productoActual.getIdRubro().getId());
            listSubRubros.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));
        }
        ProductosListasPreciosSearchFilter plpsf = ProductosListasPreciosSearchFilter.builder().activa(true).build();

        listasPrecios.addAll(listasPreciosFacade.findAllBySearchFilter(plpsf));
    }

    public void cargarSubRubros(ValueChangeEvent event) {
        listSubRubros.clear();
        subRubroSearchFilter.setIdProductosRubros(((ProductosRubrosDto) event.getNewValue()).getId());

        listSubRubros.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));

    }

    public void nuevo() {
        editandoNuevoProducto = true;

        productoActual = new ProductosDto();
        productoActual.setPrecios(new ArrayList<>());
        productoActual.setPorcentajes(new ArrayList<>());
        productoActual.setUnidadesCompraUnidadesVenta(BigDecimal.ONE);
        productoActual.setActivo(true);
    }

    public void nuevoRubro() {

        productosRubrosNuevo = new ProductosRubrosDto();

    }

    public void doGuardarRubro() {

        try {
            productosRubrosFacade.createOrEdit(productosRubrosNuevo);
            listRubros.add(productosRubrosNuevo);
            JSFUtil.addInfoMessage("Rubro " + productosRubrosNuevo.getNombreRubro() + " guardado exitosamente.");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");

        }
    }

    public void nuevoSubRubro() {
        productosSubRubrosNuevo = new ProductosSubRubrosDto();
        productosSubRubrosNuevo.setIdRubro(productoActual.getIdRubro());

    }

    public void doGuardarSubRubro() {
        try {
            productosSubRubrosFacade.createOrEdit(productosSubRubrosNuevo);
            listSubRubros.add(productosSubRubrosNuevo);
            JSFUtil.addInfoMessage("SubRubro " + productosSubRubrosNuevo.getNombreSubRubro() + " guardado exitosamente.");

        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");

        }

    }

    public void cargarNuevaMarca() {
        nuevaMarca = new ProductosMarcasDto();
    }

    public void doGuardarMarca() {

        try {

            productosMarcasFacade.createOrEdit(nuevaMarca);
            listMarcas.add(nuevaMarca);
            JSFUtil.addInfoMessage("Marca " + nuevaMarca.getNombreMarca() + " guardada exitosamente.");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar la marca");
        }

    }

    public void doGuardar() {

        try {

            if (productoActual.getId() == null) {

                actualizarPrecios();

            }

            productoActual = productosFacade.createOrEdit(productoActual);
            JSFUtil.addInfoMessage("Producto " + productoActual.getId() + " guardado Exitosamente");

        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public void actualizarPrecios() {
        BigDecimal costoAdquisicionNeto = productoActual.getCostoAdquisicionNeto();
        BigDecimal costoFinal = costoAdquisicionNeto;
        BigDecimal coeficienteIVA = productoActual.getIdAlicuotaIva().getValorAlicuota().divide(CIEN)
                .add(BigDecimal.ONE);
        if (productoActual.getPorcentajes() != null) {
            for (ProductosPorcentajesDto pp : productoActual.getPorcentajes()) {
                if (pp.getIdTipoPorcentaje().isPorcentaje()) {
                    costoFinal = costoFinal.add(costoFinal.multiply(pp.getValor().divide(CIEN)));
                } else {
                    costoFinal = costoFinal.add(pp.getValor());
                }
            }
        }
        productoActual.setCostoFinal(costoFinal);
        if (productoActual.getPrecios() != null) {
            for (ProductosPreciosDto p : productoActual.getPrecios()) {
                BigDecimal utilidad = p.getUtilidad().divide(CIEN);
                p.setNeto(costoFinal.add(costoFinal.multiply(utilidad)));
                p.setPrecio(p.getNeto().multiply(coeficienteIVA).setScale(2, RoundingMode.HALF_UP));
            }
        }
    }

    public void addPorcentaje() {
        if (tipoPorcentajeSeleccionado != null) {
            ProductosPorcentajesDto pp = new ProductosPorcentajesDto();
            pp.setIdProducto(productoActual);
            pp.setIdTipoPorcentaje(tipoPorcentajeSeleccionado);
            pp.setValor(BigDecimal.ZERO);
            pp.setFechaModificacion(new Date());
            productoActual.getPorcentajes().add(pp);
        }
    }

    public void addLista() {
        boolean listaExistente = false;
        for (ProductosPreciosDto p : productoActual.getPrecios()) {
            if (p.getIdListaPrecios().equals(listaSeleccionada)) {
                listaExistente = true;
            }
        }
        if (!listaExistente) {
            ProductosPreciosDto pp = new ProductosPreciosDto();
            pp.setIdListaPrecios(listaSeleccionada);
            pp.setIdProducto(productoActual);
            productoActual.getPrecios().add(pp);
        }

    }

    public void removePorcentaje(ProductosPorcentajesDto pp) {
        productoActual.getPorcentajes().remove(pp);
        actualizarPrecios();
    }

    public void removePrecio(ProductosPreciosDto pre) {
        productoActual.getPrecios().remove(pre);
    }

    public ProductosDto getProductoActual() {
        return productoActual;
    }

    public void setProductoActual(ProductosDto productoActual) {
        this.productoActual = productoActual;
    }

    public List<FiscalAlicuotasIvaDto> getListAlicuotaIVA() {
        return listAlicuotaIVA;
    }

    public void setListAlicuotaIVA(List<FiscalAlicuotasIvaDto> listAlicuotaIVA) {
        this.listAlicuotaIVA = listAlicuotaIVA;
    }

    public List<ProductosRubrosDto> getListRubros() {
        return listRubros;
    }

    public void setListRubros(List<ProductosRubrosDto> listRubros) {
        this.listRubros = listRubros;
    }

    public List<ProductosSubRubrosDto> getListSubRubros() {
        return listSubRubros;
    }

    public void setListSubRubros(List<ProductosSubRubrosDto> listSubRubros) {
        this.listSubRubros = listSubRubros;
    }

    public ProductosSubRubrosDto getProductosSubRubrosNuevo() {
        return productosSubRubrosNuevo;
    }

    public void setProductosSubRubrosNuevo(ProductosSubRubrosDto productosSubRubrosNuevo) {
        this.productosSubRubrosNuevo = productosSubRubrosNuevo;
    }

    public ProductosRubrosDto getProductosRubrosNuevo() {
        return productosRubrosNuevo;
    }

    public void setProductosRubrosNuevo(ProductosRubrosDto productosRubrosNuevo) {
        this.productosRubrosNuevo = productosRubrosNuevo;
    }

    public List<ProductosMarcasDto> getListMarcas() {
        return listMarcas;
    }

    public void setListMarcas(List<ProductosMarcasDto> listMarcas) {
        this.listMarcas = listMarcas;
    }

    public List<ProductosTiposUnidadesDto> getListTipoUnidades() {
        return listTipoUnidades;
    }

    public void setListTipoUnidades(List<ProductosTiposUnidadesDto> listTipoUnidades) {
        this.listTipoUnidades = listTipoUnidades;
    }

    public List<PersonasDto> getListProveedores() {
        return listProveedores;
    }

    public void setListProveedores(List<PersonasDto> listProveedores) {
        this.listProveedores = listProveedores;
    }

    public ProductosTiposProveeduriaDto getTiposProveeduria() {
        return tiposProveeduria;
    }

    public void setTiposProveeduria(ProductosTiposProveeduriaDto tiposProveeduria) {
        this.tiposProveeduria = tiposProveeduria;
    }

    public List<ProductosTiposProveeduriaDto> getListTipoProveeduria() {
        return listTipoProveeduria;
    }

    public void setListTipoProveeduria(List<ProductosTiposProveeduriaDto> listTipoProveeduria) {
        this.listTipoProveeduria = listTipoProveeduria;
    }

    public ProductosMarcasDto getNuevaMarca() {
        return nuevaMarca;
    }

    public void setNuevaMarca(ProductosMarcasDto nuevaMarca) {
        this.nuevaMarca = nuevaMarca;
    }

    public List<ProductosTiposPorcentajesDto> getTiposPorcentajesList() {
        return tiposPorcentajesList;
    }

    public void setTiposPorcentajesList(List<ProductosTiposPorcentajesDto> tiposPorcentajesList) {
        this.tiposPorcentajesList = tiposPorcentajesList;
    }

    public List<ProductosListasPreciosDto> getListasPrecios() {
        return listasPrecios;
    }

    public void setListasPrecios(List<ProductosListasPreciosDto> listasPrecios) {
        this.listasPrecios = listasPrecios;
    }

    public ProductosListasPreciosDto getListaSeleccionada() {
        return listaSeleccionada;
    }

    public void setListaSeleccionada(ProductosListasPreciosDto listaSeleccionada) {
        this.listaSeleccionada = listaSeleccionada;
    }

    public ProductosTiposPorcentajesDto getTipoPorcentajeSeleccionado() {
        return tipoPorcentajeSeleccionado;
    }

    public void setTipoPorcentajeSeleccionado(ProductosTiposPorcentajesDto tipoPorcentajeSeleccionado) {
        this.tipoPorcentajeSeleccionado = tipoPorcentajeSeleccionado;
    }

    public List<ProductoXDepositoDto> getStockPorDeposito() {
        if (stockXDepo == null) {
            if (productoActual == null || productoActual.getId() == null) {
                stockXDepo = Collections.emptyList();
                return stockXDepo;
            }
            ProductoXDepositoSearchFilter sf = new ProductoXDepositoSearchFilter();
            sf.setIdProducto(productoActual.getId());
            stockXDepo = prodXDepoFacade.findAllBySearchFilter(sf);
        }
        return stockXDepo;
    }

    public boolean isEditandoNuevoProducto() {
        return editandoNuevoProducto;
    }
}
