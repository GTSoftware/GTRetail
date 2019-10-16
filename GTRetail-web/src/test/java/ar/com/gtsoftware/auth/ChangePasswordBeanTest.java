/*
 * Copyright 2019 GT Software.
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
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.helper.JSFHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ChangePasswordBeanTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Mock
    private AuthBackingBean mockAuthBackingBean;
    @Mock
    private JSFHelper mockJsfHelper;
    @Mock
    private UsuariosService mockUsuarioService;
    @InjectMocks
    private ChangePasswordBean bean;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void deberiaActualizarClave() throws ServiceException {
        bean.setOldPassword("oldPass");
        bean.setNewPassword("newPass");
        bean.setNewPasswordRepeat("newPass");
        when(mockAuthBackingBean.getUserLoggedIn()).thenReturn(UsuariosDto.builder().id(1L).build());

        bean.actualizarClave();

        verify(mockJsfHelper).addInfoMessage(eq("La clave fue actualizada exitosamente"));
        verify(mockUsuarioService).changePassword(eq(1L), eq("newPass"));
        verifyNoMoreInteractions(mockJsfHelper);
        verificarInicializacionDeVariables();
    }

    @Test
    public void deberiaMostrarErrorCuandoNoFuePosibleActualizarClave() throws ServiceException {
        bean.setOldPassword("oldPass");
        bean.setNewPassword("newPass");
        bean.setNewPasswordRepeat("newPass");
        when(mockAuthBackingBean.getUserLoggedIn()).thenReturn(UsuariosDto.builder().id(1L).build());
        doThrow(new ServiceException("message")).when(mockUsuarioService).changePassword(1L, "newPass");

        bean.actualizarClave();

        verify(mockJsfHelper).addErrorMessage(eq("message"));
        verifyNoMoreInteractions(mockJsfHelper);
        verificarInicializacionDeVariables();
    }

    @Test
    public void noDeberiaPermitirCambiarClave() throws ServiceException {
        bean.setOldPassword("oldPass");
        bean.setNewPassword("different");
        bean.setNewPasswordRepeat("newPass");

        bean.actualizarClave();

        verify(mockJsfHelper).addErrorMessage(eq("La clave de control no coincide"));
        verifyNoMoreInteractions(mockJsfHelper);
        verifyZeroInteractions(mockAuthBackingBean);
        verificarInicializacionDeVariables();
    }

    @Test
    public void noDeberiaPermitirCambiarClaveCuandoNoSeIngresanValores() throws ServiceException {
        bean.setOldPassword("  ");
        bean.setNewPassword("different");
        bean.setNewPasswordRepeat("newPass");

        bean.actualizarClave();

        verify(mockJsfHelper).addErrorMessage(eq("Se deben completar todos los campos"));
        verifyNoMoreInteractions(mockJsfHelper);
        verifyZeroInteractions(mockAuthBackingBean);
        verificarInicializacionDeVariables();
    }

    private void verificarInicializacionDeVariables() {
        assertThat(bean.getOldPassword(), nullValue());
        assertThat(bean.getNewPassword(), nullValue());
        assertThat(bean.getNewPasswordRepeat(), nullValue());
    }
}