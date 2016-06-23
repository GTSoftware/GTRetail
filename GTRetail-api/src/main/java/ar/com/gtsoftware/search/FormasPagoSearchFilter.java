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

import org.apache.commons.lang3.StringUtils;

/**
 * SearchFilter para formas de pago
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class FormasPagoSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private Boolean disponibleVenta;
    private Boolean disponibleCompra;
    private Boolean requierePlanes;

    @Override
    public boolean hasFilter() {
        return StringUtils.isNotEmpty(nombre) || disponibleCompra != null || disponibleVenta != null
                || requierePlanes != null;
    }

    public FormasPagoSearchFilter() {
    }

    public FormasPagoSearchFilter(Boolean disponibleVenta, Boolean disponibleCompra) {
        this.disponibleVenta = disponibleVenta;
        this.disponibleCompra = disponibleCompra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getDisponibleVenta() {
        return disponibleVenta;
    }

    public void setDisponibleVenta(Boolean disponibleVenta) {
        this.disponibleVenta = disponibleVenta;
    }

    public Boolean getDisponibleCompra() {
        return disponibleCompra;
    }

    public void setDisponibleCompra(Boolean disponibleCompra) {
        this.disponibleCompra = disponibleCompra;
    }

    public Boolean getRequierePlanes() {
        return requierePlanes;
    }

    public void setRequierePlanes(Boolean requierePlanes) {
        this.requierePlanes = requierePlanes;
    }

}
