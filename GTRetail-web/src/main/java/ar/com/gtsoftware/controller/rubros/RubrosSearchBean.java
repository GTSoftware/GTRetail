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
package ar.com.gtsoftware.controller.rubros;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ProductosRubrosFacade;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.search.RubrosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "rubrosSearchBean")
@ViewScoped
public class RubrosSearchBean extends AbstractSearchBean<ProductosRubros, RubrosSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductosRubrosFacade facade;

    private final RubrosSearchFilter filter = new RubrosSearchFilter();

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public RubrosSearchBean() {
    }

    @Override
    public RubrosSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<ProductosRubros, RubrosSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("nombreRubro", true));
        }

    }

}
