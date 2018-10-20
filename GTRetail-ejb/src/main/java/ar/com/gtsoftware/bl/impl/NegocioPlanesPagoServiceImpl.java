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

import ar.com.gtsoftware.bl.NegocioPlanesPagoService;
import ar.com.gtsoftware.dto.model.NegocioPlanesPagoDto;
import ar.com.gtsoftware.eao.NegocioPlanesPagoFacade;
import ar.com.gtsoftware.mappers.NegocioPlanesPagoMapper;
import ar.com.gtsoftware.model.NegocioPlanesPago;
import ar.com.gtsoftware.search.PlanesPagoSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class NegocioPlanesPagoServiceImpl extends AbstractBasicEntityService<NegocioPlanesPagoDto,
        PlanesPagoSearchFilter, NegocioPlanesPago>
        implements NegocioPlanesPagoService {

    @EJB
    private NegocioPlanesPagoFacade facade;

    @Inject
    private NegocioPlanesPagoMapper mapper;

    @Override
    protected NegocioPlanesPagoFacade getFacade() {
        return facade;
    }

    @Override
    protected NegocioPlanesPagoMapper getMapper() {
        return mapper;
    }


}
