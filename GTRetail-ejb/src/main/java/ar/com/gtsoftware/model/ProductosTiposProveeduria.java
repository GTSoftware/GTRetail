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

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_proveeduria", columnDefinition = "serial"))
public class ProductosTiposProveeduria extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    public ProductosTiposProveeduria(Long idTipoProveeduria) {
        super(idTipoProveeduria);
    }

    public ProductosTiposProveeduria(Long idTipoProveeduria, boolean puedeComprarse, boolean puedeVenderse, boolean controlStock, boolean cambiarPrecioVenta) {
        super(idTipoProveeduria);
        this.puedeComprarse = puedeComprarse;
        this.puedeVenderse = puedeVenderse;
        this.controlStock = controlStock;
        this.cambiarPrecioVenta = cambiarPrecioVenta;
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

}
