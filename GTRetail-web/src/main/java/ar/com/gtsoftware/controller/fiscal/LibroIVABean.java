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
package ar.com.gtsoftware.controller.fiscal;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.bl.impl.LibroIVAVentasBean;
import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.dto.LibroIVADTO;
import ar.com.gtsoftware.search.IVAVentasSearchFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @since 1.0.2
 * @version 1.0.0
 */
@ManagedBean(name = "libroIVABean")
@ViewScoped
public class LibroIVABean {

    @EJB
    private LibroIVAVentasBean libroIVAVentasBean;
    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;
    private IVAVentasSearchFilter ivaVentasFilter = new IVAVentasSearchFilter();

    public LibroIVABean() {
    }

    public List<FiscalPeriodosFiscales> getPeriodosList() {
        return periodosFiscalesFacade.findAll();
    }

    public void imprimirLibroIvaVentas() throws IOException, JRException {
        try {
            LibroIVADTO libro = libroIVAVentasBean.obtenerLibroIVA(ivaVentasFilter);
            List<LibroIVADTO> libros = new ArrayList<>();
            libros.add(libro);
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(libros);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/LibroIVA_Ventas.jasper");
            //InputStream reportPath = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("main/resources/Messages.properties");
            HashMap<String, Object> parameters = new HashMap<>();
            //parameters.putAll(cargarParametros());
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=IVA-Ventas.pdf");
            ServletOutputStream servletStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (ServiceException ex) {
            Logger.getLogger(LibroIVABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public IVAVentasSearchFilter getIvaVentasFilter() {
        return ivaVentasFilter;
    }

    public void setIvaVentasFilter(IVAVentasSearchFilter ivaVentasFilter) {
        this.ivaVentasFilter = ivaVentasFilter;
    }

}
