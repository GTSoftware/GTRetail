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
import ar.com.gtsoftware.eao.CajasArqueosFacade;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

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
    @EJB
    private CajasArqueosFacade arqueosFacade;

    private final FormasPagoSearchFilter formasPagoSearchFilter = new FormasPagoSearchFilter(null, null);

    private CajasArqueos arqueoActual = new CajasArqueos();

    private CajasArqueosDetalle detalleArqueoActual;

    private int itemId = 1;

    private boolean arqueoGuardado = false;

    /**
     * Creates a new instance of ArqueoBean
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
            throw new RuntimeException("El usuario no tiene una caja abierta para poder realizar el arqueo.");
        }
        cajaActual = cajasFacade.findFirstBySearchFilter(cajasFilter);
        arqueoActual.setSaldoInicial(cajaActual.getSaldoInicial());
        arqueoActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        arqueoActual.setIdCaja(cajaActual);
        arqueoActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
        arqueoActual.setDetalleArqueo(new ArrayList<>());
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

    /**
     * Verifica que no se haya ingresado una misma forma de pago más de una vez.
     *
     * @param detalleNuevo
     * @return
     */
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

    /**
     * Borra el item de detalle de arqueo pasado por parámetro
     *
     * @param item
     */
    public void borrarDetalle(int item) {
        int index = -1;
        int cont = 0;

        for (CajasArqueosDetalle da : arqueoActual.getDetalleArqueo()) {
            if (da.getItem() == item) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            arqueoActual.getDetalleArqueo().remove(index);
        }
    }

    /**
     * Verifica que los montos de sistema coincidan con el total en caja y que estén justificadas todas las diferencias.
     *
     * @return
     */
    private boolean validarArqueo() {

        StringBuilder sb = new StringBuilder();
        BigDecimal montoTotalCaja = cajasFacade.obtenerMontoFormaPago(null, cajaActual);
        BigDecimal montoTotalArqueo = BigDecimal.ZERO;

        for (CajasArqueosDetalle ad : arqueoActual.getDetalleArqueo()) {
            montoTotalArqueo = montoTotalArqueo.add(ad.getMontoSistema());
            if (ad.getDiferencia().signum() != 0 && StringUtils.isEmpty(ad.getDescargo())) {
                sb.append(String.format("La forma de pago: %s tiene una diferencia y debe ingresar el descargo.", ad.getIdFormaPago().getNombreFormaPago()));
            }
        }
        if (montoTotalCaja.compareTo(montoTotalArqueo) != 0) {
            sb.append("El monto de las formas de pago declaradas no coincide con el saldo en caja. Declare todas las formas de pago.\n");
        }
        String errores = sb.toString();
        if (StringUtils.isNotEmpty(errores)) {
            jsfUtil.addErrorMessage(errores);
            return false;
        }

        return true;

    }

    /**
     * Persiste el arqueo actual en la base de datos si es que es válido.
     */
    public void guardarArqueo() {
        if (!validarArqueo() && !arqueoGuardado) {
            return;
        }
        Date fechaActual = new Date();
        cajaActual.setFechaCierre(fechaActual);
        arqueoActual.setFechaArqueo(fechaActual);
        arqueoActual.setSaldoFinal(cajasFacade.obtenerMontoFormaPago(null, cajaActual));
        cajasFacade.edit(cajaActual);
        arqueosFacade.create(arqueoActual);
        arqueoGuardado = true;
        jsfUtil.addInfoMessage(String.format("Arqueo guardado con éxito id: %d", arqueoActual.getId()));

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
