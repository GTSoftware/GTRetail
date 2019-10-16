/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.auth;

import ar.com.gtsoftware.bl.UsuariosService;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.helper.JSFHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthBackingBeanTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Mock
    private UsuariosService mockUsuariosService;
    @Mock
    private JSFHelper mockJsfHelper;
    @InjectMocks
    private AuthBackingBean authBackingBean;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void deberiaObtenerUsuarioLogueado() {
        final UsuariosDto usuarioDto = UsuariosDto.builder()
                .id(1L)
                .login("TestUser")
                .nombreUsuario("Test User")
                .build();
        when(mockUsuariosService.findFirstBySearchFilter(any())).thenReturn(usuarioDto);
        when(mockJsfHelper.getUserPrincipalName()).thenReturn("Test");

        UsuariosDto userLoggedIn = authBackingBean.getUserLoggedIn();
        authBackingBean.getUserLoggedIn();

        assertEquals(userLoggedIn, usuarioDto);
        verify(mockUsuariosService, times(1)).findFirstBySearchFilter(any());
        verify(mockJsfHelper, times(1)).getUserPrincipalName();
    }

    @Test
    public void deberiaFallarCuandoElUsuarioNoExiste() {
        when(mockUsuariosService.findFirstBySearchFilter(any())).thenReturn(null);
        when(mockJsfHelper.getUserPrincipalName()).thenReturn("Test");

        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Usuario no encontrado!");
        authBackingBean.getUserLoggedIn();

        fail("No debería haber llegado hasta acá");
    }

    @Test
    public void deberiaHacerLogout() {
        authBackingBean.logout();

        verify(mockJsfHelper).logout(eq("/index.html"));
    }
}