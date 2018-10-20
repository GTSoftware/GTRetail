/*
 * Copyright 2015 GT Software.
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
package ar.com.gtsoftware.bl.fiscal;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.FiscalPeriodosFiscalesDto;
import javax.ejb.Remote;

/**
 * Capa de servicio para la generación de regímenes informativos de AFIP
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Remote
public interface RegimenInformativoVentasService {

    /**
     * Genera el archivo para régimen informativo de compras y ventas
     *
     * @param periodoFiscal
     * @return
     * @throws ar.com.gtsoftware.bl.exceptions.ServiceException
     */
    RegimenInformativoDTO generarRegimenInformativo(FiscalPeriodosFiscalesDto periodoFiscal) throws ServiceException;

}
