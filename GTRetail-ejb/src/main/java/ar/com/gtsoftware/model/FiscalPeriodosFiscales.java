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
@Table(name = "fiscal_periodos_fiscales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FiscalPeriodosFiscales.findAll", query = "SELECT f FROM FiscalPeriodosFiscales f"),
    @NamedQuery(name = "FiscalPeriodosFiscales.findByIdPeriodoFiscal", query = "SELECT f FROM FiscalPeriodosFiscales f WHERE f.idPeriodoFiscal = :idPeriodoFiscal"),
    @NamedQuery(name = "FiscalPeriodosFiscales.findByNombrePeriodo", query = "SELECT f FROM FiscalPeriodosFiscales f WHERE f.nombrePeriodo = :nombrePeriodo"),
    @NamedQuery(name = "FiscalPeriodosFiscales.findByFechaInicioPeriodo", query = "SELECT f FROM FiscalPeriodosFiscales f WHERE f.fechaInicioPeriodo = :fechaInicioPeriodo"),
    @NamedQuery(name = "FiscalPeriodosFiscales.findByFechaFinPeriodo", query = "SELECT f FROM FiscalPeriodosFiscales f WHERE f.fechaFinPeriodo = :fechaFinPeriodo")})
public class FiscalPeriodosFiscales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo_fiscal")
    private Integer idPeriodoFiscal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_periodo")
    private String nombrePeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_periodo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin_periodo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinPeriodo;
    @OneToMany(mappedBy = "idPeriodoFiscal")
    private List<ContabilidadRegistroContable> contabilidadRegistroContableList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoFiscal")
    private List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList;

    public FiscalPeriodosFiscales() {
    }

    public FiscalPeriodosFiscales(Integer idPeriodoFiscal) {
        this.idPeriodoFiscal = idPeriodoFiscal;
    }

    public FiscalPeriodosFiscales(Integer idPeriodoFiscal, String nombrePeriodo, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        this.idPeriodoFiscal = idPeriodoFiscal;
        this.nombrePeriodo = nombrePeriodo;
        this.fechaInicioPeriodo = fechaInicioPeriodo;
        this.fechaFinPeriodo = fechaFinPeriodo;
    }

    public Integer getIdPeriodoFiscal() {
        return idPeriodoFiscal;
    }

    public void setIdPeriodoFiscal(Integer idPeriodoFiscal) {
        this.idPeriodoFiscal = idPeriodoFiscal;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public Date getFechaInicioPeriodo() {
        return fechaInicioPeriodo;
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo;
    }

    public Date getFechaFinPeriodo() {
        return fechaFinPeriodo;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo;
    }

    @XmlTransient
    public List<ContabilidadRegistroContable> getContabilidadRegistroContableList() {
        return contabilidadRegistroContableList;
    }

    public void setContabilidadRegistroContableList(List<ContabilidadRegistroContable> contabilidadRegistroContableList) {
        this.contabilidadRegistroContableList = contabilidadRegistroContableList;
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
        hash += (idPeriodoFiscal != null ? idPeriodoFiscal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiscalPeriodosFiscales)) {
            return false;
        }
        FiscalPeriodosFiscales other = (FiscalPeriodosFiscales) object;
        if ((this.idPeriodoFiscal == null && other.idPeriodoFiscal != null) || (this.idPeriodoFiscal != null && !this.idPeriodoFiscal.equals(other.idPeriodoFiscal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalPeriodosFiscales[ idPeriodoFiscal=" + idPeriodoFiscal + " ]";
    }
    
}
