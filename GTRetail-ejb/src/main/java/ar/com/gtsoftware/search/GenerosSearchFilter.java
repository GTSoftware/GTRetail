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

import ar.com.gtsoftware.model.LegalTiposPersoneria;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class GenerosSearchFilter extends SearchFilter {

    private Integer idGenero;
    private String nombreGenero;
    private String simbolo;
    private LegalTiposPersoneria idTipoPersoneria;

    public GenerosSearchFilter(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public GenerosSearchFilter(LegalTiposPersoneria idTipoPersoneria) {
        this.idTipoPersoneria = idTipoPersoneria;
    }

    public GenerosSearchFilter() {
    }

    @Override
    public boolean hasFilter() {
        return (idGenero != null) || (nombreGenero != null && !nombreGenero.isEmpty()) || (simbolo != null && !simbolo.isEmpty())
                || (idTipoPersoneria != null);
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public LegalTiposPersoneria getIdTipoPersoneria() {
        return idTipoPersoneria;
    }

    public void setIdTipoPersoneria(LegalTiposPersoneria idTipoPersoneria) {
        this.idTipoPersoneria = idTipoPersoneria;
    }

}
