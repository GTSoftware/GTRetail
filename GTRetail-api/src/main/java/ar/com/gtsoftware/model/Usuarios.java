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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
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
@AttributeOverride(name = "id", column = @Column(name = "id_usuario", columnDefinition = "serial"))
@NamedEntityGraph(name = "rolesUsuarios", attributeNodes = @NamedAttributeNode("usuariosGruposList"))
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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "usuarios_gruposx",
            joinColumns = {
                @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")})
    private List<UsuariosGrupos> usuariosGruposList;

    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", columnDefinition = "int4")
    @ManyToOne
    private Sucursales idSucursal;

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
    @XmlTransient
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

}
