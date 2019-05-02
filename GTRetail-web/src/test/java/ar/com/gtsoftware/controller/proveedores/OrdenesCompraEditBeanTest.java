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

package ar.com.gtsoftware.controller.proveedores;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.bl.ProveedoresOrdenesCompraService;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;

import static ar.com.gtsoftware.controller.proveedores.OrdenesCompraEditBean.ID_ESTADO_OC_INICIAL;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JSFUtil.class})
public class OrdenesCompraEditBeanTest {


    @Mock
    private AuthBackingBean mockAuthBackingBean;
    @Mock
    private ProveedoresOrdenesCompraService mockOcService;
    @Mock
    private ProductosService mockProductosFacade;

    @InjectMocks
    private OrdenesCompraEditBean ordenesCompraEditBean;

    @Before
    public void setUp() {
        initMocks(this);
        PowerMockito.mockStatic(JSFUtil.class);

        when(mockAuthBackingBean.getUserLoggedIn()).thenReturn(UsuariosDto.builder()
                .id(1L)
                .login("TestUser")
                .nombreUsuario("Test User")
                .build());

        when(mockOcService.obtenerEstado(ID_ESTADO_OC_INICIAL)).thenReturn(
                ProveedoresOrdenesCompraEstadosDto.builder()
                        .id(ID_ESTADO_OC_INICIAL)
                        .nombreEstado("Diseño").build());
    }

    @Test
    public void crearNuevaOrdenDeCompra() {

        ordenesCompraEditBean.nuevo();

        ProveedoresOrdenesCompraDto ordenCompraActual = ordenesCompraEditBean.getOrdenCompraActual();

        verify(mockAuthBackingBean).getUserLoggedIn();

        assertThat(ordenCompraActual.getIdUsuario(), is(notNullValue()));
        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList(), is(notNullValue()));
        assertThat(ordenCompraActual.getTotal(), is(BigDecimal.ZERO));
        assertThat(ordenCompraActual.getTotalIVA(), is(BigDecimal.ZERO));
        assertThat(ordenCompraActual.getIdEstadoOrdenCompra(), is(notNullValue()));
        assertThat(ordenCompraActual.getIdEstadoOrdenCompra().getId(), is(ID_ESTADO_OC_INICIAL));
    }

    @Test
    public void agregarLineaCuandoUnProductoEsSeleccionado() {
        ordenesCompraEditBean.setProductoBusquedaSeleccionado(ProductosDto.builder()
                .id(1L)
                .costoAdquisicionNeto(new BigDecimal(25)).build());

        ordenesCompraEditBean.nuevo();
        ordenesCompraEditBean.agregarLinea();

        verifyZeroInteractions(mockProductosFacade);

        ProveedoresOrdenesCompraDto ordenCompraActual = ordenesCompraEditBean.getOrdenCompraActual();
        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList(), is(notNullValue()));
        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList().size(), is(1));
        assertThat(ordenesCompraEditBean.getCantidad(), is(BigDecimal.ONE));
        assertThat(ordenesCompraEditBean.getProductoBusquedaSeleccionado(), is(nullValue()));
    }

    @Test
    public void agregarLineaCuandoUnProductoNoEstaSeleccionadoYHayFiltro() {
        ordenesCompraEditBean.setProductoBusquedaSeleccionado(null);
        ProductosSearchFilter productosFilter = ordenesCompraEditBean.getProductosFilter();
        productosFilter.setIdProducto(1L);

        when(mockProductosFacade.findFirstBySearchFilter(productosFilter)).thenReturn(ProductosDto.builder().id(1L)
                .costoAdquisicionNeto(BigDecimal.TEN).build());

        ordenesCompraEditBean.nuevo();
        ordenesCompraEditBean.agregarLinea();

        verify(mockProductosFacade).findFirstBySearchFilter(productosFilter);

        ProveedoresOrdenesCompraDto ordenCompraActual = ordenesCompraEditBean.getOrdenCompraActual();
        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList(), is(notNullValue()));
        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList().size(), is(1));
        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList().get(0).getNroLinea(), is(1));
        assertThat(ordenesCompraEditBean.getCantidad(), is(BigDecimal.ONE));
        assertThat(ordenesCompraEditBean.getProductoBusquedaSeleccionado(), is(nullValue()));
    }

    @Test
    public void mostrarErrorAlAgregarLineaCuandoLaCantidadEsInvalida() {
        ordenesCompraEditBean.setProductoBusquedaSeleccionado(ProductosDto.builder()
                .id(1L)
                .costoAdquisicionNeto(new BigDecimal(25)).build());

        ordenesCompraEditBean.nuevo();
        ordenesCompraEditBean.setCantidad(new BigDecimal(-1));
        ordenesCompraEditBean.agregarLinea();

        verifyZeroInteractions(mockProductosFacade);

        ProveedoresOrdenesCompraDto ordenCompraActual = ordenesCompraEditBean.getOrdenCompraActual();

        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList().size(), is(0));
        assertThat(ordenesCompraEditBean.getCantidad(), is(new BigDecimal(-1)));
        PowerMockito.verifyStatic();
    }

    @Test
    public void mostrarErrorAlAgregarLineaCuandoElProductoNoEsEncontrado() {
        ordenesCompraEditBean.setProductoBusquedaSeleccionado(null);

        ordenesCompraEditBean.nuevo();
        ordenesCompraEditBean.setCantidad(new BigDecimal(-1));
        ordenesCompraEditBean.agregarLinea();

        verifyZeroInteractions(mockProductosFacade);

        ProveedoresOrdenesCompraDto ordenCompraActual = ordenesCompraEditBean.getOrdenCompraActual();

        assertThat(ordenCompraActual.getProveedoresOrdenesCompraLineasList().size(), is(0));
        assertThat(ordenesCompraEditBean.getCantidad(), is(new BigDecimal(-1)));
        PowerMockito.verifyStatic();
    }

    @Test
    public void sePuedeGuardarConExito() {
        ordenesCompraEditBean.nuevo();

        ordenesCompraEditBean.setProductoBusquedaSeleccionado(ProductosDto.builder()
                .id(1L)
                .idAlicuotaIva(FiscalAlicuotasIvaDto.builder().valorAlicuota(new BigDecimal(21)).build())
                .costoAdquisicionNeto(new BigDecimal(25)).build());
        ordenesCompraEditBean.agregarLinea();

        when(mockOcService.createOrEdit(anyObject())).thenReturn(ProveedoresOrdenesCompraDto.builder().id(2L).build());

        ordenesCompraEditBean.guardarOrdenCompra();

        verify(mockOcService).createOrEdit(any(ProveedoresOrdenesCompraDto.class));
        PowerMockito.verifyStatic();
    }

    @Test
    public void sePuedeConfirmarSoloSiEstaEnDiseño() {
        ordenesCompraEditBean.nuevo();

        assertThat(ordenesCompraEditBean.getSePuedeConfirmar(), is(true));
    }

    @Test
    public void NoSePuedeConfirmarCuandoNoEstaEnDiseño() {
        ordenesCompraEditBean.nuevo();

        ordenesCompraEditBean.getOrdenCompraActual().setIdEstadoOrdenCompra(ProveedoresOrdenesCompraEstadosDto.builder()
                .id(7L).build());

        assertThat(ordenesCompraEditBean.getSePuedeConfirmar(), is(false));
    }

    @Test
    public void esEditableSoloEnDiseño() {
        ordenesCompraEditBean.nuevo();

        assertThat(ordenesCompraEditBean.getEsEditable(), is(true));
    }

    @Test
    public void noEsEditable() {
        ordenesCompraEditBean.nuevo();

        ordenesCompraEditBean.getOrdenCompraActual().setIdEstadoOrdenCompra(ProveedoresOrdenesCompraEstadosDto.builder()
                .id(7L).build());

        assertThat(ordenesCompraEditBean.getEsEditable(), is(false));
    }
}