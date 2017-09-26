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
package ar.com.gtsoftware.controller.ubicaciones;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.UbicacionPaisesFacade;
import ar.com.gtsoftware.model.UbicacionPaises;
import ar.com.gtsoftware.search.PaisesSearchFilter;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "paisesSearchBean")
@ViewScoped
public class PaisesSearchBean extends AbstractSearchBean<UbicacionPaises, PaisesSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private UbicacionPaisesFacade facade;

    private final PaisesSearchFilter filter = new PaisesSearchFilter();

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public PaisesSearchBean() {
    }

    @Override
    public PaisesSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<UbicacionPaises, PaisesSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("nombrePais", true);
        }

    }
}
