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
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ProductosListasPreciosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String nombre;

    private Boolean activa;

    @Override
    public boolean hasFilter() {
        return nombre != null || activa != null;
    }

    public ProductosListasPreciosSearchFilter() {
    }

    public ProductosListasPreciosSearchFilter(String nombre) {
        this.nombre = nombre;
    }

    public ProductosListasPreciosSearchFilter(String nombre, Boolean activa) {
        this.nombre = nombre;
        this.activa = activa;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
