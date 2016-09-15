/*
 * Copyright 2014 GT Software.
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

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.eao.ComprobantesEstadosFacade;
import ar.com.gtsoftware.eao.ComprobantesFacade;
import ar.com.gtsoftware.eao.ComprobantesPagosFacade;
import ar.com.gtsoftware.model.Comprobantes;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class VentasBean {

    @EJB
    private ComprobantesFacade ventasFacade;

    @EJB
    private ComprobantesPagosFacade pagosFacade;

    @EJB
    private PersonasCuentaCorrienteBean cuentaCorrienteBean;
    @EJB
    private ComprobantesEstadosFacade estadosFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void guardarVenta(Comprobantes venta) throws Exception {
        if (venta.isNew()) { //TODO Evaluar para utilizar el mismo método en caso de una venta modificada
            //TODO Parametrizar estado inicial de venta
            venta.setIdEstadoComprobante(estadosFacade.find(2L)); //Aceptada
            ventasFacade.create(venta);
//            for (ComprobantesPagos pago : pagos) {
//                pago.setFechaPago(venta.getFechaComprobante());
//                pago.setIdPersona(venta.getIdPersona());
//                pago.setIdUsuario(venta.getIdUsuario());
//                pago.setIdSucursal(venta.getIdSucursal());
//                pagosFacade.create(pago);
//                ComprobantesPagosLineas lineaPago = new ComprobantesPagosLineas();
//                lineaPago.setIdPagoComprobante(pago);
//                lineaPago.setIdComprobante(venta);
//                lineaPago.setImporte(pago.getImporteTotalPagado());
//                pagosLineasFacade.create(lineaPago);
//            }

            cuentaCorrienteBean.registrarMovimientoCuenta(venta.getIdPersona(), venta.getTotal().multiply(venta.getTipoComprobante().getSigno()).negate(), String.format("Comprobante Nro: %d", venta.getId()));
        }
    }

    /**
     * Anula la venta pasada como parámetro y la devuelve de la cuenta corriente del cliente
     *
     * @param venta
     * @throws ServiceException
     */
    public void anularVenta(Comprobantes venta) throws ServiceException {
        if (venta == null) {
            throw new ServiceException("Venta nula!");
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
        cuentaCorrienteBean.registrarMovimientoCuenta(venta.getIdPersona(),
                venta.getTotalConSigno(), String.format("Anulación comprobante Nro: %d", venta.getId()));
        ventasFacade.edit(venta);
    }

}
