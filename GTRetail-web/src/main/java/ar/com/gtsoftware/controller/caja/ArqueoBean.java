/*
 * Copyright 2016 GT Software.
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
package ar.com.gtsoftware.controller.caja;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.eao.CajasFacade;
import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.CajasArqueos;
import ar.com.gtsoftware.model.CajasArqueosDetalle;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.search.CajasSearchFilter;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "arqueoBean")
@ViewScoped
public class ArqueoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ArqueoBean.class.getName());

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private JSFUtil jsfUtil;

    private CajasSearchFilter cajasFilter;
    private Cajas cajaActual;

    @EJB
    private CajasFacade cajasFacade;

    @EJB
    private NegocioFormasPagoFacade formasPagoFacade;

    private final FormasPagoSearchFilter formasPagoSearchFilter = new FormasPagoSearchFilter(true, null);

    private CajasArqueos arqueoActual = new CajasArqueos();

    private CajasArqueosDetalle detalleArqueoActual;

    private int itemId = 1;

    /**
     * Creates a new instance of CobranzaBean
     */
    public ArqueoBean() {
    }

    @PostConstruct
    private void init() {
        cajasFilter = new CajasSearchFilter(authBackingBean.getUserLoggedIn(),
                authBackingBean.getUserLoggedIn().getIdSucursal(), Boolean.TRUE);
        cajasFilter.addSortField(new SortField("fechaApertura", false));

        int cantCajasAbiertas = cajasFacade.countBySearchFilter(cajasFilter);
        if (cantCajasAbiertas > 1) {
            throw new RuntimeException(String.format("El usuario %s tiene más de una caja abierta en la sucursal %d!",
                    authBackingBean.getUserLoggedIn().getNombreUsuario(),
                    authBackingBean.getUserLoggedIn().getIdSucursal().getId()));
        }
        if (cantCajasAbiertas == 0) {
            throw new RuntimeException("El usuario no tiene una caja abierta para realizar el arqueo.");

//            Cajas caja = new Cajas();
//            caja.setFechaApertura(new Date());
//            caja.setIdUsuario(authBackingBean.getUserLoggedIn());
//            caja.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
//            caja.setSaldoInicial(BigDecimal.ZERO);//TODO ver el arrastre de saldos
//            cajasFacade.create(caja);
        }
        cajaActual = cajasFacade.findFirstBySearchFilter(cajasFilter);
        arqueoActual.setSaldoInicial(cajaActual.getSaldoInicial());
        arqueoActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        arqueoActual.setIdCaja(cajaActual);
        detalleArqueoActual = new CajasArqueosDetalle();
        detalleArqueoActual.setItem(itemId);
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public Cajas getCajaActual() {
        return cajaActual;
    }

    public void agregarDetalleArqueo() {

        if (validarFormaPagoYaIngresada(detalleArqueoActual)) {
            detalleArqueoActual.setMontoSistema(obtenerSaldoCajaFormaPago(detalleArqueoActual.getIdFormaPago()));
            detalleArqueoActual.setDiferencia(detalleArqueoActual.getMontoDeclarado().subtract(detalleArqueoActual.getMontoSistema()));

            arqueoActual.agregarDetalleArqueo(detalleArqueoActual);
            detalleArqueoActual = new CajasArqueosDetalle();
            detalleArqueoActual.setItem(++itemId);
            jsfUtil.addInfoMessage("Se ha agregado el monto al arqueo con éxito");
        } else {
            jsfUtil.addErrorMessage("Ya se ha ingresado esa forma de pago!");

        }
    }

    private boolean validarFormaPagoYaIngresada(CajasArqueosDetalle detalleNuevo) {
        if (arqueoActual.getDetalleArqueo() != null) {
            for (CajasArqueosDetalle d : arqueoActual.getDetalleArqueo()) {
                if (d.getIdFormaPago().equals(detalleNuevo.getIdFormaPago())) {
                    return false;
                }
            }
        }
        return true;
    }

    private BigDecimal obtenerSaldoCajaFormaPago(NegocioFormasPago formaPago) {
        return cajasFacade.obtenerMontoFormaPago(formaPago, cajaActual);
    }

    public List<NegocioFormasPago> getFormasPagoList() {
        return formasPagoFacade.findAllBySearchFilter(formasPagoSearchFilter);
    }

    public CajasArqueos getArqueoActual() {
        return arqueoActual;
    }

    public void setArqueoActual(CajasArqueos arqueoActual) {
        this.arqueoActual = arqueoActual;
    }

    public CajasArqueosDetalle getDetalleArqueoActual() {
        return detalleArqueoActual;
    }

    public void setDetalleArqueoActual(CajasArqueosDetalle detalleArqueoActual) {
        this.detalleArqueoActual = detalleArqueoActual;
    }

}
