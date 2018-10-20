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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "productos", uniqueConstraints = @UniqueConstraint(columnNames = {"codigo_propio"}))
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_producto", columnDefinition = "serial"))
@NamedEntityGraph(name = "precios", attributeNodes = {
    @NamedAttributeNode("porcentajes")
    ,
    @NamedAttributeNode("precios")})

public class Productos extends BaseEntity {

    private static final long serialVersionUID = 3L;

    @Size(max = 100)
    @Column(name = "codigo_propio")
    private String codigoPropio;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2048)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ultima_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_adquisicion_neto", scale = 4, precision = 19)
    private BigDecimal costoAdquisicionNeto;

    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_final", scale = 4, precision = 19)
    private BigDecimal costoFinal;

    @Basic(optional = false)
    @NotNull
    @Column(name = "annos_amortizacion")
    private int annosAmortizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidades_compra_unidades_venta")
    @Min(0)
    private BigDecimal unidadesCompraUnidadesVenta;
    @JoinColumn(name = "id_tipo_unidad_venta", referencedColumnName = "id_tipo_unidad", columnDefinition = "int4")
    @ManyToOne(optional = false)
    //@JoinFetch
    private ProductosTiposUnidades idTipoUnidadVenta;
    @JoinColumn(name = "id_tipo_unidad_compra", referencedColumnName = "id_tipo_unidad", columnDefinition = "int4")
    @ManyToOne(optional = false)
    //@JoinFetch
    private ProductosTiposUnidades idTipoUnidadCompra;
    @JoinColumn(name = "id_tipo_proveeduria", referencedColumnName = "id_tipo_proveeduria", columnDefinition = "int4")
    @ManyToOne(optional = false)
    //@JoinFetch
    private ProductosTiposProveeduria idTipoProveeduria;
    @JoinColumn(name = "id_sub_rubro", referencedColumnName = "id_sub_rubro", columnDefinition = "int4")
    @ManyToOne(optional = false)
    //@JoinFetch
    private ProductosSubRubros idSubRubro;
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro", columnDefinition = "int4")
    @ManyToOne(optional = false)
//    @JoinFetch
    private ProductosRubros idRubro;

    @JoinColumn(name = "id_proveedor_habitual", referencedColumnName = "id_persona", columnDefinition = "int4")
    @ManyToOne
    private Personas idProveedorHabitual;

    @JoinColumn(name = "id_alicuota_iva", referencedColumnName = "id_alicuota_iva", columnDefinition = "int4")
    @ManyToOne(optional = false)
    //  @JoinFetch
    private FiscalAlicuotasIva idAlicuotaIva;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca", columnDefinition = "int4")
    @ManyToOne(optional = false)
    //@JoinFetch
    private ProductosMarcas idMarca;

    @OneToMany(orphanRemoval = true, mappedBy = "idProducto", cascade = CascadeType.ALL)
//    @OrderBy(value = "idListaPrecios")
    private List<ProductosPrecios> precios;

    @OneToMany(mappedBy = "idProducto", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProductosPorcentajes> porcentajes;

    @Size(max = 60)
    @Column(name = "ubicacion")
    private String ubicacion;

    @Size(max = 60)
    @Column(name = "codigo_fabricante")
    private String codigoFabricante;

    @Basic(optional = false)
    @NotNull
    @Column(name = "stock_minimo", scale = 2, precision = 19)
    private BigDecimal stockMinimo;

    public Productos() {
    }

    public Productos(Long idProducto) {
        super(idProducto);
    }

    public Productos(Long idProducto, Date fechaAlta, boolean activo, BigDecimal costoAdquisicionNeto, int annosAmortizacion) {
        super(idProducto);
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.costoAdquisicionNeto = costoAdquisicionNeto;

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

    /**
     * El costo con el cual figura el producto en la lista del proveedor
     *
     * @return
     */
    public BigDecimal getCostoAdquisicionNeto() {
        return costoAdquisicionNeto;
    }

    /**
     * El costo con el cual figura el producto en la lista del proveedor
     *
     * @param costoAdquisicionNeto
     */
    public void setCostoAdquisicionNeto(BigDecimal costoAdquisicionNeto) {
        this.costoAdquisicionNeto = costoAdquisicionNeto;
    }

    public int getAnnosAmortizacion() {
        return annosAmortizacion;
    }

    public void setAnnosAmortizacion(int annosAmortizacion) {
        this.annosAmortizacion = annosAmortizacion;
    }

    /**
     * El coeficiente de cuantas unidades de compra representan a unidades de venta. X unidades de compra son Y unidades
     * de venta. Lo normal es 1.
     *
     * @return
     */
    public BigDecimal getUnidadesCompraUnidadesVenta() {
        return unidadesCompraUnidadesVenta;
    }

    /**
     * El coeficiente de cuantas unidades de compra representan a unidades de venta. X unidades de compra son Y unidades
     * de venta. Lo normal es 1.
     *
     * @param unidadesCompraUnidadesVenta
     */
    public void setUnidadesCompraUnidadesVenta(BigDecimal unidadesCompraUnidadesVenta) {
        this.unidadesCompraUnidadesVenta = unidadesCompraUnidadesVenta;
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

    public ProductosMarcas getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(ProductosMarcas idMarca) {
        this.idMarca = idMarca;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * El costo luego de haber aplicado todos los descuentos y recargos
     *
     * @return
     */
    public BigDecimal getCostoFinal() {
        return costoFinal;
    }

    /**
     * El costo luego de haber aplicado todos los descuentos y recargos
     *
     * @param costoFinal
     */
    public void setCostoFinal(BigDecimal costoFinal) {
        this.costoFinal = costoFinal;
    }

    public List<ProductosPrecios> getPrecios() {
        return precios;
    }

    public void setPrecios(List<ProductosPrecios> precios) {
        this.precios = precios;
    }

    public List<ProductosPorcentajes> getPorcentajes() {
        return porcentajes;
    }

    public void setPorcentajes(List<ProductosPorcentajes> porcentajes) {
        this.porcentajes = porcentajes;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(BigDecimal stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(String codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    @PrePersist
    protected void onCreate() {
        fechaAlta = new Date();
        fechaUltimaModificacion = fechaAlta;
    }

    @PreUpdate
    protected void onUpdate() {
        fechaUltimaModificacion = new Date();
    }
}
