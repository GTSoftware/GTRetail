/*
 * Copyright 2018 GT Software.
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
package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.*;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.DepositosSearchFilter;
import ar.com.gtsoftware.search.ProductoXDepositoSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * Bean para el ingreso rápido de mercadería
 *
 * @author fede
 */
@ManagedBean(name = "ingresoMercaderiaBean")
@ViewScoped
public class IngresoMercaderiaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ProductosSearchFilter productosFilter = ProductosSearchFilter.builder()
            .activo(true).build();
    @EJB
    private RemitoService remitoFacade;
    @EJB
    private DepositosService depositosFacade;
    @EJB
    private RemitoTipoMovimientoService remitoTipoMovimientoFacade;
    @EJB
    private ProductosService productosFacade;
    @EJB
    private ProductoXDepositoService stockService;
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
    @Inject
    private JSFHelper jsfHelper;
    private ProductosDto productoBusquedaSeleccionado = null;
    private BigDecimal cantidad = BigDecimal.ONE;
    private int numeradorLinea = 1;
    private boolean unidadesCompra = true;

    private RemitoDto remitoCabecera = new RemitoDto();
    private List<DepositosDto> depositosList = new ArrayList<>();

    @PostConstruct
    public void init() {

        remitoCabecera.setDetalleList(new ArrayList<>());

        remitoCabecera.setIdUsuario(authBackingBean.getUserLoggedIn());
        remitoCabecera.setFechaAlta(new Date());
        remitoCabecera.setIsOrigenInterno(Boolean.FALSE);
        remitoCabecera.setIsDestinoInterno(Boolean.TRUE);

        DepositosSearchFilter dsf = DepositosSearchFilter.builder()
                .idSucursal(authBackingBean.getUserLoggedIn().getIdSucursal().getId())
                .activo(true)
                .build();
        dsf.addSortField("nombreDeposito", true);

        depositosList = depositosFacade.findAllBySearchFilter(dsf);

    }

    public void productoSelected() {
        if (productoBusquedaSeleccionado == null) {
            return;
        }
        productosFilter.setIdProducto(productoBusquedaSeleccionado.getId());
    }

    public void agregarLinea() {

        ProductosDto producto;
        if (productoBusquedaSeleccionado != null) {
            producto = productoBusquedaSeleccionado;
        } else {
            if (productosFilter.getIdProducto() == null && isEmpty(productosFilter.getCodigoPropio())
                    && isEmpty(productosFilter.getCodigoFabrica())) {
                return;
            }
            producto = productosFacade.findFirstBySearchFilter(productosFilter);
        }

        if (producto == null) {
            jsfHelper.addErrorMessage(jsfHelper.getBundle("msg").getString("productoNoEncontrado"));
            return;
        }

        remitoCabecera.getDetalleList().add(crearLineaDetalleRemito(producto));
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);
        productosFilter.setCodigoFabrica(null);

        productoBusquedaSeleccionado = null;

    }

    private RemitoDetalleDto crearLineaDetalleRemito(ProductosDto producto) {
        RemitoDetalleDto detalle = new RemitoDetalleDto();
        if (unidadesCompra) {
            cantidad = cantidad.multiply(producto.getUnidadesCompraUnidadesVenta());
        }
        detalle.setCantidad(cantidad);
        detalle.setIdProducto(producto);
        detalle.setRemitoCabecera(remitoCabecera);
        detalle.setNroLinea(numeradorLinea++);
        if (remitoCabecera.getIdDestinoPrevistoInterno() != null) {
            ProductoXDepositoSearchFilter stkf = ProductoXDepositoSearchFilter.builder()
                    .idSucursal(remitoCabecera.getIdDestinoPrevistoInterno().getIdSucursal().getId())
                    .idProducto(producto.getId())
                    .build();
            BigDecimal stockSucursal = stockService.getStockBySearchFilter(stkf);
            stkf.setIdSucursal(null);
            BigDecimal stockTotal = stockService.getStockBySearchFilter(stkf);
            detalle.setStockDeposito(stockSucursal);
            detalle.setStockTotal(stockTotal);
        }

        productosFilter.setIdProducto(null);
        productosFilter.setCodigoPropio(null);

        return detalle;
    }

    public String confirmarIngreso() {
        if (remitoCabecera.getDetalleList().isEmpty()) {
            jsfHelper.addErrorMessage("Debe ingresar los productos.");
            return StringUtils.EMPTY;
        }

        remitoCabecera.setRemitoTipoMovimiento(remitoTipoMovimientoFacade.find(1L));
        remitoCabecera.setObservaciones(String.format("Ingreso de mercaderia del proveedor: %s",
                remitoCabecera.getIdOrigenExterno().toString()));

        RemitoRecepcionDto recepcion = new RemitoRecepcionDto();

        recepcion.setRemito(remitoCabecera);
        recepcion.setIdPersona(remitoCabecera.getIdOrigenExterno());
        recepcion.setIdUsuario(authBackingBean.getUserLoggedIn());
        recepcion.setIdDeposito(remitoCabecera.getIdDestinoPrevistoInterno());

        remitoCabecera.setRemitoRecepcionesList(Collections.singletonList(recepcion));
        remitoFacade.createOrEdit(remitoCabecera);

        return "/protected/stock/remitos/index.xhtml?faces-redirect=true";
    }

    public void eliminarItem(RemitoDetalleDto linea) {

        remitoCabecera.getDetalleList().remove(linea);

    }

    // ---Getter and Setter ----------------------------------------------
    public RemitoDto getRemitoCabecera() {
        return remitoCabecera;
    }

    public void setRemitoCabecera(RemitoDto remitoCabecera) {
        this.remitoCabecera = remitoCabecera;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public ProductosSearchFilter getProductosFilter() {
        return productosFilter;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public ProductosDto getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(ProductosDto productoBusquedaSeleccionado) {
        this.productoBusquedaSeleccionado = productoBusquedaSeleccionado;
        productoSelected();
    }

    /**
     * Establece si se debe considerar la cantidad ingresada como unidades de compra
     *
     * @return
     */
    public boolean getUnidadesCompra() {
        return unidadesCompra;
    }

    /**
     * Establece si se debe considerar la cantidad ingresada como unidades de compra
     *
     * @param unidadesCompra
     */
    public void setUnidadesCompra(boolean unidadesCompra) {
        this.unidadesCompra = unidadesCompra;
    }

    public List<DepositosDto> getDepositosList() {
        return depositosList;
    }


}
