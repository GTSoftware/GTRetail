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
@Table(name = "ubicacion_paises")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_pais"))
public class UbicacionPaises extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_pais")
    private String nombrePais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<Bancos> bancosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<UbicacionProvincias> ubicacionProvinciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<Personas> personasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<Sucursales> sucursalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<Depositos> depositosList;

    public UbicacionPaises() {
    }

    public UbicacionPaises(Long idPais) {
        super(idPais);
    }

    public UbicacionPaises(Long idPais, String nombrePais) {
        super(idPais);
        this.nombrePais = nombrePais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @XmlTransient
    public List<Bancos> getBancosList() {
        return bancosList;
    }

    public void setBancosList(List<Bancos> bancosList) {
        this.bancosList = bancosList;
    }

    @XmlTransient
    public List<UbicacionProvincias> getUbicacionProvinciasList() {
        return ubicacionProvinciasList;
    }

    public void setUbicacionProvinciasList(List<UbicacionProvincias> ubicacionProvinciasList) {
        this.ubicacionProvinciasList = ubicacionProvinciasList;
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
        return "ar.com.gtsoftware.model.UbicacionPaises[ idPais=" + this.getId() + " ]";
    }

}