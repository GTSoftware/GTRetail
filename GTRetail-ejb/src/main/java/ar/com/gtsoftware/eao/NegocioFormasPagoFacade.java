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
import ar.com.gtsoftware.search.AbstractSearchFilter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class NegocioFormasPagoFacade extends AbstractFacade<NegocioFormasPago> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NegocioFormasPagoFacade() {
        super(NegocioFormasPago.class);
    }

    public List<NegocioFormasPago> findFormasPagoVenta() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NegocioFormasPago> cq = cb.createQuery(NegocioFormasPago.class);
        Root<NegocioFormasPago> formaPago = cq.from(NegocioFormasPago.class);
        cq.select(formaPago);
        Predicate p = cb.equal(formaPago.get(NegocioFormasPago_.venta), true);
        cq.where(p);
        TypedQuery<NegocioFormasPago> q = em.createQuery(cq);

        List<NegocioFormasPago> formasPagoList = q.getResultList();
        return formasPagoList;
    }

    public List<NegocioFormasPago> findFormasPagoCompra() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NegocioFormasPago> cq = cb.createQuery(NegocioFormasPago.class);
        Root<NegocioFormasPago> formaPago = cq.from(NegocioFormasPago.class);
        cq.select(formaPago);
        Predicate p = cb.equal(formaPago.get(NegocioFormasPago_.compra), true);
        cq.where(p);
        TypedQuery<NegocioFormasPago> q = em.createQuery(cq);

        List<NegocioFormasPago> formasPagoList = q.getResultList();
        return formasPagoList;
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<NegocioFormasPago> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NegocioFormasPago> findAllBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(NegocioFormasPago entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
