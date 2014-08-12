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
@Table(name = "legal_tipos_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LegalTiposDocumento.findAll", query = "SELECT l FROM LegalTiposDocumento l"),
    @NamedQuery(name = "LegalTiposDocumento.findByIdTipoDocumento", query = "SELECT l FROM LegalTiposDocumento l WHERE l.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "LegalTiposDocumento.findByNombreTipoDocumento", query = "SELECT l FROM LegalTiposDocumento l WHERE l.nombreTipoDocumento = :nombreTipoDocumento"),
    @NamedQuery(name = "LegalTiposDocumento.findByCantidadCaracteresMinimo", query = "SELECT l FROM LegalTiposDocumento l WHERE l.cantidadCaracteresMinimo = :cantidadCaracteresMinimo"),
    @NamedQuery(name = "LegalTiposDocumento.findByCantidadCaracteresMaximo", query = "SELECT l FROM LegalTiposDocumento l WHERE l.cantidadCaracteresMaximo = :cantidadCaracteresMaximo")})
public class LegalTiposDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_tipo_documento")
    private String nombreTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_caracteres_minimo")
    private int cantidadCaracteresMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_caracteres_maximo")
    private int cantidadCaracteresMaximo;
    @JoinColumn(name = "id_tipo_personeria", referencedColumnName = "id_tipo_personeria")
    @ManyToOne(optional = false)
    private LegalTiposPersoneria idTipoPersoneria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private List<Personas> personasList;

    public LegalTiposDocumento() {
    }

    public LegalTiposDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public LegalTiposDocumento(Integer idTipoDocumento, String nombreTipoDocumento, int cantidadCaracteresMinimo, int cantidadCaracteresMaximo) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombreTipoDocumento = nombreTipoDocumento;
        this.cantidadCaracteresMinimo = cantidadCaracteresMinimo;
        this.cantidadCaracteresMaximo = cantidadCaracteresMaximo;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento = nombreTipoDocumento;
    }

    public int getCantidadCaracteresMinimo() {
        return cantidadCaracteresMinimo;
    }

    public void setCantidadCaracteresMinimo(int cantidadCaracteresMinimo) {
        this.cantidadCaracteresMinimo = cantidadCaracteresMinimo;
    }

    public int getCantidadCaracteresMaximo() {
        return cantidadCaracteresMaximo;
    }

    public void setCantidadCaracteresMaximo(int cantidadCaracteresMaximo) {
        this.cantidadCaracteresMaximo = cantidadCaracteresMaximo;
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
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LegalTiposDocumento)) {
            return false;
        }
        LegalTiposDocumento other = (LegalTiposDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.LegalTiposDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
