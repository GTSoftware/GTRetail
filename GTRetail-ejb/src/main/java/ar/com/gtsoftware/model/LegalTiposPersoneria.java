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
@Table(name = "legal_tipos_personeria")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_personeria"))
public class LegalTiposPersoneria extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersoneria")
    private List<LegalTiposDocumento> legalTiposDocumentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersoneria")
    private List<LegalGeneros> legalGenerosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersoneria")
    private List<Personas> personasList;

    public LegalTiposPersoneria() {
    }

    public LegalTiposPersoneria(Long idTipoPersoneria) {
        super(idTipoPersoneria);
    }

    public LegalTiposPersoneria(Long idTipoPersoneria, String nombreTipo) {
        super(idTipoPersoneria);
        this.nombreTipo = nombreTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @XmlTransient
    public List<LegalTiposDocumento> getLegalTiposDocumentoList() {
        return legalTiposDocumentoList;
    }

    public void setLegalTiposDocumentoList(List<LegalTiposDocumento> legalTiposDocumentoList) {
        this.legalTiposDocumentoList = legalTiposDocumentoList;
    }

    @XmlTransient
    public List<LegalGeneros> getLegalGenerosList() {
        return legalGenerosList;
    }

    public void setLegalGenerosList(List<LegalGeneros> legalGenerosList) {
        this.legalGenerosList = legalGenerosList;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

}
