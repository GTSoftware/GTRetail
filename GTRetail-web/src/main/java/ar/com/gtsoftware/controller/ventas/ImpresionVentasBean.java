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

import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.Parametros;
import ar.com.gtsoftware.utils.GeneradorCodigoBarraFE;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "impresionVentasBean")
@ViewScoped
public class ImpresionVentasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ParametrosFacade parametrosFacade;

    /**
     * Creates a new instance of ImpresionVentasBean
     */
    public ImpresionVentasBean() {
    }

    /**
     * Muestra el presupuesto como PDF
     *
     * @param ventaActual
     * @throws IOException
     * @throws JRException
     */
    public void imprimirPresupuesto(Comprobantes ventaActual) throws IOException, JRException {
        List<Comprobantes> ventas = new ArrayList<>();
        String copias = parametrosFacade.findParametroByName("presupuesto.impresion.cantidad_copias").getValorParametro();
        int cantCopias = Integer.parseInt(copias);
        ventas.add(ventaActual);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ventas);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/vistaVenta.jasper");

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.putAll(cargarParametros());

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        for (int i = 1; i < cantCopias; i++) {

            jasperPrint.addPage(jasperPrint.getPages().get(0));

        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=venta-" + ventaActual.getId() + ".pdf");
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimirFactura(Comprobantes ventaActual) throws IOException, JRException {
        List<Comprobantes> ventas = new ArrayList<>();
        ventas.add(ventaActual);
//        String copias = parametrosFacade.findParametroByName("facturacion.preimreso.cantidad_copias").getValorParametro();
//        int cantCopias = Integer.parseInt(copias);
//        for (int i = 0; i < cantCopias; i++) {
//            ventas.add(ventaActual);
//        }
        String cuit = parametrosFacade.findParametroByName("empresa.cuit").getValorParametro();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ventas);
        JRBeanCollectionDataSource beanCollectionDataSource1 = new JRBeanCollectionDataSource(ventas);
        JRBeanCollectionDataSource beanCollectionDataSource2 = new JRBeanCollectionDataSource(ventas);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/facturaConDuplicado.jasper");
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.putAll(cargarParametros());
        parameters.put("logoAfip", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/afip.png"));

        if (ventaActual.getIdRegistro().getCae() != null) {
            String codigoBarras = GeneradorCodigoBarraFE.calcularCodigoBarras(ventaActual.getIdRegistro(), cuit);
            parameters.put("codigobarras", codigoBarras);
        }
        if (ventaActual.getIdRegistro().getLetraFactura().equals("A")) {
            parameters.put("subreport", "vistaVentas_lineasNeto.jasper");
        } else {
            parameters.put("subreport", "vistaVentas_lineas.jasper");
        }

        parameters.put("subDataSource", beanCollectionDataSource1);
        parameters.put("subDataSource2", beanCollectionDataSource2);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", String.format("attachment; filename=venta-%d.pdf", ventaActual.getId()));
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Devuelve los parámetros para cargar en los reportes de impresión
     *
     * @return el HashMap con los parámetros
     */
    private HashMap<String, Object> cargarParametros() {
        List<Parametros> paramList = parametrosFacade.findParametros("empresa");
        paramList.add(parametrosFacade.findParametroByName("facturacion.impresion.texto.linea1"));
        paramList.add(parametrosFacade.findParametroByName("facturacion.impresion.texto.linea2"));
        HashMap<String, Object> result = new HashMap<>();
        for (Parametros p : paramList) {
            result.put(p.getNombreParametro(), p.getValorParametro());
        }
        return result;
    }
}
