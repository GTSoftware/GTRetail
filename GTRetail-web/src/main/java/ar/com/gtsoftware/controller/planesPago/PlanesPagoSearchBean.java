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
package ar.com.gtsoftware.controller.planesPago;

import ar.com.gtsoftware.bl.NegocioFormasPagoService;
import ar.com.gtsoftware.bl.NegocioPlanesPagoService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.NegocioFormasPagoDto;
import ar.com.gtsoftware.dto.model.NegocioPlanesPagoDto;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.PlanesPagoSearchFilter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchBean para planes de pago
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "planesPagoSearchBean")
@ViewScoped
public class PlanesPagoSearchBean extends AbstractSearchBean<NegocioPlanesPagoDto, PlanesPagoSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private NegocioPlanesPagoService service;

    @EJB
    private NegocioFormasPagoService formasPagoService;

    private final List<NegocioFormasPagoDto> formasPagoList = new ArrayList<>();

    private final PlanesPagoSearchFilter filter = PlanesPagoSearchFilter.builder().activo(true).build();

    /**
     * Creates a new instance of PlanesPagoSearchBean
     */
    public PlanesPagoSearchBean() {
    }

    @PostConstruct
    private void init() {
        loadFormasPagoList();
    }

    private void loadFormasPagoList() {
        formasPagoList.clear();
        FormasPagoSearchFilter sf = FormasPagoSearchFilter.builder()
                .requierePlanes(true).build();
        sf.addSortField("nombreFormaPago", true);
        formasPagoList.addAll(formasPagoService.findAllBySearchFilter(sf));
    }

    @Override
    public PlanesPagoSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected NegocioPlanesPagoService getService() {
        return service;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("nombre", true);
        }

    }

    public List<NegocioFormasPagoDto> getFormasPagoList() {
        return formasPagoList;
    }

}
