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

import ar.com.gtsoftware.model.NegocioTiposComprobante;
import ar.com.gtsoftware.model.NegocioTiposComprobante_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.NegocioTiposComprobanteSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class NegocioTiposComprobanteFacade extends AbstractFacade<NegocioTiposComprobante> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NegocioTiposComprobanteFacade() {
        super(NegocioTiposComprobante.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<NegocioTiposComprobante> root) {
        NegocioTiposComprobanteSearchFilter nsf = (NegocioTiposComprobanteSearchFilter) sf;
        Predicate p = null;
        if (nsf.getActivo() != null) {
            Predicate p1 = cb.equal(root.get(NegocioTiposComprobante_.activo), nsf.getActivo());
            p = appendAndPredicate(cb, p, p1);
        }
        if (StringUtils.isNotEmpty(nsf.getNombre())) {
            String s = nsf.getNombre().toUpperCase();
            Predicate p1 = cb.like(root.get(NegocioTiposComprobante_.nombreComprobante), String.format("%%%s%%", s));
            p = appendAndPredicate(cb, p, p1);
        }
        return p;

    }

    public NegocioTiposComprobante getTipoFactura() {
        return this.find(1L);
    }

}