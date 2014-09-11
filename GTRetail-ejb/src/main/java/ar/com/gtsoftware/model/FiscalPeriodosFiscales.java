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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "fiscal_periodos_fiscales")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_periodo_fiscal"))
public class FiscalPeriodosFiscales extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    @Basic(optional = false)
    @Column(name = "periodo_cerrado")
    private boolean periodoCerrado;

    public FiscalPeriodosFiscales() {
    }

    public FiscalPeriodosFiscales(Long idPeriodoFiscal) {
        super(idPeriodoFiscal);
    }

    public FiscalPeriodosFiscales(Long idPeriodoFiscal, String nombrePeriodo, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        super(idPeriodoFiscal);
        this.nombrePeriodo = nombrePeriodo;
        this.fechaInicioPeriodo = fechaInicioPeriodo;
        this.fechaFinPeriodo = fechaFinPeriodo;
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

    public boolean isPeriodoCerrado() {
        return periodoCerrado;
    }

    public void setPeriodoCerrado(boolean periodoCerrado) {
        this.periodoCerrado = periodoCerrado;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalPeriodosFiscales[ idPeriodoFiscal=" + this.getId() + " ]";
    }

}
