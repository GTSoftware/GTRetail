/*
 * Copyright 2019 GT Software.
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

package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.bl.ProductoXDepositoService;
import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.bl.RemitoService;
import ar.com.gtsoftware.dto.model.ProductoXDepositoDto;
import ar.com.gtsoftware.dto.model.ProductosDto;
import ar.com.gtsoftware.dto.model.RemitoDto;
import ar.com.gtsoftware.search.ProductoXDepositoSearchFilter;
import ar.com.gtsoftware.search.RemitoSearchFilter;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductoInfoAdicionalBeanTest {


    public static final ProductosDto PRODUCTO_DTO = ProductosDto.builder().id(1L).build();
    private static final Date HOY = new Date();
    @Mock
    private ProductosService mockProductosService;
    @Mock
    private ProductoXDepositoService mockProductoXDepositoService;
    @Mock
    private RemitoService mockRemitoService;
    @InjectMocks
    private ProductoInfoAdicionalBean bean;


    @Before
    public void setUp() {
        initMocks(this);
        bean.setIdProducto(1L);
        when(mockProductosService.find(1L)).thenReturn(PRODUCTO_DTO);

        List<ProductoXDepositoDto> stockXDepo = Collections.singletonList(
                ProductoXDepositoDto.builder().stock(BigDecimal.ONE).build());

        when(mockProductoXDepositoService.findAllBySearchFilter(Matchers.any(ProductoXDepositoSearchFilter.class)))
                .thenReturn(stockXDepo);

    }

    @Test
    public void deberiaCargarProductoYStock() {
        ProductosDto producto = bean.getProducto();

        assertThat(producto, notNullValue());
        assertThat(bean.getStockXDepo(), notNullValue());

        verify(mockProductosService).find(1L);
        verify(mockProductoXDepositoService).findAllBySearchFilter(Matchers.any(ProductoXDepositoSearchFilter.class));
    }

    @Test
    public void deberiaMostrarFechaUltimaModificacionVaciaSinProducto() {
        bean.setProducto(null);

        String ultimaModificacion = bean.getUltimaModificacion();

        assertThat(ultimaModificacion, notNullValue());
        assertThat(ultimaModificacion, is(""));
    }

    @Test
    public void deberiaMostrarFechaUltimaModificacionDeEsteMes() {
        bean.setProducto(ProductosDto.builder()
                .fechaUltimaModificacion(HOY).build());

        String ultimaModificacion = bean.getUltimaModificacion();

        assertThat(ultimaModificacion, notNullValue());
        assertThat(ultimaModificacion, is("Este mes"));
    }

    @Test
    public void deberiaMostrarFechaUltimaModificacionDeHaceMeses() {
        bean.setProducto(ProductosDto.builder()
                .fechaUltimaModificacion(DateUtils.addMonths(HOY, -3)).build());

        String ultimaModificacion = bean.getUltimaModificacion();

        assertThat(ultimaModificacion, notNullValue());
        assertThat(ultimaModificacion, is("Hace 3 mes/es"));
    }

    @Test
    public void deberiaObtenerFechaUltimaRecepcion() {
        when(mockRemitoService.findFirstBySearchFilter(Matchers.any(RemitoSearchFilter.class)))
                .thenReturn(RemitoDto.builder().fechaAlta(HOY).build());
        bean.setProducto(PRODUCTO_DTO);

        Date fechaUltimaRecepcion = bean.getFechaUltimaRecepcion();

        assertThat(fechaUltimaRecepcion, notNullValue());
        assertThat(fechaUltimaRecepcion, is(HOY));

        verify(mockRemitoService).findFirstBySearchFilter(Matchers.any(RemitoSearchFilter.class));
    }

    @Test
    public void noDeberiaObtenerFechaUltimaRecepcionSiNoHayRemito() {
        when(mockRemitoService.findFirstBySearchFilter(Matchers.any(RemitoSearchFilter.class)))
                .thenReturn(null);
        bean.setProducto(PRODUCTO_DTO);

        Date fechaUltimaRecepcion = bean.getFechaUltimaRecepcion();

        assertThat(fechaUltimaRecepcion, nullValue());

        verify(mockRemitoService).findFirstBySearchFilter(Matchers.any(RemitoSearchFilter.class));
    }
}