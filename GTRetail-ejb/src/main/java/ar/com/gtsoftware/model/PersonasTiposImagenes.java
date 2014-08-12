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
@Table(name = "personas_tipos_imagenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasTiposImagenes.findAll", query = "SELECT p FROM PersonasTiposImagenes p"),
    @NamedQuery(name = "PersonasTiposImagenes.findByIdTipoImagen", query = "SELECT p FROM PersonasTiposImagenes p WHERE p.idTipoImagen = :idTipoImagen"),
    @NamedQuery(name = "PersonasTiposImagenes.findByNombreTipo", query = "SELECT p FROM PersonasTiposImagenes p WHERE p.nombreTipo = :nombreTipo"),
    @NamedQuery(name = "PersonasTiposImagenes.findByDescripcionTipo", query = "SELECT p FROM PersonasTiposImagenes p WHERE p.descripcionTipo = :descripcionTipo")})
public class PersonasTiposImagenes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_imagen")
    private Integer idTipoImagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Size(max = 200)
    @Column(name = "descripcion_tipo")
    private String descripcionTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoImagen")
    private List<PersonasImagenes> personasImagenesList;

    public PersonasTiposImagenes() {
    }

    public PersonasTiposImagenes(Integer idTipoImagen) {
        this.idTipoImagen = idTipoImagen;
    }

    public PersonasTiposImagenes(Integer idTipoImagen, String nombreTipo) {
        this.idTipoImagen = idTipoImagen;
        this.nombreTipo = nombreTipo;
    }

    public Integer getIdTipoImagen() {
        return idTipoImagen;
    }

    public void setIdTipoImagen(Integer idTipoImagen) {
        this.idTipoImagen = idTipoImagen;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    @XmlTransient
    public List<PersonasImagenes> getPersonasImagenesList() {
        return personasImagenesList;
    }

    public void setPersonasImagenesList(List<PersonasImagenes> personasImagenesList) {
        this.personasImagenesList = personasImagenesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoImagen != null ? idTipoImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasTiposImagenes)) {
            return false;
        }
        PersonasTiposImagenes other = (PersonasTiposImagenes) object;
        if ((this.idTipoImagen == null && other.idTipoImagen != null) || (this.idTipoImagen != null && !this.idTipoImagen.equals(other.idTipoImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.PersonasTiposImagenes[ idTipoImagen=" + idTipoImagen + " ]";
    }
    
}
