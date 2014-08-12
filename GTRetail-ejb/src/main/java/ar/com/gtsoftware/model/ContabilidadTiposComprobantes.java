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
@Table(name = "contabilidad_tipos_comprobantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadTiposComprobantes.findAll", query = "SELECT c FROM ContabilidadTiposComprobantes c"),
    @NamedQuery(name = "ContabilidadTiposComprobantes.findByIdTipoComprobante", query = "SELECT c FROM ContabilidadTiposComprobantes c WHERE c.idTipoComprobante = :idTipoComprobante"),
    @NamedQuery(name = "ContabilidadTiposComprobantes.findByNombreTipo", query = "SELECT c FROM ContabilidadTiposComprobantes c WHERE c.nombreTipo = :nombreTipo"),
    @NamedQuery(name = "ContabilidadTiposComprobantes.findByDescripcionTipo", query = "SELECT c FROM ContabilidadTiposComprobantes c WHERE c.descripcionTipo = :descripcionTipo")})
public class ContabilidadTiposComprobantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_comprobante")
    private Integer idTipoComprobante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Size(max = 2000)
    @Column(name = "descripcion_tipo")
    private String descripcionTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private List<ContabilidadRegistroContable> contabilidadRegistroContableList;

    public ContabilidadTiposComprobantes() {
    }

    public ContabilidadTiposComprobantes(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public ContabilidadTiposComprobantes(Integer idTipoComprobante, String nombreTipo) {
        this.idTipoComprobante = idTipoComprobante;
        this.nombreTipo = nombreTipo;
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
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
        hash += (idTipoComprobante != null ? idTipoComprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadTiposComprobantes)) {
            return false;
        }
        ContabilidadTiposComprobantes other = (ContabilidadTiposComprobantes) object;
        if ((this.idTipoComprobante == null && other.idTipoComprobante != null) || (this.idTipoComprobante != null && !this.idTipoComprobante.equals(other.idTipoComprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ContabilidadTiposComprobantes[ idTipoComprobante=" + idTipoComprobante + " ]";
    }
    
}
