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

import ar.com.gtsoftware.bl.UsuariosService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.helper.JSFHelper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador para el cambio de claves de los usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@ManagedBean(name = "changePasswordBean")
@ViewScoped
public class ChangePasswordBean implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(ChangePasswordBean.class.getName());

    @ManagedProperty(value = "#{authBackingBean}")
    @Getter
    @Setter
    private AuthBackingBean authBackingBean;
    @Inject
    private JSFHelper jsfHelper;
    @EJB
    private UsuariosService usuarioService;

    @Getter
    @Setter
    private String oldPassword;
    @Getter
    @Setter
    private String newPassword;
    @Getter
    @Setter
    private String newPasswordRepeat;

    /**
     * Creates a new instance of ChangePasswordBean
     */
    public ChangePasswordBean() {
    }

    private boolean cambioValido() {
        if (StringUtils.isBlank(oldPassword)
                || StringUtils.isBlank(newPassword)
                || StringUtils.isBlank(newPasswordRepeat)) {
            jsfHelper.addErrorMessage("Se deben completar todos los campos");

            return false;
        }

        if (!newPassword.equals(newPasswordRepeat)) {
            jsfHelper.addErrorMessage("La clave de control no coincide");
            return false;
        }

        return true;
    }

    public void actualizarClave() {
        if (cambioValido()) {
            try {
                usuarioService.changePassword(authBackingBean.getUserLoggedIn().getId(), newPassword);
                jsfHelper.addInfoMessage("La clave fue actualizada exitosamente");
            } catch (ServiceException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                jsfHelper.addErrorMessage(ex.getMessage());
            }
        }
        inicializarValores();
    }

    private void inicializarValores() {
        oldPassword = null;
        newPassword = null;
        newPasswordRepeat = null;
    }

}
