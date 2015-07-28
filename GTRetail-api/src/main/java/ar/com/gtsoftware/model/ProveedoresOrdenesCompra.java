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
@Table(name = "proveedores_ordenes_compra")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_orden_compra", columnDefinition = "serial"))
public class ProveedoresOrdenesCompra extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_orden_compra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOrdenCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_estimada_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstimadaRecepcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulada")
    private boolean anulada;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_persona", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Personas idProveedor;
    @JoinColumn(name = "id_condicion_compra", referencedColumnName = "id_condicion", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private NegocioCondicionesOperaciones idCondicionCompra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenCompra")
    private List<ProveedoresOrdenesCompraLineas> proveedoresOrdenesCompraLineasList;

    public ProveedoresOrdenesCompra() {
    }

    public ProveedoresOrdenesCompra(Long idOrdenCompra) {
        super(idOrdenCompra);
    }

    public ProveedoresOrdenesCompra(Long idOrdenCompra, Date fechaOrdenCompra, Date fechaEstimadaRecepcion, BigDecimal total, boolean anulada) {
        super(idOrdenCompra);
        this.fechaOrdenCompra = fechaOrdenCompra;
        this.fechaEstimadaRecepcion = fechaEstimadaRecepcion;
        this.total = total;
        this.anulada = anulada;
    }

    public Date getFechaOrdenCompra() {
        return fechaOrdenCompra;
    }

    public void setFechaOrdenCompra(Date fechaOrdenCompra) {
        this.fechaOrdenCompra = fechaOrdenCompra;
    }

    public Date getFechaEstimadaRecepcion() {
        return fechaEstimadaRecepcion;
    }

    public void setFechaEstimadaRecepcion(Date fechaEstimadaRecepcion) {
        this.fechaEstimadaRecepcion = fechaEstimadaRecepcion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Personas getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Personas idProveedor) {
        this.idProveedor = idProveedor;
    }

    public NegocioCondicionesOperaciones getIdCondicionCompra() {
        return idCondicionCompra;
    }

    public void setIdCondicionCompra(NegocioCondicionesOperaciones idCondicionCompra) {
        this.idCondicionCompra = idCondicionCompra;
    }

    @XmlTransient
    public List<ProveedoresOrdenesCompraLineas> getProveedoresOrdenesCompraLineasList() {
        return proveedoresOrdenesCompraLineasList;
    }

    public void setProveedoresOrdenesCompraLineasList(List<ProveedoresOrdenesCompraLineas> proveedoresOrdenesCompraLineasList) {
        this.proveedoresOrdenesCompraLineasList = proveedoresOrdenesCompraLineasList;
    }

}
