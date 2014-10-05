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

import ar.com.gtsoftware.eao.VentasEstadosFacade;
import ar.com.gtsoftware.eao.VentasFacade;
import ar.com.gtsoftware.eao.VentasPagosFacade;
import ar.com.gtsoftware.eao.VentasPagosLineasFacade;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.VentasPagos;
import ar.com.gtsoftware.model.VentasPagosLineas;
import ar.com.gtsofware.bl.exceptions.ServiceException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class VentasBean {

    @EJB
    private VentasFacade ventasFacade;

    @EJB
    private VentasPagosFacade pagosFacade;
    @EJB
    private VentasPagosLineasFacade pagosLineasFacade;
    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    @EJB
    private PersonasCuentaCorrienteBean cuentaCorrienteBean;
    @EJB
    private VentasEstadosFacade estadosFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void guardarVenta(Ventas venta, List<VentasPagos> pagos) throws Exception {
        if (venta.isNew()) { //TODO Evaluar para utilizar el mismo método en caso de una venta modificada
            //TODO Parametrizar estado inicial de venta
            venta.setIdVentasEstados(estadosFacade.find(2L)); //Aceptada
            ventasFacade.create(venta);
            em.flush();
            for (VentasPagos pago : pagos) {
                pago.setFechaPago(venta.getFechaVenta());
                pago.setIdPersona(venta.getIdPersona());
                pago.setIdUsuario(venta.getIdUsuario());
                pago.setIdSucursal(venta.getIdSucursal());
                pagosFacade.create(pago);
                VentasPagosLineas lineaPago = new VentasPagosLineas();
                lineaPago.setIdPagoVenta(pago);
                lineaPago.setIdVenta(venta);
                lineaPago.setImporte(pago.getImporteTotalPagado());
                pagosLineasFacade.create(lineaPago);
            }

            cuentaCorrienteBean.registrarMovimientoCuenta(venta.getIdPersona(), venta.getTotal().negate(), "Venta Nro: " + venta.getId());
        }
    }

    /**
     * Anula la venta pasada como parámetro y la devuelve de la cuenta corriente
     * del cliente
     *
     * @param venta
     * @throws ServiceException
     */
    public void anularVenta(Ventas venta) throws ServiceException {
        if (venta == null) {
            throw new ServiceException("Venta nula!");
        }
        if (venta.getAnulada()) {
            throw new ServiceException("La venta ya fue anulada!");
        }

        venta.setAnulada(true);
        cuentaCorrienteBean.registrarMovimientoCuenta(venta.getIdPersona(),
                venta.getTotal(), "Anulación venta Nro: " + venta.getId());
        ventasFacade.edit(venta);
    }

}
