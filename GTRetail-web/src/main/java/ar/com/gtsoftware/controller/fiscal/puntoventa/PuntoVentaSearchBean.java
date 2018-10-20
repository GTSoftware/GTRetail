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
package ar.com.gtsoftware.controller.fiscal.puntoventa;

import ar.com.gtsoftware.bl.FiscalPuntosVentaService;
import ar.com.gtsoftware.bl.SucursalesService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.FiscalPuntosVentaDto;
import ar.com.gtsoftware.dto.model.SucursalesDto;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;
import ar.com.gtsoftware.search.SucursalesSearchFilter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "puntoVentaSearchBean")
@ViewScoped
public class PuntoVentaSearchBean extends AbstractSearchBean<FiscalPuntosVentaDto, FiscalPuntosVentaSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final List<SucursalesDto> sucursalesList = new ArrayList<>();
    private final FiscalPuntosVentaSearchFilter filter = FiscalPuntosVentaSearchFilter.builder().activo(true).build();
    @EJB
    private FiscalPuntosVentaService puntosVentaService;
    @EJB
    private SucursalesService sucursalesFacade;

    @PostConstruct
    private void init() {
        SucursalesSearchFilter sf = SucursalesSearchFilter.builder()
                .activa(true).build();
        sucursalesList.addAll(sucursalesFacade.findAllBySearchFilter(sf));
    }

    @Override
    protected FiscalPuntosVentaService getService() {
        return puntosVentaService;
    }

    @Override
    public FiscalPuntosVentaSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("nroPuntoVenta", true);
        }
    }

    public List<SucursalesDto> getSucursalesList() {
        return sucursalesList;
    }
}
