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
@Table(name = "productos_x_caracteristicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosXCaracteristicas.findAll", query = "SELECT p FROM ProductosXCaracteristicas p"),
    @NamedQuery(name = "ProductosXCaracteristicas.findByIdCaracteristicaXProducto", query = "SELECT p FROM ProductosXCaracteristicas p WHERE p.idCaracteristicaXProducto = :idCaracteristicaXProducto"),
    @NamedQuery(name = "ProductosXCaracteristicas.findByValorCaracteristica", query = "SELECT p FROM ProductosXCaracteristicas p WHERE p.valorCaracteristica = :valorCaracteristica")})
public class ProductosXCaracteristicas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caracteristica_x_producto")
    private Integer idCaracteristicaXProducto;
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

    public ProductosXCaracteristicas(Integer idCaracteristicaXProducto) {
        this.idCaracteristicaXProducto = idCaracteristicaXProducto;
    }

    public ProductosXCaracteristicas(Integer idCaracteristicaXProducto, String valorCaracteristica) {
        this.idCaracteristicaXProducto = idCaracteristicaXProducto;
        this.valorCaracteristica = valorCaracteristica;
    }

    public Integer getIdCaracteristicaXProducto() {
        return idCaracteristicaXProducto;
    }

    public void setIdCaracteristicaXProducto(Integer idCaracteristicaXProducto) {
        this.idCaracteristicaXProducto = idCaracteristicaXProducto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaracteristicaXProducto != null ? idCaracteristicaXProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosXCaracteristicas)) {
            return false;
        }
        ProductosXCaracteristicas other = (ProductosXCaracteristicas) object;
        if ((this.idCaracteristicaXProducto == null && other.idCaracteristicaXProducto != null) || (this.idCaracteristicaXProducto != null && !this.idCaracteristicaXProducto.equals(other.idCaracteristicaXProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProductosXCaracteristicas[ idCaracteristicaXProducto=" + idCaracteristicaXProducto + " ]";
    }
    
}
