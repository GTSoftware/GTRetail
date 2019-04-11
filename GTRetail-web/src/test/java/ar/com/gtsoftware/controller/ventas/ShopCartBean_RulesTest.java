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

package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.rules.*;
import ar.com.gtsoftware.utils.DroolsUtility;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.StatelessKieSession;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class ShopCartBean_RulesTest {


    private StatelessKieSession session;
    private ShopCartBean bean;
    private ComprobantesDto venta;

    @Before
    public void setUp() throws Exception {
        Logger.getLogger("").setLevel(Level.FINE);
        Logger.getLogger("").getHandlers()[0].setLevel(Level.FINE);

        List<OfertaDto> reglas = construirReglas();
        session = DroolsUtility.loadSession(reglas, "ar/com/gtsoftware/drools/templates/Product.drt");
        bean = new ShopCartBean();
        venta = new ComprobantesDto();
        venta.setTipoComprobante(NegocioTiposComprobanteDto.builder().nombreComprobante("Factura").build());
        venta.setId(1L);
        bean.setProductoRedondeo(new ProductosDto());

        bean.setVenta(venta);
    }

    private List<OfertaDto> construirReglas() {

        OfertaDto rp1 = OfertaDto.builder()
                .condiciones(Arrays.asList(
                        Condicion.builder().campo(Campo.DESCRIPCION)
                                .operacion(Operacion.CONTIENE)
                                .valor("tornillo")
                                .build(),
                        Condicion.builder()
                                .campo(Campo.CANTIDAD)
                                .operacion(Operacion.MAYOR_IGUAL)
                                .valor("100").build()))
                .descuento(new BigDecimal(10))
                .textoOferta("Oferta tornillos")
                .tipoAccion(TipoAccion.DESCUENTO_PORCENTAJE)
                .build();

        OfertaDto rp2 = OfertaDto.builder()
                .condiciones(Arrays.asList(
                        Condicion.builder().campo(Campo.NOMBRE_RUBRO)
                                .operacion(Operacion.CONTIENE)
                                .valor("cortes")
                                .build(),
                        Condicion.builder()
                                .campo(Campo.CANTIDAD)
                                .operacion(Operacion.MAYOR_IGUAL)
                                .valor("10").build()))
                .descuento(new BigDecimal(12))
                .textoOferta("Oferta cortes a medida")
                .tipoAccion(TipoAccion.DESCUENTO_MONTO_FIJO)
                .build();

        OfertaDto rp3 = OfertaDto.builder()
                .condiciones(Arrays.asList(
                        Condicion.builder()
                                .campo(Campo.CANTIDAD)
                                .operacion(Operacion.MULTIPLO)
                                .valor("12").build(),
                        Condicion.builder()
                                .campo(Campo.CANTIDAD)
                                .operacion(Operacion.MAYOR_IGUAL)
                                .valor("12").build()))
                .descuento(new BigDecimal(17))
                .textoOferta("Oferta multiplo de")
                .tipoAccion(TipoAccion.DESCUENTO_MONTO_FIJO)
                .build();

        return Arrays.asList(rp1, rp2, rp3);
    }

    @Test
    public void shouldPassOnContainsRule() {
        ProductosDto prod = ProductosDto.builder()
                .id(1234L)
                .idRubro(ProductosRubrosDto.builder().nombreRubro("SARASA").build())
                .build();

        ComprobantesLineasDto linea1 = ComprobantesLineasDto.builder().descripcion("tornillos mito 10x50")
                .item(1)
                .precioUnitario(new BigDecimal(5))
                .cantidad(new BigDecimal(102))
                .subTotal(new BigDecimal(5))
                .idComprobante(venta)
                .idProducto(prod)
                .build();

        ComprobantesLineasDto linea2 = ComprobantesLineasDto.builder().descripcion("Otro item de prueba")
                .item(2)
                .idProducto(prod)
                .precioUnitario(new BigDecimal(25))
                .cantidad(BigDecimal.ONE)
                .subTotal(new BigDecimal(25))
                .idComprobante(venta).build();

        venta.addLineaVenta(linea1);
        venta.addLineaVenta(linea2);

        session.setGlobal("shopCart", bean);
        session.execute(venta.getComprobantesLineasList());

        assertEquals(3, venta.getComprobantesLineasList().size());
        assertEquals(0, new BigDecimal(-0.5).subtract(venta.getComprobantesLineasList().get(2).getSubTotal()).signum());
        assertEquals("[1234] Oferta tornillos", venta.getComprobantesLineasList().get(2).getDescripcion());
    }


    @Test
    public void shouldPassOnRubroAndCantidadRule() {
        ProductosDto prod = ProductosDto.builder()
                .id(1234L)
                .idRubro(ProductosRubrosDto.builder().nombreRubro("cortes a medida").build())
                .build();

        ProductosDto prod2 = ProductosDto.builder()
                .idRubro(ProductosRubrosDto.builder().nombreRubro("otro rubro").build())
                .build();

        ComprobantesLineasDto linea1 = ComprobantesLineasDto.builder().descripcion("cualquier cosa 10x50")
                .item(1)
                .precioUnitario(new BigDecimal(5))
                .cantidad(new BigDecimal(102))
                .subTotal(new BigDecimal(5))
                .idComprobante(venta)
                .idProducto(prod2)
                .build();

        ComprobantesLineasDto linea2 = ComprobantesLineasDto.builder().descripcion("cortes 15mm")
                .item(2)
                .idProducto(prod)
                .precioUnitario(new BigDecimal(25))
                .cantidad(BigDecimal.TEN)
                .subTotal(new BigDecimal(25))
                .idComprobante(venta).build();

        venta.addLineaVenta(linea1);
        venta.addLineaVenta(linea2);

        session.setGlobal("shopCart", bean);
        session.execute(venta.getComprobantesLineasList());

        assertEquals(3, venta.getComprobantesLineasList().size());
        assertEquals(new BigDecimal(-12), venta.getComprobantesLineasList().get(2).getSubTotal());
        assertEquals("[1234] Oferta cortes a medida", venta.getComprobantesLineasList().get(2).getDescripcion());

    }

    @Test
    public void shouldPassOnMultiploRule() {
        ProductosDto prod = ProductosDto.builder()
                .id(1234L)
                .idRubro(ProductosRubrosDto.builder().nombreRubro("zaratustra").build())
                .build();

        ProductosDto prod2 = ProductosDto.builder()
                .id(1235L)
                .idRubro(ProductosRubrosDto.builder().nombreRubro("otro rubro").build())
                .build();

        ComprobantesLineasDto linea1 = ComprobantesLineasDto.builder().descripcion("cualquier cosa 10x50")
                .item(1)
                .precioUnitario(new BigDecimal(5))
                .cantidad(new BigDecimal(144))
                .subTotal(new BigDecimal(5))
                .idComprobante(venta)
                .idProducto(prod2)
                .build();

        ComprobantesLineasDto linea2 = ComprobantesLineasDto.builder().descripcion("cortes 15mm")
                .item(2)
                .idProducto(prod)
                .precioUnitario(new BigDecimal(25))
                .cantidad(BigDecimal.TEN)
                .subTotal(new BigDecimal(25))
                .idComprobante(venta).build();

        venta.addLineaVenta(linea1);
        venta.addLineaVenta(linea2);

        session.setGlobal("shopCart", bean);
        session.execute(venta.getComprobantesLineasList());

        assertEquals(3, venta.getComprobantesLineasList().size());
        assertEquals(new BigDecimal(-17), venta.getComprobantesLineasList().get(2).getSubTotal());
        assertEquals("[1235] Oferta multiplo de", venta.getComprobantesLineasList().get(2).getDescripcion());

    }

}