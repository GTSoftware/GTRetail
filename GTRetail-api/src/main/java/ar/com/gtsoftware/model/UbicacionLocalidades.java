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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ubicacion_localidades")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_localidad", columnDefinition = "serial"))
public class UbicacionLocalidades extends BaseEntity implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocalidad")
    private List<Bancos> bancosList;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionProvincias idProvincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocalidad")
    private List<Personas> personasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocalidad")
    private List<Sucursales> sucursalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocalidad")
    private List<Depositos> depositosList;

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

    @XmlTransient
    public List<Bancos> getBancosList() {
        return bancosList;
    }

    public void setBancosList(List<Bancos> bancosList) {
        this.bancosList = bancosList;
    }

    public UbicacionProvincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(UbicacionProvincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @XmlTransient
    public List<Sucursales> getSucursalesList() {
        return sucursalesList;
    }

    public void setSucursalesList(List<Sucursales> sucursalesList) {
        this.sucursalesList = sucursalesList;
    }

    @XmlTransient
    public List<Depositos> getDepositosList() {
        return depositosList;
    }

    public void setDepositosList(List<Depositos> depositosList) {
        this.depositosList = depositosList;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.UbicacionLocalidades[ idLocalidad=" + this.getId() + " ]";
    }

}
