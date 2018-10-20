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

import ar.com.gtsoftware.bl.RecibosService;
import ar.com.gtsoftware.dto.model.RecibosDto;
import ar.com.gtsoftware.eao.RecibosFacade;
import ar.com.gtsoftware.mappers.RecibosMapper;
import ar.com.gtsoftware.model.Recibos;
import ar.com.gtsoftware.search.RecibosSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RecibosServiceImpl
        extends AbstractBasicEntityService<RecibosDto, RecibosSearchFilter, Recibos>
        implements RecibosService {

    @EJB
    private RecibosFacade facade;
    @Inject
    private RecibosMapper mapper;

    @Override
    protected RecibosFacade getFacade() {
        return facade;
    }

    @Override
    protected RecibosMapper getMapper() {
        return mapper;
    }
}
