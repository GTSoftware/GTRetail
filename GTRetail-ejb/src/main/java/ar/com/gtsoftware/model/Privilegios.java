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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "privilegios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilegios.findAll", query = "SELECT p FROM Privilegios p"),
    @NamedQuery(name = "Privilegios.findByIdPrivilegio", query = "SELECT p FROM Privilegios p WHERE p.idPrivilegio = :idPrivilegio"),
    @NamedQuery(name = "Privilegios.findByNombrePrivilegio", query = "SELECT p FROM Privilegios p WHERE p.nombrePrivilegio = :nombrePrivilegio"),
    @NamedQuery(name = "Privilegios.findByDescripcionPrivilegio", query = "SELECT p FROM Privilegios p WHERE p.descripcionPrivilegio = :descripcionPrivilegio")})
public class Privilegios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_privilegio")
    private Integer idPrivilegio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_privilegio")
    private String nombrePrivilegio;
    @Size(max = 1024)
    @Column(name = "descripcion_privilegio")
    private String descripcionPrivilegio;
    @ManyToMany(mappedBy = "privilegiosList")
    private List<UsuariosGrupos> usuariosGruposList;

    public Privilegios() {
    }

    public Privilegios(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public Privilegios(Integer idPrivilegio, String nombrePrivilegio) {
        this.idPrivilegio = idPrivilegio;
        this.nombrePrivilegio = nombrePrivilegio;
    }

    public Integer getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public String getNombrePrivilegio() {
        return nombrePrivilegio;
    }

    public void setNombrePrivilegio(String nombrePrivilegio) {
        this.nombrePrivilegio = nombrePrivilegio;
    }

    public String getDescripcionPrivilegio() {
        return descripcionPrivilegio;
    }

    public void setDescripcionPrivilegio(String descripcionPrivilegio) {
        this.descripcionPrivilegio = descripcionPrivilegio;
    }

    @XmlTransient
    public List<UsuariosGrupos> getUsuariosGruposList() {
        return usuariosGruposList;
    }

    public void setUsuariosGruposList(List<UsuariosGrupos> usuariosGruposList) {
        this.usuariosGruposList = usuariosGruposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilegio != null ? idPrivilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilegios)) {
            return false;
        }
        Privilegios other = (Privilegios) object;
        if ((this.idPrivilegio == null && other.idPrivilegio != null) || (this.idPrivilegio != null && !this.idPrivilegio.equals(other.idPrivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Privilegios[ idPrivilegio=" + idPrivilegio + " ]";
    }
    
}
