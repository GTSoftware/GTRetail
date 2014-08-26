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
import ar.com.gtsoftware.search.ProductosSearchFilter;
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
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }

    public List<Productos> findBySearchFilter(ProductosSearchFilter psf) {
        if (psf.hasFilter()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Productos> cq = cb.createQuery(Productos.class);
            Root<Productos> producto = cq.from(Productos.class);
            cq.select(producto);
            Predicate p = null;
            if (psf.getIdProducto() != null) {
                p = cb.equal(producto.get(Productos_.id), psf.getIdProducto());
            }
            if (psf.getCodigoPropio() != null && !psf.getCodigoPropio().isEmpty()) {
                p = cb.equal(producto.get(Productos_.codigoPropio), psf.getCodigoPropio());
            }
            if (psf.getTxt() != null && !psf.getTxt().isEmpty()) {
                for (String s : psf.getTxt().toUpperCase().split(" ")) {

                    Predicate p1 = cb.like(producto.get(Productos_.descripcion), String.format("%%%s%%", s));
                    Predicate p2 = cb.like(producto.get(Productos_.idRubro).get(ProductosRubros_.nombreRubro), String.format("%%%s%%", s));
                    Predicate p3 = cb.like(producto.get(Productos_.idSubRubro).get(ProductosSubRubros_.nombreSubRubro), String.format("%%%s%%", s));
                    Predicate p4 = cb.like(producto.get(Productos_.codigoPropio), String.format("%%%s%%", s));
                    Predicate p5 = cb.like(producto.get(Productos_.ubicacion), String.format("%%%s%%", s));

                    if (p == null) {
                        p = cb.or(p1, p2, p3, p4, p5);
                    } else {
                        p = cb.or(p, p1, p2, p3, p4, p5);
                    }
                }
            }
            if (psf.getIdProveedorHabitual() != null) {
                Predicate p1 = cb.equal(producto.get(Productos_.idProveedorHabitual), psf.getIdProveedorHabitual());
                p = appendAndPredicate(cb, p, p1);
            }
            if (psf.getPuedeComprarse() != null) {
                Predicate p1 = cb.equal(producto.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.puedeComprarse), psf.getPuedeComprarse());
                p = appendAndPredicate(cb, p, p1);
            }
            if (psf.getPuedeVenderse() != null) {
                Predicate p1 = cb.equal(producto.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.puedeVenderse), psf.getPuedeVenderse());
                p = appendAndPredicate(cb, p, p1);
            }
            if (psf.getIdRubro() != null) {
                Predicate p1 = cb.equal(producto.get(Productos_.idRubro), psf.getIdRubro());
                p = appendAndPredicate(cb, p, p1);
            }
            if (psf.getIdSubRubro() != null) {
                Predicate p1 = cb.equal(producto.get(Productos_.idSubRubro), psf.getIdSubRubro());
                p = appendAndPredicate(cb, p, p1);
            }
            if (psf.isActivo() != null) {
                Predicate p1 = cb.equal(producto.get(Productos_.activo), psf.isActivo());
                p = appendAndPredicate(cb, p, p1);
            }
            if (psf.getConStockEnDeposito() != null) {
                Predicate p1 = cb.equal(producto.get(Productos_.idTipoProveeduria).get(ProductosTiposProveeduria_.controlStock), Boolean.FALSE);
                //TODO Or existe que ese producto tiene stock > 0 en el deposito del filtro
            }

            cq.where(p);
            TypedQuery<Productos> q = em.createQuery(cq);

            List<Productos> productosList = q.getResultList();
            return productosList;
        }
        return new ArrayList<>();
    }

}
