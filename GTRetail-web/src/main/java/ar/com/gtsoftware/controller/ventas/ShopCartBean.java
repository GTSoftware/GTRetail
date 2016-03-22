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
package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.impl.VentasBean;
import ar.com.gtsoftware.eao.NegocioCondicionesOperacionesFacade;
import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosListasPreciosFacade;
import ar.com.gtsoftware.eao.ProductosPreciosFacade;
import ar.com.gtsoftware.eao.VentasEstadosFacade;
import ar.com.gtsoftware.model.NegocioCondicionesOperaciones;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.model.Parametros;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.model.ProductosPrecios;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.VentasEstados;
import ar.com.gtsoftware.model.VentasLineas;
import ar.com.gtsoftware.model.VentasPagos;
import ar.com.gtsoftware.search.ProductosPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * MB para manejar el carrito de compras
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Named(value = "shopCartBean")
@ConversationScoped
public class ShopCartBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Conversation conversation;

    @Inject
    private AuthBackingBean authBackingBean;
    @EJB
    private ProductosFacade productosFacade;

    @EJB
    private ProductosPreciosFacade preciosFacade;
    @EJB
    private ParametrosFacade parametrosFacade;
    @EJB
    private ProductosListasPreciosFacade listasPreciosFacade;
    @EJB
    private PersonasFacade personasFacade;
    @EJB
    private NegocioCondicionesOperacionesFacade condicionesOperacionesFacade;
    @EJB
    private NegocioFormasPagoFacade formasPagoFacade;
    @EJB
    private VentasEstadosFacade estadosFacade;
    @EJB
    private VentasBean ventasBean;

    private final ProductosSearchFilter productosFilter = new ProductosSearchFilter(Boolean.TRUE, null, Boolean.TRUE,
            Boolean.TRUE);

    private final List<VentasPagos> pagos = new ArrayList<>();

    private VentasPagos pagoActual = new VentasPagos();

    private BigDecimal cantidad = BigDecimal.ONE;

    private Ventas venta;

    private Productos productoBusquedaSeleccionado = null;

    private boolean ventaModificada = false;

    private ProductosListasPrecios lista;

    private static final String ID_LISTA_PARAM = "venta.pos.id_lista";
    private static final String CANT_DECIMALES_REDONDEO_PARAM = "venta.pos.redondear.cant_decimales";
    private static final String ID_PRODUCTO_REDONDEO_PARAM = "venta.pos.redondeo.id_producto";
    private static final String ID_CLIENTE_DEFECTO_PARAM = "venta.pos.id_cliente.defecto";
    private static final String ID_CONDICION_DEFECTO_PARAM = "venta.pos.id_condicion.defecto";
    private static final String ID_FORMA_PAGO_DEFECTO_PARAM = "venta.pos.id_forma_pago.defecto";

    private Productos productoRedondeo;

    private NegocioFormasPago formaPagoDefecto = null;

    private int cantDeccimalesRedondeo = 0;

    private final AtomicInteger itemCounter = new AtomicInteger(1);

    /**
     * Creates a new instance of ShopCartBean
     */
    public ShopCartBean() {
    }

    /**
     * Inicializa la conversación
     */
    public void initConversation() {
        if (!JSFUtil.isPostback() && conversation.isTransient()) {
            conversation.begin();
            venta = new Ventas();
            venta.setIdUsuario(authBackingBean.getUserLoggedIn());
            venta.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            venta.setFechaVenta(new Date());
            venta.setTotal(BigDecimal.ZERO);
            Parametros listaParam = parametrosFacade.find(ID_LISTA_PARAM);
            Parametros cantDecimalesParam = parametrosFacade.find(CANT_DECIMALES_REDONDEO_PARAM);
            Parametros idProdRedondeoParam = parametrosFacade.find(ID_PRODUCTO_REDONDEO_PARAM);
            Parametros idClienteParam = parametrosFacade.find(ID_CLIENTE_DEFECTO_PARAM);
            Parametros idCondicionParam = parametrosFacade.find(ID_CONDICION_DEFECTO_PARAM);
            Parametros idFormaPagoParam = parametrosFacade.find(ID_FORMA_PAGO_DEFECTO_PARAM);
            lista = listasPreciosFacade.find(Long.parseLong(listaParam.getValorParametro()));
            cantDeccimalesRedondeo = cantDecimalesParam == null ? 2 : Integer.parseInt(cantDecimalesParam.getValorParametro());
            productoRedondeo = idProdRedondeoParam == null ? null : productosFacade.find(Long.parseLong(idProdRedondeoParam.getValorParametro()));
            venta.setIdPersona(personasFacade.find(Long.parseLong(idClienteParam.getValorParametro())));
            venta.setIdCondicionVenta(condicionesOperacionesFacade.find(Long.parseLong(idCondicionParam.getValorParametro())));
            formaPagoDefecto = formasPagoFacade.find(Long.parseLong(idFormaPagoParam.getValorParametro()));
        }
    }

    /**
     * Finaliza la conversación. Almacena la operación y redirecciona.
     *
     * @return el string de la vista de destino
     */
    public String endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "index?faces-redirect=true";
    }

    public void removeFromCart(int item) {
        int index = -1;
        int cont = 0;

        for (VentasLineas vl : venta.getVentasLineasList()) {
            if (vl.getItem() == item) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            venta.getVentasLineasList().remove(index);
            calcularTotal();
            JSFUtil.addInfoMessage(JSFUtil.getBundle("msg").getString("productoQuitadoCarritoSatisfactoriamente"));
        }
    }

    public void eliminarPago(int item) {
        int index = -1;
        int cont = 0;

        for (VentasPagos vp : pagos) {
            if (vp.getItem() == item) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            pagos.remove(index);
            calcularTotalPagado();
        }
    }

    public void initPagos() {
        if (!JSFUtil.isPostback()) {
            if (pagos.isEmpty() && formaPagoDefecto != null) {
                pagoActual.setImporteTotalPagado(venta.getTotal());
                pagoActual.setIdFormaPago(formaPagoDefecto);
                venta.setSaldo(BigDecimal.ZERO);
                doAgregarPago();
            }
        }
    }

    public void addToCart() {

        Productos producto;
        if (productoBusquedaSeleccionado != null) {
            producto = productoBusquedaSeleccionado;
        } else {
            producto = productosFacade.findFirstBySearchFilter(productosFilter);
        }

        if (producto == null) {
            JSFUtil.addErrorMessage(JSFUtil.getBundle("msg").getString("productoNoEncontrado"));
            return;
        }
        ProductosPrecios precio = preciosFacade.findFirstBySearchFilter(new ProductosPreciosSearchFilter(producto, lista));
        if (precio == null) {
            JSFUtil.addErrorMessage(JSFUtil.getBundle("msg").getString("productoSinPrecio"));
            return;
        }

        venta.addLineaVenta(crearLinea(producto, precio));
        calcularTotal();
        //Init datos
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        //Init datos
        JSFUtil.addInfoMessage(JSFUtil.getBundle("msg").getString("productoAgregadoAlCarritoSatisfactoriamente"));
        ventaModificada = true;
        productoBusquedaSeleccionado = null;
    }

    private VentasLineas crearLinea(Productos prod, ProductosPrecios precio) {
        VentasLineas linea = new VentasLineas();
        linea.setIdVenta(venta);
        linea.setCantidad(cantidad);
        linea.setDescripcion(prod.getDescripcion());
        linea.setIdProducto(prod);
        linea.setCantidadEntregada(BigDecimal.ZERO);
        if (!prod.getIdTipoProveeduria().getControlStock()) {
            linea.setCantidadEntregada(linea.getCantidad());
        }
        linea.setCostoBrutoUnitario(prod.getCostoAdquisicionNeto());
        linea.setCostoNetoUnitario(prod.getCostoFinal());
        linea.setPrecioVentaUnitario(precio.getPrecio());
        linea.setSubTotal(cantidad.multiply(precio.getPrecio()));
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    private void calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (VentasLineas vl : venta.getVentasLineasList()) {
            if (!vl.getIdProducto().equals(productoRedondeo)) {
                total = total.add(vl.getSubTotal());
            }
        }

        BigDecimal totalRedondeado = total.setScale(cantDeccimalesRedondeo, RoundingMode.HALF_UP);
        BigDecimal redondeo = totalRedondeado.subtract(total);
        if (redondeo.signum() != 0 && productoRedondeo != null) {
            cargarRedondeo(redondeo);
        }

        venta.setTotal(totalRedondeado);
    }

    private void cargarRedondeo(BigDecimal redondeo) {
        int cont = 0;
        int index = -1;
        for (VentasLineas vl : venta.getVentasLineasList()) {
            if (vl.getIdProducto().equals(productoRedondeo)) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index != -1) {
            venta.getVentasLineasList().remove(index);
        }
        venta.addLineaVenta(crearLineaRedondeo(redondeo));

    }

    private VentasLineas crearLineaRedondeo(BigDecimal redondeo) {
        VentasLineas linea = new VentasLineas();
        linea.setIdVenta(venta);
        linea.setCantidad(BigDecimal.ONE);
        linea.setDescripcion(productoRedondeo.getDescripcion());
        linea.setIdProducto(productoRedondeo);
        linea.setCantidadEntregada(BigDecimal.ONE);
        linea.setCostoBrutoUnitario(BigDecimal.ZERO);
        linea.setCostoNetoUnitario(BigDecimal.ZERO);
        linea.setPrecioVentaUnitario(redondeo);
        linea.setSubTotal(redondeo);
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    public void doAgregarPago() {
        if (pagoActual.getIdFormaPago() != null) {
            if (pagoActual.getImporteTotalPagado().signum() <= 0
                    || pagoActual.getImporteTotalPagado().compareTo(venta.getSaldo()) > 0) {
                JSFUtil.addErrorMessage("El monto del pago supera el saldo!");

            } else {

                pagoActual.setItem(itemCounter.getAndIncrement());
                pagos.add(pagoActual);
                pagoActual = new VentasPagos();
                pagoActual.setImporteTotalPagado(BigDecimal.ZERO);
            }
        }
        calcularTotalPagado();
    }

    private void calcularTotalPagado() {
        venta.setSaldo(venta.getTotal());
        BigDecimal sumaPagos = BigDecimal.ZERO;
        for (VentasPagos p : pagos) {
            sumaPagos = sumaPagos.add(p.getImporteTotalPagado());
        }
        venta.setSaldo(venta.getTotal().subtract(sumaPagos));
        pagoActual.setImporteTotalPagado(venta.getSaldo());
    }

    private boolean validarVenta() {
        if (authBackingBean.getUserLoggedIn().getIdSucursal() == null) {
            JSFUtil.addErrorMessage("El usuario no tiene una sucursal configurada. Por favor configure el usuario.");
            return false;
        }
        if (venta.getIdPersona() == null) {
            JSFUtil.addErrorMessage("Por favor cargue un cliente para poder continuar.");
            return false;
        }
        if (venta.getVentasLineasList() == null || venta.getVentasLineasList().isEmpty()) {
            JSFUtil.addErrorMessage("Por favor cargue productos para poder continuar.");
            return false;
        }
        if (venta.getIdCondicionVenta() != null) {
            if (venta.getIdCondicionVenta().getPagoTotal()) {
                if (venta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
                    JSFUtil.addErrorMessage("El importe del pago debe cubrir el total de la operación para esta condición.");
                    return false;
                }
            }
        }

        return true;
    }

    public String guardarVenta() {
        if (validarVenta()) {
            venta.setFechaVenta(new Date());
            venta.setIdUsuario(authBackingBean.getUserLoggedIn());
            venta.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            try {
                venta.setSaldo(venta.getTotal());
                ventasBean.guardarVenta(venta, pagos);
                JSFUtil.addInfoMessage("Operación guardada exitosamente!");
                endConversation();
                return "/protected/ventas/index?faces-redirect=true";
            } catch (Exception ex) {
                Logger.getLogger(NuevaVentaBean.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.addErrorMessage(ex.getMessage());
            }
        }
        return null;
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

    public Ventas getVenta() {
        return venta;
    }

    public Productos getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(Productos productoBusquedaSeleccionado) {
        this.productoBusquedaSeleccionado = productoBusquedaSeleccionado;
    }

    public List<NegocioCondicionesOperaciones> getCondicionesVentaList() {
        return condicionesOperacionesFacade.findAll();
    }

    public List<NegocioFormasPago> getFormasPagoList() {
        return formasPagoFacade.findFormasPagoVenta();
    }

    public List<VentasPagos> getPagos() {
        return pagos;
    }

    public List<VentasEstados> getVentasEstados() {
        return estadosFacade.findAll();
    }

    public VentasPagos getPagoActual() {
        return pagoActual;
    }

    public void setPagoActual(VentasPagos pagoActual) {
        this.pagoActual = pagoActual;
    }

    public ProductosListasPrecios getLista() {
        return lista;
    }

}
