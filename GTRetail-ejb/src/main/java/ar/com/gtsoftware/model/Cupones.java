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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CuponesDto de tarjetas de crédito o débito
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "cupones")
public class Cupones extends Valores {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "nro_cupon")
    private Integer nroCupon;

    @Column(name = "codigo_autorizacion")
    private Integer codigoAutorizacion;

    @Column(name = "nro_lote")
    private Integer nroLote;

    @NotNull
    @Column(name = "fecha_origen")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaOrigen;

    @Column(name = "fecha_presentacion")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaPresentacion;

    @Column(name = "fecha_acreditacion")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaAcreditacion;

    @NotNull
    @Column(name = "cant_cuotas")
    private Integer cantCuotas;

    @Column(name = "notas")
    @Size(max = 255)
    private String notas;

    @Column(name = "coeficiente")
    private BigDecimal coeficiente;

    public Cupones(Long id) {
        super(id);
    }

    public Cupones() {
    }

    public Integer getNroCupon() {
        return nroCupon;
    }

    public void setNroCupon(Integer nroCupon) {
        this.nroCupon = nroCupon;
    }

    public Integer getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(Integer codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }

    public Integer getNroLote() {
        return nroLote;
    }

    public void setNroLote(Integer nroLote) {
        this.nroLote = nroLote;
    }

    public Date getFechaOrigen() {
        return fechaOrigen;
    }

    public void setFechaOrigen(Date fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }

    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }

    public Date getFechaAcreditacion() {
        return fechaAcreditacion;
    }

    public void setFechaAcreditacion(Date fechaAcreditacion) {
        this.fechaAcreditacion = fechaAcreditacion;
    }

    public Integer getCantCuotas() {
        return cantCuotas;
    }

    public void setCantCuotas(Integer cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public BigDecimal getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(BigDecimal coeficiente) {
        this.coeficiente = coeficiente;
    }

}
