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
package ar.com.gtsoftware.controller.marcas;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ProductosMarcasFacade;
import ar.com.gtsoftware.model.ProductosMarcas;
import ar.com.gtsoftware.search.MarcasSearchFilter;
import ar.com.gtsoftware.search.SortField;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "marcasSearchBean")
@ViewScoped
public class MarcasSearchBean extends AbstractSearchBean<ProductosMarcas> {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductosMarcasFacade facade;

    private final MarcasSearchFilter filter = new MarcasSearchFilter("%");

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public MarcasSearchBean() {
    }

    @Override
    public MarcasSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<ProductosMarcas> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("nombreMarca", true));
        }

    }
}
