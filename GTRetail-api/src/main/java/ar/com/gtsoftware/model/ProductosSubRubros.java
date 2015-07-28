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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@AttributeOverride(name = "id", column = @Column(name = "id_sub_rubro", columnDefinition = "serial"))
public class ProductosSubRubros extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_sub_rubro")
    private String nombreSubRubro;
    @JoinColumn(name = "id_rubro", referencedColumnName = "id_rubro", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private ProductosRubros idRubro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSubRubro")
    private List<Productos> productosList;

    public ProductosSubRubros() {
    }

    public ProductosSubRubros(Long idSubRubro) {
        super(idSubRubro);
    }

    public ProductosSubRubros(Long idSubRubro, String nombreSubRubro) {
        super(idSubRubro);
        this.nombreSubRubro = nombreSubRubro;
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

}
