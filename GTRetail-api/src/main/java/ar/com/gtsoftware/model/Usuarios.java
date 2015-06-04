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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que representa a los usuarios del sistema
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_usuario"))
public class Usuarios extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @Basic(optional = true)
    @Size(min = 4, max = 4)
    @Column(name = "punto_venta")
    private String puntoVenta;
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

    /**
     * Crea un nuevo objeto Usuario
     */
    public Usuarios() {
    }

    /**
     * Crea un nuevo objeto Usuario con el Id pasado por parámetro
     *
     * @param idUsuario
     */
    public Usuarios(Long idUsuario) {
        super(idUsuario);
    }

    /**
     * Crea un nuevo objeto Usuario con los datos pasados por parámetro
     *
     * @param idUsuario
     * @param nombreUsuario
     * @param login
     * @param password
     * @param fechaAlta
     */
    public Usuarios(Long idUsuario, String nombreUsuario, String login, String password, Date fechaAlta) {
        super(idUsuario);
        this.nombreUsuario = nombreUsuario;
        this.login = login;
        this.password = password;
        this.fechaAlta = fechaAlta;
    }

    /**
     * Devuelve el nombre de usuario
     *
     * @return
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario
     *
     * @param nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Devuelve el nombre de LogIn de usuario
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * Establece el nombre de LogIn de usuario
     *
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Devuelve la contraseña hasheada
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña hasheada
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve la fecha de alta del usuario
     *
     * @return
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Establece la fecha de alta del usuario
     *
     * @param fechaAlta
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Devuelve la lista de grupos a los que pertenece el usuario
     *
     * @return
     */
    @XmlTransient
    public List<UsuariosGrupos> getUsuariosGruposList() {
        return usuariosGruposList;
    }

    /**
     * Establece la lista de grupos a los que pertenece el usuario
     *
     * @param usuariosGruposList
     */
    public void setUsuariosGruposList(List<UsuariosGrupos> usuariosGruposList) {
        this.usuariosGruposList = usuariosGruposList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<StockMovimientos> getStockMovimientosList() {
        return stockMovimientosList;
    }

    /**
     *
     * @param stockMovimientosList
     */
    public void setStockMovimientosList(List<StockMovimientos> stockMovimientosList) {
        this.stockMovimientosList = stockMovimientosList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<ProductosImagenes> getProductosImagenesList() {
        return productosImagenesList;
    }

    /**
     *
     * @param productosImagenesList
     */
    public void setProductosImagenesList(List<ProductosImagenes> productosImagenesList) {
        this.productosImagenesList = productosImagenesList;
    }

    /**
     * Devuelve la sucursal a la que pertenece el usuario
     *
     * @return
     */
    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    /**
     * Establece la sucursal a la que pertenece el usuario
     *
     * @param idSucursal
     */
    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<VentasRemitos> getVentasRemitosList() {
        return ventasRemitosList;
    }

    /**
     *
     * @param ventasRemitosList
     */
    public void setVentasRemitosList(List<VentasRemitos> ventasRemitosList) {
        this.ventasRemitosList = ventasRemitosList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<CajasMovimientos> getCajasMovimientosList() {
        return cajasMovimientosList;
    }

    /**
     *
     * @param cajasMovimientosList
     */
    public void setCajasMovimientosList(List<CajasMovimientos> cajasMovimientosList) {
        this.cajasMovimientosList = cajasMovimientosList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<ProveedoresOrdenesCompra> getProveedoresOrdenesCompraList() {
        return proveedoresOrdenesCompraList;
    }

    /**
     *
     * @param proveedoresOrdenesCompraList
     */
    public void setProveedoresOrdenesCompraList(List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList) {
        this.proveedoresOrdenesCompraList = proveedoresOrdenesCompraList;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    /**
     *
     * @param ventasList
     */
    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    /**
     * Devuelve el punto de venta por defecto del usuario
     *
     * @return
     */
    public String getPuntoVenta() {
        return puntoVenta;
    }

    /**
     * Establece el punto de venta por defecto del usuario
     *
     * @param puntoVenta
     */
    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Usuarios[ idUsuario=" + this.getId() + " ]";
    }

}
