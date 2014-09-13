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
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
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
public class PersonasFacade extends AbstractFacade<Personas> {

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
    public List<Personas> findBySearchFilter(AbstractSearchFilter psf) {
        if (psf.hasFilter()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Personas> cq = cb.createQuery(Personas.class);
            Root<Personas> persona = cq.from(Personas.class);
            cq.select(persona);
            Predicate p = createWhereFromSearchFilter(psf, cb, persona);
            cq.where(p);
            TypedQuery<Personas> q = em.createQuery(cq);

            List<Personas> personasList = q.getResultList();
            return personasList;
        }
        return new ArrayList<>();
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Personas> cq = cb.createQuery(Personas.class);
        Root<Personas> persona = cq.from(Personas.class);
        cq.select(persona);
        Predicate p = createWhereFromSearchFilter(sf, cb, persona);
        cq.where(p);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<Personas> persona) {

        PersonasSearchFilter psf = (PersonasSearchFilter) sf;
        Predicate p = null;
        if (psf.getIdPersona() != null) {
            p = cb.equal(persona.get(Personas_.id), psf.getIdPersona());
        }
        if (psf.getTxt() != null && !psf.getTxt().isEmpty()) {
            for (String s : psf.getTxt().toUpperCase().split(" ")) {

                Predicate p1 = cb.like(persona.get(Personas_.razonSocial), String.format("%%%s%%", s));
                Predicate p2 = cb.like(persona.get(Personas_.apellidos), String.format("%%%s%%", s));
                Predicate p3 = cb.like(persona.get(Personas_.nombres), String.format("%%%s%%", s));
                Predicate p4 = cb.like(persona.get(Personas_.nombreFantasia), String.format("%%%s%%", s));
                Predicate p5 = cb.like(persona.get(Personas_.documento), String.format("%%%s%%", s));

                if (p == null) {
                    p = cb.or(p1, p2, p3, p4, p5);
                } else {
                    p = cb.or(p, p1, p2, p3, p4, p5);
                }
            }
        }
        if (psf.getDocumento() != null && psf.getIdTipoDocumento() != null) {
            Predicate p1 = cb.like(persona.get(Personas_.documento), String.format("%%%s%%", psf.getDocumento()));
            p = appendAndPredicate(cb, p, p1);
            p1 = cb.equal(persona.get(Personas_.idTipoDocumento), psf.getIdTipoDocumento());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.isCliente() != null) {
            Predicate p1 = cb.equal(persona.get(Personas_.cliente), psf.isCliente());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.isProveedor() != null) {
            Predicate p1 = cb.equal(persona.get(Personas_.proveedor), psf.isProveedor());
            p = appendAndPredicate(cb, p, p1);
        }
        if (psf.isActivo() != null) {
            Predicate p1 = cb.equal(persona.get(Personas_.activo), psf.isActivo());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;

    }

}
