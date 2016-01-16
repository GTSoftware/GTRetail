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

import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.model.GTEntity;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.utils.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.model.DataModel;

/**
 * Bean abstracto del que heredan los search bean para entidades
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 * @param <T> la entidad a filtrar
 */
public abstract class AbstractSearchBean<T extends GTEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected DataModel<T> dataModel;

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public AbstractSearchBean() {
    }

    protected abstract AbstractFacade<T> getFacade();

    public abstract AbstractSearchFilter getFilter();

    public void doSearch() {
        dataModel = null;
    }

    protected abstract void prepareSearchFilter();

    public DataModel<T> getDataModel() {
        prepareSearchFilter();

        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(getFacade(), getFilter());
        }
        return dataModel;
    }
}
