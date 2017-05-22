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

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fede
 */
@Entity
@Table(name = "productos_x_depositos")
@AttributeOverride(name = "id", column = @Column(name = "id_producto_x_deposito", columnDefinition = "serial"))
public class ProductoXDeposito extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto",
            insertable = false, updatable = false)
    @NotNull
    @ManyToOne
    private Productos producto;

    @JoinColumn(name = "id_deposito", referencedColumnName = "id_deposito",
            insertable = false, updatable = false)
    @NotNull
    @ManyToOne
    private Depositos deposito;

    @Column(name = "stock")
    private BigDecimal stock;

    //-----Getter and Setter--------------------------------------------
    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Depositos getDeposito() {
        return deposito;
    }

    public void setDeposito(Depositos deposito) {
        this.deposito = deposito;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

}
