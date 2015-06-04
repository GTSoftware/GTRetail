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
@Table(name = "legal_tipos_documento")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_documento"))
public class LegalTiposDocumento extends BaseEntity {

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

    public LegalTiposDocumento(Long idTipoDocumento) {
        super(idTipoDocumento);
    }

    public LegalTiposDocumento(Long idTipoDocumento, String nombreTipoDocumento, int cantidadCaracteresMinimo, int cantidadCaracteresMaximo) {
        super(idTipoDocumento);
        this.nombreTipoDocumento = nombreTipoDocumento;
        this.cantidadCaracteresMinimo = cantidadCaracteresMinimo;
        this.cantidadCaracteresMaximo = cantidadCaracteresMaximo;
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

}
