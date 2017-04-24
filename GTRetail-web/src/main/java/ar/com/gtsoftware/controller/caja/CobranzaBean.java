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
import ar.com.gtsoftware.bl.CajasService;
import ar.com.gtsoftware.bl.CobranzaService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.ComprobantesPagos;
import ar.com.gtsoftware.model.Cupones;
import ar.com.gtsoftware.model.Recibos;
import ar.com.gtsoftware.model.dto.PagoValorDTO;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "cobranzaBean")
@ViewScoped
public class CobranzaBean implements Serializable {

    @EJB
    private CajasService cajasService;

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CobranzaBean.class.getName());

    @EJB
    private CobranzaService cobranzaServiceImpl;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @ManagedProperty(value = "#{recibosSearchBean}")
    private RecibosSearchBean recibosSearchBean;

    @EJB
    private JSFUtil jsfUtil;

    private Cajas cajaActual;

    private Recibos reciboActual;

    private Comprobantes comprobanteACobrar;

    private List<Comprobantes> selectedComprobantes;

    private List<PagoValorDTO> pagosValores;

    /**
     * Creates a new instance of CobranzaBean
     */
    public CobranzaBean() {
    }
    //TODO permitir seleccionar varios comprobantes de un mismo cliente
    //Cambiar a otra parte de la pantalla para cargar los datos de los valores y agruparlos
    //Permitir la cobranza de comprobantes en cuenta corriente. Habra que generar notas de débito si la forma de pago elegida tiene recargos (Tal vez no permitirlos directamente para esta versión)

    @PostConstruct
    private void init() {
        Cajas cajaAbierta = cajasService.obtenerCajaActual(authBackingBean.getUserLoggedIn());
        if (cajaAbierta == null) {
            RequestContext.getCurrentInstance().execute("PF('abrirCajaDialog').show();");
        }

        cajaActual = cajaAbierta;
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

    public void cobrarComprobante() {
        RequestContext.getCurrentInstance().execute("PF('cobrarComprobanteDialog').hide();");
        if (cajaActual == null) {
            jsfUtil.addErrorMessage("No hay una caja abierta.");
            return;
        }

        if (comprobanteACobrar == null) {
            return;
        }
        if (!comprobanteACobrar.getIdCondicionComprobante().getPagoTotal()) {
            jsfUtil.addErrorMessage("No implementado para cobranza de comprobantes en CC!");
            return;
        }
        boolean requiereValores = comprobanteACobrar.getPagosList().stream().anyMatch(p -> p.getIdFormaPago().getRequiereValores());
        if (requiereValores) {
            jsfUtil.addErrorMessage("Se requiere ingresar valores!");
            return;
        }
        try {
            reciboActual = cobranzaServiceImpl.cobrarComprobante(cajaActual, comprobanteACobrar);
            jsfUtil.addInfoMessage(String.format("Comprobante cobrado exitosamente con recibo: %d", reciboActual.getId()));
            RequestContext.getCurrentInstance().execute("PF('cobrarComprobanteDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('imprimirReciboDialog').show();");
        } catch (ServiceException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public Comprobantes getComprobanteACobrar() {
        return comprobanteACobrar;
    }

    public void setComprobanteACobrar(Comprobantes comprobanteACobrar) {
        this.comprobanteACobrar = comprobanteACobrar;

    }

    public RecibosSearchBean getRecibosSearchBean() {
        return recibosSearchBean;
    }

    public void setRecibosSearchBean(RecibosSearchBean recibosSearchBean) {
        this.recibosSearchBean = recibosSearchBean;
    }

    public void imprimirRecibo() {
        recibosSearchBean.imprimirRecibo(reciboActual);
    }

    /**
     * Realiza la apertura de caja para el usuario.
     */
    public void abrirCaja() {
        Cajas cajaAbierta = cajasService.abrirCaja(authBackingBean.getUserLoggedIn());
        cajaActual = cajaAbierta;
    }

    public List<Comprobantes> getSelectedComprobantes() {
        return selectedComprobantes;
    }

    public void setSelectedComprobantes(List<Comprobantes> selectedComprobantes) {
        this.selectedComprobantes = selectedComprobantes;
    }

    private boolean validarComprobantesSeleccionados() {
        if (CollectionUtils.isEmpty(selectedComprobantes)) {
            jsfUtil.addErrorMessage("No hay comprobantes seleccionados.");
            return false;
        }
        Long idPersona = selectedComprobantes.get(0).getIdPersona().getId();
        for (Comprobantes c : selectedComprobantes) {
            if (!Objects.equals(idPersona, c.getIdPersona().getId())) {
                jsfUtil.addErrorMessage("Debe seleccionar comprobatnes de un mismo cliente.");
                return false;
            }
        }
        for (Comprobantes c : selectedComprobantes) {
            if (!c.getIdCondicionComprobante().getPagoTotal()) {
                jsfUtil.addErrorMessage("No implementado para cobranza de comprobantes en CC!");
                return false;
            }
        }
        return true;
    }

    public void cobrarComprobantes() {
        if (validarComprobantesSeleccionados()) {

            reciboActual = cobranzaServiceImpl.cobrarComprobantes(cajaActual, pagosValores);
            jsfUtil.addInfoMessage(String.format("Comprobante cobrado exitosamente con recibo: %d", reciboActual.getId()));
            RequestContext.getCurrentInstance().execute("PF('cobrarComprobantesDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('imprimirReciboDialog').show();");
        }
    }

    public void cargarValores() {

        pagosValores = new ArrayList<>();
        int item = 1;
        for (Comprobantes comp : selectedComprobantes) {
            for (ComprobantesPagos pago : comp.getPagosList()) {
                PagoValorDTO pagoValor = new PagoValorDTO(item++, pago, null);
                if (pago.getIdFormaPago().getRequiereValores()) {
                    //Supongo que siempre es con tarjeta, después veo como hago lo de cheques
                    //TODO: cable para que detecte el tipo de pago y mande a una lista distinta de DTOs
                    Cupones cupon = new Cupones();
                    cupon.setCantCuotas(pago.getIdDetallePlan().getCuotas());
                    cupon.setCoeficiente(pago.getIdDetallePlan().getCoeficienteInteres());
                    cupon.setMonto(pago.getMontoPago());
                    pagoValor.setCupon(cupon);

                }
                pagosValores.add(pagoValor);
            }
        }
    }

    public List<PagoValorDTO> getPagosValores() {
        return pagosValores;
    }

}
