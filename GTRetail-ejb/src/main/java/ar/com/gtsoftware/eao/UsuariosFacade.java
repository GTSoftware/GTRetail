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

import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.model.Usuarios_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.UsuariosSearchFilter;
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
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public List<Usuarios> findBySearchFilter(UsuariosSearchFilter usuariosSearchFilter) {
        if (usuariosSearchFilter.hasFilter()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
            Root<Usuarios> usuario = cq.from(Usuarios.class);
            cq.select(usuario);
            Predicate p = null;
            if (usuariosSearchFilter.getIdUsuario() != null) {
                p = cb.equal(usuario.get(Usuarios_.id), usuariosSearchFilter.getIdUsuario());
            }
            if (usuariosSearchFilter.getNombreUsuario() != null) {
                Predicate p1 = cb.like(usuario.get(Usuarios_.nombreUsuario), String.format("%%%s%%", usuariosSearchFilter.getNombreUsuario().toLowerCase()));
                p = appendOrPredicate(cb, p, p1);
            }
            if (usuariosSearchFilter.getLogin() != null) {
                Predicate p1 = cb.like(usuario.get(Usuarios_.login), String.format("%%%s%%", usuariosSearchFilter.getLogin()));
                p = appendOrPredicate(cb, p, p1);
            }
            if (usuariosSearchFilter.getPassword() != null) {
                Predicate p1 = cb.equal(usuario.get(Usuarios_.password), usuariosSearchFilter.getPassword());
                p = appendOrPredicate(cb, p, p1);
            }
            if (usuariosSearchFilter.hasTextFilter()) {
                for (String s : usuariosSearchFilter.getText().toUpperCase().split(" ")) {

                    Predicate p1 = cb.like(cb.upper(usuario.get(Usuarios_.login)), String.format("%%%s%%", s));
                    Predicate p2 = cb.like(cb.upper(usuario.get(Usuarios_.nombreUsuario)), String.format("%%%s%%", s));

                    p = appendOrPredicate(cb, p, p1);
                    p = appendOrPredicate(cb, p, p2);
                }
            }
            cq.where(p);
            TypedQuery<Usuarios> q = em.createQuery(cq);

            List<Usuarios> usuariosList = q.getResultList();
            return usuariosList;
        }
        return new ArrayList<>();
    }

    /**
     * Retorna el usuario cuyo login coincida con el que se pasa por parámetro
     *
     * @param login
     * @return
     */
    public Usuarios findByLogIn(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuario = cq.from(Usuarios.class);
        cq.select(usuario);
        Predicate p = cb.equal(usuario.get(Usuarios_.login), login);
        cq.where(p);
        TypedQuery<Usuarios> q = em.createQuery(cq);
        q.setMaxResults(1);
        return q.getSingleResult();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<Usuarios> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuarios> findAllBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(Usuarios entity) {
        Usuarios usuarioExistente = find(entity.getLogin());

        if (usuarioExistente != null) {
            edit(entity);
        } else {
            create(entity);
        }
        getEntityManager().flush();
    }
}
