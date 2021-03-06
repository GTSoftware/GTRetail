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

import ar.com.gtsoftware.model.FiscalTiposComprobante;
import ar.com.gtsoftware.model.FiscalTiposComprobante_;
import ar.com.gtsoftware.model.NegocioTiposComprobante_;
import ar.com.gtsoftware.search.FiscalTiposComprobanteSearchFilter;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Stateless
public class FiscalTiposComprobanteFacade extends AbstractFacade<FiscalTiposComprobante, FiscalTiposComprobanteSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalTiposComprobanteFacade() {
        super(FiscalTiposComprobante.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(FiscalTiposComprobanteSearchFilter ftsf, CriteriaBuilder cb, Root<FiscalTiposComprobante> root) {

        Predicate p = null;
        if (StringUtils.isNotEmpty(ftsf.getLetra())) {
            Predicate p1 = cb.equal(root.get(FiscalTiposComprobante_.letra), ftsf.getLetra());
            p = appendAndPredicate(cb, p, p1);
        }
        if (ftsf.getIdTipoComprobante() != null) {
            Predicate p1 = cb.equal(root.get(FiscalTiposComprobante_.tipoComprobante).get(NegocioTiposComprobante_.id), ftsf.getIdTipoComprobante());
            p = appendAndPredicate(cb, p, p1);
        }

        return p;
    }

}
