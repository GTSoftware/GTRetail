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

import ar.com.gtsoftware.model.ProductosTiposUnidades;
import ar.com.gtsoftware.model.ProductosTiposUnidades_;
import ar.com.gtsoftware.search.UnidadesSearchFilter;
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
public class ProductosTiposUnidadesFacade extends AbstractFacade<ProductosTiposUnidades, UnidadesSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosTiposUnidadesFacade() {
        super(ProductosTiposUnidades.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(UnidadesSearchFilter sf, CriteriaBuilder cb, Root<ProductosTiposUnidades> root) {
        UnidadesSearchFilter usf = (UnidadesSearchFilter) sf;
        Predicate p = null;
        if (usf.getNombreUnidad() != null) {
            String s = usf.getNombreUnidad().toUpperCase();
            p = cb.like(root.get(ProductosTiposUnidades_.nombreUnidad), String.format("%%%s%%", s));
        }
        return p;
    }

}
