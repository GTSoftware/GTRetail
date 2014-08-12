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
@Table(name = "bancos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bancos.findAll", query = "SELECT b FROM Bancos b"),
    @NamedQuery(name = "Bancos.findByIdBanco", query = "SELECT b FROM Bancos b WHERE b.idBanco = :idBanco"),
    @NamedQuery(name = "Bancos.findByRazonSocial", query = "SELECT b FROM Bancos b WHERE b.razonSocial = :razonSocial"),
    @NamedQuery(name = "Bancos.findByDireccion", query = "SELECT b FROM Bancos b WHERE b.direccion = :direccion"),
    @NamedQuery(name = "Bancos.findByTelefonoFijo", query = "SELECT b FROM Bancos b WHERE b.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "Bancos.findByCelular", query = "SELECT b FROM Bancos b WHERE b.celular = :celular"),
    @NamedQuery(name = "Bancos.findByCuit", query = "SELECT b FROM Bancos b WHERE b.cuit = :cuit"),
    @NamedQuery(name = "Bancos.findByFechaAlta", query = "SELECT b FROM Bancos b WHERE b.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Bancos.findByActivo", query = "SELECT b FROM Bancos b WHERE b.activo = :activo"),
    @NamedQuery(name = "Bancos.findByObservaciones", query = "SELECT b FROM Bancos b WHERE b.observaciones = :observaciones")})
public class Bancos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_banco")
    private Integer idBanco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 500)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 20)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "cuit")
    private String cuit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBanco")
    private List<BancosCuentas> bancosCuentasList;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    @ManyToOne(optional = false)
    private UbicacionProvincias idProvincia;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private UbicacionPaises idPais;
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad")
    @ManyToOne(optional = false)
    private UbicacionLocalidades idLocalidad;
    @JoinColumn(name = "id_responsabilidad_iva", referencedColumnName = "id_resoponsabildiad_iva")
    @ManyToOne(optional = false)
    private FiscalResponsabilidadesIva idResponsabilidadIva;

    public Bancos() {
    }

    public Bancos(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Bancos(Integer idBanco, String razonSocial, String cuit, Date fechaAlta, boolean activo) {
        this.idBanco = idBanco;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<BancosCuentas> getBancosCuentasList() {
        return bancosCuentasList;
    }

    public void setBancosCuentasList(List<BancosCuentas> bancosCuentasList) {
        this.bancosCuentasList = bancosCuentasList;
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

    public FiscalResponsabilidadesIva getIdResponsabilidadIva() {
        return idResponsabilidadIva;
    }

    public void setIdResponsabilidadIva(FiscalResponsabilidadesIva idResponsabilidadIva) {
        this.idResponsabilidadIva = idResponsabilidadIva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBanco != null ? idBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bancos)) {
            return false;
        }
        Bancos other = (Bancos) object;
        if ((this.idBanco == null && other.idBanco != null) || (this.idBanco != null && !this.idBanco.equals(other.idBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Bancos[ idBanco=" + idBanco + " ]";
    }
    
}
