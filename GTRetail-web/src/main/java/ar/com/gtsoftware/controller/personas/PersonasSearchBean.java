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

import ar.com.gtsoftware.bl.PersonasService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.PersonasDto;
import ar.com.gtsoftware.search.PersonasSearchFilter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * SearchBean para buscar personas activas sin importar si son proveedores o clientes
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "personasSearchBean")
@ViewScoped
public class PersonasSearchBean extends AbstractSearchBean<PersonasDto, PersonasSearchFilter> {

    private static final long serialVersionUID = 1L;
    private static final int MAX_RESULTS = 15;
    private final PersonasSearchFilter filter = PersonasSearchFilter.builder().activo(true).build();
    @EJB
    private PersonasService service;

    /**
     * Creates a new instance of PersonasSearchBean
     */
    public PersonasSearchBean() {
    }

    @Override
    protected PersonasService getService() {
        return service;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("razonSocial", true);
        }
    }

    @Override
    public PersonasSearchFilter getFilter() {
        return filter;
    }

    public List<PersonasDto> findPersonaByString(String query) {
        filter.setTxt(query);
        return service.findBySearchFilter(filter, 0, MAX_RESULTS);
    }

}
