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

import ar.com.gtsoftware.model.UbicacionPaises_;
import ar.com.gtsoftware.model.UbicacionProvincias;
import ar.com.gtsoftware.model.UbicacionProvincias_;
import ar.com.gtsoftware.search.ProvinciasSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author rodrigo
 */
@Stateless
public class UbicacionProvinciasFacade extends AbstractFacade<UbicacionProvincias, ProvinciasSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public UbicacionProvinciasFacade() {
        super(UbicacionProvincias.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Predicate createWhereFromSearchFilter(ProvinciasSearchFilter psf, CriteriaBuilder cb, Root<UbicacionProvincias> root) {
        Predicate p = null;
        if (psf.getIdProvincia() != null) {
            p = cb.equal(root.get(UbicacionProvincias_.id), psf.getIdProvincia());
        }
        if (psf.getNombreProvincia() != null) {
            Predicate p1 = cb.like(root.get(UbicacionProvincias_.nombreProvincia), String.format("%%%s%%", psf.getNombreProvincia().toUpperCase()));
            p = appendOrPredicate(cb, p, p1);
        }
        if (psf.getIdPais() != null) {
            Predicate p1 = cb.equal(root.get(UbicacionProvincias_.idPais).get(UbicacionPaises_.id), psf.getIdPais());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;
    }

}
