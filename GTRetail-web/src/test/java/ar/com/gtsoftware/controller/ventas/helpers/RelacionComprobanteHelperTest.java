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

package ar.com.gtsoftware.controller.ventas.helpers;

import ar.com.gtsoftware.controller.ventas.dto.ComprobanteRelacionado;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.ComprobantesLineasDto;
import ar.com.gtsoftware.dto.model.NegocioTiposComprobanteDto;
import ar.com.gtsoftware.dto.model.ProductosDto;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static ar.com.gtsoftware.enums.NegocioTiposComprobanteEnum.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RelacionComprobanteHelperTest {


    private RelacionComprobanteHelper helper;
    @Mock
    private ComprobantesDto mockComprobanteOriginal;
    private AtomicInteger itemCounter = new AtomicInteger(1);

    @Before
    public void setUp() {
        initMocks(this);
        helper = new RelacionComprobanteHelper(itemCounter);
    }

    @Test
    public void deberiaGenerarComprobanteSinProductosReservados() {
        when(mockComprobanteOriginal.getComprobantesLineasList()).thenReturn(
                crearLineasComprobante(1)
        );
        when(mockComprobanteOriginal.getTipoComprobante()).thenReturn(
                NegocioTiposComprobanteDto.builder().id(PRESUPUESTO.getId()).build()
        );

        ComprobanteRelacionado comprobanteRelacionado = helper.generarComprobanteRelacionado(mockComprobanteOriginal,
                null);

        assertThat(comprobanteRelacionado, notNullValue());
        assertThat(comprobanteRelacionado.getComprobantesLineas(), notNullValue());
        assertThat(comprobanteRelacionado.getComprobantesLineas().size(), is(1));
        ComprobantesLineasDto linea = comprobanteRelacionado.getComprobantesLineas().get(0);
        assertThat(linea.getId(), nullValue());
        assertThat(linea.getIdComprobante(), nullValue());
    }

    @Test
    public void deberiaGenerarComprobanteConProductosReservados() {
        when(mockComprobanteOriginal.getComprobantesLineasList()).thenReturn(
                crearLineasComprobante(3)
        );
        when(mockComprobanteOriginal.getTipoComprobante()).thenReturn(
                NegocioTiposComprobanteDto.builder().id(PRESUPUESTO.getId()).build()
        );

        List<Long> idsReservados = Collections.singletonList(1L);
        ComprobanteRelacionado comprobanteRelacionado = helper.generarComprobanteRelacionado(mockComprobanteOriginal,
                idsReservados);

        assertThat(comprobanteRelacionado, notNullValue());
        assertThat(comprobanteRelacionado.getComprobantesLineas(), notNullValue());
        assertThat(comprobanteRelacionado.getComprobantesLineas().size(), is(2));
        for (ComprobantesLineasDto linea : comprobanteRelacionado.getComprobantesLineas()) {
            assertThat(idsReservados.contains(linea.getIdProducto().getId()), is(false));
            assertThat(linea.getId(), nullValue());
            assertThat(linea.getIdComprobante(), nullValue());
            assertThat(linea.getVersion(), nullValue());
            assertThat(linea.getItem(), not((99)));
        }
    }

    @Test
    public void deberiaGenerarFacturaCuandoElOriginalEsNotaDeCredito() {
        when(mockComprobanteOriginal.getTipoComprobante()).thenReturn(
                NegocioTiposComprobanteDto.builder().id(NOTA_DE_CREDITO.getId()).build()
        );

        ComprobanteRelacionado comprobanteRelacionado = helper.generarComprobanteRelacionado(mockComprobanteOriginal,
                null);

        assertThat(comprobanteRelacionado, notNullValue());
        assertThat(comprobanteRelacionado.getTipoComprobante(), is(FACTURA));
    }

    @Test
    public void deberiaGenerarFacturaCuandoElOriginalEsPresupuesto() {
        when(mockComprobanteOriginal.getTipoComprobante()).thenReturn(
                NegocioTiposComprobanteDto.builder().id(PRESUPUESTO.getId()).build()
        );

        ComprobanteRelacionado comprobanteRelacionado = helper.generarComprobanteRelacionado(mockComprobanteOriginal,
                null);

        assertThat(comprobanteRelacionado, notNullValue());
        assertThat(comprobanteRelacionado.getTipoComprobante(), is(FACTURA));
    }

    @Test
    public void deberiaGenerarNotaDeCreditoCuandoElOriginalEsFactura() {
        when(mockComprobanteOriginal.getTipoComprobante()).thenReturn(
                NegocioTiposComprobanteDto.builder().id(FACTURA.getId()).build()
        );

        ComprobanteRelacionado comprobanteRelacionado = helper.generarComprobanteRelacionado(mockComprobanteOriginal,
                null);

        assertThat(comprobanteRelacionado, notNullValue());
        assertThat(comprobanteRelacionado.getTipoComprobante(), is(NOTA_DE_CREDITO));
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
                    .idComprobante(ComprobantesDto.builder().id(1L).build())
                    .item(99)
                    .build();
            result.add(linea);
        }
        return result;
    }
}