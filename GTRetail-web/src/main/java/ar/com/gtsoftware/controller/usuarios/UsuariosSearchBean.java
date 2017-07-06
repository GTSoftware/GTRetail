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

import ar.com.gtsoftware.eao.UsuariosFacade;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.search.UsuariosSearchFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Controlador para el caso de uso de búsqueda de usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@ManagedBean(name = "usuariosSearchBean")
@ViewScoped
public class UsuariosSearchBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private UsuariosSearchFilter filter = new UsuariosSearchFilter();

    @EJB
    private UsuariosFacade usuarioFacade;

    private List<Usuarios> usuariosList = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger(UsuariosSearchBean.class.getName());

    /**
     * Creates a new instance of ConfigurarPisosBean
     */
    public UsuariosSearchBean() {
    }

    public void findUsuarios() {
        usuariosList.clear();
        usuariosList.addAll(usuarioFacade.findAllBySearchFilter(filter));
    }

    public UsuariosSearchFilter getFilter() {
        return filter;
    }

    public void setFilter(UsuariosSearchFilter filter) {
        this.filter = filter;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

}
