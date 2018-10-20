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
@Table(name = "contabilidad_periodos_contables")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_periodo_contable", columnDefinition = "serial"))
public class ContabilidadPeriodosContables extends BaseEntity {

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
    @OneToMany(mappedBy = "idPeriodoContable")
    private List<ContabilidadRegistroContable> contabilidadRegistroContableList;

    public ContabilidadPeriodosContables() {
    }

    public ContabilidadPeriodosContables(Long idPeriodoContable) {
        super(idPeriodoContable);
    }

    public ContabilidadPeriodosContables(Long idPeriodoContable, String nombrePeriodo, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        super(idPeriodoContable);
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

    @XmlTransient
    public List<ContabilidadRegistroContable> getContabilidadRegistroContableList() {
        return contabilidadRegistroContableList;
    }

    public void setContabilidadRegistroContableList(List<ContabilidadRegistroContable> contabilidadRegistroContableList) {
        this.contabilidadRegistroContableList = contabilidadRegistroContableList;
    }

}
