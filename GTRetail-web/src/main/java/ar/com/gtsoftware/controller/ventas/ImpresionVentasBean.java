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
package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.bl.FacturacionVentasService;
import ar.com.gtsoftware.bl.ParametrosService;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.ParametrosDto;
import ar.com.gtsoftware.dto.model.RemitoDto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "impresionVentasBean")
@ViewScoped
public class ImpresionVentasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ParametrosService parametrosFacade;
    @EJB
    private FacturacionVentasService facturacionVentasService;

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
    public void imprimirPresupuesto(ComprobantesDto ventaActual) throws IOException, JRException {
        List<ComprobantesDto> ventas = Collections.singletonList(ventaActual);
        String mostrarPreciosValue = parametrosFacade.findParametroByName("presupuesto.impresion.mostrar_detalle_precios").getValorParametro();
        int mostrarPrecios = Integer.parseInt(mostrarPreciosValue);

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ventas);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/presupuesto.jasper");

        HashMap<String, Object> parameters = cargarParametros();
        parameters.put("presupuesto.impresion.mostrar_detalle_precios", mostrarPrecios > 0);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=venta-" + ventaActual.getId() + ".pdf");
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimirFactura(ComprobantesDto ventaActual) throws IOException, JRException {
        List<ComprobantesDto> ventas = Collections.singletonList(ventaActual);


        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ventas);
        JRBeanCollectionDataSource beanCollectionDataSource1 = new JRBeanCollectionDataSource(ventas);
        JRBeanCollectionDataSource beanCollectionDataSource2 = new JRBeanCollectionDataSource(ventas);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/facturaConDuplicado.jasper");
        HashMap<String, Object> parameters = cargarParametros();

        parameters.put("logoAfip", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/afip.png"));


        parameters.put("codigobarras", facturacionVentasService.obtenerCodigoBarrasFE(ventaActual.getId()));

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
        List<ParametrosDto> paramList = parametrosFacade.findParametros("empresa");
        paramList.add(parametrosFacade.findParametroByName("facturacion.impresion.texto.linea1"));
        paramList.add(parametrosFacade.findParametroByName("facturacion.impresion.texto.linea2"));
        HashMap<String, Object> result = new HashMap<>(paramList.size());
        for (ParametrosDto p : paramList) {
            result.put(p.getNombreParametro(), p.getValorParametro());
        }
        return result;
    }

    /**
     * Muestra el remitoDtoCabecera como PDF
     *
     * @param remito
     * @throws IOException
     * @throws JRException
     */
    public void imprimirRemito(RemitoDto remito) throws IOException, JRException {
        List<RemitoDto> remitos = Collections.singletonList(remito);

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remitos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/remito.jasper");

        HashMap<String, Object> parameters = cargarParametros();

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", String.format("attachment; filename=remito-%d.pdf", remito.getId()));
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
