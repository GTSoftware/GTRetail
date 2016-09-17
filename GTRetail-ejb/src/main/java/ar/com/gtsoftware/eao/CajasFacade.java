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
import ar.com.gtsoftware.model.Cajas_;
import ar.com.gtsoftware.search.CajasSearchFilter;
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
public class CajasFacade extends AbstractFacade<Cajas, CajasSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajasFacade() {
        super(Cajas.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(CajasSearchFilter psf, CriteriaBuilder cb, Root<Cajas> root) {
        Predicate p = null;
        if (psf.getAbierta() != null) {
            Predicate p1 = cb.isNotNull(root.get(Cajas_.fechaCierre));
            if (psf.getAbierta()) {
                p1 = cb.isNull(root.get(Cajas_.fechaCierre));
            }
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getSucursal() != null) {
            Predicate p1 = cb.equal(root.get(Cajas_.idSucursal), psf.getSucursal());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getUsuario() != null) {
            Predicate p1 = cb.equal(root.get(Cajas_.idUsuario), psf.getUsuario());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;

    }

}