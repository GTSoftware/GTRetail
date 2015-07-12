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
import ar.com.gtsoftware.bl.fiscal.RegInfoCvVentasAlicuotas;
import ar.com.gtsoftware.bl.fiscal.RegimenInformativoDTO;
import ar.com.gtsoftware.bl.fiscal.RegimenInformativoVentasService;
import ar.com.gtsoftware.bl.fiscal.ReginfoCvCabecera;
import ar.com.gtsoftware.bl.fiscal.ReginfoCvVentasCbte;
import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @since 1.0.2
 * @version 1.0.0
 */
@ManagedBean(name = "regimenInformativoBean")
@ViewScoped
public class RegimenInformativoBean {

    private static final Logger LOG = Logger.getLogger(RegimenInformativoBean.class.getName());

    @EJB
    private RegimenInformativoVentasService regimenInformativoService;

    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;

    private FiscalPeriodosFiscales periodo;

    private static final SimpleDateFormat frm = new SimpleDateFormat("yyyyMM");

    private static final String ENCODING = "ISO-8859-1";

    private static final String EXPORT_FILENAME = "RegimenInformativo-%s.zip";

    public RegimenInformativoBean() {
    }

    public List<FiscalPeriodosFiscales> getPeriodosList() {
        return periodosFiscalesFacade.findAll();
    }

    public void generarRI() throws IOException, JRException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + String.format(EXPORT_FILENAME, frm.format(periodo.getFechaInicioPeriodo())));
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(servletStream)) {
            try {
                RegimenInformativoDTO regimenInformativo = regimenInformativoService.generarRegimenInformativo(periodo);

                ZipEntry cabecera = new ZipEntry(ReginfoCvCabecera.FILE_NAME);
                zos.putNextEntry(cabecera);
                zos.write(regimenInformativo.generarCabecera().getBytes(Charset.forName(ENCODING)));
                zos.flush();
                zos.closeEntry();

                ZipEntry ventas = new ZipEntry(ReginfoCvVentasCbte.FILE_NAME);
                zos.putNextEntry(ventas);
                zos.write(regimenInformativo.generarVentas().getBytes(Charset.forName(ENCODING)));
                zos.flush();
                zos.closeEntry();

                ZipEntry alicuotas = new ZipEntry(RegInfoCvVentasAlicuotas.FILE_NAME);
                zos.putNextEntry(alicuotas);
                zos.write(regimenInformativo.generarVentasAlicuotas().getBytes(Charset.forName(ENCODING)));
                zos.flush();
                zos.closeEntry();

            } catch (ServiceException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }

        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    public FiscalPeriodosFiscales getPeriodo() {
        return periodo;
    }

    public void setPeriodo(FiscalPeriodosFiscales periodo) {
        this.periodo = periodo;
    }

}
