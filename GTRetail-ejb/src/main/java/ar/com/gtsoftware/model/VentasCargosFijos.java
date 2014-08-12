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
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "ventas_cargos_fijos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasCargosFijos.findAll", query = "SELECT v FROM VentasCargosFijos v"),
    @NamedQuery(name = "VentasCargosFijos.findByIdCargoFijo", query = "SELECT v FROM VentasCargosFijos v WHERE v.idCargoFijo = :idCargoFijo"),
    @NamedQuery(name = "VentasCargosFijos.findByFechaAlta", query = "SELECT v FROM VentasCargosFijos v WHERE v.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "VentasCargosFijos.findByActivo", query = "SELECT v FROM VentasCargosFijos v WHERE v.activo = :activo"),
    @NamedQuery(name = "VentasCargosFijos.findByPorcentaje", query = "SELECT v FROM VentasCargosFijos v WHERE v.porcentaje = :porcentaje"),
    @NamedQuery(name = "VentasCargosFijos.findByImporteCargo", query = "SELECT v FROM VentasCargosFijos v WHERE v.importeCargo = :importeCargo"),
    @NamedQuery(name = "VentasCargosFijos.findByIdClasificacionCliente", query = "SELECT v FROM VentasCargosFijos v WHERE v.idClasificacionCliente = :idClasificacionCliente")})
public class VentasCargosFijos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cargo_fijo")
    private Integer idCargoFijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private boolean porcentaje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_cargo")
    private BigDecimal importeCargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_clasificacion_cliente")
    private int idClasificacionCliente;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public VentasCargosFijos() {
    }

    public VentasCargosFijos(Integer idCargoFijo) {
        this.idCargoFijo = idCargoFijo;
    }

    public VentasCargosFijos(Integer idCargoFijo, Date fechaAlta, boolean activo, boolean porcentaje, BigDecimal importeCargo, int idClasificacionCliente) {
        this.idCargoFijo = idCargoFijo;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.porcentaje = porcentaje;
        this.importeCargo = importeCargo;
        this.idClasificacionCliente = idClasificacionCliente;
    }

    public Integer getIdCargoFijo() {
        return idCargoFijo;
    }

    public void setIdCargoFijo(Integer idCargoFijo) {
        this.idCargoFijo = idCargoFijo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getImporteCargo() {
        return importeCargo;
    }

    public void setImporteCargo(BigDecimal importeCargo) {
        this.importeCargo = importeCargo;
    }

    public int getIdClasificacionCliente() {
        return idClasificacionCliente;
    }

    public void setIdClasificacionCliente(int idClasificacionCliente) {
        this.idClasificacionCliente = idClasificacionCliente;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCargoFijo != null ? idCargoFijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasCargosFijos)) {
            return false;
        }
        VentasCargosFijos other = (VentasCargosFijos) object;
        if ((this.idCargoFijo == null && other.idCargoFijo != null) || (this.idCargoFijo != null && !this.idCargoFijo.equals(other.idCargoFijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasCargosFijos[ idCargoFijo=" + idCargoFijo + " ]";
    }
    
}
