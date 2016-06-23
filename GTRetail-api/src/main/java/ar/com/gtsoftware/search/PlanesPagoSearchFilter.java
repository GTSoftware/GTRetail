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

import ar.com.gtsoftware.model.NegocioFormasPago;
import org.apache.commons.lang3.StringUtils;

/**
 * SearchFilter para planes de pago
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class PlanesPagoSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private NegocioFormasPago idFormaPago;
    private String nombre;
    private Boolean activo;

    @Override
    public boolean hasFilter() {
        return StringUtils.isNotEmpty(nombre) || activo != null || idFormaPago != null;
    }

    public PlanesPagoSearchFilter() {
    }

    public PlanesPagoSearchFilter(NegocioFormasPago idFormaPago, Boolean activo) {
        this.idFormaPago = idFormaPago;
        this.activo = activo;
    }

    public PlanesPagoSearchFilter(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
