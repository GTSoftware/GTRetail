/*
 * Copyright 2016 GT Software.
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "cajas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_caja"))
public class Cajas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuarios idUsuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    private Sucursales idSucursal;

    @NotNull
    @Column(name = "fecha_apertura")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaApertura;

    @NotNull
    @Column(name = "fecha_cierre")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCierre;

    @NotNull
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;

    @OneToMany(mappedBy = "idCaja")
    private List<CajasMovimientos> cajasMovimientoss;

    @OneToMany(mappedBy = "idCaja")
    private List<Recibos> recibosList;

    public Cajas(Long id) {
        super(id);
    }

    public Cajas() {
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

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    @XmlTransient
    public List<CajasMovimientos> getCajasMovimientoss() {
        return cajasMovimientoss;
    }

    public void setCajasMovimientoss(List<CajasMovimientos> cajasMovimientoss) {
        this.cajasMovimientoss = cajasMovimientoss;
    }

    @XmlTransient
    public List<Recibos> getRecibosList() {
        return recibosList;
    }

    public void setRecibosList(List<Recibos> recibosList) {
        this.recibosList = recibosList;
    }

}
