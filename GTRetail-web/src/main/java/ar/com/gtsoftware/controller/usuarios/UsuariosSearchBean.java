/*
 * Copyright 2014 GTSoftware
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
package ar.com.gtsoftware.controller.usuarios;

import ar.com.gtsoftware.bl.UsuariosService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.search.UsuariosSearchFilter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bena para búsqueda de usuarios
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "usuariosSearchBean")
@ViewScoped
public class UsuariosSearchBean extends AbstractSearchBean<UsuariosDto, UsuariosSearchFilter> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final UsuariosSearchFilter filter = new UsuariosSearchFilter();

    @EJB
    private UsuariosService usuariosService;

    /**
     * Creates a new instance of UsuariosSearchBean
     */
    public UsuariosSearchBean() {
    }

    @Override
    public UsuariosSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected UsuariosService getService() {
        return usuariosService;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("login", true);
        }
    }

}
