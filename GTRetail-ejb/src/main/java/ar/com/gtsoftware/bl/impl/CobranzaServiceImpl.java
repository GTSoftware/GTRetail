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
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.eao.CajasMovimientosFacade;
import ar.com.gtsoftware.eao.ComprobantesFacade;
import ar.com.gtsoftware.eao.CuponesFacade;
import ar.com.gtsoftware.eao.RecibosFacade;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.CajasMovimientos;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.ComprobantesPagos;
import ar.com.gtsoftware.model.Recibos;
import ar.com.gtsoftware.model.RecibosDetalle;
import ar.com.gtsoftware.model.dto.PagoValorDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.collections.CollectionUtils;

/**
 *
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
    private PersonasCuentaCorrienteServiceImpl cuentaCorrienteBean;

    @EJB
    private CuponesFacade cuponesFacade;

    @Override
    public Recibos cobrarComprobante(Cajas caja, Comprobantes comprobante) throws ServiceException {

        Date fecha = new Date();
        Recibos recibo = new Recibos();
        recibo.setFechaRecibo(fecha);
        recibo.setIdCaja(caja);
        recibo.setIdPersona(comprobante.getIdPersona());
        recibo.setIdUsuario(caja.getIdUsuario());
        recibo.setMontoTotal(comprobante.getSaldoConSigno());
//        RecibosDetalle reciboDet = new RecibosDetalle();
//        reciboDet.setIdRecibo(recibo);
//        reciboDet.setMontoPagado(comprobante.getSaldoConSigno());
//        ComprobantesPagos compPago = comprobante.getPagosList().get(0);
        List<RecibosDetalle> recibosDetalleList = new ArrayList<>();
        for (ComprobantesPagos compPago : comprobante.getPagosList()) {
            RecibosDetalle reciboDet = new RecibosDetalle();
            reciboDet.setIdRecibo(recibo);
            reciboDet.setMontoPagado(compPago.getMontoPago());
            reciboDet.setIdFormaPago(compPago.getIdFormaPago());
            reciboDet.setIdPagoComprobante(compPago);

            compPago.setFechaPago(fecha);
            compPago.setFechaUltimoPago(fecha);
            compPago.setMontoPagado(compPago.getMontoPago());
            recibosDetalleList.add(reciboDet);
        }
        recibo.setRecibosDetalles(recibosDetalleList);
//        reciboDet.setIdFormaPago(compPago.getIdFormaPago());
//        reciboDet.setIdPagoComprobante(compPago);
//        recibo.setRecibosDetalles(Arrays.asList(reciboDet));
        comprobante.setSaldo(BigDecimal.ZERO);

        comprobantesFacade.edit(comprobante);
        recibosFacade.create(recibo);

        CajasMovimientos movimiento = new CajasMovimientos();
        movimiento.setFechaMovimiento(fecha);
        String descMovimiento = String.format("Cobro de comprobante %d del cliente %s - Recibo: %d",
                comprobante.getId(),
                comprobante.getIdPersona().getBusinessString(), recibo.getId());
        movimiento.setDescripcion(descMovimiento);
        movimiento.setIdCaja(caja);
        movimiento.setMontoMovimiento(recibo.getMontoTotal());
        cajasMovimientosFacade.create(movimiento);

        cuentaCorrienteBean.registrarMovimientoCuenta(comprobante.getIdPersona(), recibo.getMontoTotal(), descMovimiento);
        return recibo;

    }

    @Override
    public Recibos cobrarComprobantes(Cajas caja, List<PagoValorDTO> pagos) {
        if (CollectionUtils.isEmpty(pagos)) {
            throw new IllegalArgumentException("La lista de pagos no puede estar vacía!");
        }
        Date fecha = new Date();
        Recibos recibo = new Recibos();
        recibo.setFechaRecibo(fecha);
        recibo.setIdCaja(caja);
        Comprobantes comprobante = pagos.get(0).getPago().getIdComprobante();
        BigDecimal montoTotal = BigDecimal.ZERO;
        for (PagoValorDTO pv : pagos) {
            montoTotal = montoTotal.add(pv.getPago().getMontoPagoConSigno());
        }
        recibo.setIdPersona(comprobante.getIdPersona());
        recibo.setIdUsuario(caja.getIdUsuario());
        recibo.setMontoTotal(montoTotal);
        Set<Comprobantes> comprobantesToEdit = new HashSet<>();
        List<RecibosDetalle> recibosDetalleList = new ArrayList<>();
        for (PagoValorDTO pv : pagos) {
            ComprobantesPagos compPago = pv.getPago();
            RecibosDetalle reciboDet = new RecibosDetalle();
            reciboDet.setIdRecibo(recibo);
            reciboDet.setMontoPagado(compPago.getMontoPago());
            reciboDet.setIdFormaPago(compPago.getIdFormaPago());
            reciboDet.setIdPagoComprobante(compPago);
            if (pv.getCupon() != null) {
                pv.getCupon().setFechaOrigen(fecha);
                reciboDet.setIdValor(pv.getCupon());
            }

            compPago.setFechaPago(fecha);
            compPago.setFechaUltimoPago(fecha);
            compPago.setMontoPagado(compPago.getMontoPago());
            recibosDetalleList.add(reciboDet);

            comprobantesToEdit.add(pv.getPago().getIdComprobante());

        }

        for (Comprobantes c : comprobantesToEdit) {
            c.setSaldo(BigDecimal.ZERO);
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

        cuentaCorrienteBean.registrarMovimientoCuenta(comprobante.getIdPersona(), recibo.getMontoTotal(), descMovimiento);
        return recibo;
    }

}
