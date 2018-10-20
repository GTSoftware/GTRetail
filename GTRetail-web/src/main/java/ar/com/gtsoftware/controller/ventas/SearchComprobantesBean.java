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

import ar.com.gtsoftware.bl.ComprobantesService;
import ar.com.gtsoftware.bl.NegocioTiposComprobanteService;
import ar.com.gtsoftware.bl.PersonasService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.NegocioTiposComprobanteDto;
import ar.com.gtsoftware.dto.model.PersonasDto;
import ar.com.gtsoftware.search.ComprobantesSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.utils.LazyEntityDataModel;
import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "searchComprobantesBean")
@ViewScoped
public class SearchComprobantesBean extends AbstractSearchBean<ComprobantesDto, ComprobantesSearchFilter> {

    private static final long serialVersionUID = 1L;

    private final ComprobantesSearchFilter filter = ComprobantesSearchFilter.builder()
            .fechaVentaDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))
            .fechaVentaHasta(DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH))
            .anulada(false).build();

    private final PersonasSearchFilter personasSearchFilter = PersonasSearchFilter.builder()
            .activo(true)
            .cliente(true).build();

    private final List<NegocioTiposComprobanteDto> tiposCompList = new ArrayList<>();
    @EJB
    private ComprobantesService ventasFacade;
    @EJB
    private PersonasService clientesFacade;
    @EJB
    private NegocioTiposComprobanteService tiposComprobanteFacade;
    private BigDecimal totalVentasFacturadas = BigDecimal.ZERO;
    private BigDecimal totalVentas = BigDecimal.ZERO;
    private BigDecimal totalVentasSinFacturar = BigDecimal.ZERO;

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
        Boolean facturadaStatus = filter.getFacturada();
        filter.setFacturada(true);
        totalVentasFacturadas = ventasFacade.calcularTotalVentas(filter);
        filter.setFacturada(null);
        totalVentas = ventasFacade.calcularTotalVentas(filter);
        filter.setFacturada(false);
        totalVentasSinFacturar = ventasFacade.calcularTotalVentas(filter);
        filter.setFacturada(facturadaStatus);
    }

    public List<PersonasDto> findClientesByString(String query) {
        personasSearchFilter.setTxt(query);
        return clientesFacade.findBySearchFilter(personasSearchFilter, 0, 15);
    }

    public List<NegocioTiposComprobanteDto> getTiposCompList() {
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
    public DataModel<ComprobantesDto> getDataModel() {
        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(ventasFacade, filter);
            calcularTotales();
        }
        return dataModel;
    }

    @Override
    protected ComprobantesService getService() {
        return ventasFacade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("fechaComprobante", true);
        }
    }

}
