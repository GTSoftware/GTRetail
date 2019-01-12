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
import ar.com.gtsoftware.bl.*;
import ar.com.gtsoftware.dto.PagoValorDTO;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.search.BancosSearchFilter;
import ar.com.gtsoftware.search.ComprobantesPagosSearchFilter;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static ar.com.gtsoftware.utils.JSFUtil.addErrorMessage;
import static ar.com.gtsoftware.utils.JSFUtil.addInfoMessage;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "cobranzaBean")
@ViewScoped
public class CobranzaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CobranzaBean.class.getName());
    private static final BigDecimal REDONDEO = new BigDecimal(5);

    @EJB
    private CajasService cajasService;
    @EJB
    private CobranzaService cobranzaService;
    @EJB
    private BancosService bancosService;
    @EJB
    private NegocioFormasPagoService formasPagoService;
    @EJB
    private ComprobantesPagosService pagosService;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @ManagedProperty(value = "#{recibosSearchBean}")
    private RecibosSearchBean recibosSearchBean;

    private CajasDto cajaActual;

    private RecibosDto reciboActual;


    private List<ComprobantesDto> selectedComprobantes;

    private List<PagoValorDTO> pagosValores;

    private boolean hayCupones = false;
    private boolean hayCheques = false;

    private List<BancosDto> bancosList;

    /**
     * Creates a new instance of CobranzaBean
     */
    public CobranzaBean() {
    }

    //Permitir la cobranza de comprobantes en cuenta corriente. Habra que generar notas de débito si la forma de pago elegida tiene recargos (Tal vez no permitirlos directamente para esta versión)
    @PostConstruct
    private void init() {
        CajasDto cajaAbierta = cajasService.obtenerCajaActual(authBackingBean.getUserLoggedIn());
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

    public CajasDto getCajaActual() {
        return cajaActual;
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
        cajaActual = cajasService.abrirCaja(authBackingBean.getUserLoggedIn());
    }

    public List<ComprobantesDto> getSelectedComprobantes() {
        return selectedComprobantes;
    }

    public void setSelectedComprobantes(List<ComprobantesDto> selectedComprobantes) {
        this.selectedComprobantes = selectedComprobantes;
    }

    private boolean validarComprobantesSeleccionados() {
        if (CollectionUtils.isEmpty(selectedComprobantes)) {
            addErrorMessage("No hay comprobantes seleccionados.");
            return false;
        }
        Long idPersona = selectedComprobantes.get(0).getIdPersona().getId();
        for (ComprobantesDto c : selectedComprobantes) {
            if (!Objects.equals(idPersona, c.getIdPersona().getId())) {
                addErrorMessage("Debe seleccionar comprobatnes de un mismo cliente.");
                return false;
            }
        }
        //TODO Validar que se completen todos los campos requeridos de las formas de pago
//        //Deben ser bien todos de pago contado o todos de corriente
//        boolean allContado = selectedComprobantes.stream().allMatch(x -> x.getIdCondicionComprobante().getPagoTotal());
//        boolean allCuentaCorriente = selectedComprobantes.stream().allMatch(x -> !x.getIdCondicionComprobante().getPagoTotal());
//
//        if (!(allContado || allCuentaCorriente)) {
//            addErrorMessage("Los comprobantes a cobrar deben ser todos de cuenta corriente o todos de contado.");
//            return false;
//        }
        return true;
    }

    public void cobrarComprobantes() {
        if (cajaActual == null) {
            addErrorMessage("No posee una caja abierta");
            return;
        }
        if (validarComprobantesSeleccionados() && validarMontosEnPagos()) {

            reciboActual = cobranzaService.cobrarComprobantes(cajaActual, pagosValores);
            addInfoMessage(String.format("Comprobante cobrado exitosamente con recibo: %d", reciboActual.getId()));
            RequestContext.getCurrentInstance().execute("PF('cobrarComprobantesDialog').hide();");
            RequestContext.getCurrentInstance().execute("PF('imprimirReciboDialog').show();");
        }
    }

    private boolean validarMontosEnPagos() {
        for (PagoValorDTO pago : pagosValores) {
            if (pago.getMontoRealPagado().compareTo(pago.getMontoMaximoConRedondeo()) > 0
                    || pago.getMontoRealPagado().compareTo(pago.getMontoMinimoConRedondeo()) < 0) {

                addErrorMessage(String.format("El monto pago para el comp: %s no se encuentra dentro de los parámetros válidos",
                        pago.getPago().getIdComprobante()));
                return false;
            }
        }
        return true;
    }

    public void cargarValores() {

        pagosValores = new ArrayList<>();
        int item = 1;
        for (ComprobantesDto comp : selectedComprobantes) {
            //Si es contado tiene los registros de pago, sino hay que hacerlos porque es CC.
            //if (comp.getIdCondicionComprobante().getPagoTotal()) {
            BigDecimal montoPorCubrir = comp.getSaldo();
            ComprobantesPagosSearchFilter pagosFilter = new ComprobantesPagosSearchFilter(comp.getId(), true);
            for (ComprobantesPagosDto pago : pagosService.findAllBySearchFilter(pagosFilter)) {
                BigDecimal saldoPago = pago.getMontoPago().subtract(pago.getMontoPagado());
                montoPorCubrir = montoPorCubrir.subtract(saldoPago);
                PagoValorDTO pagoValor = PagoValorDTO.builder()
                        .item(item++)
                        .pago(pago)
                        .montoRealPagado(pago.getMontoPago())
                        .montoMaximo(saldoPago)
                        .montoMaximoConRedondeo(saldoPago)
                        .montoMinimoConRedondeo(saldoPago)
                        .build();

                if (pago.getIdFormaPago().isRequiereValores()) {
                    //Cheque == 4
                    if (pago.getIdFormaPago().getId() != 4) {

                        CuponesDto cupon = new CuponesDto();
                        cupon.setCantCuotas(pago.getIdDetallePlan().getCuotas());
                        cupon.setCoeficiente(pago.getIdDetallePlan().getCoeficienteInteres());
                        cupon.setMonto(pago.getMontoPagoConSigno());
                        pagoValor.setCupon(cupon);
                        hayCupones = true;
                    } else {
                        ChequesTercerosDto chq = new ChequesTercerosDto();
                        chq.setMonto(pago.getMontoPagoConSigno());
                        pagoValor.setCheque(chq);
                        hayCheques = true;
                    }

                }
                if (pago.getIdFormaPago().getId() == 1) {
                    pagoValor.setMontoEditable(true);
                    BigDecimal saldoPagoSinDecimales = saldoPago.setScale(0, RoundingMode.HALF_UP);
                    pagoValor.setMontoMinimoConRedondeo(saldoPagoSinDecimales
                            .subtract(REDONDEO).max(BigDecimal.ZERO));
                    pagoValor.setMontoMaximoConRedondeo(saldoPagoSinDecimales.add(REDONDEO));
                    pagoValor.setMontoRealPagado(saldoPago);
                }

                pagosValores.add(pagoValor);
            }
            //Esto es para cuando no se definió una forma de pago en el comprobante (ej: cuenta corriente)
            if (montoPorCubrir.signum() > 0) {

                ComprobantesPagosDto cp = new ComprobantesPagosDto();
                cp.setIdComprobante(comp);
                //Cableado para efectivo...
                cp.setIdFormaPago(formasPagoService.find(1L));
                cp.setMontoPago(montoPorCubrir);

                comp.addPago(cp);

                PagoValorDTO pagoValor = PagoValorDTO.builder()
                        .item(item++)
                        .pago(cp)
                        .montoEditable(true)
                        .montoMaximo(montoPorCubrir)
                        .montoRealPagado(montoPorCubrir)
                        .montoMaximoConRedondeo(montoPorCubrir)
                        .montoMinimoConRedondeo(BigDecimal.ZERO).build();
                pagosValores.add(pagoValor);
            }

        }
    }

    public List<PagoValorDTO> getPagosValores() {
        //TODO: Sería bueno separar cada lista según el tipo de pago para que en la vista quede mejor presentado.
        return pagosValores;
    }

    /**
     * Si existe alguna forma de pago con cupones devuelve true.
     *
     * @return
     */
    public boolean getHayCupones() {
        return hayCupones;
    }

    /**
     * Si existe alguna forma de pago con cheques devuelve true.
     *
     * @return
     */
    public boolean getHayCheques() {
        return hayCheques;
    }

    /**
     * Busca los bancos
     *
     * @return
     */
    public List<BancosDto> getBancosList() {
        if (bancosList == null) {
            BancosSearchFilter sf = new BancosSearchFilter();
            sf.addSortField("razonSocial", true);
            bancosList = bancosService.findAllBySearchFilter(sf);
        }
        return bancosList;
    }
}
