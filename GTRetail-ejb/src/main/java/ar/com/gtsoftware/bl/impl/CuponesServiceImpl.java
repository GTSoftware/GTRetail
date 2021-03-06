/*
 * Copyright 2019 GT Software.
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

import ar.com.gtsoftware.bl.CuponesService;
import ar.com.gtsoftware.dto.model.CuponesDto;
import ar.com.gtsoftware.eao.CuponesFacade;
import ar.com.gtsoftware.mappers.CuponesMapper;
import ar.com.gtsoftware.model.Cupones;
import ar.com.gtsoftware.search.CuponesSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CuponesServiceImpl
        extends AbstractBasicEntityService<CuponesDto, CuponesSearchFilter, Cupones>
        implements CuponesService {


    @EJB
    private CuponesFacade facade;

    @Inject
    private CuponesMapper mapper;

    @Override
    protected CuponesFacade getFacade() {
        return facade;
    }

    @Override
    protected CuponesMapper getMapper() {
        return mapper;
    }
}
