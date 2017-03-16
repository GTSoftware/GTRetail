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
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Arqueos de caja realizadados por los cajeros. Delimitan un cierre y apertura de caja.
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "caja_arqueos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_arqueo"))
public class CajasArqueos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario_arqueo", referencedColumnName = "id_usuario")
    private Usuarios idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario_control", referencedColumnName = "id_usuario")
    private Usuarios idUsuarioControl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    private Sucursales idSucursal;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_caja", referencedColumnName = "id_caja")
    private Cajas idCaja;

    @NotNull
    @Column(name = "fecha_arqueo")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaArqueo;

    @Column(name = "fecha_control")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaControl;

    @NotNull
    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;
    @NotNull
    @Column(name = "saldo_final")
    private BigDecimal saldoFinal;

    @Column(name = "observaciones_control")
    @Size(max = 500)
    private String obvervacionesControl;

    public CajasArqueos(Long id) {
        super(id);
    }

    public CajasArqueos() {
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

    public Date getFechaArqueo() {
        return fechaArqueo;
    }

    public void setFechaArqueo(Date fechaArqueo) {
        this.fechaArqueo = fechaArqueo;
    }

    public Date getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(Date fechaControl) {
        this.fechaControl = fechaControl;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Cajas getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Cajas idCaja) {
        this.idCaja = idCaja;
    }

    public Usuarios getIdUsuarioControl() {
        return idUsuarioControl;
    }

    public void setIdUsuarioControl(Usuarios idUsuarioControl) {
        this.idUsuarioControl = idUsuarioControl;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public String getObvervacionesControl() {
        return obvervacionesControl;
    }

    public void setObvervacionesControl(String obvervacionesControl) {
        this.obvervacionesControl = obvervacionesControl;
    }

}
