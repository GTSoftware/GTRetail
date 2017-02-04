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
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.eao.RecibosFacade;
import ar.com.gtsoftware.model.Parametros;
import ar.com.gtsoftware.model.Recibos;
import ar.com.gtsoftware.search.RecibosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.NumberToLetterConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "recibosSearchBean")
@ViewScoped
public class RecibosSearchBean extends AbstractSearchBean<Recibos, RecibosSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private RecibosFacade facade;
    @EJB
    private ParametrosFacade parametrosFacade;

    private boolean soloMiosFilter = true;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private final RecibosSearchFilter filter = new RecibosSearchFilter(DateUtils.truncate(new Date(),
            Calendar.DAY_OF_MONTH),
            DateUtils.addDays(new Date(), 1));

    /**
     * Creates a new instance of ClientesSearchBean
     */
    public RecibosSearchBean() {
    }

    @Override
    protected AbstractFacade<Recibos, RecibosSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaRecibo", false));
        }
        filter.setIdUsuario(null);
        if (soloMiosFilter) {
            filter.setIdUsuario(authBackingBean.getUserLoggedIn());
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

    public void imprimirRecibo(Recibos recibo) {
        List<Recibos> recibos = new ArrayList<>();

        recibos.add(recibo);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(recibos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/recibo.jasper");

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.putAll(cargarParametros());
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
        List<Parametros> paramList = parametrosFacade.findParametros("empresa");

        HashMap<String, Object> result = new HashMap<>();
        for (Parametros p : paramList) {
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
