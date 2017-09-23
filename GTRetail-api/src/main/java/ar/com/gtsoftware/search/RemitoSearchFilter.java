/*
 * Copyright 2017 GT Software.
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

import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.RemitoTipoMovimiento;
import java.util.Date;

/**
 * SearchFilter para remitos
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class RemitoSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Date fechaAltaDesde;
    private Date fechaAltaHasta;
    private RemitoTipoMovimiento tipoMovimiento;
    private Productos idProducto;

    @Override
    public boolean hasFilter() {
        return hasFechaEntreFechasAltaFilter() || tipoMovimiento != null || idProducto != null;
    }

    /**
     * Retorna true si ambos filtros de búsqueda por fecha de alta del remito están establecidos.
     *
     * @return
     */
    public boolean hasFechaEntreFechasAltaFilter() {
        return fechaAltaDesde != null && fechaAltaHasta != null;
    }

    /**
     * La fecha de alta desde la que se desea buscar los remitos.
     *
     * @return
     */
    public Date getFechaAltaDesde() {
        return fechaAltaDesde;
    }

    /**
     * La fecha de alta desde la que se desea buscar los remitos.
     *
     * @param fechaAltaDesde
     */
    public void setFechaAltaDesde(Date fechaAltaDesde) {
        this.fechaAltaDesde = fechaAltaDesde;
    }

    /**
     * La fecha de alta hasta la que se desea buscar los remitos.
     *
     * @return
     */
    public Date getFechaAltaHasta() {
        return fechaAltaHasta;
    }

    /**
     * La fecha de alta hasta la que se desea buscar los remitos.
     *
     * @param fechaAltaHasta
     */
    public void setFechaAltaHasta(Date fechaAltaHasta) {
        this.fechaAltaHasta = fechaAltaHasta;
    }

    /**
     * El tipo de movimiento del remito
     *
     * @return
     */
    public RemitoTipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    /**
     * El tipo de movimiento del remito
     *
     * @param tipoMovimiento
     */
    public void setTipoMovimiento(RemitoTipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * El producto esté dentro de alguno de los detalles del remito
     *
     * @return
     */
    public Productos getIdProducto() {
        return idProducto;
    }

    /**
     * El producto esté dentro de alguno de los detalles del remito
     *
     * @param idProducto
     */
    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

}
