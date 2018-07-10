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

import ar.com.gtsoftware.model.ProveedoresOrdenesCompra;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompraLineas;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompraLineas_;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompra_;
import ar.com.gtsoftware.search.OrdenCompraSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * @author rodrigo
 */
@Stateless
public class ProveedoresOrdenesCompraFacade extends AbstractFacade<ProveedoresOrdenesCompra, OrdenCompraSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedoresOrdenesCompraFacade() {
        super(ProveedoresOrdenesCompra.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(OrdenCompraSearchFilter sf, CriteriaBuilder cb, Root<ProveedoresOrdenesCompra> root) {
        Predicate p = null;
        if (sf.hasEntreFechasAltaFilter()) {
            Predicate p1 = cb.between(root.get(ProveedoresOrdenesCompra_.fechaAlta), sf.getFechaAltaDesde(), sf.getFechaAltaHasta());
            p = appendAndPredicate(cb, p1, p);
        }
        if (sf.getIdProveedor() != null) {
            Predicate p1 = cb.equal(root.get(ProveedoresOrdenesCompra_.idProveedor), sf.getIdProveedor());
            p = appendAndPredicate(cb, p1, p);
        }
        if (sf.getIdEstadoOrdenCompra() != null) {
            Predicate p1 = cb.equal(root.get(ProveedoresOrdenesCompra_.idEstadoOrdenCompra), sf.getIdEstadoOrdenCompra());
            p = appendAndPredicate(cb, p1, p);
        }
        if (sf.getIdProducto() != null) {
            //Verifica que exista alguna línea de la OC que tenga ese producto.
            Subquery<Long> subQOcLineas = cb.createQuery().subquery(Long.class);
            Root<ProveedoresOrdenesCompraLineas> fromSubQ = subQOcLineas.from(ProveedoresOrdenesCompraLineas.class);

            subQOcLineas.select(fromSubQ.get(ProveedoresOrdenesCompraLineas_.id));

            Predicate ps1 = cb.equal(fromSubQ.get(ProveedoresOrdenesCompraLineas_.idProducto), sf.getIdProducto());
            Predicate ps2 = cb.equal(fromSubQ.get(ProveedoresOrdenesCompraLineas_.idOrdenCompra), root);
            subQOcLineas.where(cb.and(ps1, ps2));

            Predicate p1 = cb.exists(subQOcLineas);

            p = appendAndPredicate(cb, p1, p);
        }

        if (sf.hasTxtFilter()) {
            String s = sf.getTxt().toUpperCase();
            Predicate p1 = cb.like(root.get(ProveedoresOrdenesCompra_.observaciones), String.format("%%%s%%", s));
            p = appendAndPredicate(cb, p1, p);
        }

        return p;

    }

}
