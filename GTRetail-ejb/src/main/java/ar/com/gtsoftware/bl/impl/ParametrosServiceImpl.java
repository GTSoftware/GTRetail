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

import ar.com.gtsoftware.bl.ParametrosService;
import ar.com.gtsoftware.dto.model.ParametrosDto;
import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.mappers.ParametrosMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@Stateless
public class ParametrosServiceImpl implements ParametrosService {

    @Inject
    private ParametrosMapper mapper;

    @EJB
    private ParametrosFacade facade;

    @Override
    public ParametrosDto createOrEdit(@NotNull ParametrosDto parametrosDto) {
        return mapper.entityToDto(facade.createOrEdit(mapper.dtoToEntity(parametrosDto,
                new CycleAvoidingMappingContext())),
                new CycleAvoidingMappingContext());
    }

    @Override
    public ParametrosDto findParametroByName(@NotNull String nombre) {
        return mapper.entityToDto(facade.findParametroByName(nombre), new CycleAvoidingMappingContext());
    }

    @Override
    public List<ParametrosDto> findParametros(@NotNull String txt) {
        return mapper.entitiesToDtos(facade.findParametros(txt), new CycleAvoidingMappingContext());
    }

}
