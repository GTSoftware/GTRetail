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
package ar.com.gtsoftware.controller.formasPago;

import ar.com.gtsoftware.bl.NegocioFormasPagoService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.NegocioFormasPagoDto;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.SortField;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * SearchBean para formas de pago
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "formasPagoSearchBean")
@ViewScoped
public class FormasPagoSearchBean extends AbstractSearchBean<NegocioFormasPagoDto, FormasPagoSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final FormasPagoSearchFilter filter = new FormasPagoSearchFilter();
    @EJB
    private NegocioFormasPagoService formasPagoService;

    /**
     * Creates a new instance of FormasPagoSearchBean
     */
    public FormasPagoSearchBean() {
    }

    @Override
    public FormasPagoSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected NegocioFormasPagoService getService() {
        return formasPagoService;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("nombreFormaPago", true));
        }

    }
}
