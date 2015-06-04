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
@Table(name = "personas_imagenes")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_imagen"))
public class PersonasImagenes extends BaseEntity {

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

    public PersonasImagenes(Long idImagen) {
        super(idImagen);
    }

    public PersonasImagenes(Long idImagen, Date fechaAlta) {
        super(idImagen);
        this.fechaAlta = fechaAlta;
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

}
