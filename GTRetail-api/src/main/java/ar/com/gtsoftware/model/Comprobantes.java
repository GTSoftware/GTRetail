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
import java.util.ArrayList;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que representa las ventas que se realizan
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "comprobantes")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_comprobante"))
public class Comprobantes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_comprobante")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaComprobante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 100)
    @Column(name = "remitente")
    private String remitente;
    @Size(max = 100)
    @Column(name = "nroremito")
    private String nroRemito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulada")
    private boolean anulada;

    @Size(max = 1)
    @Column(name = "letra")
    private String letra;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_negocio_tipo_comprobante", referencedColumnName = "id_negocio_tipo_comprobante")
    private NegocioTiposComprobante tipoComprobante;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComprobante")
    private List<ComprobantesLineas> comprobantesLineasList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_condicion_comprobante", referencedColumnName = "id_condicion")
    @ManyToOne(optional = false)
    private NegocioCondicionesOperaciones idCondicionComprobante;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private ComprobantesEstados idEstadoComprobante;
    @JoinColumn(name = "id_registro_iva", referencedColumnName = "id_registro")
    @ManyToOne
    private FiscalLibroIvaVentas idRegistro;

    @Transient
    private BigDecimal totalConSigno;
    @OneToMany(mappedBy = "idComprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComprobantesPagos> comprobantesPagoss;

    public Comprobantes() {
    }

    public Comprobantes(Long idVenta) {
        super(idVenta);
    }

    public Comprobantes(Long idVenta, Date fechaVenta, BigDecimal total, BigDecimal saldo, boolean anulada) {
        super(idVenta);
        this.fechaComprobante = fechaVenta;
        this.total = total;
        this.saldo = saldo;
        this.anulada = anulada;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    @XmlTransient
    public List<ComprobantesLineas> getComprobantesLineasList() {
        return comprobantesLineasList;
    }

    public void setComprobantesLineasList(List<ComprobantesLineas> comprobantesLineasList) {
        this.comprobantesLineasList = comprobantesLineasList;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public NegocioCondicionesOperaciones getIdCondicionComprobante() {
        return idCondicionComprobante;
    }

    public void setIdCondicionComprobante(NegocioCondicionesOperaciones idCondicionComprobante) {
        this.idCondicionComprobante = idCondicionComprobante;
    }

    public FiscalLibroIvaVentas getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(FiscalLibroIvaVentas idRegistro) {
        this.idRegistro = idRegistro;
    }

    public ComprobantesEstados getIdEstadoComprobante() {
        return idEstadoComprobante;
    }

    public void setIdEstadoComprobante(ComprobantesEstados idEstadoComprobante) {
        this.idEstadoComprobante = idEstadoComprobante;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(String nroRemito) {
        this.nroRemito = nroRemito;
    }

    public void addLineaVenta(ComprobantesLineas linea) {
        if (comprobantesLineasList == null) {
            comprobantesLineasList = new ArrayList<>();
        }
        comprobantesLineasList.add(linea);
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public NegocioTiposComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(NegocioTiposComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    @XmlTransient
    public List<ComprobantesPagos> getComprobantesPagoss() {
        return comprobantesPagoss;
    }

    public void setComprobantesPagoss(List<ComprobantesPagos> comprobantesPagoss) {
        this.comprobantesPagoss = comprobantesPagoss;
    }

    public BigDecimal getTotalConSigno() {
        if (total != null && tipoComprobante != null) {
            if (totalConSigno == null) {
                totalConSigno = total.multiply(tipoComprobante.getSigno());
            }
        }
        return totalConSigno;
    }
}
