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
import ar.com.gtsoftware.model.PersonasCuentaCorriente;
import ar.com.gtsoftware.model.PersonasCuentaCorriente_;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import java.math.BigDecimal;
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
public class PersonasCuentaCorrienteFacade extends AbstractFacade<PersonasCuentaCorriente> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasCuentaCorrienteFacade() {
        super(PersonasCuentaCorriente.class);
    }

    public BigDecimal getSaldoPersona(Personas persona) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<PersonasCuentaCorriente> cuenta = cq.from(PersonasCuentaCorriente.class);
        cq.select(cb.sum(cuenta.get(PersonasCuentaCorriente_.importeMovimiento)).alias("SUM"));
        Predicate p = cb.equal(cuenta.get(PersonasCuentaCorriente_.idPersona), persona.getId());
        cq.where(p);
        BigDecimal result = em.createQuery(cq).getSingleResult();
        if (result == null) {
            return BigDecimal.ZERO;
        }
        return result;
    }

    public List<PersonasCuentaCorriente> getUltimosMovimientos(Personas persona) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PersonasCuentaCorriente> cq = cb.createQuery(PersonasCuentaCorriente.class);
        Root<PersonasCuentaCorriente> cuenta = cq.from(PersonasCuentaCorriente.class);
        cq.select(cuenta);
        Predicate p = cb.equal(cuenta.get(PersonasCuentaCorriente_.idPersona), persona.getId());
        cq.where(p);
        cq.orderBy(cb.desc(cuenta.get(PersonasCuentaCorriente_.fechaMovimiento)));
        TypedQuery<PersonasCuentaCorriente> q = em.createQuery(cq);
        q.setMaxResults(100);
        return q.getResultList();
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<PersonasCuentaCorriente> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonasCuentaCorriente> findBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(PersonasCuentaCorriente entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
