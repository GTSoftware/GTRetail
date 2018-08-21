/*
 * Copyright 2018 GT Software.
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
package ar.com.gtsoftware.controller.proveedores;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ComprobantesProveedorFacade;
import ar.com.gtsoftware.eao.NegocioTiposComprobanteFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.model.NegocioTiposComprobante;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.ProveedoresComprobantes;
import ar.com.gtsoftware.search.ComprobantesProveedorSearchFilter;
import ar.com.gtsoftware.search.NegocioTiposComprobanteSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.LazyEntityDataModel;
import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "searchComprobantesProveedoresBean")
@ViewScoped
public class SearchComprobantesProveedoresBean extends AbstractSearchBean<ProveedoresComprobantes, ComprobantesProveedorSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private ComprobantesProveedorFacade comprobantesFacade;

    @EJB
    private PersonasFacade proveedoresFacade;

    @EJB
    private NegocioTiposComprobanteFacade tiposComprobanteFacade;

    private final ComprobantesProveedorSearchFilter filter = ComprobantesProveedorSearchFilter.builder()
            .fechaComprobanteDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))
            .fechaComprobanteHasta(DateUtils.addDays(new Date(), 1))
            .anulada(false).build();

    private final PersonasSearchFilter personasSearchFilter = PersonasSearchFilter.builder()
            .activo(true).proveedor(true).build();

    private final NegocioTiposComprobanteSearchFilter tiposComprobanteSearchFilter = NegocioTiposComprobanteSearchFilter
            .builder().activo(true).build();

    /*private BigDecimal totalVentasFacturadas = BigDecimal.ZERO;
    private BigDecimal totalVentas = BigDecimal.ZERO;
    private BigDecimal totalVentasSinFacturar = BigDecimal.ZERO;*/
    private final List<NegocioTiposComprobante> tiposCompList = new ArrayList<>();


    public SearchComprobantesProveedoresBean() {
    }

    @PostConstruct
    private void init() {
        tiposCompList.addAll(tiposComprobanteFacade.findAll());
    }

   /* private void calcularTotales() {
        totalVentasFacturadas = comprobantesFacade.calcularTotalVentasFacturadasBySearchFilter(filter);
        totalVentas = comprobantesFacade.calcularTotalVentasBySearchFilter(filter);
        totalVentasSinFacturar = comprobantesFacade.calcularTotalVentasSinFacturarBySearchFilter(filter);
    }*/

    public List<Personas> findProveedoresByString(String query) {
        personasSearchFilter.setTxt(query);
        return proveedoresFacade.findBySearchFilter(personasSearchFilter, 0, 15);
    }

    public List<NegocioTiposComprobante> autocompleteTiposComp(String query) {
        tiposComprobanteSearchFilter.setNombre(query);
        return tiposComprobanteFacade.findAllBySearchFilter(tiposComprobanteSearchFilter);
    }

    public List<NegocioTiposComprobante> getTiposCompList() {
        return tiposCompList;
    }

    @Override
    public ComprobantesProveedorSearchFilter getFilter() {
        return filter;
    }


    @Override
    public DataModel<ProveedoresComprobantes> getDataModel() {
        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(comprobantesFacade, filter);
        }
        return dataModel;
    }

    @Override
    protected AbstractFacade<ProveedoresComprobantes, ComprobantesProveedorSearchFilter> getFacade() {
        return comprobantesFacade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaComprobante", true));
        }
    }

}
