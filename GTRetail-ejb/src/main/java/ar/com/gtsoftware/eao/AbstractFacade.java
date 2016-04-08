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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @param <T>
 */
public abstract class AbstractFacade<T extends GTEntity> {

    private final Class<T> entityClass;

    private static final Logger LOG = Logger.getLogger(AbstractFacade.class.getName());

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        constraintViolationsDetected(entity);
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    public void edit(T entity) {
        constraintViolationsDetected(entity);
        getEntityManager().merge(entity);
        getEntityManager().flush();

    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
        getEntityManager().flush();

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

    public List<T> findAllBySearchFilter(AbstractSearchFilter sf) {
        return findBySearchFilter(sf, 0, countBySearchFilter(sf));
    }

    public List<T> findBySearchFilter(AbstractSearchFilter sf, int firstResult, int maxResults) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        if (sf != null) {
            if (sf.hasFilter()) {
                Predicate p = createWhereFromSearchFilter(sf, cb, root);
                cq.where(p);
            }
            if (sf.hasOrderFields()) {
                cq.orderBy(createOrderFromSearchFilter(sf, root, cb));
            }
        }
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        List<T> resultList = q.getResultList();
        return resultList;

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

    /**
     * Cuenta los elementos encontrados por el filtro de búsqueda.
     *
     * @param sf el filtro de búsqueda
     * @return la cantidad de elementos encontrados
     */
    public int countBySearchFilter(AbstractSearchFilter sf) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        if (sf.hasFilter()) {
            Predicate p = createWhereFromSearchFilter(sf, getEntityManager().getCriteriaBuilder(), rt);
            cq.where(p);
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Devuelve el primer elemento encontrado por el filtro de búsqueda.
     *
     * @param sf el filtro de búsuqeda
     * @return el primer elemento o null si no encuentra ninguno.
     */
    public T findFirstBySearchFilter(AbstractSearchFilter sf) {
        List<T> results = findBySearchFilter(sf, 0, 1);
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    /**
     * Retorna el OR entre los predicados pasados como parámetro
     *
     * @param cb el criteria builder
     * @param p1 el predicado 1
     * @param p2 el predicado 2
     * @return un predicado que une a p1 y p2 mediante un OR
     */
    protected Predicate appendOrPredicate(CriteriaBuilder cb, Predicate p1, Predicate p2) {
        validateAppendArguments(cb, p1, p2);
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
     * @param cb el criteria builder
     * @param p1 el predicado 1
     * @param p2 el predicado 2
     * @return un predicado que une a p1 y p2 mediante un AND
     */
    protected Predicate appendAndPredicate(CriteriaBuilder cb, Predicate p1, Predicate p2) {
        validateAppendArguments(cb, p1, p2);
        if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }
        return cb.and(p1, p2);
    }

    /**
     * Método que valida los parámetros que reciben los métodos de append.
     *
     * @param cb el criteria builder
     * @param p1 el predicado 1
     * @param p2 el predicado 2
     */
    private void validateAppendArguments(CriteriaBuilder cb, Predicate p1, Predicate p2) {
        if (cb == null) {
            throw new IllegalArgumentException("El CriteriaBuilder no puede ser null.");
        }
        if (p1 == null && p2 == null) {
            throw new IllegalArgumentException("Al menos unos de los predicados debe estar establecido.");
        }
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

    private boolean constraintViolationsDetected(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                LOG.log(Level.SEVERE, "{0}.{1} {2}", new Object[]{cv.getRootBeanClass().getName(), cv.getPropertyPath(), cv.getMessage()});
            }
            return true;
        } else {
            return false;
        }
    }
}
