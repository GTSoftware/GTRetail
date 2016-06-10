/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gtsoftware.automated.tests.gtretail;

import ar.com.gtsoftware.automated.tests.AutomatedTest;
import ar.com.gtsoftware.pages.gtretail.PageManager;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GTRetailTest extends AutomatedTest {

    @Test
    public void logInValidUser() throws IOException {
        PageManager pm = new PageManager();
        getPageManagers().put(Thread.currentThread().getStackTrace()[1].getMethodName(), pm);

        Assert.assertTrue(pm.getLoginPage().logIn("admin", "admin"),
                "Se debería haber podido ingresar con ese usuario y clave");

        pm.saveScreenshot("LogInSuccess.png");

    }

    @Test
    public void searchUsuario() {
        PageManager pm = new PageManager();
        getPageManagers().put(Thread.currentThread().getStackTrace()[1].getMethodName(), pm);

        pm.getLoginPage().logIn("system", "admin");

        Assert.assertTrue(pm.getHomePage().goAdministrarUsuariosPage(),
                "Se debe poder navegar hasta la búsqueda de usuarios");
        pm.saveScreenshot("BusquedaUsuarios.png");

        Assert.assertTrue(pm.getSearchUsuariosPage().searchUsuario("system"),
                "Se debe poder buscar un usuario");
        pm.saveScreenshot("BuscarUsuario.png");

        Assert.assertTrue(pm.getSearchUsuariosPage().filaTablaUsuario(1).getText().contains("system"),
                "Se debe poder encontrar el usuario en la tabla");
        pm.saveScreenshot("UsuarioEncontrado.png");

    }

}
