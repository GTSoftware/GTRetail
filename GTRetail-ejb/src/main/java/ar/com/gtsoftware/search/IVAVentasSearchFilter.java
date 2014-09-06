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
package ar.com.gtsoftware.search;

import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import java.util.Date;

/**
 * Clase que sirve para encapsular los distintos criterios de búsqueda para la
 * clase FiscalLibroIvaVentas
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @since 1.0.0
 * @version 1.0.0
 */
public class IVAVentasSearchFilter extends SearchFilter {

    private FiscalPeriodosFiscales periodo;
    private Date fechaDesde;
    private Date fechaHasta;

    @Override
    public boolean hasFilter() {
        return (periodo != null) || (fechaDesde != null && fechaHasta != null);
    }

    public FiscalPeriodosFiscales getPeriodo() {
        return periodo;
    }

    public void setPeriodo(FiscalPeriodosFiscales periodo) {
        this.periodo = periodo;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public boolean hasFechasDesdeHasta() {
        return (fechaDesde != null && fechaHasta != null) && (fechaDesde.compareTo(fechaHasta) <= 0);
    }

}
