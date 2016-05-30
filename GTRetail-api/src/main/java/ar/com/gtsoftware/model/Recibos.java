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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "recibos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_recibo"))
public class Recibos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "fecha_recibo")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaRecibo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Personas idPersona;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuarios idUsuario;

    @NotNull
    @Column(name = "monto_total")
    private BigDecimal montoTotal;

    @Column(name = "observaciones")
    @Size(max = 255)
    private String observaciones;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_caja", referencedColumnName = "id_caja")
    private Cajas idCaja;

    @OneToMany(mappedBy = "idRecibo")
    private List<RecibosDetalle> recibosDetalles;

    public Recibos(Long id) {
        super(id);
    }

    public Recibos() {
    }

    public Date getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(Date fechaRecibo) {
        this.fechaRecibo = fechaRecibo;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Cajas getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Cajas idCaja) {
        this.idCaja = idCaja;
    }

    @XmlTransient
    public List<RecibosDetalle> getRecibosDetalles() {
        return recibosDetalles;
    }

    public void setRecibosDetalles(List<RecibosDetalle> recibosDetalles) {
        this.recibosDetalles = recibosDetalles;
    }

}
