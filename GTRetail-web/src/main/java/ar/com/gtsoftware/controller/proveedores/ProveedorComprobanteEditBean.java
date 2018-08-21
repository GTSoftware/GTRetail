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
import ar.com.gtsoftware.eao.*;
import ar.com.gtsoftware.model.*;
import ar.com.gtsoftware.search.FiscalLetrasComprobantesSearchFilter;
import ar.com.gtsoftware.search.FiscalPeriodosFiscalesSearchFilter;
import ar.com.gtsoftware.search.FiscalTiposComprobanteSearchFilter;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "proveedorComprobanteEditBean")
@ViewScoped
public class ProveedorComprobanteEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private ComprobantesProveedorFacade facade;
    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;
    @EJB
    private FiscalResponsabilidadesIvaFacade responsabilidadesIvaFacade;
    @EJB
    private FiscalLetrasComprobantesFacade letrasComprobantesFacade;
    @EJB
    private NegocioTiposComprobanteFacade tiposComprobanteFacade;
    @EJB
    private FiscalAlicuotasIvaFacade alicuotasIvaFacade;
    @EJB
    private FiscalLibroIvaComprasFacade ivaComprasFacade;
    @EJB
    private FiscalTiposComprobanteFacade fiscalTiposComprobanteFacade;

    private final NegocioTiposComprobanteSearchFilter tipoCompSf = NegocioTiposComprobanteSearchFilter.builder()
            .activo(true).build();

    private List<FiscalPeriodosFiscales> periodosFiscalesList = null;

    private List<NegocioTiposComprobante> tiposComprobanteList = null;

    private ProveedoresComprobantes comprobanteActual;
    private static final Logger LOG = Logger.getLogger(ProveedorComprobanteEditBean.class.getName());
    private List<FiscalAlicuotasIva> alicuotasIVAList;
    private FiscalLibroIvaComprasLineas lineaLibro = null;
    private int numerador = 1;


    @PostConstruct
    private void init() {
        String idComprobante = JSFUtil.getRequestParameterMap().get("idComprobante");
        if (StringUtils.isEmpty(idComprobante)) {
            nuevoComprobante();
        } else {
            comprobanteActual = facade.find(Long.parseLong(idComprobante));
            if (comprobanteActual == null) {
                nuevoComprobante();
                JSFUtil.addErrorMessage("Comprobante inexistente");
                LOG.log(Level.INFO, "Comprobante inexistente");
            }
        }

    }

    private void nuevoComprobante() {
        comprobanteActual = new ProveedoresComprobantes();
        comprobanteActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        comprobanteActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
        comprobanteActual.setIdRegistro(new FiscalLibroIvaCompras());
        comprobanteActual.setAnulada(false);
        if (CollectionUtils.isNotEmpty(getPeriodosFiscalesList())) {
            comprobanteActual.getIdRegistro().setIdPeriodoFiscal(getPeriodosFiscalesList().get(0));
        }
        comprobanteActual.getIdRegistro().setFiscalLibroIvaComprasLineasList(new ArrayList<>());
        comprobanteActual.setTotal(BigDecimal.ZERO);
        comprobanteActual.getIdRegistro().setImportePercepcionIngresosBrutos(BigDecimal.ZERO);
        comprobanteActual.getIdRegistro().setImportePercepcionIva(BigDecimal.ZERO);
    }

    public ProveedoresComprobantes getComprobanteActual() {
        return comprobanteActual;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public List<FiscalPeriodosFiscales> getPeriodosFiscalesList() {
        if (periodosFiscalesList == null) {
            FiscalPeriodosFiscalesSearchFilter sf = FiscalPeriodosFiscalesSearchFilter.builder().cerrado(false).build();
            sf.addSortField("fechaInicioPeriodo", false);
            periodosFiscalesList = periodosFiscalesFacade.findAllBySearchFilter(sf);
        }
        return periodosFiscalesList;
    }

    public String guardar() {
        if (CollectionUtils.isEmpty(comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList())) {
            JSFUtil.addErrorMessage("Falta agregar aglún alicuota al comprobante");
            return StringUtils.EMPTY;
        }
        FiscalLibroIvaCompras registro = comprobanteActual.getIdRegistro();
        registro.setFechaFactura(comprobanteActual.getFechaComprobante());
        registro.setDocumento(comprobanteActual.getIdProveedor().getDocumento());
        registro.setLetraFactura(comprobanteActual.getLetra());
        registro.setTotalFactura(comprobanteActual.getTotal());
        registro.setIdPersona(comprobanteActual.getIdProveedor());
        registro.setIdResponsabilidadIva(comprobanteActual.getIdProveedor().getIdResponsabilidadIva());
        registro.setPuntoVentaFactura(StringUtils.leftPad(registro.getPuntoVentaFactura(), 4, "0"));
        registro.setNumeroFactura(StringUtils.leftPad(registro.getNumeroFactura(), 8, "0"));

        calcularTotalesLibro(registro);

        FiscalTiposComprobanteSearchFilter ftcsf = new FiscalTiposComprobanteSearchFilter(comprobanteActual.getLetra(), comprobanteActual.getTipoComprobante());
        FiscalTiposComprobante tipoCompFiscal = fiscalTiposComprobanteFacade.findFirstBySearchFilter(ftcsf);
        if (tipoCompFiscal == null) {
            JSFUtil.addErrorMessage("Este tipo de comprobante no puede ser fiscalizado");
            return StringUtils.EMPTY;
        }
        registro.setCodigoTipoComprobante(tipoCompFiscal);
        ivaComprasFacade.createOrEdit(registro);
        facade.createOrEdit(comprobanteActual);
        return "../index.xhtml";
    }

    private void calcularTotalesLibro(FiscalLibroIvaCompras registro) {
        //TODO ver el tema de los exentos
        BigDecimal totalIVA = BigDecimal.ZERO;
        BigDecimal totalNG = BigDecimal.ZERO;
        BigDecimal totalNoGravado = BigDecimal.ZERO;
        for (FiscalLibroIvaComprasLineas linea :
                registro.getFiscalLibroIvaComprasLineasList()) {
            totalIVA = totalIVA.add(linea.getImporteIva());
            totalNG = totalNG.add(linea.getNetoGravado());
            totalNoGravado = totalNoGravado.add(linea.getNoGravado());
        }
        registro.setImporteIva(totalIVA);
        registro.setImporteNetoGravado(totalNG);
        registro.setImporteNetoNoGravado(totalNoGravado);

        registro.setImporteTributos(BigDecimal.ZERO);
        registro.setImporteExento(BigDecimal.ZERO);
    }

    private void establecerLetraComprobante() {
        if (comprobanteActual.getLetra() == null) {
            FiscalLetrasComprobantesSearchFilter lsf = FiscalLetrasComprobantesSearchFilter.builder()
                    .ivaEmisor(comprobanteActual.getIdProveedor().getIdResponsabilidadIva())
                    .ivaReceptor(responsabilidadesIvaFacade.find(2L)).build();
            FiscalLetrasComprobantes letra = letrasComprobantesFacade.findFirstBySearchFilter(lsf);
            comprobanteActual.setLetra(letra.getLetraComprobante());
        }
    }

    public List<NegocioTiposComprobante> getTiposComprobanteList() {
        if (tiposComprobanteList == null) {
            tiposComprobanteList = tiposComprobanteFacade.findAllBySearchFilter(tipoCompSf);
        }
        return tiposComprobanteList;
    }

    public List<FiscalAlicuotasIva> getAlicuotasIVA() {
        if (alicuotasIVAList == null) {
            alicuotasIVAList = alicuotasIvaFacade.findAll();
        }
        return alicuotasIVAList;
    }

    public void nuevaAlicuota() {
        lineaLibro = new FiscalLibroIvaComprasLineas();
        lineaLibro.setItem(numerador++);
        lineaLibro.setIdRegistro(comprobanteActual.getIdRegistro());
    }

    public FiscalLibroIvaComprasLineas getLineaLibro() {
        return lineaLibro;
    }

    public void actualizarTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (FiscalLibroIvaComprasLineas lin : comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList()) {
            total = total.add(lin.getImporteIva().add(lin.getNetoGravado()).add(lin.getNoGravado()));
        }
        total = total.add(comprobanteActual.getIdRegistro().getImportePercepcionIngresosBrutos());
        total = total.add(comprobanteActual.getIdRegistro().getImportePercepcionIva());
        comprobanteActual.setTotal(total);
    }

    public void agregarAlicuota() {
        establecerLetraComprobante();
        if (lineaLibro != null) {
            boolean existeAlicuota = false;
            for (FiscalLibroIvaComprasLineas linea : comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList()) {
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

            if (lineaLibro.getIdAlicuotaIva().getGravarIva()) {
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
        for (FiscalLibroIvaComprasLineas linea : comprobanteActual.getIdRegistro().getFiscalLibroIvaComprasLineasList()) {
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
