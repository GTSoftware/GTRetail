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
package ar.com.gtsoftware.controller.proveedores;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProveedoresOrdenesCompraFacade;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompra;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompraLineas;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.gtsoftware.utils.JSFUtil.addErrorMessage;
import static ar.com.gtsoftware.utils.JSFUtil.getBundle;
import static ar.com.gtsoftware.utils.JSFUtil.getRequestParameterMap;

/**
 * Bean para la edición de ordenes de compra
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "ordenesCompraEditBean")
@ViewScoped
public class OrdenesCompraEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private ProveedoresOrdenesCompraFacade ocFacade;
    @EJB
    private ProductosFacade productosFacade;

    private final ProductosSearchFilter productosFilter = new ProductosSearchFilter(true, true, null, null);
    private Productos productoBusquedaSeleccionado = null;

    private BigDecimal cantidad = BigDecimal.ONE;
    private int numeradorLinea = 1;

    private ProveedoresOrdenesCompra ordenCompraActual = null;

    private static final Logger LOG = Logger.getLogger(OrdenesCompraEditBean.class.getName());

    /**
     * Creates a new instance of OrdenesCompraEditBean
     */
    public OrdenesCompraEditBean() {
    }

    @PostConstruct
    private void init() {

        String idOrdenCompra = getRequestParameterMap().get("idOrdenCompra");
        if (StringUtils.isEmpty(idOrdenCompra)) {
            nuevo();
        } else {
            ordenCompraActual = ocFacade.find(Long.parseLong(idOrdenCompra));
            if (ordenCompraActual == null) {
                nuevo();
                addErrorMessage("Orden de compra inexistente!");
                LOG.log(Level.INFO, "Orden de compra inexistente!");
            }
        }

    }

    public void agregarLinea() {

        if (cantidad.signum() <= 0) {
            addErrorMessage("La cantidad no puede ser cero o negativo.");
            return;
        }

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
            addErrorMessage(getBundle("msg").getString("productoNoEncontrado"));
            return;
        }

        ordenCompraActual.getProveedoresOrdenesCompraLineasList().add(crearLineaDetalleOrdenCompra(producto));
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

        productoBusquedaSeleccionado = null;

    }

    private ProveedoresOrdenesCompraLineas crearLineaDetalleOrdenCompra(Productos producto) {
        ProveedoresOrdenesCompraLineas detalle = new ProveedoresOrdenesCompraLineas();

        detalle.setCantidadPedida(cantidad);
        detalle.setIdProducto(producto);
        detalle.setIdOrdenCompra(ordenCompraActual);
        detalle.setNroLinea(numeradorLinea++);
        detalle.setIdTipoUnidad(producto.getIdTipoUnidadCompra());
        detalle.setCantidadRecibida(BigDecimal.ZERO);
        detalle.setPrecioCompraUnitario(producto.getCostoAdquisicionNeto());
        detalle.setSubTotal(detalle.getCantidadPedida().multiply(detalle.getPrecioCompraUnitario()));

        productosFilter.setIdProducto(null);
        productosFilter.setCodigoPropio(null);

        return detalle;
    }

    public void calcularTotal() {
        //TODO: Ver el tema del cálculo de impuestos y demás

        BigDecimal totalNeto = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        for (ProveedoresOrdenesCompraLineas ocd : ordenCompraActual.getProveedoresOrdenesCompraLineasList()) {

            ocd.setSubTotal(ocd.getCantidadPedida().multiply(ocd.getPrecioCompraUnitario()));
            totalNeto = totalNeto.add(ocd.getSubTotal());
            BigDecimal coeficiente = ocd.getIdProducto().getIdAlicuotaIva().getValorAlicuota().divide(new BigDecimal(100));
            iva = iva.add(ocd.getSubTotal().multiply(coeficiente));

        }

        BigDecimal totalRedondeado = totalNeto.setScale(2, RoundingMode.HALF_UP);

        ordenCompraActual.setTotal(totalRedondeado);
        ordenCompraActual.setTotalIVA(iva.setScale(2, RoundingMode.HALF_UP));
    }

    public void nuevo() {
        ordenCompraActual = new ProveedoresOrdenesCompra();
        ordenCompraActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        ordenCompraActual.setProveedoresOrdenesCompraLineasList(new ArrayList<>());
        ordenCompraActual.setTotalIVA(BigDecimal.ZERO);
        ordenCompraActual.setTotal(BigDecimal.ZERO);
    }

    public void productoSelected() {
        if (productoBusquedaSeleccionado == null) {
            return;
        }
        productosFilter.setIdProducto(productoBusquedaSeleccionado.getId());
    }


    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public ProveedoresOrdenesCompra getOrdenCompraActual() {
        return ordenCompraActual;
    }

    public Productos getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(Productos productoBusquedaSeleccionado) {
        this.productoBusquedaSeleccionado = productoBusquedaSeleccionado;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public ProductosSearchFilter getProductosFilter() {
        return productosFilter;
    }
}
