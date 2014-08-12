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
@Table(name = "contabilidad_tipos_operacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadTiposOperacion.findAll", query = "SELECT c FROM ContabilidadTiposOperacion c"),
    @NamedQuery(name = "ContabilidadTiposOperacion.findByIdTipoOperacion", query = "SELECT c FROM ContabilidadTiposOperacion c WHERE c.idTipoOperacion = :idTipoOperacion"),
    @NamedQuery(name = "ContabilidadTiposOperacion.findByNombreTipoOperacion", query = "SELECT c FROM ContabilidadTiposOperacion c WHERE c.nombreTipoOperacion = :nombreTipoOperacion"),
    @NamedQuery(name = "ContabilidadTiposOperacion.findByDescripcionTipoOperacion", query = "SELECT c FROM ContabilidadTiposOperacion c WHERE c.descripcionTipoOperacion = :descripcionTipoOperacion")})
public class ContabilidadTiposOperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_operacion")
    private Integer idTipoOperacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_tipo_operacion")
    private String nombreTipoOperacion;
    @Size(max = 2000)
    @Column(name = "descripcion_tipo_operacion")
    private String descripcionTipoOperacion;
    @OneToMany(mappedBy = "idTipoOperacion")
    private List<ContabilidadRegistroContable> contabilidadRegistroContableList;

    public ContabilidadTiposOperacion() {
    }

    public ContabilidadTiposOperacion(Integer idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public ContabilidadTiposOperacion(Integer idTipoOperacion, String nombreTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
        this.nombreTipoOperacion = nombreTipoOperacion;
    }

    public Integer getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(Integer idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public String getNombreTipoOperacion() {
        return nombreTipoOperacion;
    }

    public void setNombreTipoOperacion(String nombreTipoOperacion) {
        this.nombreTipoOperacion = nombreTipoOperacion;
    }

    public String getDescripcionTipoOperacion() {
        return descripcionTipoOperacion;
    }

    public void setDescripcionTipoOperacion(String descripcionTipoOperacion) {
        this.descripcionTipoOperacion = descripcionTipoOperacion;
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
        hash += (idTipoOperacion != null ? idTipoOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadTiposOperacion)) {
            return false;
        }
        ContabilidadTiposOperacion other = (ContabilidadTiposOperacion) object;
        if ((this.idTipoOperacion == null && other.idTipoOperacion != null) || (this.idTipoOperacion != null && !this.idTipoOperacion.equals(other.idTipoOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ContabilidadTiposOperacion[ idTipoOperacion=" + idTipoOperacion + " ]";
    }
    
}
