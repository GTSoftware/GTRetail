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
package ar.com.gtsoftware.controller.unidades;

import ar.com.gtsoftware.bl.ProductosTiposUnidadesService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.ProductosTiposUnidadesDto;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.search.UnidadesSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "unidadesSearchBean")
@ViewScoped
public class UnidadesSearchBean extends AbstractSearchBean<ProductosTiposUnidadesDto, UnidadesSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final UnidadesSearchFilter filter = new UnidadesSearchFilter();
    @EJB
    private ProductosTiposUnidadesService facade;
    private ProductosTiposUnidadesDto unidad;

    public UnidadesSearchBean() {
    }

    @Override
    public UnidadesSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected ProductosTiposUnidadesService getService() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("nombreUnidad", true));
        }

    }

    public ProductosTiposUnidadesDto getUnidad() {
        return unidad;
    }

    public void setUnidad(ProductosTiposUnidadesDto unidad) {
        this.unidad = unidad;
    }

    public void deleteUnidad() {
        facade.remove(unidad);
        JSFUtil.addInfoMessage("Unidad eliminada satisfactoriamente");
    }
}
