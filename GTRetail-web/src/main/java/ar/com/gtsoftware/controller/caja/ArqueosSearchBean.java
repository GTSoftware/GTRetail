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
import ar.com.gtsoftware.bl.CajasArqueosService;
import ar.com.gtsoftware.bl.SucursalesService;
import ar.com.gtsoftware.bl.UsuariosService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.CajasArqueosDto;
import ar.com.gtsoftware.dto.model.SucursalesDto;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.search.CajasArqueosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.search.SucursalesSearchFilter;
import ar.com.gtsoftware.search.UsuariosSearchFilter;
import org.apache.commons.lang3.time.DateUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Search Bean para arqueos de caja
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "arqueosSearchBean")
@ViewScoped
public class ArqueosSearchBean extends AbstractSearchBean<CajasArqueosDto, CajasArqueosSearchFilter> {

    private static final long serialVersionUID = 1L;

    private final CajasArqueosSearchFilter filter = CajasArqueosSearchFilter.builder()
            .fechaArqueoDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))
            .fechaArqueoHasta(DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH))
            .build();
    @EJB
    private CajasArqueosService service;
    @EJB
    private SucursalesService sucursalesService;
    @EJB
    private UsuariosService usuariosService;

    private List<SucursalesDto> sucursalesList;
    private List<UsuariosDto> usuariosList;


    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    public ArqueosSearchBean() {
    }

    @Override
    protected CajasArqueosService getService() {
        return service;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaArqueo", false));
        }

        if (!isPermiteFiltrarArqueos()) {
            filter.setIdUsuario(authBackingBean.getUserLoggedIn().getId());
        }
    }

    @Override
    public CajasArqueosSearchFilter getFilter() {
        return filter;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public boolean isPermiteFiltrarArqueos() {
        return authBackingBean.isUserAdministrador();
    }

    public List<SucursalesDto> getSucursalesList() {
        if (sucursalesList == null) {
            SucursalesSearchFilter ssf = SucursalesSearchFilter.builder()
                    .activa(true).build();
            ssf.addSortField("nombreSucursal", true);
            sucursalesList = sucursalesService.findAllBySearchFilter(ssf);
        }
        return sucursalesList;
    }

    public List<UsuariosDto> getUsuariosList() {
        if (usuariosList == null) {
            UsuariosSearchFilter usf = new UsuariosSearchFilter();
            usf.addSortField("nombreUsuario", true);
            usuariosList = usuariosService.findAllBySearchFilter(usf);
        }
        return usuariosList;
    }
}
