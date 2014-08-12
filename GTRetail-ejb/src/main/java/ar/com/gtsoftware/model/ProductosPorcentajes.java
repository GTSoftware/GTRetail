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

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "ProductosPorcentajes.findAll", query = "SELECT p FROM ProductosPorcentajes p"),
    @NamedQuery(name = "ProductosPorcentajes.findByIdPorcentaje", query = "SELECT p FROM ProductosPorcentajes p WHERE p.idPorcentaje = :idPorcentaje"),
    @NamedQuery(name = "ProductosPorcentajes.findByPorcentaje", query = "SELECT p FROM ProductosPorcentajes p WHERE p.porcentaje = :porcentaje"),
    @NamedQuery(name = "ProductosPorcentajes.findByDescripcionPorcentaje", query = "SELECT p FROM ProductosPorcentajes p WHERE p.descripcionPorcentaje = :descripcionPorcentaje")})
public class ProductosPorcentajes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_porcentaje")
    private Integer idPorcentaje;
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

    public ProductosPorcentajes(Integer idPorcentaje) {
        this.idPorcentaje = idPorcentaje;
    }

    public ProductosPorcentajes(Integer idPorcentaje, BigDecimal porcentaje, String descripcionPorcentaje) {
        this.idPorcentaje = idPorcentaje;
        this.porcentaje = porcentaje;
        this.descripcionPorcentaje = descripcionPorcentaje;
    }

    public Integer getIdPorcentaje() {
        return idPorcentaje;
    }

    public void setIdPorcentaje(Integer idPorcentaje) {
        this.idPorcentaje = idPorcentaje;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPorcentaje != null ? idPorcentaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosPorcentajes)) {
            return false;
        }
        ProductosPorcentajes other = (ProductosPorcentajes) object;
        if ((this.idPorcentaje == null && other.idPorcentaje != null) || (this.idPorcentaje != null && !this.idPorcentaje.equals(other.idPorcentaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProductosPorcentajes[ idPorcentaje=" + idPorcentaje + " ]";
    }
    
}
