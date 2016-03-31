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
package ar.com.gtsoftware.utils;

import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.FiscalTiposComprobante;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class GeneradorCodigoBarraFETest {

    public GeneradorCodigoBarraFETest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calcularCodigoBarras method, of class GeneradorCodigoBarraFE.
     */
    @Test
    public void testCalcularCodigoBarras() throws ParseException {
        System.out.println("calcularCodigoBarras");
        FiscalLibroIvaVentas registro = new FiscalLibroIvaVentas();
        registro.setPuntoVentaFactura("0002");
        registro.setCae(66107895270467L);
        SimpleDateFormat amd = new SimpleDateFormat("yyyyMMdd");
        registro.setFechaVencimientoCae(amd.parse("20160314"));
        FiscalTiposComprobante tipoComp = new FiscalTiposComprobante();
        tipoComp.setCodigoTipoComprobante("11");
        registro.setCodigoTipoComprobante(tipoComp);
        String cuitEmpresa = "20342577157";
        String expResult = "2034257715711000266107895270467201603148";
        String result = GeneradorCodigoBarraFE.calcularCodigoBarras(registro, cuitEmpresa);
        Assert.assertEquals(expResult, result);

    }

}
