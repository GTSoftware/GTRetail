/*
 * Copyright 2018 GT Software.
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
package ar.com.gtsoftware.controller.proveedores;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.*;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.search.ComprobantesProveedorSearchFilter;
import ar.com.gtsoftware.search.FiscalPeriodosFiscalesSearchFilter;
import ar.com.gtsoftware.search.NegocioTiposComprobanteSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "proveedorComprobanteEditBean")
@ViewScoped
public class ProveedorComprobanteEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ProveedorComprobanteEditBean.class.getName());
    private final NegocioTiposComprobanteSearchFilter tipoCompSf = NegocioTiposComprobanteSearchFilter.builder()
            .activo(true).build();

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private ComprobantesProveedorService service;
    @EJB
    private FiscalPeriodosFiscalesService periodosFiscalesService;

    @EJB
    private FiscalLetrasComprobantesService letrasComprobantesService;
    @EJB
    private NegocioTiposComprobanteService tiposComprobanteService;
    @EJB
    private FiscalAlicuotasIvaService alicuotasIvaService;
    private List<FiscalPeriodosFiscalesDto> periodosFiscalesList = null;
    private List<NegocioTiposComprobanteDto> tiposComprobanteList = null;
    private ProveedoresComprobantesDto comprobanteActual;
    private List<FiscalAlicuotasIvaDto> alicuotasIVAList;
    private FiscalLibroIvaComprasLineasDto lineaLibro = null;
    private int numerador = 1;


    @PostConstruct
    private void init() {
        String idComprobante = JSFUtil.getRequestParameterMap().get("idComprobante");
        if (isEmpty(idComprobante)) {
            nuevoComprobante();
        } else {
            comprobanteActual = service.find(Long.parseLong(idComprobante));
            if (comprobanteActual == null) {
                nuevoComprobante();
                JSFUtil.addErrorMessage("Comprobante inexistente");
                LOG.log(Level.INFO, "Comprobante inexistente");
            }
        }

    }

    private void nuevoComprobante() {
        comprobanteActual = new ProveedoresComprobantesDto();
        comprobanteActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        comprobanteActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
        comprobanteActual.setIdRegistro(new FiscalLibroIvaComprasDto());
        comprobanteActual.setAnulada(false);
        if (CollectionUtils.isNotEmpty(getPeriodosFiscalesList())) {
            comprobanteActual.getIdRegistro().setIdPeriodoFiscal(getPeriodosFiscalesList().get(0));
        }
        comprobanteActual.getIdRegistro().setFiscalLibroIvaComprasLineasList(new ArrayList<>());
        comprobanteActual.setTotal(BigDecimal.ZERO);
        comprobanteActual.getIdRegistro().setImportePercepcionIngresosBrutos(BigDecimal.ZERO);
        comprobanteActual.getIdRegistro().setImportePercepcionIva(BigDecimal.ZERO);
    }

    public ProveedoresComprobantesDto getComprobanteActual() {
        return comprobanteActual;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public List<FiscalPeriodosFiscalesDto> getPeriodosFiscalesList() {
        if (periodosFiscalesList == null) {
            FiscalPeriodosFiscalesSearchFilter sf = FiscalPeriodosFiscalesSearchFilter.builder().cerrado(false).build();
            sf.addSortField("fechaInicioPeriodo", false);
            periodosFiscalesList = periodosFiscalesService.findAllBySearchFilter(sf);
        }
        return periodosFiscalesList;
    }

    private String armarNumeroFactura() {
        FiscalLibroIvaComprasDto idRegistro = comprobanteActual.getIdRegistro();
        return comprobanteActual.getLetra()
                + StringUtils.leftPad(idRegistro.getPuntoVentaFactura(), 4, "0")
                + StringUtils.leftPad(idRegistro.getNumeroFactura(), 8, "0");
    }

    public String guardar() {

        establecerLetraComprobante();

        ComprobantesProveedorSearchFilter sf = ComprobantesProveedorSearchFilter.builder()
                .idProveedor(comprobanteActual.getIdProveedor().getId())
                .numeroFactura(armarNumeroFactura())
                .idTiposComprobanteList(Collections.singletonList(comprobanteActual.getTipoComprobante().getId()))
                .build();

        ProveedoresComprobantesDto comp = service.findFirstBySearchFilter(sf);
        if (comp != null) {
            JSFUtil.addErrorMessage("El comprobante ya ha sido cargado, su ID es: " + comp.getId());
            return StringUtils.EMPTY;
        }

        try {


            service.guardarYFiscalizar(comprobanteActual);

        } catch (ServiceException ex) {
            JSFUtil.addErrorMessage(ex.getMessage());
            return StringUtils.EMPTY;
        }
        return "/protected/proveedores/comprobantes/index?faces-redirect=true";
    }


    private void establecerLetraComprobante() {
        if (comprobanteActual.getLetra() == null) {
            String letra = letrasComprobantesService.obtenerLetra(
                    comprobanteActual.getIdProveedor().getIdResponsabilidadIva().getId(),
                    2L);
            comprobanteActual.setLetra(letra);
        }
    }

    public List<NegocioTiposComprobanteDto> getTiposComprobanteList() {
        if (tiposComprobanteList == null) {
            tiposComprobanteList = tiposComprobanteService.findAllBySearchFilter(tipoCompSf);
        }
        return tiposComprobanteList;
    }

    public List<FiscalAlicuotasIvaDto> getAlicuotasIVA() {
        if (alicuotasIVAList == null) {
            alicuotasIVAList = alicuotasIvaService.findAll();
        }
        return alicuotasIVAList;
    }

    public void nuevaAlicuota() {
        lineaLibro = new FiscalLibroIvaComprasLineasDto();
        lineaLibro.setItem(numerador++);
        lineaLibro.setIdRegistro(comprobanteActual.getIdRegistro());
    }

    public FiscalLibroIvaComprasLineasDto getLineaLibro() {
        return lineaLibro;
    }

    public void actualizarTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (FiscalLibroIvaComprasLineasDto lin : comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList()) {
            total = total.add(
                    lin.getImporteIva()
                            .add(lin.getNetoGravado())
                            .add(lin.getNoGravado()));
        }
        total = total.add(comprobanteActual.getIdRegistro().getImportePercepcionIngresosBrutos());
        total = total.add(comprobanteActual.getIdRegistro().getImportePercepcionIva());
        comprobanteActual.setTotal(total);
    }

    public void agregarAlicuota() {
        establecerLetraComprobante();
        if (lineaLibro != null) {
            boolean existeAlicuota = false;
            for (FiscalLibroIvaComprasLineasDto linea : comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList()) {
                if (linea.getIdAlicuotaIva().equals(lineaLibro.getIdAlicuotaIva())) {
                    existeAlicuota = true;
                    break;
                }
            }
            if (existeAlicuota) {
                JSFUtil.addErrorMessage("Ya se ha cargado esa alicuota.");
                nuevaAlicuota();
                return;
            }

            lineaLibro.setImporteIva(lineaLibro.getImporteIva().setScale(2, RoundingMode.HALF_UP));

            if (lineaLibro.getIdAlicuotaIva().isGravarIva()) {
                BigDecimal coefAlicuota = lineaLibro.getIdAlicuotaIva().getValorAlicuota().divide(new BigDecimal(100));
                lineaLibro.setNetoGravado(lineaLibro.getImporteIva()
                        .divide(coefAlicuota, 2, RoundingMode.HALF_UP));
                lineaLibro.setNoGravado(BigDecimal.ZERO);
            } else {
                lineaLibro.setNoGravado(lineaLibro.getImporteIva());
                lineaLibro.setImporteIva(BigDecimal.ZERO);
                lineaLibro.setNetoGravado(BigDecimal.ZERO);
            }

            comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList().add(lineaLibro);
            actualizarTotal();
            nuevaAlicuota();
        }
    }

    public void eliminarAlicuota(int item) {
        int index = -1;
        int cont = 0;
        for (FiscalLibroIvaComprasLineasDto linea : comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList()) {
            if (linea.getItem() == item) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList().remove(index);
        }
        actualizarTotal();

    }

}
