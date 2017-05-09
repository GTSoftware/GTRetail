/*
 * Copyright 2017 GT Software.
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

import ar.com.gtsoftware.model.ProductoXDeposito;
import ar.com.gtsoftware.model.ProductoXDeposito_;
import ar.com.gtsoftware.search.ProductoXDepositoSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author fede
 */
@Stateless
public class ProductoXDepositoFacade extends AbstractFacade<ProductoXDeposito, ProductoXDepositoSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ProductoXDepositoFacade() {
        super(ProductoXDeposito.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected Predicate createWhereFromSearchFilter(ProductoXDepositoSearchFilter sf, CriteriaBuilder cb, Root<ProductoXDeposito> root) {

        Predicate p = null, p1;

        if (sf.hasFilter()) {
            if (sf.hasIdDeposito()) {
                p1 = cb.equal(root.get(ProductoXDeposito_.producto), sf.getIdProducto());
                p = appendAndPredicate(cb, p, p1);
            }
            if (sf.hasIdDeposito()) {
                p1 = cb.equal(root.get(ProductoXDeposito_.deposito), sf.getIdDeposito());
                p = appendAndPredicate(cb, p, p1);
            }
        }
        return p;
    }

}
