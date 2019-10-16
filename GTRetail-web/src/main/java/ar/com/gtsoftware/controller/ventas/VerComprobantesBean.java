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
import ar.com.gtsoftware.bl.ComprobantesService;
import ar.com.gtsoftware.bl.FacturacionVentasService;
import ar.com.gtsoftware.bl.FiscalPuntosVentaService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.FiscalPuntosVentaDto;
import ar.com.gtsoftware.enums.TiposPuntosVenta;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "verComprobantesBean")
@ViewScoped
public class VerComprobantesBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(VerComprobantesBean.class.getName());
    private final List<FiscalPuntosVentaDto> puntosVentaList = new ArrayList<>();
    private ComprobantesDto ventaActual;
    @EJB
    private ComprobantesService ventasFacade;

    @EJB
    private FacturacionVentasService facturacionBean;

    @EJB
    private FiscalPuntosVentaService puntosVentaFacade;
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
    @Inject
    private JSFHelper jsfHelper;
    private FiscalPuntosVentaDto puntoVentaSeleccionado;
    private long numeroComprobante;
    private boolean permitirCargarNroComprobante = false;

    /**
     * Creates a new instance of VerVentasBean
     */
    public VerComprobantesBean() {
    }

    public void init() {
        if (jsfHelper.isPostback()) {
            return;
        }
        String idVenta = jsfHelper.getRequestParameterMap().get("idComprobante");
        if (idVenta == null) {
            throw new IllegalArgumentException("Parámetro nulo!");
        }
        ventaActual = ventasFacade.find(Long.parseLong(idVenta));
        if (ventaActual == null) {

            throw new IllegalArgumentException("Venta inexistente");

        }
        //lineasVenta.addAll(ventasFacade.obtenerLineasComprobantes(ventaActual.getId()));

        puntosVentaList.addAll(puntosVentaFacade.findAllBySearchFilter(FiscalPuntosVentaSearchFilter.builder()
                .idSucursal(authBackingBean.getUserLoggedIn().getIdSucursal().getId())
                .activo(true).build()));

    }

    /**
     * Registra la factura en el libro de IVA Ventas
     */
    public void registrarFactura() {

        if (puntoVentaSeleccionado == null) {
            jsfHelper.addErrorMessage("Debe seleccionar un punto de venta.");
            return;
        }
        if (puntoVentaSeleccionado.getTipo() != TiposPuntosVenta.MANUAL
                && puntoVentaSeleccionado.getTipo() != TiposPuntosVenta.ELECTRONICO) {
            jsfHelper.addErrorMessage("Solo disponible para puntos de venta manual o electrónico");
            return;
        }
        if (puntoVentaSeleccionado.getTipo() == TiposPuntosVenta.MANUAL && numeroComprobante == 0) {
            jsfHelper.addErrorMessage("Debe ingresar un número de comprobante");
            return;
        }
        try {

            facturacionBean.registrarFacturaVenta(ventaActual.getId(),
                    puntoVentaSeleccionado,
                    numeroComprobante,
                    new Date());
            ventaActual = ventasFacade.find(ventaActual.getId());
            jsfHelper.addInfoMessage(String.format("Factura registrada correctamente: %s",
                    ventaActual.getIdRegistro().getNumeroFactura()));
        } catch (ServiceException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            jsfHelper.addErrorMessage(ex.getMessage());
        }

    }

    /**
     * Anula la venta pasada por parámetro y la factura si es que la tiene
     */
    public void anularVenta() {
        try {
            facturacionBean.anularFactura(ventaActual.getId());
            jsfHelper.addInfoMessage("Factura anulada correctamente");
        } catch (ServiceException ex) {
            LOG.log(Level.SEVERE, null, ex);
            jsfHelper.addErrorMessage(ex.getMessage());
        }
    }

    public ComprobantesDto getVentaActual() {
        return ventaActual;
    }

    public void setVentaActual(ComprobantesDto ventaActual) {
        this.ventaActual = ventaActual;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public List<FiscalPuntosVentaDto> getPuntosVentaList() {
        return puntosVentaList;
    }

    public FiscalPuntosVentaDto getPuntoVentaSeleccionado() {
        return puntoVentaSeleccionado;
    }

    public void setPuntoVentaSeleccionado(FiscalPuntosVentaDto puntoVentaSeleccionado) {
        this.puntoVentaSeleccionado = puntoVentaSeleccionado;
    }

    public long getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(long numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public boolean isPermitirCargarNroComprobante() {
        return permitirCargarNroComprobante;
    }

    public void setPermitirCargarNroComprobante(boolean permitirCargarNroComprobante) {
        this.permitirCargarNroComprobante = permitirCargarNroComprobante;
    }

    public void cargarNumeroComprobante() {
        permitirCargarNroComprobante = false;
        if (puntoVentaSeleccionado.getTipo().equals(TiposPuntosVenta.MANUAL)) {
            numeroComprobante = facturacionBean.obtenerProximoNumeroFactura(ventaActual.getLetra(),
                    puntoVentaSeleccionado.getNroPuntoVenta());
            permitirCargarNroComprobante = true;
        }
    }

}
