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

import ar.com.gtsoftware.bl.ChequesTercerosService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.ChequesTercerosDto;
import ar.com.gtsoftware.search.ChequesTercerosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;

/**
 * Search Bean para cheques de terceros
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "chequesSearchBean")
@ViewScoped
public class ChequesSearchBean extends AbstractSearchBean<ChequesTercerosDto, ChequesTercerosSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final ChequesTercerosSearchFilter filter = new ChequesTercerosSearchFilter();
    @EJB
    private ChequesTercerosService chequesTercerosService;

    /**
     * Creates a new instance of ChequesSearchBean
     */
    public ChequesSearchBean() {
    }

    @Override
    protected ChequesTercerosService getService() {
        return chequesTercerosService;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaOrigen", false));
        }
    }

    @Override
    public ChequesTercerosSearchFilter getFilter() {
        return filter;
    }

    /**
     * Marca el cheque como cobrado a la fecha de hoy.
     *
     * @param cheque
     */
    public void cobrarCheque(ChequesTercerosDto cheque) {
        if (cheque == null) {
            return;
        }
        cheque.setFechaCobro(new Date());
        chequesTercerosService.createOrEdit(cheque);
        JSFUtil.addInfoMessage(String.format("Cheque: %s marcado como cobrado.", cheque.getNroCheque()));
    }
}
