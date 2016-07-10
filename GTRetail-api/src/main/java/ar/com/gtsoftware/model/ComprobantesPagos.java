/*
 * Copyright 2016 GT Software.
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
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Los planes de pago elegidos para cada comprobante
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "comprobantes_pagos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_pago"))
public class ComprobantesPagos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
    private Comprobantes idComprobante;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago")
    private NegocioFormasPago idFormaPago;

    @ManyToOne
    @JoinColumn(name = "id_plan", referencedColumnName = "id_plan")
    private NegocioPlanesPago idPlan;

    @ManyToOne
    @JoinColumn(name = "id_detalle_plan", referencedColumnName = "id_detalle_plan")
    private NegocioPlanesPagoDetalle idDetallePlan;

    @NotNull
    @Column(name = "monto_pago")
    private BigDecimal montoPago;

    @NotNull
    @Column(name = "monto_pagado")
    private BigDecimal montoPagado;

    @Column(name = "fecha_pago")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaPago;

    @Column(name = "fecha_ultimo_pago")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaUltimoPago;

    @Transient
    private int item;

    @Transient
    private int productoRecargoItem;

    public ComprobantesPagos(Long id) {
        super(id);
    }

    public ComprobantesPagos() {
    }

    public Comprobantes getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Comprobantes idComprobante) {
        this.idComprobante = idComprobante;
    }

    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public NegocioPlanesPago getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(NegocioPlanesPago idPlan) {
        this.idPlan = idPlan;
    }

    public NegocioPlanesPagoDetalle getIdDetallePlan() {
        return idDetallePlan;
    }

    public void setIdDetallePlan(NegocioPlanesPagoDetalle idDetallePlan) {
        this.idDetallePlan = idDetallePlan;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(Date fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    /**
     * Item del producto de recargo por financiación
     *
     * @return
     */
    public int getProductoRecargoItem() {
        return productoRecargoItem;
    }

    /**
     * Item del producto de recargo por financiación
     *
     * @param productoRecargoItem
     */
    public void setProductoRecargoItem(int productoRecargoItem) {
        this.productoRecargoItem = productoRecargoItem;
    }

}
