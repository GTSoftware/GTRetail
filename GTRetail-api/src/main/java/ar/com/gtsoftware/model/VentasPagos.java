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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "ventas_pagos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_pago_venta", columnDefinition = "serial"))
public class VentasPagos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe_total_pagado")
    private BigDecimal importeTotalPagado;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private NegocioFormasPago idFormaPago;
    @JoinColumn(name = "id_movimiento_caja", referencedColumnName = "id_movimiento_caja", columnDefinition = "int4")
    @ManyToOne(optional = true)
    private CajasMovimientos idMovimientoCaja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPagoVenta")
    private List<VentasPagosLineas> ventasPagosLineasList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", nullable = false, columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false, columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", nullable = false, columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;

    @Transient
    private Integer item;

    public VentasPagos() {
    }

    public VentasPagos(Long idPagoVenta) {
        super(idPagoVenta);
    }

    public VentasPagos(Long idPagoVenta, Date fechaPago) {
        super(idPagoVenta);
        this.fechaPago = fechaPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getImporteTotalPagado() {
        return importeTotalPagado;
    }

    public void setImporteTotalPagado(BigDecimal importeTotalPagado) {
        this.importeTotalPagado = importeTotalPagado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public CajasMovimientos getIdMovimientoCaja() {
        return idMovimientoCaja;
    }

    public void setIdMovimientoCaja(CajasMovimientos idMovimientoCaja) {
        this.idMovimientoCaja = idMovimientoCaja;
    }

    @XmlTransient
    public List<VentasPagosLineas> getVentasPagosLineasList() {
        return ventasPagosLineasList;
    }

    public void setVentasPagosLineasList(List<VentasPagosLineas> ventasPagosLineasList) {
        this.ventasPagosLineasList = ventasPagosLineasList;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
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

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasPagos[ idPagoVenta=" + this.getId() + " ]";
    }

}
