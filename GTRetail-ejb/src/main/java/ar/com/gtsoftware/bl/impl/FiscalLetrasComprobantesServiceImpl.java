/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.FiscalLetrasComprobantesService;
import ar.com.gtsoftware.eao.FiscalLetrasComprobantesFacade;
import ar.com.gtsoftware.model.FiscalLetrasComprobantes;
import ar.com.gtsoftware.search.FiscalLetrasComprobantesSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

import static java.util.Objects.isNull;

@Stateless
public class FiscalLetrasComprobantesServiceImpl implements FiscalLetrasComprobantesService {

    @EJB
    private FiscalLetrasComprobantesFacade facade;

    @Override
    public String obtenerLetra(@NotNull Long idResponsabilidadIvaEmisor, @NotNull Long idResponsabilidadIvaReceptor) {
        FiscalLetrasComprobantesSearchFilter sf = FiscalLetrasComprobantesSearchFilter.builder()
                .idRespIvaEmisor(idResponsabilidadIvaEmisor)
                .idRespIvaReceptor(idResponsabilidadIvaReceptor).build();
        FiscalLetrasComprobantes letra = facade.findFirstBySearchFilter(sf);

        return isNull(letra) ? null : letra.getLetraComprobante();

    }
}
