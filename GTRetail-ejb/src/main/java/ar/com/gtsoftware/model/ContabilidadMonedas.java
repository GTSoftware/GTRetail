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
@Table(name = "contabilidad_monedas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadMonedas.findAll", query = "SELECT c FROM ContabilidadMonedas c"),
    @NamedQuery(name = "ContabilidadMonedas.findByIdMoneda", query = "SELECT c FROM ContabilidadMonedas c WHERE c.idMoneda = :idMoneda"),
    @NamedQuery(name = "ContabilidadMonedas.findByNombreMoneda", query = "SELECT c FROM ContabilidadMonedas c WHERE c.nombreMoneda = :nombreMoneda"),
    @NamedQuery(name = "ContabilidadMonedas.findByNombreCortoMoneda", query = "SELECT c FROM ContabilidadMonedas c WHERE c.nombreCortoMoneda = :nombreCortoMoneda"),
    @NamedQuery(name = "ContabilidadMonedas.findBySimboloMoneda", query = "SELECT c FROM ContabilidadMonedas c WHERE c.simboloMoneda = :simboloMoneda")})
public class ContabilidadMonedas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_moneda")
    private Integer idMoneda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_moneda")
    private String nombreMoneda;
    @Size(max = 10)
    @Column(name = "nombre_corto_moneda")
    private String nombreCortoMoneda;
    @Size(max = 5)
    @Column(name = "simbolo_moneda")
    private String simboloMoneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
    private List<BancosCuentas> bancosCuentasList;

    public ContabilidadMonedas() {
    }

    public ContabilidadMonedas(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public ContabilidadMonedas(Integer idMoneda, String nombreMoneda) {
        this.idMoneda = idMoneda;
        this.nombreMoneda = nombreMoneda;
    }

    public Integer getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public String getNombreCortoMoneda() {
        return nombreCortoMoneda;
    }

    public void setNombreCortoMoneda(String nombreCortoMoneda) {
        this.nombreCortoMoneda = nombreCortoMoneda;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
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
        hash += (idMoneda != null ? idMoneda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadMonedas)) {
            return false;
        }
        ContabilidadMonedas other = (ContabilidadMonedas) object;
        if ((this.idMoneda == null && other.idMoneda != null) || (this.idMoneda != null && !this.idMoneda.equals(other.idMoneda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ContabilidadMonedas[ idMoneda=" + idMoneda + " ]";
    }
    
}
