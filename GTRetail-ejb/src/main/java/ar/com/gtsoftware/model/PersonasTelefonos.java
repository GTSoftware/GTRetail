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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "personas_telefonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasTelefonos.findAll", query = "SELECT p FROM PersonasTelefonos p"),
    @NamedQuery(name = "PersonasTelefonos.findByIdTelefono", query = "SELECT p FROM PersonasTelefonos p WHERE p.idTelefono = :idTelefono"),
    @NamedQuery(name = "PersonasTelefonos.findByNumero", query = "SELECT p FROM PersonasTelefonos p WHERE p.numero = :numero"),
    @NamedQuery(name = "PersonasTelefonos.findByReferencia", query = "SELECT p FROM PersonasTelefonos p WHERE p.referencia = :referencia")})
public class PersonasTelefonos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_telefono")
    private Integer idTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numero")
    //@Pattern(regexp = "(?<=\\s|:)\\(?(?:(0?[1-3]\\d{1,2})\\)?(?:\\s|-)?)?((?:\\d[\\d-]{5}|15[\\s\\d-]{7})\\d+)", message = "Número de teléfono no válido")
    private String numero;
    @Size(max = 100)
    @Column(name = "referencia")
    private String referencia;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @Transient
    private boolean eliminado;

    public PersonasTelefonos() {
    }

    public PersonasTelefonos(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public PersonasTelefonos(Integer idTelefono, String numero) {
        this.idTelefono = idTelefono;
        this.numero = numero;
    }

    public Integer getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Integer idTelefono) {
        this.idTelefono = idTelefono;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelefono != null ? idTelefono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasTelefonos)) {
            return false;
        }
        PersonasTelefonos other = (PersonasTelefonos) object;
        if ((this.idTelefono == null && other.idTelefono != null) || (this.idTelefono != null && !this.idTelefono.equals(other.idTelefono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.PersonasTelefonos[ idTelefono=" + idTelefono + " ]";
    }

}
