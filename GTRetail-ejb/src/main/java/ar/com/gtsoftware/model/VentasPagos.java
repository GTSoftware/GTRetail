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
@Table(name = "ventas_pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasPagos.findAll", query = "SELECT v FROM VentasPagos v"),
    @NamedQuery(name = "VentasPagos.findByIdPagoVenta", query = "SELECT v FROM VentasPagos v WHERE v.idPagoVenta = :idPagoVenta"),
    @NamedQuery(name = "VentasPagos.findByFechaPago", query = "SELECT v FROM VentasPagos v WHERE v.fechaPago = :fechaPago"),
    @NamedQuery(name = "VentasPagos.findByImporteTotalPagado", query = "SELECT v FROM VentasPagos v WHERE v.importeTotalPagado = :importeTotalPagado"),
    @NamedQuery(name = "VentasPagos.findByObservaciones", query = "SELECT v FROM VentasPagos v WHERE v.observaciones = :observaciones")})
public class VentasPagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pago_venta")
    private Integer idPagoVenta;
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
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago")
    @ManyToOne(optional = false)
    private NegocioFormasPago idFormaPago;
    @JoinColumn(name = "id_movimiento_caja", referencedColumnName = "id_movimiento_caja")
    @ManyToOne(optional = false)
    private CajasMovimientos idMovimientoCaja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPagoVenta")
    private List<VentasPagosLineas> ventasPagosLineasList;

    public VentasPagos() {
    }

    public VentasPagos(Integer idPagoVenta) {
        this.idPagoVenta = idPagoVenta;
    }

    public VentasPagos(Integer idPagoVenta, Date fechaPago) {
        this.idPagoVenta = idPagoVenta;
        this.fechaPago = fechaPago;
    }

    public Integer getIdPagoVenta() {
        return idPagoVenta;
    }

    public void setIdPagoVenta(Integer idPagoVenta) {
        this.idPagoVenta = idPagoVenta;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagoVenta != null ? idPagoVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasPagos)) {
            return false;
        }
        VentasPagos other = (VentasPagos) object;
        if ((this.idPagoVenta == null && other.idPagoVenta != null) || (this.idPagoVenta != null && !this.idPagoVenta.equals(other.idPagoVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasPagos[ idPagoVenta=" + idPagoVenta + " ]";
    }
    
}
