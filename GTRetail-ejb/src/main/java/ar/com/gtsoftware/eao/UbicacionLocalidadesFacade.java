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

import ar.com.gtsoftware.model.UbicacionLocalidades;
import ar.com.gtsoftware.model.UbicacionLocalidades_;
import ar.com.gtsoftware.search.LocalidadesSearchFilter;
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
public class UbicacionLocalidadesFacade extends AbstractFacade<UbicacionLocalidades> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbicacionLocalidadesFacade() {
        super(UbicacionLocalidades.class);
    }

    public List<UbicacionLocalidades> findBySearchFilter(LocalidadesSearchFilter lsf) {
        if (lsf.hasFilter()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<UbicacionLocalidades> cq = cb.createQuery(UbicacionLocalidades.class);
            Root<UbicacionLocalidades> localidad = cq.from(UbicacionLocalidades.class);
            cq.select(localidad);
            Predicate p = null;
            if (lsf.getIdLocalidad() != null) {
                p = cb.equal(localidad.get(UbicacionLocalidades_.id), lsf.getIdLocalidad());
            }
            if (lsf.getNombreLocalidad() != null) {
                Predicate p1 = cb.like(localidad.get(UbicacionLocalidades_.nombreLocalidad), String.format("%%%s%%", lsf.getNombreLocalidad().toUpperCase()));
                p = appendOrPredicate(cb, p, p1);
            }
            if (lsf.getIdProvincia() != null) {
                Predicate p1 = cb.equal(localidad.get(UbicacionLocalidades_.idProvincia), lsf.getIdProvincia());
                p = appendAndPredicate(cb, p, p1);
            }

            cq.where(p);

            TypedQuery<UbicacionLocalidades> q = em.createQuery(cq);

            List<UbicacionLocalidades> localidadesList = q.getResultList();
            return localidadesList;
        }
        return new ArrayList<>();
    }
}
