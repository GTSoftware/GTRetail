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

import ar.com.gtsoftware.bl.ComprobantesProveedorService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.ProveedoresComprobantesDto;
import ar.com.gtsoftware.eao.ComprobantesProveedorFacade;
import ar.com.gtsoftware.eao.FiscalLibroIvaComprasFacade;
import ar.com.gtsoftware.eao.FiscalTiposComprobanteFacade;
import ar.com.gtsoftware.mappers.ProveedoresComprobantesMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.FiscalLibroIvaCompras;
import ar.com.gtsoftware.model.FiscalLibroIvaComprasLineas;
import ar.com.gtsoftware.model.FiscalTiposComprobante;
import ar.com.gtsoftware.model.ProveedoresComprobantes;
import ar.com.gtsoftware.search.ComprobantesProveedorSearchFilter;
import ar.com.gtsoftware.search.FiscalTiposComprobanteSearchFilter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;

@Stateless
public class ComprobantesProveedorServiceImpl
        extends AbstractBasicEntityService<ProveedoresComprobantesDto,
        ComprobantesProveedorSearchFilter,
        ProveedoresComprobantes>
        implements ComprobantesProveedorService {


    @EJB
    private ComprobantesProveedorFacade facade;
    @EJB
    private FiscalTiposComprobanteFacade tiposComprobanteFacade;
    @EJB
    private FiscalLibroIvaComprasFacade ivaComprasFacade;

    @Inject
    private ProveedoresComprobantesMapper mapper;

    @Override
    protected ComprobantesProveedorFacade getFacade() {
        return facade;
    }

    @Override
    protected ProveedoresComprobantesMapper getMapper() {
        return mapper;
    }

    @Override
    public ProveedoresComprobantesDto guardarYFiscalizar(ProveedoresComprobantesDto comprobanteDto) throws ServiceException {
        //TODO hacer las validaciones con annotations

        if (CollectionUtils.isEmpty(comprobanteDto.getIdRegistro().getFiscalLibroIvaComprasLineasList())) {
            throw new ServiceException("Falta agregar aglún alicuota al comprobante");
        }
        if (comprobanteDto.getIdRegistro().getIdPeriodoFiscal() == null) {
            throw new ServiceException("Se debe establacer un perìodo fiscal");

        }
        FiscalTiposComprobanteSearchFilter ftcsf = FiscalTiposComprobanteSearchFilter.builder()
                .letra(comprobanteDto.getLetra())
                .idTipoComprobante(comprobanteDto.getTipoComprobante().getId()).build();
        FiscalTiposComprobante tipoCompFiscal = tiposComprobanteFacade.findFirstBySearchFilter(ftcsf);

        if (tipoCompFiscal == null) {
            throw new ServiceException("Este tipo de comprobante no puede ser fiscalizado");
        }
        ProveedoresComprobantes comprobante = mapper.dtoToEntity(comprobanteDto, new CycleAvoidingMappingContext());

        FiscalLibroIvaCompras registro = comprobante.getIdRegistro();
        registro.setFechaFactura(comprobante.getFechaComprobante());
        registro.setDocumento(comprobante.getIdProveedor().getDocumento());
        registro.setLetraFactura(comprobante.getLetra());
        registro.setTotalFactura(comprobante.getTotal());
        registro.setIdPersona(comprobante.getIdProveedor());
        registro.setIdResponsabilidadIva(comprobante.getIdProveedor().getIdResponsabilidadIva());
        registro.setPuntoVentaFactura(StringUtils.leftPad(registro.getPuntoVentaFactura(), 4, "0"));
        registro.setNumeroFactura(StringUtils.leftPad(registro.getNumeroFactura(), 8, "0"));

        calcularTotalesLibro(registro);


        registro.setCodigoTipoComprobante(tipoCompFiscal);
        ivaComprasFacade.createOrEdit(registro);
        ProveedoresComprobantes compEditado = facade.createOrEdit(comprobante);

        return mapper.entityToDto(compEditado, new CycleAvoidingMappingContext());
    }

    private void calcularTotalesLibro(FiscalLibroIvaCompras registro) {
        //TODO ver el tema de los exentos
        BigDecimal totalIVA = BigDecimal.ZERO;
        BigDecimal totalNG = BigDecimal.ZERO;
        BigDecimal totalNoGravado = BigDecimal.ZERO;
        for (FiscalLibroIvaComprasLineas linea :
                registro.getFiscalLibroIvaComprasLineasList()) {
            totalIVA = totalIVA.add(linea.getImporteIva());
            totalNG = totalNG.add(linea.getNetoGravado());
            totalNoGravado = totalNoGravado.add(linea.getNoGravado());
        }
        registro.setImporteIva(totalIVA);
        registro.setImporteNetoGravado(totalNG);
        registro.setImporteNetoNoGravado(totalNoGravado);

        registro.setImporteTributos(BigDecimal.ZERO);
        registro.setImporteExento(BigDecimal.ZERO);
        BigDecimal totalComprobante = totalIVA.add(totalNG).add(totalNoGravado);
        totalComprobante = totalComprobante.add(registro.getImportePercepcionIngresosBrutos());
        totalComprobante = totalComprobante.add(registro.getImportePercepcionIva());
        registro.setTotalFactura(totalComprobante);
    }
}
