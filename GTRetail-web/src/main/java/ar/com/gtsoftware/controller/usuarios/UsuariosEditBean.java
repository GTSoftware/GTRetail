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
import ar.com.gtsoftware.eao.SucursalesFacade;
import ar.com.gtsoftware.eao.UsuariosFacade;
import ar.com.gtsoftware.model.Sucursales;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.search.SucursalesSearchFilter;
import ar.com.gtsoftware.utils.HashUtils;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Controlador para el caso de uso de edición de usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @since 2.0.1
 * @version 1.0.0
 */
@ManagedBean(name = "usuariosEditBean")
@ViewScoped
public class UsuariosEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private UsuariosFacade usuarioFacade;
    @EJB
    private SucursalesFacade sucursalesFacade;

    private List<Sucursales> sucursalesList;

    private Usuarios usuarioActual;

    private boolean nuevo = false;

    private String passwordRepeat;

    private String newPassword;

    private static final Logger LOG = Logger.getLogger(UsuariosEditBean.class.getName());

    private static final String RESERVED_USERNAME = "admin";

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
            usuarioActual = usuarioFacade.find(Long.parseLong(logIn));
            nuevo = false;
            if (usuarioActual == null) {
                LOG.log(Level.SEVERE, "Usuario inexistente! {0}", logIn);
                throw new IllegalArgumentException(String.format("Usuario inexistente: %s", logIn));
            }
        }
        SucursalesSearchFilter sucSF = new SucursalesSearchFilter(true);
        sucSF.addSortField("nombreSucursal", true);
        sucursalesList = sucursalesFacade.findAllBySearchFilter(sucSF);

    }

    private void nuevoUsuario() {
        usuarioActual = new Usuarios();
        usuarioActual.setFechaAlta(new Date());
        nuevo = true;
    }

    public Usuarios getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuarios usuarioActual) {
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
            usuarioFacade.createOrEdit(usuarioActual);
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
            usuarioFacade.remove(usuarioActual);
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
        if (newPassword != null && !newPassword.isEmpty()) {
            validarPassword();
        }
        if (usuarioActual.getLogin().equals(RESERVED_USERNAME)) {
            throw new ValidationException("No se permite modificar el usuario reservado");
        }
    }

    private void validarPassword() throws ValidationException {
        if (newPassword == null || passwordRepeat == null) {
            throw new ValidationException("Todos los campos de clave deben completarse");

        }
        if (newPassword.length() < 6) {
            throw new ValidationException("La clave debe tener 6 o más caracteres");
        }

        if (!newPassword.equals(passwordRepeat)) {
            throw new ValidationException("La clave de control no coincide");

        }
        String newPassHashed = HashUtils.getHash(newPassword);

        usuarioActual.setPassword(newPassHashed);
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * La lista de sucursales activas en las que se puede encontrar un usuario
     *
     * @return
     */
    public List<Sucursales> getSucursalesList() {
        return sucursalesList;
    }

}
