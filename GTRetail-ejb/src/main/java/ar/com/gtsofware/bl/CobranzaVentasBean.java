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
package ar.com.gtsofware.bl;

import ar.com.gtsoftware.eao.VentasFacade;
import ar.com.gtsoftware.eao.VentasPagosFacade;
import ar.com.gtsoftware.eao.VentasPagosLineasFacade;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.VentasPagos;
import ar.com.gtsoftware.model.VentasPagosLineas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class CobranzaVentasBean {

    @EJB
    private VentasPagosFacade pagosFacade;
    @EJB
    private VentasPagosLineasFacade pagosLineasFacade;
    @EJB
    private CajaBean cajaBean;
    @EJB
    private PersonasCuentaCorrienteBean clientesCuentaCorrienteBean;
    @EJB
    private VentasFacade ventasFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void registrarCobranza(List<Ventas> ventasAPagar, List<VentasPagos> pagosAImputar, Usuarios usuario, Cajas caja) throws Exception {
        //Calcular el importe de cada línea de pago que impute en cada venta.

        List<SaldoPago> saldosPorPago = new ArrayList<>();
        for (VentasPagos pago : pagosAImputar) {
            if (pago.getId()== null) {
                pago.setFechaPago(GregorianCalendar.getInstance().getTime());
                pagosFacade.create(pago);
            }
            SaldoPago saldo = new SaldoPago();
            saldo.setPago(pago);
            saldo.setSaldoPago(pago.getImporteTotalPagado());
            saldosPorPago.add(saldo);

            clientesCuentaCorrienteBean.registrarMovimientoCuenta(ventasAPagar.get(0).getIdPersona(), pago.getImporteTotalPagado(), "Cobranza de ventas");

        }

        for (Ventas v : ventasAPagar) {
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
                        ventasFacade.edit(v);
                        if (sp.getPago().getId() == null) {
                            VentasPagosLineas pagoLinea = new VentasPagosLineas();
                            pagoLinea.setIdVenta(v);
                            pagoLinea.setIdPagoVenta(sp.getPago());
                            pagoLinea.setImporte(importeAsignado);
                            pagosLineasFacade.create(pagoLinea);
                        }

                    }
                }
            }
        }

        cajaBean.registrarPagos(pagosAImputar, usuario, caja);

    }

    public List<VentasPagos> obtenerPagosNoAcentadosVenta(Ventas venta) {
        Set<VentasPagos> result = new HashSet<>();
        List<VentasPagosLineas> lineas = pagosLineasFacade.findLineasPagosNoAcentadas(venta);
        for (VentasPagosLineas l : lineas) {
            result.add(l.getIdPagoVenta());
        }
        List<VentasPagos> pagos = new ArrayList<>();
        pagos.addAll(result);
        return pagos;
    }

    private class SaldoPago {

        private VentasPagos pago;
        private BigDecimal saldoPago;

        public VentasPagos getPago() {
            return pago;
        }

        public void setPago(VentasPagos pago) {
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
