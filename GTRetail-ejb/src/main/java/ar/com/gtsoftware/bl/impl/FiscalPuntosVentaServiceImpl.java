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

import ar.com.gtsoftware.bl.FiscalPuntosVentaService;
import ar.com.gtsoftware.dto.model.FiscalPuntosVentaDto;
import ar.com.gtsoftware.eao.FiscalPuntosVentaFacade;
import ar.com.gtsoftware.mappers.FiscalPuntosVentaMapper;
import ar.com.gtsoftware.model.FiscalPuntosVenta;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FiscalPuntosVentaServiceImpl
        extends AbstractBasicEntityService<FiscalPuntosVentaDto, FiscalPuntosVentaSearchFilter, FiscalPuntosVenta>
        implements FiscalPuntosVentaService {

    @EJB
    private FiscalPuntosVentaFacade facade;

    @Inject
    private FiscalPuntosVentaMapper mapper;

    @Override
    protected FiscalPuntosVentaFacade getFacade() {
        return facade;
    }

    @Override
    protected FiscalPuntosVentaMapper getMapper() {
        return mapper;
    }
}
