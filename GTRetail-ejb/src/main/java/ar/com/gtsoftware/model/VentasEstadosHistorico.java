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
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "ventas_pagos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_estado_historico"))
public class VentasEstadosHistorico extends BaseEntity implements Serializable {

    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false)
    private Ventas idVenta;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;

    @JoinColumn(name = "id_estado_anterior", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private VentasEstados idEstadoAnterior;

    @JoinColumn(name = "id_estado_actual", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private VentasEstados idEstadoActual;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;

    public VentasEstadosHistorico() {
    }

    public VentasEstadosHistorico(Integer id) {
        super(id);
    }

    public VentasEstadosHistorico(Ventas idVenta, Date fechaCambio, 
            VentasEstados idEstadoAnterior, VentasEstados idEstadoActual, 
            Usuarios idUsuario, Integer id) {
        
        super(id);
        this.idVenta = idVenta;
        this.fechaCambio = fechaCambio;
        this.idEstadoAnterior = idEstadoAnterior;
        this.idEstadoActual = idEstadoActual;
        this.idUsuario = idUsuario;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public VentasEstados getIdEstadoAnterior() {
        return idEstadoAnterior;
    }

    public void setIdEstadoAnterior(VentasEstados idEstadoAnterior) {
        this.idEstadoAnterior = idEstadoAnterior;
    }

    public VentasEstados getIdEstadoActual() {
        return idEstadoActual;
    }

    public void setIdEstadoActual(VentasEstados idEstadoActual) {
        this.idEstadoActual = idEstadoActual;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "VentasEstadosHistorico{" + "id=" + this.getId() + '}';
    }

}
