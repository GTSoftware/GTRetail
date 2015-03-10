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
@Table(name = "productos_x_caracteristicas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_caracteristica_x_producto"))
public class ProductosXCaracteristicas extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "valor_caracteristica")
    private String valorCaracteristica;
    @JoinColumn(name = "id_caracteristica", referencedColumnName = "id_caracteristica")
    @ManyToOne(optional = false)
    private ProductosCaracteristicas idCaracteristica;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public ProductosXCaracteristicas() {
    }

    public ProductosXCaracteristicas(Long idCaracteristicaXProducto) {
        super(idCaracteristicaXProducto);
    }

    public ProductosXCaracteristicas(Long idCaracteristicaXProducto, String valorCaracteristica) {
        super(idCaracteristicaXProducto);
        this.valorCaracteristica = valorCaracteristica;
    }

    public String getValorCaracteristica() {
        return valorCaracteristica;
    }

    public void setValorCaracteristica(String valorCaracteristica) {
        this.valorCaracteristica = valorCaracteristica;
    }

    public ProductosCaracteristicas getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(ProductosCaracteristicas idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

}
