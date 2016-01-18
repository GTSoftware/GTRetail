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

import ar.com.gtsoftware.model.ProductosRubros;

/**
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class SubRubroSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private ProductosRubros productosRubros;
    private String nombreSubRubro;

    public SubRubroSearchFilter() {
    }

    public SubRubroSearchFilter(String nombreSubRubro) {
        this.nombreSubRubro = nombreSubRubro;
    }

    @Override
    public boolean hasFilter() {
        return (productosRubros != null || (nombreSubRubro != null && !nombreSubRubro.isEmpty()));
    }

    public ProductosRubros getProductosRubros() {
        return productosRubros;
    }

    public void setProductosRubros(ProductosRubros productosRubros) {
        this.productosRubros = productosRubros;
    }

    public String getNombreSubRubro() {
        return nombreSubRubro;
    }

    public void setNombreSubRubro(String nombreSubRubro) {
        this.nombreSubRubro = nombreSubRubro;
    }
}
