/*
 * Copyright 2019 GT Software.
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
package ar.com.gtsoftware.controller.caja;

import ar.com.gtsoftware.bl.CuponesService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.CuponesDto;
import ar.com.gtsoftware.search.CuponesSearchFilter;
import ar.com.gtsoftware.search.SortField;
import org.apache.commons.lang3.time.DateUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Calendar;
import java.util.Date;

/**
 * Search Bean para cupones de tarjetas de crédito
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "cuponesSearchBean")
@ViewScoped
public class CuponesSearchBean extends AbstractSearchBean<CuponesDto, CuponesSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final CuponesSearchFilter filter = CuponesSearchFilter.builder()
            .fechaOrigenDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))
            .fechaOrigenHasta(DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH))
            .build();
    @EJB
    private CuponesService service;

    /**
     * Creates a new instance of ChequesSearchBean
     */
    public CuponesSearchBean() {
    }

    @Override
    protected CuponesService getService() {
        return service;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaOrigen", false));
        }
    }

    @Override
    public CuponesSearchFilter getFilter() {
        return filter;
    }

}
