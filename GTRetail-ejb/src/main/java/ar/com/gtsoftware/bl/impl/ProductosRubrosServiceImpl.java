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

import ar.com.gtsoftware.bl.ProductosRubrosService;
import ar.com.gtsoftware.dto.model.ProductosRubrosDto;
import ar.com.gtsoftware.eao.ProductosRubrosFacade;
import ar.com.gtsoftware.mappers.ProductosRubrosMapper;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.search.RubrosSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ProductosRubrosServiceImpl
        extends AbstractBasicEntityService<ProductosRubrosDto, RubrosSearchFilter, ProductosRubros>
        implements ProductosRubrosService {

    @EJB
    private ProductosRubrosFacade facade;

    @Inject
    private ProductosRubrosMapper mapper;

    @Override
    protected ProductosRubrosFacade getFacade() {
        return facade;
    }

    @Override
    protected ProductosRubrosMapper getMapper() {
        return mapper;
    }
}
