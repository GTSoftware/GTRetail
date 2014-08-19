/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtsoft.eao.bl;

import com.gtsoft.eao.CajasCategoriasMovimientosFacade;
import com.gtsoft.eao.CajasMovimientosFacade;
import com.gtsoft.eao.VentasPagosFacade;
import com.gtsoft.model.Cajas;
import com.gtsoft.model.CajasCategoriasMovimientos;
import com.gtsoft.model.CajasMovimientos;
import com.gtsoft.model.Usuarios;
import com.gtsoft.model.VentasPagos;
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
            movimiento.setObservaciones("Registro del pago nro: " + p.getIdPagoVenta());
            movimiento.setSaldoAcumulado(saldoAcumAnt.add(p.getImporteTotalPagado()));
            cajasMovimientosFacade.create(movimiento);
            p.setIdMovimientoCaja(movimiento);
            pagosFacade.edit(p);
        }
    }
}
