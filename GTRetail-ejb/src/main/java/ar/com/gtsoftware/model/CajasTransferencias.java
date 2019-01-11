/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package ar.com.gtsoftware.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Transferencia de valores entre cajas
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "cajas_transferencias")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_transferencia"))
public class CajasTransferencias extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @JoinColumn(name = "id_caja_origen", referencedColumnName = "id_caja")
    @ManyToOne
    private Cajas idCajaOrigen;


    @NotNull
    @JoinColumn(name = "id_caja_destino", referencedColumnName = "id_caja")
    @ManyToOne
    private Cajas idCajaDestino;

    @NotNull
    @Column(name = "fecha_transferencia")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaTransferencia;

    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "observaciones")
    @Size(max = 90)
    private String observaciones;

    @NotNull
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago")
    @ManyToOne
    private NegocioFormasPago idFormaPago;

    public CajasTransferencias() {
    }

    @NotNull
    public Cajas getIdCajaOrigen() {
        return idCajaOrigen;
    }

    public void setIdCajaOrigen(@NotNull Cajas idCajaOrigen) {
        this.idCajaOrigen = idCajaOrigen;
    }

    @NotNull
    public Cajas getIdCajaDestino() {
        return idCajaDestino;
    }

    public void setIdCajaDestino(@NotNull Cajas idCajaDestino) {
        this.idCajaDestino = idCajaDestino;
    }

    @NotNull
    public Date getFechaTransferencia() {
        return fechaTransferencia;
    }

    public void setFechaTransferencia(@NotNull Date fechaTransferencia) {
        this.fechaTransferencia = fechaTransferencia;
    }

    @NotNull
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(@NotNull BigDecimal monto) {
        this.monto = monto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @NotNull
    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(@NotNull NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }
}
