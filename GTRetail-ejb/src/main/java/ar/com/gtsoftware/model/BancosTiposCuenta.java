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
@Table(name = "bancos_tipos_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BancosTiposCuenta.findAll", query = "SELECT b FROM BancosTiposCuenta b"),
    @NamedQuery(name = "BancosTiposCuenta.findByIdTipoCuentaBanco", query = "SELECT b FROM BancosTiposCuenta b WHERE b.idTipoCuentaBanco = :idTipoCuentaBanco"),
    @NamedQuery(name = "BancosTiposCuenta.findByNombreTipoCuenta", query = "SELECT b FROM BancosTiposCuenta b WHERE b.nombreTipoCuenta = :nombreTipoCuenta")})
public class BancosTiposCuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_cuenta_banco")
    private Integer idTipoCuentaBanco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_tipo_cuenta")
    private String nombreTipoCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCuentaBanco")
    private List<BancosCuentas> bancosCuentasList;

    public BancosTiposCuenta() {
    }

    public BancosTiposCuenta(Integer idTipoCuentaBanco) {
        this.idTipoCuentaBanco = idTipoCuentaBanco;
    }

    public BancosTiposCuenta(Integer idTipoCuentaBanco, String nombreTipoCuenta) {
        this.idTipoCuentaBanco = idTipoCuentaBanco;
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

    public Integer getIdTipoCuentaBanco() {
        return idTipoCuentaBanco;
    }

    public void setIdTipoCuentaBanco(Integer idTipoCuentaBanco) {
        this.idTipoCuentaBanco = idTipoCuentaBanco;
    }

    public String getNombreTipoCuenta() {
        return nombreTipoCuenta;
    }

    public void setNombreTipoCuenta(String nombreTipoCuenta) {
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

    @XmlTransient
    public List<BancosCuentas> getBancosCuentasList() {
        return bancosCuentasList;
    }

    public void setBancosCuentasList(List<BancosCuentas> bancosCuentasList) {
        this.bancosCuentasList = bancosCuentasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCuentaBanco != null ? idTipoCuentaBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BancosTiposCuenta)) {
            return false;
        }
        BancosTiposCuenta other = (BancosTiposCuenta) object;
        if ((this.idTipoCuentaBanco == null && other.idTipoCuentaBanco != null) || (this.idTipoCuentaBanco != null && !this.idTipoCuentaBanco.equals(other.idTipoCuentaBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.BancosTiposCuenta[ idTipoCuentaBanco=" + idTipoCuentaBanco + " ]";
    }
    
}
