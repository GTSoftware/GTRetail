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

package ar.com.gtsoftware.service.fiscal;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.LibroIVADTO;
import ar.com.gtsoftware.search.LibroIVASearchFilter;

import javax.ejb.Remote;

@Remote
public interface LibroIVAService {
    /**
     * Genera el libro de IVA para el período establecido en el filtro
     *
     * @param filter
     * @return el libro de IVA
     * @throws ServiceException
     */
    LibroIVADTO obtenerLibroIVA(LibroIVASearchFilter filter) throws ServiceException;
}
