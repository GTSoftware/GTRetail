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

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa las compras que se realizan
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "proveedores_comprobantes")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_comprobante"))
public class ProveedoresComprobantes extends BaseEntity {

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

    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
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

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idProveedor;


    @JoinColumn(name = "id_registro_iva", referencedColumnName = "id_registro")
    @ManyToOne
    private FiscalLibroIvaCompras idRegistro;

    @Transient
    private BigDecimal totalConSigno;


    public ProveedoresComprobantes() {
    }

    public ProveedoresComprobantes(Long idCompra) {
        super(idCompra);
    }

    public ProveedoresComprobantes(Long idVenta, Date fechaVenta, BigDecimal total, boolean anulada) {
        super(idVenta);
        this.fechaComprobante = fechaVenta;
        this.total = total;
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

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Personas getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Personas idProveedor) {
        this.idProveedor = idProveedor;
    }

    @XmlTransient
    public FiscalLibroIvaCompras getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(FiscalLibroIvaCompras idRegistro) {
        this.idRegistro = idRegistro;
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

    public BigDecimal getTotalConSigno() {
        if (total != null && tipoComprobante != null) {
            if (totalConSigno == null) {
                totalConSigno = total.multiply(tipoComprobante.getSigno());
            }
        }
        return totalConSigno;
    }

    public String getBusinessString() {
        if (idRegistro != null) {
            return String.format("[%d] %s %s %s-%s", getId(), tipoComprobante.getNombreComprobante(), letra,
                    idRegistro.getPuntoVentaFactura(), idRegistro.getNumeroFactura());
        }
        return String.format("[%d] %s", getId(), tipoComprobante.getNombreComprobante());
    }
}
