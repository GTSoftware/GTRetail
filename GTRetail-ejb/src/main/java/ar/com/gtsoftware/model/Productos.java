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

package ar.com.gtsoftware.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Productos.findByCodigoPropio", query = "SELECT p FROM Productos p WHERE p.codigoPropio = :codigoPropio"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByFechaAlta", query = "SELECT p FROM Productos p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Productos.findByActivo", query = "SELECT p FROM Productos p WHERE p.activo = :activo"),
    @NamedQuery(name = "Productos.findByCostoAdquisicionNeto", query = "SELECT p FROM Productos p WHERE p.costoAdquisicionNeto = :costoAdquisicionNeto"),
    @NamedQuery(name = "Productos.findByUtilidad", query = "SELECT p FROM Productos p WHERE p.utilidad = :utilidad"),
    @NamedQuery(name = "Productos.findByPrecioVenta", query = "SELECT p FROM Productos p WHERE p.precioVenta = :precioVenta"),
    @NamedQuery(name = "Productos.findByStockTotal", query = "SELECT p FROM Productos p WHERE p.stockTotal = :stockTotal"),
    @NamedQuery(name = "Productos.findByAnnosAmortizacion", query = "SELECT p FROM Productos p WHERE p.annosAmortizacion = :annosAmortizacion"),
    @NamedQuery(name = "Productos.findByUbicacion", query = "SELECT p FROM Productos p WHERE p.ubicacion = :ubicacion")})
public class Productos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Size(max = 100)
    @Column(name = "codigo_propio")
    private String codigoPropio;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_adquisicion_neto")
    private BigDecimal costoAdquisicionNeto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "utilidad")
    private BigDecimal utilidad;
    @Column(name = "precio_venta")
    private BigDecimal precioVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_total")
    private BigDecimal stockTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "annos_amortizacion")
    private int annosAmortizacion;
    @Size(max = 20)
    @Column(name = "ubicacion")
    private String ubicacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<StockMovimientos> stockMovimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<ProductosXCaracteristicas> productosXCaracteristicasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<ProductosImagenes> productosImagenesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<VentasCargosFijos> ventasCargosFijosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<VentasRemitosLineas> ventasRemitosLineasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<ProductosPorcentajes> productosPorcentajesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<VentasLineas> ventasLineasList;
    @JoinColumn(name = "id_tipo_unidad_venta", referencedColumnName = "id_tipo_unidad")
    @ManyToOne(optional = false)
    private ProductosTiposUnidades idTipoUnidadVenta;
    @JoinColumn(name = "id_tipo_unidad_compra", referencedColumnName = "id_tipo_unidad")
    @ManyToOne(optional = false)
    private ProductosTiposUnidades idTipoUnidadCompra;
    @JoinColumn(name = "id_tipo_proveeduria", referencedColumnName = "id_tipo_proveeduria")
    @ManyToOne(optional = false)
    private ProductosTiposProveeduria idTipoProveeduria;
    @JoinColumn(name = "id_sub_rubro", referencedColumnName = "id_sub_rubro")
    @ManyToOne(optional = false)
    private ProductosSubRubros idSubRubro;
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro")
    @ManyToOne(optional = false)
    private ProductosRubros idRubro;
    @JoinColumn(name = "id_proveedor_habitual", referencedColumnName = "id_persona")
    @ManyToOne
    private Personas idProveedorHabitual;
    @JoinColumn(name = "id_alicuota_iva", referencedColumnName = "id_alicuota_iva")
    @ManyToOne(optional = false)
    private FiscalAlicuotasIva idAlicuotaIva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<ProveedoresOrdenesCompraLineas> proveedoresOrdenesCompraLineasList;

    public Productos() {
    }

    public Productos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Productos(Integer idProducto, Date fechaAlta, boolean activo, BigDecimal costoAdquisicionNeto, BigDecimal utilidad, BigDecimal stockTotal, int annosAmortizacion) {
        this.idProducto = idProducto;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.costoAdquisicionNeto = costoAdquisicionNeto;
        this.utilidad = utilidad;
        this.stockTotal = stockTotal;
        this.annosAmortizacion = annosAmortizacion;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoPropio() {
        return codigoPropio;
    }

    public void setCodigoPropio(String codigoPropio) {
        this.codigoPropio = codigoPropio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public BigDecimal getCostoAdquisicionNeto() {
        return costoAdquisicionNeto;
    }

    public void setCostoAdquisicionNeto(BigDecimal costoAdquisicionNeto) {
        this.costoAdquisicionNeto = costoAdquisicionNeto;
    }

    public BigDecimal getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(BigDecimal stockTotal) {
        this.stockTotal = stockTotal;
    }

    public int getAnnosAmortizacion() {
        return annosAmortizacion;
    }

    public void setAnnosAmortizacion(int annosAmortizacion) {
        this.annosAmortizacion = annosAmortizacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @XmlTransient
    public List<StockMovimientos> getStockMovimientosList() {
        return stockMovimientosList;
    }

    public void setStockMovimientosList(List<StockMovimientos> stockMovimientosList) {
        this.stockMovimientosList = stockMovimientosList;
    }

    @XmlTransient
    public List<ProductosXCaracteristicas> getProductosXCaracteristicasList() {
        return productosXCaracteristicasList;
    }

    public void setProductosXCaracteristicasList(List<ProductosXCaracteristicas> productosXCaracteristicasList) {
        this.productosXCaracteristicasList = productosXCaracteristicasList;
    }

    @XmlTransient
    public List<ProductosImagenes> getProductosImagenesList() {
        return productosImagenesList;
    }

    public void setProductosImagenesList(List<ProductosImagenes> productosImagenesList) {
        this.productosImagenesList = productosImagenesList;
    }

    @XmlTransient
    public List<VentasCargosFijos> getVentasCargosFijosList() {
        return ventasCargosFijosList;
    }

    public void setVentasCargosFijosList(List<VentasCargosFijos> ventasCargosFijosList) {
        this.ventasCargosFijosList = ventasCargosFijosList;
    }

    @XmlTransient
    public List<VentasRemitosLineas> getVentasRemitosLineasList() {
        return ventasRemitosLineasList;
    }

    public void setVentasRemitosLineasList(List<VentasRemitosLineas> ventasRemitosLineasList) {
        this.ventasRemitosLineasList = ventasRemitosLineasList;
    }

    @XmlTransient
    public List<ProductosPorcentajes> getProductosPorcentajesList() {
        return productosPorcentajesList;
    }

    public void setProductosPorcentajesList(List<ProductosPorcentajes> productosPorcentajesList) {
        this.productosPorcentajesList = productosPorcentajesList;
    }

    @XmlTransient
    public List<VentasLineas> getVentasLineasList() {
        return ventasLineasList;
    }

    public void setVentasLineasList(List<VentasLineas> ventasLineasList) {
        this.ventasLineasList = ventasLineasList;
    }

    public ProductosTiposUnidades getIdTipoUnidadVenta() {
        return idTipoUnidadVenta;
    }

    public void setIdTipoUnidadVenta(ProductosTiposUnidades idTipoUnidadVenta) {
        this.idTipoUnidadVenta = idTipoUnidadVenta;
    }

    public ProductosTiposUnidades getIdTipoUnidadCompra() {
        return idTipoUnidadCompra;
    }

    public void setIdTipoUnidadCompra(ProductosTiposUnidades idTipoUnidadCompra) {
        this.idTipoUnidadCompra = idTipoUnidadCompra;
    }

    public ProductosTiposProveeduria getIdTipoProveeduria() {
        return idTipoProveeduria;
    }

    public void setIdTipoProveeduria(ProductosTiposProveeduria idTipoProveeduria) {
        this.idTipoProveeduria = idTipoProveeduria;
    }

    public ProductosSubRubros getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(ProductosSubRubros idSubRubro) {
        this.idSubRubro = idSubRubro;
    }

    public ProductosRubros getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(ProductosRubros idRubro) {
        this.idRubro = idRubro;
    }

    public Personas getIdProveedorHabitual() {
        return idProveedorHabitual;
    }

    public void setIdProveedorHabitual(Personas idProveedorHabitual) {
        this.idProveedorHabitual = idProveedorHabitual;
    }

    public FiscalAlicuotasIva getIdAlicuotaIva() {
        return idAlicuotaIva;
    }

    public void setIdAlicuotaIva(FiscalAlicuotasIva idAlicuotaIva) {
        this.idAlicuotaIva = idAlicuotaIva;
    }

    @XmlTransient
    public List<ProveedoresOrdenesCompraLineas> getProveedoresOrdenesCompraLineasList() {
        return proveedoresOrdenesCompraLineasList;
    }

    public void setProveedoresOrdenesCompraLineasList(List<ProveedoresOrdenesCompraLineas> proveedoresOrdenesCompraLineasList) {
        this.proveedoresOrdenesCompraLineasList = proveedoresOrdenesCompraLineasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Productos[ idProducto=" + idProducto + " ]";
    }
    
}
