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

import ar.com.gtsoftware.eao.ComprobantesFacade;
import ar.com.gtsoftware.eao.ComprobantesPagosFacade;
import ar.com.gtsoftware.eao.ComprobantesPagosLineasFacade;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.ComprobantesPagos;
import ar.com.gtsoftware.model.ComprobantesPagosLineas;
import ar.com.gtsoftware.model.Usuarios;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class CobranzaVentasBean {

    @EJB
    private ComprobantesPagosFacade pagosFacade;
    @EJB
    private ComprobantesPagosLineasFacade pagosLineasFacade;
    @EJB
    private CajaBean cajaBean;
    @EJB
    private PersonasCuentaCorrienteBean clientesCuentaCorrienteBean;
    @EJB
    private ComprobantesFacade comprobantesFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void registrarCobranza(List<Comprobantes> comprobantesAPagar, List<ComprobantesPagos> pagosAImputar, Usuarios usuario, Cajas caja) throws Exception {
        //Calcular el importe de cada línea de pago que impute en cada venta.

        List<SaldoPago> saldosPorPago = new ArrayList<>();
        for (ComprobantesPagos pago : pagosAImputar) {
            if (pago.getId() == null) {
                pago.setFechaPago(GregorianCalendar.getInstance().getTime());
                pagosFacade.create(pago);
            }
            SaldoPago saldo = new SaldoPago();
            saldo.setPago(pago);
            saldo.setSaldoPago(pago.getImporteTotalPagado());
            saldosPorPago.add(saldo);

            clientesCuentaCorrienteBean.registrarMovimientoCuenta(comprobantesAPagar.get(0).getIdPersona(), pago.getImporteTotalPagado(), "Cobranza de ventas");

        }

        for (Comprobantes v : comprobantesAPagar) {
            for (SaldoPago sp : saldosPorPago) {
                if (v.getSaldo().compareTo(BigDecimal.ZERO) > 0) {
                    if (sp.getSaldoPago().compareTo(BigDecimal.ZERO) > 0) {

                        BigDecimal importeAsignado;
                        if (v.getSaldo().compareTo(sp.getSaldoPago()) > 0) {//El saldo de la venta es más que el saldo del pago
                            v.setSaldo(v.getSaldo().subtract(sp.saldoPago));
                            importeAsignado = sp.getSaldoPago();
                            sp.setSaldoPago(BigDecimal.ZERO);

                        } else if (v.getSaldo().compareTo(sp.getSaldoPago()) < 0) {//El saldo de la venta es inferior al saldo del pago
                            sp.setSaldoPago(sp.getSaldoPago().subtract(v.getSaldo()));
                            importeAsignado = v.getSaldo();
                            v.setSaldo(BigDecimal.ZERO);
                        } else {
                            importeAsignado = sp.getSaldoPago();
                            v.setSaldo(BigDecimal.ZERO);
                            sp.setSaldoPago(BigDecimal.ZERO);
                        }
                        comprobantesFacade.edit(v);
                        if (sp.getPago().getId() == null) {
                            ComprobantesPagosLineas pagoLinea = new ComprobantesPagosLineas();
                            pagoLinea.setIdComprobante(v);
                            pagoLinea.setIdPagoComprobante(sp.getPago());
                            pagoLinea.setImporte(importeAsignado);
                            pagosLineasFacade.create(pagoLinea);
                        }

                    }
                }
            }
        }

        cajaBean.registrarPagos(pagosAImputar, usuario, caja);

    }

    public List<ComprobantesPagos> obtenerPagosNoAcentadosVenta(Comprobantes venta) {
        Set<ComprobantesPagos> result = new HashSet<>();
        List<ComprobantesPagosLineas> lineas = pagosLineasFacade.findLineasPagosNoAcentadas(venta);
        for (ComprobantesPagosLineas l : lineas) {
            result.add(l.getIdPagoComprobante());
        }
        List<ComprobantesPagos> pagos = new ArrayList<>();
        pagos.addAll(result);
        return pagos;
    }

    private class SaldoPago {

        private ComprobantesPagos pago;
        private BigDecimal saldoPago;

        public ComprobantesPagos getPago() {
            return pago;
        }

        public void setPago(ComprobantesPagos pago) {
            this.pago = pago;
        }

        public BigDecimal getSaldoPago() {
            return saldoPago;
        }

        public void setSaldoPago(BigDecimal saldoPago) {
            this.saldoPago = saldoPago;
        }
    }
}
