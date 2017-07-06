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

import ar.com.gtsoftware.model.Cajas;
import java.util.Date;

/**
 * SearchFilter para Cupones
 *
 * @author Rodrigo M. Tato Rothamel
 */
public class CuponesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Cajas caja;
    private Date fechaOrigenDesde, fechaOrigenHasta;

    public CuponesSearchFilter() {
    }

    @Override
    public boolean hasFilter() {
        return caja != null
                || hasValidFechasOrigen();
    }

    public CuponesSearchFilter(Cajas caja) {
        this.caja = caja;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

    public Date getFechaOrigenDesde() {
        return fechaOrigenDesde;
    }

    public void setFechaOrigenDesde(Date fechaOrigenDesde) {
        this.fechaOrigenDesde = fechaOrigenDesde;
    }

    public Date getFechaOrigenHasta() {
        return fechaOrigenHasta;
    }

    public void setFechaOrigenHasta(Date fechaOrigenHasta) {
        this.fechaOrigenHasta = fechaOrigenHasta;
    }

    public boolean hasValidFechasOrigen() {
        return fechaOrigenDesde != null && fechaOrigenHasta != null;
    }
}
