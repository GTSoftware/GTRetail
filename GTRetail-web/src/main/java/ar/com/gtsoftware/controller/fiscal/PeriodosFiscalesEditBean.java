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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

    private List<FiscalPeriodosFiscales> periodosList = new ArrayList<>();

    /**
     * Creates a new instance of PeriodosFiscalesEditBean
     */
    public PeriodosFiscalesEditBean() {

    }

    public void cerrarPeriodo() {
        periodoActual.setPeriodoCerrado(true);
        guardarPeriodo();
    }

    public void nuevoPeriodo() {
        periodoActual = new FiscalPeriodosFiscales();
        periodoActual.setFechaInicioPeriodo(UtilUI.getDesde());
        periodoActual.setFechaFinPeriodo(UtilUI.getHasta());
    }

    public void guardarPeriodo() {
        if (periodoActual.isNew()) {
            periodosFiscalesFacade.create(periodoActual);
        } else {
            periodosFiscalesFacade.edit(periodoActual);
        }
        periodoActual = new FiscalPeriodosFiscales();
    }

    public void eliminarPeriodo() {
        //TODO capa de servicio para verificar si el periodo no tiene ventas asociadas
    }

    public FiscalPeriodosFiscales getPeriodoActual() {
        return periodoActual;
    }

    public void setPeriodoActual(FiscalPeriodosFiscales periodoActual) {
        this.periodoActual = periodoActual;
    }

    public List<FiscalPeriodosFiscales> getPeriodosList() {
        return periodosList;
    }

    public void setPeriodosList(List<FiscalPeriodosFiscales> periodosList) {
        this.periodosList = periodosList;
    }

}
