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
import ar.com.gtsoftware.model.ProductosTiposPorcentajes;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ProductosPorcentajesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Productos producto;
    private ProductosTiposPorcentajes tipoPorcentaje;

    @Override
    public boolean hasFilter() {
        return producto != null || tipoPorcentaje != null;
    }

    public ProductosPorcentajesSearchFilter() {
    }

    public ProductosPorcentajesSearchFilter(Productos producto, ProductosTiposPorcentajes tipoPorcentaje) {
        this.producto = producto;
        this.tipoPorcentaje = tipoPorcentaje;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public ProductosTiposPorcentajes getTipoPorcentaje() {
        return tipoPorcentaje;
    }

    public void setTipoPorcentaje(ProductosTiposPorcentajes tipoPorcentaje) {
        this.tipoPorcentaje = tipoPorcentaje;
    }

}