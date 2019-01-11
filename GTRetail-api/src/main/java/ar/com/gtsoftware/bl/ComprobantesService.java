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

package ar.com.gtsoftware.bl;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.ComprobantesLineasDto;
import ar.com.gtsoftware.search.ComprobantesSearchFilter;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Remote
public interface ComprobantesService
        extends EntityService<ComprobantesDto, ComprobantesSearchFilter> {


    BigDecimal calcularTotalVentas(@NotNull ComprobantesSearchFilter sf);

    void anularVenta(@NotNull Long idComprobante) throws ServiceException;

}
