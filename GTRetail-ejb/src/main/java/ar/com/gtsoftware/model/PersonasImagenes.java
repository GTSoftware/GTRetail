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
@Table(name = "personas_imagenes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonasImagenes.findAll", query = "SELECT p FROM PersonasImagenes p"),
    @NamedQuery(name = "PersonasImagenes.findByIdImagen", query = "SELECT p FROM PersonasImagenes p WHERE p.idImagen = :idImagen"),
    @NamedQuery(name = "PersonasImagenes.findByFechaAlta", query = "SELECT p FROM PersonasImagenes p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "PersonasImagenes.findByObservaciones", query = "SELECT p FROM PersonasImagenes p WHERE p.observaciones = :observaciones")})
public class PersonasImagenes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imagen")
    private Integer idImagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_tipo_imagen", referencedColumnName = "id_tipo_imagen")
    @ManyToOne(optional = false)
    private PersonasTiposImagenes idTipoImagen;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;

    public PersonasImagenes() {
    }

    public PersonasImagenes(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public PersonasImagenes(Integer idImagen, Date fechaAlta) {
        this.idImagen = idImagen;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public PersonasTiposImagenes getIdTipoImagen() {
        return idTipoImagen;
    }

    public void setIdTipoImagen(PersonasTiposImagenes idTipoImagen) {
        this.idTipoImagen = idTipoImagen;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasImagenes)) {
            return false;
        }
        PersonasImagenes other = (PersonasImagenes) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.PersonasImagenes[ idImagen=" + idImagen + " ]";
    }
    
}
