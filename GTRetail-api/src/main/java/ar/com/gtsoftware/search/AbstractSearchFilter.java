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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa una agrupación de criterios de filtrado
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @since 1.0.0
 * @version 1.0.0
 */
public abstract class AbstractSearchFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<SortField> sortFields;

    public boolean hasFilter() {
        return false;
    }

    public boolean hasOrderFields() {
        return (sortFields != null && !sortFields.isEmpty());
    }

    public List<SortField> getSortFields() {
        return sortFields;
    }

    /**
     * Agrega un nuevo campo de ordenamiento
     *
     * @param sortField
     */
    public void addSortField(SortField sortField) {
        if (sortFields == null) {
            sortFields = new ArrayList<>();
        }
        sortFields.add(sortField);
    }

    /**
     * Setea en nulo la lista de campos de ordenamiento
     */
    public void clearSortFields() {
        sortFields = null;
    }

}
