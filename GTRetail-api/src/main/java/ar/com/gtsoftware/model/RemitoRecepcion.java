/*
 * Copyright 2017 GT Software.
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fede
 */
@Entity
@Table(name = "remitos_recepciones")
@AttributeOverride(name = "id", column = @Column(name = "id_recepcion", columnDefinition = "serial"))
public class RemitoRecepcion extends BaseEntity {

    @JoinColumn(name = "id_remito", referencedColumnName = "id_remito")
    @NotNull
    private Remito remito;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToMany
    private Usuarios idUsuario;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @OneToMany
    private Personas idPersona;

    @JoinColumn(name = "id_deposito", referencedColumnName = "id_deposito")
    @OneToMany
    private Depositos idDeposito;

    //----------Getter and Setter ------------------------------
    @PrePersist
    public void dateOnCreate() {
        fecha = new Date();
    }

    public Remito getRemito() {
        return remito;
    }

    public void setRemito(Remito remito) {
        this.remito = remito;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public Depositos getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Depositos idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Date getFecha() {
        return fecha;
    }

}
