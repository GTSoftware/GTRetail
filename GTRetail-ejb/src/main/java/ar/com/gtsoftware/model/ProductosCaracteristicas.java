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
@Table(name = "productos_caracteristicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosCaracteristicas.findAll", query = "SELECT p FROM ProductosCaracteristicas p"),
    @NamedQuery(name = "ProductosCaracteristicas.findByIdCaracteristica", query = "SELECT p FROM ProductosCaracteristicas p WHERE p.idCaracteristica = :idCaracteristica"),
    @NamedQuery(name = "ProductosCaracteristicas.findByNombreCaracteristica", query = "SELECT p FROM ProductosCaracteristicas p WHERE p.nombreCaracteristica = :nombreCaracteristica")})
public class ProductosCaracteristicas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caracteristica")
    private Integer idCaracteristica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_caracteristica")
    private String nombreCaracteristica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaracteristica")
    private List<ProductosXCaracteristicas> productosXCaracteristicasList;

    public ProductosCaracteristicas() {
    }

    public ProductosCaracteristicas(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public ProductosCaracteristicas(Integer idCaracteristica, String nombreCaracteristica) {
        this.idCaracteristica = idCaracteristica;
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public Integer getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public String getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(String nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }

    @XmlTransient
    public List<ProductosXCaracteristicas> getProductosXCaracteristicasList() {
        return productosXCaracteristicasList;
    }

    public void setProductosXCaracteristicasList(List<ProductosXCaracteristicas> productosXCaracteristicasList) {
        this.productosXCaracteristicasList = productosXCaracteristicasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaracteristica != null ? idCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosCaracteristicas)) {
            return false;
        }
        ProductosCaracteristicas other = (ProductosCaracteristicas) object;
        if ((this.idCaracteristica == null && other.idCaracteristica != null) || (this.idCaracteristica != null && !this.idCaracteristica.equals(other.idCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProductosCaracteristicas[ idCaracteristica=" + idCaracteristica + " ]";
    }
    
}
