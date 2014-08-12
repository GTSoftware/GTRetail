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

import ar.com.gtsoftware.model.UbicacionProvincias;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class LocalidadesSearchFilter extends SearchFilter {

    private Integer idLocalidad;
    private String nombreLocalidad;
    private UbicacionProvincias idProvincia;

    @Override
    public boolean hasFilter() {
        return (idLocalidad != null) || (nombreLocalidad != null && !nombreLocalidad.isEmpty()) || (idProvincia != null);
    }

    public LocalidadesSearchFilter(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public LocalidadesSearchFilter(UbicacionProvincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Integer getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Integer idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public UbicacionProvincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(UbicacionProvincias idProvincia) {
        this.idProvincia = idProvincia;
    }

}
