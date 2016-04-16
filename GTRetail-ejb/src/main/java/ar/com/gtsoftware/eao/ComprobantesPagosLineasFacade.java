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

import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.ComprobantesPagos;
import ar.com.gtsoftware.model.ComprobantesPagosLineas;
import ar.com.gtsoftware.model.ComprobantesPagosLineas_;
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
public class ComprobantesPagosLineasFacade extends AbstractFacade<ComprobantesPagosLineas> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprobantesPagosLineasFacade() {
        super(ComprobantesPagosLineas.class);
    }

    public List<ComprobantesPagosLineas> findLineasPagosNoAcentadas(Comprobantes venta) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ComprobantesPagosLineas> cq = cb.createQuery(ComprobantesPagosLineas.class);
        Root<ComprobantesPagosLineas> pagoLinea = cq.from(ComprobantesPagosLineas.class);
        cq.select(pagoLinea);
        Predicate p1 = cb.equal(pagoLinea.get(ComprobantesPagosLineas_.idComprobante), venta);
        Predicate p2 = cb.isNull(pagoLinea.get(ComprobantesPagosLineas_.idCajasMovimientos));
        cq.where(cb.and(p1, p2));
        TypedQuery<ComprobantesPagosLineas> q = em.createQuery(cq);
        List<ComprobantesPagosLineas> pagosLineasList = q.getResultList();
        return pagosLineasList;

    }

    public List<ComprobantesPagosLineas> findLineasPago(ComprobantesPagos pago) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ComprobantesPagosLineas> cq = cb.createQuery(ComprobantesPagosLineas.class);
        Root<ComprobantesPagosLineas> pagoLinea = cq.from(ComprobantesPagosLineas.class);
        cq.select(pagoLinea);
        Predicate p1 = cb.equal(pagoLinea.get(ComprobantesPagosLineas_.idPagoComprobante), pago);

        cq.where(p1);
        TypedQuery<ComprobantesPagosLineas> q = em.createQuery(cq);
        List<ComprobantesPagosLineas> pagosLineasList = q.getResultList();
        return pagosLineasList;

    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<ComprobantesPagosLineas> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComprobantesPagosLineas> findAllBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(ComprobantesPagosLineas entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
