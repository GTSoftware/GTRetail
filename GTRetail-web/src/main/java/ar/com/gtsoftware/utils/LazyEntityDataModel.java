/*
 * Copyright (C) 2013-2015 Nuevo Banco del Chaco S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.utils;

import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.model.GTEntity;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * Implemantación de LazyLoading para PrimeFaces
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @param <Entity>
 * @param <Filter>
 */
public class LazyEntityDataModel<Entity extends GTEntity<?>, Filter extends AbstractSearchFilter>
        extends LazyDataModel<Entity> implements Serializable {

    private static final Logger LOG = Logger.getLogger(LazyEntityDataModel.class.getName());
    private static final long serialVersionUID = 1L;

    private AbstractFacade<Entity, Filter> facade;
    private Filter filter;

    List<Entity> resultsFromEao = null;

    public LazyEntityDataModel(AbstractFacade<Entity, Filter> facade, Filter filter) {
        super();
        if (facade == null) {
            throw new IllegalArgumentException("El Facade no puede ser nulo");
        }
        this.facade = facade;
        this.filter = filter;
    }

    @Override
    public List<Entity> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {

        resultsFromEao = new ArrayList<>();
        if (filter == null) {

            return resultsFromEao;
        }
        int count;

        count = facade.countBySearchFilter(filter);
        LOG.log(Level.FINE, "Encontradas: {0} entidades.", count);
        setRowCount(count);
        setPageSize(pageSize);
        if (count > 0) {
            if (sortField != null) {
                filter.clearSortFields();
                SortField sof = new SortField(sortField, !sortOrder.equals(SortOrder.DESCENDING));
                filter.addSortField(sof);
            }
            resultsFromEao = facade.findBySearchFilter(filter, first, pageSize);
            return resultsFromEao;

        }
        return resultsFromEao;
    }

    @Override
    public List<Entity> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Object getRowKey(Entity dto) {
        return dto.getId();
    }

    @Override
    public Entity getRowData(String rowKey) {
        if (resultsFromEao == null) {
            return null;
        }
        for (Entity dto : resultsFromEao) {
            if (String.valueOf(dto.getId()).equals(rowKey)) {
                return dto;
            }
        }
        return null;
    }

}
