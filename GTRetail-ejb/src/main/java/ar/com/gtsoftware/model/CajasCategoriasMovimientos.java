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
@Table(name = "cajas_categorias_movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajasCategoriasMovimientos.findAll", query = "SELECT c FROM CajasCategoriasMovimientos c"),
    @NamedQuery(name = "CajasCategoriasMovimientos.findByIdCategoriaMovimiento", query = "SELECT c FROM CajasCategoriasMovimientos c WHERE c.idCategoriaMovimiento = :idCategoriaMovimiento"),
    @NamedQuery(name = "CajasCategoriasMovimientos.findByNombreCategoriaMovimiento", query = "SELECT c FROM CajasCategoriasMovimientos c WHERE c.nombreCategoriaMovimiento = :nombreCategoriaMovimiento")})
public class CajasCategoriasMovimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria_movimiento")
    private Integer idCategoriaMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_categoria_movimiento")
    private String nombreCategoriaMovimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaMovimiento")
    private List<CajasMovimientos> cajasMovimientosList;

    public CajasCategoriasMovimientos() {
    }

    public CajasCategoriasMovimientos(Integer idCategoriaMovimiento) {
        this.idCategoriaMovimiento = idCategoriaMovimiento;
    }

    public CajasCategoriasMovimientos(Integer idCategoriaMovimiento, String nombreCategoriaMovimiento) {
        this.idCategoriaMovimiento = idCategoriaMovimiento;
        this.nombreCategoriaMovimiento = nombreCategoriaMovimiento;
    }

    public Integer getIdCategoriaMovimiento() {
        return idCategoriaMovimiento;
    }

    public void setIdCategoriaMovimiento(Integer idCategoriaMovimiento) {
        this.idCategoriaMovimiento = idCategoriaMovimiento;
    }

    public String getNombreCategoriaMovimiento() {
        return nombreCategoriaMovimiento;
    }

    public void setNombreCategoriaMovimiento(String nombreCategoriaMovimiento) {
        this.nombreCategoriaMovimiento = nombreCategoriaMovimiento;
    }

    @XmlTransient
    public List<CajasMovimientos> getCajasMovimientosList() {
        return cajasMovimientosList;
    }

    public void setCajasMovimientosList(List<CajasMovimientos> cajasMovimientosList) {
        this.cajasMovimientosList = cajasMovimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaMovimiento != null ? idCategoriaMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajasCategoriasMovimientos)) {
            return false;
        }
        CajasCategoriasMovimientos other = (CajasCategoriasMovimientos) object;
        if ((this.idCategoriaMovimiento == null && other.idCategoriaMovimiento != null) || (this.idCategoriaMovimiento != null && !this.idCategoriaMovimiento.equals(other.idCategoriaMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.CajasCategoriasMovimientos[ idCategoriaMovimiento=" + idCategoriaMovimiento + " ]";
    }
    
}
