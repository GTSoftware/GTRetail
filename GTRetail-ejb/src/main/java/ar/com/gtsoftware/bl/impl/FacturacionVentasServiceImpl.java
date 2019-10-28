/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.ComprobantesService;
import ar.com.gtsoftware.bl.FacturacionVentasService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.fiscal.CAEResponse;
import ar.com.gtsoftware.dto.fiscal.TotalesAlicuotas;
import ar.com.gtsoftware.dto.model.FiscalPuntosVentaDto;
import ar.com.gtsoftware.eao.*;
import ar.com.gtsoftware.enums.TiposPuntosVenta;
import ar.com.gtsoftware.mappers.FiscalAlicuotasIvaMapper;
import ar.com.gtsoftware.mappers.FiscalPuntosVentaMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.*;
import ar.com.gtsoftware.search.FiscalPeriodosFiscalesSearchFilter;
import ar.com.gtsoftware.search.FiscalTiposComprobanteSearchFilter;
import ar.com.gtsoftware.service.afip.WSAAService;
import ar.com.gtsoftware.service.afip.WSFEClient;
import ar.com.gtsoftware.utils.GeneradorCodigoBarraFE;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class FacturacionVentasServiceImpl implements FacturacionVentasService {

    @EJB
    private WSAAService wSAAService;

    @EJB
    private ComprobantesFacade ventasFacade;

    @EJB
    private ComprobantesLineasFacade ventasLineasFacade;
    @EJB
    private FiscalLibroIvaVentasFacade ivaVentasFacade;
    @EJB
    private FiscalLibroIvaVentasLineasFacade ivaVentasLineasFacade;
    @EJB
    private FiscalTiposComprobanteFacade fiscalTiposComprobanteFacade;
    @EJB
    private ParametrosFacade parametrosFacade;
    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;
    @EJB
    private ComprobantesService comprobantesService;


    @Inject
    private FiscalPuntosVentaMapper puntosVentaMapper;

    @Inject
    private FiscalAlicuotasIvaMapper alicuotasIvaMapper;

    /**
     * Registra la factura fiscalmente en el libro de IVA ventas para la venta en el período fiscal y con la fecha de
     * factura pasados como parámetro
     *
     * @throws ServiceException
     */
    @Override
    public void registrarFacturaVenta(@NotNull Long idComprobante,
                                      @NotNull FiscalPuntosVentaDto puntoVentaComprobanteDto,
                                      long numeroComprobante,
                                      Date fechaFactura) throws ServiceException {

        FiscalPeriodosFiscalesSearchFilter psf = FiscalPeriodosFiscalesSearchFilter.builder()
                .cerrado(false)
                .build();

        if (fechaFactura == null) {
            psf.setVigente(true);
            fechaFactura = new Date();
        } else {
            psf.setFechaActual(fechaFactura);
        }

        FiscalPeriodosFiscales periodoFiscal = periodosFiscalesFacade.findFirstBySearchFilter(psf);
        if (periodoFiscal == null) {
            throw new ServiceException("No existe un periodoFiscal fiscal configurado!");
        }
        Comprobantes venta = ventasFacade.find(idComprobante);
        if (venta == null) {
            throw new ServiceException("Venta inexistente!");
        }
        if (venta.getIdRegistro() != null) {
            throw new ServiceException("Venta ya facturada!");
        }
        if (venta.isAnulada()) {
            throw new ServiceException("Venta anulada!");
        }
        FiscalTiposComprobanteSearchFilter ftcsf = FiscalTiposComprobanteSearchFilter.builder()
                .letra(venta.getLetra())
                .idTipoComprobante(venta.getTipoComprobante().getId()).build();

        FiscalTiposComprobante tipoCompFiscal = fiscalTiposComprobanteFacade.findFirstBySearchFilter(ftcsf);
        if (tipoCompFiscal == null) {
            throw new ServiceException("Este comprobante no puede ser fiscalizado!");
        }
        FiscalPuntosVenta fiscalPuntosVenta = puntosVentaMapper.dtoToEntity(puntoVentaComprobanteDto, new CycleAvoidingMappingContext());

        FiscalLibroIvaVentas registro = new FiscalLibroIvaVentas();
        //TODO Falta registrar contablemente el asiento de la factura
        registro.setDocumento(venta.getIdPersona().getDocumento());
        registro.setFechaFactura(fechaFactura);
        registro.setIdPersona(venta.getIdPersona());
        registro.setIdResponsabilidadIva(venta.getIdPersona().getIdResponsabilidadIva());
        registro.setIdPeriodoFiscal(periodoFiscal);
        registro.setLetraFactura(venta.getLetra());
        registro.setNumeroFactura(formatNumeroFactura(numeroComprobante));
        registro.setPuntoVentaFactura(formatPuntoVenta(fiscalPuntosVenta.getNroPuntoVenta()));
        registro.setTotalFactura(venta.getTotal().multiply(venta.getTipoComprobante().getSigno()));

        registro.setCodigoTipoComprobante(tipoCompFiscal);

        List<ComprobantesLineas> lineasVenta = ventasLineasFacade.findVentasLineas(venta);
        calcularIVA(lineasVenta, registro, venta.getTipoComprobante().getSigno());

        if (puntoVentaComprobanteDto.getTipo().equals(TiposPuntosVenta.ELECTRONICO)) {
            generarFacturaElectronica(registro, fiscalPuntosVenta);
        }
        ivaVentasFacade.create(registro);
        venta.setIdRegistro(registro);
        ventasFacade.edit(venta);

    }

    /**
     * Calcula los importes de IVA por cada alícuota
     */
    private void calcularIVA(List<ComprobantesLineas> lineasVenta, FiscalLibroIvaVentas registro, BigDecimal signo) {
        List<FiscalLibroIvaVentasLineas> lineasIva = new ArrayList<>();

        ArrayList<TotalesAlicuotas> totales = new ArrayList<>();
        for (ComprobantesLineas vl : lineasVenta) {

            FiscalAlicuotasIva alicuota = vl.getIdProducto().getIdAlicuotaIva();
            TotalesAlicuotas subTotal = TotalesAlicuotas.builder()
                    .alicuota(alicuotasIvaMapper.entityToDto(alicuota, new CycleAvoidingMappingContext())).build();
            //Importe*(1+alicuota/100)=Neto

            if (alicuota.isGravarIva()) {
                //Importe*(1+alicuota/100)=Neto
                BigDecimal coeficienteIVA = BigDecimal.ONE.add(alicuota.getValorAlicuota().divide(new BigDecimal(100)));
                subTotal.setNetoGravado(vl.getSubTotal().divide(coeficienteIVA, 2, RoundingMode.HALF_UP));
                BigDecimal importeIva = vl.getSubTotal().subtract(subTotal.getNetoGravado());
                importeIva = importeIva.setScale(2, RoundingMode.HALF_UP);
                subTotal.setImporteIva(importeIva.multiply(signo));
                subTotal.setNetoGravado(subTotal.getNetoGravado().multiply(signo));

            } else {
                subTotal.setNoGravado(vl.getSubTotal().multiply(signo));
            }

            int index = totales.indexOf(subTotal);
            if (index > -1) {
                TotalesAlicuotas total = totales.get(index);
                total.add(subTotal);
            } else {
                totales.add(subTotal);
            }

        }
        BigDecimal totalIVA = BigDecimal.ZERO, totalNetoGravado = BigDecimal.ZERO, totalNetoNoGravado = BigDecimal.ZERO;

        for (TotalesAlicuotas total : totales) {
            FiscalLibroIvaVentasLineas registroLinea = new FiscalLibroIvaVentasLineas();
            registroLinea.setIdRegistro(registro);

            registroLinea.setIdAlicuotaIva(alicuotasIvaMapper.dtoToEntity(total.getAlicuota(), new CycleAvoidingMappingContext()));
            registroLinea.setImporteIva(total.getImporteIva());
            registroLinea.setNetoGravado(total.getNetoGravado());
            registroLinea.setNoGravado(total.getNoGravado());
            lineasIva.add(registroLinea);

            totalIVA = totalIVA.add(total.getImporteIva());
            totalNetoGravado = totalNetoGravado.add(total.getNetoGravado());
            totalNetoNoGravado = totalNetoNoGravado.add(total.getNoGravado());
        }
        registro.setFiscalLibroIvaVentasLineasList(lineasIva);

        registro.setImporteExento(BigDecimal.ZERO);//TODO ver esto como es
        registro.setImporteIva(totalIVA);
        registro.setImporteNetoGravado(totalNetoGravado);
        registro.setImporteNetoNoGravado(totalNetoNoGravado);
        registro.setImporteTributos(BigDecimal.ZERO);//TODO Chequear esto en algun momento

    }

    private void generarFacturaElectronica(FiscalLibroIvaVentas registro, FiscalPuntosVenta puntoVentaComprobante)
            throws ServiceException {
        AFIPAuthServices loginTicket = wSAAService.obtenerLoginTicket("wsfe");
        String cuit = parametrosFacade.findParametroByName("empresa.cuit").getValorParametro();
        String endpoint = parametrosFacade.findParametroByName("afip.wsfe.endpoint").getValorParametro();
        int ultimoNro = WSFEClient.obtenerUltimoComprobanteAutorizado(loginTicket, cuit, endpoint,
                puntoVentaComprobante.getNroPuntoVenta(),
                Integer.parseInt(registro.getCodigoTipoComprobante().getCodigoTipoComprobante()));

        registro.setNumeroFactura(formatNumeroFactura(ultimoNro + 1));
        CAEResponse caeDto = WSFEClient.solicitarCAE(loginTicket, cuit, registro, endpoint);
        registro.setCae(caeDto.getCae());
        registro.setFechaVencimientoCae(caeDto.getFechaVencimientoCae());
    }

    /**
     * Devuelve el próximo número de factura a utilizar para el punto de venta y letra pasados como parámetro
     *
     * @param letra
     * @param puntoVenta
     * @return número de factura disponible
     */
    @Override
    public long obtenerProximoNumeroFactura(String letra, int puntoVenta) {
        FiscalLibroIvaVentas ultimaFactura = ivaVentasFacade.findUltimaFactura(letra, formatPuntoVenta(puntoVenta));
        long nro = 1;
        if (ultimaFactura != null) {
            nro = Long.parseLong(ultimaFactura.getNumeroFactura());
            nro++;
        }
        return nro;
    }

    /**
     * Devuelve el número de factura con el formato de 8 dígitos
     *
     * @param nroFactura
     * @return
     */
    private String formatNumeroFactura(long nroFactura) {
        return StringUtils.leftPad(String.valueOf(nroFactura), 8, "0");
    }

    /**
     * Devuelve el número de punto de venta con el formato de 4 dígitos
     *
     * @param puntoVenta
     * @return
     */
    private String formatPuntoVenta(int puntoVenta) {
        return StringUtils.leftPad(String.valueOf(puntoVenta), 4, "0");
    }

    /**
     * Anula la factura asociada a la venta pasada como parámetro
     *
     * @param idComprobante
     * @throws ServiceException
     */
    @Override
    public void anularFactura(@NotNull Long idComprobante) throws ServiceException {
        Comprobantes venta = ventasFacade.find(idComprobante);
        if (venta == null) {
            throw new ServiceException("Venta inexistente!");
        }
        if (venta.getIdRegistro() == null) {
            throw new ServiceException("Venta no facturada: ".concat(venta.toString()));
        }
        if (venta.getIdRegistro().getIdPeriodoFiscal().isPeriodoCerrado()) {
            throw new ServiceException("Venta de un período cerrado: ".concat(venta.toString()));
        }
        if (venta.isAnulada()) {
            throw new ServiceException("Venta ya anulada!");
        }

        FiscalLibroIvaVentas factura = venta.getIdRegistro();
        factura.setTotalFactura(BigDecimal.ZERO);
        factura.setAnulada(true);
        for (FiscalLibroIvaVentasLineas lineaF : ivaVentasLineasFacade.getLineasFactura(factura)) {
            lineaF.setImporteIva(BigDecimal.ZERO);
            lineaF.setNetoGravado(BigDecimal.ZERO);
            lineaF.setNoGravado(BigDecimal.ZERO);
            ivaVentasLineasFacade.edit(lineaF);
        }
        ivaVentasFacade.edit(factura);
        comprobantesService.anularVenta(venta.getId());
    }

    @Override
    public String obtenerCodigoBarrasFE(@NotNull Long idComprobante) {
        Comprobantes comprobante = ventasFacade.find(idComprobante);
        if (comprobante == null) {
            throw new IllegalArgumentException("Comprobante inexistente");
        }
        if (comprobante.getIdRegistro() == null) {
            return StringUtils.EMPTY;
        }
        if (comprobante.getIdRegistro().getCae() != null) {
            String cuit = parametrosFacade.findParametroByName("empresa.cuit").getValorParametro();
            return GeneradorCodigoBarraFE.calcularCodigoBarras(comprobante.getIdRegistro(), cuit);
        }
        return comprobante.getIdRegistro().getNumeroFactura();

    }

}
