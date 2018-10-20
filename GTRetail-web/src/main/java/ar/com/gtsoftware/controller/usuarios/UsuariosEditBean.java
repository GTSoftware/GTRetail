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

import ar.com.gtsoftware.bl.SucursalesService;
import ar.com.gtsoftware.bl.UsuariosService;
import ar.com.gtsoftware.controller.exceptions.ValidationException;
import ar.com.gtsoftware.dto.model.SucursalesDto;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.search.SucursalesSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
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
@ManagedBean(name = "usuariosEditBean")
@ViewScoped
public class UsuariosEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(UsuariosEditBean.class.getName());
    private static final String RESERVED_USERNAME = "admin";
    @EJB
    private UsuariosService usuariosService;
    @EJB
    private SucursalesService sucursalesService;
    private List<SucursalesDto> sucursalesList;
    private UsuariosDto usuarioActual;
    private boolean nuevo = false;

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public UsuariosEditBean() {
    }

    @PostConstruct
    private void init() {

        String logIn = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
        if (logIn == null) {
            nuevoUsuario();
        } else {
            usuarioActual = usuariosService.find(Long.parseLong(logIn));
            nuevo = false;
            if (usuarioActual == null) {
                LOG.log(Level.SEVERE, "Usuario inexistente! {0}", logIn);
                throw new IllegalArgumentException(String.format("Usuario inexistente: %s", logIn));
            }
        }
        SucursalesSearchFilter sucSF = SucursalesSearchFilter.builder().activa(true).build();
        sucSF.addSortField("nombreSucursal", true);
        sucursalesList = sucursalesService.findAllBySearchFilter(sucSF);

    }

    private void nuevoUsuario() {
        usuarioActual = new UsuariosDto();
        usuarioActual.setFechaAlta(new Date());
        nuevo = true;
    }

    public UsuariosDto getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(UsuariosDto usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public void doGuardarUsuario() {
        try {
            validarUsuario();
            usuarioActual.setNombreUsuario(usuarioActual.getNombreUsuario().toUpperCase());
            usuarioActual.setLogin(usuarioActual.getLogin().toLowerCase());
            usuarioActual = usuariosService.createOrEdit(usuarioActual);
            JSFUtil.addInfoMessage(String.format("Usuario guardado exitosamente: %s", usuarioActual.getLogin()));

        } catch (ValidationException ex) {
            JSFUtil.addErrorMessage(String.format("Error de validación de datos: %s", ex.getMessage()));

        }

    }

    public void doEliminarUsuario() {
        if (usuarioActual.getLogin().equals(RESERVED_USERNAME)) {
            JSFUtil.addErrorMessage("El usuario reservado no puede borrarse!");

            return;
        }
        try {
            usuariosService.remove(usuarioActual);
            usuarioActual = null;
            JSFUtil.addInfoMessage("Usuario eliminado exitosamente!");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage("Error al borrar!");

        }
    }

    private void validarUsuario() throws ValidationException {
        if (usuarioActual == null) {
            throw new ValidationException("No se permite guardar el usuario en el estado actual");
        }

        if (usuarioActual.getLogin().equals(RESERVED_USERNAME)) {
            throw new ValidationException("No se permite modificar el usuario reservado");
        }
    }


    /**
     * La lista de sucursales activas en las que se puede encontrar un usuario
     *
     * @return
     */
    public List<SucursalesDto> getSucursalesList() {
        return sucursalesList;
    }

}
