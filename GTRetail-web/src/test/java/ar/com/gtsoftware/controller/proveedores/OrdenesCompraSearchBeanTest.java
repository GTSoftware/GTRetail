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

package ar.com.gtsoftware.controller.proveedores;

import ar.com.gtsoftware.bl.ParametrosService;
import ar.com.gtsoftware.bl.ProveedoresOrdenesCompraService;
import ar.com.gtsoftware.dto.model.ProveedoresOrdenesCompraDto;
import ar.com.gtsoftware.search.OrdenCompraSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JSFUtil.class, JasperExportManager.class})
public class OrdenesCompraSearchBeanTest {

    @Mock
    private OrdenCompraSearchFilter mockFilter;
    @Mock
    private ProveedoresOrdenesCompraService mockService;
    @Mock
    private ParametrosService mockParametrosService;

    @InjectMocks
    private OrdenesCompraSearchBean ordenesCompraSearchBean;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void prepararFiltroConFecha() {
        when(mockFilter.hasOrderFields()).thenReturn(false);
        PowerMockito.mockStatic(JSFUtil.class);


        ordenesCompraSearchBean.prepareSearchFilter();

        verify(mockFilter).hasOrderFields();
        verify(mockFilter).addSortField("fechaAlta", false);
    }

    @Test
    public void noPrepararFiltroCuandoYaExistenOrdenamientos() {
        when(mockFilter.hasOrderFields()).thenReturn(true);

        ordenesCompraSearchBean.prepareSearchFilter();

        verify(mockFilter).hasOrderFields();
        verifyNoMoreInteractions(mockFilter);
    }

    @Test
    public void verOrdenCompra() {
        ProveedoresOrdenesCompraDto ordenesCompraDto = ProveedoresOrdenesCompraDto.builder().id(3L).build();
        String linkOrdenCompra = ordenesCompraSearchBean.verOrdenCompra(ordenesCompraDto);

        assertThat(linkOrdenCompra, is("edicion/index.xhtml?faces-redirect=true;&idOrdenCompra=3"));
    }

    @Test
    public void puedeImprimirOrdenCompra() throws IOException, JRException {
        PowerMockito.mockStatic(JSFUtil.class);
        PowerMockito.mockStatic(JasperExportManager.class);

        HttpServletResponse mockHttpServletResponse = mock(HttpServletResponse.class);
        ServletOutputStream mockServletOutputStream = mock(ServletOutputStream.class);
        when(mockHttpServletResponse.getOutputStream()).thenReturn(mockServletOutputStream);
        when(JSFUtil.getResponse()).thenReturn(mockHttpServletResponse);
        when(JSFUtil.getRealPath("/resources/reports/orden_compra.jasper")).thenReturn("src/main/webapp/resources/reports/orden_compra.jasper");

        when(mockParametrosService.findParametros(anyString())).thenReturn(Collections.emptyList());

        ordenesCompraSearchBean.imprimirOrdenCompra(ProveedoresOrdenesCompraDto.builder().id(3L).build());

        verify(mockParametrosService).findParametros(eq("empresa"));
        verify(mockHttpServletResponse).addHeader(eq("Content-disposition"), eq("attachment; filename=orden-compra-3.pdf"));
        verify(mockHttpServletResponse).getOutputStream();
        PowerMockito.verifyStatic();
        JasperExportManager.exportReportToPdfStream(Mockito.<JasperPrint>anyObject(), eq(mockServletOutputStream));
    }
}