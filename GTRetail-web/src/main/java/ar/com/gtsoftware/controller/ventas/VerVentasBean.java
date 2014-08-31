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
package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.eao.FiscalLetrasComprobantesFacade;
import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.eao.VentasFacade;
import ar.com.gtsoftware.eao.VentasLineasFacade;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.VentasLineas;
import ar.com.gtsoftware.utils.UtilUI;
import ar.com.gtsofware.bl.FacturacionVentasBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "verVentasBean")
@ViewScoped
public class VerVentasBean {

    private Ventas ventaActual;
    private List<VentasLineas> lineasVenta = new ArrayList<>();
    @EJB
    private VentasFacade ventasFacade;
    @EJB
    private VentasLineasFacade lineasFacade;
    @EJB
    private FacturacionVentasBean facturacionBean;
    @EJB
    private FiscalLetrasComprobantesFacade letrasComprobantesFacade;
    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;
    @Inject
    private AuthBackingBean authBackingBean;

    private String letraComprobante = "B";
    private String puntoVentaComprobante = "0000";
    private String numeroComprobante = "00000000";
    private Date fechaFactura = UtilUI.getNow();

    /**
     * Creates a new instance of ImpresionVentasBean
     */
    public VerVentasBean() {
    }

    @PostConstruct
    private void init() {
        String idVenta = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idVenta");
        if (idVenta == null) {
            //TODO error y fallo 
        } else {
            ventaActual = ventasFacade.find(Integer.parseInt(idVenta));
            if (ventaActual == null) {
                //TODO Error!
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Venta inexistente!", "Venta inexistente!"));
                Logger.getLogger(VerVentasBean.class.getName()).log(Level.INFO, "Cliente inexistente!");
            } else {
                lineasVenta.addAll(lineasFacade.findVentasLineas(ventaActual));
                letraComprobante = letrasComprobantesFacade.findLetraComprobante(2, ventaActual.getIdPersona().getIdResponsabilidadIva()).getLetraComprobante();
                puntoVentaComprobante = "0001"; //TODO Fix HARDCODE
                numeroComprobante = facturacionBean.obtenerProximoNumeroFactura(letraComprobante, puntoVentaComprobante);
            }
        }
    }
    
    public void registrarFactura() {
        try {
            FiscalPeriodosFiscales pf = periodosFiscalesFacade.findAll().get(0);
            facturacionBean.registrarFacturaVenta(ventaActual, letraComprobante, puntoVentaComprobante, numeroComprobante, pf, fechaFactura);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Factura registrada correctamente"));
        } catch (Exception ex) {
            Logger.getLogger(FacturacionVentasBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
        }

    }

    public Ventas getVentaActual() {
        return ventaActual;
    }

    public void setVentaActual(Ventas ventaActual) {
        this.ventaActual = ventaActual;
    }

    public List<VentasLineas> getLineasVenta() {
        return lineasVenta;
    }

    public void setLineasVenta(List<VentasLineas> lineasVenta) {
        this.lineasVenta = lineasVenta;
    }

    public String getLetraComprobante() {
        return letraComprobante;
    }

    public void setLetraComprobante(String letraComprobante) {
        this.letraComprobante = letraComprobante;
    }

    public String getPuntoVentaComprobante() {
        return puntoVentaComprobante;
    }

    public void setPuntoVentaComprobante(String puntoVentaComprobante) {
        this.puntoVentaComprobante = puntoVentaComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

}
