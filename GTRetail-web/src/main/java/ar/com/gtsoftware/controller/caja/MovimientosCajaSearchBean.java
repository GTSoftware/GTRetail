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

import ar.com.gtsoftware.bl.CajasMovimientosService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.CajasMovimientosDto;
import ar.com.gtsoftware.search.CajasMovimientosSearchFilter;
import ar.com.gtsoftware.search.SortField;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "movimientosCajaSearchBean")
@ViewScoped
public class MovimientosCajaSearchBean extends AbstractSearchBean<CajasMovimientosDto, CajasMovimientosSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final CajasMovimientosSearchFilter filter = new CajasMovimientosSearchFilter();
    @EJB
    private CajasMovimientosService facade;

    /**
     * Creates a new instance of ClientesSearchBean
     */
    public MovimientosCajaSearchBean() {
    }

    @Override
    protected CajasMovimientosService getService() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaMovimiento", false));
        }
    }

    @Override
    public CajasMovimientosSearchFilter getFilter() {
        return filter;
    }

}
