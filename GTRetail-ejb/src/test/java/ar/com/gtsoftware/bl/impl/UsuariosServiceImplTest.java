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

package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.eao.UsuariosFacade;
import ar.com.gtsoftware.model.Usuarios;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UsuariosServiceImplTest {

    @Mock
    private UsuariosFacade facade;

    @Mock
    private Usuarios mockedUsuario;

    @InjectMocks
    private UsuariosServiceImpl service = new UsuariosServiceImpl();

    private static final Long ID_USUARIO = 1L;
    private static final String HASHED_PASSWORD = "jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=";//aka 123456


    @Before
    public void setUp() {
        initMocks(this);

        when(facade.find(ID_USUARIO)).thenReturn(mockedUsuario);
        when(mockedUsuario.getPassword()).thenReturn(HASHED_PASSWORD);

    }


    @Test
    public void shouldChangePassword() throws ServiceException {
        service.changePassword(ID_USUARIO, "nuevaClave");
        verify(mockedUsuario).setPassword(anyString());
        verify(facade).edit(any());
    }

    @Test
    public void shouldNotUpdateWithShortPassword() {
        try {
            service.changePassword(ID_USUARIO, "short");
        } catch (ServiceException ex) {
            assertEquals("La clave no puede contener menos de 6 dìgitos", ex.getMessage());
        }
        verifyZeroInteractions(facade);

    }

    @Test
    public void shouldNotUpdateWithSamePassword() {
        try {
            service.changePassword(ID_USUARIO, "123456");
        } catch (ServiceException ex) {
            assertEquals("La clave anterior y la nueva coinciden", ex.getMessage());
        }
        verify(facade).find(ID_USUARIO);
        verify(mockedUsuario, times(0)).setPassword(anyString());
        verify(facade, times(0)).edit(any());

    }

    @Test
    public void shouldFailOnInexistentUser() {
        when(facade.find(ID_USUARIO)).thenReturn(null);

        try {
            service.changePassword(ID_USUARIO, "123456");
        } catch (ServiceException ex) {
            assertEquals("Usuario inexistente", ex.getMessage());
        }
        verify(facade).find(ID_USUARIO);
        verify(mockedUsuario, times(0)).setPassword(anyString());
        verify(facade, times(0)).edit(any());

    }
}