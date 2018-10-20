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
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.faces.context.FacesContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthBackingBeanTest {

    @Mock
    private UsuariosService mockUsuariosService;

    @Mock
    private FacesContext mockFacesContext;

    @InjectMocks
    private AuthBackingBean authBackingBean;

    private final UsuariosDto usuarioDto = UsuariosDto.builder()
            .id(1L)
            .login("TestUser")
            .nombreUsuario("Test User")
            .build();
    ;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        authBackingBean = new AuthBackingBean();
    }

    @Test
    @Ignore //TODO see how to mock facesContext
    public void getUserLoggedIn() {
        when(mockUsuariosService.findFirstBySearchFilter(any())).thenReturn(usuarioDto);
        when(mockFacesContext.getExternalContext().getUserPrincipal().getName()).thenReturn("Test");

        assertEquals(authBackingBean.getUserLoggedIn(), usuarioDto);
    }


}