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

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ComprobantesFacade;
import ar.com.gtsoftware.eao.NegocioTiposComprobanteFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.NegocioTiposComprobante;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.search.ComprobantesSearchFilter;
import ar.com.gtsoftware.search.NegocioTiposComprobanteSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.LazyEntityDataModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "searchComprobantesBean")
@ViewScoped
public class SearchComprobantesBean extends AbstractSearchBean<Comprobantes> {

    private static final long serialVersionUID = 1L;

    @EJB
    private ComprobantesFacade ventasFacade;
    private final ComprobantesSearchFilter filter = new ComprobantesSearchFilter(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH),
            DateUtils.addDays(new Date(), 1), Boolean.FALSE);
    @EJB
    private PersonasFacade clientesFacade;
    @EJB
    private NegocioTiposComprobanteFacade tiposComprobanteFacade;
    private final PersonasSearchFilter personasSearchFilter = new PersonasSearchFilter(Boolean.TRUE, Boolean.TRUE, null);
    private final NegocioTiposComprobanteSearchFilter tiposComprobanteSearchFilter = new NegocioTiposComprobanteSearchFilter(Boolean.TRUE);

    private BigDecimal totalVentasFacturadas = BigDecimal.ZERO;
    private BigDecimal totalVentas = BigDecimal.ZERO;
    private BigDecimal totalVentasSinFacturar = BigDecimal.ZERO;
    private final List<NegocioTiposComprobante> tiposCompList = new ArrayList<>();

    /**
     * Creates a new instance of SearchVentasBean
     */
    public SearchComprobantesBean() {
    }

    @PostConstruct
    private void init() {
        tiposCompList.addAll(tiposComprobanteFacade.findAll());
    }

    private void calcularTotales() {
        totalVentasFacturadas = ventasFacade.calcularTotalVentasFacturadasBySearchFilter(filter);
        totalVentas = ventasFacade.calcularTotalVentasBySearchFilter(filter);
        totalVentasSinFacturar = ventasFacade.calcularTotalVentasSinFacturarBySearchFilter(filter);
    }

    public List<Personas> findClientesByString(String query) {
        personasSearchFilter.setTxt(query);
        return clientesFacade.findBySearchFilter(personasSearchFilter, 0, 15);
    }

    public List<NegocioTiposComprobante> autocompleteTiposComp(String query) {
        tiposComprobanteSearchFilter.setNombre(query);
        return tiposComprobanteFacade.findAllBySearchFilter(tiposComprobanteSearchFilter);
    }

    public List<NegocioTiposComprobante> getTiposCompList() {
        return tiposCompList;
    }

    @Override
    public ComprobantesSearchFilter getFilter() {
        return filter;
    }

    public BigDecimal getTotalVentasFacturadas() {
        return totalVentasFacturadas;
    }

    public void setTotalVentasFacturadas(BigDecimal totalVentasFacturadas) {
        this.totalVentasFacturadas = totalVentasFacturadas;
    }

    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }

    public BigDecimal getTotalVentasSinFacturar() {
        return totalVentasSinFacturar;
    }

    public void setTotalVentasSinFacturar(BigDecimal totalVentasSinFacturar) {
        this.totalVentasSinFacturar = totalVentasSinFacturar;
    }

    @Override
    public DataModel<Comprobantes> getDataModel() {
        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(ventasFacade, filter);
            calcularTotales();
        }
        return dataModel;
    }

    @Override
    protected AbstractFacade<Comprobantes> getFacade() {
        return ventasFacade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaComprobante", true));
        }
    }

}
