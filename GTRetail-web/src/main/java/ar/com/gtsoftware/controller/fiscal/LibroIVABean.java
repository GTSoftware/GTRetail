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
import ar.com.gtsoftware.eao.FiscalAlicuotasIvaFacade;
import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.dto.ImportesAlicuotasIVA;
import ar.com.gtsoftware.model.dto.LibroIVADTO;
import ar.com.gtsoftware.model.dto.RegistroIVADTO;
import ar.com.gtsoftware.search.IVAVentasSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

    @EJB
    private FiscalAlicuotasIvaFacade alicuotasFacade;

    private final IVAVentasSearchFilter ivaVentasFilter = new IVAVentasSearchFilter();

    public LibroIVABean() {
    }

    public List<FiscalPeriodosFiscales> getPeriodosList() {
        return periodosFiscalesFacade.findAll();
    }

    @Deprecated
    /**
     * No se utiliza más pues es preferible generarlo en excel
     */
    public void imprimirLibroIvaVentas() throws IOException, JRException {
        try {
            LibroIVADTO libro = libroIVAVentasBean.obtenerLibroIVA(ivaVentasFilter);
            List<LibroIVADTO> libros = new ArrayList<>();
            libros.add(libro);
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(libros);
            String reportPath = FacesContext.getCurrentInstance().getExternalContext()
                    .getRealPath("/resources/reports/LibroIVA_Ventas.jasper");

            HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=IVA-Ventas.pdf");
            ServletOutputStream servletStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (ServiceException ex) {
            Logger.getLogger(LibroIVABean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportIVAVentaExcel() throws IOException, ServiceException {
        // TODO ver la forma de mejorar esto para achicar el método
        // Agregar totales por categoría de IVA
        // Agregar encabezado con título

        LibroIVADTO libro = libroIVAVentasBean.obtenerLibroIVA(ivaVentasFilter);

        Row row = null;
        Cell cell = null;

        try (XSSFWorkbook wb = new XSSFWorkbook()) {

            XSSFCellStyle styleHeader = wb.createCellStyle();
            XSSFFont fontHeader = wb.createFont();
            fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            styleHeader.setFont(fontHeader);

            XSSFCellStyle dateStyle = wb.createCellStyle();
            XSSFCellStyle moneyStyle = wb.createCellStyle();
            CreationHelper createHelper = wb.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            moneyStyle.setDataFormat(createHelper.createDataFormat().getFormat("$ #.##"));

            Sheet sheet = wb.createSheet("Libro IVA Ventas");
            int nroFila = 0;

            row = sheet.createRow(nroFila++);
            cell = row.createCell(0);
            cell.setCellValue("Libro de IVA Ventas");
            cell.setCellStyle(styleHeader);

            row = sheet.createRow(nroFila++);
            cell = row.createCell(0);
            cell.setCellValue("Fecha de impresión:");
            cell = row.createCell(1);

            cell.setCellValue(new Date());
            cell.setCellStyle(dateStyle);

            cell = row.createCell(5);
            cell.setCellValue("Periodo:");
            cell = row.createCell(6);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            cell.setCellValue(String.format("%s al %s", dateFormat.format(libro.getFechaDesde()),
                    dateFormat.format(libro.getFechaHasta())));

            row = sheet.createRow(nroFila++);
            ArrayList<String> columnNames = new ArrayList<>();
            columnNames.add("Fecha");
            columnNames.add("Tipo");
            columnNames.add("Número");
            columnNames.add("Tipo Doc");
            columnNames.add("Documento");
            columnNames.add("Razón Social");
            columnNames.add("Cat. IVA");
            columnNames.add("Neto Gravado");
            columnNames.add("No Grav.");
            columnNames.add("IVA Total");
            HashMap<FiscalAlicuotasIva, Integer> columnaAlicuota = new HashMap<>();
            //Nombres de columna para encabezados por cada alicuota
            for (FiscalAlicuotasIva al : alicuotasFacade.findAll()) {
                columnNames.add(al.getNombreAlicuotaIva());
                columnaAlicuota.put(al, columnNames.size() - 1);
            }
            columnNames.add("Total");

            //Formateo los encabezados
            for (int i = 0; i < columnNames.size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(columnNames.get(i));
                cell.setCellStyle(styleHeader);

            }
            //Armo el detalle de cada comprobante
            for (RegistroIVADTO fact : libro.getFacturasList()) {
                int colNum = 0;
                row = sheet.createRow(nroFila++);

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getFechaFactura());
                cell.setCellStyle(dateStyle);

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getTipoComprobante());

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getNumeroFactura());

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getTipoDocumento());

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getDocumentoCliente());

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getRazonSocialCliente());

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getCategoriaIVACliente());

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getNetoGravado().doubleValue());
                cell.setCellStyle(moneyStyle);

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getNoGravado().doubleValue());
                cell.setCellStyle(moneyStyle);

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getTotalIva().doubleValue());
                cell.setCellStyle(moneyStyle);

                for (ImportesAlicuotasIVA al : fact.getTotalAlicuota()) {

                    cell = row.createCell(columnaAlicuota.get(al.getAlicuota()));
                    cell.setCellValue(al.getImporteIva().doubleValue());
                    cell.setCellStyle(moneyStyle);

                }
                //Columna de total
                cell = row.createCell(columnNames.size() - 1);
                cell.setCellValue(fact.getTotalFactura().doubleValue());
                cell.setCellStyle(moneyStyle);

            }
            for (int i = 0; i < columnNames.size(); i++) {
                sheet.autoSizeColumn(i);
            }
            nroFila = nroFila + 3;
            row = sheet.createRow(nroFila++);
            cell = row.createCell(0);
            cell.setCellValue("Alícuota");
            cell.setCellStyle(styleHeader);
            cell = row.createCell(1);
            cell.setCellValue("Importe");
            cell.setCellStyle(styleHeader);

            //Arma encabezados de totales por alícuota
            for (ImportesAlicuotasIVA al : libro.getTotalesAlicuota()) {
                Row alicRow = sheet.createRow(nroFila++);
                Cell alicCell = alicRow.createCell(0);
                alicCell.setCellValue(al.getAlicuota().getNombreAlicuotaIva());

                alicCell = alicRow.createCell(1);
                alicCell.setCellValue(al.getImporteIva().doubleValue());
                alicCell.setCellStyle(moneyStyle);
            }

            cell = row.createCell(3);
            cell.setCellValue("Facturación total:");
            cell.setCellStyle(styleHeader);
            cell = row.createCell(4);
            cell.setCellValue(libro.getImporteTotal().doubleValue());
            cell.setCellStyle(moneyStyle);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType(JSFUtil.MS_EXCEL_2007_MIME_TYPE);
            externalContext.setResponseHeader("Content-Disposition", String
                    .format("attachment; filename=IVAVentas-%s.xlsx", dateFormat.format(libro.getFechaGeneracion())));

            wb.write(externalContext.getResponseOutputStream());
            facesContext.responseComplete();
        }

    }

    public IVAVentasSearchFilter getIvaVentasFilter() {
        return ivaVentasFilter;
    }

}
