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
@Table(name = "personas_cuenta_corriente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasCuentaCorriente.findAll", query = "SELECT p FROM PersonasCuentaCorriente p"),
    @NamedQuery(name = "PersonasCuentaCorriente.findByIdMovimiento", query = "SELECT p FROM PersonasCuentaCorriente p WHERE p.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "PersonasCuentaCorriente.findByFechaMovimiento", query = "SELECT p FROM PersonasCuentaCorriente p WHERE p.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "PersonasCuentaCorriente.findByImporteMovimiento", query = "SELECT p FROM PersonasCuentaCorriente p WHERE p.importeMovimiento = :importeMovimiento"),
    @NamedQuery(name = "PersonasCuentaCorriente.findByDescripcionMovimiento", query = "SELECT p FROM PersonasCuentaCorriente p WHERE p.descripcionMovimiento = :descripcionMovimiento")})
public class PersonasCuentaCorriente implements Serializable {
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
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_registro_contable", referencedColumnName = "id_registro")
    @ManyToOne(optional = false)
    private ContabilidadRegistroContable idRegistroContable;

    public PersonasCuentaCorriente() {
    }

    public PersonasCuentaCorriente(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public PersonasCuentaCorriente(Integer idMovimiento, Date fechaMovimiento, BigDecimal importeMovimiento, String descripcionMovimiento) {
        this.idMovimiento = idMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.importeMovimiento = importeMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
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

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public ContabilidadRegistroContable getIdRegistroContable() {
        return idRegistroContable;
    }

    public void setIdRegistroContable(ContabilidadRegistroContable idRegistroContable) {
        this.idRegistroContable = idRegistroContable;
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
        if (!(object instanceof PersonasCuentaCorriente)) {
            return false;
        }
        PersonasCuentaCorriente other = (PersonasCuentaCorriente) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.PersonasCuentaCorriente[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
