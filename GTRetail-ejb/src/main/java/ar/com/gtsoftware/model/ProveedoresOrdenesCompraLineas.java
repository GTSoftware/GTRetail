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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "proveedores_ordenes_compra_lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedoresOrdenesCompraLineas.findAll", query = "SELECT p FROM ProveedoresOrdenesCompraLineas p"),
    @NamedQuery(name = "ProveedoresOrdenesCompraLineas.findByIdLinea", query = "SELECT p FROM ProveedoresOrdenesCompraLineas p WHERE p.idLinea = :idLinea"),
    @NamedQuery(name = "ProveedoresOrdenesCompraLineas.findByPrecioCompraUnitario", query = "SELECT p FROM ProveedoresOrdenesCompraLineas p WHERE p.precioCompraUnitario = :precioCompraUnitario"),
    @NamedQuery(name = "ProveedoresOrdenesCompraLineas.findByCantidad", query = "SELECT p FROM ProveedoresOrdenesCompraLineas p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "ProveedoresOrdenesCompraLineas.findBySubTotal", query = "SELECT p FROM ProveedoresOrdenesCompraLineas p WHERE p.subTotal = :subTotal"),
    @NamedQuery(name = "ProveedoresOrdenesCompraLineas.findByCantidadRecibida", query = "SELECT p FROM ProveedoresOrdenesCompraLineas p WHERE p.cantidadRecibida = :cantidadRecibida")})
public class ProveedoresOrdenesCompraLineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linea")
    private Integer idLinea;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_compra_unitario")
    private BigDecimal precioCompraUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @Column(name = "cantidad_recibida")
    private BigDecimal cantidadRecibida;
    @JoinColumn(name = "id_orden_compra", referencedColumnName = "id_orden_compra")
    @ManyToOne(optional = false)
    private ProveedoresOrdenesCompra idOrdenCompra;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public ProveedoresOrdenesCompraLineas() {
    }

    public ProveedoresOrdenesCompraLineas(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public ProveedoresOrdenesCompraLineas(Integer idLinea, BigDecimal precioCompraUnitario, BigDecimal cantidad, BigDecimal subTotal) {
        this.idLinea = idLinea;
        this.precioCompraUnitario = precioCompraUnitario;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public BigDecimal getPrecioCompraUnitario() {
        return precioCompraUnitario;
    }

    public void setPrecioCompraUnitario(BigDecimal precioCompraUnitario) {
        this.precioCompraUnitario = precioCompraUnitario;
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

    public BigDecimal getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(BigDecimal cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public ProveedoresOrdenesCompra getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(ProveedoresOrdenesCompra idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
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
        hash += (idLinea != null ? idLinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedoresOrdenesCompraLineas)) {
            return false;
        }
        ProveedoresOrdenesCompraLineas other = (ProveedoresOrdenesCompraLineas) object;
        if ((this.idLinea == null && other.idLinea != null) || (this.idLinea != null && !this.idLinea.equals(other.idLinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProveedoresOrdenesCompraLineas[ idLinea=" + idLinea + " ]";
    }
    
}
