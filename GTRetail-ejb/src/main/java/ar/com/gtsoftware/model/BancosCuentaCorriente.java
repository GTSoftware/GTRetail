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
@Table(name = "bancos_cuenta_corriente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BancosCuentaCorriente.findAll", query = "SELECT b FROM BancosCuentaCorriente b"),
    @NamedQuery(name = "BancosCuentaCorriente.findByIdMovimiento", query = "SELECT b FROM BancosCuentaCorriente b WHERE b.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "BancosCuentaCorriente.findByFechaMovimiento", query = "SELECT b FROM BancosCuentaCorriente b WHERE b.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "BancosCuentaCorriente.findByImporteMovimiento", query = "SELECT b FROM BancosCuentaCorriente b WHERE b.importeMovimiento = :importeMovimiento"),
    @NamedQuery(name = "BancosCuentaCorriente.findByDescripcionMovimiento", query = "SELECT b FROM BancosCuentaCorriente b WHERE b.descripcionMovimiento = :descripcionMovimiento"),
    @NamedQuery(name = "BancosCuentaCorriente.findByIdRegistroContable", query = "SELECT b FROM BancosCuentaCorriente b WHERE b.idRegistroContable = :idRegistroContable")})
public class BancosCuentaCorriente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;
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
    @JoinColumn(name = "id_cuenta_banco", referencedColumnName = "id_cuenta_banco")
    @ManyToOne(optional = false)
    private BancosCuentas idCuentaBanco;

    public BancosCuentaCorriente() {
    }

    public BancosCuentaCorriente(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public BancosCuentaCorriente(Integer idMovimiento, Date fechaMovimiento, BigDecimal importeMovimiento, String descripcionMovimiento, int idRegistroContable) {
        this.idMovimiento = idMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.importeMovimiento = importeMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
        this.idRegistroContable = idRegistroContable;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BancosCuentaCorriente)) {
            return false;
        }
        BancosCuentaCorriente other = (BancosCuentaCorriente) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.BancosCuentaCorriente[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
