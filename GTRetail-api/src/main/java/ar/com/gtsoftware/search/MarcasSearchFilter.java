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
public class MarcasSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String nombreMarca;

    public MarcasSearchFilter() {
    }

    public MarcasSearchFilter(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    @Override
    public boolean hasFilter() {
        return nombreMarca != null;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

}
