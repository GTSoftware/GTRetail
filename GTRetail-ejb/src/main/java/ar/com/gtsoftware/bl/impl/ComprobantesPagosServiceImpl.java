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

package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.ComprobantesPagosService;
import ar.com.gtsoftware.dto.model.ComprobantesPagosDto;
import ar.com.gtsoftware.eao.ComprobantesPagosFacade;
import ar.com.gtsoftware.mappers.ComprobantesPagosMapper;
import ar.com.gtsoftware.model.ComprobantesPagos;
import ar.com.gtsoftware.search.ComprobantesPagosSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ComprobantesPagosServiceImpl
        extends AbstractBasicEntityService<ComprobantesPagosDto, ComprobantesPagosSearchFilter, ComprobantesPagos>
        implements ComprobantesPagosService {

    @EJB
    private ComprobantesPagosFacade facade;
    @Inject
    private ComprobantesPagosMapper mapper;

    @Override
    protected ComprobantesPagosFacade getFacade() {
        return facade;
    }

    @Override
    protected ComprobantesPagosMapper getMapper() {
        return mapper;
    }
}
