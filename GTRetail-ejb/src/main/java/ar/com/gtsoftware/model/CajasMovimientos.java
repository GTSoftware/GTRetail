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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Movimientos de cajas
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "cajas_movimientos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_movimiento_caja"))
public class CajasMovimientos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @JoinColumn(name = "id_caja", referencedColumnName = "id_caja")
    @ManyToOne
    private Cajas idCaja;

    @NotNull
    @Column(name = "fecha_movimiento")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    @NotNull
    @Column(name = "monto_movimiento")
    private BigDecimal montoMovimiento;

    @Column(name = "descripcion")
    @Size(max = 255)
    private String descripcion;

    public CajasMovimientos(Long id) {
        super(id);
    }

    public CajasMovimientos() {
    }

    public Cajas getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Cajas idCaja) {
        this.idCaja = idCaja;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public BigDecimal getMontoMovimiento() {
        return montoMovimiento;
    }

    public void setMontoMovimiento(BigDecimal montoMovimiento) {
        this.montoMovimiento = montoMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
