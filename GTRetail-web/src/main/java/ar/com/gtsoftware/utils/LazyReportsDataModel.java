/*
 * Copyright 2019 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package ar.com.gtsoftware.utils;

import ar.com.gtsoftware.bl.ReportsService;
import ar.com.gtsoftware.dto.IdentifiableDto;
import ar.com.gtsoftware.dto.reportes.GenericReport;
import ar.com.gtsoftware.search.reportes.AbstractReportSearchFilter;
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
 * @param <Report>
 * @param <Filter>
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
public class LazyReportsDataModel<Report extends GenericReport<T>, Filter extends AbstractReportSearchFilter,
        T extends IdentifiableDto>
        extends LazyDataModel<T> implements Serializable {

    private static final Logger LOG = Logger.getLogger(LazyReportsDataModel.class.getName());
    private static final long serialVersionUID = 1L;

    private ReportsService<Report, Filter> service;
    private Filter filter;

    private Report report = null;

    public LazyReportsDataModel(@NotNull ReportsService<Report, Filter> service, Filter filter) {
        super();
        this.service = service;
        this.filter = filter;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                        Map<String, Object> filters) {


        if (filter == null) {
            return Collections.emptyList();
        }

        filter.setFirstRow(first);
        filter.setPageSize(pageSize);

        report = service.obtenerReporte(filter);
        int totalRows = report.getTotalRows();

        setRowCount(totalRows);
        setPageSize(pageSize);

        setWrappedData(report.getPageRows());
        return report.getPageRows();
    }

    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        throw new UnsupportedOperationException("LazyLoading not implemented with multiSort and filters");
    }

    @Override
    public Object getRowKey(T dto) {
        return dto.getStringId();
    }

    @Override
    public T getRowData(String rowKey) {
        if (report == null || rowKey == null) {
            return null;
        }
        Optional<T> found = report.getPageRows().stream().filter(x -> Objects.equals(rowKey, x.getStringId())).findFirst();
        return found.orElse(null);
    }

}
