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
import ar.com.gtsoftware.model.dto.FacturaDTO;
import ar.com.gtsoftware.model.dto.LibroIVADTO;
import ar.com.gtsoftware.search.IVAVentasSearchFilter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.model.DefaultStreamedContent;

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

    public void exportIVAVentaExcel() throws IOException
    {
        try {
            LibroIVADTO libro = libroIVAVentasBean.obtenerLibroIVA(ivaVentasFilter);

            Row row = null;
            Cell cell = null;

            Workbook wb = new HSSFWorkbook();
            HSSFCellStyle styleHeader = (HSSFCellStyle) wb.createCellStyle();
            HSSFFont fontHeader = (HSSFFont) wb.createFont();
            fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            styleHeader.setFont(fontHeader);
            Sheet sheet = wb.createSheet("sheet");
            row = sheet.createRow((short) 0);

            cell = row.createCell(0);
            cell.setCellValue("Fecha de impresion :");
            cell = row.createCell(1);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cell.setCellValue(dateFormat.format(cal.getTime()));
            cell = row.createCell(5);
            cell.setCellValue("Periodo:");
            cell = row.createCell(6);
            dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            cell.setCellValue(dateFormat.format(libro.getFechaDesde())+  "-" +dateFormat.format(libro.getFechaHasta()));

            row = sheet.createRow((short) 1);
            ArrayList<String> columnNames = new ArrayList<>();
            columnNames.add("Fecha");
            columnNames.add("Factura");
            columnNames.add("Documento");
            columnNames.add("Neto");
            columnNames.add("No Grav.");
            columnNames.add("IVA");
            columnNames.add("Total");

            for (int i = 0; i < columnNames.size(); i++)
            {
                cell = row.createCell(i);
                cell.setCellValue(columnNames.get(i));
                cell.setCellStyle(styleHeader);
            }

            int j = 2;

            for (FacturaDTO fact: libro.getFacturasList())
            {
                row = sheet.createRow(j);
                cell = row.createCell(0);
                cell.setCellValue(dateFormat.format(fact.getFechaFactura()));

                cell = row.createCell(1);
                cell.setCellValue(fact.getNumeroFactura());

                cell = row.createCell(2);
                cell.setCellValue(fact.getDocumentoCliente());

                cell = row.createCell(3);
                cell.setCellValue(String.valueOf(fact.getNetoGravado()));

                cell = row.createCell(4);
                cell.setCellValue(String.valueOf(fact.getNoGravado()));

                cell = row.createCell(5);
                cell.setCellValue(String.valueOf(fact.getTotalIva()));

                cell = row.createCell(6);
                cell.setCellValue(String.valueOf(fact.getTotalFactura()));

                j++;
            }

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.ms-excel");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"IVAVenta "+dateFormat.format(libro.getFechaGeneracion())+" .xls\"");

            wb.write(externalContext.getResponseOutputStream());
            facesContext.responseComplete();

//            String excelFileName = ("xls");
//
//            FileOutputStream fos = new FileOutputStream(excelFileName);
//            wb.write(fos);
//            fos.flush();
//            fos.close();
//
//            InputStream stream = new BufferedInputStream(new FileInputStream(excelFileName));
//            exportFile = new DefaultStreamedContent(stream, "application/xls", excelFileName);



        }catch (ServiceException ex){
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
