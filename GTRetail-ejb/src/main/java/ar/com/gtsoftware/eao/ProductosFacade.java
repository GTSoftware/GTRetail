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

import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosRubros_;
import ar.com.gtsoftware.model.ProductosSubRubros_;
import ar.com.gtsoftware.model.ProductosTiposProveeduria_;
import ar.com.gtsoftware.model.Productos_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
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
public class ProductosFacade extends AbstractFacade<Productos> {

    private static final String WORDS = "\\W";
    private static final String LIKE = "%%%s%%";

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<Productos> root) {
        ProductosSearchFilter psf = (ProductosSearchFilter) sf;
        Predicate p = null;
        if (psf.getIdProducto() != null) {
            p = cb.equal(root.get(Productos_.id), psf.getIdProducto());
        }
        if (!StringUtils.isEmpty(psf.getCodigoPropio())) {
            p = cb.equal(root.get(Productos_.codigoPropio), psf.getCodigoPropio());
        }
        if (!StringUtils.isEmpty(psf.getTxt())) {

            for (String s : psf.getTxt().toUpperCase().split(WORDS)) {
                Predicate pTxt = null;
                Predicate p1 = cb.like(root.get(Productos_.descripcion), String.format(LIKE, s));
                Predicate p2 = cb.like(root.get(Productos_.idRubro).get(ProductosRubros_.nombreRubro), String.format(LIKE, s));
                Predicate p3 = cb.like(root.get(Productos_.idSubRubro).get(ProductosSubRubros_.nombreSubRubro), String.format(LIKE, s));
                Predicate p4 = cb.like(root.get(Productos_.codigoPropio), String.format(LIKE, s));
                pTxt = appendOrPredicate(cb, pTxt, p1);
                pTxt = appendOrPredicate(cb, pTxt, p2);
                pTxt = appendOrPredicate(cb, pTxt, p3);
                pTxt = appendOrPredicate(cb, pTxt, p4);

                if (StringUtils.isNumeric(s)) {
                    Predicate pId = cb.equal(root.get(Productos_.id), Long.parseLong(s));
                    pTxt = appendOrPredicate(cb, pTxt, pId);
                }
                p = appendAndPredicate(cb, p, pTxt);
            }

        }
        if (psf.getIdProveedorHabitual() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idProveedorHabitual), psf.getIdProveedorHabitual());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getPuedeComprarse() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.puedeComprarse),
                    psf.getPuedeComprarse());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getPuedeVenderse() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.puedeVenderse), psf.getPuedeVenderse());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getIdRubro() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idRubro), psf.getIdRubro());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getIdSubRubro() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idSubRubro), psf.getIdSubRubro());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.isActivo() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.activo), psf.isActivo());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getConStockEnDeposito() != null) {
            Predicate p1 = cb.isTrue(root.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.controlStock));
            //TODO Or existe que ese producto tiene stock > 0 en el deposito del filtro
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getIdTipoProveeduria() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idTipoProveeduria), psf.getIdTipoProveeduria());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getIdMarca() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idMarca), psf.getIdMarca());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;

    }

}
