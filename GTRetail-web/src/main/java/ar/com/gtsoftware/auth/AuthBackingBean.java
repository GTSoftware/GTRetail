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
import ar.com.gtsoftware.search.UsuariosSearchFilter;
import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Named(value = "authBackingBean")
@SessionScoped
public class AuthBackingBean implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
    private static Logger log = Logger.getLogger(AuthBackingBean.class.getName());

    /**
     * Creates a new instance of AuthBackingBean
     */
    public AuthBackingBean() {
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public Usuarios getUserLoggedIn() {
        Principal usuP = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (usuP != null) {
            Usuarios usuario = usuariosFacade.findByLogIn(usuP.getName());
            if (usuario != null) {
                return usuario;
            }
        }
        Usuarios usu = new Usuarios();
        usu.setNombreUsuario("ANONYMOUS");

        return usu;
    }
    /*
     public boolean tienePrivilegio(Integer idPrivilegio) {
     HashMap<Integer, String> privilegios = obtenerPrivilegiosUsuario(getUserLoggedIn());
     if (privilegios != null && !privilegios.isEmpty()) {
     privilegios.containsKey(idPrivilegio);
     }
     return false;
     }

     private HashMap<Integer, String> obtenerPrivilegiosUsuario(Usuarios usuario) {
     HashMap<Integer, String> privilegiosList = new HashMap<Integer, String>();
     for (UsuariosGrupos g : usuario.getUsuariosGruposList()) {
     for (Privilegios p : g.getPrivilegiosList()) {
     privilegiosList.put(p.getIdPrivilegio(), p.getNombrePrivilegio());
     }
     }
     return privilegiosList;
     }
     */
}
