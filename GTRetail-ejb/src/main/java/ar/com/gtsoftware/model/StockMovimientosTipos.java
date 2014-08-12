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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "stock_movimientos_tipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockMovimientosTipos.findAll", query = "SELECT s FROM StockMovimientosTipos s"),
    @NamedQuery(name = "StockMovimientosTipos.findByIdTipoMovimiento", query = "SELECT s FROM StockMovimientosTipos s WHERE s.idTipoMovimiento = :idTipoMovimiento"),
    @NamedQuery(name = "StockMovimientosTipos.findByNombreTipo", query = "SELECT s FROM StockMovimientosTipos s WHERE s.nombreTipo = :nombreTipo")})
public class StockMovimientosTipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_tipo")
    private String nombreTipo;

    public StockMovimientosTipos() {
    }

    public StockMovimientosTipos(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public StockMovimientosTipos(Integer idTipoMovimiento, String nombreTipo) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombreTipo = nombreTipo;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMovimiento != null ? idTipoMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockMovimientosTipos)) {
            return false;
        }
        StockMovimientosTipos other = (StockMovimientosTipos) object;
        if ((this.idTipoMovimiento == null && other.idTipoMovimiento != null) || (this.idTipoMovimiento != null && !this.idTipoMovimiento.equals(other.idTipoMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.StockMovimientosTipos[ idTipoMovimiento=" + idTipoMovimiento + " ]";
    }
    
}
