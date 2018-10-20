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

import ar.com.gtsoftware.bl.FiscalResponsabilidadesIvaService;
import ar.com.gtsoftware.dto.model.FiscalResponsabilidadesIvaDto;
import ar.com.gtsoftware.eao.FiscalResponsabilidadesIvaFacade;
import ar.com.gtsoftware.mappers.FiscalResponsabilidadesIvaMapper;
import ar.com.gtsoftware.model.FiscalResponsabilidadesIva;
import ar.com.gtsoftware.search.AbstractSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FiscalResponsabilidadesIvaServiceImpl
        extends AbstractBasicEntityService<FiscalResponsabilidadesIvaDto, AbstractSearchFilter,
        FiscalResponsabilidadesIva>
        implements FiscalResponsabilidadesIvaService {

    @EJB
    private FiscalResponsabilidadesIvaFacade facade;

    @Inject
    private FiscalResponsabilidadesIvaMapper mapper;

    @Override
    protected FiscalResponsabilidadesIvaFacade getFacade() {
        return facade;
    }

    @Override
    protected FiscalResponsabilidadesIvaMapper getMapper() {
        return mapper;
    }
}
