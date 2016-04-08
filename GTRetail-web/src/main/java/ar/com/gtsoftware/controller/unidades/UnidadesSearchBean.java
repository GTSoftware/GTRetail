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

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ProductosTiposUnidadesFacade;
import ar.com.gtsoftware.model.ProductosTiposUnidades;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.search.UnidadesSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "unidadesSearchBean")
@ViewScoped
public class UnidadesSearchBean extends AbstractSearchBean<ProductosTiposUnidades> {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductosTiposUnidadesFacade facade;

    private ProductosTiposUnidades unidad;

    private final UnidadesSearchFilter filter = new UnidadesSearchFilter("%");

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public UnidadesSearchBean() {
    }

    @Override
    public UnidadesSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<ProductosTiposUnidades> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("nombreUnidad", true));
        }

    }

    public ProductosTiposUnidades getUnidad() {
        return unidad;
    }

    public void setUnidad(ProductosTiposUnidades unidad) {
        this.unidad = unidad;
    }

    public void deleteUnidad() {
        facade.remove(unidad);
        JSFUtil.addInfoMessage("Unidad eliminada satisfactoriamente");
    }
}
