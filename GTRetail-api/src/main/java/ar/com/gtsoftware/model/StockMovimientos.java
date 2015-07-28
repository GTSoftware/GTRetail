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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "stock_movimientos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_movimiento_stock", columnDefinition = "serial"))
public class StockMovimientos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_anterior")
    private BigDecimal cantidadAnterior;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_movimiento")
    private BigDecimal cantidadMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_actual")
    private BigDecimal cantidadActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_movimiento")
    private int idTipoMovimiento;
    @Size(max = 255)
    @Column(name = "observaciones_movimiento")
    private String observacionesMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_total_movimiento")
    private BigDecimal costoTotalMovimiento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "id_deposito_movimiento", referencedColumnName = "id_deposito", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Depositos idDepositoMovimiento;

    public StockMovimientos() {
    }

    public StockMovimientos(Long idMovimientoStock) {
        super(idMovimientoStock);
    }

    public StockMovimientos(Long idMovimientoStock, Date fechaMovimiento, BigDecimal cantidadAnterior, BigDecimal cantidadMovimiento, BigDecimal cantidadActual, int idTipoMovimiento, BigDecimal costoTotalMovimiento) {
        super(idMovimientoStock);
        this.fechaMovimiento = fechaMovimiento;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadMovimiento = cantidadMovimiento;
        this.cantidadActual = cantidadActual;
        this.idTipoMovimiento = idTipoMovimiento;
        this.costoTotalMovimiento = costoTotalMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public BigDecimal getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(BigDecimal cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public BigDecimal getCantidadMovimiento() {
        return cantidadMovimiento;
    }

    public void setCantidadMovimiento(BigDecimal cantidadMovimiento) {
        this.cantidadMovimiento = cantidadMovimiento;
    }

    public BigDecimal getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(BigDecimal cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getObservacionesMovimiento() {
        return observacionesMovimiento;
    }

    public void setObservacionesMovimiento(String observacionesMovimiento) {
        this.observacionesMovimiento = observacionesMovimiento;
    }

    public BigDecimal getCostoTotalMovimiento() {
        return costoTotalMovimiento;
    }

    public void setCostoTotalMovimiento(BigDecimal costoTotalMovimiento) {
        this.costoTotalMovimiento = costoTotalMovimiento;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Depositos getIdDepositoMovimiento() {
        return idDepositoMovimiento;
    }

    public void setIdDepositoMovimiento(Depositos idDepositoMovimiento) {
        this.idDepositoMovimiento = idDepositoMovimiento;
    }

}
