/*
 * Copyright 2016 GT Software.
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
import ar.com.gtsoftware.utils.JSFUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class AuthBackingBeanTest {

    private AuthBackingBean authBackingBean;

    private UsuariosFacade usuariosFacade;
    private JSFUtil jsfUtil;

    public AuthBackingBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        usuariosFacade = mock(UsuariosFacade.class);
        jsfUtil = mock(JSFUtil.class);
        authBackingBean = new AuthBackingBean(usuariosFacade, jsfUtil);

    }

    @After
    public void tearDown() {
        authBackingBean = null;
        jsfUtil = null;
        usuariosFacade = null;
    }

    /**
     * Test of getUserLoggedIn method, of class AuthBackingBean.
     */
    @Test
    public void testGetUserLoggedIn() {
        System.out.println("getUserLoggedIn");

        Usuarios logueado = new Usuarios();
        logueado.setNombreUsuario("Usuario");
        Mockito.when(usuariosFacade.findFirstBySearchFilter(any())).thenReturn(logueado);
        Mockito.when(jsfUtil.getUserPrincipalName()).thenReturn("Usuario");
        Assert.assertEquals("Usuario", authBackingBean.getUserLoggedIn().getNombreUsuario());
    }

    @Test(expected = RuntimeException.class)
    public void testGetUserLoggedInInvalid() {
        System.out.println("getUserLoggedInInvalid");

        Usuarios logueado = new Usuarios();
        logueado.setNombreUsuario("Usuario");
        Mockito.when(usuariosFacade.findFirstBySearchFilter(any())).thenReturn(null);
        Mockito.when(jsfUtil.getUserPrincipalName()).thenReturn("Usuario");
        authBackingBean.getUserLoggedIn();
    }

    public void testGetRolesMenuModel() {
        System.out.println("getRolesMenuModel");

        Usuarios logueado = new Usuarios();
        logueado.setNombreUsuario("Usuario");
        Mockito.when(usuariosFacade.findFirstBySearchFilter(any())).thenReturn(logueado);
        Mockito.when(jsfUtil.getUserPrincipalName()).thenReturn("Usuario");
        authBackingBean.init();
        Assert.assertNotNull("El menu de roles no deberia ser nulo", authBackingBean.getRolesMenuModel());

    }

}
