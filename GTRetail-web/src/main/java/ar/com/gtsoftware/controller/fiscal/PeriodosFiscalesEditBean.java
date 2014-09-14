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
package ar.com.gtsoftware.controller.fiscal;

import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.utils.UtilUI;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @since 1.0.2
 * @version 1.0.0
 */
@ManagedBean(name = "periodosFiscalesEditBean")
@ViewScoped
public class PeriodosFiscalesEditBean {

    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;

    private FiscalPeriodosFiscales periodoActual;

    /**
     * Creates a new instance of PeriodosFiscalesEditBean
     */
    public PeriodosFiscalesEditBean() {
        nuevoPeriodo();
    }

    public void cerrarPeriodo() {
        periodoActual.setPeriodoCerrado(true);
        guardarPeriodo();
    }

    public final void nuevoPeriodo() {
        periodoActual = new FiscalPeriodosFiscales();
        periodoActual.setFechaInicioPeriodo(UtilUI.getDesde());
        periodoActual.setFechaFinPeriodo(UtilUI.getHasta());
        periodoActual.setPeriodoCerrado(false);
    }

    public void guardarPeriodo() {
        if (periodoActual.isNew()) {
            periodosFiscalesFacade.create(periodoActual);
        } else {
            periodosFiscalesFacade.edit(periodoActual);
        }
        nuevoPeriodo();
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Exito!", "Se ha guardado exitosamente!"));
    }

    public void editarPeriodo(FiscalPeriodosFiscales pf) {
        periodoActual = pf;
    }

    public FiscalPeriodosFiscales getPeriodoActual() {
        return periodoActual;
    }

    public void setPeriodoActual(FiscalPeriodosFiscales periodoActual) {
        this.periodoActual = periodoActual;
    }

    public List<FiscalPeriodosFiscales> getPeriodosList() {
        return periodosFiscalesFacade.findAll();
    }

}
