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
package ar.com.gtsoftware.controller.caja;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.RecibosFacade;
import ar.com.gtsoftware.model.Recibos;
import ar.com.gtsoftware.search.RecibosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "recibosSearchBean")
@ViewScoped
public class RecibosSearchBean extends AbstractSearchBean<Recibos, RecibosSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private RecibosFacade facade;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private final RecibosSearchFilter filter = new RecibosSearchFilter(DateUtils.truncate(new Date(),
            Calendar.DAY_OF_MONTH),
            DateUtils.addDays(new Date(), 1));

    /**
     * Creates a new instance of ClientesSearchBean
     */
    public RecibosSearchBean() {
    }

    @Override
    protected AbstractFacade<Recibos, RecibosSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaRecibo", false));
        }
    }

    @Override
    public RecibosSearchFilter getFilter() {
        return filter;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

}
