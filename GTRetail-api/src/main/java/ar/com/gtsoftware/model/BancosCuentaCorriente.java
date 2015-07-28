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
@Table(name = "bancos_cuenta_corriente")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_movimiento", columnDefinition = "serial"))
public class BancosCuentaCorriente extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_movimiento")
    private BigDecimal importeMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion_movimiento")
    private String descripcionMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_registro_contable")
    private int idRegistroContable;
    @JoinColumn(name = "id_cuenta_banco", referencedColumnName = "id_cuenta_banco", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private BancosCuentas idCuentaBanco;

    public BancosCuentaCorriente() {
    }

    public BancosCuentaCorriente(Long idMovimiento) {
        super(idMovimiento);
    }

    public BancosCuentaCorriente(Long idMovimiento, Date fechaMovimiento, BigDecimal importeMovimiento, String descripcionMovimiento, int idRegistroContable) {
        super(idMovimiento);
        this.fechaMovimiento = fechaMovimiento;
        this.importeMovimiento = importeMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
        this.idRegistroContable = idRegistroContable;
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

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }

    public int getIdRegistroContable() {
        return idRegistroContable;
    }

    public void setIdRegistroContable(int idRegistroContable) {
        this.idRegistroContable = idRegistroContable;
    }

    public BancosCuentas getIdCuentaBanco() {
        return idCuentaBanco;
    }

    public void setIdCuentaBanco(BancosCuentas idCuentaBanco) {
        this.idCuentaBanco = idCuentaBanco;
    }

}
