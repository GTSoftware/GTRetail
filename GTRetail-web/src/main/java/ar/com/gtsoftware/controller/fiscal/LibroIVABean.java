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

package ar.com.gtsoftware.controller.fiscal;

import ar.com.gtsoftware.bl.FiscalAlicuotasIvaService;
import ar.com.gtsoftware.bl.FiscalPeriodosFiscalesService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.ImportesAlicuotasIVA;
import ar.com.gtsoftware.dto.LibroIVADTO;
import ar.com.gtsoftware.dto.RegistroIVADTO;
import ar.com.gtsoftware.dto.model.FiscalAlicuotasIvaDto;
import ar.com.gtsoftware.dto.model.FiscalPeriodosFiscalesDto;
import ar.com.gtsoftware.search.LibroIVASearchFilter;
import ar.com.gtsoftware.service.fiscal.LibroIVAService;
import ar.com.gtsoftware.utils.JSFUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 1.0.2
 */
@ManagedBean(name = "libroIVABean")
@ViewScoped
public class LibroIVABean implements Serializable {

    private final LibroIVASearchFilter ivaVentasFilter = new LibroIVASearchFilter();
    @EJB(beanName= "libroIVAVentasServiceImpl")
    private LibroIVAService libroIVAVentasBean;
    @EJB
    private FiscalPeriodosFiscalesService periodosFiscalesService;
    @EJB(beanName = "libroIVAComprasServiceImpl")
    private LibroIVAService libroIVAComprasBean;
    @EJB
    private FiscalAlicuotasIvaService alicuotasIvaService;

    public LibroIVABean() {
    }

    public List<FiscalPeriodosFiscalesDto> getPeriodosList() {
        return periodosFiscalesService.findAll();
    }

    /*@Deprecated
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
    }*/

    private void generarLibroIVA(LibroIVAService service, final String titulo, final String filename)
            throws IOException, ServiceException {
        // TODO ver la forma de mejorar esto para achicar el método
        // Agregar totales por categoría de IVA
        // Agregar encabezado con título

        LibroIVADTO libro = service.obtenerLibroIVA(ivaVentasFilter);

        Row row = null;
        Cell cell = null;

        try (XSSFWorkbook wb = new XSSFWorkbook()) {

            XSSFCellStyle styleHeader = wb.createCellStyle();
            XSSFFont fontHeader = wb.createFont();
            fontHeader.setBold(true);
            styleHeader.setFont(fontHeader);

            XSSFCellStyle dateStyle = wb.createCellStyle();
            XSSFCellStyle moneyStyle = wb.createCellStyle();
            CreationHelper createHelper = wb.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            moneyStyle.setDataFormat(createHelper.createDataFormat().getFormat("$ #.##"));

            Sheet sheet = wb.createSheet(titulo);
            int nroFila = 0;

            row = sheet.createRow(nroFila++);
            cell = row.createCell(0);
            cell.setCellValue(titulo);
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
            columnNames.add("Perc. IVA");
            columnNames.add("Perc. Ing.Br.");
            HashMap<FiscalAlicuotasIvaDto, Integer> columnaAlicuota = new HashMap<>();
            //Nombres de columna para encabezados por cada alicuota
            for (FiscalAlicuotasIvaDto al : alicuotasIvaService.findAll()) {
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

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getPercepcionIva().doubleValue());
                cell.setCellStyle(moneyStyle);

                cell = row.createCell(colNum++);
                cell.setCellValue(fact.getPercepcionIngresosBrutos().doubleValue());
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
            cell.setCellValue("Alicuota");
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
            externalContext.setResponseHeader("Content-Disposition",
                    String.format("attachment; filename=%s-%s.xlsx", filename, dateFormat.format(libro.getFechaGeneracion())));

            wb.write(externalContext.getResponseOutputStream());
            facesContext.responseComplete();
        }

    }

    public void exportIVAVentaExcel() {
        try {
            generarLibroIVA(libroIVAVentasBean, "Libro IVA Ventas", "IVA_Ventas");
        } catch (IOException | ServiceException e) {
            JSFUtil.addErrorMessage("Error al generar el libro: " + e.getMessage());
        }
    }

    public void exportIVAComprasExcel() {
        try {
            generarLibroIVA(libroIVAComprasBean, "Libro IVA Compras", "IVA_Compras");
        } catch (IOException | ServiceException e) {
            JSFUtil.addErrorMessage("Error al generar el libro: " + e.getMessage());
        }
    }

    public LibroIVASearchFilter getIvaVentasFilter() {
        return ivaVentasFilter;
    }

}
