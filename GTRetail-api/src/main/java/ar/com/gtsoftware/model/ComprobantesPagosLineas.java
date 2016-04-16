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
@Table(name = "comprobantes_pagos_lineas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_linea_pago"))
public class ComprobantesPagosLineas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private BigDecimal importe;
    @JoinColumn(name = "id_pago_comprobante", referencedColumnName = "id_pago_comprobante")
    @ManyToOne(optional = false)
    private ComprobantesPagos idPagoComprobante;
    @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
    @ManyToOne(optional = false)
    private Comprobantes idComprobante;
    @JoinColumn(name = "id_movimiento_caja", referencedColumnName = "id_movimiento_caja", columnDefinition = "int4")
    @ManyToOne(optional = true)
    private CajasMovimientos idCajasMovimientos;

    public ComprobantesPagosLineas() {
    }

    public ComprobantesPagosLineas(Long idLineaPago) {
        super(idLineaPago);
    }

    public ComprobantesPagosLineas(Long idLineaPago, BigDecimal importe) {
        super(idLineaPago);
        this.importe = importe;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public ComprobantesPagos getIdPagoComprobante() {
        return idPagoComprobante;
    }

    public void setIdPagoComprobante(ComprobantesPagos idPagoComprobante) {
        this.idPagoComprobante = idPagoComprobante;
    }

    public Comprobantes getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Comprobantes idComprobante) {
        this.idComprobante = idComprobante;
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
