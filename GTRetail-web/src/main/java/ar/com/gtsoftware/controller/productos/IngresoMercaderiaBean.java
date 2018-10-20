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
import ar.com.gtsoftware.bl.DepositosService;
import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.bl.RemitoService;
import ar.com.gtsoftware.bl.RemitoTipoMovimientoService;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.search.DepositosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
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
            if (productosFilter.getIdProducto() == null && StringUtils.isEmpty(productosFilter.getCodigoPropio())) {
                return;
            }
            producto = productosFacade.findFirstBySearchFilter(productosFilter);
        }

        if (producto == null) {
            JSFUtil.addErrorMessage(JSFUtil.getBundle("msg").getString("productoNoEncontrado"));
            return;
        }

        remitoCabecera.getDetalleList().add(crearLineaDetalleRemito(producto));
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

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

        productosFilter.setIdProducto(null);
        productosFilter.setCodigoPropio(null);

        return detalle;
    }

    public String confirmarIngreso() {
        if (remitoCabecera.getDetalleList().isEmpty()) {
            JSFUtil.addErrorMessage("Debe ingresar los productos.");
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

        remitoCabecera.setRemitoRecepcionesList(Arrays.asList(recepcion));
        remitoFacade.createOrEdit(remitoCabecera);

        return "/protected/stock/remitos/index.xhtml?faces-redirect=true";
    }

    /**
     * Borra el ítem con el número pasado por parámetro
     *
     * @param nroItem
     */
    public void eliminarItem(int nroItem) {
        int index = -1;
        int cont = 0;

        for (RemitoDetalleDto vl : remitoCabecera.getDetalleList()) {
            if (vl.getNroLinea() == nroItem) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            remitoCabecera.getDetalleList().remove(index);
        }
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
