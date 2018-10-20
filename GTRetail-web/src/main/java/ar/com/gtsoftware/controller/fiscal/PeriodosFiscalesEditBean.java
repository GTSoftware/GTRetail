/*
 * Copyright 2018 GT Software.
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

import ar.com.gtsoftware.bl.FiscalPeriodosFiscalesService;
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.FiscalPeriodosFiscalesDto;
import ar.com.gtsoftware.search.FiscalPeriodosFiscalesSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.*;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 1.0.2
 */
@ManagedBean(name = "periodosFiscalesEditBean")
@ViewScoped
public class PeriodosFiscalesEditBean extends AbstractSearchBean<FiscalPeriodosFiscalesDto, FiscalPeriodosFiscalesSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final FiscalPeriodosFiscalesSearchFilter filter = new FiscalPeriodosFiscalesSearchFilter();
    private final SimpleDateFormat YEAR_MONTH = new SimpleDateFormat("YYYY-MM");
    @EJB
    private FiscalPeriodosFiscalesService periodosFiscalesFacade;
    private FiscalPeriodosFiscalesDto periodoActual;

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
        periodoActual = new FiscalPeriodosFiscalesDto();
        periodoActual.setFechaInicioPeriodo(truncate(new Date(), Calendar.DAY_OF_MONTH));
        periodoActual.setFechaFinPeriodo(addMilliseconds(addMonths(periodoActual.getFechaInicioPeriodo(), 1), -1));
        periodoActual.setPeriodoCerrado(false);
        periodoActual.setNombrePeriodo(YEAR_MONTH.format(periodoActual.getFechaInicioPeriodo()));
    }

    public void guardarPeriodo() {

        periodoActual = periodosFiscalesFacade.createOrEdit(periodoActual);


        nuevoPeriodo();
        JSFUtil.addInfoMessage("Se ha guardado exitosamente!");

    }

    public void editarPeriodo(FiscalPeriodosFiscalesDto pf) {
        periodoActual = pf;
    }

    public FiscalPeriodosFiscalesDto getPeriodoActual() {
        return periodoActual;
    }

    public void setPeriodoActual(FiscalPeriodosFiscalesDto periodoActual) {
        this.periodoActual = periodoActual;
    }

    @Override
    protected FiscalPeriodosFiscalesService getService() {
        return periodosFiscalesFacade;
    }

    @Override
    public FiscalPeriodosFiscalesSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("nombrePeriodo", true);
        }
    }
}
