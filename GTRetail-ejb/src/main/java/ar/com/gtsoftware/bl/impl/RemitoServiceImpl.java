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

import ar.com.gtsoftware.bl.RemitoService;
import ar.com.gtsoftware.dto.model.RemitoDto;
import ar.com.gtsoftware.dto.model.RemitoTipoMovimientoDto;
import ar.com.gtsoftware.eao.RemitoFacade;
import ar.com.gtsoftware.mappers.RemitoMapper;
import ar.com.gtsoftware.model.Remito;
import ar.com.gtsoftware.search.RemitoSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class RemitoServiceImpl
        extends AbstractBasicEntityService<RemitoDto, RemitoSearchFilter, Remito>
        implements RemitoService {

    @EJB
    private RemitoFacade facade;

    @Inject
    private RemitoMapper mapper;

    @Override
    protected RemitoFacade getFacade() {
        return facade;
    }

    @Override
    protected RemitoMapper getMapper() {
        return mapper;
    }

}
