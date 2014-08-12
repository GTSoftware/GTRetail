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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "contabilidad_plan_cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContabilidadPlanCuentas.findAll", query = "SELECT c FROM ContabilidadPlanCuentas c"),
    @NamedQuery(name = "ContabilidadPlanCuentas.findByIdCuenta", query = "SELECT c FROM ContabilidadPlanCuentas c WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "ContabilidadPlanCuentas.findByNombreCuenta", query = "SELECT c FROM ContabilidadPlanCuentas c WHERE c.nombreCuenta = :nombreCuenta"),
    @NamedQuery(name = "ContabilidadPlanCuentas.findByNumeroCuenta", query = "SELECT c FROM ContabilidadPlanCuentas c WHERE c.numeroCuenta = :numeroCuenta"),
    @NamedQuery(name = "ContabilidadPlanCuentas.findByDescripcionCuenta", query = "SELECT c FROM ContabilidadPlanCuentas c WHERE c.descripcionCuenta = :descripcionCuenta"),
    @NamedQuery(name = "ContabilidadPlanCuentas.findByCuentaRubro", query = "SELECT c FROM ContabilidadPlanCuentas c WHERE c.cuentaRubro = :cuentaRubro")})
public class ContabilidadPlanCuentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_cuenta")
    private String nombreCuenta;
    @Size(max = 100)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Size(max = 2000)
    @Column(name = "descripcion_cuenta")
    private String descripcionCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta_rubro")
    private boolean cuentaRubro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuenta")
    private List<ContabilidadRegistroContableLineas> contabilidadRegistroContableLineasList;
    @JoinColumn(name = "id_tipo_cuenta", referencedColumnName = "id_tipo_cuenta")
    @ManyToOne(optional = false)
    private ContabilidadTiposCuenta idTipoCuenta;
    @OneToMany(mappedBy = "idCuentaPadre")
    private List<ContabilidadPlanCuentas> contabilidadPlanCuentasList;
    @JoinColumn(name = "id_cuenta_padre", referencedColumnName = "id_cuenta")
    @ManyToOne
    private ContabilidadPlanCuentas idCuentaPadre;

    public ContabilidadPlanCuentas() {
    }

    public ContabilidadPlanCuentas(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public ContabilidadPlanCuentas(Integer idCuenta, String nombreCuenta, boolean cuentaRubro) {
        this.idCuenta = idCuenta;
        this.nombreCuenta = nombreCuenta;
        this.cuentaRubro = cuentaRubro;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getDescripcionCuenta() {
        return descripcionCuenta;
    }

    public void setDescripcionCuenta(String descripcionCuenta) {
        this.descripcionCuenta = descripcionCuenta;
    }

    public boolean getCuentaRubro() {
        return cuentaRubro;
    }

    public void setCuentaRubro(boolean cuentaRubro) {
        this.cuentaRubro = cuentaRubro;
    }

    @XmlTransient
    public List<ContabilidadRegistroContableLineas> getContabilidadRegistroContableLineasList() {
        return contabilidadRegistroContableLineasList;
    }

    public void setContabilidadRegistroContableLineasList(List<ContabilidadRegistroContableLineas> contabilidadRegistroContableLineasList) {
        this.contabilidadRegistroContableLineasList = contabilidadRegistroContableLineasList;
    }

    public ContabilidadTiposCuenta getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(ContabilidadTiposCuenta idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    @XmlTransient
    public List<ContabilidadPlanCuentas> getContabilidadPlanCuentasList() {
        return contabilidadPlanCuentasList;
    }

    public void setContabilidadPlanCuentasList(List<ContabilidadPlanCuentas> contabilidadPlanCuentasList) {
        this.contabilidadPlanCuentasList = contabilidadPlanCuentasList;
    }

    public ContabilidadPlanCuentas getIdCuentaPadre() {
        return idCuentaPadre;
    }

    public void setIdCuentaPadre(ContabilidadPlanCuentas idCuentaPadre) {
        this.idCuentaPadre = idCuentaPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadPlanCuentas)) {
            return false;
        }
        ContabilidadPlanCuentas other = (ContabilidadPlanCuentas) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ContabilidadPlanCuentas[ idCuenta=" + idCuenta + " ]";
    }
    
}
