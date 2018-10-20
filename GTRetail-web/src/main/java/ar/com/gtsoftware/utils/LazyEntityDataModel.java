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

import ar.com.gtsoftware.bl.EntityService;
import ar.com.gtsoftware.dto.IdentifiableDto;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

/**
 * Implemantación de LazyLoading para PrimeFaces
 *
 * @param <Entity>
 * @param <Filter>
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
public class LazyEntityDataModel<Entity extends IdentifiableDto, Filter extends AbstractSearchFilter>
        extends LazyDataModel<Entity> implements Serializable {

    private static final Logger LOG = Logger.getLogger(LazyEntityDataModel.class.getName());
    private static final long serialVersionUID = 1L;

    private EntityService<Entity, Filter> service;
    private Filter filter;

    private List<Entity> resultsFromEao = null;

    public LazyEntityDataModel(@NotNull EntityService<Entity, Filter> service, Filter filter) {
        super();
        this.service = service;
        this.filter = filter;
    }

    @Override
    public List<Entity> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                             Map<String, Object> filters) {


        if (filter == null) {
            return Collections.emptyList();
        }
        int count;

        count = service.countBySearchFilter(filter);
        resultsFromEao = new ArrayList<>(count);
        //LOG.log(Level.FINE, "Encontradas: {0} entidades.", count);
        setRowCount(count);
        setPageSize(pageSize);
        if (count > 0) {
            if (sortField != null) {
                filter.clearSortFields();
                filter.addSortField(sortField, !sortOrder.equals(SortOrder.DESCENDING));
            }
            resultsFromEao.addAll(service.findBySearchFilter(filter, first, pageSize));
            return resultsFromEao;

        }
        setWrappedData(resultsFromEao);
        return resultsFromEao;
    }

    @Override
    public List<Entity> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        throw new UnsupportedOperationException("LazyLoading not implemented with multiSort and filters");
    }

    @Override
    public Object getRowKey(Entity dto) {
        return dto.getStringId();
    }

    @Override
    public Entity getRowData(String rowKey) {
        if (resultsFromEao == null || rowKey == null) {
            return null;
        }
        Optional<Entity> found = resultsFromEao.stream().filter(x -> Objects.equals(rowKey, x.getStringId())).findFirst();
        return found.orElse(null);
    }

}
