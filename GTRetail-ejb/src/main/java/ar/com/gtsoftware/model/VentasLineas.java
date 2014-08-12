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
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "ventas_lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasLineas.findAll", query = "SELECT v FROM VentasLineas v"),
    @NamedQuery(name = "VentasLineas.findByIdLineaVenta", query = "SELECT v FROM VentasLineas v WHERE v.idLineaVenta = :idLineaVenta"),
    @NamedQuery(name = "VentasLineas.findByPrecioVentaUnitario", query = "SELECT v FROM VentasLineas v WHERE v.precioVentaUnitario = :precioVentaUnitario"),
    @NamedQuery(name = "VentasLineas.findByCantidad", query = "SELECT v FROM VentasLineas v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentasLineas.findBySubTotal", query = "SELECT v FROM VentasLineas v WHERE v.subTotal = :subTotal"),
    @NamedQuery(name = "VentasLineas.findByCostoNetoUnitario", query = "SELECT v FROM VentasLineas v WHERE v.costoNetoUnitario = :costoNetoUnitario"),
    @NamedQuery(name = "VentasLineas.findByCostoBrutoUnitario", query = "SELECT v FROM VentasLineas v WHERE v.costoBrutoUnitario = :costoBrutoUnitario"),
    @NamedQuery(name = "VentasLineas.findByCantidadEntregada", query = "SELECT v FROM VentasLineas v WHERE v.cantidadEntregada = :cantidadEntregada")})
public class VentasLineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linea_venta")
    private Integer idLineaVenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_venta_unitario")
    private BigDecimal precioVentaUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_neto_unitario")
    private BigDecimal costoNetoUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_bruto_unitario")
    private BigDecimal costoBrutoUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_entregada")
    private BigDecimal cantidadEntregada;
    @OneToMany(mappedBy = "idLineaVentaReferencia")
    private List<VentasLineas> ventasLineasList;
    @JoinColumn(name = "id_linea_venta_referencia", referencedColumnName = "id_linea_venta")
    @ManyToOne
    private VentasLineas idLineaVentaReferencia;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false)
    private Ventas idVenta;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public VentasLineas() {
    }

    public VentasLineas(Integer idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }

    public VentasLineas(Integer idLineaVenta, BigDecimal precioVentaUnitario, BigDecimal cantidad, BigDecimal subTotal, BigDecimal costoNetoUnitario, BigDecimal costoBrutoUnitario, BigDecimal cantidadEntregada) {
        this.idLineaVenta = idLineaVenta;
        this.precioVentaUnitario = precioVentaUnitario;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.costoNetoUnitario = costoNetoUnitario;
        this.costoBrutoUnitario = costoBrutoUnitario;
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getIdLineaVenta() {
        return idLineaVenta;
    }

    public void setIdLineaVenta(Integer idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }

    public BigDecimal getPrecioVentaUnitario() {
        return precioVentaUnitario;
    }

    public void setPrecioVentaUnitario(BigDecimal precioVentaUnitario) {
        this.precioVentaUnitario = precioVentaUnitario;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getCostoNetoUnitario() {
        return costoNetoUnitario;
    }

    public void setCostoNetoUnitario(BigDecimal costoNetoUnitario) {
        this.costoNetoUnitario = costoNetoUnitario;
    }

    public BigDecimal getCostoBrutoUnitario() {
        return costoBrutoUnitario;
    }

    public void setCostoBrutoUnitario(BigDecimal costoBrutoUnitario) {
        this.costoBrutoUnitario = costoBrutoUnitario;
    }

    public BigDecimal getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(BigDecimal cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    @XmlTransient
    public List<VentasLineas> getVentasLineasList() {
        return ventasLineasList;
    }

    public void setVentasLineasList(List<VentasLineas> ventasLineasList) {
        this.ventasLineasList = ventasLineasList;
    }

    public VentasLineas getIdLineaVentaReferencia() {
        return idLineaVentaReferencia;
    }

    public void setIdLineaVentaReferencia(VentasLineas idLineaVentaReferencia) {
        this.idLineaVentaReferencia = idLineaVentaReferencia;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLineaVenta != null ? idLineaVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasLineas)) {
            return false;
        }
        VentasLineas other = (VentasLineas) object;
        if ((this.idLineaVenta == null && other.idLineaVenta != null) || (this.idLineaVenta != null && !this.idLineaVenta.equals(other.idLineaVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasLineas[ idLineaVenta=" + idLineaVenta + " ]";
    }
    
}
