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
@Table(name = "ubicacion_provincias")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_provincia"))
public class UbicacionProvincias extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_provincia")
    private String nombreProvincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private List<Bancos> bancosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private List<UbicacionLocalidades> ubicacionLocalidadesList;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private UbicacionPaises idPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private List<Personas> personasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private List<Sucursales> sucursalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private List<Depositos> depositosList;

    public UbicacionProvincias() {
    }

    public UbicacionProvincias(Integer idProvincia) {
        super(idProvincia);
    }

    public UbicacionProvincias(Integer idProvincia, String nombreProvincia) {
        super(idProvincia);
        this.nombreProvincia = nombreProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    @XmlTransient
    public List<Bancos> getBancosList() {
        return bancosList;
    }

    public void setBancosList(List<Bancos> bancosList) {
        this.bancosList = bancosList;
    }

    @XmlTransient
    public List<UbicacionLocalidades> getUbicacionLocalidadesList() {
        return ubicacionLocalidadesList;
    }

    public void setUbicacionLocalidadesList(List<UbicacionLocalidades> ubicacionLocalidadesList) {
        this.ubicacionLocalidadesList = ubicacionLocalidadesList;
    }

    public UbicacionPaises getIdPais() {
        return idPais;
    }

    public void setIdPais(UbicacionPaises idPais) {
        this.idPais = idPais;
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

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.UbicacionProvincias[ idProvincia=" + this.getId() + " ]";
    }

}
