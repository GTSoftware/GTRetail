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

import ar.com.gtsoftware.eao.CajasCategoriasMovimientosFacade;
import ar.com.gtsoftware.eao.CajasMovimientosFacade;
import ar.com.gtsoftware.eao.VentasPagosFacade;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.CajasCategoriasMovimientos;
import ar.com.gtsoftware.model.CajasMovimientos;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.model.VentasPagos;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class CajaBean {

    @EJB
    private CajasMovimientosFacade cajasMovimientosFacade;
    @EJB
    private CajasCategoriasMovimientosFacade categoriasMovimientosFacade;
    @EJB
    private VentasPagosFacade pagosFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void registrarPagos(List<VentasPagos> pagos, Usuarios usuario, Cajas caja) throws Exception {
        CajasCategoriasMovimientos categoriaPagosPorVentas = categoriasMovimientosFacade.find(1);

        for (VentasPagos p : pagos) {
            BigDecimal saldoAcumAnt = cajasMovimientosFacade.findSaldoCaja(caja);
            if (saldoAcumAnt == null) {
                saldoAcumAnt = BigDecimal.ZERO;
            }
            CajasMovimientos movimiento = new CajasMovimientos();
            movimiento.setFechaMovimiento(GregorianCalendar.getInstance().getTime());
            movimiento.setIdCaja(caja);
            movimiento.setIdCategoriaMovimiento(categoriaPagosPorVentas);
            movimiento.setIdFormaPago(p.getIdFormaPago());
            movimiento.setIdUsuario(usuario);
            movimiento.setImporteMovimiento(p.getImporteTotalPagado());
            movimiento.setObservaciones("Registro del pago nro: " + p.getId());
            movimiento.setSaldoAcumulado(saldoAcumAnt.add(p.getImporteTotalPagado()));
            cajasMovimientosFacade.create(movimiento);
            p.setIdMovimientoCaja(movimiento);
            pagosFacade.edit(p);
        }
    }
}
