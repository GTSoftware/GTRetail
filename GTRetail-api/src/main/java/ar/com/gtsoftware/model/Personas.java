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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "personas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_persona", columnDefinition = "serial"))
public class Personas extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String BUSINESS_STRING = "[%d] %s - %s: %s";

    @Column(name = "email", length = 100)
    @Pattern(regexp = "^$|^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 60)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 60)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 200)
    @Column(name = "nombre_fantasia")
    private String nombreFantasia;
    @Size(max = 100)
    @Column(name = "calle")
    private String calle;
    @Size(max = 50)
    @Column(name = "altura")
    private String altura;
    @Size(max = 3)
    @Column(name = "piso")
    private String piso;
    @Size(max = 5)
    @Column(name = "depto")
    private String depto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "documento")
    private String documento;
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
    @Column(name = "cliente")
    private boolean cliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proveedor")
    private boolean proveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<PersonasImagenes> personasImagenesList;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "idProveedor")
    private List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "idPersona")
    private List<Ventas> ventasList;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionProvincias idProvincia;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionPaises idPais;
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionLocalidades idLocalidad;
    @JoinColumn(name = "id_tipo_personeria", referencedColumnName = "id_tipo_personeria", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private LegalTiposPersoneria idTipoPersoneria;
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private LegalTiposDocumento idTipoDocumento;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private LegalGeneros idGenero;
    @JoinColumn(name = "id_responsabilidad_iva", referencedColumnName = "id_resoponsabildiad_iva", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private FiscalResponsabilidadesIva idResponsabilidadIva;
    @OneToMany(mappedBy = "idProveedorHabitual")
    private List<Productos> productosList;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "idPersona")
    private List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "idPersona")
    private List<PersonasTelefonos> personasTelefonosList;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "idPersona")
    private List<PersonasCuentaCorriente> personasCuentaCorrienteList;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;

    public Personas() {
    }

    public Personas(Long idPersona) {
        super(idPersona);
    }

    public Personas(Long idPersona, String razonSocial, String documento, Date fechaAlta, boolean activo, boolean cliente, boolean proveedor) {
        super(idPersona);
        this.razonSocial = razonSocial;
        this.documento = documento;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.cliente = cliente;
        this.proveedor = proveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public boolean getCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public boolean getProveedor() {
        return proveedor;
    }

    public void setProveedor(boolean proveedor) {
        this.proveedor = proveedor;
    }

    @XmlTransient
    public List<PersonasImagenes> getPersonasImagenesList() {
        return personasImagenesList;
    }

    public void setPersonasImagenesList(List<PersonasImagenes> personasImagenesList) {
        this.personasImagenesList = personasImagenesList;
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

    public UbicacionProvincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(UbicacionProvincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    public UbicacionPaises getIdPais() {
        return idPais;
    }

    public void setIdPais(UbicacionPaises idPais) {
        this.idPais = idPais;
    }

    public UbicacionLocalidades getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(UbicacionLocalidades idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public LegalTiposPersoneria getIdTipoPersoneria() {
        return idTipoPersoneria;
    }

    public void setIdTipoPersoneria(LegalTiposPersoneria idTipoPersoneria) {
        this.idTipoPersoneria = idTipoPersoneria;
    }

    public LegalTiposDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(LegalTiposDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public LegalGeneros getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(LegalGeneros idGenero) {
        this.idGenero = idGenero;
    }

    public FiscalResponsabilidadesIva getIdResponsabilidadIva() {
        return idResponsabilidadIva;
    }

    public void setIdResponsabilidadIva(FiscalResponsabilidadesIva idResponsabilidadIva) {
        this.idResponsabilidadIva = idResponsabilidadIva;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @XmlTransient
    public List<FiscalLibroIvaVentas> getFiscalLibroIvaVentasList() {
        return fiscalLibroIvaVentasList;
    }

    public void setFiscalLibroIvaVentasList(List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList) {
        this.fiscalLibroIvaVentasList = fiscalLibroIvaVentasList;
    }

    @XmlTransient
    public List<PersonasTelefonos> getPersonasTelefonosList() {
        return personasTelefonosList;
    }

    public void setPersonasTelefonosList(List<PersonasTelefonos> personasTelefonosList) {
        this.personasTelefonosList = personasTelefonosList;
    }

    @XmlTransient
    public List<PersonasCuentaCorriente> getPersonasCuentaCorrienteList() {
        return personasCuentaCorrienteList;
    }

    public void setPersonasCuentaCorrienteList(List<PersonasCuentaCorriente> personasCuentaCorrienteList) {
        this.personasCuentaCorrienteList = personasCuentaCorrienteList;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Personas[ idPersona=" + this.getId() + " ]";
    }

    public String getBusinessString() {
        return String.format(BUSINESS_STRING, this.getId(), razonSocial, idTipoDocumento.getNombreTipoDocumento(), documento);
    }
}
