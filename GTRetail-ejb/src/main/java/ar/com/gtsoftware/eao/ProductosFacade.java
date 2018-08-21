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

import ar.com.gtsoftware.model.ProductoXDeposito;
import ar.com.gtsoftware.model.ProductoXDeposito_;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosMarcas_;
import ar.com.gtsoftware.model.ProductosRubros_;
import ar.com.gtsoftware.model.ProductosSubRubros_;
import ar.com.gtsoftware.model.ProductosTiposProveeduria_;
import ar.com.gtsoftware.model.Productos_;
import ar.com.gtsoftware.search.ProductosSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos, ProductosSearchFilter> {

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
    public Predicate createWhereFromSearchFilter(ProductosSearchFilter psf, CriteriaBuilder cb, Root<Productos> root) {

        Predicate p = null;
        if (psf.getIdProducto() != null) {
            p = cb.equal(root.get(Productos_.id), psf.getIdProducto());
        }
        if (!StringUtils.isEmpty(psf.getCodigoPropio())) {
            p = cb.equal(root.get(Productos_.codigoPropio), psf.getCodigoPropio());
        }
        if (StringUtils.isNotEmpty(psf.getTxt())) {

            if (StringUtils.isNumeric(psf.getTxt())) {

                Predicate pId = cb.equal(root.get(Productos_.id), Long.parseLong(psf.getTxt()));
                Predicate pCodigo = cb.like(root.get(Productos_.codigoPropio), psf.getTxt());

                p = appendOrPredicate(cb, p, pId);
                p = appendOrPredicate(cb, p, pCodigo);

            } else {

                for (String s : psf.getTxt().toUpperCase().split(WORDS)) {
                    Predicate pTxt = null;
                    String likeS = String.format(LIKE, s);

                    Predicate p1 = cb.like(root.get(Productos_.descripcion), likeS);
                    Predicate p2 = cb.like(root.get(Productos_.idRubro).get(ProductosRubros_.nombreRubro), likeS);
                    Predicate p3 = cb.like(root.get(Productos_.idSubRubro).get(ProductosSubRubros_.nombreSubRubro), likeS);
                    Predicate p4 = cb.like(root.get(Productos_.idMarca).get(ProductosMarcas_.nombreMarca), likeS);
                    Predicate pCod = cb.like(root.get(Productos_.codigoPropio), likeS);

                    pTxt = appendOrPredicate(cb, pTxt, pCod);
                    pTxt = appendOrPredicate(cb, pTxt, p1);
                    pTxt = appendOrPredicate(cb, pTxt, p2);
                    pTxt = appendOrPredicate(cb, pTxt, p3);
                    pTxt = appendOrPredicate(cb, pTxt, p4);

                    p = appendAndPredicate(cb, p, pTxt);
                }
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
        if (psf.getActivo() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.activo), psf.getActivo());
            p = appendAndPredicate(cb, p, p1);
        }
        //TODO para cuando se implemente el stock por depósito
//        if (psf.getConStockEnDeposito() != null) {
//            Predicate p1 = cb.isTrue(root.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.controlStock));
//
//            p = appendAndPredicate(cb, p, p1);
//        }
        if (psf.getIdTipoProveeduria() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idTipoProveeduria), psf.getIdTipoProveeduria());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getIdMarca() != null) {
            Predicate p1 = cb.equal(root.get(Productos_.idMarca), psf.getIdMarca());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getConStock() != null) {
            //Subquery de existencias en depósitos

            Predicate pstk = null;
            if (psf.getConStock()) {
                Subquery<Long> subQStk = cb.createQuery().subquery(Long.class);
                Root<ProductoXDeposito> fromSubQ = subQStk.from(ProductoXDeposito.class);

                subQStk.select(fromSubQ.get(ProductoXDeposito_.id));
                Predicate ps1 = cb.gt(fromSubQ.get(ProductoXDeposito_.stock), 0);
                Predicate ps2 = cb.equal(fromSubQ.get(ProductoXDeposito_.producto), root);
                subQStk.where(cb.and(ps1, ps2));

                Predicate p1 = cb.exists(subQStk);
                Predicate p2 = cb.isFalse(root.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.controlStock));
                pstk = appendOrPredicate(cb, p1, p2);

            }
            p = appendAndPredicate(cb, p, pstk);
        }

        //TODO check that this is working properly
        if (psf.getStockDebajoMinimo() != null) {
            Predicate pstk = null;
            if (psf.getStockDebajoMinimo()) {
                Subquery<BigDecimal> subQStk = cb.createQuery().subquery(BigDecimal.class);
                Root<ProductoXDeposito> fromSubQ = subQStk.from(ProductoXDeposito.class);

                subQStk.select(cb.sum(fromSubQ.get(ProductoXDeposito_.stock)));
                //Predicate ps1 = cb.lessThanOrEqualTo(cb.sum(fromSubQ.get(ProductoXDeposito_.stock)), root.get(Productos_.stockMinimo));
                Predicate ps2 = cb.equal(fromSubQ.get(ProductoXDeposito_.producto), root);
                //subQStk.where(cb.and(ps1, ps2));
                subQStk.where(cb.and(ps2));

                Predicate p1 = cb.lessThanOrEqualTo(cb.sum(fromSubQ.get(ProductoXDeposito_.stock)), root.get(Productos_.stockMinimo));
                ;
                //Predicate p2 = cb.isFalse(root.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.controlStock));
                //pstk = appendOrPredicate(cb, p1, p2);
                pstk = p1;

            }
            p = appendAndPredicate(cb, p, pstk);
        }
        return p;

    }

}
