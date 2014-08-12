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
@Table(name = "ventas_pagos_lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasPagosLineas.findAll", query = "SELECT v FROM VentasPagosLineas v"),
    @NamedQuery(name = "VentasPagosLineas.findByIdLineaPago", query = "SELECT v FROM VentasPagosLineas v WHERE v.idLineaPago = :idLineaPago"),
    @NamedQuery(name = "VentasPagosLineas.findByImporte", query = "SELECT v FROM VentasPagosLineas v WHERE v.importe = :importe")})
public class VentasPagosLineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linea_pago")
    private Integer idLineaPago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private BigDecimal importe;
    @JoinColumn(name = "id_pago_venta", referencedColumnName = "id_pago_venta")
    @ManyToOne(optional = false)
    private VentasPagos idPagoVenta;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false)
    private Ventas idVenta;

    public VentasPagosLineas() {
    }

    public VentasPagosLineas(Integer idLineaPago) {
        this.idLineaPago = idLineaPago;
    }

    public VentasPagosLineas(Integer idLineaPago, BigDecimal importe) {
        this.idLineaPago = idLineaPago;
        this.importe = importe;
    }

    public Integer getIdLineaPago() {
        return idLineaPago;
    }

    public void setIdLineaPago(Integer idLineaPago) {
        this.idLineaPago = idLineaPago;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public VentasPagos getIdPagoVenta() {
        return idPagoVenta;
    }

    public void setIdPagoVenta(VentasPagos idPagoVenta) {
        this.idPagoVenta = idPagoVenta;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLineaPago != null ? idLineaPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasPagosLineas)) {
            return false;
        }
        VentasPagosLineas other = (VentasPagosLineas) object;
        if ((this.idLineaPago == null && other.idLineaPago != null) || (this.idLineaPago != null && !this.idLineaPago.equals(other.idLineaPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasPagosLineas[ idLineaPago=" + idLineaPago + " ]";
    }
    
}
