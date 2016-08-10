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

import ar.com.gtsoftware.model.FiscalPuntosVenta;
import ar.com.gtsoftware.model.FiscalPuntosVenta_;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class FiscalPuntosVentaFacade extends AbstractFacade<FiscalPuntosVenta, FiscalPuntosVentaSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalPuntosVentaFacade() {
        super(FiscalPuntosVenta.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(FiscalPuntosVentaSearchFilter pvsf, CriteriaBuilder cb, Root<FiscalPuntosVenta> root) {

        Predicate p = null;
        if (pvsf.getSucursal() != null) {
            Predicate p1 = cb.equal(root.get(FiscalPuntosVenta_.sucursal), pvsf.getSucursal());
            p = appendAndPredicate(cb, p, p1);
        }

        if (pvsf.getActivo() != null) {
            Predicate p1 = cb.equal(root.get(FiscalPuntosVenta_.activo), pvsf.getActivo());
            p = appendAndPredicate(cb, p, p1);
        }

        if (pvsf.getNroPuntoVenta() != null) {
            Predicate p1 = cb.equal(root.get(FiscalPuntosVenta_.nroPuntoVenta), pvsf.getNroPuntoVenta());
            p = appendAndPredicate(cb, p, p1);
        }

        if (pvsf.getTipoPuntoVenta() != null) {
            Predicate p1 = cb.equal(root.get(FiscalPuntosVenta_.tipo), pvsf.getTipoPuntoVenta());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;
    }

}
