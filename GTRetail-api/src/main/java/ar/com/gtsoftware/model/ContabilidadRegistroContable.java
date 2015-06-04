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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "contabilidad_registro_contable")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_registro"))
public class ContabilidadRegistroContable extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_proceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProceso;
    @Size(max = 100)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 10)
    @Column(name = "letra_comprobante")
    private String letraComprobante;
    @Size(max = 10)
    @Column(name = "punto_venta_comprobante")
    private String puntoVentaComprobante;
    @Size(max = 100)
    @Column(name = "numero_comprobante")
    private String numeroComprobante;
    @Size(max = 11)
    @Column(name = "cuit_emisor_comprobante")
    private String cuitEmisorComprobante;
    @Size(max = 11)
    @Column(name = "cuit_receptor_comprobante")
    private String cuitReceptorComprobante;
    @Column(name = "fecha_comprobante")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaComprobante;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Size(max = 2000)
    @Column(name = "concepto_comprobante")
    private String conceptoComprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistroContable")
    private List<ContabilidadRegistroContableLineas> contabilidadRegistroContableLineasList;
    @JoinColumn(name = "id_periodo_fiscal", referencedColumnName = "id_periodo_fiscal")
    @ManyToOne
    private FiscalPeriodosFiscales idPeriodoFiscal;
    @JoinColumn(name = "id_tipo_operacion", referencedColumnName = "id_tipo_operacion")
    @ManyToOne
    private ContabilidadTiposOperacion idTipoOperacion;
    @JoinColumn(name = "id_tipo_comprobante", referencedColumnName = "id_tipo_comprobante")
    @ManyToOne(optional = false)
    private ContabilidadTiposComprobantes idTipoComprobante;
    @JoinColumn(name = "id_periodo_contable", referencedColumnName = "id_periodo_contable")
    @ManyToOne
    private ContabilidadPeriodosContables idPeriodoContable;
    @JoinColumn(name = "id_libro", referencedColumnName = "id_libro")
    @ManyToOne(optional = false)
    private ContabilidadLibros idLibro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistroContable")
    private List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistroContable")
    private List<PersonasCuentaCorriente> personasCuentaCorrienteList;

    public ContabilidadRegistroContable() {
    }

    public ContabilidadRegistroContable(Long idRegistro) {
        super(idRegistro);
    }

    public ContabilidadRegistroContable(Long idRegistro, Date fechaProceso) {
        super(idRegistro);
        this.fechaProceso = fechaProceso;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLetraComprobante() {
        return letraComprobante;
    }

    public void setLetraComprobante(String letraComprobante) {
        this.letraComprobante = letraComprobante;
    }

    public String getPuntoVentaComprobante() {
        return puntoVentaComprobante;
    }

    public void setPuntoVentaComprobante(String puntoVentaComprobante) {
        this.puntoVentaComprobante = puntoVentaComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getCuitEmisorComprobante() {
        return cuitEmisorComprobante;
    }

    public void setCuitEmisorComprobante(String cuitEmisorComprobante) {
        this.cuitEmisorComprobante = cuitEmisorComprobante;
    }

    public String getCuitReceptorComprobante() {
        return cuitReceptorComprobante;
    }

    public void setCuitReceptorComprobante(String cuitReceptorComprobante) {
        this.cuitReceptorComprobante = cuitReceptorComprobante;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getConceptoComprobante() {
        return conceptoComprobante;
    }

    public void setConceptoComprobante(String conceptoComprobante) {
        this.conceptoComprobante = conceptoComprobante;
    }

    @XmlTransient
    public List<ContabilidadRegistroContableLineas> getContabilidadRegistroContableLineasList() {
        return contabilidadRegistroContableLineasList;
    }

    public void setContabilidadRegistroContableLineasList(List<ContabilidadRegistroContableLineas> contabilidadRegistroContableLineasList) {
        this.contabilidadRegistroContableLineasList = contabilidadRegistroContableLineasList;
    }

    public FiscalPeriodosFiscales getIdPeriodoFiscal() {
        return idPeriodoFiscal;
    }

    public void setIdPeriodoFiscal(FiscalPeriodosFiscales idPeriodoFiscal) {
        this.idPeriodoFiscal = idPeriodoFiscal;
    }

    public ContabilidadTiposOperacion getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(ContabilidadTiposOperacion idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public ContabilidadTiposComprobantes getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(ContabilidadTiposComprobantes idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public ContabilidadPeriodosContables getIdPeriodoContable() {
        return idPeriodoContable;
    }

    public void setIdPeriodoContable(ContabilidadPeriodosContables idPeriodoContable) {
        this.idPeriodoContable = idPeriodoContable;
    }

    public ContabilidadLibros getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(ContabilidadLibros idLibro) {
        this.idLibro = idLibro;
    }

    @XmlTransient
    public List<FiscalLibroIvaVentas> getFiscalLibroIvaVentasList() {
        return fiscalLibroIvaVentasList;
    }

    public void setFiscalLibroIvaVentasList(List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList) {
        this.fiscalLibroIvaVentasList = fiscalLibroIvaVentasList;
    }

    @XmlTransient
    public List<PersonasCuentaCorriente> getPersonasCuentaCorrienteList() {
        return personasCuentaCorrienteList;
    }

    public void setPersonasCuentaCorrienteList(List<PersonasCuentaCorriente> personasCuentaCorrienteList) {
        this.personasCuentaCorrienteList = personasCuentaCorrienteList;
    }

}
