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

import ar.com.gtsoftware.bl.NegocioTiposComprobanteService;
import ar.com.gtsoftware.bl.VentasService;
import ar.com.gtsoftware.controller.ventas.dto.ComprobanteRelacionado;
import ar.com.gtsoftware.controller.ventas.helpers.RelacionComprobanteHelper;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.enums.NegocioTiposComprobanteEnum;
import ar.com.gtsoftware.helper.JSFHelper;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShopCartBeanTest {

    private ComprobantesDto venta;
    @Mock
    private VentasService mockVentasService;
    @Mock
    private NegocioTiposComprobanteService mockNegocioTiposComprobanteService;
    @Mock
    private OfertasHelper mockOfertasHelper;
    @Mock
    private RelacionComprobanteHelper mockRelacionComprobanteHelper;
    @Mock
    private JSFHelper mockJsfHelper;

    @InjectMocks
    private ShopCartBean shopCartBean;

    @Before
    public void setUp() {
        initMocks(this);
        venta = ComprobantesDto.builder().build();
        shopCartBean.setVenta(venta);
    }

    @Test
    public void deberiaVolverAlPasoInicialEnPagoSinPagos() {
        List<ComprobantesPagosDto> pagosList = Collections.emptyList();

        venta.setPagosList(pagosList);

        String actual = shopCartBean.volverPasoInicialEnPago();

        assertThat(actual, is("index.xhtml?faces-redirect=true"));
    }

    @Test
    public void deberiaVolverAlPasoInicialEnPagoConPagos() {
        ComprobantesPagosDto pago = new ComprobantesPagosDto();
        List<ComprobantesPagosDto> pagosList = new ArrayList<>(1);
        pagosList.add(pago);

        ComprobantesLineasDto linea = ComprobantesLineasDto.builder().item(1).build();
        List<ComprobantesLineasDto> lineasVentaList = new ArrayList<>(1);
        lineasVentaList.add(linea);

        venta.setPagosList(pagosList);
        pago.setProductoRecargoItem(1);
        venta.setComprobantesLineasList(lineasVentaList);

        String actual = shopCartBean.volverPasoInicialEnPago();

        assertThat(actual, is("index.xhtml?faces-redirect=true"));
        assertThat(lineasVentaList.size(), is(0));
        assertThat(pagosList.size(), is(0));

    }

    @Test
    public void deberiaInicializarLosPagosCuandoNoEsPostback() {
        when(mockJsfHelper.isNotPostback()).thenReturn(true);
        venta.setPagosList(new ArrayList<>());
        shopCartBean.formaPagoDefecto = NegocioFormasPagoDto.builder().id(1L).build();
        venta.setTotal(BigDecimal.TEN);

        shopCartBean.initPagos();

        ComprobantesPagosDto pagoAgregado = venta.getPagosList().get(0);
        assertThat(pagoAgregado.getMontoPago(), is(BigDecimal.TEN));
        assertThat(pagoAgregado.getMontoPagado(), is(BigDecimal.ZERO));
        assertThat(pagoAgregado.getIdFormaPago().getId(), is(1L));
        assertThat(venta.getSaldo(), is(BigDecimal.ZERO));
    }

    @Test
    public void noDeberiaInicializarLosPagosCuandoEsPostback() {
        when(mockJsfHelper.isNotPostback()).thenReturn(false);
        venta.setPagosList(new ArrayList<>());
        shopCartBean.formaPagoDefecto = NegocioFormasPagoDto.builder().id(1L).build();
        venta.setTotal(BigDecimal.TEN);

        shopCartBean.initPagos();

        assertThat(venta.getPagosList().isEmpty(), is(true));
    }

    @Test
    public void deberiaRelacionarComprobante() {
        shopCartBean.setIdComprobanteRelacionado(1L);
        shopCartBean.setProductoRedondeo(ProductosDto.builder().id(1L).build());
        PersonasDto cliente = PersonasDto.builder().id(5L).build();

        ComprobantesDto compOriginal = ComprobantesDto.builder().id(1L)
                .tipoComprobante(NegocioTiposComprobanteDto.builder().id(1L).build())
                .comprobantesLineasList(crearLineasComprobante(3))
                .idPersona(cliente)
                .build();
        when(mockVentasService.obtenerComprobante(anyLong())).thenReturn(compOriginal);
        when(mockRelacionComprobanteHelper.generarComprobanteRelacionado(eq(compOriginal), anyList()))
                .thenReturn(new ComprobanteRelacionado(crearLineasComprobante(2),
                        NegocioTiposComprobanteEnum.NOTA_DE_CREDITO));

        shopCartBean.cargarComprobanteRelacionado();

        assertThat(venta.getTipoComprobante().getId(), is(NegocioTiposComprobanteEnum.NOTA_DE_CREDITO.getId()));
        assertThat(venta.getComprobantesLineasList().size(), is(2));
        assertThat(venta.getTotal().signum(), is(1));
        assertThat(venta.getIdPersona(), is(cliente));
    }

    @Test
    public void deberiaMostrarErrorCuandoNoExisteComprobanteRelacionado() {
        when(mockVentasService.obtenerComprobante(anyLong())).thenReturn(null);
        shopCartBean.setIdComprobanteRelacionado(1L);

        shopCartBean.cargarComprobanteRelacionado();

        verify(mockJsfHelper).addErrorMessage("Comprobante no encontrado");
    }

    private List<ComprobantesLineasDto> crearLineasComprobante(int cantElementos) {
        List<ComprobantesLineasDto> result = new ArrayList<>(cantElementos);

        for (int i = 1; i <= cantElementos; i++) {
            long id = Integer.toUnsignedLong(i);
            ComprobantesLineasDto linea = ComprobantesLineasDto.builder().id(id)
                    .cantidad(BigDecimal.ONE)
                    .idProducto(ProductosDto.builder().id(id).build())
                    .precioUnitario(BigDecimal.valueOf(RandomUtils.nextDouble(0, 1000)))
                    .descripcion("Producto " + i)
                    .build();
            result.add(linea);
        }
        return result;
    }
}