/*
 * Copyright 2019 GT Software.
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

package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.bl.ReportesVentaService;
import ar.com.gtsoftware.bl.SucursalesService;
import ar.com.gtsoftware.dto.model.SucursalesDto;
import ar.com.gtsoftware.dto.reportes.VentaPorProducto;
import ar.com.gtsoftware.search.SucursalesSearchFilter;
import ar.com.gtsoftware.search.reportes.ReporteVentasSearchFilter;
import ar.com.gtsoftware.utils.LazyReportsDataModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "reporteVentasBean")
@ViewScoped
public class ReporteVentasBean implements Serializable {

    private final ReporteVentasSearchFilter filter = new ReporteVentasSearchFilter();
    protected DataModel<VentaPorProducto> dataModel;

    private List<SucursalesDto> sucursalesList;

    @EJB
    private ReportesVentaService service;
    @EJB
    private SucursalesService sucursalesService;

    public DataModel<VentaPorProducto> getDataModel() {

        if (dataModel == null) {
            dataModel = new LazyReportsDataModel<>(service, filter);
        }
        return dataModel;
    }

    public void doSearch() {
        dataModel = null;
    }

    public ReporteVentasSearchFilter getFilter() {
        return filter;
    }

    public List<SucursalesDto> getSucursalesList() {
        if (sucursalesList == null) {
            SucursalesSearchFilter ssf = SucursalesSearchFilter.builder()
                    .activa(true).build();
            ssf.addSortField("nombreSucursal", true);
            sucursalesList = sucursalesService.findAllBySearchFilter(ssf);
        }
        return sucursalesList;
    }
}
