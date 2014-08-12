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
@Table(name = "fiscal_responsabilidades_iva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FiscalResponsabilidadesIva.findAll", query = "SELECT f FROM FiscalResponsabilidadesIva f"),
    @NamedQuery(name = "FiscalResponsabilidadesIva.findByIdResoponsabildiadIva", query = "SELECT f FROM FiscalResponsabilidadesIva f WHERE f.idResoponsabildiadIva = :idResoponsabildiadIva"),
    @NamedQuery(name = "FiscalResponsabilidadesIva.findByNombreResponsabildiad", query = "SELECT f FROM FiscalResponsabilidadesIva f WHERE f.nombreResponsabildiad = :nombreResponsabildiad")})
public class FiscalResponsabilidadesIva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resoponsabildiad_iva")
    private Integer idResoponsabildiadIva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_responsabildiad")
    private String nombreResponsabildiad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fiscalResponsabilidadesIva")
    private List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fiscalResponsabilidadesIva1")
    private List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsabilidadIva")
    private List<Bancos> bancosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsabilidadIva")
    private List<Personas> personasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsabilidadIva")
    private List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList;

    public FiscalResponsabilidadesIva() {
    }

    public FiscalResponsabilidadesIva(Integer idResoponsabildiadIva) {
        this.idResoponsabildiadIva = idResoponsabildiadIva;
    }

    public FiscalResponsabilidadesIva(Integer idResoponsabildiadIva, String nombreResponsabildiad) {
        this.idResoponsabildiadIva = idResoponsabildiadIva;
        this.nombreResponsabildiad = nombreResponsabildiad;
    }

    public Integer getIdResoponsabildiadIva() {
        return idResoponsabildiadIva;
    }

    public void setIdResoponsabildiadIva(Integer idResoponsabildiadIva) {
        this.idResoponsabildiadIva = idResoponsabildiadIva;
    }

    public String getNombreResponsabildiad() {
        return nombreResponsabildiad;
    }

    public void setNombreResponsabildiad(String nombreResponsabildiad) {
        this.nombreResponsabildiad = nombreResponsabildiad;
    }

    @XmlTransient
    public List<FiscalLetrasComprobantes> getFiscalLetrasComprobantesList() {
        return fiscalLetrasComprobantesList;
    }

    public void setFiscalLetrasComprobantesList(List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList) {
        this.fiscalLetrasComprobantesList = fiscalLetrasComprobantesList;
    }

    @XmlTransient
    public List<FiscalLetrasComprobantes> getFiscalLetrasComprobantesList1() {
        return fiscalLetrasComprobantesList1;
    }

    public void setFiscalLetrasComprobantesList1(List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList1) {
        this.fiscalLetrasComprobantesList1 = fiscalLetrasComprobantesList1;
    }

    @XmlTransient
    public List<Bancos> getBancosList() {
        return bancosList;
    }

    public void setBancosList(List<Bancos> bancosList) {
        this.bancosList = bancosList;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @XmlTransient
    public List<FiscalLibroIvaVentas> getFiscalLibroIvaVentasList() {
        return fiscalLibroIvaVentasList;
    }

    public void setFiscalLibroIvaVentasList(List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList) {
        this.fiscalLibroIvaVentasList = fiscalLibroIvaVentasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResoponsabildiadIva != null ? idResoponsabildiadIva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiscalResponsabilidadesIva)) {
            return false;
        }
        FiscalResponsabilidadesIva other = (FiscalResponsabilidadesIva) object;
        if ((this.idResoponsabildiadIva == null && other.idResoponsabildiadIva != null) || (this.idResoponsabildiadIva != null && !this.idResoponsabildiadIva.equals(other.idResoponsabildiadIva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalResponsabilidadesIva[ idResoponsabildiadIva=" + idResoponsabildiadIva + " ]";
    }
    
}
