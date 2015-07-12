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
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    private List<Productos> productosList = new ArrayList<>();
    private Productos productoActual;

    /**
     * Por defecto creamos un filtro para productos a la venta activos
     */
    private ProductosSearchFilter filter = new ProductosSearchFilter(Boolean.TRUE, null, Boolean.TRUE);

    /**
     * Creates a new instance of ProductosSearchBean
     */
    public ProductosSearchBean() {
    }

    @PostConstruct
    private void init() {
        //Logger.getLogger(ClientesSearchBean.class.getName()).log(Level.INFO, "Post Construct...", 0);
    }

    public void doSearch() {
        productosList.clear();
        productosList.addAll(productosFacade.findBySearchFilter(filter));
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
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

    public void setFilter(ProductosSearchFilter filter) {
        this.filter = filter;
    }

}
