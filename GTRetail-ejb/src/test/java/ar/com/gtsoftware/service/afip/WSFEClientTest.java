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
package ar.com.gtsoftware.service.afip;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.model.AFIPAuthServices;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.FiscalLibroIvaVentasLineas;
import ar.com.gtsoftware.model.FiscalTiposComprobante;
import ar.com.gtsoftware.model.LegalTiposDocumento;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.dto.fiscal.AuthTicket;
import ar.com.gtsoftware.model.dto.fiscal.CAEResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class WSFEClientTest {

    public static final String ENDPOINT = "https://wswhomo.afip.gov.ar/wsfev1/service.asmx";

    public WSFEClientTest() {
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
     * Test of solicitarCAE method, of class WSFEClient.
     *
     * @throws ar.com.gtsoftware.bl.exceptions.ServiceException
     */
    @Test
    @Ignore
    public void testSolicitarCAE() throws ServiceException {
        AFIPAuthServices loginTicket = new AFIPAuthServices();
        AuthTicket loginTk = WSAAClient.performAuthentication(WSAAClientTest.ENDPOINT, WSAAClientTest.CERT_PATH, WSAAClientTest.PASSWORD, WSAAClientTest.DST_DN, "wsfe");

        loginTicket.setToken(loginTk.getToken());
        loginTicket.setSign(loginTk.getSign());

        String cuit = "20342577157";
        FiscalLibroIvaVentas comprobante = new FiscalLibroIvaVentas();
        comprobante.setDocumento("34257715");
        FiscalTiposComprobante fiscalTiposComprobante = new FiscalTiposComprobante();
        fiscalTiposComprobante.setCodigoTipoComprobante("006");

        comprobante.setCodigoTipoComprobante(fiscalTiposComprobante);

        int ultimoComp = WSFEClient.obtenerUltimoComprobanteAutorizado(loginTicket, cuit, ENDPOINT, 1, 6);

        comprobante.setNumeroFactura(String.valueOf(ultimoComp + 1));
        comprobante.setPuntoVentaFactura("0001");
        comprobante.setTotalFactura(new BigDecimal(121));
        comprobante.setFechaFactura(new Date());

        FiscalLibroIvaVentasLineas linea = new FiscalLibroIvaVentasLineas();
        FiscalAlicuotasIva alicuota = new FiscalAlicuotasIva();
        alicuota.setFiscalCodigoAlicuota(5);
        linea.setIdAlicuotaIva(alicuota);
        linea.setImporteIva(new BigDecimal(21));
        linea.setNetoGravado(new BigDecimal(100));
        List<FiscalLibroIvaVentasLineas> lineas = new ArrayList<>();
        lineas.add(linea);
        comprobante.setFiscalLibroIvaVentasLineasList(lineas);

        Personas p = new Personas();
        LegalTiposDocumento td = new LegalTiposDocumento();
        td.setFiscalCodigoTipoDocumento(96);
        p.setDocumento("34257715");
        p.setIdTipoDocumento(td);
        comprobante.setIdPersona(p);

        CAEResponse solicitarCAE = WSFEClient.solicitarCAE(loginTicket, cuit, comprobante, ENDPOINT);
        Assert.assertNotNull(solicitarCAE);
        Assert.assertTrue("CAE debe ser mayor que 0", solicitarCAE.getCae() > 0);
    }

    @Test
    @Ignore
    public void testObtenerUltimoComprobanteAutorizado() throws ServiceException {
        AFIPAuthServices loginTicket = new AFIPAuthServices();
        AuthTicket loginTk = WSAAClient.performAuthentication(WSAAClientTest.ENDPOINT, WSAAClientTest.CERT_PATH,
                WSAAClientTest.PASSWORD, WSAAClientTest.DST_DN, "wsfe");

        loginTicket.setToken(loginTk.getToken());
        loginTicket.setSign(loginTk.getSign());

        String cuit = "20342577157";
        int utlimoComp = WSFEClient.obtenerUltimoComprobanteAutorizado(loginTicket, cuit, ENDPOINT, 1, 6);
        Assert.assertTrue("Error, debería ser cero o mayor que cero", utlimoComp >= 0);
    }

}
