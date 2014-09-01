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
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "ventas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_venta"))
public class Ventas extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulada")
    private boolean anulada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private List<VentasRemitos> ventasRemitosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private List<VentasLineas> ventasLineasList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_condicion_venta", referencedColumnName = "id_condicion")
    @ManyToOne(optional = false)
    private NegocioCondicionesOperaciones idCondicionVenta;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private VentasEstados idVentasEstados;
    @JoinColumn(name = "id_registro_iva", referencedColumnName = "id_factura")
    @ManyToOne
    private FiscalLibroIvaVentas idRegistroIva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private List<VentasPagosLineas> ventasPagosLineasList;

    public Ventas() {
    }

    public Ventas(Integer idVenta) {
        super(idVenta);
    }

    public Ventas(Integer idVenta, Date fechaVenta, BigDecimal total, BigDecimal saldo, boolean anulada) {
        super(idVenta);
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.saldo = saldo;
        this.anulada = anulada;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
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
    public List<VentasRemitos> getVentasRemitosList() {
        return ventasRemitosList;
    }

    public void setVentasRemitosList(List<VentasRemitos> ventasRemitosList) {
        this.ventasRemitosList = ventasRemitosList;
    }

    @XmlTransient
    public List<VentasLineas> getVentasLineasList() {
        return ventasLineasList;
    }

    public void setVentasLineasList(List<VentasLineas> ventasLineasList) {
        this.ventasLineasList = ventasLineasList;
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

    public NegocioCondicionesOperaciones getIdCondicionVenta() {
        return idCondicionVenta;
    }

    public void setIdCondicionVenta(NegocioCondicionesOperaciones idCondicionVenta) {
        this.idCondicionVenta = idCondicionVenta;
    }

    public FiscalLibroIvaVentas getIdRegistroIva() {
        return idRegistroIva;
    }

    public void setIdRegistroIva(FiscalLibroIvaVentas idRegistroIva) {
        this.idRegistroIva = idRegistroIva;
    }

    @XmlTransient
    public List<VentasPagosLineas> getVentasPagosLineasList() {
        return ventasPagosLineasList;
    }

    public void setVentasPagosLineasList(List<VentasPagosLineas> ventasPagosLineasList) {
        this.ventasPagosLineasList = ventasPagosLineasList;
    }

    public VentasEstados getIdVentasEstados() {
        return idVentasEstados;
    }

    public void setIdVentasEstados(VentasEstados idVentasEstados) {
        this.idVentasEstados = idVentasEstados;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.Ventas[ idVenta=" + this.getId() + " ]";
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

}
