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

import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.Personas_;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas, PersonasSearchFilter> {

    private static final String LIKE_FORMAT = "%%%s%%";
    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }

    @Override
    protected Predicate createWhereFromSearchFilter(PersonasSearchFilter psf, CriteriaBuilder cb, Root<Personas> persona) {

        Predicate p = null;
        if (psf.getIdPersona() != null) {
            p = cb.equal(persona.get(Personas_.id), psf.getIdPersona());
        }
        if (StringUtils.isNotEmpty(psf.getTxt())) {
            String s = psf.getTxt().toUpperCase();
            Predicate p1 = cb.like(persona.get(Personas_.razonSocial), String.format(LIKE_FORMAT, s));
            Predicate p2 = cb.like(persona.get(Personas_.apellidos), String.format(LIKE_FORMAT, s));
            Predicate p3 = cb.like(persona.get(Personas_.nombres), String.format(LIKE_FORMAT, s));
            Predicate p4 = cb.like(persona.get(Personas_.nombreFantasia), String.format(LIKE_FORMAT, s));
            Predicate p5 = cb.like(persona.get(Personas_.documento), String.format(LIKE_FORMAT, s));

            if (p == null) {
                p = cb.or(p1, p2, p3, p4, p5);
            } else {
                p = cb.or(p, p1, p2, p3, p4, p5);
            }
        }
        if (psf.getDocumento() != null && psf.getIdTipoDocumento() != null) {
            Predicate p1 = cb.like(persona.get(Personas_.documento), String.format(LIKE_FORMAT, psf.getDocumento()));
            p = appendAndPredicate(cb, p, p1);
            p1 = cb.equal(persona.get(Personas_.idTipoDocumento), psf.getIdTipoDocumento());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getCliente() != null) {
            Predicate p1 = cb.equal(persona.get(Personas_.cliente), psf.getCliente());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getProveedor() != null) {
            Predicate p1 = cb.equal(persona.get(Personas_.proveedor), psf.getProveedor());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.getActivo() != null) {
            Predicate p1 = cb.equal(persona.get(Personas_.activo), psf.getActivo());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;

    }

    /**
     * Devuelve true si ya existe una persona con ese tipo y número de documento y no es la persona pasada por parámetro
     *
     * @param persona
     * @return
     */
    public boolean existePersonaRepetida(Personas persona) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<Personas> rt = cq.from(Personas.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Predicate p = cb.notEqual(rt.get(Personas_.id), persona.getId());
        p = appendAndPredicate(cb, p, cb.equal(rt.get(Personas_.idTipoDocumento), persona.getIdTipoDocumento()));
        p = appendAndPredicate(cb, p, cb.equal(rt.get(Personas_.documento), persona.getDocumento()));
        cq.where(p);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()) > 0L;
    }

}
