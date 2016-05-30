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

import ar.com.gtsoftware.model.LegalGeneros;
import ar.com.gtsoftware.model.LegalGeneros_;
import ar.com.gtsoftware.search.GenerosSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rodrigo
 */
@Stateless
public class LegalGenerosFacade extends AbstractFacade<LegalGeneros, GenerosSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LegalGenerosFacade() {
        super(LegalGeneros.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(GenerosSearchFilter gsf, CriteriaBuilder cb, Root<LegalGeneros> root) {
        Predicate p = null;
        if (gsf.getIdGenero() != null) {
            p = cb.equal(root.get(LegalGeneros_.id), gsf.getIdGenero());
        }
        if (gsf.getIdTipoPersoneria() != null) {
            Predicate p1 = cb.equal(root.get(LegalGeneros_.idTipoPersoneria), gsf.getIdTipoPersoneria());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;
    }

}
