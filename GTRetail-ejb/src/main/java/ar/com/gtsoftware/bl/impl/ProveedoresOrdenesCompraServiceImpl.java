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

import ar.com.gtsoftware.bl.ProveedoresOrdenesCompraService;
import ar.com.gtsoftware.dto.model.ProveedoresOrdenesCompraDto;
import ar.com.gtsoftware.dto.model.ProveedoresOrdenesCompraEstadosDto;
import ar.com.gtsoftware.eao.ProveedoresOrdenesCompraEstadosFacade;
import ar.com.gtsoftware.eao.ProveedoresOrdenesCompraFacade;
import ar.com.gtsoftware.mappers.ProveedoresOrdenesCompraEstadosMapper;
import ar.com.gtsoftware.mappers.ProveedoresOrdenesCompraMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompra;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompraEstados;
import ar.com.gtsoftware.search.OrdenCompraSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProveedoresOrdenesCompraServiceImpl
        extends AbstractBasicEntityService<ProveedoresOrdenesCompraDto, OrdenCompraSearchFilter, ProveedoresOrdenesCompra>
        implements ProveedoresOrdenesCompraService {

    @EJB
    private ProveedoresOrdenesCompraFacade facade;
    @EJB
    private ProveedoresOrdenesCompraEstadosFacade estadosFacade;

    @Inject
    private ProveedoresOrdenesCompraMapper mapper;

    @Inject
    private ProveedoresOrdenesCompraEstadosMapper estadosMapper;

    @Override
    protected ProveedoresOrdenesCompraFacade getFacade() {
        return facade;
    }

    @Override
    protected ProveedoresOrdenesCompraMapper getMapper() {
        return mapper;
    }

    @Override
    public List<ProveedoresOrdenesCompraEstadosDto> obtenerEstados() {
        List<ProveedoresOrdenesCompraEstados> allEstados = estadosFacade.findAll();
        return estadosMapper.entitiesToDtos(allEstados, new CycleAvoidingMappingContext());

    }

    @Override
    public ProveedoresOrdenesCompraEstadosDto obtenerEstado(Long id) {
        ProveedoresOrdenesCompraEstados estado = estadosFacade.find(id);
        return estadosMapper.entityToDto(estado, new CycleAvoidingMappingContext());

    }
}
