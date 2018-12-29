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

import ar.com.gtsoftware.bl.CajasTransferenciasService;
import ar.com.gtsoftware.dto.model.CajasTransferenciasDto;
import ar.com.gtsoftware.eao.CajasTransferenciasFacade;
import ar.com.gtsoftware.mappers.CajasTransferenciasMapper;
import ar.com.gtsoftware.model.CajasTransferencias;
import ar.com.gtsoftware.search.CajasTransferenciasSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CajasTransferenciasServiceImpl
        extends AbstractBasicEntityService<CajasTransferenciasDto, CajasTransferenciasSearchFilter, CajasTransferencias>
        implements CajasTransferenciasService {

    @EJB
    private CajasTransferenciasFacade facade;

    @Inject
    private CajasTransferenciasMapper mapper;


    @Override
    protected CajasTransferenciasFacade getFacade() {
        return facade;
    }

    @Override
    protected CajasTransferenciasMapper getMapper() {
        return mapper;
    }
}
