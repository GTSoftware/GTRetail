///*
// * Copyright 2016 GT Software.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package ar.com.gtsoftware.controller.productos;
//
//import ar.com.gtsoftware.eao.PersonasFacade;
//import ar.com.gtsoftware.eao.ProductosFacade;
//import ar.com.gtsoftware.eao.ProductosMarcasFacade;
//import ar.com.gtsoftware.eao.ProductosRubrosFacade;
//import ar.com.gtsoftware.eao.ProductosSubRubrosFacade;
//import ar.com.gtsoftware.eao.ProductosTiposProveeduriaFacade;
//import ar.com.gtsoftware.model.Personas;
//import ar.com.gtsoftware.model.Productos;
//import ar.com.gtsoftware.model.ProductosMarcas;
//import ar.com.gtsoftware.model.ProductosPorcentajes;
//import ar.com.gtsoftware.model.ProductosPrecios;
//import ar.com.gtsoftware.model.ProductosRubros;
//import ar.com.gtsoftware.model.ProductosSubRubros;
//import ar.com.gtsoftware.model.ProductosTiposProveeduria;
//import ar.com.gtsoftware.search.MarcasSearchFilter;
//import ar.com.gtsoftware.search.PersonasSearchFilter;
//import ar.com.gtsoftware.search.ProductosSearchFilter;
//import ar.com.gtsoftware.search.RubrosSearchFilter;
//import ar.com.gtsoftware.search.SortField;
//import ar.com.gtsoftware.search.SubRubroSearchFilter;
//import ar.com.gtsoftware.utils.JSFUtil;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//
///**
// * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
// */
//@ManagedBean(name = "productosPreciosBean")
//@ViewScoped
//public class ProductosPreciosBean implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    private static final BigDecimal CIEN = new BigDecimal(100);
//
//    private final ProductosSearchFilter filter = new ProductosSearchFilter(Boolean.TRUE, null, null);
//    private BigDecimal porcentajeCosto = BigDecimal.ZERO;
//    private List<ProductosTiposProveeduria> tiposProveeduriaList;
//    private List<Personas> proveedoresList;
//    private List<ProductosRubros> rubrosList;
//    private List<ProductosSubRubros> subRubrosList;
//    private List<ProductosMarcas> marcasList;
//
//    @EJB
//    private ProductosFacade productosFacade;
//    @EJB
//    private ProductosRubrosFacade productosRubrosFacade;
//
//    @EJB
//    private ProductosSubRubrosFacade productosSubRubrosFacade;
//    @EJB
//    private ProductosTiposProveeduriaFacade productosTiposProveeduriaFacade;
//    @EJB
//    private ProductosMarcasFacade productosMarcasFacade;
//    @EJB
//    private PersonasFacade personasFacade;
//
//
//    /**
//     * Creates a new instance of ProductosPreciosBean
//     */
//    public ProductosPreciosBean() {
//    }
//
//    public ProductosSearchFilter getFilter() {
//        return filter;
//    }
//
//    public List<ProductosTiposProveeduria> getTiposProveeduriaList() {
//        if (tiposProveeduriaList == null) {
//            tiposProveeduriaList = new ArrayList<>();
//            tiposProveeduriaList.addAll(productosTiposProveeduriaFacade.findAll());
//        }
//        return tiposProveeduriaList;
//    }
//
//    public List<Personas> getProveedoresList() {
//        if (proveedoresList == null) {
//            PersonasSearchFilter psf = PersonasSearchFilter.builder()
//                    .proveedor(true)
//                    .activo(true).build();
//            psf.addSortField(new SortField("razonSocial", true));
//            proveedoresList = new ArrayList<>();
//            proveedoresList.addAll(personasFacade.findAllBySearchFilter(psf));
//        }
//        return proveedoresList;
//    }
//
//    public List<ProductosRubros> getRubrosList() {
//        if (rubrosList == null) {
//            RubrosSearchFilter prsf = new RubrosSearchFilter();
//            prsf.addSortField(new SortField("nombreRubro", true));
//            rubrosList = new ArrayList<>();
//            rubrosList.addAll(productosRubrosFacade.findAllBySearchFilter(prsf));
//        }
//        return rubrosList;
//    }
//
//    public List<ProductosSubRubros> getSubRubrosList() {
//
//        if (subRubrosList == null) {
//            subRubrosList = new ArrayList<>();
//        }
//        subRubrosList.clear();
//        if (filter.getIdRubro() != null) {
//            SubRubroSearchFilter subRubroSearchFilter = new SubRubroSearchFilter();
//            subRubroSearchFilter.setProductosRubros(filter.getIdRubro());
//            subRubroSearchFilter.addSortField(new SortField("nombreSubRubro", true));
//            subRubrosList.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));
//        }
//        return subRubrosList;
//    }
//
//    public List<ProductosMarcas> getMarcasList() {
//        if (marcasList == null) {
//            MarcasSearchFilter msf = new MarcasSearchFilter();
//            msf.addSortField(new SortField("nombreMarca", true));
//            marcasList = new ArrayList<>();
//            marcasList.addAll(productosMarcasFacade.findAllBySearchFilter(msf));
//        }
//        return marcasList;
//    }
//
//    public int getCantidadProductosAEditar() {
//        return productosFacade.countBySearchFilter(filter);
//    }
//
//    public void actualizarPrecios() {
//        List<Productos> productos = productosFacade.findAllBySearchFilter(filter);
//        for (Productos producto : productos) {
//            BigDecimal costoAdquisicionNeto = producto.getCostoAdquisicionNeto();
//            costoAdquisicionNeto = costoAdquisicionNeto.add(costoAdquisicionNeto.multiply(porcentajeCosto.divide(CIEN)));
//            producto.setCostoAdquisicionNeto(costoAdquisicionNeto);
//            BigDecimal costoFinal = costoAdquisicionNeto;
//            BigDecimal coeficienteIVA = producto.getIdAlicuotaIva().getValorAlicuota().divide(CIEN).add(BigDecimal.ONE);
//            if (producto.getPorcentajes() != null) {
//                for (ProductosPorcentajes pp : producto.getPorcentajes()) {
//                    if (pp.getIdTipoPorcentaje().getIsPorcentaje()) {
//                        costoFinal = costoFinal.add(costoFinal.multiply(pp.getValor().divide(CIEN)));
//                    } else {
//                        costoFinal = costoFinal.add(pp.getValor());
//                    }
//                }
//            }
//            producto.setCostoFinal(costoFinal);
//            if (producto.getPrecios() != null) {
//                for (ProductosPrecios pp : producto.getPrecios()) {
//                    BigDecimal utilidad = pp.getUtilidad().divide(CIEN);
//                    pp.setNeto(costoFinal.add(costoFinal.multiply(utilidad)));
//                    pp.setPrecio(pp.getNeto().multiply(coeficienteIVA).setScale(2, RoundingMode.HALF_UP));
//                }
//            }
//            productosFacade.edit(producto);
//        }
//        JSFUtil.addInfoMessage("ProductosDto actualizados exitosamente.");
//    }
//
//    public BigDecimal getPorcentajeCosto() {
//        return porcentajeCosto;
//    }
//
//    public void setPorcentajeCosto(BigDecimal porcentajeCosto) {
//        this.porcentajeCosto = porcentajeCosto;
//    }
//
//}
