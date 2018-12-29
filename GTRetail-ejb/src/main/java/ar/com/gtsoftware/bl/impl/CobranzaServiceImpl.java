/*
 * Copyright 2015 GT Software.
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

import ar.com.gtsoftware.bl.CobranzaService;
import ar.com.gtsoftware.bl.PersonasCuentaCorrienteService;
import ar.com.gtsoftware.dto.PagoValorDTO;
import ar.com.gtsoftware.dto.model.CajasDto;
import ar.com.gtsoftware.dto.model.RecibosDto;
import ar.com.gtsoftware.eao.*;
import ar.com.gtsoftware.mappers.*;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.*;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Stateless
public class CobranzaServiceImpl implements CobranzaService {

    @EJB
    private RecibosFacade recibosFacade;
    @EJB
    private ComprobantesFacade comprobantesFacade;
    @EJB
    private CajasMovimientosFacade cajasMovimientosFacade;
    @EJB
    private CajasFacade cajasFacade;


    @EJB
    private PersonasCuentaCorrienteService cuentaCorrienteBean;
    @EJB
    private ComprobantesPagosFacade pagosFacade;
    @EJB
    private UsuariosFacade usuariosFacade;

    @Inject
    private ComprobantesPagosMapper comprobantesPagosMapper;
    @Inject
    private CuponesMapper cuponesMapper;
    @Inject
    private ChequesTercerosMapper chequesTercerosMapper;
    @Inject
    private RecibosMapper recibosMapper;
    @Inject
    private PersonasMapper personasMapper;

    @EJB
    private CuponesFacade cuponesFacade;

    @Override
    public RecibosDto cobrarComprobantes(CajasDto cajasDto, List<PagoValorDTO> pagos) {
        if (CollectionUtils.isEmpty(pagos)) {
            throw new IllegalArgumentException("La lista de pagos no puede estar vacía!");
        }
        Date fecha = new Date();
        Recibos recibo = new Recibos();
        recibo.setFechaRecibo(fecha);
        Cajas caja = cajasFacade.find(cajasDto.getId());
        recibo.setIdCaja(caja);
        Comprobantes comprobante = comprobantesFacade.find(pagos.get(0).getPago().getIdComprobante().getId());
        BigDecimal montoTotal = BigDecimal.ZERO;
        for (PagoValorDTO pv : pagos) {
            if (pv.getPago().getMontoPago().compareTo(pv.getMontoMaximo()) > 0) {
                throw new IllegalArgumentException("El total de pagos excede al saldo disponible!");
            }
            montoTotal = montoTotal.add(pv.getPago().getMontoPagoConSigno());
        }
        recibo.setIdPersona(comprobante.getIdPersona());
        recibo.setIdUsuario(usuariosFacade.find(cajasDto.getIdUsuario().getId()));
        recibo.setMontoTotal(montoTotal);
        Set<Comprobantes> comprobantesToEdit = new HashSet<>();
        List<RecibosDetalle> recibosDetalleList = new ArrayList<>();

        for (PagoValorDTO pv : pagos) {

            ComprobantesPagos compPago = comprobantesPagosMapper.dtoToEntity(pv.getPago(),
                    new CycleAvoidingMappingContext());

            RecibosDetalle reciboDet = new RecibosDetalle();
            reciboDet.setIdRecibo(recibo);
            reciboDet.setMontoPagado(compPago.getMontoPago());
            reciboDet.setMontoPagadoConSigno(compPago.getMontoPagoConSigno());
            reciboDet.setIdFormaPago(compPago.getIdFormaPago());

            if (pv.getCupon() != null) {
                Cupones cupon = cuponesMapper.dtoToEntity(pv.getCupon(),
                        new CycleAvoidingMappingContext());
                cupon.setFechaOrigen(fecha);
                reciboDet.setIdValor(cupon);
            }

            if (pv.getCheque() != null) {
                ChequesTerceros cheque = chequesTercerosMapper.dtoToEntity(pv.getCheque(),
                        new CycleAvoidingMappingContext());
                reciboDet.setIdValor(cheque);
            }

            compPago.setFechaPago(fecha);
            compPago.setFechaUltimoPago(fecha);
            compPago.setMontoPagado(compPago.getMontoPago());

            if (compPago.isNew()) {
                pagosFacade.create(compPago);
            }
            reciboDet.setIdPagoComprobante(compPago);

            recibosDetalleList.add(reciboDet);

            comprobantesToEdit.add(compPago.getIdComprobante());

        }

        for (Comprobantes c : comprobantesToEdit) {
            BigDecimal saldo = c.getTotal();
            for (ComprobantesPagos cp : c.getPagosList()) {
                saldo = saldo.subtract(cp.getMontoPagado());
            }
            c.setSaldo(saldo);
            comprobantesFacade.edit(c);
        }
        recibo.setRecibosDetalles(recibosDetalleList);

        recibosFacade.create(recibo);

        CajasMovimientos movimiento = new CajasMovimientos();
        movimiento.setFechaMovimiento(fecha);
        String descMovimiento = String.format("Cobro de comprobantes del cliente %s - Recibo: %d",
                comprobante.getIdPersona().getBusinessString(), recibo.getId());
        movimiento.setDescripcion(descMovimiento);
        movimiento.setIdCaja(caja);
        movimiento.setMontoMovimiento(recibo.getMontoTotal());
        cajasMovimientosFacade.create(movimiento);

        cuentaCorrienteBean.registrarMovimientoCuenta(
                personasMapper.entityToDto(comprobante.getIdPersona(),
                        new CycleAvoidingMappingContext()),
                recibo.getMontoTotal(),
                descMovimiento);
        return recibosMapper.entityToDto(recibo, new CycleAvoidingMappingContext());
    }

}
