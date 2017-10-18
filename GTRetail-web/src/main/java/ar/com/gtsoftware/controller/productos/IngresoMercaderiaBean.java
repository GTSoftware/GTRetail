/*
 * Copyright 2016 GT Software.
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
import ar.com.gtsoftware.eao.DepositosFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.RemitoFacade;
import ar.com.gtsoftware.eao.RemitoTipoMovimientoFacade;
import ar.com.gtsoftware.model.Depositos;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.Remito;
import ar.com.gtsoftware.model.RemitoDetalle;
import ar.com.gtsoftware.model.RemitoRecepcion;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;

/**
 * Bean para el ingreso rápido de mercadería
 *
 * @author fede
 */
@ManagedBean(name = "ingresoMercaderiaBean")
@ViewScoped
public class IngresoMercaderiaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private RemitoFacade remitoFacade;
    @EJB
    private DepositosFacade depositosFacade;
    @EJB
    private RemitoTipoMovimientoFacade remitoTipoMovimientoFacade;
    @EJB
    private ProductosFacade productosFacade;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private JSFUtil jsfUtil;
    private final ProductosSearchFilter productosFilter = new ProductosSearchFilter(Boolean.TRUE, null, null, null);
    private Productos productoBusquedaSeleccionado = null;
    private BigDecimal cantidad = BigDecimal.ONE;
    private int numeradorLinea = 1;
    private boolean unidadesCompra = true;

    private Remito remitoCabecera = new Remito();
    private List<Depositos> depositosList = new ArrayList<>();

    @PostConstruct
    public void init() {

        remitoCabecera.setDetalleList(new ArrayList<>());

        remitoCabecera.setIdUsuario(authBackingBean.getUserLoggedIn());
        remitoCabecera.setFechaAlta(new Date());
        remitoCabecera.setIsOrigenInterno(Boolean.FALSE);
        remitoCabecera.setIsDestinoInterno(Boolean.TRUE);

        depositosList = authBackingBean.getUserLoggedIn().getIdSucursal().getDepositosList();

    }

    public void productoSelected() {
        if (productoBusquedaSeleccionado == null) {
            return;
        }
        productosFilter.setIdProducto(productoBusquedaSeleccionado.getId());
    }

    public void agregarLinea() {

        Productos producto;
        if (productoBusquedaSeleccionado != null) {
            producto = productoBusquedaSeleccionado;
        } else {
            if (productosFilter.getIdProducto() == null && StringUtils.isEmpty(productosFilter.getCodigoPropio())) {
                return;
            }
            producto = productosFacade.findFirstBySearchFilter(productosFilter);
        }

        if (producto == null) {
            jsfUtil.addErrorMessage(jsfUtil.getBundle("msg").getString("productoNoEncontrado"));
            return;
        }

        remitoCabecera.getDetalleList().add(crearLineaDetalleRemito(producto));
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

        productoBusquedaSeleccionado = null;

    }

    private RemitoDetalle crearLineaDetalleRemito(Productos producto) {
        RemitoDetalle detalle = new RemitoDetalle();
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
            jsfUtil.addErrorMessage("Debe ingresar los productos.");
            return StringUtils.EMPTY;
        }

        remitoCabecera.setRemitoTipoMovimiento(remitoTipoMovimientoFacade.find(1L));
        remitoCabecera.setObservaciones(String.format("Ingreso de mercaderia del proveedor: %s",
                remitoCabecera.getIdOrigenExterno().getBusinessString()));

        RemitoRecepcion recepcion = new RemitoRecepcion();

        recepcion.setRemito(remitoCabecera);
        recepcion.setIdPersona(remitoCabecera.getIdOrigenExterno());
        recepcion.setIdUsuario(authBackingBean.getUserLoggedIn());
        recepcion.setIdDeposito(remitoCabecera.getIdDestinoPrevistoInterno());

        remitoCabecera.setRemitoRecepcionesList(Arrays.asList(recepcion));
        remitoFacade.create(remitoCabecera);

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

        for (RemitoDetalle vl : remitoCabecera.getDetalleList()) {
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
    public Remito getRemitoCabecera() {
        return remitoCabecera;
    }

    public void setRemitoCabecera(Remito remitoCabecera) {
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

    public Productos getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(Productos productoBusquedaSeleccionado) {
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

    public List<Depositos> getDepositosList() {
        return depositosList;
    }

    public void setDepositosList(List<Depositos> depositosList) {
        this.depositosList = depositosList;
    }

}
