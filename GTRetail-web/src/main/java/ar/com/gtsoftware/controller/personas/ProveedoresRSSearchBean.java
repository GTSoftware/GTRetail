/*
 * Copyright 2018 GT Software.
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
import ar.com.gtsoftware.search.SortField;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * SearchBean para buscar proveedores activos
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "proveedoresRSSearchBean")
@RequestScoped
public class ProveedoresRSSearchBean extends AbstractSearchBean<PersonasDto, PersonasSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final PersonasSearchFilter filter = PersonasSearchFilter.builder()
            .activo(true).proveedor(true).build();
    @EJB
    private PersonasService facade;

    /**
     * Creates a new instance of PersonasSearchBean
     */
    public ProveedoresRSSearchBean() {
    }

    @Override
    protected PersonasService getService() {
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

    public List<PersonasDto> findPersonaByString(String query) {
        filter.setTxt(query);
        return facade.findBySearchFilter(filter, 0, 15);
    }

}
