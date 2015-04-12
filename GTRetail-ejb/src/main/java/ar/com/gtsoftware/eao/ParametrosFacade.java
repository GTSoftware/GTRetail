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

import ar.com.gtsoftware.model.Parametros;
import ar.com.gtsoftware.model.Parametros_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
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
 * @author rodrigo
 */
@Stateless
public class ParametrosFacade extends AbstractFacade<Parametros> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrosFacade() {
        super(Parametros.class);
    }

    /**
     * Busca el parámetro con exactamente el nombre especificado
     * @param nombre
     * @return 
     */
    public Parametros findParametroByName(String nombre) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Parametros> cq = cb.createQuery(Parametros.class);
        Root<Parametros> parametro = cq.from(Parametros.class);
        cq.select(parametro);
        Predicate p = cb.equal(parametro.get(Parametros_.nombreParametro), nombre);
        cq.where(p);
        TypedQuery<Parametros> q = em.createQuery(cq);

        List<Parametros> paramList = q.getResultList();
        if (!paramList.isEmpty()) {
            return paramList.get(0);
        }
        return null;
    }

    /**
     * Busca los parámetros que coincidan con txt
     * @param txt
     * @return 
     */
    public List<Parametros> findParametros(String txt) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Parametros> cq = cb.createQuery(Parametros.class);
        Root<Parametros> parametro = cq.from(Parametros.class);
        cq.select(parametro);
        Predicate p1 = cb.like(parametro.get(Parametros_.nombreParametro), String.format("%%%s%%", txt));
        Predicate p2 = cb.like(parametro.get(Parametros_.descripcionParametro), String.format("%%%s%%", txt));
        cq.where(cb.or(p1, p2));
        TypedQuery<Parametros> q = em.createQuery(cq);

        List<Parametros> paramList = q.getResultList();
        if (!paramList.isEmpty()) {
            return paramList;
        }
        return new ArrayList<>();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<Parametros> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Parametros> findAllBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(Parametros entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
