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

import ar.com.gtsoftware.controller.exceptions.ValidationException;
import ar.com.gtsoftware.eao.UsuariosFacade;
import ar.com.gtsoftware.eao.UsuariosGruposFacade;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.model.UsuariosGrupos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Controlador para el caso de uso de edición de usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @since 2.0.1
 * @version 1.0.0
 */
@Named(value = "usuariosRolesEditBean")
@ViewScoped
public class UsuariosRolesEditBean implements Serializable {

    @EJB
    private UsuariosFacade usuarioFacade;

    @EJB
    private UsuariosGruposFacade rolFacade;

    private Usuarios usuarioActual;

    private static final Logger LOG = Logger.getLogger(UsuariosRolesEditBean.class.getName());

    private static final String RESERVED_USERNAME = "system";

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public UsuariosRolesEditBean() {
    }

    @PostConstruct
    private void init() {

        String logIn = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
        if (logIn == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Usuario inexistente!", logIn));
            LOG.log(Level.INFO, "Usuario inexistente! {0}", logIn);
            usuarioActual = null;
        } else {
            usuarioActual = usuarioFacade.find(logIn);
            if (usuarioActual == null) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Usuario inexistente!", logIn));
                LOG.log(Level.INFO, "Usuario inexistente! {0}", logIn);
            }
        }

    }

    public Usuarios getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuarios usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    private void doGuardarUsuario() {
        try {
            if (usuarioActual.getLogin().equals(RESERVED_USERNAME)) {
                throw new ValidationException("No se permite modificar el usuario reservado");
            }
            usuarioFacade.createOrEdit(usuarioActual);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario guardado exitosamente!", usuarioActual.getLogin()));

        } catch (ValidationException ex) {
            LOG.log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", ex.getMessage()));
        }

    }

    public List<UsuariosGrupos> getRolesDisponibles() {
        return rolFacade.findAll();
    }

    public List<UsuariosGrupos> getRolesUsuario() {
        if (usuarioActual == null) {
            return new ArrayList<>();
        }
        return usuarioActual.getUsuariosGruposList();
    }

    public void asignarRol(UsuariosGrupos rol) {
        if (usuarioActual != null) {
            if (usuarioActual.getUsuariosGruposList() == null) {
                usuarioActual.setUsuariosGruposList(new ArrayList<UsuariosGrupos>());
            }
            usuarioActual.getUsuariosGruposList().add(rol);
            doGuardarUsuario();
        }
    }

    public void quitarRol(UsuariosGrupos rol) {
        if (usuarioActual != null) {
            if (usuarioActual.getUsuariosGruposList() != null) {

                usuarioActual.getUsuariosGruposList().remove(rol);
                doGuardarUsuario();
            }
        }
    }
}
