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

import java.math.BigDecimal;
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
@Table(name = "productos_porcentajes")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_porcentaje"))
public class ProductosPorcentajes extends BaseEntity {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "descripcion_porcentaje")
    private String descripcionPorcentaje;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public ProductosPorcentajes() {
    }

    public ProductosPorcentajes(Long idPorcentaje) {
        super(idPorcentaje);
    }

    public ProductosPorcentajes(Long idPorcentaje, BigDecimal porcentaje, String descripcionPorcentaje) {
        super(idPorcentaje);
        this.porcentaje = porcentaje;
        this.descripcionPorcentaje = descripcionPorcentaje;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcionPorcentaje() {
        return descripcionPorcentaje;
    }

    public void setDescripcionPorcentaje(String descripcionPorcentaje) {
        this.descripcionPorcentaje = descripcionPorcentaje;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

}
