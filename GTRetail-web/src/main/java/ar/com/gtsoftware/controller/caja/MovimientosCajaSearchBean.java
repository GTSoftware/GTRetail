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
import ar.com.gtsoftware.bl.CajasMovimientosService;
import ar.com.gtsoftware.bl.CajasService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.CajasDto;
import ar.com.gtsoftware.dto.model.CajasMovimientosDto;
import ar.com.gtsoftware.search.CajasMovimientosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "movimientosCajaSearchBean")
@ViewScoped
public class MovimientosCajaSearchBean extends AbstractSearchBean<CajasMovimientosDto, CajasMovimientosSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final CajasMovimientosSearchFilter filter = CajasMovimientosSearchFilter.builder()
            .fechaDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))
            .fechaHasta(DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH))
            .build();
    @EJB
    private CajasMovimientosService facade;
    @EJB
    private CajasService cajasService;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private CajasDto cajaActual;

    /**
     * Creates a new instance of ClientesSearchBean
     */
    public MovimientosCajaSearchBean() {
    }

    @PostConstruct
    private void init() {
        cajaActual = cajasService.obtenerCajaActual(authBackingBean.getUserLoggedIn());
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

        if (!isPermiteFiltrarCajas()) {
            filter.setIdCaja(cajaActual.getId());
        }
    }

    @Override
    public DataModel<CajasMovimientosDto> getDataModel() {
        if (cajaActual == null && !isPermiteFiltrarCajas()) {
            return new DataModel<CajasMovimientosDto>() {
                @Override
                public boolean isRowAvailable() {
                    return false;
                }

                @Override
                public int getRowCount() {
                    return 0;
                }

                @Override
                public CajasMovimientosDto getRowData() {
                    return null;
                }

                @Override
                public int getRowIndex() {
                    return 0;
                }

                @Override
                public void setRowIndex(int rowIndex) {

                }

                @Override
                public Object getWrappedData() {
                    return null;
                }

                @Override
                public void setWrappedData(Object data) {

                }
            };
        }
        return super.getDataModel();
    }

    @Override
    public CajasMovimientosSearchFilter getFilter() {
        return filter;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public boolean isPermiteFiltrarCajas() {
        return authBackingBean.isUserAdministrador();
    }
}
