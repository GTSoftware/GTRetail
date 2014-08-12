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
import javax.persistence.ManyToMany;
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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByNombreUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuarios.findByLogin", query = "SELECT u FROM Usuarios u WHERE u.login = :login"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByFechaAlta", query = "SELECT u FROM Usuarios u WHERE u.fechaAlta = :fechaAlta")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @ManyToMany(mappedBy = "usuariosList")
    private List<UsuariosGrupos> usuariosGruposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<StockMovimientos> stockMovimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<ProductosImagenes> productosImagenesList;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne
    private Sucursales idSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<VentasRemitos> ventasRemitosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<CajasMovimientos> cajasMovimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Ventas> ventasList;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String nombreUsuario, String login, String password, Date fechaAlta) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.login = login;
        this.password = password;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @XmlTransient
    public List<UsuariosGrupos> getUsuariosGruposList() {
        return usuariosGruposList;
    }

    public void setUsuariosGruposList(List<UsuariosGrupos> usuariosGruposList) {
        this.usuariosGruposList = usuariosGruposList;
    }

    @XmlTransient
    public List<StockMovimientos> getStockMovimientosList() {
        return stockMovimientosList;
    }

    public void setStockMovimientosList(List<StockMovimientos> stockMovimientosList) {
        this.stockMovimientosList = stockMovimientosList;
    }

    @XmlTransient
    public List<ProductosImagenes> getProductosImagenesList() {
        return productosImagenesList;
    }

    public void setProductosImagenesList(List<ProductosImagenes> productosImagenesList) {
        this.productosImagenesList = productosImagenesList;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    @XmlTransient
    public List<VentasRemitos> getVentasRemitosList() {
        return ventasRemitosList;
    }

    public void setVentasRemitosList(List<VentasRemitos> ventasRemitosList) {
        this.ventasRemitosList = ventasRemitosList;
    }

    @XmlTransient
    public List<CajasMovimientos> getCajasMovimientosList() {
        return cajasMovimientosList;
    }

    public void setCajasMovimientosList(List<CajasMovimientos> cajasMovimientosList) {
        this.cajasMovimientosList = cajasMovimientosList;
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
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
