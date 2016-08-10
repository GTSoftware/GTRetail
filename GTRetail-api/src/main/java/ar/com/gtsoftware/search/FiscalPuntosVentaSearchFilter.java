/*
 * Copyright 2016 GT Software.
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

import ar.com.gtsoftware.model.Sucursales;
import ar.com.gtsoftware.model.enums.TiposPuntosVenta;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class FiscalPuntosVentaSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 2L;

    private Sucursales sucursal;
    private Boolean activo;
    private Integer nroPuntoVenta;
    private TiposPuntosVenta tipoPuntoVenta;

    @Override
    public boolean hasFilter() {
        return sucursal != null || activo != null || nroPuntoVenta != null || tipoPuntoVenta != null;
    }

    public FiscalPuntosVentaSearchFilter(Sucursales sucursal, Boolean activo) {
        this.sucursal = sucursal;
        this.activo = activo;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getNroPuntoVenta() {
        return nroPuntoVenta;
    }

    public void setNroPuntoVenta(Integer nroPuntoVenta) {
        this.nroPuntoVenta = nroPuntoVenta;
    }

    public TiposPuntosVenta getTipoPuntoVenta() {
        return tipoPuntoVenta;
    }

    public void setTipoPuntoVenta(TiposPuntosVenta tipoPuntoVenta) {
        this.tipoPuntoVenta = tipoPuntoVenta;
    }

}
