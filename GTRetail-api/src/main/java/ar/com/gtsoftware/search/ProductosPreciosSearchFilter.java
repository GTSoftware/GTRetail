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
package ar.com.gtsoftware.search;

import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosListasPrecios;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ProductosPreciosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Productos producto;
    private ProductosListasPrecios lista;

    @Override
    public boolean hasFilter() {
        return producto != null || lista != null;
    }

    public ProductosPreciosSearchFilter() {
    }

    public ProductosPreciosSearchFilter(Productos producto, ProductosListasPrecios lista) {
        this.producto = producto;
        this.lista = lista;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public ProductosListasPrecios getLista() {
        return lista;
    }

    public void setLista(ProductosListasPrecios lista) {
        this.lista = lista;
    }

}
