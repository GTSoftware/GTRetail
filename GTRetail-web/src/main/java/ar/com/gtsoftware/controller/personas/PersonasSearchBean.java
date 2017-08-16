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
package ar.com.gtsoftware.controller.personas;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * SearchBean para buscar personas activas sin importar si son proveedores o clientes
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "personasSearchBean")
@ViewScoped
public class PersonasSearchBean extends AbstractSearchBean<Personas, PersonasSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private PersonasFacade facade;

    private final PersonasSearchFilter filter = new PersonasSearchFilter(Boolean.TRUE, null, null);

    /**
     * Creates a new instance of PersonasSearchBean
     */
    public PersonasSearchBean() {
    }

    @Override
    protected AbstractFacade<Personas, PersonasSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("razonSocial", true));
        }
    }

    @Override
    public PersonasSearchFilter getFilter() {
        return filter;
    }

    public List<Personas> findPersonaByString(String query) {
        filter.setTxt(query);
        return facade.findBySearchFilter(filter, 0, 15);
    }

}
