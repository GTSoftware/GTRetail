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
import ar.com.gtsoftware.bl.CobranzaService;
import ar.com.gtsoftware.eao.CajasFacade;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.search.CajasSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "cobranzaBean")
@ViewScoped
public class CobranzaBean implements Serializable {

    @EJB
    private CobranzaService cobranzaServiceImpl;

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private CajasSearchFilter cajasFilter;
    private Cajas cajaActual;

    @EJB
    private CajasFacade facade;

    /**
     * Creates a new instance of CobranzaBean
     */
    public CobranzaBean() {
    }

    @PostConstruct
    private void init() {
        cajasFilter = new CajasSearchFilter(authBackingBean.getUserLoggedIn(),
                authBackingBean.getUserLoggedIn().getIdSucursal(), Boolean.TRUE);
        cajasFilter.addSortField(new SortField("fechaApertura", false));

        int cantCajasAbiertas = facade.countBySearchFilter(cajasFilter);
        if (cantCajasAbiertas > 1) {
            throw new RuntimeException(String.format("El usuario %s tiene más de una caja abierta en la sucursal %d!",
                    authBackingBean.getUserLoggedIn().getNombreUsuario(),
                    authBackingBean.getUserLoggedIn().getIdSucursal().getId()));
        }
        if (cantCajasAbiertas == 0) {

            Cajas caja = new Cajas();
            caja.setFechaApertura(new Date());
            caja.setIdUsuario(authBackingBean.getUserLoggedIn());
            caja.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            caja.setSaldoInicial(BigDecimal.ZERO);//TODO ver el arrastre de saldos
            facade.create(caja);
        }
        cajaActual = facade.findFirstBySearchFilter(cajasFilter);
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

    public void cobrarComprobante(Comprobantes comprobante) {
        cobranzaServiceImpl.cobrarComprobanteEfectivo(cajaActual, comprobante);
    }
}