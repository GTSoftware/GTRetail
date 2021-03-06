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

import ar.com.gtsoftware.model.FiscalLetrasComprobantes;
import ar.com.gtsoftware.model.FiscalLetrasComprobantes_;
import ar.com.gtsoftware.model.FiscalResponsabilidadesIva_;
import ar.com.gtsoftware.search.FiscalLetrasComprobantesSearchFilter;

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
public class FiscalLetrasComprobantesFacade extends AbstractFacade<FiscalLetrasComprobantes, FiscalLetrasComprobantesSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalLetrasComprobantesFacade() {
        super(FiscalLetrasComprobantes.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(FiscalLetrasComprobantesSearchFilter lsf, CriteriaBuilder cb, Root<FiscalLetrasComprobantes> root) {

        Predicate p = null;
        if (lsf.getIdRespIvaEmisor() != null) {
            Predicate p1 = cb.equal(root.get(FiscalLetrasComprobantes_.idResponsabilidadIvaEmisor).get(FiscalResponsabilidadesIva_.id), lsf.getIdRespIvaEmisor());
            p = appendAndPredicate(cb, p, p1);
        }
        if (lsf.getIdRespIvaReceptor() != null) {
            Predicate p1 = cb.equal(root.get(FiscalLetrasComprobantes_.idResponsabilidadIvaReceptor).get(FiscalResponsabilidadesIva_.id), lsf.getIdRespIvaReceptor());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;

    }

}
