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
import ar.com.gtsoftware.dto.fiscal.AuthTicket;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class WSAAClientTest {

    public static final String ENDPOINT = "https://wsaahomo.afip.gov.ar/ws/services/LoginCms";
    public static final String CERT_PATH = "/home/rodrigo/certs/afip/alias.p12";
    public static final String PASSWORD = "soloio";
    public static final String DST_DN = "cn=wsaahomo,o=afip,c=ar,serialNumber=CUIT 33693450239";

    public WSAAClientTest() {
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
     * Test of performAuthentication method, of class WSAAClient.
     *
     * @throws ar.com.gtsoftware.bl.exceptions.ServiceException
     */
    @Test
    @Ignore
    public void testPerformAuthentication() throws ServiceException {
        System.out.println("performAuthentication");

        String service = "wsfe";

        AuthTicket result = WSAAClient.performAuthentication(ENDPOINT, CERT_PATH, PASSWORD, DST_DN, service);
        Assert.assertNotNull(result);

        System.out.println(result);

    }

}
