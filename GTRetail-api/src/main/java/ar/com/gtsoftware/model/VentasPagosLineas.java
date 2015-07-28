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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo Tato <rotaotmel@gmail.com>
 */
@Entity
@Table(name = "ventas_pagos_lineas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_linea_pago", columnDefinition = "serial"))
public class VentasPagosLineas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private BigDecimal importe;
    @JoinColumn(name = "id_pago_venta", referencedColumnName = "id_pago_venta", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private VentasPagos idPagoVenta;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Ventas idVenta;
    @JoinColumn(name = "id_movimiento_caja", referencedColumnName = "id_movimiento_caja", columnDefinition = "int4")
    @ManyToOne(optional = true)
    private CajasMovimientos idCajasMovimientos;

    public VentasPagosLineas() {
    }

    public VentasPagosLineas(Long idLineaPago) {
        super(idLineaPago);
    }

    public VentasPagosLineas(Long idLineaPago, BigDecimal importe) {
        super(idLineaPago);
        this.importe = importe;
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

    public CajasMovimientos getIdCajasMovimientos() {
        return idCajasMovimientos;
    }

    public void setIdCajasMovimientos(CajasMovimientos idCajasMovimientos) {
        this.idCajasMovimientos = idCajasMovimientos;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasPagosLineas[ idLineaPago=" + this.getId() + " ]";
    }

}
