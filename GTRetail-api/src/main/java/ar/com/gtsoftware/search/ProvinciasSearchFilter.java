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

import ar.com.gtsoftware.model.UbicacionPaises;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ProvinciasSearchFilter extends AbstractSearchFilter {

    /**
     *
     */
    private static final long serialVersionUID = 2L;
    private Integer idProvincia;
    private String nombreProvincia;
    private UbicacionPaises idPais;

    @Override
    public boolean hasFilter() {
        return (idProvincia != null) || (StringUtils.isNotEmpty(nombreProvincia)) || (idPais != null);
    }

    public ProvinciasSearchFilter() {
    }

    public ProvinciasSearchFilter(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public ProvinciasSearchFilter(UbicacionPaises idPais) {
        this.idPais = idPais;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public UbicacionPaises getIdPais() {
        return idPais;
    }

    public void setIdPais(UbicacionPaises idPais) {
        this.idPais = idPais;
    }

}
