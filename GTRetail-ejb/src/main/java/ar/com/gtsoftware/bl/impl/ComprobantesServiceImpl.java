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

import ar.com.gtsoftware.bl.ComprobantesService;
import ar.com.gtsoftware.bl.PersonasCuentaCorrienteService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.ComprobantesLineasDto;
import ar.com.gtsoftware.eao.ComprobantesEstadosFacade;
import ar.com.gtsoftware.eao.ComprobantesFacade;
import ar.com.gtsoftware.mappers.ComprobantesLineasMapper;
import ar.com.gtsoftware.mappers.ComprobantesMapper;
import ar.com.gtsoftware.mappers.PersonasMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.search.ComprobantesSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Stateless
public class ComprobantesServiceImpl
        extends AbstractBasicEntityService<ComprobantesDto, ComprobantesSearchFilter, Comprobantes>
        implements ComprobantesService {

    @EJB
    private ComprobantesFacade facade;
    @EJB
    private PersonasCuentaCorrienteService cuentaCorrienteBean;
    @EJB
    private ComprobantesEstadosFacade estadosFacade;

    @Inject
    private ComprobantesMapper mapper;

    @Inject
    private PersonasMapper personasMapper;

    @Inject
    private ComprobantesLineasMapper comprobantesLineasMapper;


    @Override
    protected ComprobantesFacade getFacade() {
        return facade;
    }

    @Override
    protected ComprobantesMapper getMapper() {
        return mapper;
    }

    @Override
    public BigDecimal calcularTotalVentas(ComprobantesSearchFilter sf) {
        return facade.calcularTotalVentasBySearchFilter(sf);
    }

    @Override
    public void guardarVenta(ComprobantesDto ventaDto) {
        Comprobantes venta = mapper.dtoToEntity(ventaDto, new CycleAvoidingMappingContext());
        if (venta.isNew()) {
            venta.setIdEstadoComprobante(estadosFacade.find(2L)); //Aceptada
            facade.create(venta);
            cuentaCorrienteBean.registrarMovimientoCuenta(ventaDto.getIdPersona(),
                    venta.getTotal().multiply(venta.getTipoComprobante().getSigno()).negate(),
                    String.format("Comprobante Nro: %d", venta.getId()));
        } else {
            throw new IllegalArgumentException("La venta ya està guardada.");
        }

    }

    @Override
    public void anularVenta(Long idComprobante) throws ServiceException {
        Comprobantes venta = facade.find(idComprobante);
        if (venta == null) {
            throw new ServiceException("Comprobante inexistente");
        }
        if (venta.getAnulada()) {
            throw new ServiceException("El comprobante ya fue anulado!");
        }
        if (venta.getIdRegistro() != null) {
            throw new ServiceException("Comprobante impreso fiscalmente!");
        }
        if (venta.getTotal().subtract(venta.getSaldo()).signum() > 0) {
            throw new ServiceException("Comprobante total o parcialmente cobrado!");
        }
        venta.setAnulada(true);
        cuentaCorrienteBean.registrarMovimientoCuenta(personasMapper.entityToDto(venta.getIdPersona(),
                new CycleAvoidingMappingContext()),
                venta.getTotalConSigno(), String.format("Anulación comprobante Nro: %d", venta.getId()));
        facade.edit(venta);
    }

    @Override
    public List<ComprobantesLineasDto> obtenerLineasComprobantes(@NotNull Long idComprobante) {
        Comprobantes comprobante = facade.find(idComprobante);
        return comprobantesLineasMapper.entitiesToDtos(comprobante.getComprobantesLineasList(), new CycleAvoidingMappingContext());
    }
}
