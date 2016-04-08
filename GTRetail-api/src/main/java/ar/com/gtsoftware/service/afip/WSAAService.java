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
import javax.ejb.Remote;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Remote
public interface WSAAService {

    /**
     * Retorna true si el service pasado como parámetro ya se encuentra autenticado
     *
     * @param service
     * @return true si el servicio se encuentra autenticado
     */
    public boolean isAuthenticated(String service);

    /**
     * Obtiene un loginTicket para el servicio especificado
     *
     * @param service
     * @return AFIPAuthServices
     * @throws ar.com.gtsoftware.bl.exceptions.ServiceException
     */
    public AFIPAuthServices obtenerLoginTicket(String service) throws ServiceException;
}
