/*
 * Copyright 2014 GT Software.
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

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.PersonasService;
import ar.com.gtsoftware.bl.ProductosListasPreciosService;
import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.PersonasDto;
import ar.com.gtsoftware.dto.model.ProductosDto;
import ar.com.gtsoftware.dto.model.ProductosListasPreciosDto;
import ar.com.gtsoftware.search.ProductosListasPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "productosSearchBean")
@ViewScoped
public class ProductosSearchBean extends AbstractSearchBean<ProductosDto, ProductosSearchFilter> {

    private static final long serialVersionUID = 1L;

    private final ProductosSearchFilter filter = ProductosSearchFilter.builder()
            .activo(true).build();
    @EJB
    private ProductosService service;
    @EJB
    private PersonasService personasService;
    @EJB
    private ProductosListasPreciosService listasPreciosService;

    private ProductosListasPreciosDto listaSeleccionada;
    private List<ProductosListasPreciosDto> listasPrecio;
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    /**
     * Creates a new instance of ProductosSearchBean
     */
    public ProductosSearchBean() {
    }

    public List<ProductosDto> autocompleteProductos(String query) {
        filter.setTxt(query);
        return service.findBySearchFilter(filter, 0, 5);
    }

    public List<ProductosListasPreciosDto> getListasPrecio() {
        if (listasPrecio == null) {
            ProductosListasPreciosSearchFilter sf = new ProductosListasPreciosSearchFilter();
            sf.setActiva(true);
            sf.addSortField("id", true);
            listasPrecio = listasPreciosService.findAllBySearchFilter(sf);
        }
        return listasPrecio;
    }

    public void imprimirEtiqueta(ProductosDto prod) throws JRException, IOException {
        List<ProductosDto> productos = Collections.singletonList(prod);

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/productoEtiqueta.jasper");

        HashMap<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", String.format("attachment; filename=etiqueta-%s.pdf", prod.getId()));
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    @PostConstruct
    private void initParamsFromComposite() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = FacesContext.getCurrentInstance().getApplication();
        listaSeleccionada = app.evaluateExpressionGet(fc,
                "#{cc.attrs.listaPrecios}", ProductosListasPreciosDto.class);
        if (listaSeleccionada != null) {
            filter.setIdListaPrecio(listaSeleccionada.getId());
        }

        filter.setConStock(app.evaluateExpressionGet(fc,
                "#{cc.attrs.soloConStock}", Boolean.class));
        filter.setPuedeVenderse(app.evaluateExpressionGet(fc,
                "#{cc.attrs.puedeVenderse}", Boolean.class));
        filter.setPuedeComprarse(app.evaluateExpressionGet(fc,
                "#{cc.attrs.puedeComprarse}", Boolean.class));

        PersonasDto proveedor = app.evaluateExpressionGet(fc,
                "#{cc.attrs.proveedor}", PersonasDto.class);
        if (proveedor != null) {
            filter.setIdProveedorHabitual(proveedor.getId());
        }

    }

    public ProductosListasPreciosDto getListaSeleccionada() {
        return listaSeleccionada;
    }

    public void setListaSeleccionada(ProductosListasPreciosDto listaSeleccionada) {
        this.listaSeleccionada = listaSeleccionada;
    }
//
//    public BigDecimal getPrecio(ProductosDto producto) {
//        if (preciosSF == null) {
//            preciosSF = new ProductosPreciosSearchFilter(null, listaSeleccionada);
//        }
//        preciosSF.setProducto(producto);
//        ProductosPrecios precio = preciosFacade.findFirstBySearchFilter(preciosSF);
//        if (precio == null) {
//            return null;
//        }
//        return precio.getPrecio();
//    }

    @Override
    protected ProductosService getService() {
        return service;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("descripcion", true);
        }
        if (listaSeleccionada == null) {
            listaSeleccionada = getListasPrecio().get(0);
            filter.setIdListaPrecio(listaSeleccionada.getId());
        }
    }

    @Override
    public ProductosSearchFilter getFilter() {
        return filter;
    }

    public PersonasDto getIdProveedorHabitual() {
        if (filter.getIdProveedorHabitual() == null) {
            return null;
        }
        return personasService.find(filter.getIdProveedorHabitual());
    }

    public void setIdProveedorHabitual(PersonasDto proveedor) {
        if (proveedor != null) {
            filter.setIdProveedorHabitual(proveedor.getId());
        } else {
            filter.setIdProveedorHabitual(null);
        }
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

//    public BigDecimal getStockSucursal(ProductosDto producto) {
//        ProductoXDepositoSearchFilter stFilter = new ProductoXDepositoSearchFilter();
//        stFilter.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal().getId());
//        stFilter.setIdProducto(producto.getId());
//
//        return stockFacade.getStockBySearchFilter(stFilter);
//    }

//    public BigDecimal getStockTotal(ProductosDto producto) {
//        ProductoXDepositoSearchFilter stFilter = new ProductoXDepositoSearchFilter();
//        stFilter.setIdProducto(producto);
//
//        return stockFacade.getStockBySearchFilter(stFilter);
//    }

}
