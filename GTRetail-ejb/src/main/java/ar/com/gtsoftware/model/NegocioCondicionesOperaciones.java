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
@Table(name = "negocio_condiciones_operaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NegocioCondicionesOperaciones.findAll", query = "SELECT n FROM NegocioCondicionesOperaciones n"),
    @NamedQuery(name = "NegocioCondicionesOperaciones.findByIdCondicion", query = "SELECT n FROM NegocioCondicionesOperaciones n WHERE n.idCondicion = :idCondicion"),
    @NamedQuery(name = "NegocioCondicionesOperaciones.findByNombreCondicion", query = "SELECT n FROM NegocioCondicionesOperaciones n WHERE n.nombreCondicion = :nombreCondicion"),
    @NamedQuery(name = "NegocioCondicionesOperaciones.findByActivo", query = "SELECT n FROM NegocioCondicionesOperaciones n WHERE n.activo = :activo"),
    @NamedQuery(name = "NegocioCondicionesOperaciones.findByVenta", query = "SELECT n FROM NegocioCondicionesOperaciones n WHERE n.venta = :venta"),
    @NamedQuery(name = "NegocioCondicionesOperaciones.findByCompra", query = "SELECT n FROM NegocioCondicionesOperaciones n WHERE n.compra = :compra")})
public class NegocioCondicionesOperaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_condicion")
    private Integer idCondicion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_condicion")
    private String nombreCondicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta")
    private boolean venta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra")
    private boolean compra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondicionCompra")
    private List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondicionVenta")
    private List<Ventas> ventasList;

    public NegocioCondicionesOperaciones() {
    }

    public NegocioCondicionesOperaciones(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public NegocioCondicionesOperaciones(Integer idCondicion, String nombreCondicion, boolean activo, boolean venta, boolean compra) {
        this.idCondicion = idCondicion;
        this.nombreCondicion = nombreCondicion;
        this.activo = activo;
        this.venta = venta;
        this.compra = compra;
    }

    public Integer getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getNombreCondicion() {
        return nombreCondicion;
    }

    public void setNombreCondicion(String nombreCondicion) {
        this.nombreCondicion = nombreCondicion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getVenta() {
        return venta;
    }

    public void setVenta(boolean venta) {
        this.venta = venta;
    }

    public boolean getCompra() {
        return compra;
    }

    public void setCompra(boolean compra) {
        this.compra = compra;
    }

    @XmlTransient
    public List<ProveedoresOrdenesCompra> getProveedoresOrdenesCompraList() {
        return proveedoresOrdenesCompraList;
    }

    public void setProveedoresOrdenesCompraList(List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList) {
        this.proveedoresOrdenesCompraList = proveedoresOrdenesCompraList;
    }

    @XmlTransient
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCondicion != null ? idCondicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NegocioCondicionesOperaciones)) {
            return false;
        }
        NegocioCondicionesOperaciones other = (NegocioCondicionesOperaciones) object;
        if ((this.idCondicion == null && other.idCondicion != null) || (this.idCondicion != null && !this.idCondicion.equals(other.idCondicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.NegocioCondicionesOperaciones[ idCondicion=" + idCondicion + " ]";
    }
    
}
