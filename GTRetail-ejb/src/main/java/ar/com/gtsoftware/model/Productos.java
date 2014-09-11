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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_producto"))
public class Productos extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
    

    public Productos() {
    }

    public Productos(Long idProducto) {
        super(idProducto);
    }

    public Productos(Long idProducto, Date fechaAlta, boolean activo, BigDecimal costoAdquisicionNeto, BigDecimal utilidad, BigDecimal stockTotal, int annosAmortizacion) {
        super(idProducto);
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.costoAdquisicionNeto = costoAdquisicionNeto;
        this.utilidad = utilidad;
        this.stockTotal = stockTotal;
        this.annosAmortizacion = annosAmortizacion;
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

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Productos[ idProducto=" + this.getId() + " ]";
    }

}
