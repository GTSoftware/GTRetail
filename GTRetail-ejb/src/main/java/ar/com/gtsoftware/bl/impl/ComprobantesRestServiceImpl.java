/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.FacturacionVentasService;
import ar.com.gtsoftware.dto.model.rest.RegistrarFacturaRequest;
import ar.com.gtsoftware.dto.model.rest.VentasPendientesFacturarRequest;
import ar.com.gtsoftware.eao.ComprobantesFacade;
import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.eao.FiscalPuntosVentaFacade;
import ar.com.gtsoftware.eao.SucursalesFacade;
import ar.com.gtsoftware.mappers.FiscalPuntosVentaMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.mappers.rest.ComprobantesResponseMapper;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.FiscalPuntosVenta;
import ar.com.gtsoftware.model.Sucursales;
import ar.com.gtsoftware.search.ComprobantesSearchFilter;
import ar.com.gtsoftware.search.FiscalPeriodosFiscalesSearchFilter;
import ar.com.gtsoftware.service.rest.ComprobantesEndpoint;
import org.apache.commons.lang3.time.DateUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la API REST para ventas
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Stateless
public class ComprobantesRestServiceImpl implements ComprobantesEndpoint {

    @EJB
    private SucursalesFacade sucursalesFacade;

    @EJB
    private ComprobantesFacade comprobantesFacade;
    @EJB
    private FacturacionVentasService facturacionBean;
    @EJB
    private FiscalPuntosVentaFacade puntosVentaFacade;
    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;

    @Inject
    private ComprobantesResponseMapper comprobantesMapper;
    @Inject
    private FiscalPuntosVentaMapper puntosVentaMapper;

    @Override
    public Response getPendientesFacturar(Long idSucursal) {

        Date desde = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        Date hasta = DateUtils.addHours(desde, 24);
        return Response.ok(getPendientesFacturar(VentasPendientesFacturarRequest.builder()
                .idSucursal(idSucursal)
                .fechaDesde(desde)
                .fechaHasta(hasta).build())
        ).build();
    }

    @Override
    public Response getPendientesFacturar(VentasPendientesFacturarRequest pendientesFacturarRequest) {
        if (pendientesFacturarRequest.getIdSucursal() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Sucursales sucursal = sucursalesFacade.find(pendientesFacturarRequest.getIdSucursal());
        if (sucursal == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        ComprobantesSearchFilter csf = ComprobantesSearchFilter.builder()
                .anulada(Boolean.FALSE)
                .idSucursal(pendientesFacturarRequest.getIdSucursal())
                .facturada(Boolean.FALSE)
                .fechaVentaDesde(DateUtils.truncate(pendientesFacturarRequest.getFechaDesde(), Calendar.DAY_OF_MONTH))
                .fechaVentaHasta(pendientesFacturarRequest.getFechaHasta())
                .build();
        csf.addSortField("fechaComprobante", false);
        try {
            List<Comprobantes> comprobantes = comprobantesFacade.findBySearchFilter(csf, 0, 100);

            return Response.ok(comprobantesMapper.comprobantesToResponses(comprobantes)).build();
        } catch (Exception e) {
            Logger.getLogger(ComprobantesRestServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public Response getPendienteFacturar(Long idComprobante) {
        if (idComprobante == null) {
            return null;
        }
        ComprobantesSearchFilter csf = new ComprobantesSearchFilter();
        csf.setAnulada(Boolean.FALSE);
        csf.setIdVenta(idComprobante);
        csf.setFacturada(Boolean.FALSE);
        Comprobantes comp = comprobantesFacade.findFirstBySearchFilter(csf);
        return Response.ok(comprobantesMapper.comprobanteToResponse(comp)).build();
    }

    @Override
    public Response registrarFacturacion(RegistrarFacturaRequest registro) {
        if (registro.getNumeroComprobante() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Número de comprobante inválido").build();
        }

        Comprobantes comprobante = comprobantesFacade.find(registro.getIdComprobante());
        if (comprobante == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Comprobante inexistente").build();
        }
        FiscalPuntosVenta puntoVenta = puntosVentaFacade.find(registro.getPuntoVenta());
        if (puntoVenta == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Punto de venta inexistente").build();
        }
        FiscalPeriodosFiscalesSearchFilter psf = FiscalPeriodosFiscalesSearchFilter.builder().vigente(Boolean.TRUE).build();
        FiscalPeriodosFiscales periodo = periodosFiscalesFacade.findFirstBySearchFilter(psf);
        if (periodo == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("No hay un periodo fiscal configurado").build();
        }
        try {
            facturacionBean.registrarFacturaVenta(comprobante.getId(),
                    puntosVentaMapper.entityToDto(puntoVenta, new CycleAvoidingMappingContext()),
                    registro.getNumeroComprobante(),
                    null);
        } catch (Exception ex) {
            Logger.getLogger(ComprobantesRestServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().entity(ex.getMessage()).build();
        }
        return Response.ok().build();
    }

}
