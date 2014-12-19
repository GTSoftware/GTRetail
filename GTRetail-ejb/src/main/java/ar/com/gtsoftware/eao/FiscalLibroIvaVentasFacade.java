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

import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.FiscalLibroIvaVentas_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.IVAVentasSearchFilter;
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
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @since 1.0.0
 * @version 1.0.0
 */
@Stateless
public class FiscalLibroIvaVentasFacade extends AbstractFacade<FiscalLibroIvaVentas> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalLibroIvaVentasFacade() {
        super(FiscalLibroIvaVentas.class);
    }

    /**
     * Retorna la ultima factura registrada con la letra y el punto de venta
     * pasados por parametro
     *
     * @param letra
     * @param puntoVenta
     * @return una factura
     */
    public FiscalLibroIvaVentas findUltimaFactura(String letra, String puntoVenta) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FiscalLibroIvaVentas> cq = cb.createQuery(FiscalLibroIvaVentas.class);
        Root<FiscalLibroIvaVentas> registroIVA = cq.from(FiscalLibroIvaVentas.class);
        cq.select(registroIVA);
        Predicate p1 = cb.equal(registroIVA.get(FiscalLibroIvaVentas_.letraFactura), letra);
        Predicate p2 = cb.equal(registroIVA.get(FiscalLibroIvaVentas_.puntoVentaFactura), puntoVenta);

        cq.where(cb.and(p1, p2));
        cq.orderBy(cb.desc(registroIVA.get(FiscalLibroIvaVentas_.numeroFactura)));
        TypedQuery<FiscalLibroIvaVentas> q = em.createQuery(cq);
        q.setMaxResults(1);
        List<FiscalLibroIvaVentas> registroIVAList = q.getResultList();
        if (!registroIVAList.isEmpty()) {
            return registroIVAList.get(0);
        }
        return null;

    }

    /**
     * Retorna la lista de facturas que corresponden con los criterios de
     * filtrado
     *
     * @param ivavsf
     * @return una lista de facturas
     */
    public List<FiscalLibroIvaVentas> findBySearchFilter(IVAVentasSearchFilter ivavsf) {
        if (ivavsf.hasFilter()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<FiscalLibroIvaVentas> cq = cb.createQuery(FiscalLibroIvaVentas.class);
            Root<FiscalLibroIvaVentas> registroIVA = cq.from(FiscalLibroIvaVentas.class);
            cq.select(registroIVA);
            Predicate p = null;
            if (ivavsf.getPeriodo() != null) {
                Predicate p1 = cb.equal(registroIVA.get(FiscalLibroIvaVentas_.idPeriodoFiscal), ivavsf.getPeriodo());
                p = appendAndPredicate(cb, p1, p);
            }
            if (ivavsf.hasFechasDesdeHasta()) {
                Predicate p1 = cb.between(registroIVA.get(FiscalLibroIvaVentas_.fechaFactura), ivavsf.getFechaDesde(), ivavsf.getFechaHasta());
                p = appendAndPredicate(cb, p1, p);
            }
            if (ivavsf.getAnuladas() != null) {
                Predicate p1 = cb.equal(registroIVA.get(FiscalLibroIvaVentas_.anulada), ivavsf.getAnuladas());
                p = appendAndPredicate(cb, p1, p);
            }
            cq.where(p);
            cq.orderBy(cb.asc(registroIVA.get(FiscalLibroIvaVentas_.fechaFactura)),
                    cb.asc(registroIVA.get(FiscalLibroIvaVentas_.numeroFactura)));
            TypedQuery<FiscalLibroIvaVentas> q = em.createQuery(cq);
            List<FiscalLibroIvaVentas> registroIVAList = q.getResultList();
            return registroIVAList;
        }
        return new ArrayList<>();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<FiscalLibroIvaVentas> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FiscalLibroIvaVentas> findBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(FiscalLibroIvaVentas entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
