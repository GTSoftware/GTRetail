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
package ar.com.gtsoftware.controller.remitos;

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
import ar.com.gtsoftware.model.RemitoTipoMovimiento;
import ar.com.gtsoftware.search.DepositosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import static ar.com.gtsoftware.utils.JSFUtil.*;

/**
 * Maneja la creación de un nuevo remito.
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "nuevoRemitoBean")
@ViewScoped
public class NuevoRemitoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
    @EJB
    private DepositosFacade depositosFacade;
    @EJB
    private RemitoTipoMovimientoFacade tipoMovimientoFacade;
    @EJB
    private ProductosFacade productosFacade;

    @EJB
    private RemitoFacade remitoFacade;

    private final Remito newRemito = new Remito();
    private final List<Depositos> depositosList = new ArrayList<>();
    private final List<RemitoTipoMovimiento> tiposMovimientoList = new ArrayList<>();
    private final ProductosSearchFilter productosFilter = new ProductosSearchFilter(Boolean.TRUE, null, null, null);
    private BigDecimal cantidad = BigDecimal.ONE;
    private Productos productoBusquedaSeleccionado = null;
    private int numeradorLinea = 1;

    private static final Logger LOG = Logger.getLogger(NuevoRemitoBean.class.getName());

    /**
     * Creates a new instance of NuevoRemitoBean
     */
    public NuevoRemitoBean() {
    }

    @PostConstruct
    public void init() {
        if (isPostback()) {
            return;
        }
        DepositosSearchFilter dsf = new DepositosSearchFilter();
        dsf.addSortField("nombreDeposito", true);
        depositosList.addAll(depositosFacade.findAllBySearchFilter(dsf));
        newRemito.setIsDestinoInterno(Boolean.TRUE);
        newRemito.setIsOrigenInterno(Boolean.FALSE);
        newRemito.setDetalleList(new ArrayList<>());
        newRemito.setIdUsuario(authBackingBean.getUserLoggedIn());

        tiposMovimientoList.addAll(tipoMovimientoFacade.findAll());

    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    public Remito getNewRemito() {
        return newRemito;
    }

    public List<Depositos> getDepositosList() {
        return depositosList;
    }

    public List<RemitoTipoMovimiento> getTiposMovimientoList() {
        return tiposMovimientoList;
    }

    public String doGuardarRemito() {

        if (!validarRemito()) {
            return StringUtils.EMPTY;
        }
        Date hoy = new Date();
        newRemito.setFechaAlta(hoy);
        newRemito.setFechaCierre(hoy);
        // Se crea la recepción según los datos ingresados para cerrar el remito
        RemitoRecepcion recepcion = new RemitoRecepcion();
        recepcion.setFecha(hoy);
        recepcion.setIdUsuario(authBackingBean.getUserLoggedIn());
        recepcion.setRemito(newRemito);
        if (newRemito.getIsDestinoInterno()) {
            recepcion.setIdDeposito(newRemito.getIdDestinoPrevistoInterno());
        } else {
            recepcion.setIdPersona(newRemito.getIdDestinoPrevistoExterno());
        }
        newRemito.setRemitoRecepcionesList(Arrays.asList(recepcion));

        remitoFacade.createOrEdit(newRemito);

        return "/protected/stock/remitos/index.xhtml?faces-redirect=true";

        //jsfUtil.addInfoMessage(String.format("Remito guardado exitosamente ID: %d", newRemito.getId()));
    }

    public boolean validarRemito() {
        if (!newRemito.getIsOrigenInterno() && !newRemito.getIsDestinoInterno()) {
            addErrorMessage("El remito no puede tener origen y detino externos");
            return false;
        }
        if (CollectionUtils.isEmpty(newRemito.getDetalleList())) {
            addErrorMessage("El remito debe tener al menos un producto");
            return false;
        }
        if (newRemito.getIsOrigenInterno() && newRemito.getIdOrigenInterno() == null) {
            addErrorMessage("Debe seleccionar el ORIGEN para el remito");
            return false;
        }
        if (!newRemito.getIsOrigenInterno() && newRemito.getIdOrigenExterno() == null) {
            addErrorMessage("Debe seleccionar el ORIGEN para el remito");
            return false;
        }
        if (newRemito.getIsDestinoInterno() && newRemito.getIdDestinoPrevistoInterno() == null) {
            addErrorMessage("Debe seleccionar el DESTINO para el remito");
            return false;
        }
        if (!newRemito.getIsDestinoInterno() && newRemito.getIdDestinoPrevistoExterno() == null) {
            addErrorMessage("Debe seleccionar el DESTINO para el remito");
            return false;
        }
        return true;
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
    }

    public void agregarProducto() {

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

        newRemito.getDetalleList().add(crearLineaDetalleRemito(producto));
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

        productoBusquedaSeleccionado = null;
    }

    private RemitoDetalle crearLineaDetalleRemito(Productos producto) {
        RemitoDetalle detalle = new RemitoDetalle();
        detalle.setCantidad(cantidad);
        detalle.setIdProducto(producto);
        detalle.setRemitoCabecera(newRemito);
        detalle.setNroLinea(numeradorLinea++);
        return detalle;
    }

}
