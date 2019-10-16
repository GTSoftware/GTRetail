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
import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.bl.ProveedoresOrdenesCompraService;
import ar.com.gtsoftware.dto.model.ProductosDto;
import ar.com.gtsoftware.dto.model.ProveedoresOrdenesCompraDto;
import ar.com.gtsoftware.dto.model.ProveedoresOrdenesCompraLineasDto;
import ar.com.gtsoftware.helper.JSFHelper;
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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bean para la edición de ordenes de compra
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "ordenesCompraEditBean")
@ViewScoped
public class OrdenesCompraEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(OrdenesCompraEditBean.class.getName());
    private final ProductosSearchFilter productosFilter = ProductosSearchFilter.builder().activo(true)
            .puedeComprarse(true).build();
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
    @EJB
    private ProveedoresOrdenesCompraService ocFacade;
    @EJB
    private ProductosService productosFacade;
    @Inject
    private JSFHelper jsfHelper;
    private ProductosDto productoBusquedaSeleccionado = null;
    private BigDecimal cantidad = BigDecimal.ONE;
    private int numeradorLinea = 1;
    private ProveedoresOrdenesCompraDto ordenCompraActual = null;

    /**
     * Creates a new instance of OrdenesCompraEditBean
     */
    public OrdenesCompraEditBean() {
    }

    @PostConstruct
    private void init() {

        String idOrdenCompra = jsfHelper.getRequestParameterMap().get("idOrdenCompra");
        if (StringUtils.isEmpty(idOrdenCompra)) {
            nuevo();
        } else {
            ordenCompraActual = ocFacade.find(Long.parseLong(idOrdenCompra));
            if (ordenCompraActual == null) {
                nuevo();
                jsfHelper.addErrorMessage("Orden de compra inexistente!");
                LOG.log(Level.INFO, "Orden de compra inexistente!");
            }
        }

    }

    public void agregarLinea() {

        if (cantidad.signum() <= 0) {
            jsfHelper.addErrorMessage("La cantidad no puede ser cero o negativo.");
            return;
        }

        ProductosDto producto;
        if (productoBusquedaSeleccionado != null) {
            producto = productoBusquedaSeleccionado;
        } else {
            if (productosFilter.getIdProducto() == null && StringUtils.isEmpty(productosFilter.getCodigoPropio()) && StringUtils.isEmpty(productosFilter.getCodigoFabrica())) {
                return;
            }
            producto = productosFacade.findFirstBySearchFilter(productosFilter);
        }

        if (producto == null) {
            jsfHelper.addErrorMessage(jsfHelper.getBundle("msg").getString("productoNoEncontrado"));
            return;
        }

        ordenCompraActual.getProveedoresOrdenesCompraLineasList().add(crearLineaDetalleOrdenCompra(producto));
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

        productoBusquedaSeleccionado = null;

    }

    private ProveedoresOrdenesCompraLineasDto crearLineaDetalleOrdenCompra(ProductosDto producto) {
        ProveedoresOrdenesCompraLineasDto detalle = new ProveedoresOrdenesCompraLineasDto();

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
        productosFilter.setCodigoFabrica(null);

        return detalle;
    }

    public void calcularTotal() {
        //TODO: Ver el tema del cálculo de impuestos y demás

        BigDecimal totalNeto = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;

        for (ProveedoresOrdenesCompraLineasDto ocd : ordenCompraActual.getProveedoresOrdenesCompraLineasList()) {

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
        ordenCompraActual = new ProveedoresOrdenesCompraDto();
        ordenCompraActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        ordenCompraActual.setProveedoresOrdenesCompraLineasList(new ArrayList<>());
        ordenCompraActual.setTotalIVA(BigDecimal.ZERO);
        ordenCompraActual.setTotal(BigDecimal.ZERO);
        //TODO setear el estado inicial de la OC en Diseño (por ej)
//        ordenCompraActual.setIdEstadoOrdenCompra();
    }

    public void borrarItem(ProveedoresOrdenesCompraLineasDto linea) {
        ordenCompraActual.getProveedoresOrdenesCompraLineasList().remove(linea);
        calcularTotal();
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

    public ProveedoresOrdenesCompraDto getOrdenCompraActual() {
        return ordenCompraActual;
    }

    public ProductosDto getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(ProductosDto productoBusquedaSeleccionado) {
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
