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

import ar.com.gtsoftware.bl.LegalTiposPersoneriaService;
import ar.com.gtsoftware.dto.model.LegalTiposPersoneriaDto;
import ar.com.gtsoftware.eao.LegalTiposPersoneriaFacade;
import ar.com.gtsoftware.mappers.LegalTiposPersoneriaMapper;
import ar.com.gtsoftware.model.LegalTiposPersoneria;
import ar.com.gtsoftware.search.AbstractSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LegalTiposPersoneriaServiceImpl
        extends AbstractBasicEntityService<LegalTiposPersoneriaDto, AbstractSearchFilter, LegalTiposPersoneria>
        implements LegalTiposPersoneriaService {

    @Inject
    private LegalTiposPersoneriaMapper mapper;

    @EJB
    private LegalTiposPersoneriaFacade facade;

    @Override
    protected LegalTiposPersoneriaFacade getFacade() {
        return facade;
    }

    @Override
    protected LegalTiposPersoneriaMapper getMapper() {
        return mapper;
    }
}
