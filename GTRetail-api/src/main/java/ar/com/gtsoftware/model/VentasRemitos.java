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
@Table(name = "ventas_remitos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_remito", columnDefinition = "serial"))
public class VentasRemitos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_remito")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRemito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulado")
    private boolean anulado;
    @Size(max = 255)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRemito")
    private List<VentasRemitosLineas> ventasRemitosLineasList;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Ventas idVenta;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;

    public VentasRemitos() {
    }

    public VentasRemitos(Long idRemito) {
        super(idRemito);
    }

    public VentasRemitos(Long idRemito, Date fechaRemito, boolean anulado) {
        super(idRemito);
        this.fechaRemito = fechaRemito;
        this.anulado = anulado;
    }

    public Date getFechaRemito() {
        return fechaRemito;
    }

    public void setFechaRemito(Date fechaRemito) {
        this.fechaRemito = fechaRemito;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<VentasRemitosLineas> getVentasRemitosLineasList() {
        return ventasRemitosLineasList;
    }

    public void setVentasRemitosLineasList(List<VentasRemitosLineas> ventasRemitosLineasList) {
        this.ventasRemitosLineasList = ventasRemitosLineasList;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
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

}
