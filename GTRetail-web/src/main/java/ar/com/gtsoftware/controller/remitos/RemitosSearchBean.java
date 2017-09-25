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
package ar.com.gtsoftware.controller.remitos;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.RemitoFacade;
import ar.com.gtsoftware.eao.RemitoTipoMovimientoFacade;
import ar.com.gtsoftware.model.Remito;
import ar.com.gtsoftware.model.RemitoTipoMovimiento;
import ar.com.gtsoftware.search.RemitoSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "remitosSearchBean")
@ViewScoped
public class RemitosSearchBean extends AbstractSearchBean<Remito, RemitoSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private JSFUtil jsfUtil;

    @EJB
    private RemitoFacade facade;
    @EJB
    private RemitoTipoMovimientoFacade tipoMovimientoFacade;

    /**
     * Por defecto creamos un filtro
     */
    private final RemitoSearchFilter filter = new RemitoSearchFilter();
    private final List<RemitoTipoMovimiento> tiposMovimientoList = new ArrayList<>();

    /**
     * Creates a new instance of RemitosSearchBean
     */
    public RemitosSearchBean() {
    }

    @PostConstruct
    public void init() {
        if (jsfUtil.isPostback()) {
            return;
        }

        tiposMovimientoList.addAll(tipoMovimientoFacade.findAll());

    }

    @Override
    protected AbstractFacade<Remito, RemitoSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("fechaAlta", false);
        }
    }

    @Override
    public RemitoSearchFilter getFilter() {
        return filter;
    }

    public List<RemitoTipoMovimiento> getTiposMovimientoList() {
        return tiposMovimientoList;
    }

    public void eliminarProductoBusqueda() {
        filter.setIdProducto(null);
    }
}
