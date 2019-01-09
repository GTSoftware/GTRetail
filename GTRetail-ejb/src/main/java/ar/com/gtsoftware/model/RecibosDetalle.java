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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "recibos_detalle")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_detalle_recibo"))
public class RecibosDetalle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_recibo", referencedColumnName = "id_recibo")
    private Recibos idRecibo;

    @ManyToOne
    @JoinColumn(name = "id_comprobante_pago", referencedColumnName = "id_pago")
    private ComprobantesPagos idPagoComprobante;

    @NotNull
    @Column(name = "monto_pagado")
    private BigDecimal montoPagado;

    @NotNull
    @Column(name = "monto_pagado_con_signo")
    private BigDecimal montoPagadoConSigno;

    @NotNull
    @Column(name = "redondeo")
    private BigDecimal redondeo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago")
    private NegocioFormasPago idFormaPago;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_valor", referencedColumnName = "id_valor")
    @OneToOne
    private Valores idValor;

    public RecibosDetalle(Long id) {
        super(id);
    }

    public RecibosDetalle() {
    }

    public Recibos getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Recibos idRecibo) {
        this.idRecibo = idRecibo;
    }

    public ComprobantesPagos getIdPagoComprobante() {
        return idPagoComprobante;
    }

    public void setIdPagoComprobante(ComprobantesPagos idPagoComprobante) {
        this.idPagoComprobante = idPagoComprobante;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public Valores getIdValor() {
        return idValor;
    }

    public void setIdValor(Valores idValor) {
        this.idValor = idValor;
    }


    public BigDecimal getMontoPagadoConSigno() {
        return montoPagadoConSigno;
    }

    public void setMontoPagadoConSigno(BigDecimal montoPagadoConSigno) {
        this.montoPagadoConSigno = montoPagadoConSigno;
    }

    @NotNull
    public BigDecimal getRedondeo() {
        return redondeo;
    }

    public void setRedondeo(@NotNull BigDecimal redondeo) {
        this.redondeo = redondeo;
    }
}
