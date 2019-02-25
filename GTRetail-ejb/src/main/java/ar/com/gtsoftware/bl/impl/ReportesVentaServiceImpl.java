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

package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.ReportesVentaService;
import ar.com.gtsoftware.dto.reportes.VentaPorProducto;
import ar.com.gtsoftware.dto.reportes.VentaPorProductoReport;
import ar.com.gtsoftware.search.reportes.ReporteVentasSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ReportesVentaServiceImpl implements ReportesVentaService {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public VentaPorProductoReport obtenerReporte(ReporteVentasSearchFilter filter) {
        TypedQuery<Long> qCount = em.createNamedQuery("ReporteVentasQueryCount", Long.class);
        if (!filter.hasFechasFilter()) {
            filter.setDefaultDatesValues();
        }
        qCount.setParameter("fechaDesde", filter.getFechaDesde(), TemporalType.DATE);
        qCount.setParameter("fechaHasta", filter.getFechaHasta(), TemporalType.DATE);
        qCount.setParameter("idSucursal", filter.getIdSucursal());

        Integer maxRows = qCount.getSingleResult().intValue();

        VentaPorProductoReport report = new VentaPorProductoReport(maxRows);

        TypedQuery<VentaPorProducto> q = em.createNamedQuery("ReporteVentasQuery", VentaPorProducto.class);
        q.setMaxResults(filter.getPageSize());
        q.setFirstResult(filter.getFirstRow());

        q.setParameter("fechaDesde", filter.getFechaDesde(), TemporalType.DATE);
        q.setParameter("fechaHasta", filter.getFechaHasta(), TemporalType.DATE);
        q.setParameter("idSucursal", filter.getIdSucursal());

        List<VentaPorProducto> items = q.getResultList();

        report.setPageRows(items);

        return report;
    }
}
