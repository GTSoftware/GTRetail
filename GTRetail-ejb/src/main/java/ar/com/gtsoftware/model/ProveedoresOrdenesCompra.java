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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
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
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_estimada_recepcion")
    @Temporal(TemporalType.DATE)
    private Date fechaEstimadaRecepcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_orden_compra")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto_iva_total")
    private BigDecimal totalIVA;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;

    @NotNull
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @NotNull
    @JoinColumn(name = "id_estado_orden_compra", referencedColumnName = "id_estado_orden_compra", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private ProveedoresOrdenesCompraEstados idEstadoOrdenCompra;

    @NotNull
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_persona", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Personas idProveedor;
    @JoinColumn(name = "id_transporte", referencedColumnName = "id_persona", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Personas idTransporte;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenCompra", orphanRemoval = true)
    private List<ProveedoresOrdenesCompraLineas> proveedoresOrdenesCompraLineasList;

    public ProveedoresOrdenesCompra() {
    }

    public ProveedoresOrdenesCompra(Long idOrdenCompra) {
        super(idOrdenCompra);
    }

    public ProveedoresOrdenesCompra(Long idOrdenCompra, Date fechaOrdenCompra, Date fechaEstimadaRecepcion, BigDecimal total, boolean anulada) {
        super(idOrdenCompra);
        this.fechaAlta = fechaOrdenCompra;
        this.fechaEstimadaRecepcion = fechaEstimadaRecepcion;
        this.total = total;

    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
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

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Personas getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Personas idProveedor) {
        this.idProveedor = idProveedor;
    }

    @XmlTransient
    public List<ProveedoresOrdenesCompraLineas> getProveedoresOrdenesCompraLineasList() {
        return proveedoresOrdenesCompraLineasList;
    }

    public void setProveedoresOrdenesCompraLineasList(List<ProveedoresOrdenesCompraLineas> proveedoresOrdenesCompraLineasList) {
        this.proveedoresOrdenesCompraLineasList = proveedoresOrdenesCompraLineasList;
    }

    public BigDecimal getTotalIVA() {
        return totalIVA;
    }

    public void setTotalIVA(BigDecimal totalIVA) {
        this.totalIVA = totalIVA;
    }

    public ProveedoresOrdenesCompraEstados getIdEstadoOrdenCompra() {
        return idEstadoOrdenCompra;
    }

    public void setIdEstadoOrdenCompra(ProveedoresOrdenesCompraEstados idEstadoOrdenCompra) {
        this.idEstadoOrdenCompra = idEstadoOrdenCompra;
    }

    public Personas getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Personas idTransporte) {
        this.idTransporte = idTransporte;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @PreUpdate
    private void preUpdate() {
        this.fechaModificacion = new Date();
    }

    @PrePersist
    private void prePersist() {
        Date today = new Date();
        this.fechaAlta = today;
        this.fechaModificacion = today;
    }
}
