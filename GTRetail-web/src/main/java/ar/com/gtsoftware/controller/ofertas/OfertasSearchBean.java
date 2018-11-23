/*
 * Copyright 2018 GT Software.
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

package ar.com.gtsoftware.controller.ofertas;

import ar.com.gtsoftware.bl.OfertasService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.rules.OfertaDto;
import ar.com.gtsoftware.search.OfertasSearchFilter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ofertasSearchBean")
@ViewScoped
public class OfertasSearchBean extends AbstractSearchBean<OfertaDto, OfertasSearchFilter> {

    private final OfertasSearchFilter filter = OfertasSearchFilter.builder().activas(true).build();

    @EJB
    private OfertasService service;

    @Override
    protected OfertasService getService() {
        return service;
    }

    @Override
    public OfertasSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected void prepareSearchFilter() {

    }
}
