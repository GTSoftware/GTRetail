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

import ar.com.gtsoftware.model.Ventas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
public class ImpresionVentasBean {

    /**
     * Creates a new instance of ImpresionVentasBean
     */
    public ImpresionVentasBean() {
    }
    
    
    /**
     * Muestra la venta como PDF
     *
     * @param ventaActual
     * @throws IOException
     * @throws JRException
     */
    public void imprimirPresupuesto(Ventas ventaActual) throws IOException, JRException {
        List<Ventas> ventas = new ArrayList<>();
        ventas.add(ventaActual);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ventas);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/vistaVenta.jasper");
        //InputStream reportPath = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("main/resources/Messages.properties");
        HashMap<String, Object> parameters = new HashMap<>();
        //TODO Agregar parámetros de empresa y demás
        //BufferedImage image = ImageIO.read(getClass().getResource(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/logo_empresa.png")));
        //parameters.put("logo", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/logo_empresa.png"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=venta-" + ventaActual.getId() + ".pdf");
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

}
