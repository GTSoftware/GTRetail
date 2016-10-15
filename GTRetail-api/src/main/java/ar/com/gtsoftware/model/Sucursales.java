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

import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
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
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "sucursales")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_sucursal", columnDefinition = "serial"))
@NamedEntityGraph(name = "depositos", attributeNodes = @NamedAttributeNode("depositosList"))
public class Sucursales extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_sucursal")
    private String nombreSucursal;
    @Size(max = 500)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
//
//    @OneToMany(mappedBy = "idSucursal")
//    private List<Usuarios> usuariosList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal")
//    private List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionProvincias idProvincia;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionPaises idPais;
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionLocalidades idLocalidad;
    @OneToMany(mappedBy = "idSucursal")
    private List<Depositos> depositosList;

    public Sucursales() {
    }

    public Sucursales(Long idSucursal) {
        super(idSucursal);
    }

    public Sucursales(Long idSucursal, String nombreSucursal, Date fechaAlta, boolean activo) {
        super(idSucursal);
        this.nombreSucursal = nombreSucursal;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
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

//    @XmlTransient
//    public List<Usuarios> getUsuariosList() {
//        return usuariosList;
//    }
//
//    public void setUsuariosList(List<Usuarios> usuariosList) {
//        this.usuariosList = usuariosList;
//    }
//
//    @XmlTransient
//    public List<ProveedoresOrdenesCompra> getProveedoresOrdenesCompraList() {
//        return proveedoresOrdenesCompraList;
//    }
//
//    public void setProveedoresOrdenesCompraList(List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList) {
//        this.proveedoresOrdenesCompraList = proveedoresOrdenesCompraList;
//    }
    @XmlTransient
    public UbicacionProvincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(UbicacionProvincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    @XmlTransient
    public UbicacionPaises getIdPais() {
        return idPais;
    }

    public void setIdPais(UbicacionPaises idPais) {
        this.idPais = idPais;
    }

    @XmlTransient
    public UbicacionLocalidades getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(UbicacionLocalidades idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    @XmlTransient
    public List<Depositos> getDepositosList() {
        return depositosList;
    }

    public void setDepositosList(List<Depositos> depositosList) {
        this.depositosList = depositosList;
    }

    public String getBusinessString() {
        return String.format("[%d] %s", getId(), nombreSucursal);
    }

}
