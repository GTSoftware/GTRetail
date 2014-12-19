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

import ar.com.gtsoftware.model.FiscalLetrasComprobantes;
import ar.com.gtsoftware.model.FiscalResponsabilidadesIva;
import ar.com.gtsoftware.search.AbstractSearchFilter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rodrigo
 */
@Stateless
public class FiscalLetrasComprobantesFacade extends AbstractFacade<FiscalLetrasComprobantes> {
    @PersistenceContext(unitName = "ar.com.gtsoftware_GTRetail-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiscalLetrasComprobantesFacade() {
        super(FiscalLetrasComprobantes.class);
    }
    
    public FiscalLetrasComprobantes findLetraComprobante(int idResponsabildiadEmisor, FiscalResponsabilidadesIva ivaReceptor) {
        Query q = getEntityManager().createQuery("SELECT l FROM FiscalLetrasComprobantes l WHERE l.fiscalLetrasComprobantesPK.idResoponsabildiadIvaEmisor = :ivaEmisor AND l.fiscalLetrasComprobantesPK.idResoponsabildiadIvaReceptor = :ivaReceptor");
        q.setParameter("ivaEmisor", idResponsabildiadEmisor);
        q.setParameter("ivaReceptor", ivaReceptor.getIdResoponsabildiadIva());
        List<FiscalLetrasComprobantes> lista = q.getResultList();
        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }

    @Override
    public Predicate createWhereFromSearchFilter(AbstractSearchFilter sf, CriteriaBuilder cb, Root<FiscalLetrasComprobantes> root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FiscalLetrasComprobantes> findBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countBySearchFilter(AbstractSearchFilter sf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createOrEdit(FiscalLetrasComprobantes entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
