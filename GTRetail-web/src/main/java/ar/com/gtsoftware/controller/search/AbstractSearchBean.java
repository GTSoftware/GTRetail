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
package ar.com.gtsoftware.controller.search;

import ar.com.gtsoftware.bl.EntityService;
import ar.com.gtsoftware.dto.IdentifiableDto;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.utils.LazyEntityDataModel;

import javax.faces.model.DataModel;
import java.io.Serializable;

/**
 * Bean abstracto del que heredan los search bean para entidades
 *
 * @param <D> la entidad a filtrar
 * @param <S> el SearchFilter para esa entidad
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public abstract class AbstractSearchBean<D extends IdentifiableDto,
        S extends AbstractSearchFilter> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected DataModel<D> dataModel;

    protected D elemento;

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public AbstractSearchBean() {
    }

    protected abstract EntityService<D, S> getService();

    public abstract S getFilter();

    public void doSearch() {
        dataModel = null;
    }

    protected abstract void prepareSearchFilter();

    public DataModel<D> getDataModel() {
        prepareSearchFilter();

        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(getService(), getFilter());
        }
        return dataModel;
    }

    public D getElemento() {
        return elemento;
    }

    public void setElemento(D elemento) {
        this.elemento = elemento;
    }

    public void deleteElemento() throws Exception {
        getService().remove(elemento);
    }

}
