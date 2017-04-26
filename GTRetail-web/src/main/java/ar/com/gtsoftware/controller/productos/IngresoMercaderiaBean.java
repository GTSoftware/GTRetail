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
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.RemitoFacade;
import ar.com.gtsoftware.eao.RemitoRecepcionFacade;
import ar.com.gtsoftware.eao.RemitoTipoMovimientoFacade;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.Remito;
import ar.com.gtsoftware.model.RemitoDetalle;
import ar.com.gtsoftware.model.RemitoRecepcion;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private PersonasFacade personasFacade;
    @EJB
    private RemitoFacade remitoFacade;
    @EJB
    private DepositosFacade depositosFacade;
    @EJB
    private RemitoTipoMovimientoFacade remitoTipoMovimientoFacade;

    @EJB
    private RemitoRecepcionFacade remitoRecepcionFacade;
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private JSFUtil jsfUtil;

    private Remito remitoCabecera = new Remito();
    private List<Personas> proveedoresList = new ArrayList();
    private RemitoDetalle remitoDetalle = new RemitoDetalle();

    @PostConstruct
    public void init() {
        PersonasSearchFilter psf = new PersonasSearchFilter();
        psf.setProveedor(true);
        psf.setActivo(true);
        psf.addSortField(new SortField("razonSocial", true));
        proveedoresList.addAll(personasFacade.findAllBySearchFilter(psf));
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
                remitoCabecera.getIdOrigenExterno()));
        remitoFacade.create(remitoCabecera);

        //REALIZA LA RECEPCION DEL REMITO
        RemitoRecepcion recepcion = new RemitoRecepcion();

        recepcion.setRemito(remitoCabecera);
        recepcion.setIdPersona(remitoCabecera.getIdOrigenExterno());
        recepcion.setIdUsuario(authBackingBean.getUserLoggedIn());

        remitoRecepcionFacade.create(recepcion);

        return "/protected/index.xhtml?faces-redirect=true";
    }

    //---Getter and Setter ----------------------------------------------
    public Remito getRemitoCabecera() {
        return remitoCabecera;
    }

    public void setRemitoCabecera(Remito remitoCabecera) {
        this.remitoCabecera = remitoCabecera;
    }

    public List<Personas> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Personas> proveedoresList) {
        this.proveedoresList = proveedoresList;
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
