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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "productos_rubros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosRubros.findAll", query = "SELECT p FROM ProductosRubros p"),
    @NamedQuery(name = "ProductosRubros.findByIdRubro", query = "SELECT p FROM ProductosRubros p WHERE p.idRubro = :idRubro"),
    @NamedQuery(name = "ProductosRubros.findByNombreRubro", query = "SELECT p FROM ProductosRubros p WHERE p.nombreRubro = :nombreRubro")})
public class ProductosRubros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rubro")
    private Integer idRubro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_rubro")
    private String nombreRubro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRubro")
    private List<ProductosSubRubros> productosSubRubrosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRubro")
    private List<Productos> productosList;

    public ProductosRubros() {
    }

    public ProductosRubros(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public ProductosRubros(Integer idRubro, String nombreRubro) {
        this.idRubro = idRubro;
        this.nombreRubro = nombreRubro;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
        this.idRubro = idRubro;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    @XmlTransient
    public List<ProductosSubRubros> getProductosSubRubrosList() {
        return productosSubRubrosList;
    }

    public void setProductosSubRubrosList(List<ProductosSubRubros> productosSubRubrosList) {
        this.productosSubRubrosList = productosSubRubrosList;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRubro != null ? idRubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosRubros)) {
            return false;
        }
        ProductosRubros other = (ProductosRubros) object;
        if ((this.idRubro == null && other.idRubro != null) || (this.idRubro != null && !this.idRubro.equals(other.idRubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProductosRubros[ idRubro=" + idRubro + " ]";
    }
    
}
