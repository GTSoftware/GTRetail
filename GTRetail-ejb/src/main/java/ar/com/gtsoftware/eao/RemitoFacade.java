/*
 * Copyright 2017 GT Software.
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

import ar.com.gtsoftware.model.Remito;
import ar.com.gtsoftware.model.Remito_;
import ar.com.gtsoftware.search.RemitoSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author fede
 */
@Stateless
public class RemitoFacade extends AbstractFacade<Remito, RemitoSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public RemitoFacade() {
        super(Remito.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    protected Predicate createWhereFromSearchFilter(RemitoSearchFilter sf, CriteriaBuilder cb, Root<Remito> root) {
        Predicate p = null;
        if (sf.hasFechaEntreFechasAltaFilter()) {
            Predicate p1 = cb.between(root.get(Remito_.fechaAlta), sf.getFechaAltaDesde(), sf.getFechaAltaHasta());
            p = appendAndPredicate(cb, p1, p);
        }

        if (sf.getTipoMovimiento() != null) {
            Predicate p1 = cb.equal(root.get(Remito_.remitoTipoMovimiento), sf.getTipoMovimiento());
            p = appendAndPredicate(cb, p1, p);
        }
        return p;
    }

}
