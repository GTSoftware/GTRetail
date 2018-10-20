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
import ar.com.gtsoftware.eao.AFIPAuthServicesFacade;
import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.model.AFIPAuthServices;
import ar.com.gtsoftware.search.AFIPAuthServicesSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Stateless
public class WSAAService {

    @EJB
    private AFIPAuthServicesFacade aFIPAuthServicesFacade;

    @EJB
    private ParametrosFacade parametrosFacade;

    private static final String CERT_PATH_PARAM = "afip.wsaa.cert.path";
    private static final String CERT_PASSWORD_PARAM = "afip.wsaa.cert.password";
    private static final String DN_PARAM = "afip.wsaa.dn";
    private static final String ENDPOINT_PARAM = "afip.wsaa.endpoint";

    public boolean isAuthenticated(String service) {
        AFIPAuthServicesSearchFilter asf = new AFIPAuthServicesSearchFilter(service, Boolean.TRUE);
        AFIPAuthServices loginTicket = aFIPAuthServicesFacade.findFirstBySearchFilter(asf);
        return loginTicket != null;
    }

    public AFIPAuthServices obtenerLoginTicket(String service) throws ServiceException {

        AFIPAuthServices loginTicket = aFIPAuthServicesFacade.find(service);
        if (loginTicket == null) {
            loginTicket = new AFIPAuthServices();
        } else if (loginTicket.getFechaExpiracion().after(new Date())) {
            return loginTicket;
        }
        loginTicket.setNombreServicio(service);
        String endpoint = parametrosFacade.findParametroByName(ENDPOINT_PARAM).getValorParametro();
        String certPath = parametrosFacade.findParametroByName(CERT_PATH_PARAM).getValorParametro();
        String certPassword = parametrosFacade.findParametroByName(CERT_PASSWORD_PARAM).getValorParametro();
        String dstDN = parametrosFacade.findParametroByName(DN_PARAM).getValorParametro();

        AuthTicket ticketDto = WSAAClient.performAuthentication(endpoint, certPath, certPassword, dstDN, service);
        loginTicket.setFechaExpiracion(ticketDto.getExpirationDate());
        loginTicket.setSign(ticketDto.getSign());
        loginTicket.setToken(ticketDto.getToken());
        aFIPAuthServicesFacade.createOrEdit(loginTicket);
        return loginTicket;

    }
}
