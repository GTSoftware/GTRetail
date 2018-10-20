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
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "productos_porcentajes")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_producto_porcentaje", columnDefinition = "serial"))
public class ProductosPorcentajes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @NotNull
    @Column(name = "valor", nullable = false, precision = 19, scale = 4)
    private BigDecimal valor;

    @JoinColumn(name = "id_tipo_porcentaje", referencedColumnName = "id_tipo_porcentaje", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private ProductosTiposPorcentajes idTipoPorcentaje;

    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public ProductosPorcentajes() {
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ProductosTiposPorcentajes getIdTipoPorcentaje() {
        return idTipoPorcentaje;
    }

    public void setIdTipoPorcentaje(ProductosTiposPorcentajes idTipoPorcentaje) {
        this.idTipoPorcentaje = idTipoPorcentaje;
    }

    @PreUpdate
    @PrePersist
    protected void onUpdate() {
        fechaModificacion = new Date();
    }
}
