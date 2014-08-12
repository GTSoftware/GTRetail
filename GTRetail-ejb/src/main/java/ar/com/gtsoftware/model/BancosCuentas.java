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
@Table(name = "bancos_cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BancosCuentas.findAll", query = "SELECT b FROM BancosCuentas b"),
    @NamedQuery(name = "BancosCuentas.findByIdCuentaBanco", query = "SELECT b FROM BancosCuentas b WHERE b.idCuentaBanco = :idCuentaBanco"),
    @NamedQuery(name = "BancosCuentas.findByDescripcionCuenta", query = "SELECT b FROM BancosCuentas b WHERE b.descripcionCuenta = :descripcionCuenta"),
    @NamedQuery(name = "BancosCuentas.findByNumeroCuenta", query = "SELECT b FROM BancosCuentas b WHERE b.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "BancosCuentas.findByNumeroSucursal", query = "SELECT b FROM BancosCuentas b WHERE b.numeroSucursal = :numeroSucursal"),
    @NamedQuery(name = "BancosCuentas.findByCbu", query = "SELECT b FROM BancosCuentas b WHERE b.cbu = :cbu"),
    @NamedQuery(name = "BancosCuentas.findByActivo", query = "SELECT b FROM BancosCuentas b WHERE b.activo = :activo"),
    @NamedQuery(name = "BancosCuentas.findByFechaApertura", query = "SELECT b FROM BancosCuentas b WHERE b.fechaApertura = :fechaApertura")})
public class BancosCuentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta_banco")
    private Integer idCuentaBanco;
    @Size(max = 200)
    @Column(name = "descripcion_cuenta")
    private String descripcionCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Size(max = 10)
    @Column(name = "numero_sucursal")
    private String numeroSucursal;
    @Size(max = 22)
    @Column(name = "cbu")
    private String cbu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "fecha_apertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApertura;
    @JoinColumn(name = "id_moneda", referencedColumnName = "id_moneda")
    @ManyToOne(optional = false)
    private ContabilidadMonedas idMoneda;
    @JoinColumn(name = "id_tipo_cuenta_banco", referencedColumnName = "id_tipo_cuenta_banco")
    @ManyToOne(optional = false)
    private BancosTiposCuenta idTipoCuentaBanco;
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    @ManyToOne(optional = false)
    private Bancos idBanco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaBanco")
    private List<BancosCuentaCorriente> bancosCuentaCorrienteList;

    public BancosCuentas() {
    }

    public BancosCuentas(Integer idCuentaBanco) {
        this.idCuentaBanco = idCuentaBanco;
    }

    public BancosCuentas(Integer idCuentaBanco, String numeroCuenta, boolean activo) {
        this.idCuentaBanco = idCuentaBanco;
        this.numeroCuenta = numeroCuenta;
        this.activo = activo;
    }

    public Integer getIdCuentaBanco() {
        return idCuentaBanco;
    }

    public void setIdCuentaBanco(Integer idCuentaBanco) {
        this.idCuentaBanco = idCuentaBanco;
    }

    public String getDescripcionCuenta() {
        return descripcionCuenta;
    }

    public void setDescripcionCuenta(String descripcionCuenta) {
        this.descripcionCuenta = descripcionCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(String numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public ContabilidadMonedas getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(ContabilidadMonedas idMoneda) {
        this.idMoneda = idMoneda;
    }

    public BancosTiposCuenta getIdTipoCuentaBanco() {
        return idTipoCuentaBanco;
    }

    public void setIdTipoCuentaBanco(BancosTiposCuenta idTipoCuentaBanco) {
        this.idTipoCuentaBanco = idTipoCuentaBanco;
    }

    public Bancos getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Bancos idBanco) {
        this.idBanco = idBanco;
    }

    @XmlTransient
    public List<BancosCuentaCorriente> getBancosCuentaCorrienteList() {
        return bancosCuentaCorrienteList;
    }

    public void setBancosCuentaCorrienteList(List<BancosCuentaCorriente> bancosCuentaCorrienteList) {
        this.bancosCuentaCorrienteList = bancosCuentaCorrienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuentaBanco != null ? idCuentaBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BancosCuentas)) {
            return false;
        }
        BancosCuentas other = (BancosCuentas) object;
        if ((this.idCuentaBanco == null && other.idCuentaBanco != null) || (this.idCuentaBanco != null && !this.idCuentaBanco.equals(other.idCuentaBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.BancosCuentas[ idCuentaBanco=" + idCuentaBanco + " ]";
    }
    
}
