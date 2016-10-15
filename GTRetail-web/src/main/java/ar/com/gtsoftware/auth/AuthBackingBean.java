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

import ar.com.gtsoftware.eao.UsuariosFacade;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.model.UsuariosGrupos;
import ar.com.gtsoftware.search.UsuariosSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "authBackingBean")
@SessionScoped
public class AuthBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UsuariosFacade usuariosFacade;

    private Usuarios usuarioLogueado;
    private static final Logger LOG = Logger.getLogger(AuthBackingBean.class.getName());
    private MenuModel rolesMenuModel;

    /**
     * Creates a new instance of AuthBackingBean
     */
    public AuthBackingBean() {
    }

    @PostConstruct
    public void init() {

        rolesMenuModel = new DefaultMenuModel();
        for (UsuariosGrupos rol : getUserLoggedIn().getUsuariosGruposList()) {
            DefaultMenuItem rolMi = new DefaultMenuItem(String.format("Rol: %s", rol.getNombreGrupo()));
            rolesMenuModel.addElement(rolMi);
        }
        DefaultMenuItem cambiarClaveMi = new DefaultMenuItem("Cambiar clave");
        cambiarClaveMi.setIcon("fa fa-fw fa-lock");
        cambiarClaveMi.setCommand(String.format("/protected/user/changePassword.xhtml%s", JSFUtil.JSF_REDIRECT_ESCAPED));
        rolesMenuModel.addElement(cambiarClaveMi);

        DefaultMenuItem salirMi = new DefaultMenuItem("Salir");
        salirMi.setIcon("fa fa-fw fa-power-off");
        salirMi.setCommand("#{authBackingBean.logout}");
        rolesMenuModel.addElement(salirMi);

    }

    public void logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        try {
            request.getSession(false).invalidate();
            request.logout();
            ec.redirect(ec.getRequestContextPath() + "/index.html");
        } catch (IOException | ServletException e) {
            LOG.log(Level.SEVERE, "Logout error: {0}", e.getMessage());
        }
    }

    public Usuarios getUserLoggedIn() {
        if (usuarioLogueado == null) {
            Principal usuP = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

            if (usuP != null) {
                UsuariosSearchFilter sf = new UsuariosSearchFilter(usuP.getName());
                sf.setNamedEntityGraph("rolesUsuarios");

                Usuarios usuario = usuariosFacade.findFirstBySearchFilter(sf);
                if (usuario != null) {
                    usuarioLogueado = usuario;
                    return usuario;
                }
            }
            Usuarios usu = new Usuarios();
            usu.setNombreUsuario("ANONYMOUS");

            return usu;
        }
        return usuarioLogueado;
    }

    public MenuModel getRolesMenuModel() {
        return rolesMenuModel;
    }

}
