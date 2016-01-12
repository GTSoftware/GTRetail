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

import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosListasPreciosFacade;
import ar.com.gtsoftware.eao.ProductosPreciosFacade;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.model.ProductosPrecios;
import ar.com.gtsoftware.search.ProductosListasPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.LazyEntityDataModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "productosSearchBean")
@ViewScoped
public class ProductosSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductosFacade productosFacade;
    @EJB
    private ProductosListasPreciosFacade listasPreciosFacade;
    @EJB
    private ProductosPreciosFacade preciosFacade;

    private Productos productoActual;
    private ProductosListasPrecios listaSeleccionada;
    private DataModel<Productos> dataModel;
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

    public DataModel<Productos> getDataModel() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("descripcion", true));
        }
        if (listaSeleccionada == null) {
            listaSeleccionada = getListasPrecio().get(0);
        }
        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(productosFacade, filter);
        }
        return dataModel;
    }

    public void doSearch() {
        dataModel = null;
    }

    public List<Productos> autocompleteProductos(String query) {
        filter.setTxt(query);
        return productosFacade.findBySearchFilter(filter, 0, 5);
    }

    public Productos getProductoActual() {
        return productoActual;
    }

    public void setProductoActual(Productos productoActual) {
        this.productoActual = productoActual;
    }

    public ProductosSearchFilter getFilter() {
        return filter;
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
        List<ProductosPrecios> precio = preciosFacade.findBySearchFilter(preciosSF, 0, 1);
        if (precio.isEmpty()) {
            return null;
        }
        return precio.get(0).getPrecio();
    }
}
