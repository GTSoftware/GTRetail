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

package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.ComprobantesLineasDto;
import ar.com.gtsoftware.dto.model.ComprobantesPagosDto;
import ar.com.gtsoftware.dto.model.NegocioFormasPagoDto;
import ar.com.gtsoftware.utils.JSFUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JSFUtil.class})
public class ShopCartBeanTest {

    private ShopCartBean shopCartBean;

    @Mock
    private ComprobantesDto mockVenta;


    @Before
    public void setUp() {
        PowerMockito.mockStatic(JSFUtil.class);
        shopCartBean = new ShopCartBean();
        shopCartBean.setVenta(mockVenta);

    }

    @Test
    public void deberiaVolverAlPasoInicialEnPagoSinPagos() {
        List<ComprobantesPagosDto> pagosList = Collections.emptyList();

        when(mockVenta.getPagosList()).thenReturn(pagosList);

        String actual = shopCartBean.volverPasoInicialEnPago();

        verify(mockVenta).getPagosList();
        assertThat(actual, is("index.xhtml?faces-redirect=true"));
    }

    @Test
    public void deberiaVolverAlPasoInicialEnPagoConPagos() {
        ComprobantesPagosDto mockPago = mock(ComprobantesPagosDto.class);
        List<ComprobantesPagosDto> pagosList = new ArrayList<>(1);
        pagosList.add(mockPago);

        ComprobantesLineasDto linea = ComprobantesLineasDto.builder().item(1).build();
        List<ComprobantesLineasDto> lineasVentaList = new ArrayList<>(1);
        lineasVentaList.add(linea);

        when(mockVenta.getPagosList()).thenReturn(pagosList);
        when(mockPago.getProductoRecargoItem()).thenReturn(1);
        when(mockVenta.getComprobantesLineasList()).thenReturn(lineasVentaList);


        String actual = shopCartBean.volverPasoInicialEnPago();

        verify(mockVenta).getPagosList();
        verify(mockPago).getProductoRecargoItem();
        verify(mockVenta, times(3)).getComprobantesLineasList();

        assertThat(actual, is("index.xhtml?faces-redirect=true"));
        assertThat(lineasVentaList.size(), is(0));
        assertThat(pagosList.size(), is(0));

    }

    @Test
    public void deberiaInicializarLosPagosCuandoNoEsPostback() {
        when(JSFUtil.isPostback()).thenReturn(false);
        when(mockVenta.getPagosList()).thenReturn(Collections.emptyList());
        shopCartBean.formaPagoDefecto = mock(NegocioFormasPagoDto.class);
        shopCartBean.pagoActual = mock(ComprobantesPagosDto.class);
        when(mockVenta.getTotal()).thenReturn(BigDecimal.TEN);

        shopCartBean.initPagos();

        verify(mockVenta, times(2)).getPagosList();
        verify(shopCartBean.pagoActual).setMontoPago(eq(BigDecimal.TEN));
        verify(shopCartBean.pagoActual).setMontoPagado(eq(BigDecimal.ZERO));
        verify(shopCartBean.pagoActual).setIdFormaPago(eq(shopCartBean.formaPagoDefecto));
        verify(mockVenta, times(3)).setSaldo(eq(BigDecimal.TEN));

    }

    @Test
    public void noDeberiaInicializarLosPagosCuandoEsPostback() {
        when(JSFUtil.isPostback()).thenReturn(true);
        when(mockVenta.getPagosList()).thenReturn(Collections.emptyList());

        shopCartBean.formaPagoDefecto = mock(NegocioFormasPagoDto.class);
        shopCartBean.pagoActual = mock(ComprobantesPagosDto.class);
        when(mockVenta.getTotal()).thenReturn(BigDecimal.TEN);

        shopCartBean.initPagos();

        verify(mockVenta, times(0)).getPagosList();
        verify(mockVenta, times(0)).setSaldo(eq(BigDecimal.TEN));

    }
}