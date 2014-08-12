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
@Table(name = "productos_tipos_unidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosTiposUnidades.findAll", query = "SELECT p FROM ProductosTiposUnidades p"),
    @NamedQuery(name = "ProductosTiposUnidades.findByIdTipoUnidad", query = "SELECT p FROM ProductosTiposUnidades p WHERE p.idTipoUnidad = :idTipoUnidad"),
    @NamedQuery(name = "ProductosTiposUnidades.findByNombreUnidad", query = "SELECT p FROM ProductosTiposUnidades p WHERE p.nombreUnidad = :nombreUnidad"),
    @NamedQuery(name = "ProductosTiposUnidades.findByCantidadEntera", query = "SELECT p FROM ProductosTiposUnidades p WHERE p.cantidadEntera = :cantidadEntera")})
public class ProductosTiposUnidades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_unidad")
    private Integer idTipoUnidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_unidad")
    private String nombreUnidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_entera")
    private boolean cantidadEntera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoUnidadVenta")
    private List<Productos> productosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoUnidadCompra")
    private List<Productos> productosList1;

    public ProductosTiposUnidades() {
    }

    public ProductosTiposUnidades(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public ProductosTiposUnidades(Integer idTipoUnidad, String nombreUnidad, boolean cantidadEntera) {
        this.idTipoUnidad = idTipoUnidad;
        this.nombreUnidad = nombreUnidad;
        this.cantidadEntera = cantidadEntera;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public boolean getCantidadEntera() {
        return cantidadEntera;
    }

    public void setCantidadEntera(boolean cantidadEntera) {
        this.cantidadEntera = cantidadEntera;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @XmlTransient
    public List<Productos> getProductosList1() {
        return productosList1;
    }

    public void setProductosList1(List<Productos> productosList1) {
        this.productosList1 = productosList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUnidad != null ? idTipoUnidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosTiposUnidades)) {
            return false;
        }
        ProductosTiposUnidades other = (ProductosTiposUnidades) object;
        if ((this.idTipoUnidad == null && other.idTipoUnidad != null) || (this.idTipoUnidad != null && !this.idTipoUnidad.equals(other.idTipoUnidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProductosTiposUnidades[ idTipoUnidad=" + idTipoUnidad + " ]";
    }
    
}
