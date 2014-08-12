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
@Table(name = "productos_tipos_proveeduria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosTiposProveeduria.findAll", query = "SELECT p FROM ProductosTiposProveeduria p"),
    @NamedQuery(name = "ProductosTiposProveeduria.findByIdTipoProveeduria", query = "SELECT p FROM ProductosTiposProveeduria p WHERE p.idTipoProveeduria = :idTipoProveeduria"),
    @NamedQuery(name = "ProductosTiposProveeduria.findByNombreTipoProveeduria", query = "SELECT p FROM ProductosTiposProveeduria p WHERE p.nombreTipoProveeduria = :nombreTipoProveeduria"),
    @NamedQuery(name = "ProductosTiposProveeduria.findByPuedeComprarse", query = "SELECT p FROM ProductosTiposProveeduria p WHERE p.puedeComprarse = :puedeComprarse"),
    @NamedQuery(name = "ProductosTiposProveeduria.findByPuedeVenderse", query = "SELECT p FROM ProductosTiposProveeduria p WHERE p.puedeVenderse = :puedeVenderse"),
    @NamedQuery(name = "ProductosTiposProveeduria.findByControlStock", query = "SELECT p FROM ProductosTiposProveeduria p WHERE p.controlStock = :controlStock"),
    @NamedQuery(name = "ProductosTiposProveeduria.findByCambiarPrecioVenta", query = "SELECT p FROM ProductosTiposProveeduria p WHERE p.cambiarPrecioVenta = :cambiarPrecioVenta")})
public class ProductosTiposProveeduria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_proveeduria")
    private Integer idTipoProveeduria;
    @Size(max = 60)
    @Column(name = "nombre_tipo_proveeduria")
    private String nombreTipoProveeduria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puede_comprarse")
    private boolean puedeComprarse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puede_venderse")
    private boolean puedeVenderse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_stock")
    private boolean controlStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cambiar_precio_venta")
    private boolean cambiarPrecioVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoProveeduria")
    private List<Productos> productosList;

    public ProductosTiposProveeduria() {
    }

    public ProductosTiposProveeduria(Integer idTipoProveeduria) {
        this.idTipoProveeduria = idTipoProveeduria;
    }

    public ProductosTiposProveeduria(Integer idTipoProveeduria, boolean puedeComprarse, boolean puedeVenderse, boolean controlStock, boolean cambiarPrecioVenta) {
        this.idTipoProveeduria = idTipoProveeduria;
        this.puedeComprarse = puedeComprarse;
        this.puedeVenderse = puedeVenderse;
        this.controlStock = controlStock;
        this.cambiarPrecioVenta = cambiarPrecioVenta;
    }

    public Integer getIdTipoProveeduria() {
        return idTipoProveeduria;
    }

    public void setIdTipoProveeduria(Integer idTipoProveeduria) {
        this.idTipoProveeduria = idTipoProveeduria;
    }

    public String getNombreTipoProveeduria() {
        return nombreTipoProveeduria;
    }

    public void setNombreTipoProveeduria(String nombreTipoProveeduria) {
        this.nombreTipoProveeduria = nombreTipoProveeduria;
    }

    public boolean getPuedeComprarse() {
        return puedeComprarse;
    }

    public void setPuedeComprarse(boolean puedeComprarse) {
        this.puedeComprarse = puedeComprarse;
    }

    public boolean getPuedeVenderse() {
        return puedeVenderse;
    }

    public void setPuedeVenderse(boolean puedeVenderse) {
        this.puedeVenderse = puedeVenderse;
    }

    public boolean getControlStock() {
        return controlStock;
    }

    public void setControlStock(boolean controlStock) {
        this.controlStock = controlStock;
    }

    public boolean getCambiarPrecioVenta() {
        return cambiarPrecioVenta;
    }

    public void setCambiarPrecioVenta(boolean cambiarPrecioVenta) {
        this.cambiarPrecioVenta = cambiarPrecioVenta;
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
        hash += (idTipoProveeduria != null ? idTipoProveeduria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosTiposProveeduria)) {
            return false;
        }
        ProductosTiposProveeduria other = (ProductosTiposProveeduria) object;
        if ((this.idTipoProveeduria == null && other.idTipoProveeduria != null) || (this.idTipoProveeduria != null && !this.idTipoProveeduria.equals(other.idTipoProveeduria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ProductosTiposProveeduria[ idTipoProveeduria=" + idTipoProveeduria + " ]";
    }
    
}
