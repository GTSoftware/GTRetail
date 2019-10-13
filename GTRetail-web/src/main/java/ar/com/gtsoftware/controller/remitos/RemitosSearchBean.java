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

import ar.com.gtsoftware.bl.RemitoService;
import ar.com.gtsoftware.bl.RemitoTipoMovimientoService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.ProductosDto;
import ar.com.gtsoftware.dto.model.RemitoDto;
import ar.com.gtsoftware.dto.model.RemitoTipoMovimientoDto;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.RemitoSearchFilter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "remitosSearchBean")
@ViewScoped
public class RemitosSearchBean extends AbstractSearchBean<RemitoDto, RemitoSearchFilter> {

    private static final long serialVersionUID = 1L;
    /**
     * Por defecto creamos un filtro
     */
    private final RemitoSearchFilter filter = new RemitoSearchFilter();
    private final List<RemitoTipoMovimientoDto> tiposMovimientoList = new ArrayList<>();
    @EJB
    private RemitoService remitoService;
    @EJB
    private RemitoTipoMovimientoService tipoMovimientoService;
    @Inject
    private JSFHelper jsfHelper;
    private ProductosDto productoBusquedaSeleccionado;

    /**
     * Creates a new instance of RemitosSearchBean
     */
    public RemitosSearchBean() {
    }

    @PostConstruct
    public void init() {
        if (jsfHelper.isPostback()) {
            return;
        }

        tiposMovimientoList.addAll(tipoMovimientoService.findAll());

    }

    @Override
    protected RemitoService getService() {
        return remitoService;
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

    public List<RemitoTipoMovimientoDto> getTiposMovimientoList() {
        return tiposMovimientoList;
    }

    public void eliminarProductoBusqueda() {
        filter.setIdProducto(null);
    }

    public ProductosDto getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(ProductosDto productoBusquedaSeleccionado) {
        this.productoBusquedaSeleccionado = productoBusquedaSeleccionado;
        filter.setIdProducto(productoBusquedaSeleccionado.getId());
    }
}
