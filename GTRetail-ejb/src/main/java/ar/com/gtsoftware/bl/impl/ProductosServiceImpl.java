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

import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.dto.model.ProductosDto;
import ar.com.gtsoftware.eao.ProductoXDepositoFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosPreciosFacade;
import ar.com.gtsoftware.mappers.ProductosMapper;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosPrecios;
import ar.com.gtsoftware.search.ProductoXDepositoSearchFilter;
import ar.com.gtsoftware.search.ProductosPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Stateless
public class ProductosServiceImpl
        extends AbstractBasicEntityService<ProductosDto, ProductosSearchFilter, Productos>
        implements ProductosService {

    @EJB
    private ProductosFacade facade;

    @EJB
    private ProductosPreciosFacade preciosFacade;
    @EJB
    private ProductoXDepositoFacade stockFacade;

    @Inject
    private ProductosMapper mapper;

    @Override
    protected ProductosFacade getFacade() {
        return facade;
    }

    @Override
    protected ProductosMapper getMapper() {
        return mapper;
    }

    @Override
    public List<ProductosDto> findBySearchFilter(@NotNull ProductosSearchFilter sf, int firstResult, int maxResults) {
        List<ProductosDto> productosDtos = super.findBySearchFilter(sf, firstResult, maxResults);
        establecerPrecioYStock(productosDtos, sf);
        return productosDtos;

    }

    private void establecerPrecioYStock(List<ProductosDto> productosDtos, @NotNull ProductosSearchFilter sf) {
        if (sf.getIdListaPrecio() != null) {
            ProductosPreciosSearchFilter preciosSF = ProductosPreciosSearchFilter.builder().idListaPrecios(sf.getIdListaPrecio())
                    .build();
            ProductoXDepositoSearchFilter stockSf = new ProductoXDepositoSearchFilter();
            for (ProductosDto prod : productosDtos) {
                stockSf.setIdProducto(prod.getId());
                preciosSF.setIdProducto(prod.getId());

                ProductosPrecios productosPrecios = preciosFacade.findFirstBySearchFilter(preciosSF);
                BigDecimal precioVenta = productosPrecios == null ? null : productosPrecios.getPrecio();
                prod.setPrecioVenta(precioVenta);

                BigDecimal stockTotal = stockFacade.getStockBySearchFilter(stockSf);
                prod.setStockActual(stockTotal);
            }
        }
    }

    @Override
    public ProductosDto findFirstBySearchFilter(@NotNull ProductosSearchFilter sf) {
        ProductosDto productosDto = super.findFirstBySearchFilter(sf);
        establecerPrecioYStock(Collections.singletonList(productosDto), sf);
        return productosDto;
    }
}
