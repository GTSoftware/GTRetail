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

import java.math.BigDecimal;
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
 * Clase que almacena la información de las facturas del libro de iva ventas
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "fiscal_libro_iva_ventas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_factura", columnDefinition = "serial"))
public class FiscalLibroIvaVentas extends BaseEntity {

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
    @Size(min = 1, max = 4)
    @Column(name = "punto_venta_factura")
    private String puntoVentaFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_factura")
    private BigDecimal totalFactura;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_tipo_comprobante", referencedColumnName = "codigo_tipo_comprobante", columnDefinition = "bpchar")
    private FiscalTiposComprobante codigoTipoComprobante;

    @OneToMany(mappedBy = "idRegistroIva")
    private List<Ventas> ventasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactura")
    private List<FiscalLibroIvaVentasLineas> fiscalLibroIvaVentasLineasList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_responsabilidad_iva", referencedColumnName = "id_resoponsabildiad_iva", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private FiscalResponsabilidadesIva idResponsabilidadIva;
    @JoinColumn(name = "id_periodo_fiscal", referencedColumnName = "id_periodo_fiscal", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private FiscalPeriodosFiscales idPeriodoFiscal;
    @JoinColumn(name = "id_registro_contable", referencedColumnName = "id_registro", columnDefinition = "int4")
    @ManyToOne(optional = true)
    private ContabilidadRegistroContable idRegistroContable;

    public FiscalLibroIvaVentas() {
    }

    public FiscalLibroIvaVentas(Long idFactura) {
        super(idFactura);
    }

    public FiscalLibroIvaVentas(Long idFactura, Date fechaFactura, String documento, String letraFactura, String puntoVentaFactura, String numeroFactura) {
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
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
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

    public List<FiscalLibroIvaVentasLineas> getFiscalLibroIvaVentasLineasList() {
        return fiscalLibroIvaVentasLineasList;
    }

    public void setFiscalLibroIvaVentasLineasList(List<FiscalLibroIvaVentasLineas> fiscalLibroIvaVentasLineasList) {
        this.fiscalLibroIvaVentasLineasList = fiscalLibroIvaVentasLineasList;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalLibroIvaVentas[ idFactura=" + this.getId() + " ]";
    }

}
