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
package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.eao.DepositosFacade;
import ar.com.gtsoftware.eao.RemitoFacade;
import ar.com.gtsoftware.eao.RemitoTipoMovimientoFacade;
import ar.com.gtsoftware.model.Remito;
import ar.com.gtsoftware.model.RemitoDetalle;
import ar.com.gtsoftware.model.RemitoRecepcion;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author fede
 */
@ManagedBean(name = "ingresoMercaderiaBean")
@ViewScoped
public class IngresoMercaderiaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private RemitoFacade remitoFacade;
    @EJB
    private DepositosFacade depositosFacade;
    @EJB
    private RemitoTipoMovimientoFacade remitoTipoMovimientoFacade;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private JSFUtil jsfUtil;

    private Remito remitoCabecera = new Remito();

    private RemitoDetalle remitoDetalle = new RemitoDetalle();

    @PostConstruct
    public void init() {

        remitoCabecera.setDetalleList(new ArrayList<>());
        remitoCabecera.setIdDestinoPrevistoInterno(depositosFacade.find(2L));//TODO: ID deposito Cableado por el momento
        remitoCabecera.setIdUsuario(authBackingBean.getUserLoggedIn());
        remitoCabecera.setFechaAlta(new Date());
        remitoCabecera.setIsOrigenInterno(Boolean.FALSE);
        remitoCabecera.setIsDestinoInterno(Boolean.TRUE);

    }

    public void agregarLinea() {

        if (remitoDetalle.getIdProducto() == null) {
            jsfUtil.addErrorMessage("Debe seleccionar un producto antes.");
            return;
        }

        if (remitoCabecera.getDetalleList() == null) {
            remitoCabecera.setDetalleList(new ArrayList<>());
        }
        remitoDetalle.setRemitoCabecera(remitoCabecera);
        remitoCabecera.getDetalleList().add(remitoDetalle);
        remitoDetalle = new RemitoDetalle();

    }

    public String confirmarIngreso() {
        if (remitoCabecera.getDetalleList().isEmpty()) {
            jsfUtil.addErrorMessage("Debe ingresar algunas lineas.");
            return "";
        }

        remitoCabecera.setRemitoTipoMovimiento(remitoTipoMovimientoFacade.find(2L));
        remitoCabecera.setObservaciones(String.format("Ingreso de mercaderia del proveedor: %s",
                remitoCabecera.getIdOrigenExterno().getBusinessString()));

        RemitoRecepcion recepcion = new RemitoRecepcion();

        recepcion.setRemito(remitoCabecera);
        recepcion.setIdPersona(remitoCabecera.getIdOrigenExterno());
        recepcion.setIdUsuario(authBackingBean.getUserLoggedIn());
        recepcion.setIdDeposito(remitoCabecera.getIdDestinoPrevistoInterno());

        remitoCabecera.setRemitoRecepcionesList(Arrays.asList(recepcion));
        remitoFacade.create(remitoCabecera);

        //TODO: Recorrer la lista de productos y actualziar el stock
        return "/protected/index.xhtml?faces-redirect=true";
    }

    //---Getter and Setter ----------------------------------------------
    public Remito getRemitoCabecera() {
        return remitoCabecera;
    }

    public void setRemitoCabecera(Remito remitoCabecera) {
        this.remitoCabecera = remitoCabecera;
    }

    public RemitoDetalle getRemitoDetalle() {
        return remitoDetalle;
    }

    public void setRemitoDetalle(RemitoDetalle remitoDetalle) {
        this.remitoDetalle = remitoDetalle;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

}
