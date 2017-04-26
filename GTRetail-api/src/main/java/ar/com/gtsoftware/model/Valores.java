/*
 * Copyright 2016 GT Software.
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

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Son los documentos que representan cupones o cheques.
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "valores")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_valor")
@AttributeOverride(name = "id", column = @Column(name = "id_valor"))
public class Valores extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;

    @OneToOne(mappedBy = "idValor")
    private RecibosDetalle reciboDetalle;

    public Valores(Long id) {
        super(id);
    }

    public Valores() {
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public RecibosDetalle getReciboDetalle() {
        return reciboDetalle;
    }

    public void setReciboDetalle(RecibosDetalle reciboDetalle) {
        this.reciboDetalle = reciboDetalle;
    }

}
