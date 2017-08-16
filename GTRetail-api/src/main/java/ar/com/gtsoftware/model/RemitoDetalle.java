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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author fede
 */
@Entity
@Table(name = "remitos_detalle")
@AttributeOverride(name = "id", column = @Column(name = "id_remito_detalle", columnDefinition = "serial"))
public class RemitoDetalle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_remito", referencedColumnName = "id_remito")
    @ManyToOne
    private Remito remitoCabecera;

    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne
    private Productos idProducto;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Transient
    private int nroLinea;

    // Getter and Setter ----------------------------------------------
    public Remito getRemitoCabecera() {
        return remitoCabecera;
    }

    public void setRemitoCabecera(Remito remitoCabecera) {
        this.remitoCabecera = remitoCabecera;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * El número de linea utilizado para ubicar las líneas antes de que se han
     * guardado en la bd
     * 
     * @return
     */
    public int getNroLinea() {
        return nroLinea;
    }

    /**
     * El número de linea utilizado para ubicar las líneas antes de que se han
     * guardado en la bd
     * 
     * @param nroLinea
     */
    public void setNroLinea(int nroLinea) {
        this.nroLinea = nroLinea;
    }

}
