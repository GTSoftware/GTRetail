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
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cajas_movimientos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_movimiento_caja", columnDefinition = "serial"))
public class CajasMovimientos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe_movimiento")
    private BigDecimal importeMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo_acumulado")
    private BigDecimal saldoAcumulado;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMovimientoCaja")
    private List<ComprobantesPagos> ventasPagosList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private NegocioFormasPago idFormaPago;
    @JoinColumn(name = "id_categoria_movimiento", referencedColumnName = "id_categoria_movimiento", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private CajasCategoriasMovimientos idCategoriaMovimiento;
    @JoinColumn(name = "id_caja", referencedColumnName = "id_caja", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Cajas idCaja;

    public CajasMovimientos() {
    }

    public CajasMovimientos(Long idMovimientoCaja) {
        super(idMovimientoCaja);
    }

    public CajasMovimientos(Long idMovimientoCaja, Date fechaMovimiento, BigDecimal saldoAcumulado) {
        super(idMovimientoCaja);
        this.fechaMovimiento = fechaMovimiento;
        this.saldoAcumulado = saldoAcumulado;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public BigDecimal getImporteMovimiento() {
        return importeMovimiento;
    }

    public void setImporteMovimiento(BigDecimal importeMovimiento) {
        this.importeMovimiento = importeMovimiento;
    }

    public BigDecimal getSaldoAcumulado() {
        return saldoAcumulado;
    }

    public void setSaldoAcumulado(BigDecimal saldoAcumulado) {
        this.saldoAcumulado = saldoAcumulado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<ComprobantesPagos> getVentasPagosList() {
        return ventasPagosList;
    }

    public void setVentasPagosList(List<ComprobantesPagos> ventasPagosList) {
        this.ventasPagosList = ventasPagosList;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public CajasCategoriasMovimientos getIdCategoriaMovimiento() {
        return idCategoriaMovimiento;
    }

    public void setIdCategoriaMovimiento(CajasCategoriasMovimientos idCategoriaMovimiento) {
        this.idCategoriaMovimiento = idCategoriaMovimiento;
    }

    public Cajas getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Cajas idCaja) {
        this.idCaja = idCaja;
    }

}
