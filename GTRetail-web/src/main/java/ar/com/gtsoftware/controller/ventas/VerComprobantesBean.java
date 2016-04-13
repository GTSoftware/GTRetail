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
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.bl.impl.FacturacionVentasBean;
import ar.com.gtsoftware.bl.impl.VentasBean;
import ar.com.gtsoftware.eao.ComprobantesFacade;
import ar.com.gtsoftware.eao.ComprobantesLineasFacade;
import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.eao.FiscalPuntosVentaFacade;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.ComprobantesLineas;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.FiscalPuntosVenta;
import ar.com.gtsoftware.search.FiscalPeriodosFiscalesSearchFilter;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "verComprobantesBean")
@ViewScoped
public class VerComprobantesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Comprobantes ventaActual;
    private List<ComprobantesLineas> lineasVenta = new ArrayList<>();
    @EJB
    private ComprobantesFacade ventasFacade;
    @EJB
    private VentasBean ventasBean;
    @EJB
    private ComprobantesLineasFacade lineasFacade;
    @EJB
    private FacturacionVentasBean facturacionBean;

    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;
    @EJB
    private FiscalPuntosVentaFacade puntosVentaFacade;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private final List<FiscalPuntosVenta> puntosVentaList = new ArrayList<>();

    private FiscalPuntosVenta puntoVentaSeleccionado;
    private static final Logger LOG = Logger.getLogger(VerComprobantesBean.class.getName());

    /**
     * Creates a new instance of VerVentasBean
     */
    public VerComprobantesBean() {
    }

    public void init() {
        if (JSFUtil.isPostback()) {
            return;
        }
        String idVenta = JSFUtil.getRequestParameterMap().get("idComprobante");
        if (idVenta == null) {
            throw new IllegalArgumentException("Parámetro nulo!");
        } else {
            ventaActual = ventasFacade.find(Long.parseLong(idVenta));
            if (ventaActual == null) {

                JSFUtil.addErrorMessage("Venta inexistente!");
                LOG.log(Level.INFO, "Cliente inexistente!");
            } else {
                lineasVenta.addAll(lineasFacade.findVentasLineas(ventaActual));

                //puntoVentaComprobante = authBackingBean.getUserLoggedIn().getPuntoVenta();
                //numeroComprobante = facturacionBean.obtenerProximoNumeroFactura(ventaActual.getLetra(), puntoVentaComprobante);
                puntosVentaList.addAll(puntosVentaFacade.findAllBySearchFilter(new FiscalPuntosVentaSearchFilter(
                        authBackingBean.getUserLoggedIn().getIdSucursal(), Boolean.TRUE)));
            }
        }
    }

    /**
     * Registra la factura en el libro de IVA Ventas
     */
    public void registrarFactura() {
        FiscalPeriodosFiscalesSearchFilter psf = new FiscalPeriodosFiscalesSearchFilter(Boolean.TRUE);
        FiscalPeriodosFiscales periodo = periodosFiscalesFacade.findFirstBySearchFilter(psf);
        if (periodo == null) {
            JSFUtil.addErrorMessage("No hay un período fiscal configurado!");
        }
        if (puntoVentaSeleccionado == null) {
            JSFUtil.addErrorMessage("Debe seleccionar un punto de venta.");
        }
        try {
            String numeroComprobante = facturacionBean.obtenerProximoNumeroFactura(ventaActual.getLetra(),
                    puntoVentaSeleccionado.getNroPuntoVenta().toString());

            facturacionBean.registrarFacturaVenta(ventaActual,
                    puntoVentaSeleccionado, numeroComprobante,
                    periodo, new Date());
            ventaActual = ventasFacade.find(ventaActual.getId());
            JSFUtil.addInfoMessage("Factura registrada correctamente");
        } catch (ServiceException ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(ex.getMessage());
        }

    }

    /**
     * Anula la venta pasada por parámetro y la factura si es que la tiene
     */
    public void anularVenta() {
        try {
            ventasBean.anularVenta(ventaActual);
            JSFUtil.addInfoMessage("Factura anulada correctamente");

        } catch (ServiceException ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.addErrorMessage(ex.getMessage());

        }
    }

    public Comprobantes getVentaActual() {
        return ventaActual;
    }

    public void setVentaActual(Comprobantes ventaActual) {
        this.ventaActual = ventaActual;
    }

    public List<ComprobantesLineas> getLineasVenta() {
        return lineasVenta;
    }

    public void setLineasVenta(List<ComprobantesLineas> lineasVenta) {
        this.lineasVenta = lineasVenta;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public List<FiscalPuntosVenta> getPuntosVentaList() {
        return puntosVentaList;
    }

    public FiscalPuntosVenta getPuntoVentaSeleccionado() {
        return puntoVentaSeleccionado;
    }

    public void setPuntoVentaSeleccionado(FiscalPuntosVenta puntoVentaSeleccionado) {
        this.puntoVentaSeleccionado = puntoVentaSeleccionado;
    }

}
