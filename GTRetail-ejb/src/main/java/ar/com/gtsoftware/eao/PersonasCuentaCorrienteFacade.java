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

import ar.com.gtsoftware.model.PersonasCuentaCorriente;
import ar.com.gtsoftware.model.PersonasCuentaCorriente_;
import ar.com.gtsoftware.model.Personas_;
import ar.com.gtsoftware.search.PersonasCuentaCorrienteSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

/**
 * @author rodrigo
 */
@Stateless
public class PersonasCuentaCorrienteFacade extends AbstractFacade<PersonasCuentaCorriente, PersonasCuentaCorrienteSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PersonasCuentaCorrienteFacade() {
        super(PersonasCuentaCorriente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BigDecimal getSaldoPersona(Long idPersona) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
        Root<PersonasCuentaCorriente> cuenta = cq.from(PersonasCuentaCorriente.class);
        cq.select(cb.sum(cuenta.get(PersonasCuentaCorriente_.importeMovimiento)).alias("SUM"));
        Predicate p = cb.equal(cuenta.get(PersonasCuentaCorriente_.idPersona).get(Personas_.id), idPersona);
        cq.where(p);
        BigDecimal result = em.createQuery(cq).getSingleResult();
        if (result == null) {
            return BigDecimal.ZERO;
        }
        return result;
    }

    @Override
    public Predicate createWhereFromSearchFilter(PersonasCuentaCorrienteSearchFilter sf, CriteriaBuilder cb,
                                                 Root<PersonasCuentaCorriente> root) {
        Predicate p = null;
        if (sf.getIdPersona() != null) {
            Predicate p1 = cb.equal(root.get(PersonasCuentaCorriente_.idPersona).get(Personas_.id), sf.getIdPersona());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;
    }

}
