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

import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.Cupones;
import ar.com.gtsoftware.model.Cupones_;
import ar.com.gtsoftware.model.Recibos;
import ar.com.gtsoftware.model.RecibosDetalle;
import ar.com.gtsoftware.model.RecibosDetalle_;
import ar.com.gtsoftware.model.Recibos_;
import ar.com.gtsoftware.search.CuponesSearchFilter;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rodrigo
 */
@Stateless
public class CuponesFacade extends AbstractFacade<Cupones, CuponesSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuponesFacade() {
        super(Cupones.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(CuponesSearchFilter sf, CriteriaBuilder cb, Root<Cupones> root) {

        Predicate p = null;
        if (sf.getCaja() != null) {
            Join<Cupones, RecibosDetalle> joinCup = root.join(Cupones_.reciboDetalle);
            Join<RecibosDetalle, Recibos> joinRec = joinCup.join(RecibosDetalle_.idRecibo);
            Predicate p1 = cb.equal(joinRec.get(Recibos_.idCaja), sf.getCaja());
            p = appendAndPredicate(cb, p, p1);

        }
        if (sf.hasValidFechasOrigen()) {
            Predicate p1 = cb.between(root.get(Cupones_.fechaOrigen), sf.getFechaOrigenDesde(), sf.getFechaOrigenHasta());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;
    }

    public void establecerFechaPresentacion(Cajas idCaja, Date fechaPresentacion) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<Cupones> update = cb.createCriteriaUpdate(Cupones.class);
        Root root = update.from(Cupones.class);
        update.set(Cupones_.fechaPresentacion, fechaPresentacion);
        CuponesSearchFilter sf = new CuponesSearchFilter(idCaja);
        Predicate p = createWhereFromSearchFilter(sf, cb, root);
        Predicate pNoPres = cb.isNull(root.get(Cupones_.fechaPresentacion));
        p = appendAndPredicate(cb, p, pNoPres);
        update.where(p);
        this.em.createQuery(update).executeUpdate();
    }

}
