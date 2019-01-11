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
import ar.com.gtsoftware.bl.NegocioFormasPagoService;
import ar.com.gtsoftware.dto.model.CajasDto;
import ar.com.gtsoftware.dto.model.CajasTransferenciasDto;
import ar.com.gtsoftware.dto.model.NegocioFormasPagoDto;
import ar.com.gtsoftware.search.CajasSearchFilter;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "nuevaTransferenciaBean")
@ViewScoped
public class NuevaTransferenciaBean {

    @EJB
    private CajasTransferenciasService service;

    @EJB
    private CajasService cajasService;

    @EJB
    private NegocioFormasPagoService formasPagoService;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private CajasTransferenciasDto transferenciaActual = null;

    private CajasDto cajaActual = null;

    private List<CajasDto> cajasAbiertas;


    @PostConstruct
    private void init() {
        CajasDto cajaAbierta = cajasService.obtenerCajaActual(authBackingBean.getUserLoggedIn());
        if (cajaAbierta == null) {
            throw new RuntimeException("No se pueden realizar transferencias sin tener una caja abierta");
        }
        cajaActual = cajaAbierta;
        transferenciaActual = CajasTransferenciasDto.builder()
                .idCajaOrigen(cajaAbierta)
                .build();
    }


    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public CajasTransferenciasDto getTransferenciaActual() {
        return transferenciaActual;
    }

    public CajasDto getCajaActual() {
        return cajaActual;
    }

    public List<CajasDto> getCajasAbiertas() {
        if (cajasAbiertas == null) {
            CajasSearchFilter csf = CajasSearchFilter.builder()
                    .abierta(true).build();
            cajasAbiertas = cajasService.findAllBySearchFilter(csf);

            cajasAbiertas.remove(cajaActual);
        }
        return cajasAbiertas;
    }

    public String doGuardar() {

        transferenciaActual.setFechaTransferencia(new Date());
        service.generarTransferencia(transferenciaActual);

        return "index.xhtml?faces-redirect=true";
    }

    public List<NegocioFormasPagoDto> getFormasPagoList() {
        FormasPagoSearchFilter fsf = new FormasPagoSearchFilter();
        fsf.addSortField("nombreFormaPago", true);
        return formasPagoService.findAllBySearchFilter(fsf);
    }
}
