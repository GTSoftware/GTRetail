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

import ar.com.gtsoftware.bl.ComprobantesProveedorService;
import ar.com.gtsoftware.bl.NegocioTiposComprobanteService;
import ar.com.gtsoftware.bl.PersonasService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.NegocioTiposComprobanteDto;
import ar.com.gtsoftware.dto.model.PersonasDto;
import ar.com.gtsoftware.dto.model.ProveedoresComprobantesDto;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.ComprobantesProveedorSearchFilter;
import ar.com.gtsoftware.search.NegocioTiposComprobanteSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.utils.LazyEntityDataModel;
import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "searchComprobantesProveedoresBean")
@ViewScoped
public class SearchComprobantesProveedoresBean extends AbstractSearchBean<ProveedoresComprobantesDto, ComprobantesProveedorSearchFilter> {

    private static final long serialVersionUID = 1L;
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
    private final List<NegocioTiposComprobanteDto> tiposCompList = new ArrayList<>();
    @EJB
    private ComprobantesProveedorService comprobantesProveedorService;
    @EJB
    private PersonasService personasService;
    @EJB
    private NegocioTiposComprobanteService tiposComprobanteService;
    @Inject
    private JSFHelper jsfHelper;

    public SearchComprobantesProveedoresBean() {
    }

    @PostConstruct
    private void init() {
        tiposCompList.addAll(tiposComprobanteService.findAll());
    }

   /* private void calcularTotales() {
        totalVentasFacturadas = comprobantesProveedorService.calcularTotalVentasFacturadasBySearchFilter(filter);
        totalVentas = comprobantesProveedorService.calcularTotalVentasBySearchFilter(filter);
        totalVentasSinFacturar = comprobantesProveedorService.calcularTotalVentasSinFacturarBySearchFilter(filter);
    }*/

    public List<PersonasDto> findProveedoresByString(String query) {
        personasSearchFilter.setTxt(query);
        return personasService.findBySearchFilter(personasSearchFilter, 0, 15);
    }

    public List<NegocioTiposComprobanteDto> autocompleteTiposComp(String query) {
        tiposComprobanteSearchFilter.setNombre(query);
        return tiposComprobanteService.findAllBySearchFilter(tiposComprobanteSearchFilter);
    }

    public List<NegocioTiposComprobanteDto> getTiposCompList() {
        return tiposCompList;
    }

    @Override
    public ComprobantesProveedorSearchFilter getFilter() {
        return filter;
    }


    @Override
    public DataModel<ProveedoresComprobantesDto> getDataModel() {
        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(comprobantesProveedorService, filter);
        }
        return dataModel;
    }

    @Override
    protected ComprobantesProveedorService getService() {
        return comprobantesProveedorService;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("fechaComprobante", true);
        }
    }

    public void eliminarComprobante(ProveedoresComprobantesDto comp) {
        try {
            comprobantesProveedorService.eliminarComprobante(comp);
            jsfHelper.addInfoMessage("Comprobante: " + comp.getId() + " eliminado correctamente");
        } catch (ServiceException e) {
            jsfHelper.addErrorMessage(e.getMessage());
        }
    }

    public PersonasDto getIdProveedor() {
        if (filter.getIdProveedor() == null) {
            return null;
        }
        return personasService.find(filter.getIdProveedor());
    }

    public void setIdProveedor(PersonasDto proveedor) {
        if (proveedor != null) {
            filter.setIdProveedor(proveedor.getId());
        } else {
            filter.setIdProveedor(null);
        }
    }
}
