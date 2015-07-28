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
@Table(name = "personas_telefonos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_telefono", columnDefinition = "serial"))
public class PersonasTelefonos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numero")
    //@Pattern(regexp = "(?<=\\s|:)\\(?(?:(0?[1-3]\\d{1,2})\\)?(?:\\s|-)?)?((?:\\d[\\d-]{5}|15[\\s\\d-]{7})\\d+)", message = "Número de teléfono no válido")
    private String numero;
    @Size(max = 100)
    @Column(name = "referencia")
    private String referencia;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Personas idPersona;

    public PersonasTelefonos() {
    }

    public PersonasTelefonos(Long idTelefono) {
        super(idTelefono);
    }

    public PersonasTelefonos(Long idTelefono, String numero) {
        super(idTelefono);
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

}
