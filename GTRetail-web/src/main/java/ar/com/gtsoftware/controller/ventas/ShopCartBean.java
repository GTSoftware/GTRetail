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
package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.auth.Roles;
import ar.com.gtsoftware.bl.*;
import ar.com.gtsoftware.dto.RegistroVentaDto;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.rules.TipoAccion;
import ar.com.gtsoftware.search.*;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.CellEditEvent;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.gtsoftware.utils.JSFUtil.*;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.contains;

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
    private static final String ID_LISTA_PARAM = "venta.pos.id_lista";
    private static final String CANT_DECIMALES_REDONDEO_PARAM = "venta.pos.redondear.cant_decimales";
    private static final String ID_PRODUCTO_REDONDEO_PARAM = "venta.pos.redondeo.id_producto";
    private static final String ID_CLIENTE_DEFECTO_PARAM = "venta.pos.id_cliente.defecto";
    private static final String ID_CONDICION_DEFECTO_PARAM = "venta.pos.id_condicion.defecto";
    private static final String ID_FORMA_PAGO_DEFECTO_PARAM = "venta.pos.id_forma_pago.defecto";
    private static final BigDecimal CIEN = new BigDecimal(100);

    private final ProductosSearchFilter productosFilter = ProductosSearchFilter.builder()
            .activo(true)
            .puedeVenderse(true)
            .conStock(true).build();
    private final NegocioTiposComprobanteSearchFilter tipoCompSf = NegocioTiposComprobanteSearchFilter.builder()
            .activo(true).build();
    private final FormasPagoSearchFilter formasPagoSearchFilter = FormasPagoSearchFilter.builder()
            .disponibleVenta(true).build();
    private final AtomicInteger itemCounter = new AtomicInteger(1);
    private final List<NegocioPlanesPagoDetalleDto> detallePlan = new ArrayList<>();

    @Inject
    private Conversation conversation;
    @Inject
    private AuthBackingBean authBackingBean;
    @EJB
    private ProductosService productosFacade;
    @EJB
    private ParametrosService parametrosFacade;
    @EJB
    private ProductosListasPreciosService listasPreciosFacade;
    @EJB
    private PersonasService personasFacade;
    @EJB
    private NegocioCondicionesOperacionesService condicionesOperacionesFacade;
    @EJB
    private NegocioFormasPagoService formasPagoFacade;

    @EJB
    private NegocioTiposComprobanteService tiposComprobanteFacade;

    @EJB
    private NegocioPlanesPagoService planesPagoFacade;
    @EJB
    private NegocioPlanesPagoDetalleService pagoDetalleFacade;
    @EJB
    private VentasService ventasService;
    @EJB
    private OfertasService ofertasService;

    private OfertasHelper ofertasHelper;
    ComprobantesPagosDto pagoActual = new ComprobantesPagosDto();
    private BigDecimal cantidad = BigDecimal.ONE;
    private ComprobantesDto venta;
    private ProductosDto productoBusquedaSeleccionado = null;
    private ProductosListasPreciosDto lista;
    private ProductosDto productoRedondeo;
    NegocioFormasPagoDto formaPagoDefecto = null;
    private int cantDecimalesRedondeo = 2;
    private int redondeoIndex = 0;
    private boolean vendedor = false;

    /**
     * Creates a new instance of ShopCartBean
     */
    public ShopCartBean() {
    }

    /**
     * Inicializa la conversación
     */
    public void initConversation() {
        if (!isPostback() && conversation.isTransient()) {
            conversation.begin();
            venta = new ComprobantesDto();
            venta.setIdUsuario(authBackingBean.getUserLoggedIn());
            venta.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            venta.setFechaComprobante(new Date());
            venta.setTotal(BigDecimal.ZERO);
            venta.setPagosList(new ArrayList<>());

            ParametrosDto listaParam = parametrosFacade.findParametroByName(ID_LISTA_PARAM);
            ParametrosDto cantDecimalesParam = parametrosFacade.findParametroByName(CANT_DECIMALES_REDONDEO_PARAM);
            ParametrosDto idProdRedondeoParam = parametrosFacade.findParametroByName(ID_PRODUCTO_REDONDEO_PARAM);
            ParametrosDto idClienteParam = parametrosFacade.findParametroByName(ID_CLIENTE_DEFECTO_PARAM);
            ParametrosDto idCondicionParam = parametrosFacade.findParametroByName(ID_CONDICION_DEFECTO_PARAM);
            ParametrosDto idFormaPagoParam = parametrosFacade.findParametroByName(ID_FORMA_PAGO_DEFECTO_PARAM);

            lista = listasPreciosFacade.find(Long.parseLong(listaParam.getValorParametro()));
            cantDecimalesRedondeo = cantDecimalesParam == null ? 2 : Integer.parseInt(cantDecimalesParam.getValorParametro());
            productoRedondeo = idProdRedondeoParam == null ? null : productosFacade.find(Long.parseLong(idProdRedondeoParam.getValorParametro()));
            venta.setIdPersona(personasFacade.find(Long.parseLong(idClienteParam.getValorParametro())));
            venta.setIdCondicionComprobante(condicionesOperacionesFacade.find(Long.parseLong(idCondicionParam.getValorParametro())));
            formaPagoDefecto = formasPagoFacade.find(Long.parseLong(idFormaPagoParam.getValorParametro()));
            venta.setTipoComprobante(tiposComprobanteFacade.getTipoFactura());

            vendedor = isUserInRole(Roles.VENDEDORES);

            ofertasHelper = new OfertasHelper(ofertasService, this);
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

    private ComprobantesLineasDto getItem(final int nroItem) {
        Optional<ComprobantesLineasDto> first = venta.getComprobantesLineasList().stream().filter(x -> x.getItem() == nroItem).findFirst();
        return first.orElse(null);
    }

    public void removeFromCart(int nroItem) {

        List<ComprobantesLineasDto> comprobantesLineasList = venta.getComprobantesLineasList();

        int index = comprobantesLineasList.indexOf(ComprobantesLineasDto.builder().item(nroItem).build());
        ComprobantesLineasDto lineaParaBorrar = comprobantesLineasList.get(index);

        eliminarLineasRelacionadas(lineaParaBorrar);

        if (lineaParaBorrar.getNroItemAsociado() != null) {
            ComprobantesLineasDto itemAsociado = ComprobantesLineasDto.builder().item(lineaParaBorrar.getNroItemAsociado()).build();
            comprobantesLineasList.remove(itemAsociado);
        }

        comprobantesLineasList.remove(lineaParaBorrar);
        calcularTotal();

    }

    public void eliminarPago(int item) {
        int index = -1;
        int cont = 0;

        for (ComprobantesPagosDto vp : venta.getPagosList()) {
            if (vp.getItem() == item) {
                index = cont;
                break;
            }
            cont++;
        }
        if (index >= 0) {
            ComprobantesPagosDto pago = venta.getPagosList().get(index);
            if (pago.getProductoRecargoItem() > 0) {
                removeFromCart(pago.getProductoRecargoItem());
            }
            venta.getPagosList().remove(index);
            calcularTotalPagado();
        }
    }

    public void initPagos() {
        if (!isPostback()) {
            clearPagos();
            if (formaPagoDefecto != null) {
                pagoActual.setMontoPago(venta.getTotal());
                pagoActual.setMontoPagado(BigDecimal.ZERO);
                pagoActual.setIdFormaPago(formaPagoDefecto);
                venta.setSaldo(venta.getTotal());
                doAgregarPago();
            }
        }
    }

    private void clearPagos() {
        List<ComprobantesPagosDto> pagosList = venta.getPagosList();

        if (!pagosList.isEmpty()) {
            for (ComprobantesPagosDto pago : pagosList) {
                int productoRecargoItem = pago.getProductoRecargoItem();
                if (productoRecargoItem > 0) {
                    removeFromCart(productoRecargoItem);
                }
            }
            pagosList.clear();
        }
    }

    public String volverPasoInicialEnPago() {
        clearPagos();
        return "index.xhtml?faces-redirect=true";
    }

    public void addToCart() {

        if (cantidad.signum() == 0) {
            addErrorMessage("La cantidad no puede ser cero");
        }

        ProductosDto producto;
        if (productoBusquedaSeleccionado != null) {
            producto = productoBusquedaSeleccionado;
        } else {
            if (productosFilter.getIdProducto() == null && isEmpty(productosFilter.getCodigoPropio())) {
                return;
            }
            productosFilter.setIdListaPrecio(lista.getId());
            productosFilter.setIdSucursal(venta.getIdSucursal().getId());

            producto = productosFacade.findFirstBySearchFilter(productosFilter);
        }

        if (producto == null) {
            addErrorMessage(getBundle("msg").getString("productoNoEncontrado"));
            return;
        }

        if (producto.getPrecioVenta() == null) {
            addErrorMessage(getBundle("msg").getString("productoSinPrecio"));
            return;
        }
        ComprobantesLineasDto linea = crearLinea(producto);
        venta.addLineaVenta(linea);

        ofertasHelper.ejecutarReglasOferta(linea);

        calcularTotal();

        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

        productoBusquedaSeleccionado = null;
    }

    private ComprobantesLineasDto crearLinea(ProductosDto prod) {
        ComprobantesLineasDto linea = new ComprobantesLineasDto();
        linea.setIdComprobante(venta);
        linea.setCantidad(cantidad.setScale(2, RoundingMode.HALF_UP));
        linea.setDescripcion(prod.getDescripcion());
        linea.setIdProducto(prod);
        linea.setCantidadEntregada(BigDecimal.ZERO);
        if (!prod.getIdTipoProveeduria().isControlStock()) {
            linea.setCantidadEntregada(linea.getCantidad());
        }
        linea.setCostoBrutoUnitario(prod.getCostoAdquisicionNeto());
        linea.setCostoNetoUnitario(prod.getCostoFinal());
        linea.setPrecioUnitario(prod.getPrecioVenta());
        linea.setSubTotal(linea.getCantidad().multiply(linea.getIdProducto().getPrecioVenta()));
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    public void calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        eliminarRedondeo();
        for (ComprobantesLineasDto vl : venta.getComprobantesLineasList()) {

            vl.setSubTotal(vl.getCantidad().multiply(vl.getPrecioUnitario()));
            total = total.add(vl.getSubTotal());

        }

        BigDecimal totalRedondeado = total.setScale(cantDecimalesRedondeo, RoundingMode.HALF_UP);
        BigDecimal redondeo = totalRedondeado.subtract(total).setScale(cantDecimalesRedondeo, RoundingMode.HALF_UP);
        if (redondeo.signum() > 0 && productoRedondeo != null) {
            cargarRedondeo(redondeo);
        }

        venta.setTotal(totalRedondeado);
    }

    private void cargarRedondeo(BigDecimal redondeo) {
        ComprobantesLineasDto lineaRedondeo = crearLineaRedondeo(redondeo);
        redondeoIndex = lineaRedondeo.getItem();
        venta.addLineaVenta(lineaRedondeo);

    }

    private void eliminarRedondeo() {
        if (redondeoIndex > 0) {
            int cont = 0;
            int index = -1;
            for (ComprobantesLineasDto vl : venta.getComprobantesLineasList()) {
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

    private ComprobantesLineasDto crearLineaRedondeo(BigDecimal redondeo) {
        ComprobantesLineasDto linea = new ComprobantesLineasDto();
        linea.setIdComprobante(venta);
        linea.setCantidad(BigDecimal.ONE);
        linea.setDescripcion(productoRedondeo.getDescripcion());
        linea.setIdProducto(productoRedondeo);
        linea.setCantidadEntregada(BigDecimal.ONE);
        linea.setCostoBrutoUnitario(BigDecimal.ZERO);
        linea.setCostoNetoUnitario(BigDecimal.ZERO);
        linea.setPrecioUnitario(redondeo);
        linea.setSubTotal(redondeo);
        linea.setPermiteModificarCantidad(false);
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    private ComprobantesLineasDto crearLineaRecargo(BigDecimal recargo, NegocioPlanesPagoDetalleDto detallePlan) {
        ComprobantesLineasDto linea = new ComprobantesLineasDto();
        linea.setIdComprobante(venta);
        linea.setCantidad(BigDecimal.ONE);
        linea.setDescripcion(String.format("COSTO FINANCIERO POR %s EN: %s CUOTAS", detallePlan.getIdPlan().getNombre(),
                detallePlan.getCuotas()));
        linea.setIdProducto(productoRedondeo);
        linea.setCantidadEntregada(BigDecimal.ONE);
        linea.setCostoBrutoUnitario(BigDecimal.ZERO);
        linea.setCostoNetoUnitario(BigDecimal.ZERO);
        linea.setPrecioUnitario(recargo);
        linea.setSubTotal(recargo);
        linea.setPermiteModificarCantidad(false);
        linea.setItem(itemCounter.getAndIncrement());
        return linea;
    }

    public void doAgregarPago() {
        if (pagoActual.getIdFormaPago() != null) {
            if (pagoActual.getMontoPago().signum() <= 0
                    || pagoActual.getMontoPago().compareTo(venta.getSaldo()) > 0) {
                addErrorMessage("El monto del pago supera el saldo!");

            } else {

                if (pagoActual.getIdFormaPago().isRequierePlan()) {
                    //Calcular el recargo por el pago
                    //Agregar el producto de recargo
                    //Resguardar el item
                    //Agregar el pago recargado
                    BigDecimal recargo = pagoActual.getMontoPago().multiply(pagoActual.getIdDetallePlan().getCoeficienteInteres()).subtract(pagoActual.getMontoPago()).setScale(2, RoundingMode.HALF_UP);

                    ComprobantesLineasDto lineaRecargo = crearLineaRecargo(recargo, pagoActual.getIdDetallePlan());
                    venta.getComprobantesLineasList().add(lineaRecargo);
                    pagoActual.setProductoRecargoItem(lineaRecargo.getItem());
                    pagoActual.setMontoPago(pagoActual.getMontoPago().multiply(pagoActual.getIdDetallePlan().getCoeficienteInteres()).setScale(2, RoundingMode.HALF_UP));
                    calcularTotal();

                }

                pagoActual.setItem(itemCounter.getAndIncrement());
                pagoActual.setIdComprobante(venta);
                venta.getPagosList().add(pagoActual);
                pagoActual = new ComprobantesPagosDto();
                pagoActual.setMontoPago(BigDecimal.ZERO);
                pagoActual.setMontoPagado(BigDecimal.ZERO);
            }
        }
        calcularTotalPagado();
    }

    private void calcularTotalPagado() {
        venta.setSaldo(venta.getTotal());
        BigDecimal sumaPagos = BigDecimal.ZERO;
        for (ComprobantesPagosDto p : venta.getPagosList()) {
            sumaPagos = sumaPagos.add(p.getMontoPago());
        }
        venta.setSaldo(venta.getTotal().subtract(sumaPagos));
        pagoActual.setMontoPago(venta.getSaldo());
    }

    public String siguientePasoDesdePos() {
        if (!validarCantidades()) {
            return StringUtils.EMPTY;
        }
        return "cliente.xhtml?faces-redirect=true";
    }

    private boolean validarVenta() {
        if (authBackingBean.getUserLoggedIn().getIdSucursal() == null) {
            addErrorMessage("El usuario no tiene una sucursal configurada. Por favor configure el usuario.");
            return false;
        }
        if (venta.getIdPersona() == null) {
            addErrorMessage("Por favor cargue un cliente para poder continuar.");
            return false;
        }
        if (venta.getTotal().signum() <= 0) {
            addErrorMessage("El total del comprobante debe ser mayor que cero.");
            return false;
        }
        if (venta.getComprobantesLineasList() == null || venta.getComprobantesLineasList().isEmpty()) {
            addErrorMessage("Por favor cargue productos para poder continuar.");
            return false;
        }

        if (venta.getIdCondicionComprobante() != null) {
            if (venta.getIdCondicionComprobante().isPagoTotal()) {
                if (venta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
                    addErrorMessage("El importe del pago debe cubrir el total de la operación para esta condición.");
                    return false;
                }
            }
        }

        return true;
    }

    private boolean validarCantidades() {
        for (ComprobantesLineasDto linea : venta.getComprobantesLineasList()) {
            if (linea.getCantidad().signum() <= 0) {
                addErrorMessage(String.format("El producto: %d no puede tener cantidad 0 o inferior.", linea.getIdProducto().getId()));
                return false;
            }
            BigDecimal parteDecimal = linea.getCantidad().subtract(linea.getCantidad().setScale(0, RoundingMode.DOWN));
            if (parteDecimal.signum() != 0 && linea.getIdProducto().getIdTipoUnidadVenta().isCantidadEntera()) {
                addErrorMessage(String.format("El producto: %d no admite cantidades fraccionadas.", linea.getIdProducto().getId()));
                return false;
            }
        }
        return true;
    }

    public String guardarVenta() {
        if (validarVenta()) {


            venta.setIdUsuario(authBackingBean.getUserLoggedIn());
            venta.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            try {


                RegistroVentaDto registroVentaDto = ventasService.guardarVenta(venta, true);

                addInfoMessage("Operación: " + registroVentaDto.getIdComprobante()
                        + " guardada exitosamente");
                endConversation();
                return String.format("/protected/ventas/vista/verVenta.xhtml?idComprobante=%d&faces-redirect=true", registroVentaDto.getIdComprobante());
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
                addErrorMessage(ex.getMessage());
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

    public ComprobantesDto getVenta() {
        return venta;
    }

    protected void setVenta(ComprobantesDto venta) {
        this.venta = venta;
    }

    public ProductosDto getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(ProductosDto productoBusquedaSeleccionado) {
        this.productoBusquedaSeleccionado = productoBusquedaSeleccionado;
    }

    public List<NegocioCondicionesOperacionesDto> getCondicionesVentaList() {
        return condicionesOperacionesFacade.findAll();
    }

    public List<NegocioTiposComprobanteDto> getTiposComprobanteList() {

        return tiposComprobanteFacade.findAllBySearchFilter(tipoCompSf);
    }

    public List<NegocioFormasPagoDto> getFormasPagoList() {
        return formasPagoFacade.findAllBySearchFilter(formasPagoSearchFilter);
    }

    public ComprobantesPagosDto getPagoActual() {
        return pagoActual;
    }

    public void setPagoActual(ComprobantesPagosDto pagoActual) {
        this.pagoActual = pagoActual;
    }

    public ProductosListasPreciosDto getLista() {
        return lista;
    }

    public List<NegocioPlanesPagoDto> getPlanesPagoList() {
        PlanesPagoSearchFilter planesSearchFilter = PlanesPagoSearchFilter.builder().activo(true)
                .idFormaPago(pagoActual.getIdFormaPago().getId()).build();
        return planesPagoFacade.findAllBySearchFilter(planesSearchFilter);
    }

    public List<NegocioPlanesPagoDetalleDto> getDetallePlan() {
        return detallePlan;
    }

    public void cargarDetallePlan(ValueChangeEvent event) {
        detallePlan.clear();
        NegocioPlanesPagoDto plan = (NegocioPlanesPagoDto) event.getNewValue();
        if (plan != null) {
            PlanesPagoDetalleSearchFilter psf = PlanesPagoDetalleSearchFilter.builder()
                    .activo(true)
                    .idPlan(plan.getId()).build();
            psf.addSortField("cuotas", true);
            detallePlan.addAll(pagoDetalleFacade.findAllBySearchFilter(psf));
        }
    }

    public boolean isVendedor() {
        return vendedor;
    }

    /**
     * Este método es llamado desde las reglas de ofertas. Agrega una línea de descuento a la venta.
     *
     * @param nroItem
     * @param tipoAccion
     * @param descuento
     * @param texto
     */
    public void aplicarDescuento(int nroItem, TipoAccion tipoAccion, BigDecimal descuento, String texto) {
        ComprobantesLineasDto itemConDescuento = getItem(nroItem);
        if (itemConDescuento == null) {
            return;
        }

        BigDecimal montoDescuento = null;
        //Construir el ìtem de descuento
        switch (tipoAccion) {
            case DESCUENTO_MONTO_FIJO:
                //Setear el monto en el nuevo item creado
                montoDescuento = descuento;
                break;
            case DESCUENTO_PORCENTAJE:
                //Calcular el monto y setearlo en el item
                montoDescuento = itemConDescuento.getSubTotal().multiply(descuento.divide(CIEN)).setScale(2, RoundingMode.HALF_UP);
                break;
        }
        montoDescuento = montoDescuento.negate();
        ComprobantesLineasDto lineaDescuento = ComprobantesLineasDto.builder()
                .idComprobante(venta)
                .cantidad(BigDecimal.ONE)
                .descripcion(StringUtils.left("[" + itemConDescuento.getIdProducto().getId() + "] " + texto, 90))
                .idProducto(productoRedondeo)
                .cantidadEntregada(BigDecimal.ONE)
                .costoBrutoUnitario(BigDecimal.ZERO)
                .costoNetoUnitario(BigDecimal.ZERO)
                .precioUnitario(montoDescuento)
                .subTotal(montoDescuento)
                .permiteModificarCantidad(false)
                .nroItemAsociado(itemConDescuento.getItem())
                .item(itemCounter.getAndIncrement()).build();


        venta.addLineaVenta(lineaDescuento);
        calcularTotal();

    }

    public void setProductoRedondeo(ProductosDto productoRedondeo) {
        this.productoRedondeo = productoRedondeo;
    }

    public void onCellEditLinea(CellEditEvent event) {
        if (contains(event.getColumn().getHeaderText(), "Cantidad")) {
            ComprobantesLineasDto linea = venta.getComprobantesLineasList().get(event.getRowIndex());

            eliminarLineasRelacionadas(linea);

            linea.setSubTotal(linea.getCantidad().multiply(linea.getPrecioUnitario()));

            ofertasHelper.ejecutarReglasOferta(linea);
        }
        calcularTotal();
    }

    private void eliminarLineasRelacionadas(ComprobantesLineasDto linea) {
        List<ComprobantesLineasDto> lineasRelacionadas = new ArrayList<>(1);
        List<ComprobantesLineasDto> comprobantesLineasList = venta.getComprobantesLineasList();

        for (ComprobantesLineasDto lineasDto : comprobantesLineasList) {
            if (lineasDto.getNroItemAsociado() != null
                    && lineasDto.getNroItemAsociado().equals(linea.getItem())) {
                lineasRelacionadas.add(lineasDto);
            }
        }
        comprobantesLineasList.removeAll(lineasRelacionadas);
    }
}
