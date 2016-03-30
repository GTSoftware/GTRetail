/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.eao.FiscalLibroIvaVentasFacade;
import ar.com.gtsoftware.eao.FiscalLibroIvaVentasLineasFacade;
import ar.com.gtsoftware.eao.FiscalTiposComprobanteFacade;
import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.eao.VentasFacade;
import ar.com.gtsoftware.eao.VentasLineasFacade;
import ar.com.gtsoftware.model.AFIPAuthServices;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.FiscalLibroIvaVentasLineas;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.FiscalPuntosVenta;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.VentasLineas;
import ar.com.gtsoftware.model.dto.fiscal.CAEResponse;
import ar.com.gtsoftware.model.dto.fiscal.TotalesAlicuotas;
import ar.com.gtsoftware.model.enums.TiposPuntosVenta;
import ar.com.gtsoftware.service.afip.WSAAService;
import ar.com.gtsoftware.service.afip.WSFEClient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class FacturacionVentasBean {

    @EJB
    private WSAAService wSAAService;

    @EJB
    private VentasFacade ventasFacade;
    @EJB
    private VentasBean ventasBean;
    @EJB
    private VentasLineasFacade ventasLineasFacade;
    @EJB
    private FiscalLibroIvaVentasFacade ivaVentasFacade;
    @EJB
    private FiscalLibroIvaVentasLineasFacade ivaVentasLineasFacade;
    @EJB
    private FiscalTiposComprobanteFacade fiscalTiposComprobanteFacade;
    @EJB
    private ParametrosFacade parametrosFacade;

    /**
     * Registra la factura fiscalmente en el libro de IVA ventas para la venta en el período fiscal y con la fecha de
     * factura pasados como parámetro
     *
     * @param venta
     * @param letraComprobante
     * @param puntoVentaComprobante
     * @param numeroComprobante
     * @param periodoFiscal
     * @param fechaFactura
     * @throws ServiceException
     */
    public void registrarFacturaVenta(Ventas venta, String letraComprobante,
            FiscalPuntosVenta puntoVentaComprobante,
            String numeroComprobante,
            FiscalPeriodosFiscales periodoFiscal,
            Date fechaFactura) throws ServiceException {
        if (periodoFiscal == null) {
            throw new ServiceException("Periodo no puede nulo!");
        }
        if (fechaFactura == null) {
            throw new ServiceException("Fecha de factura no puede ser nula!");
        }
        if (fechaFactura.after(periodoFiscal.getFechaFinPeriodo())
                || fechaFactura.before(periodoFiscal.getFechaInicioPeriodo())) {
            throw new ServiceException("La fecha de factura no puede estar fuera de las fechas que comprenden el período!");
        }
        if (venta == null) {
            throw new ServiceException("Venta nula!");
        }
        if (venta.getIdRegistroIva() != null) {
            throw new ServiceException("Venta ya facturada!");
        }
        FiscalLibroIvaVentas registro = new FiscalLibroIvaVentas();
        //TODO Falta registrar contablemente el asiento de la factura
        registro.setDocumento(venta.getIdPersona().getDocumento());
        registro.setFechaFactura(fechaFactura);
        registro.setIdPersona(venta.getIdPersona());
        registro.setIdResponsabilidadIva(venta.getIdPersona().getIdResponsabilidadIva());
        registro.setIdPeriodoFiscal(periodoFiscal);
        registro.setLetraFactura(letraComprobante);
        registro.setNumeroFactura(formatNumeroFactura(numeroComprobante));
        registro.setPuntoVentaFactura(formatPuntoVenta(puntoVentaComprobante.getNroPuntoVenta().toString()));
        registro.setTotalFactura(venta.getTotal());
        //TODO arreglar esto, por el momento solo soporta facturas A o B
        if (letraComprobante.equalsIgnoreCase("B")) {
            registro.setCodigoTipoComprobante(fiscalTiposComprobanteFacade.find("006"));
        } else {
            registro.setCodigoTipoComprobante(fiscalTiposComprobanteFacade.find("001"));
        }

        List<VentasLineas> lineasVenta = ventasLineasFacade.findVentasLineas(venta);
        calcularIVA(lineasVenta, registro);
//        BigDecimal totalIVA = BigDecimal.ZERO;
//        BigDecimal totalNetoGravado = BigDecimal.ZERO;
//        BigDecimal totalNetoNoGravado = BigDecimal.ZERO;
//        List<FiscalLibroIvaVentasLineas> registroLineas = new ArrayList<>();
//        for (VentasLineas linea : lineasVenta) {
//            FiscalLibroIvaVentasLineas registroLinea = new FiscalLibroIvaVentasLineas();
//            registroLinea.setIdFactura(registro);
//            FiscalAlicuotasIva alicuota = linea.getIdProducto().getIdAlicuotaIva();
//            registroLinea.setIdAlicuotaIva(linea.getIdProducto().getIdAlicuotaIva());
//            BigDecimal importeIva = BigDecimal.ZERO;
//            BigDecimal netoGravado = BigDecimal.ZERO;
//            BigDecimal noGravado = BigDecimal.ZERO;
//
//            if (alicuota.getGravarIva()) {
//                //Importe*(1+alicuota/100)=Neto
//                BigDecimal coeficienteIVA = BigDecimal.ONE.add(alicuota.getValorAlicuota().divide(new BigDecimal(100)));
//                netoGravado = linea.getSubTotal().divide(coeficienteIVA, 2, RoundingMode.HALF_UP);
//                importeIva = linea.getSubTotal().subtract(netoGravado);
//                importeIva = importeIva.setScale(2, RoundingMode.HALF_UP);
//
//                noGravado = BigDecimal.ZERO;
//            } else {
//                importeIva = BigDecimal.ZERO;
//                //noGravado = linea.getSubTotal().multiply(alicuota.getValorAlicuota().divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
//                //netoGravado = linea.getSubTotal().subtract(noGravado).setScale(2, RoundingMode.HALF_UP);
//                noGravado = linea.getSubTotal();//TODO Chequear que esto sea correcto
//            }
//            registroLinea.setImporteIva(importeIva);
//            registroLinea.setNetoGravado(netoGravado);
//            registroLinea.setNoGravado(noGravado);
//            totalIVA = totalIVA.add(importeIva);
//            totalNetoGravado = totalNetoGravado.add(netoGravado);
//            totalNetoNoGravado = totalNetoNoGravado.add(noGravado);
//            registroLineas.add(registroLinea);
////            ivaVentasLineasFacade.create(registroLinea);
//        }
//        registro.setFiscalLibroIvaVentasLineasList(registroLineas);

        if (puntoVentaComprobante.getTipo().equals(TiposPuntosVenta.ELECTRONICO)) {
            generarFacturaElectronica(registro, puntoVentaComprobante);
        }
        ivaVentasFacade.create(registro);
        venta.setIdRegistroIva(registro);
        ventasFacade.edit(venta);

    }

    /**
     * Calcula los importes de IVA por cada alícuota
     */
    private void calcularIVA(List<VentasLineas> lineasVenta, FiscalLibroIvaVentas registro) {
        List<FiscalLibroIvaVentasLineas> lineasIva = new ArrayList<>();

        ArrayList<TotalesAlicuotas> totales = new ArrayList<>();
        for (VentasLineas vl : lineasVenta) {

            FiscalAlicuotasIva alicuota = vl.getIdProducto().getIdAlicuotaIva();
            TotalesAlicuotas subTotal = new TotalesAlicuotas(alicuota);
            //Importe*(1+alicuota/100)=Neto

            if (alicuota.getGravarIva()) {
                //Importe*(1+alicuota/100)=Neto
                BigDecimal coeficienteIVA = BigDecimal.ONE.add(alicuota.getValorAlicuota().divide(new BigDecimal(100)));
                subTotal.setNetoGravado(vl.getSubTotal().divide(coeficienteIVA, 2, RoundingMode.HALF_UP));
                BigDecimal importeIva = vl.getSubTotal().subtract(subTotal.getNetoGravado());
                importeIva = importeIva.setScale(2, RoundingMode.HALF_UP);
                subTotal.setImporteIva(importeIva);

            } else {
                subTotal.setNoGravado(vl.getSubTotal());
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
            registroLinea.setIdFactura(registro);

            registroLinea.setIdAlicuotaIva(total.getAlicuota());
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

        registro.setNumeroFactura(formatNumeroFactura(String.valueOf(ultimoNro + 1)));
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
    public String obtenerProximoNumeroFactura(String letra, String puntoVenta) {
        FiscalLibroIvaVentas ultimaFactura = ivaVentasFacade.findUltimaFactura(letra, puntoVenta);
        int nro = 1;
        if (ultimaFactura != null) {
            nro = Integer.parseInt(ultimaFactura.getNumeroFactura());
            nro++;
//            return formatNumeroFactura(String.valueOf("00000000" + nro));
        }
//        return "00000001";
        return StringUtils.leftPad(String.valueOf(nro), 8, "0");
    }

    /**
     * Devuelve el número de factura con el formato de 8 dígitos
     *
     * @param nroFactura
     * @return
     */
    private String formatNumeroFactura(String nroFactura) {
        return StringUtils.leftPad(nroFactura, 8, "0");
//        String proxNum = String.valueOf("00000000".concat(nroFactura));
//        proxNum = proxNum.substring(proxNum.length() - 8);
//        return proxNum;
    }

    /**
     * Devuelve el número de punto de venta con el formato de 4 dígitos
     *
     * @param puntoVenta
     * @return
     */
    private String formatPuntoVenta(String puntoVenta) {
        return StringUtils.leftPad(puntoVenta, 4, "0");
//        String proxNum = String.valueOf("00000000".concat(puntoVenta));
//        proxNum = proxNum.substring(proxNum.length() - 4);
//        return proxNum;
    }

    /**
     * Anula la factura asociada a la venta pasada como parámetro
     *
     * @param venta
     * @throws ServiceException
     */
    public void anularFactura(Ventas venta) throws ServiceException {
        if (venta == null) {
            throw new ServiceException("Venta nula!");
        }
        if (venta.getIdRegistroIva() == null) {
            throw new ServiceException("Venta no facturada: ".concat(venta.toString()));
        }
        if (venta.getIdRegistroIva().getIdPeriodoFiscal().isPeriodoCerrado()) {
            throw new ServiceException("Venta de un período cerrado: ".concat(venta.toString()));
        }

        FiscalLibroIvaVentas factura = venta.getIdRegistroIva();
        factura.setTotalFactura(BigDecimal.ZERO);
        factura.setAnulada(true);
        for (FiscalLibroIvaVentasLineas lineaF : ivaVentasLineasFacade.getLineasFactura(factura)) {
            lineaF.setImporteIva(BigDecimal.ZERO);
            lineaF.setNetoGravado(BigDecimal.ZERO);
            lineaF.setNoGravado(BigDecimal.ZERO);
            ivaVentasLineasFacade.edit(lineaF);
        }
        ivaVentasFacade.edit(factura);
        ventasBean.anularVenta(venta);
    }

}
