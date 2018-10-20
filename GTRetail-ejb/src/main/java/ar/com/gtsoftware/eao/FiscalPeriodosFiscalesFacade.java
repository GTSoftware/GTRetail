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

import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales_;
import ar.com.gtsoftware.search.FiscalPeriodosFiscalesSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
public class FiscalPeriodosFiscalesFacade extends AbstractFacade<FiscalPeriodosFiscales, FiscalPeriodosFiscalesSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalPeriodosFiscalesFacade() {
        super(FiscalPeriodosFiscales.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(FiscalPeriodosFiscalesSearchFilter psf, CriteriaBuilder cb, Root<FiscalPeriodosFiscales> root) {

        Predicate p = null;
        if (psf.getVigente() != null) {

            Predicate p3 = cb.between(cb.currentTimestamp(),
                    root.<Date>get(FiscalPeriodosFiscales_.fechaInicioPeriodo), root.<Date>get(FiscalPeriodosFiscales_.fechaFinPeriodo));
            if (psf.getVigente()) {
                p = appendAndPredicate(cb, p, p3);
            } else {
                p = appendAndPredicate(cb, p, cb.not(p3));
            }
        }

        if (psf.getCerrado() != null) {
            Predicate p1 = cb.equal(root.get(FiscalPeriodosFiscales_.periodoCerrado), psf.getCerrado());
            p = appendAndPredicate(cb, p, p1);
        }

        if (psf.getFechaActual() != null) {
            Predicate pDesde = cb.lessThanOrEqualTo(root.get(FiscalPeriodosFiscales_.fechaInicioPeriodo), psf.getFechaActual());
            Predicate pHasta = cb.greaterThanOrEqualTo(root.get(FiscalPeriodosFiscales_.fechaFinPeriodo), psf.getFechaActual());
            p = appendAndPredicate(cb, p, pDesde);
            p = appendAndPredicate(cb, p, pHasta);
        }
        return p;
    }

}
