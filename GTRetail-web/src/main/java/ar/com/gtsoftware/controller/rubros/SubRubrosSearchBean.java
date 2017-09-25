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
package ar.com.gtsoftware.controller.rubros;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.ProductosRubrosFacade;
import ar.com.gtsoftware.eao.ProductosSubRubrosFacade;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.model.ProductosSubRubros;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.search.SubRubroSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "subRubrosSearchBean")
@ViewScoped
public class SubRubrosSearchBean extends AbstractSearchBean<ProductosSubRubros, SubRubroSearchFilter> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SubRubrosSearchBean.class.getName());

    @EJB
    private ProductosSubRubrosFacade facade;
    @EJB
    private ProductosRubrosFacade rubrosFacade;
    @EJB
    private JSFUtil jsfUtil;

    private ProductosRubros rubro;

    private final SubRubroSearchFilter filter = new SubRubroSearchFilter();

    /**
     * Creates a new instance of SubRubrosSearchBean
     */
    public SubRubrosSearchBean() {
    }

    @PostConstruct
    public void init() {

        String idRubro = jsfUtil.getRequestParameterMap().get("idRubro");

        if (idRubro == null) {
            throw new RuntimeException("No hay parámetro de Rubro!");
        } else {
            rubro = rubrosFacade.find(Long.parseLong(idRubro));

            if (rubro == null) {
                LOG.log(Level.SEVERE, "Rubro inexistente!");
                throw new RuntimeException("Rubro inexistente!");

            }

        }

    }

    @Override
    public SubRubroSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<ProductosSubRubros, SubRubroSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("nombreSubRubro", true));
        }
        filter.setProductosRubros(rubro);

    }

    public ProductosRubros getRubro() {
        return rubro;
    }

}
