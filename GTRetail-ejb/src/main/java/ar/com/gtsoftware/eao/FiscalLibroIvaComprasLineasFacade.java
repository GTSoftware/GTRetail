/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package ar.com.gtsoftware.eao;

import ar.com.gtsoftware.model.FiscalLibroIvaCompras;
import ar.com.gtsoftware.model.FiscalLibroIvaComprasLineas;
import ar.com.gtsoftware.model.FiscalLibroIvaComprasLineas_;
import ar.com.gtsoftware.search.AbstractSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
@Stateless
public class FiscalLibroIvaComprasLineasFacade extends AbstractFacade<FiscalLibroIvaComprasLineas, AbstractSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalLibroIvaComprasLineasFacade() {
        super(FiscalLibroIvaComprasLineas.class);
    }

    /**
     * Devuelve las líneas de factura para la factura pasada como parámetro
     *
     * @param factura
     * @return una lista con las líneas de la factura
     */
    public List<FiscalLibroIvaComprasLineas> getLineasFactura(FiscalLibroIvaCompras factura) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FiscalLibroIvaComprasLineas> cq = cb.createQuery(FiscalLibroIvaComprasLineas.class);
        Root<FiscalLibroIvaComprasLineas> lineaFactura = cq.from(FiscalLibroIvaComprasLineas.class);
        cq.select(lineaFactura);
        Predicate p = cb.equal(lineaFactura.get(FiscalLibroIvaComprasLineas_.idRegistro), factura);

        cq.where(p);
        TypedQuery<FiscalLibroIvaComprasLineas> q = em.createQuery(cq);

        return q.getResultList();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<FiscalLibroIvaComprasLineas> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
