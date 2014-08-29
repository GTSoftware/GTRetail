/*
 * Copyright 2014 GT Software.
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
import ar.com.gtsoftware.controller.helper.ImportesAlicuotasIVA;
import ar.com.gtsoftware.eao.NegocioCondicionesOperacionesFacade;
import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.NegocioCondicionesOperaciones;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.model.VentasLineas;
import ar.com.gtsoftware.model.VentasPagos;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsofware.bl.VentasBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Named(value = "nuevaVentaBean")
@ConversationScoped
public class NuevaVentaBean implements Serializable {

    @Inject
    private Conversation conversation;

    @EJB
    private PersonasFacade clientesFacade;
    @EJB
    private ProductosFacade productosFacade;
    @EJB
    private NegocioCondicionesOperacionesFacade condicionesOperacionesFacade;
    @EJB
    private NegocioFormasPagoFacade formasPagoFacade;
    @EJB
    private VentasBean ventasBean;
    @Inject
    private AuthBackingBean authBackingBean;
    private Ventas ventaActual;
    private ProductosSearchFilter productoSearchFilter;
    private Productos productoActual;
    private VentasLineas lineaActual; //Linea temporal actual antes de asignarla a la venta
    private List<ImportesAlicuotasIVA> importesIVA = new ArrayList<>();
    private Integer idCliente;
    private VentasPagos pagoActual = new VentasPagos();
    private List<VentasPagos> pagos = new ArrayList<VentasPagos>();

    /**
     * Creates a new instance of NuevaVentaBean
     */
    public NuevaVentaBean() {
    }

    @PostConstruct
    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            if (conversation.isTransient()) {
                conversation.begin();
                productoSearchFilter = new ProductosSearchFilter();
                productoSearchFilter.setActivo(Boolean.TRUE);
                PersonasSearchFilter personasSearchFilter = new PersonasSearchFilter(Boolean.TRUE,
                        Boolean.TRUE, null);
                ventaActual = new Ventas();
                ventaActual.setAnulada(false);
                ventaActual.setSaldo(BigDecimal.ZERO);
                ventaActual.setTotal(BigDecimal.ZERO);
                pagoActual = new VentasPagos();
                inicializarLineaVenta();
            }
        }
    }

    /**
     * Realiza la búsqueda de productos si y solo si el id o el código están
     * seteados
     */
    public void buscarProductoPorClave() {
        productoActual = null;
        if (productoSearchFilter.getCodigoPropio() != null
                || productoSearchFilter.getIdProducto() != null) {
            List<Productos> productos = productosFacade.findBySearchFilter(productoSearchFilter);
            if (!productos.isEmpty()) {
                productoActual = productos.get(0);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha encontrado un producto con ese código!", ""));

            }
        }
    }

    public void buscarClientesPorClave() {
        if (idCliente != null) {
            ventaActual.setIdPersona(clientesFacade.find(idCliente));
        }
    }

    public void buscarProductos() {
        List<Productos> productos = productosFacade.findBySearchFilter(productoSearchFilter);

    }

    /**
     * Calcula el subtotal de la línea de venta
     */
    public void calculatSubTotal() {
        if (validarCantidad()) {
            lineaActual.setSubTotal(lineaActual.getCantidad().multiply(productoActual.getPrecioVenta()).setScale(2, RoundingMode.HALF_UP));
        } else {
            lineaActual.setSubTotal(BigDecimal.ZERO);
        }

    }

    public void agregarLineaVenta() {
        if (productoActual != null) {
            if (lineaActual.getSubTotal().compareTo(BigDecimal.ZERO) != 0) {
                lineaActual.setCantidadEntregada(BigDecimal.ZERO);
                lineaActual.setIdProducto(productoActual);
                lineaActual.setIdVenta(ventaActual);
                lineaActual.setPrecioVentaUnitario(productoActual.getPrecioVenta());
                lineaActual.setCostoBrutoUnitario(productoActual.getCostoAdquisicionNeto());
                lineaActual.setCostoNetoUnitario(productoActual.getCostoAdquisicionNeto());
                if (ventaActual.getVentasLineasList() == null) {
                    ventaActual.setVentasLineasList(new ArrayList<VentasLineas>());
                }
                UUID idOne = UUID.randomUUID();
                lineaActual.setItem(String.valueOf(idOne));
                ventaActual.getVentasLineasList().add(lineaActual);
                calcularTotalVenta();
                inicializarLineaVenta();
            }
        }
    }

    private void inicializarLineaVenta() {
        lineaActual = new VentasLineas();
        lineaActual.setCantidad(BigDecimal.ZERO);
        lineaActual.setSubTotal(BigDecimal.ZERO);
        productoActual = null;
        productoSearchFilter.setIdProducto(0);
        productoSearchFilter.setCodigoPropio("");
    }

    private void calcularTotalVenta() {
        BigDecimal total = BigDecimal.ZERO;
        if (ventaActual.getVentasLineasList() != null) {
            for (VentasLineas linea : ventaActual.getVentasLineasList()) {
                total = total.add(linea.getSubTotal());
            }
        }
        total = total.setScale(2, RoundingMode.HALF_UP);
        ventaActual.setTotal(total);
        ventaActual.setSaldo(total);
        calcularIVA();
    }

    /**
     * Calcula los importes de IVA por cada alícuota
     */
    private void calcularIVA() {
        importesIVA.clear();
        HashMap<FiscalAlicuotasIva, BigDecimal> importe = new HashMap<>();
        for (VentasLineas vl : ventaActual.getVentasLineasList()) {
            FiscalAlicuotasIva alicuota = vl.getIdProducto().getIdAlicuotaIva();
            //Importe*(alicuota/100)
            BigDecimal importeIva = vl.getSubTotal().multiply(alicuota.getValorAlicuota().divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
            if (importe.containsKey(alicuota)) {
                importeIva = importeIva.add(importe.get(alicuota));
            }
            importe.put(alicuota, importeIva);
        }
        for (Entry<FiscalAlicuotasIva, BigDecimal> i : importe.entrySet()) {
            ImportesAlicuotasIVA imp = new ImportesAlicuotasIVA(i.getKey(), i.getValue());
            importesIVA.add(imp);
        }

    }

    /**
     * Valida si la cantidad corresponde con la unidad de venta
     *
     * @return true si la cantidad corresponde con el tipo de unidad
     */
    private boolean validarCantidad() {
        if (productoActual != null) {
            if (lineaActual.getCantidad().compareTo(BigDecimal.ZERO) == 0) {
                return false;
            }
            if (productoActual.getIdTipoUnidadVenta().getCantidadEntera()) {
                if (lineaActual.getCantidad().scale() != 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cantidad debe ser un valor entero!", ""));
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    /**
     * Elimina una lina de venta
     *
     * @param linea
     */
    public void eliminarLinea(VentasLineas linea) {
        int item = -1;

        for (int i = 0; i < ventaActual.getVentasLineasList().size(); i++) {

            if (ventaActual.getVentasLineasList().get(i).getItem().equalsIgnoreCase(linea.getItem())) {
                item = i;
            }
        }
        if (item >= 0) {
            ventaActual.getVentasLineasList().remove(item);
            calcularTotalVenta();
        }
    }

    public void doAgregarPago() {
        if (pagoActual.getIdFormaPago() != null) {
            if (pagoActual.getImporteTotalPagado().compareTo(ventaActual.getSaldo()) > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El monto del pago supera el saldo!"));
            } else {
                UUID idOne = UUID.randomUUID();
                pagoActual.setItem(String.valueOf(idOne));
                pagos.add(pagoActual);
                pagoActual = new VentasPagos();
            }
        }
        calcularTotalPagado();
    }

    private void calcularTotalPagado() {
        ventaActual.setSaldo(ventaActual.getTotal());
        BigDecimal sumaPagos = BigDecimal.ZERO;
        for (VentasPagos p : pagos) {
            sumaPagos = sumaPagos.add(p.getImporteTotalPagado());
        }
        ventaActual.setSaldo(ventaActual.getTotal().subtract(sumaPagos));
    }

    private boolean validarVenta() {
        if (authBackingBean.getUserLoggedIn().getIdSucursal() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El usuario no tiene una sucursal configurada!"));
            return false;
        }
        if (ventaActual.getIdPersona() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Debe cargar un cliente!"));
            return false;
        }
        if (ventaActual.getVentasLineasList() == null || ventaActual.getVentasLineasList().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Debe cargar productos!"));
            return false;
        }
        if (ventaActual.getIdCondicionVenta() != null) {
            if (ventaActual.getIdCondicionVenta().getPagoTotal() && ventaActual.getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                return true;
            } else if (!ventaActual.getIdCondicionVenta().getPagoTotal()) {
                return true;
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El importe del pago debe cubrir el total de la venta!"));
        return false;
    }

    public String guardarVenta() {
        if (validarVenta()) {
            ventaActual.setFechaVenta(GregorianCalendar.getInstance().getTime());
            ventaActual.setIdUsuario(authBackingBean.getUserLoggedIn());
            ventaActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
            try {
                ventaActual.setSaldo(ventaActual.getTotal());
                ventasBean.guardarVenta(ventaActual, pagos);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta", "Venta guardada exitosamente!"));
                endConversation();
                return "/protected/ventas/index";
            } catch (Exception ex) {
                Logger.getLogger(NuevaVentaBean.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ex.getMessage()));
            }
        }
        return null;
    }

    public String cancelar() {
        endConversation();
        return "/protected/ventas/index.xhtml";
    }

    public void eliminarPago(VentasPagos pago) {
        int item = -1;

        for (int i = 0; i < pagos.size(); i++) {

            if (pagos.get(i).getItem().equalsIgnoreCase(pago.getItem())) {
                item = i;
            }
        }
        if (item >= 0) {
            pagos.remove(item);
            calcularTotalPagado();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public ProductosSearchFilter getProductoSearchFilter() {
        return productoSearchFilter;
    }

    public void setProductoSearchFilter(ProductosSearchFilter productoSearchFilter) {
        this.productoSearchFilter = productoSearchFilter;
    }

    public Productos getProductoActual() {
        return productoActual;
    }

    public void setProductoActual(Productos productoActual) {
        this.productoActual = productoActual;
    }

    public VentasLineas getLineaActual() {
        return lineaActual;
    }

    public void setLineaActual(VentasLineas lineaActual) {
        this.lineaActual = lineaActual;
    }

    public Ventas getVentaActual() {
        return ventaActual;
    }

    public void setVentaActual(Ventas ventaActual) {
        this.ventaActual = ventaActual;
    }

    public List<ImportesAlicuotasIVA> getImportesIVA() {
        return importesIVA;
    }

    public void setImportesIVA(List<ImportesAlicuotasIVA> importesIVA) {
        this.importesIVA = importesIVA;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public List<NegocioCondicionesOperaciones> getCondicionesVentaList() {
        return condicionesOperacionesFacade.findAll();
    }

    public List<NegocioFormasPago> getFormasPagoList() {
        return formasPagoFacade.findFormasPagoVenta();
    }

    public VentasPagos getPagoActual() {
        return pagoActual;
    }

    public void setPagoActual(VentasPagos pagoActual) {
        this.pagoActual = pagoActual;
    }

    public List<VentasPagos> getPagos() {
        return pagos;
    }

    public void setPagos(List<VentasPagos> pagos) {
        this.pagos = pagos;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

}
