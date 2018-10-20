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
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.dto.model.UsuariosGruposDto;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador para el caso de uso de edición de usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 2.0.1
 */
@ManagedBean(name = "usuariosRolesEditBean")
@ViewScoped
public class UsuariosRolesEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(UsuariosRolesEditBean.class.getName());
    private static final String RESERVED_USERNAME = "admin";
    @EJB
    private UsuariosService usuarioFacade;

    private UsuariosDto usuarioActual;

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public UsuariosRolesEditBean() {
    }

    @PostConstruct
    private void init() {

        String logIn = JSFUtil.getRequestParameterMap().get("login");
        if (logIn == null) {

            LOG.log(Level.INFO, "Usuario inexistente! {0}", logIn);
            usuarioActual = null;
        } else {
            usuarioActual = usuarioFacade.find(Long.parseLong(logIn));
            if (usuarioActual == null) {
                LOG.log(Level.INFO, "Usuario inexistente! {0}", logIn);
                throw new IllegalArgumentException(String.format("Usuario inexistente: %s", logIn));
            }
        }

    }

    public UsuariosDto getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(UsuariosDto usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public List<UsuariosGruposDto> getRolesDisponibles() {
        return usuarioFacade.obtenerRolesDisponibles();
    }

    public List<UsuariosGruposDto> getRolesUsuario() {
        if (usuarioActual == null) {
            return Collections.emptyList();
        }
        return usuarioFacade.obtenerRolesUsuario(usuarioActual.getId());
    }

    public void asignarRol(UsuariosGruposDto rol) {
        usuarioFacade.agregarRol(usuarioActual.getId(), rol.getId());
    }

    public void quitarRol(UsuariosGruposDto rol) {
        usuarioFacade.quitarRol(usuarioActual.getId(), rol.getId());
    }
}
