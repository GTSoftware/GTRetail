/*
 * Copyright 2018 GT Software.
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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Clase que almacena la información de las facturas del libro de iva compras
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "fiscal_libro_iva_compras")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_registro"))
public class FiscalLibroIvaCompras extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "letra_factura")
    private String letraFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "punto_venta_factura")
    private String puntoVentaFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "total_factura")
    private BigDecimal totalFactura;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_tipo_comprobante", referencedColumnName = "codigo_tipo_comprobante")
    private FiscalTiposComprobante codigoTipoComprobante;

    @OneToMany(mappedBy = "idRegistro")
    private List<ProveedoresComprobantes> comprobantesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistro")
    private List<FiscalLibroIvaComprasLineas> fiscalLibroIvaComprasLineasList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_responsabilidad_iva", referencedColumnName = "id_resoponsabildiad_iva")
    @ManyToOne(optional = false)
    private FiscalResponsabilidadesIva idResponsabilidadIva;
    @JoinColumn(name = "id_periodo_fiscal", referencedColumnName = "id_periodo_fiscal")
    @ManyToOne(optional = false)
    private FiscalPeriodosFiscales idPeriodoFiscal;
    @JoinColumn(name = "id_registro_contable", referencedColumnName = "id_registro")
    @ManyToOne(optional = true)
    private ContabilidadRegistroContable idRegistroContable;

    @Column(name = "cae")
    private Long cae;
    @Column(name = "fecha_vencimiento_cae")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoCae;

    @Column(name = "importe_neto_no_gravado")
    @NotNull
    private BigDecimal importeNetoNoGravado;
    @Column(name = "importe_exento")
    @NotNull
    private BigDecimal importeExento;
    @Column(name = "importe_neto_gravado")
    @NotNull
    private BigDecimal importeNetoGravado;
    @Column(name = "importe_tributos")
    @NotNull
    private BigDecimal importeTributos;
    @Column(name = "importe_iva")
    @NotNull
    private BigDecimal importeIva;
    @Column(name = "importe_percepcion_iva")
    @NotNull
    private BigDecimal importePercepcionIva;
    @Column(name = "importe_percepcion_ingresos_brutos")
    @NotNull
    private BigDecimal importePercepcionIngresosBrutos;

    public FiscalLibroIvaCompras() {
    }

    public FiscalLibroIvaCompras(Long idFactura) {
        super(idFactura);
    }

    public FiscalLibroIvaCompras(Long idFactura, Date fechaFactura, String documento, String letraFactura, String puntoVentaFactura, String numeroFactura) {
        super(idFactura);
        this.fechaFactura = fechaFactura;
        this.documento = documento;
        this.letraFactura = letraFactura;
        this.puntoVentaFactura = puntoVentaFactura;
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLetraFactura() {
        return letraFactura;
    }

    public void setLetraFactura(String letraFactura) {
        this.letraFactura = letraFactura;
    }

    public String getPuntoVentaFactura() {
        return puntoVentaFactura;
    }

    public void setPuntoVentaFactura(String puntoVentaFactura) {
        this.puntoVentaFactura = puntoVentaFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(BigDecimal totalFactura) {
        this.totalFactura = totalFactura;
    }

    @XmlTransient
    public List<ProveedoresComprobantes> getComprobantesList() {
        return comprobantesList;
    }

    public void setComprobantesList(List<ProveedoresComprobantes> comprobantesList) {
        this.comprobantesList = comprobantesList;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public FiscalResponsabilidadesIva getIdResponsabilidadIva() {
        return idResponsabilidadIva;
    }

    public void setIdResponsabilidadIva(FiscalResponsabilidadesIva idResponsabilidadIva) {
        this.idResponsabilidadIva = idResponsabilidadIva;
    }

    public FiscalPeriodosFiscales getIdPeriodoFiscal() {
        return idPeriodoFiscal;
    }

    public void setIdPeriodoFiscal(FiscalPeriodosFiscales idPeriodoFiscal) {
        this.idPeriodoFiscal = idPeriodoFiscal;
    }

    public ContabilidadRegistroContable getIdRegistroContable() {
        return idRegistroContable;
    }

    public void setIdRegistroContable(ContabilidadRegistroContable idRegistroContable) {
        this.idRegistroContable = idRegistroContable;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public FiscalTiposComprobante getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(FiscalTiposComprobante codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public List<FiscalLibroIvaComprasLineas> getFiscalLibroIvaComprasLineasList() {
        return fiscalLibroIvaComprasLineasList;
    }

    public void setFiscalLibroIvaComprasLineasList(List<FiscalLibroIvaComprasLineas> fiscalLibroIvaComprasLineasList) {
        this.fiscalLibroIvaComprasLineasList = fiscalLibroIvaComprasLineasList;
    }

    public Long getCae() {
        return cae;
    }

    public void setCae(Long cae) {
        this.cae = cae;
    }

    public Date getFechaVencimientoCae() {
        return fechaVencimientoCae;
    }

    public void setFechaVencimientoCae(Date fechaVencimientoCae) {
        this.fechaVencimientoCae = fechaVencimientoCae;
    }

    public BigDecimal getImporteNetoNoGravado() {
        return importeNetoNoGravado;
    }

    public void setImporteNetoNoGravado(BigDecimal importeNetoNoGravado) {
        this.importeNetoNoGravado = importeNetoNoGravado;
    }

    public BigDecimal getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento) {
        this.importeExento = importeExento;
    }

    public BigDecimal getImporteNetoGravado() {
        return importeNetoGravado;
    }

    public void setImporteNetoGravado(BigDecimal importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    public BigDecimal getImporteTributos() {
        return importeTributos;
    }

    public void setImporteTributos(BigDecimal importeTributos) {
        this.importeTributos = importeTributos;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    @NotNull
    public BigDecimal getImportePercepcionIva() {
        return importePercepcionIva;
    }

    public void setImportePercepcionIva(@NotNull BigDecimal importePercepcionIva) {
        this.importePercepcionIva = importePercepcionIva;
    }

    @NotNull
    public BigDecimal getImportePercepcionIngresosBrutos() {
        return importePercepcionIngresosBrutos;
    }

    public void setImportePercepcionIngresosBrutos(@NotNull BigDecimal importePercepcionIngresosBrutos) {
        this.importePercepcionIngresosBrutos = importePercepcionIngresosBrutos;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalLibroIvaCompras[ idFactura=" + this.getId() + " ]";
    }

}
