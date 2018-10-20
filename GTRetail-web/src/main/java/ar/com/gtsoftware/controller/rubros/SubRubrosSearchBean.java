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
package ar.com.gtsoftware.controller.rubros;

import ar.com.gtsoftware.bl.ProductosRubrosService;
import ar.com.gtsoftware.bl.ProductosSubRubrosService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.ProductosRubrosDto;
import ar.com.gtsoftware.dto.model.ProductosSubRubrosDto;
import ar.com.gtsoftware.search.SubRubroSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "subRubrosSearchBean")
@ViewScoped
public class SubRubrosSearchBean extends AbstractSearchBean<ProductosSubRubrosDto, SubRubroSearchFilter> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SubRubrosSearchBean.class.getName());
    private final SubRubroSearchFilter filter = new SubRubroSearchFilter();
    @EJB
    private ProductosSubRubrosService service;
    @EJB
    private ProductosRubrosService rubrosService;
    private ProductosRubrosDto rubro;

    /**
     * Creates a new instance of SubRubrosSearchBean
     */
    public SubRubrosSearchBean() {
    }

    @PostConstruct
    public void init() {

        String idRubro = JSFUtil.getRequestParameterMap().get("idRubro");

        if (isEmpty(idRubro)) {
            throw new RuntimeException("No hay parámetro de Rubro!");
        } else {
            rubro = rubrosService.find(Long.parseLong(idRubro));

            if (rubro == null) {
                LOG.log(Level.SEVERE, "Rubro inexistente!");
                throw new IllegalArgumentException("Rubro inexistente!");

            }

        }

    }

    @Override
    public SubRubroSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected ProductosSubRubrosService getService() {
        return service;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("nombreSubRubro", true);
        }
        filter.setIdProductosRubros(rubro.getId());

    }

    public ProductosRubrosDto getRubro() {
        return rubro;
    }

}
