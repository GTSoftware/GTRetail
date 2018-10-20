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
package ar.com.gtsoftware.controller.caja;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.ParametrosService;
import ar.com.gtsoftware.bl.RecibosService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.ParametrosDto;
import ar.com.gtsoftware.dto.model.RecibosDto;
import ar.com.gtsoftware.search.RecibosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.NumberToLetterConverter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.time.DateUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "recibosSearchBean")
@ViewScoped
public class RecibosSearchBean extends AbstractSearchBean<RecibosDto, RecibosSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final RecibosSearchFilter filter = RecibosSearchFilter.builder()
            .fechaDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))
            .fechaHasta(DateUtils.addDays(new Date(), 1)).build();
    @EJB
    private RecibosService recibosService;
    @EJB
    private ParametrosService parametrosService;
    private boolean soloMiosFilter = true;
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    /**
     * Creates a new instance of ClientesSearchBean
     */
    public RecibosSearchBean() {
    }

    @Override
    protected RecibosService getService() {
        return recibosService;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaRecibo", false));
        }
        filter.setIdUsuario(null);
        if (soloMiosFilter) {
            filter.setIdUsuario(authBackingBean.getUserLoggedIn().getId());
        }
    }

    @Override
    public RecibosSearchFilter getFilter() {
        return filter;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public void imprimirRecibo(RecibosDto recibo) {

        List<RecibosDto> recibos = Collections.singletonList(recibo);

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(recibos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/recibo.jasper");

        HashMap<String, Object> parameters = new HashMap<>(cargarParametros());
        parameters.put("totalEnLetras", NumberToLetterConverter.convertNumberToLetter(recibo.getMontoTotal().abs().doubleValue()));

        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", String.format("attachment; filename=recibo-%d.pdf", recibo.getId()));
            ServletOutputStream servletStream = httpServletResponse.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        } catch (JRException | IOException ex) {
            Logger.getLogger(RecibosSearchBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Devuelve los parámetros para cargar en los reportes de impresión
     *
     * @return el HashMap con los parámetros
     */
    private HashMap<String, Object> cargarParametros() {
        List<ParametrosDto> paramList = parametrosService.findParametros("empresa");

        HashMap<String, Object> result = new HashMap<>(paramList.size());
        for (ParametrosDto p : paramList) {
            result.put(p.getNombreParametro(), p.getValorParametro());
        }
        return result;
    }

    /**
     * Si es True especifica que solo se deben buscar recibos del usuario logueado
     *
     * @return
     */
    public Boolean getSoloMiosFilter() {
        return soloMiosFilter;
    }

    /**
     * Si es True especifica que solo se deben buscar recibos del usuario logueado
     *
     * @param soloMiosFilter
     */
    public void setSoloMiosFilter(Boolean soloMiosFilter) {
        this.soloMiosFilter = soloMiosFilter;
    }

}
