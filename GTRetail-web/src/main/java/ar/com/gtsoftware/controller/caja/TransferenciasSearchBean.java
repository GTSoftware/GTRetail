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

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.CajasService;
import ar.com.gtsoftware.bl.CajasTransferenciasService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.CajasDto;
import ar.com.gtsoftware.dto.model.CajasTransferenciasDto;
import ar.com.gtsoftware.search.CajasTransferenciasSearchFilter;
import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import java.util.Calendar;
import java.util.Date;

@ManagedBean(name = "transferenciasSearchBean")
@ViewScoped
public class TransferenciasSearchBean extends AbstractSearchBean<CajasTransferenciasDto, CajasTransferenciasSearchFilter> {

    private final CajasTransferenciasSearchFilter filter = CajasTransferenciasSearchFilter.builder()
            .fechaDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))
            .fechaHasta(DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH))
            .build();
    @EJB
    private CajasTransferenciasService service;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private CajasDto cajaActual;

    @EJB
    private CajasService cajasService;

    @PostConstruct
    private void init() {
        CajasDto cajaAbierta = cajasService.obtenerCajaActual(authBackingBean.getUserLoggedIn());
        if (cajaAbierta != null) {
            cajaActual = cajaAbierta;
            filter.setIdCaja(cajaActual.getId());
        }
    }

    @Override
    public DataModel<CajasTransferenciasDto> getDataModel() {
        if (cajaActual == null && !authBackingBean.isUserAdministrador()) {
            return new DataModel<CajasTransferenciasDto>() {
                @Override
                public boolean isRowAvailable() {
                    return false;
                }

                @Override
                public int getRowCount() {
                    return 0;
                }

                @Override
                public CajasTransferenciasDto getRowData() {
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
    protected CajasTransferenciasService getService() {
        return service;
    }

    @Override
    public CajasTransferenciasSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("fechaTransferencia", false);
        }
    }

    public boolean isCajaAbierta() {
        return cajaActual != null;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }
}
