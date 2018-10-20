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
import ar.com.gtsoftware.bl.CajasArqueosService;
import ar.com.gtsoftware.bl.CajasService;
import ar.com.gtsoftware.bl.NegocioFormasPagoService;
import ar.com.gtsoftware.dto.model.CajasArqueosDetalleDto;
import ar.com.gtsoftware.dto.model.CajasArqueosDto;
import ar.com.gtsoftware.dto.model.CajasDto;
import ar.com.gtsoftware.dto.model.NegocioFormasPagoDto;
import ar.com.gtsoftware.search.CajasSearchFilter;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static ar.com.gtsoftware.utils.JSFUtil.*;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "arqueoBean")
@ViewScoped
public class ArqueoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ArqueoBean.class.getName());

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private CajasDto cajaActual;


    @EJB
    private NegocioFormasPagoService formasPagoFacade;

    @EJB
    private CajasArqueosService arqueosService;

    @EJB
    private CajasService cajasService;

    private final FormasPagoSearchFilter formasPagoSearchFilter = new FormasPagoSearchFilter();

    private CajasArqueosDto arqueoActual = new CajasArqueosDto();

    private CajasArqueosDetalleDto detalleArqueoActual;

    private int itemId = 1;

    private boolean arqueoGuardado = false;

    /**
     * Creates a new instance of ArqueoBean
     */
    public ArqueoBean() {
    }

    @PostConstruct
    private void init() {
        CajasDto cajaAbierta = cajasService.obtenerCajaActual(authBackingBean.getUserLoggedIn());

        if (cajaAbierta == null) {
            addErrorMessage("El usuario no tiene una caja abierta para poder realizar el arqueo.");
            redirect("/protected/index.xhtml");

            return;
        }
        cajaActual = cajaAbierta;
        arqueoActual.setSaldoInicial(cajaActual.getSaldoInicial());
        arqueoActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        arqueoActual.setIdCaja(cajaActual);
        arqueoActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
        arqueoActual.setDetalleArqueo(new ArrayList<>());
        detalleArqueoActual = new CajasArqueosDetalleDto();
        detalleArqueoActual.setItem(itemId);
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

    public void agregarDetalleArqueo() {

        if (validarFormaPagoYaIngresada(detalleArqueoActual)) {
            detalleArqueoActual.setMontoSistema(obtenerSaldoCajaFormaPago(detalleArqueoActual.getIdFormaPago()));
            detalleArqueoActual.setDiferencia(detalleArqueoActual.getMontoDeclarado().subtract(detalleArqueoActual.getMontoSistema()));

            arqueoActual.getDetalleArqueo().add(detalleArqueoActual);
            detalleArqueoActual = new CajasArqueosDetalleDto();
            detalleArqueoActual.setItem(++itemId);
            addInfoMessage("Se ha agregado el monto al arqueo con éxito");
        } else {
            addErrorMessage("Ya se ha ingresado esa forma de pago!");

        }
    }

    /**
     * Verifica que no se haya ingresado una misma forma de pago más de una vez.
     *
     * @param detalleNuevo
     * @return
     */
    private boolean validarFormaPagoYaIngresada(CajasArqueosDetalleDto detalleNuevo) {
        if (CollectionUtils.isNotEmpty(arqueoActual.getDetalleArqueo())) {
            for (CajasArqueosDetalleDto d : arqueoActual.getDetalleArqueo()) {
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

        for (CajasArqueosDetalleDto da : arqueoActual.getDetalleArqueo()) {
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
        CajasSearchFilter csf = CajasSearchFilter.builder()
                .idCaja(cajaActual.getId()).build();
        BigDecimal montoTotalCaja = cajasService.obtenerTotalEnCaja(csf);
        BigDecimal montoTotalArqueo = BigDecimal.ZERO;

        for (CajasArqueosDetalleDto ad : arqueoActual.getDetalleArqueo()) {
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
            addErrorMessage(errores);
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

        arqueoActual.setFechaArqueo(fechaActual);
        CajasSearchFilter csf = CajasSearchFilter.builder()
                .idCaja(cajaActual.getId()).build();
        arqueoActual.setSaldoFinal(cajasService.obtenerTotalEnCaja(csf));
        arqueosService.createOrEdit(arqueoActual);
        cajasService.cerrarCaja(cajaActual, fechaActual);
        arqueoGuardado = true;
        addInfoMessage(String.format("Arqueo guardado con éxito id: %d", arqueoActual.getId()));

    }

    private BigDecimal obtenerSaldoCajaFormaPago(NegocioFormasPagoDto formaPago) {
        CajasSearchFilter csf = CajasSearchFilter.builder()
                .idCaja(cajaActual.getId())
                .idFormaPago(formaPago.getId()).build();

        return cajasService.obtenerTotalEnCaja(csf);
    }

    public List<NegocioFormasPagoDto> getFormasPagoList() {
        return formasPagoFacade.findAllBySearchFilter(formasPagoSearchFilter);
    }

    public CajasArqueosDto getArqueoActual() {
        return arqueoActual;
    }

    public void setArqueoActual(CajasArqueosDto arqueoActual) {
        this.arqueoActual = arqueoActual;
    }

    public CajasArqueosDetalleDto getDetalleArqueoActual() {
        return detalleArqueoActual;
    }

    public void setDetalleArqueoActual(CajasArqueosDetalleDto detalleArqueoActual) {
        this.detalleArqueoActual = detalleArqueoActual;
    }

    public boolean getArqueoGuardado() {
        return arqueoGuardado;
    }

}
