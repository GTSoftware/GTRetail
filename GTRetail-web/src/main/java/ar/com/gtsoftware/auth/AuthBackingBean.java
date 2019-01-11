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
import ar.com.gtsoftware.search.UsuariosSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "authBackingBean")
@SessionScoped
public class AuthBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UsuariosService usuariosService;


    private UsuariosDto usuarioLogueado;

    private MenuModel rolesMenuModel;

    public AuthBackingBean() {
    }


    @PostConstruct
    public void init() {

        rolesMenuModel = new DefaultMenuModel();

        DefaultMenuItem cambiarClaveMi = new DefaultMenuItem("Cambiar clave");
        cambiarClaveMi.setIcon("fa fa-fw fa-lock");
        cambiarClaveMi.setCommand(String.format("/protected/user/changePassword.xhtml%s", JSFUtil.JSF_REDIRECT_ESCAPED));
        rolesMenuModel.addElement(cambiarClaveMi);

        DefaultMenuItem salirMi = new DefaultMenuItem("Salir");
        salirMi.setIcon("fa fa-fw fa-power-off");
        salirMi.setCommand("#{authBackingBean.logout}");
        rolesMenuModel.addElement(salirMi);

        DefaultSeparator separator = new DefaultSeparator();
        rolesMenuModel.addElement(separator);

      /*  DefaultSubMenu rolesSubMenu = new DefaultSubMenu("Roles");

        for (UsuariosGrupos rol : getUserLoggedIn().getUsuariosGruposList()) {
            DefaultMenuItem rolMi = new DefaultMenuItem(rol.getNombreGrupo());
            rolesSubMenu.addElement(rolMi);
        }

        rolesSubMenu.setExpanded(false);

        rolesMenuModel.addElement(rolesSubMenu);*/

    }

    public void logout() {
        JSFUtil.logOut("/index.html");
    }

    public UsuariosDto getUserLoggedIn() {
        if (usuarioLogueado == null) {
            String usuP = JSFUtil.getUserPrincipalName();

            if (StringUtils.isNotEmpty(usuP)) {
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
        return rolesMenuModel;
    }

    public boolean isUserVendedor() {
        return JSFUtil.isUserInRole(Roles.VENDEDORES);
    }

    public boolean isUserCajero() {
        return JSFUtil.isUserInRole(Roles.CAJEROS);
    }

    public boolean isUserAdministrador() {
        return JSFUtil.isUserInRole(Roles.ADMINISTRADORES);
    }

}
