/*
 Copyright 2014 GT Software.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package ar.com.gtsoftware.auth;

import ar.com.gtsoftware.bl.UsuariosService;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.UsuariosSearchFilter;
import org.primefaces.model.menu.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "authBackingBean")
@SessionScoped
public class AuthBackingBean implements Serializable {

    @EJB
    private UsuariosService usuariosService;
    @Inject
    private JSFHelper jsfHelper;

    private UsuariosDto usuarioLogueado;

    private MenuModel rolesMenuModel;

    public AuthBackingBean() {
    }

    private void armarMenu() {
        rolesMenuModel = new DefaultMenuModel();
        rolesMenuModel.addElement(armarCambiarClaveItem());
        rolesMenuModel.addElement(armarSalirItem());
        rolesMenuModel.addElement(new DefaultSeparator());

      /*  DefaultSubMenu rolesSubMenu = new DefaultSubMenu("Roles");

        for (UsuariosGrupos rol : getUserLoggedIn().getUsuariosGruposList()) {
            DefaultMenuItem rolMi = new DefaultMenuItem(rol.getNombreGrupo());
            rolesSubMenu.addElement(rolMi);
        }

        rolesSubMenu.setExpanded(false);

        rolesMenuModel.addElement(rolesSubMenu);*/
    }

    private MenuElement armarSalirItem() {
        DefaultMenuItem salirMi = new DefaultMenuItem("Salir");
        salirMi.setIcon("fa fa-fw fa-power-off");
        salirMi.setCommand("#{authBackingBean.logout}");
        return salirMi;
    }

    private MenuElement armarCambiarClaveItem() {
        DefaultMenuItem cambiarClaveMi = new DefaultMenuItem("Cambiar clave");
        cambiarClaveMi.setIcon("fa fa-fw fa-lock");
        cambiarClaveMi.setCommand(String.format("/protected/user/changePassword.xhtml%s", JSFHelper.JSF_REDIRECT_ESCAPED));
        return cambiarClaveMi;
    }

    public void logout() {
        jsfHelper.logout("/index.html");
    }

    public UsuariosDto getUserLoggedIn() {
        if (usuarioLogueado == null) {
            String usuP = jsfHelper.getUserPrincipalName();

            if (isNotEmpty(usuP)) {
                UsuariosSearchFilter sf = UsuariosSearchFilter.builder()
                        .login(usuP).build();
                sf.setNamedEntityGraph("rolesUsuarios");

                UsuariosDto usuario = usuariosService.findFirstBySearchFilter(sf);
                if (usuario != null) {
                    usuarioLogueado = usuario;
                    return usuario;
                }
            }
            throw new RuntimeException("Usuario no encontrado!");

        }
        return usuarioLogueado;
    }

    public MenuModel getRolesMenuModel() {
        if (rolesMenuModel == null) {
            armarMenu();
        }
        return rolesMenuModel;
    }

    public boolean isUserVendedor() {
        return jsfHelper.isUserInRole(Roles.VENDEDORES);
    }

    public boolean isUserCajero() {
        return jsfHelper.isUserInRole(Roles.CAJEROS);
    }

    public boolean isUserAdministrador() {
        return jsfHelper.isUserInRole(Roles.ADMINISTRADORES);
    }

}
