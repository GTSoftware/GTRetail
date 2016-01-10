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

import ar.com.gtsoftware.search.ProductosSearchFilter;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Named(value = "productosPreciosBean")
@ViewScoped
public class ProductosPreciosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ProductosSearchFilter filter = new ProductosSearchFilter(Boolean.TRUE, null, null);

    /**
     * Creates a new instance of ProductosPreciosBean
     */
    public ProductosPreciosBean() {
    }

    public ProductosSearchFilter getFilter() {
        return filter;
    }

}
