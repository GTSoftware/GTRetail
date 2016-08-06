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

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosListasPreciosFacade;
import ar.com.gtsoftware.eao.ProductosPreciosFacade;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.model.ProductosPrecios;
import ar.com.gtsoftware.search.ProductosListasPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "productosSearchBean")
@ViewScoped
public class ProductosSearchBean extends AbstractSearchBean<Productos, ProductosSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductosFacade facade;
    @EJB
    private ProductosListasPreciosFacade listasPreciosFacade;
    @EJB
    private ProductosPreciosFacade preciosFacade;

    private ProductosListasPrecios listaSeleccionada;

    private ProductosPreciosSearchFilter preciosSF;

    private List<ProductosListasPrecios> listasPrecio;
    /**
     * Por defecto creamos un filtro para productos a la venta activos
     */
    private final ProductosSearchFilter filter = new ProductosSearchFilter(Boolean.TRUE, null, Boolean.TRUE, Boolean.TRUE);

    /**
     * Creates a new instance of ProductosSearchBean
     */
    public ProductosSearchBean() {
    }

    public List<Productos> autocompleteProductos(String query) {
        filter.setTxt(query);
        return facade.findBySearchFilter(filter, 0, 5);
    }

    public List<ProductosListasPrecios> getListasPrecio() {
        if (listasPrecio == null) {
            ProductosListasPreciosSearchFilter sf = new ProductosListasPreciosSearchFilter();
            sf.setActiva(true);
            sf.addSortField(new SortField("id", true));
            listasPrecio = listasPreciosFacade.findAllBySearchFilter(sf);
        }
        return listasPrecio;
    }
    
    public void imprimirEtiqueta(Productos prod) throws JRException, IOException{
        List<Productos> productos = new ArrayList<>();
        
        productos.add(prod);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/productoEtiqueta.jasper");

        HashMap<String, Object> parameters = new HashMap<>();
        //parameters.put("codProv","112");
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=venta-" + prod.getId() + ".pdf");
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public ProductosListasPrecios getListaSeleccionada() {
        return listaSeleccionada;
    }

    public void setListaSeleccionada(ProductosListasPrecios listaSeleccionada) {
        this.listaSeleccionada = listaSeleccionada;
    }

    public BigDecimal getPrecio(Productos producto) {
        if (preciosSF == null) {
            preciosSF = new ProductosPreciosSearchFilter(null, listaSeleccionada);
        }
        preciosSF.setProducto(producto);
        ProductosPrecios precio = preciosFacade.findFirstBySearchFilter(preciosSF);
        if (precio == null) {
            return null;
        }
        return precio.getPrecio();
    }

    @Override
    protected AbstractFacade<Productos, ProductosSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("descripcion", true));
        }
        if (listaSeleccionada == null) {
            listaSeleccionada = getListasPrecio().get(0);
        }
    }

    @Override
    public ProductosSearchFilter getFilter() {
        return filter;
    }
}
