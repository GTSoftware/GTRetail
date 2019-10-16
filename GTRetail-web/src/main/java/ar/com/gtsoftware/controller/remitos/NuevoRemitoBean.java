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
package ar.com.gtsoftware.controller.remitos;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.DepositosService;
import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.bl.RemitoService;
import ar.com.gtsoftware.bl.RemitoTipoMovimientoService;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.DepositosSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.logging.Logger;

/**
 * Maneja la creación de un nuevo remitoDtoCabecera.
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "nuevoRemitoBean")
@ViewScoped
public class NuevoRemitoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(NuevoRemitoBean.class.getName());
    private final RemitoDto newRemito = new RemitoDto();
    private final List<DepositosDto> depositosList = new ArrayList<>();
    private final List<RemitoTipoMovimientoDto> tiposMovimientoList = new ArrayList<>();
    private final ProductosSearchFilter productosFilter = new ProductosSearchFilter(Boolean.TRUE, null, null, null);
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
    @EJB
    private DepositosService depositosFacade;
    @EJB
    private RemitoTipoMovimientoService tipoMovimientoFacade;
    @EJB
    private ProductosService productosFacade;
    @EJB
    private RemitoService remitoFacade;
    @Inject
    private JSFHelper jsfHelper;
    private BigDecimal cantidad = BigDecimal.ONE;
    private ProductosDto productoBusquedaSeleccionado = null;
    private int numeradorLinea = 1;

    /**
     * Creates a new instance of NuevoRemitoBean
     */
    public NuevoRemitoBean() {
    }

    @PostConstruct
    public void init() {
        if (jsfHelper.isPostback()) {
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

    public RemitoDto getNewRemito() {
        return newRemito;
    }

    public List<DepositosDto> getDepositosList() {
        return depositosList;
    }

    public List<RemitoTipoMovimientoDto> getTiposMovimientoList() {
        return tiposMovimientoList;
    }

    public String doGuardarRemito() {

        if (!validarRemito()) {
            return StringUtils.EMPTY;
        }
        Date hoy = new Date();
        newRemito.setFechaAlta(hoy);
        newRemito.setFechaCierre(hoy);
        // Se crea la recepción según los datos ingresados para cerrar el remitoDtoCabecera
        RemitoRecepcionDto recepcion = new RemitoRecepcionDto();
        recepcion.setFecha(hoy);
        recepcion.setIdUsuario(authBackingBean.getUserLoggedIn());
        recepcion.setRemito(newRemito);
        if (newRemito.getIsDestinoInterno()) {
            recepcion.setIdDeposito(newRemito.getIdDestinoPrevistoInterno());
        } else {
            recepcion.setIdPersona(newRemito.getIdDestinoPrevistoExterno());
        }
        newRemito.setRemitoRecepcionesList(Collections.singletonList(recepcion));

        remitoFacade.createOrEdit(newRemito);

        return "/protected/stock/remitos/index.xhtml?faces-redirect=true";

        //jsfUtil.addInfoMessage(String.format("RemitoDto guardado exitosamente ID: %d", newRemito.getId()));
    }

    private boolean validarRemito() {
        if (!newRemito.getIsOrigenInterno() && !newRemito.getIsDestinoInterno()) {
            jsfHelper.addErrorMessage("El remito no puede tener origen y detino externos");
            return false;
        }
        if (CollectionUtils.isEmpty(newRemito.getDetalleList())) {
            jsfHelper.addErrorMessage("El remito debe tener al menos un producto");
            return false;
        }
        if (newRemito.getIsOrigenInterno() && newRemito.getIdOrigenInterno() == null) {
            jsfHelper.addErrorMessage("Debe seleccionar el ORIGEN para el remito");
            return false;
        }
        if (!newRemito.getIsOrigenInterno() && newRemito.getIdOrigenExterno() == null) {
            jsfHelper.addErrorMessage("Debe seleccionar el ORIGEN para el remito");
            return false;
        }
        if (newRemito.getIsDestinoInterno() && newRemito.getIdDestinoPrevistoInterno() == null) {
            jsfHelper.addErrorMessage("Debe seleccionar el DESTINO para el remito");
            return false;
        }
        if (!newRemito.getIsDestinoInterno() && newRemito.getIdDestinoPrevistoExterno() == null) {
            jsfHelper.addErrorMessage("Debe seleccionar el DESTINO para el remito");
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

    public ProductosDto getProductoBusquedaSeleccionado() {
        return productoBusquedaSeleccionado;
    }

    public void setProductoBusquedaSeleccionado(ProductosDto productoBusquedaSeleccionado) {
        this.productoBusquedaSeleccionado = productoBusquedaSeleccionado;
    }

    public void agregarProducto() {

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
            jsfHelper.addErrorMessage(jsfHelper.getBundle("msg").getString("productoNoEncontrado"));
            return;
        }

        newRemito.getDetalleList().add(crearLineaDetalleRemito(producto));
        cantidad = BigDecimal.ONE;
        productosFilter.setCodigoPropio(null);
        productosFilter.setIdProducto(null);

        productoBusquedaSeleccionado = null;
    }

    private RemitoDetalleDto crearLineaDetalleRemito(ProductosDto producto) {
        RemitoDetalleDto detalle = RemitoDetalleDto.builder()
                .cantidad(cantidad)
                .idProducto(producto)
                .remitoCabecera(newRemito)
                .nroLinea(numeradorLinea++)
                .build();

        return detalle;
    }

}
