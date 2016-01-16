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

import ar.com.gtsoftware.model.ProductosMarcas;
import ar.com.gtsoftware.model.ProductosMarcas_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.MarcasSearchFilter;
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
public class ProductosMarcasFacade extends AbstractFacade<ProductosMarcas> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosMarcasFacade() {
        super(ProductosMarcas.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<ProductosMarcas> root) {
        MarcasSearchFilter msf = (MarcasSearchFilter) sf;
        Predicate p = null;
        if (msf.getNombreMarca() != null) {
            String s = msf.getNombreMarca().toUpperCase();
            p = cb.like(root.get(ProductosMarcas_.nombreMarca), String.format("%%%s%%", s));
        }

        return p;
    }

}
