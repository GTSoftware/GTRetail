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
package ar.com.gtsoftware.eao;

import ar.com.gtsoftware.model.NegocioPlanesPagoDetalle;
import ar.com.gtsoftware.model.NegocioPlanesPagoDetalle_;
import ar.com.gtsoftware.model.NegocioPlanesPago_;
import ar.com.gtsoftware.search.PlanesPagoDetalleSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Capa de acceso a datos para planes de pago
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class NegocioPlanesPagoDetalleFacade extends AbstractFacade<NegocioPlanesPagoDetalle, PlanesPagoDetalleSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public NegocioPlanesPagoDetalleFacade() {
        super(NegocioPlanesPagoDetalle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Predicate createWhereFromSearchFilter(PlanesPagoDetalleSearchFilter sf, CriteriaBuilder cb, Root<NegocioPlanesPagoDetalle> root) {
        Predicate p = null;

        if (sf.getIdPlan() != null) {
            Predicate p1 = cb.equal(root.get(NegocioPlanesPagoDetalle_.idPlan).get(NegocioPlanesPago_.id), sf.getIdPlan());
            p = appendAndPredicate(cb, p, p1);
        }

        if (sf.getActivo() != null) {
            Predicate p1 = cb.equal(root.get(NegocioPlanesPagoDetalle_.activo), sf.getActivo());
            p = appendAndPredicate(cb, p, p1);
        }

        return p;

    }

}
