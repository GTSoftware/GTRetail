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

import ar.com.gtsoftware.bl.ChequesTercerosService;
import ar.com.gtsoftware.bl.impl.AbstractBasicEntityService;
import ar.com.gtsoftware.dto.model.ChequesTercerosDto;
import ar.com.gtsoftware.eao.ChequesTercerosFacade;
import ar.com.gtsoftware.mappers.ChequesTercerosMapper;
import ar.com.gtsoftware.model.ChequesTerceros;
import ar.com.gtsoftware.search.ChequesTercerosSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ChequesTercerosServiceImpl
        extends AbstractBasicEntityService<ChequesTercerosDto, ChequesTercerosSearchFilter, ChequesTerceros>
        implements ChequesTercerosService {

    @EJB
    private ChequesTercerosFacade facade;
    @Inject
    private ChequesTercerosMapper mapper;


    @Override
    protected ChequesTercerosFacade getFacade() {
        return facade;
    }

    @Override
    protected ChequesTercerosMapper getMapper() {
        return mapper;
    }
}
