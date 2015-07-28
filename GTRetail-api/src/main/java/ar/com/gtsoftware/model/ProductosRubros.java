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
@Table(name = "productos_rubros")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_rubro", columnDefinition = "serial"))
public class ProductosRubros extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    public ProductosRubros(Long idRubro) {
        super(idRubro);
    }

    public ProductosRubros(Long idRubro, String nombreRubro) {
        super(idRubro);
        this.nombreRubro = nombreRubro;
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

}
