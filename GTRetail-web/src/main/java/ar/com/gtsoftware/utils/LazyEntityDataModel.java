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
import ar.com.gtsoftware.model.BaseEntity;
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
 */
public class LazyEntityDataModel<Entity extends BaseEntity>
        extends LazyDataModel<Entity>
        implements Serializable {

    private static final Logger LOG = Logger.getLogger(LazyEntityDataModel.class.getName());

    private AbstractFacade<Entity> facade;
    private AbstractSearchFilter filter;
    private int pageSize;
    private int rowIndex = -1;
    private int rowCount = -1;
    List<Entity> resultsFromEao = null;

    public LazyEntityDataModel(AbstractFacade<Entity> facade, AbstractSearchFilter filter) {
        super();
        if (facade == null) {
            throw new IllegalArgumentException("El Facade no puede ser nulo");
        }
        this.facade = facade;
        this.filter = filter;
    }

    @Override
    public List<Entity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

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
    public boolean isRowAvailable() {
        if (facade == null) {
            return false;
        }
        int index = rowIndex % pageSize;
        return index >= 0 && index < resultsFromEao.size();
    }

    @Override
    public Object getRowKey(Entity dto
    ) {
        return String.valueOf(dto.getId());
    }

    @Override
    public Entity getRowData() {
        if (facade == null) {
            return null;
        }
        int index = rowIndex % pageSize;
        if (index >= resultsFromEao.size()) {
            return null;
        }
        return resultsFromEao.get(index);
    }

    @Override
    public Entity getRowData(String rowKey
    ) {
        if (resultsFromEao == null) {
            return null;
        }
        for (Entity dto : resultsFromEao) {
            if (dto.getId().toString().equals(rowKey)) {
                return dto;
            }
        }
        return null;
    }

    @Override
    public void setPageSize(int pageSize
    ) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex
    ) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount
    ) {
        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public void setWrappedData(Object list
    ) {
        this.resultsFromEao = (List<Entity>) list;
    }

    @Override
    public Object getWrappedData() {
        return resultsFromEao;
    }
}
