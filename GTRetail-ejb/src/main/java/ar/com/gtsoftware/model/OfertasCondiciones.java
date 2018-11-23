/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.model;

import ar.com.gtsoftware.rules.Campo;
import ar.com.gtsoftware.rules.Operacion;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "ofertas_condiciones")
@AttributeOverride(name = "id", column = @Column(name = "id_oferta_condicion"))
public class OfertasCondiciones extends BaseEntity implements Serializable {

    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta")
    @ManyToOne(optional = false)
    @NotNull
    private Ofertas idOferta;

    @Column(name = "operacion")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Operacion operacion;

    @Column(name = "campo")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Campo campo;

    @Column(name = "valor")
    @NotNull
    private String valor;

    public OfertasCondiciones() {
    }

    @NotNull
    public Ofertas getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(@NotNull Ofertas idOferta) {
        this.idOferta = idOferta;
    }

    @NotNull
    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(@NotNull Operacion operacion) {
        this.operacion = operacion;
    }

    @NotNull
    public Campo getCampo() {
        return campo;
    }

    public void setCampo(@NotNull Campo campo) {
        this.campo = campo;
    }

    @NotNull
    public String getValor() {
        return valor;
    }

    public void setValor(@NotNull String valor) {
        this.valor = valor;
    }
}
