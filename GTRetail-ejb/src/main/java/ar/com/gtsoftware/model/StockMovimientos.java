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
@NamedQueries({
    @NamedQuery(name = "StockMovimientos.findAll", query = "SELECT s FROM StockMovimientos s"),
    @NamedQuery(name = "StockMovimientos.findByIdMovimientoStock", query = "SELECT s FROM StockMovimientos s WHERE s.idMovimientoStock = :idMovimientoStock"),
    @NamedQuery(name = "StockMovimientos.findByFechaMovimiento", query = "SELECT s FROM StockMovimientos s WHERE s.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "StockMovimientos.findByCantidadAnterior", query = "SELECT s FROM StockMovimientos s WHERE s.cantidadAnterior = :cantidadAnterior"),
    @NamedQuery(name = "StockMovimientos.findByCantidadMovimiento", query = "SELECT s FROM StockMovimientos s WHERE s.cantidadMovimiento = :cantidadMovimiento"),
    @NamedQuery(name = "StockMovimientos.findByCantidadActual", query = "SELECT s FROM StockMovimientos s WHERE s.cantidadActual = :cantidadActual"),
    @NamedQuery(name = "StockMovimientos.findByIdTipoMovimiento", query = "SELECT s FROM StockMovimientos s WHERE s.idTipoMovimiento = :idTipoMovimiento"),
    @NamedQuery(name = "StockMovimientos.findByObservacionesMovimiento", query = "SELECT s FROM StockMovimientos s WHERE s.observacionesMovimiento = :observacionesMovimiento"),
    @NamedQuery(name = "StockMovimientos.findByCostoTotalMovimiento", query = "SELECT s FROM StockMovimientos s WHERE s.costoTotalMovimiento = :costoTotalMovimiento")})
public class StockMovimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimiento_stock")
    private Integer idMovimientoStock;
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
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "id_deposito_movimiento", referencedColumnName = "id_deposito")
    @ManyToOne(optional = false)
    private Depositos idDepositoMovimiento;

    public StockMovimientos() {
    }

    public StockMovimientos(Integer idMovimientoStock) {
        this.idMovimientoStock = idMovimientoStock;
    }

    public StockMovimientos(Integer idMovimientoStock, Date fechaMovimiento, BigDecimal cantidadAnterior, BigDecimal cantidadMovimiento, BigDecimal cantidadActual, int idTipoMovimiento, BigDecimal costoTotalMovimiento) {
        this.idMovimientoStock = idMovimientoStock;
        this.fechaMovimiento = fechaMovimiento;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadMovimiento = cantidadMovimiento;
        this.cantidadActual = cantidadActual;
        this.idTipoMovimiento = idTipoMovimiento;
        this.costoTotalMovimiento = costoTotalMovimiento;
    }

    public Integer getIdMovimientoStock() {
        return idMovimientoStock;
    }

    public void setIdMovimientoStock(Integer idMovimientoStock) {
        this.idMovimientoStock = idMovimientoStock;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimientoStock != null ? idMovimientoStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockMovimientos)) {
            return false;
        }
        StockMovimientos other = (StockMovimientos) object;
        if ((this.idMovimientoStock == null && other.idMovimientoStock != null) || (this.idMovimientoStock != null && !this.idMovimientoStock.equals(other.idMovimientoStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.StockMovimientos[ idMovimientoStock=" + idMovimientoStock + " ]";
    }
    
}
