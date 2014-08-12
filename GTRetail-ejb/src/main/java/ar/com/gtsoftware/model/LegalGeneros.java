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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "legal_generos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LegalGeneros.findAll", query = "SELECT l FROM LegalGeneros l"),
    @NamedQuery(name = "LegalGeneros.findByIdGenero", query = "SELECT l FROM LegalGeneros l WHERE l.idGenero = :idGenero"),
    @NamedQuery(name = "LegalGeneros.findByNombreGenero", query = "SELECT l FROM LegalGeneros l WHERE l.nombreGenero = :nombreGenero"),
    @NamedQuery(name = "LegalGeneros.findBySimbolo", query = "SELECT l FROM LegalGeneros l WHERE l.simbolo = :simbolo")})
public class LegalGeneros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genero")
    private Integer idGenero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_genero")
    private String nombreGenero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "simbolo")
    private String simbolo;
    @JoinColumn(name = "id_tipo_personeria", referencedColumnName = "id_tipo_personeria")
    @ManyToOne(optional = false)
    private LegalTiposPersoneria idTipoPersoneria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGenero")
    private List<Personas> personasList;

    public LegalGeneros() {
    }

    public LegalGeneros(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public LegalGeneros(Integer idGenero, String nombreGenero, String simbolo) {
        this.idGenero = idGenero;
        this.nombreGenero = nombreGenero;
        this.simbolo = simbolo;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public LegalTiposPersoneria getIdTipoPersoneria() {
        return idTipoPersoneria;
    }

    public void setIdTipoPersoneria(LegalTiposPersoneria idTipoPersoneria) {
        this.idTipoPersoneria = idTipoPersoneria;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenero != null ? idGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LegalGeneros)) {
            return false;
        }
        LegalGeneros other = (LegalGeneros) object;
        if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.LegalGeneros[ idGenero=" + idGenero + " ]";
    }
    
}
