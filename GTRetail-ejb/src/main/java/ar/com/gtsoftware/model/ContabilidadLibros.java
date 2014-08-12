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
@Table(name = "contabilidad_libros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadLibros.findAll", query = "SELECT c FROM ContabilidadLibros c"),
    @NamedQuery(name = "ContabilidadLibros.findByIdLibro", query = "SELECT c FROM ContabilidadLibros c WHERE c.idLibro = :idLibro"),
    @NamedQuery(name = "ContabilidadLibros.findByNombreLibro", query = "SELECT c FROM ContabilidadLibros c WHERE c.nombreLibro = :nombreLibro"),
    @NamedQuery(name = "ContabilidadLibros.findByDescripcionLibro", query = "SELECT c FROM ContabilidadLibros c WHERE c.descripcionLibro = :descripcionLibro")})
public class ContabilidadLibros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_libro")
    private Integer idLibro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_libro")
    private String nombreLibro;
    @Size(max = 255)
    @Column(name = "descripcion_libro")
    private String descripcionLibro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibro")
    private List<ContabilidadRegistroContable> contabilidadRegistroContableList;

    public ContabilidadLibros() {
    }

    public ContabilidadLibros(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public ContabilidadLibros(Integer idLibro, String nombreLibro) {
        this.idLibro = idLibro;
        this.nombreLibro = nombreLibro;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getDescripcionLibro() {
        return descripcionLibro;
    }

    public void setDescripcionLibro(String descripcionLibro) {
        this.descripcionLibro = descripcionLibro;
    }

    @XmlTransient
    public List<ContabilidadRegistroContable> getContabilidadRegistroContableList() {
        return contabilidadRegistroContableList;
    }

    public void setContabilidadRegistroContableList(List<ContabilidadRegistroContable> contabilidadRegistroContableList) {
        this.contabilidadRegistroContableList = contabilidadRegistroContableList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLibro != null ? idLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadLibros)) {
            return false;
        }
        ContabilidadLibros other = (ContabilidadLibros) object;
        if ((this.idLibro == null && other.idLibro != null) || (this.idLibro != null && !this.idLibro.equals(other.idLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ContabilidadLibros[ idLibro=" + idLibro + " ]";
    }
    
}
