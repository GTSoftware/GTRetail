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

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class UnidadesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String nombreUnidad;

    public UnidadesSearchFilter() {
    }

    public UnidadesSearchFilter(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    @Override
    public boolean hasFilter() {
        return nombreUnidad != null;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

}