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

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "ubicacion_localidades")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_localidad", columnDefinition = "serial"))
public class UbicacionLocalidades extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_localidad")
    private String nombreLocalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigo_postal")
    private String codigoPostal;

    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    @ManyToOne(optional = false)
    private UbicacionProvincias idProvincia;

    public UbicacionLocalidades() {
    }

    public UbicacionLocalidades(Long idLocalidad) {
        super(idLocalidad);
    }

    public UbicacionLocalidades(Long idLocalidad, String nombreLocalidad) {
        super(idLocalidad);
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public UbicacionProvincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(UbicacionProvincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

}
