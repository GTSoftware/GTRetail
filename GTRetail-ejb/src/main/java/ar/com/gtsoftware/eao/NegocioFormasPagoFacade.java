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

import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.model.NegocioFormasPago_;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class NegocioFormasPagoFacade extends AbstractFacade<NegocioFormasPago, FormasPagoSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NegocioFormasPagoFacade() {
        super(NegocioFormasPago.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(FormasPagoSearchFilter sf, CriteriaBuilder cb, Root<NegocioFormasPago> root) {
        Predicate p = null;
        if (sf.getDisponibleCompra() != null) {
            Predicate p1 = cb.equal(root.get(NegocioFormasPago_.compra), sf.getDisponibleCompra());
            p = appendAndPredicate(cb, p, p1);
        }

        if (sf.getRequierePlanes() != null) {
            Predicate p1 = cb.equal(root.get(NegocioFormasPago_.requierePlan), sf.getRequierePlanes());
            p = appendAndPredicate(cb, p, p1);
        }

        if (sf.getDisponibleVenta() != null) {
            Predicate p1 = cb.equal(root.get(NegocioFormasPago_.venta), sf.getDisponibleVenta());
            p = appendAndPredicate(cb, p, p1);
        }

        if (StringUtils.isNotEmpty(sf.getNombre())) {
            Predicate p1 = cb.like(cb.upper(root.get(NegocioFormasPago_.nombreFormaPago)), String.format("%%%s%%", sf.getNombre().toUpperCase()));
            p = appendAndPredicate(cb, p, p1);
        }

        return p;

    }

}
