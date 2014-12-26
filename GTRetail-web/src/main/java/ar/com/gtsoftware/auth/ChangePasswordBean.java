/*
 * Copyright 2014 Dilcar S.A..
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
package ar.com.gtsoftware.auth;

import ar.com.gtsoftware.eao.UsuariosFacade;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.utils.HashUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 * Controlador para el cambio de claves de los usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @since 1.0.0
 * @version 1.0.0
 */
@Named(value = "changePasswordBean")
@ViewScoped
public class ChangePasswordBean implements Serializable {

    @Inject
    private AuthBackingBean authBackingBean;

    @EJB
    private UsuariosFacade usuarioFacade;

    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;

    /**
     * Creates a new instance of ChangePasswordBean
     */
    public ChangePasswordBean() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }

    private boolean cambioValido() {
        if (oldPassword == null || newPassword == null || newPasswordRepeat == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cambio de clave", "Se deben completar todos los campos"));

            return false;
        }
        if (newPassword.length() < 6) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cambio de clave", "La clave debe tener 6 o más caracteres"));
            return false;
        }
        String oldPassHashed = HashUtils.getHash(oldPassword);
        if (!oldPassHashed.equals(authBackingBean.getUserLoggedIn().getPassword())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cambio de clave", "La contraseña anterior debe coincidir"));
            return false;
        }
        if (!newPassword.equals(newPasswordRepeat)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cambio de clave", "La clave de control no coincide"));
            return false;
        }
        String newPassHashed = HashUtils.getHash(newPassword);

        if (newPassHashed.equals(oldPassHashed)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cambio de clave", "La nueva clave no puede ser la misma que la anterior"));
            return false;
        }
        return true;
    }

    public void actualizarClave() {
        if (cambioValido()) {
            String newPassHashed = HashUtils.getHash(newPassword);
            Usuarios usuario = authBackingBean.getUserLoggedIn();
            usuario.setPassword(newPassHashed);
            try {
                usuarioFacade.edit(usuario);
            } catch (Exception ex) {
                Logger.getLogger(ChangePasswordBean.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cambio de clave", "La clave no pudo ser actualizada"));
            }
            oldPassword = null;
            newPassword = null;
            newPasswordRepeat = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambio de clave", "La clave fue actualizada exitosamente"));
        }
    }
}
