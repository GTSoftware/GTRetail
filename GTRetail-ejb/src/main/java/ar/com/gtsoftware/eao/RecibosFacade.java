/*
 * Copyright 2016 GT Software.
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

import ar.com.gtsoftware.model.Recibos;
import ar.com.gtsoftware.model.Recibos_;
import ar.com.gtsoftware.search.RecibosSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@Stateless
public class RecibosFacade extends AbstractFacade<Recibos, RecibosSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecibosFacade() {
        super(Recibos.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(RecibosSearchFilter rsf, CriteriaBuilder cb, Root<Recibos> root) {
        Predicate p = null;

        if (rsf.getIdRecibo() != null) {
            Predicate p1 = cb.equal(root.get(Recibos_.id), rsf.getIdRecibo());
            p = appendAndPredicate(cb, p1, p);

        }

        if (rsf.getFechaDesde() != null && rsf.getFechaHasta() != null) {
            Predicate p1 = cb.between(root.get(Recibos_.fechaRecibo), rsf.getFechaDesde(), rsf.getFechaHasta());
            p = appendAndPredicate(cb, p1, p);
        }

        if (rsf.getIdCaja() != null) {
            Predicate p1 = cb.equal(root.get(Recibos_.idCaja), rsf.getIdCaja());
            p = appendAndPredicate(cb, p1, p);
        }

        if (rsf.getIdPersona() != null) {
            Predicate p1 = cb.equal(root.get(Recibos_.idPersona), rsf.getIdPersona());
            p = appendAndPredicate(cb, p1, p);
        }

        if (rsf.getIdUsuario() != null) {
            Predicate p1 = cb.equal(root.get(Recibos_.idUsuario), rsf.getIdUsuario());
            p = appendAndPredicate(cb, p1, p);
        }
        return p;
    }

}
