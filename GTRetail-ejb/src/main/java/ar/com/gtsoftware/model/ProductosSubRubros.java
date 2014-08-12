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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "productos_sub_rubros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosSubRubros.findAll", query = "SELECT p FROM ProductosSubRubros p"),
    @NamedQuery(name = "ProductosSubRubros.findByIdSubRubro", query = "SELECT p FROM ProductosSubRubros p WHERE p.idSubRubro = :idSubRubro"),
    @NamedQuery(name = "ProductosSubRubros.findByNombreSubRubro", query = "SELECT p FROM ProductosSubRubros p WHERE p.nombreSubRubro = :nombreSubRubro")})
public class ProductosSubRubros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sub_rubro")
    private Integer idSubRubro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_sub_rubro")
    private String nombreSubRubro;
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro")
    @ManyToOne(optional = false)
    private ProductosRubros idRubro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubRubro")
    private List<Productos> productosList;

    public ProductosSubRubros() {
    }

    public ProductosSubRubros(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
    }

    public ProductosSubRubros(Integer idSubRubro, String nombreSubRubro) {
        this.idSubRubro = idSubRubro;
        this.nombreSubRubro = nombreSubRubro;
    }

    public Integer getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(Integer idSubRubro) {
        this.idSubRubro = idSubRubro;
    }

    public String getNombreSubRubro() {
        return nombreSubRubro;
    }

    public void setNombreSubRubro(String nombreSubRubro) {
        this.nombreSubRubro = nombreSubRubro;
    }

    public ProductosRubros getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(ProductosRubros idRubro) {
        this.idRubro = idRubro;
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
        hash += (idSubRubro != null ? idSubRubro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosSubRubros)) {
            return false;
        }
        ProductosSubRubros other = (ProductosSubRubros) object;
        if ((this.idSubRubro == null && other.idSubRubro != null) || (this.idSubRubro != null && !this.idSubRubro.equals(other.idSubRubro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProductosSubRubros[ idSubRubro=" + idSubRubro + " ]";
    }
    
}
