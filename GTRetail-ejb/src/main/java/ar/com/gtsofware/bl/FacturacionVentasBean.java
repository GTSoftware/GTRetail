/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gtsofware.bl;

import ar.com.gtsoftware.eao.FiscalLibroIvaVentasFacade;
import ar.com.gtsoftware.eao.FiscalLibroIvaVentasLineasFacade;
import ar.com.gtsoftware.eao.VentasFacade;
import ar.com.gtsoftware.eao.VentasLineasFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.FiscalLibroIvaVentasLineas;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.VentasLineas;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class FacturacionVentasBean {

    @EJB
    private VentasFacade ventasFacade;
    @EJB
    private VentasLineasFacade ventasLineasFacade;
    @EJB
    private FiscalLibroIvaVentasFacade ivaVentasFacade;
    @EJB
    private FiscalLibroIvaVentasLineasFacade ivaVentasLineasFacade;

    /**
     * Registra la factura fiscalmente en el libro de IVA ventas
     * para la venta en el período fiscal y con la fecha de factura
     * pasados como parámetro
     * 
     * @param venta
     * @param letraComprobante
     * @param puntoVentaComprobante
     * @param numeroComprobante
     * @param periodoFiscal
     * @param fechaFactura
     * @throws Exception
     */
    public void registrarFacturaVenta(Ventas venta, String letraComprobante,
            String puntoVentaComprobante,
            String numeroComprobante,
            FiscalPeriodosFiscales periodoFiscal,
            Date fechaFactura) throws Exception {

        FiscalLibroIvaVentas registro = new FiscalLibroIvaVentas();
        //TODO Falta registrar contablemente el asiento de la factura
        registro.setDocumento(venta.getIdPersona().getDocumento());
        registro.setFechaFactura(fechaFactura);
        registro.setIdPersona(venta.getIdPersona());
        registro.setIdResponsabilidadIva(venta.getIdPersona().getIdResponsabilidadIva());
        registro.setIdPeriodoFiscal(periodoFiscal);
        registro.setLetraFactura(letraComprobante);
        registro.setNumeroFactura(numeroComprobante);
        registro.setPuntoVentaFactura(puntoVentaComprobante);
        registro.setTotalFactura(venta.getTotal());
        ivaVentasFacade.create(registro);
        List<VentasLineas> lineasVenta = ventasLineasFacade.findVentasLineas(venta);
        for (VentasLineas linea : lineasVenta) {
            FiscalLibroIvaVentasLineas registroLinea = new FiscalLibroIvaVentasLineas();
            registroLinea.setIdFactura(registro);
            FiscalAlicuotasIva alicuota = linea.getIdProducto().getIdAlicuotaIva();
            registroLinea.setIdAlicuotaIva(linea.getIdProducto().getIdAlicuotaIva());
            BigDecimal importeIva = BigDecimal.ZERO;
            BigDecimal netoGravado = BigDecimal.ZERO;
            BigDecimal noGravado = BigDecimal.ZERO;
            if (alicuota.getGravarIva()) {
                importeIva = linea.getSubTotal().multiply(alicuota.getValorAlicuota().divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
                netoGravado = linea.getSubTotal().subtract(importeIva).setScale(2, RoundingMode.HALF_UP);
                noGravado = BigDecimal.ZERO;
            } else {
                importeIva = BigDecimal.ZERO;
                //noGravado = linea.getSubTotal().multiply(alicuota.getValorAlicuota().divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
                //netoGravado = linea.getSubTotal().subtract(noGravado).setScale(2, RoundingMode.HALF_UP);
                noGravado = linea.getSubTotal();//TODO Chequear que esto sea correcto
            }
            registroLinea.setImporteIva(importeIva);
            registroLinea.setNetoGravado(netoGravado);
            registroLinea.setNoGravado(noGravado);
            ivaVentasLineasFacade.create(registroLinea);
        }
        venta.setIdRegistroIva(registro);
        ventasFacade.edit(venta);

    }

    /**
     * Devuelve el próximo número de factura a utilizar para el punto de
     * venta yt letras pasados como parámetro
     * @param letra
     * @param puntoVenta
     * @return número de factura disponible
     */
    public String obtenerProximoNumeroFactura(String letra, String puntoVenta) {
        FiscalLibroIvaVentas ultimaFactura = ivaVentasFacade.findUltimaFactura(letra, puntoVenta);
        if (ultimaFactura != null) {
            int nro = Integer.parseInt(ultimaFactura.getNumeroFactura());
            nro++;
            String proxNum = String.valueOf("00000000" + nro);
            proxNum = proxNum.substring(proxNum.length() - 8);
            return proxNum;
        }
        return "00000001";
    }
}
