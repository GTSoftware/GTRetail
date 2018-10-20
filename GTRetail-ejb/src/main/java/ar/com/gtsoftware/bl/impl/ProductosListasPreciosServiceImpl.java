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

import ar.com.gtsoftware.bl.NegocioFormasPagoService;
import ar.com.gtsoftware.bl.ProductosListasPreciosService;
import ar.com.gtsoftware.dto.model.NegocioFormasPagoDto;
import ar.com.gtsoftware.dto.model.ProductosListasPreciosDto;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.eao.ProductosListasPreciosFacade;
import ar.com.gtsoftware.mappers.GenericMapper;
import ar.com.gtsoftware.mappers.NegocioFormasPagoMapper;
import ar.com.gtsoftware.mappers.ProductosListasPreciosMapper;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.ProductosListasPreciosSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProductosListasPreciosServiceImpl extends AbstractBasicEntityService<ProductosListasPreciosDto,
        ProductosListasPreciosSearchFilter, ProductosListasPrecios>
        implements ProductosListasPreciosService {

    @EJB
    private ProductosListasPreciosFacade facade;

    @Inject
    private ProductosListasPreciosMapper mapper;

    @Override
    public ProductosListasPreciosFacade getFacade() {
        return facade;
    }

    @Override
    public ProductosListasPreciosMapper getMapper() {
        return mapper;
    }
}
