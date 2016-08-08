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
import ar.com.gtsoftware.eao.ComprobantesEstadosFacade;
import ar.com.gtsoftware.eao.FiscalLetrasComprobantesFacade;
import ar.com.gtsoftware.eao.FiscalResponsabilidadesIvaFacade;
import ar.com.gtsoftware.eao.NegocioCondicionesOperacionesFacade;
import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.eao.NegocioPlanesPagoDetalleFacade;
import ar.com.gtsoftware.eao.NegocioPlanesPagoFacade;
import ar.com.gtsoftware.eao.NegocioTiposComprobanteFacade;
import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosListasPreciosFacade;
import ar.com.gtsoftware.eao.ProductosPreciosFacade;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.ComprobantesEstados;
import ar.com.gtsoftware.model.ComprobantesLineas;
import ar.com.gtsoftware.model.ComprobantesPagos;
import ar.com.gtsoftware.model.FiscalLetrasComprobantes;
import ar.com.gtsoftware.model.NegocioCondicionesOperaciones;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.model.NegocioPlanesPago;
import ar.com.gtsoftware.model.NegocioPlanesPagoDetalle;
import ar.com.gtsoftware.model.NegocioTiposComprobante;
import ar.com.gtsoftware.model.Parametros;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.model.ProductosPrecios;
import ar.com.gtsoftware.search.FiscalLetrasComprobantesSearchFilter;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.NegocioTiposComprobanteSearchFilter;
import ar.com.gtsoftware.search.PlanesPagoDetalleSearchFilter;
import ar.com.gtsoftware.search.PlanesPagoSearchFilter;
import ar.com.gtsoftware.search.ProductosPreciosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.search.SortField;
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
import org.apache.commons.lang.StringUtils;

/**
 * MB para manejar el carrito de compras
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Named(value = "shopCartBean")
@ConversationScoped
public class ShopCartBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ShopCartBean.class.getName());

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
    private ComprobantesEstadosFacade estadosFacade;
    @EJB
    private NegocioTiposComprobanteFacade tiposComprobanteFacade;
    @EJB
    private VentasBean ventasBean;
    @EJB
    private FiscalLetrasComprobantesFacade letrasComprobantesFacade;
    @EJB
    private FiscalResponsabilidadesIvaFacade responsabilidadesIvaFacade;
    @EJB
    private NegocioPlanesPagoFacade planesPagoFacade;
    @EJB
    private NegocioPlanesPagoDetalleFacade pagoDetalleFacade;

    private final ProductosSearchFilter productosFilter = new ProductosSearchFilter(Boolean.TRUE, null, Boolean.TRUE,
            Boolean.TRUE);

    private final NegocioTiposComprobanteSearchFilter tipoCompSf = new NegocioTiposComprobanteSearchFilter(Boolean.TRUE);

    private final PlanesPagoSearchFilter planesSearchFilter = new PlanesPagoSearchFilter(Boolean.TRUE);

    private final PlanesPagoDetalleSearchFilter pagoDetalleSearchFilter = new PlanesPagoDetalleSearchFilter(Boolean.TRUE);

    private ComprobantesPagos pagoActual = new ComprobantesPagos();

    private BigDecimal cantidad = BigDecimal.ONE;

    private Comprobantes venta;

    private Productos productoBusquedaSeleccionado = null;

    private boolean ventaModificada = false;

    private ProductosListasPrecios lista;

    private static final String ID_LISTA_PARAM = "venta.pos.id_lista";
    private static final String CANT_DECIMALES_REDONDEO_PARAM = "venta.pos.redondear.cant_decimales";
    private static final String ID_PRODUCTO_REDONDEO_PARAM = "venta.pos.redondeo.id_producto";
    private static final String ID_CLIENTE_DEFECTO_PARAM = "venta.pos.id_cliente.defecto";
    private static final String ID_CONDICION_DEFECTO_PARAM = "venta.pos.id_condicion.defecto";
    private static final String ID_FORMA_PAGO_DEFECTO_PARAM = "venta.pos.id_forma_pago.defecto";
    private final FormasPagoSearchFilter formasPagoSearchFilter = new FormasPagoSearchFilter(true, null);

    private Productos productoRedondeo;

    private NegocioFormasPago formaPagoDefecto = null;

    private int cantDeccimalesRedondeo = 0;
    private int redondeoIndex = 0;

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
            venta = new Comprobantes();
            venta.setIdUsuario(authBackingBean.getUserLoggedIn());
            venta.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            venta.setFechaComprobante(new Date());
            venta.setTotal(BigDecimal.ZERO);
            venta.setPagosList(new ArrayList<>());
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
            venta.setIdCondicionComprobante(condicionesOperacionesFacade.find(Long.parseLong(idCondicionParam.getValorParametro())));
            formaPagoDefecto = formasPagoFacade.find(Long.parseLong(idFormaPagoParam.getValorParametro()));
            venta.setTipoComprobante(tiposComprobanteFacade.getTipoFactura());

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

        for (ComprobantesLineas vl : venta.getComprobantesLineasList()) {
            if (vl.getItem() == item) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            venta.getComprobantesLineasList().remove(index);
            calcularTotal();
        }
    }

    public void eliminarPago(int item) {
        int index = -1;
        int cont = 0;

        for (ComprobantesPagos vp : venta.getPagosList()) {
            if (vp.getItem() == item) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            ComprobantesPagos pago = venta.getPagosList().get(index);
            if (pago.getProductoRecargoItem() > 0) {
                removeFromCart(pago.getProductoRecargoItem());
            }
            venta.getPagosList().remove(index);
            calcularTotalPagado();
        }
    }

    public void initPagos() {
        if (!JSFUtil.isPostback()) {
            if (venta.getPagosList().isEmpty() && formaPagoDefecto != null) {
                pagoActual.setMontoPago(venta.getTotal());
                pagoActual.setMontoPagado(BigDecimal.ZERO);
                pagoActual.setIdFormaPago(formaPagoDefecto);
                venta.setSaldo(venta.getTotal());
                doAgregarPago();
            }
        }
    }

    public void addToCart() {

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

        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

        ventaModificada = true;
        productoBusquedaSeleccionado = null;
    }

    private ComprobantesLineas crearLinea(Productos prod, ProductosPrecios precio) {
        ComprobantesLineas linea = new ComprobantesLineas();
        linea.setIdComprobante(venta);
        linea.setCantidad(cantidad);
        linea.setDescripcion(prod.getDescripcion());
        linea.setIdProducto(prod);
        linea.setCantidadEntregada(BigDecimal.ZERO);
        if (!prod.getIdTipoProveeduria().getControlStock()) {
            linea.setCantidadEntregada(linea.getCantidad());
        }
        linea.setCostoBrutoUnitario(prod.getCostoAdquisicionNeto());
        linea.setCostoNetoUnitario(prod.getCostoFinal());
        linea.setPrecioUnitario(precio.getPrecio());
        linea.setSubTotal(cantidad.multiply(precio.getPrecio()));
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    public void calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        eliminarRedondeo();
        for (ComprobantesLineas vl : venta.getComprobantesLineasList()) {

            vl.setSubTotal(vl.getCantidad().multiply(vl.getPrecioUnitario()));
            total = total.add(vl.getSubTotal());

        }

        BigDecimal totalRedondeado = total.setScale(cantDeccimalesRedondeo, RoundingMode.HALF_UP);
        BigDecimal redondeo = totalRedondeado.subtract(total).setScale(cantDeccimalesRedondeo, RoundingMode.HALF_UP);
        if (redondeo.signum() > 0 && productoRedondeo != null) {
            cargarRedondeo(redondeo);
        }

        venta.setTotal(totalRedondeado);
    }

    private void cargarRedondeo(BigDecimal redondeo) {
        ComprobantesLineas lineaRedondeo = crearLineaRedondeo(redondeo);
        redondeoIndex = lineaRedondeo.getItem();
        venta.addLineaVenta(lineaRedondeo);

    }

    private void eliminarRedondeo() {
        if (redondeoIndex > 0) {
            int cont = 0;
            int index = -1;
            for (ComprobantesLineas vl : venta.getComprobantesLineasList()) {
                if (vl.getItem().equals(redondeoIndex)) {
                    index = cont;
                    break;
                }
                cont++;
            }
            if (index != -1) {
                venta.getComprobantesLineasList().remove(index);
            }
            redondeoIndex = 0;
        }
    }

    private ComprobantesLineas crearLineaRedondeo(BigDecimal redondeo) {
        ComprobantesLineas linea = new ComprobantesLineas();
        linea.setIdComprobante(venta);
        linea.setCantidad(BigDecimal.ONE);
        linea.setDescripcion(productoRedondeo.getDescripcion());
        linea.setIdProducto(productoRedondeo);
        linea.setCantidadEntregada(BigDecimal.ONE);
        linea.setCostoBrutoUnitario(BigDecimal.ZERO);
        linea.setCostoNetoUnitario(BigDecimal.ZERO);
        linea.setPrecioUnitario(redondeo);
        linea.setSubTotal(redondeo);
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    private ComprobantesLineas crearLineaRecargo(BigDecimal recargo, NegocioPlanesPagoDetalle detallePlan) {
        ComprobantesLineas linea = new ComprobantesLineas();
        linea.setIdComprobante(venta);
        linea.setCantidad(BigDecimal.ONE);
        linea.setDescripcion("COSTO FINANCIERO POR " + detallePlan.getIdPlan().getNombre() + " EN: " + detallePlan.getCuotas() + " CUOTAS");
        linea.setIdProducto(productoRedondeo);
        linea.setCantidadEntregada(BigDecimal.ONE);
        linea.setCostoBrutoUnitario(BigDecimal.ZERO);
        linea.setCostoNetoUnitario(BigDecimal.ZERO);
        linea.setPrecioUnitario(recargo);
        linea.setSubTotal(recargo);
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    public void doAgregarPago() {
        if (pagoActual.getIdFormaPago() != null) {
            if (pagoActual.getMontoPago().signum() <= 0
                    || pagoActual.getMontoPago().compareTo(venta.getSaldo()) > 0) {
                JSFUtil.addErrorMessage("El monto del pago supera el saldo!");

            } else {

                if (pagoActual.getIdFormaPago().getRequierePlan()) {
                    //Calcular el recargo por el pago
                    //Agregar el producto de recargo
                    //Resguardar el item
                    //Agregar el pago recargado
                    BigDecimal recargo = pagoActual.getMontoPago().multiply(pagoActual.getIdDetallePlan().getCoeficienteInteres()).subtract(pagoActual.getMontoPago()).setScale(2, RoundingMode.HALF_UP);

                    ComprobantesLineas lineaRecargo = crearLineaRecargo(recargo, pagoActual.getIdDetallePlan());
                    venta.getComprobantesLineasList().add(lineaRecargo);
                    pagoActual.setProductoRecargoItem(lineaRecargo.getItem());
                    pagoActual.setMontoPago(pagoActual.getMontoPago().multiply(pagoActual.getIdDetallePlan().getCoeficienteInteres()).setScale(2, RoundingMode.HALF_UP));
                    calcularTotal();

                }

                pagoActual.setItem(itemCounter.getAndIncrement());
                pagoActual.setIdComprobante(venta);
                venta.getPagosList().add(pagoActual);
                pagoActual = new ComprobantesPagos();
                pagoActual.setMontoPago(BigDecimal.ZERO);
                pagoActual.setMontoPagado(BigDecimal.ZERO);
            }
        }
        calcularTotalPagado();
    }

    private void calcularTotalPagado() {
        venta.setSaldo(venta.getTotal());
        BigDecimal sumaPagos = BigDecimal.ZERO;
        for (ComprobantesPagos p : venta.getPagosList()) {
            sumaPagos = sumaPagos.add(p.getMontoPago());
        }
        venta.setSaldo(venta.getTotal().subtract(sumaPagos));
        pagoActual.setMontoPago(venta.getSaldo());
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
        if (venta.getTotal().signum() <= 0) {
            JSFUtil.addErrorMessage("El total del comprobante debe ser mayor que cero.");
            return false;
        }
        if (venta.getComprobantesLineasList() == null || venta.getComprobantesLineasList().isEmpty()) {
            JSFUtil.addErrorMessage("Por favor cargue productos para poder continuar.");
            return false;
        }
        if (venta.getIdCondicionComprobante() != null) {
            if (venta.getIdCondicionComprobante().getPagoTotal()) {
                if (venta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
                    JSFUtil.addErrorMessage("El importe del pago debe cubrir el total de la operación para esta condición.");
                    return false;
                }
            }
        }

        return true;
    }

    private void establecerLetraComprobante() {
        FiscalLetrasComprobantesSearchFilter lsf = new FiscalLetrasComprobantesSearchFilter(
                venta.getIdPersona().getIdResponsabilidadIva(),
                responsabilidadesIvaFacade.find(2L));
        FiscalLetrasComprobantes letra = letrasComprobantesFacade.findFirstBySearchFilter(lsf);
        venta.setLetra(letra.getLetraComprobante());
    }

    public String guardarVenta() {
        if (validarVenta()) {
            establecerLetraComprobante();
            venta.setFechaComprobante(new Date());
            venta.setIdUsuario(authBackingBean.getUserLoggedIn());
            venta.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            try {

                venta.setSaldo(venta.getTotal());
                ventasBean.guardarVenta(venta);

                for (ComprobantesLineas vl : venta.getComprobantesLineasList()) {

                    Productos product = vl.getIdProducto();
                    if (product.getIdTipoProveeduria().getControlStock()) {
                        product.setStockActual(product.getStockActual().subtract(vl.getCantidad().multiply(venta.getTipoComprobante().getSigno())));
                        if (product.getStockActual().signum() < 0) {
                            product.setStockActual(BigDecimal.ZERO);
                        }
                        productosFacade.edit(product);

                    }

                }
                JSFUtil.addInfoMessage("Operación guardada exitosamente!");
                endConversation();
                return "/protected/ventas/index?faces-redirect=true";
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, null, ex);
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

    public Comprobantes getVenta() {
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

    public List<NegocioTiposComprobante> getTiposComprobanteList() {

        return tiposComprobanteFacade.findAllBySearchFilter(tipoCompSf);
    }

    public List<NegocioFormasPago> getFormasPagoList() {
        return formasPagoFacade.findAllBySearchFilter(formasPagoSearchFilter);
    }

    public List<ComprobantesEstados> getVentasEstados() {
        return estadosFacade.findAll();
    }

    public ComprobantesPagos getPagoActual() {
        return pagoActual;
    }

    public void setPagoActual(ComprobantesPagos pagoActual) {
        this.pagoActual = pagoActual;
    }

    public ProductosListasPrecios getLista() {
        return lista;
    }

    public List<NegocioPlanesPago> getPlanesPagoList() {
        planesSearchFilter.setIdFormaPago(pagoActual.getIdFormaPago());
        return planesPagoFacade.findAllBySearchFilter(planesSearchFilter);
    }

    public List<NegocioPlanesPagoDetalle> getDetallePlan() {
        pagoDetalleSearchFilter.setIdPlan(pagoActual.getIdPlan());
        pagoDetalleSearchFilter.addSortField(new SortField("cuotas", true));
        return pagoDetalleFacade.findAllBySearchFilter(pagoDetalleSearchFilter);
    }
}
