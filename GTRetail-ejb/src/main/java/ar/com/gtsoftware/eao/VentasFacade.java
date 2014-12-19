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

import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.Ventas_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.VentasSearchFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
 * @author rodrigo
 */
@Stateless
public class VentasFacade extends AbstractFacade<Ventas> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentasFacade() {
        super(Ventas.class);
    }

    public List<Ventas> findBySearchFilter(VentasSearchFilter vsf) {
        if (vsf.hasFilter()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ventas> cq = cb.createQuery(Ventas.class);
            Root<Ventas> venta = cq.from(Ventas.class);
            cq.select(venta);
            Predicate p = null;
            if (vsf.getIdVenta() != null) {
                p = cb.equal(venta.get(Ventas_.id), vsf.getIdVenta());
            }
            if (vsf.getFechaVentaDesde() != null && vsf.getFechaVentaHasta() != null) {
                Predicate p1 = cb.between(venta.get(Ventas_.fechaVenta), vsf.getFechaVentaDesde(), vsf.getFechaVentaHasta());
                p = appendAndPredicate(cb, p1, p);
            }
            if (vsf.getIdPersona() != null) {
                Predicate p1 = cb.equal(venta.get(Ventas_.idPersona), vsf.getIdPersona());
                p = appendAndPredicate(cb, p1, p);
            }
            if (vsf.getIdSucursal() != null) {
                Predicate p1 = cb.equal(venta.get(Ventas_.idSucursal), vsf.getIdSucursal());
                p = appendAndPredicate(cb, p1, p);
            }
            if (vsf.getCondicionVenta() != null) {
                Predicate p1 = cb.equal(venta.get(Ventas_.idCondicionVenta), vsf.getCondicionVenta());
                p = appendAndPredicate(cb, p1, p);
            }
            if (vsf.getIdUsuario() != null) {
                Predicate p1 = cb.equal(venta.get(Ventas_.idUsuario), vsf.getIdUsuario());
                p = appendAndPredicate(cb, p1, p);
            }
            if (vsf.getFacturada() != null) {
                Predicate p1;
                if (vsf.getFacturada()) {
                    p1 = cb.not(cb.isNull(venta.get(Ventas_.idRegistroIva)));
                } else {
                    p1 = cb.isNull(venta.get(Ventas_.idRegistroIva));
                }

                p = appendAndPredicate(cb, p1, p);
            }
            if (vsf.getConSaldo() != null) {
                Predicate p1;
                if (vsf.getConSaldo()) {
                    p1 = cb.greaterThan(venta.get(Ventas_.saldo), BigDecimal.ZERO);
                } else {
                    p1 = cb.equal(venta.get(Ventas_.saldo), BigDecimal.ZERO);
                }

                p = appendAndPredicate(cb, p1, p);
            }
            if (vsf.getAnulada() != null) {
                Predicate p1;
                if (vsf.getAnulada()) {
                    p1 = cb.isTrue(venta.get(Ventas_.anulada));
                } else {
                    p1 = cb.isFalse(venta.get(Ventas_.anulada));
                }

                p = appendAndPredicate(cb, p1, p);
            }
            cq.where(p);
            TypedQuery<Ventas> q = em.createQuery(cq);

            List<Ventas> ventasList = q.getResultList();
            return ventasList;
        }
        return new ArrayList<>();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<Ventas> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ventas> findBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(Ventas entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
