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

import ar.com.gtsoftware.model.GTEntity;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @param <T>
 */
public abstract class AbstractFacade<T extends GTEntity> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected abstract Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<T> root);

    public List<T> findBySearchFilter(AbstractSearchFilter sf) {
        return findBySearchFilter(sf, 0, Integer.MAX_VALUE);
    }

    public List<T> findBySearchFilter(AbstractSearchFilter sf, int firstResult, int maxResults) {
        if (sf.hasFilter()) {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            cq.select(root);
            Predicate p = createWhereFromSearchFilter(sf, cb, root);
            cq.where(p);
            if (sf.hasOrderFields()) {
                cq.orderBy(createOrderFromSearchFilter(sf, root, cb));
            }
            TypedQuery<T> q = getEntityManager().createQuery(cq);
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
            List<T> resultList = q.getResultList();
            return resultList;
        }
        return new ArrayList<>();
    }

    protected List<Order> createOrderFromSearchFilter(AbstractSearchFilter sf, Root<T> root, CriteriaBuilder cb) {
        List<Order> orders = new ArrayList<>();
        for (SortField sof : sf.getSortFields()) {
            Order ord;
            Path<Object> path;

            String[] fields = sof.getFieldName().split("\\.");
            if (fields.length != 0) {
                path = root.get(fields[0]);
                for (int i = 1; i < fields.length; i++) {
                    path = path.get(fields[i]);
                }
            } else {
                path = root.get(sof.getFieldName());
            }
            if (sof.isAscending()) {

                ord = cb.asc(path);
            } else {
                ord = cb.desc(path);
            }
            orders.add(ord);
        }
        return orders;
    }

    public int countBySearchFilter(AbstractSearchFilter sf) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Predicate p = createWhereFromSearchFilter(sf, getEntityManager().getCriteriaBuilder(), rt);
        cq.where(p);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Retorna el OR entre los predicados pasados como parámetro
     *
     * @param cb
     * @param p1
     * @param p2
     * @return un predicado que une a p1 y p2 mediante un OR
     */
    protected Predicate appendOrPredicate(CriteriaBuilder cb, Predicate p1, Predicate p2) {
        if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }
        return cb.or(p1, p2);
    }

    /**
     * Retorna el AND entre los predicados pasados como parámetro
     *
     * @param cb
     * @param p1
     * @param p2
     * @return un predicado que une a p1 y p2 mediante un AND
     */
    protected Predicate appendAndPredicate(CriteriaBuilder cb, Predicate p1, Predicate p2) {
        if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }
        return cb.and(p1, p2);
    }

    /**
     * Crea o edita la entidad pasada como parámetro según sea necesario
     *
     * @param entity
     */
    public void createOrEdit(T entity) {
        if (entity == null) {
            return;
        }
        if (entity.isNew()) {
            create(entity);
        } else {
            edit(entity);
        }
    }

}
