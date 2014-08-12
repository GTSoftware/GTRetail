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
@Table(name = "contabilidad_tipos_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadTiposCuenta.findAll", query = "SELECT c FROM ContabilidadTiposCuenta c"),
    @NamedQuery(name = "ContabilidadTiposCuenta.findByIdTipoCuenta", query = "SELECT c FROM ContabilidadTiposCuenta c WHERE c.idTipoCuenta = :idTipoCuenta"),
    @NamedQuery(name = "ContabilidadTiposCuenta.findByNombreTipo", query = "SELECT c FROM ContabilidadTiposCuenta c WHERE c.nombreTipo = :nombreTipo"),
    @NamedQuery(name = "ContabilidadTiposCuenta.findByDescripcionTipo", query = "SELECT c FROM ContabilidadTiposCuenta c WHERE c.descripcionTipo = :descripcionTipo")})
public class ContabilidadTiposCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_cuenta")
    private Integer idTipoCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Size(max = 255)
    @Column(name = "descripcion_tipo")
    private String descripcionTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCuenta")
    private List<ContabilidadPlanCuentas> contabilidadPlanCuentasList;

    public ContabilidadTiposCuenta() {
    }

    public ContabilidadTiposCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public ContabilidadTiposCuenta(Integer idTipoCuenta, String nombreTipo) {
        this.idTipoCuenta = idTipoCuenta;
        this.nombreTipo = nombreTipo;
    }

    public Integer getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(Integer idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
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
    public List<ContabilidadPlanCuentas> getContabilidadPlanCuentasList() {
        return contabilidadPlanCuentasList;
    }

    public void setContabilidadPlanCuentasList(List<ContabilidadPlanCuentas> contabilidadPlanCuentasList) {
        this.contabilidadPlanCuentasList = contabilidadPlanCuentasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCuenta != null ? idTipoCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadTiposCuenta)) {
            return false;
        }
        ContabilidadTiposCuenta other = (ContabilidadTiposCuenta) object;
        if ((this.idTipoCuenta == null && other.idTipoCuenta != null) || (this.idTipoCuenta != null && !this.idTipoCuenta.equals(other.idTipoCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ContabilidadTiposCuenta[ idTipoCuenta=" + idTipoCuenta + " ]";
    }
    
}
