/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.gtsofware.bl;

import com.gtsoft.eao.FiscalLibroIvaVentasFacade;
import com.gtsoft.eao.FiscalLibroIvaVentasLineasFacade;
import com.gtsoft.eao.VentasFacade;
import com.gtsoft.eao.VentasLineasFacade;
import com.gtsoft.model.FiscalAlicuotasIva;
import com.gtsoft.model.FiscalLibroIvaVentas;
import com.gtsoft.model.FiscalLibroIvaVentasLineas;
import com.gtsoft.model.FiscalPeriodosFiscales;
import com.gtsoft.model.Ventas;
import com.gtsoft.model.VentasLineas;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void registrarFacturaVenta(Ventas venta, String letraComprobante,
            String puntoVentaComprobante,
            String numeroComprobante,
            FiscalPeriodosFiscales periodoFiscal,
            Date fechaFactura) throws Exception {

        FiscalLibroIvaVentas registro = new FiscalLibroIvaVentas();
        //TODO Falta registrar contablemente el asiento de la factura
        registro.setDocumento(venta.getIdCliente().getDocumento());
        registro.setFechaFactura(fechaFactura);
        registro.setIdCliente(venta.getIdCliente());
        registro.setIdResponsabilidadIva(venta.getIdCliente().getIdResponsabilidadIva());
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
            BigDecimal importeIva;
            BigDecimal netoGravado;
            BigDecimal noGravado;
            if (alicuota.getGravarIva()) {
                importeIva = linea.getSubTotal().multiply(alicuota.getValorAlicuota().divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
                netoGravado = linea.getSubTotal().subtract(importeIva).setScale(2, RoundingMode.HALF_UP);
                noGravado = BigDecimal.ZERO;
            } else {
                importeIva = BigDecimal.ZERO;
                noGravado = linea.getSubTotal().multiply(alicuota.getValorAlicuota().divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
                netoGravado = linea.getSubTotal().subtract(noGravado).setScale(2, RoundingMode.HALF_UP);
            }
            registroLinea.setImporteIva(importeIva);
            registroLinea.setNetoGravado(netoGravado);
            registroLinea.setNoGravado(noGravado);
            ivaVentasLineasFacade.create(registroLinea);
        }
        venta.setIdRegistroIva(registro);
        ventasFacade.edit(venta);

    }

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
