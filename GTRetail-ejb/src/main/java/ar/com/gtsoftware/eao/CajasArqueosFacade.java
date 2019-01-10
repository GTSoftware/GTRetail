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

import ar.com.gtsoftware.model.CajasArqueos;
import ar.com.gtsoftware.model.CajasArqueos_;
import ar.com.gtsoftware.model.Sucursales_;
import ar.com.gtsoftware.model.Usuarios_;
import ar.com.gtsoftware.search.CajasArqueosSearchFilter;

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
public class CajasArqueosFacade extends AbstractFacade<CajasArqueos, CajasArqueosSearchFilter> {

    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CajasArqueosFacade() {
        super(CajasArqueos.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(CajasArqueosSearchFilter psf, CriteriaBuilder cb, Root<CajasArqueos> root) {
        Predicate p = null;
        if (psf.hasValidFechasArqueo()) {
            Predicate p1 = cb.between(root.get(CajasArqueos_.fechaArqueo), psf.getFechaArqueoDesde(), psf.getFechaArqueoHasta());
            p = appendAndPredicate(cb, p1, p);
        }

        if (psf.getControlado() != null) {
            Predicate p1;
            if (psf.getControlado()) {
                p1 = cb.isNotNull(root.get(CajasArqueos_.idUsuarioControl));
            } else {
                p1 = cb.isNull(root.get(CajasArqueos_.idUsuarioControl));
            }

            p = appendAndPredicate(cb, p1, p);
        }

        if (psf.getIdSucursal() != null) {
            Predicate p1 = cb.equal(root.get(CajasArqueos_.idSucursal).get(Sucursales_.id), psf.getIdSucursal());
            p = appendAndPredicate(cb, p1, p);
        }

        if (psf.getIdUsuario() != null) {
            Predicate p1 = cb.equal(root.get(CajasArqueos_.idUsuario).get(Usuarios_.id), psf.getIdUsuario());
            p = appendAndPredicate(cb, p1, p);
        }

        return p;

    }

}
