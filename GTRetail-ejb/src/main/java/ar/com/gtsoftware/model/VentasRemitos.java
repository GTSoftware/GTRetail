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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "ventas_remitos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasRemitos.findAll", query = "SELECT v FROM VentasRemitos v"),
    @NamedQuery(name = "VentasRemitos.findByIdRemito", query = "SELECT v FROM VentasRemitos v WHERE v.idRemito = :idRemito"),
    @NamedQuery(name = "VentasRemitos.findByFechaRemito", query = "SELECT v FROM VentasRemitos v WHERE v.fechaRemito = :fechaRemito"),
    @NamedQuery(name = "VentasRemitos.findByAnulado", query = "SELECT v FROM VentasRemitos v WHERE v.anulado = :anulado"),
    @NamedQuery(name = "VentasRemitos.findByObservaciones", query = "SELECT v FROM VentasRemitos v WHERE v.observaciones = :observaciones")})
public class VentasRemitos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_remito")
    private Integer idRemito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_remito")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRemito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulado")
    private boolean anulado;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRemito")
    private List<VentasRemitosLineas> ventasRemitosLineasList;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false)
    private Ventas idVenta;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;

    public VentasRemitos() {
    }

    public VentasRemitos(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public VentasRemitos(Integer idRemito, Date fechaRemito, boolean anulado) {
        this.idRemito = idRemito;
        this.fechaRemito = fechaRemito;
        this.anulado = anulado;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Date getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(Date fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<VentasRemitosLineas> getVentasRemitosLineasList() {
        return ventasRemitosLineasList;
    }

    public void setVentasRemitosLineasList(List<VentasRemitosLineas> ventasRemitosLineasList) {
        this.ventasRemitosLineasList = ventasRemitosLineasList;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRemito != null ? idRemito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasRemitos)) {
            return false;
        }
        VentasRemitos other = (VentasRemitos) object;
        if ((this.idRemito == null && other.idRemito != null) || (this.idRemito != null && !this.idRemito.equals(other.idRemito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasRemitos[ idRemito=" + idRemito + " ]";
    }
    
}
