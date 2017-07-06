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

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.eao.NegocioPlanesPagoFacade;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.model.NegocioPlanesPago;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.PlanesPagoSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * SearchBean para planes de pago
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "planesPagoSearchBean")
@ViewScoped
public class PlanesPagoSearchBean extends AbstractSearchBean<NegocioPlanesPago, PlanesPagoSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private NegocioPlanesPagoFacade facade;

    @EJB
    private NegocioFormasPagoFacade formasPagoFacade;

    private final List<NegocioFormasPago> formasPagoList = new ArrayList<>();

    private final PlanesPagoSearchFilter filter = new PlanesPagoSearchFilter(null, Boolean.TRUE);

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
        FormasPagoSearchFilter sf = new FormasPagoSearchFilter();
        sf.setRequierePlanes(Boolean.TRUE);
        sf.addSortField(new SortField("nombreFormaPago", true));
        formasPagoList.addAll(formasPagoFacade.findAllBySearchFilter(sf));
    }

    @Override
    public PlanesPagoSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<NegocioPlanesPago, PlanesPagoSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("nombre", true));
        }

    }

    public List<NegocioFormasPago> getFormasPagoList() {
        return formasPagoList;
    }

}
