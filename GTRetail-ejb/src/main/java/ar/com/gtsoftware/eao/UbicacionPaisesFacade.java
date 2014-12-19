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

import ar.com.gtsoftware.model.UbicacionPaises;
import ar.com.gtsoftware.model.UbicacionPaises_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.PaisesSearchFilter;
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
public class UbicacionPaisesFacade extends AbstractFacade<UbicacionPaises> {
    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbicacionPaisesFacade() {
        super(UbicacionPaises.class);
    }
    
    public List<UbicacionPaises> findBySearchFilter(PaisesSearchFilter psf) {
        if (psf.hasFilter()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<UbicacionPaises> cq = cb.createQuery(UbicacionPaises.class);
            Root<UbicacionPaises> pais = cq.from(UbicacionPaises.class);
            cq.select(pais);
            Predicate p = null;
            if (psf.getIdPais()!= null) {
                p = cb.equal(pais.get(UbicacionPaises_.id), psf.getIdPais());
            }
            if (psf.getNombrePais()!= null) {
                Predicate p1 = cb.like(pais.get(UbicacionPaises_.nombrePais), String.format("%%%s%%", psf.getNombrePais().toUpperCase()));
                p = appendOrPredicate(cb, p, p1);
            }
            
            cq.where(p);
            
            TypedQuery<UbicacionPaises> q = em.createQuery(cq);

            List<UbicacionPaises> paisesList = q.getResultList();
            return paisesList;
        }
        return new ArrayList<>();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<UbicacionPaises> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UbicacionPaises> findBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(UbicacionPaises entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
