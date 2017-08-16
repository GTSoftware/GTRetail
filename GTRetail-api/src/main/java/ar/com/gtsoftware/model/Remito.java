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

import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
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

/**
 *
 * @author fede
 */
@Entity
@Table(name = "remitos")
@AttributeOverride(name = "id", column = @Column(name = "id_remito", columnDefinition = "serial"))
public class Remito extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    @NotNull
    private Usuarios idUsuario;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "origen_is_interno")
    @NotNull
    private Boolean isOrigenInterno;

    @JoinColumn(name = "id_origen_externo", referencedColumnName = "id_persona")
    @ManyToOne
    private Personas idOrigenExterno;

    @JoinColumn(name = "id_origen_interno", referencedColumnName = "id_deposito")
    @ManyToOne
    private Depositos idOrigenInterno;

    @Column(name = "destino_is_interno")
    @NotNull
    private Boolean isDestinoInterno;

    @JoinColumn(name = "destino_previsto_externo", referencedColumnName = "id_persona")
    @ManyToOne
    private Personas idDestinoPrevistoExterno;

    @JoinColumn(name = "destino_previsto_interno", referencedColumnName = "id_deposito")
    @ManyToOne
    private Depositos idDestinoPrevistoInterno;

    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;

    @JoinColumn(name = "id_tipo_movimiento", referencedColumnName = "id_tipo_movimiento")
    @ManyToOne
    private RemitoTipoMovimiento remitoTipoMovimiento;

    @OneToMany(mappedBy = "remitoCabecera", cascade = CascadeType.ALL)
    private List<RemitoDetalle> detalleList;

    @OneToMany(mappedBy = "remito", cascade = CascadeType.ALL)
    private List<RemitoRecepcion> remitoRecepcionesList;

    //-------Getter and Setter ----------------------------------------------
    public Personas getIdOrigenExterno() {
        return idOrigenExterno;
    }

    public void setIdOrigenExterno(Personas idOrigenExterno) {
        this.idOrigenExterno = idOrigenExterno;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getIsOrigenInterno() {
        return isOrigenInterno;
    }

    public void setIsOrigenInterno(Boolean isOrigenInterno) {
        this.isOrigenInterno = isOrigenInterno;
    }

    public Depositos getIdOrigenInterno() {
        return idOrigenInterno;
    }

    public void setIdOrigenInterno(Depositos idOrigenInterno) {
        this.idOrigenInterno = idOrigenInterno;
    }

    public Boolean getIsDestinoInterno() {
        return isDestinoInterno;
    }

    public void setIsDestinoInterno(Boolean isDestinoInterno) {
        this.isDestinoInterno = isDestinoInterno;
    }

    public Personas getIdDestinoPrevistoExterno() {
        return idDestinoPrevistoExterno;
    }

    public void setIdDestinoPrevistoExterno(Personas idDestinoPrevistoExterno) {
        this.idDestinoPrevistoExterno = idDestinoPrevistoExterno;
    }

    public Depositos getIdDestinoPrevistoInterno() {
        return idDestinoPrevistoInterno;
    }

    public void setIdDestinoPrevistoInterno(Depositos idDestinoPrevistoInterno) {
        this.idDestinoPrevistoInterno = idDestinoPrevistoInterno;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public RemitoTipoMovimiento getRemitoTipoMovimiento() {
        return remitoTipoMovimiento;
    }

    public void setRemitoTipoMovimiento(RemitoTipoMovimiento remitoTipoMovimiento) {
        this.remitoTipoMovimiento = remitoTipoMovimiento;
    }

    public List<RemitoDetalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<RemitoDetalle> detalleList) {
        this.detalleList = detalleList;
    }

    public List<RemitoRecepcion> getRemitoRecepcionesList() {
        return remitoRecepcionesList;
    }

    public void setRemitoRecepcionesList(List<RemitoRecepcion> remitoRecepcionesList) {
        this.remitoRecepcionesList = remitoRecepcionesList;
    }

    /**
     * Devuelve la representación en Stirng del origen del remito.
     *
     * @return un String que representa el origen
     */
    public String getNombreOrigen() {
        if (this.isOrigenInterno) {
            return String.format("INTERNO: %s", this.getIdOrigenInterno().getBusinessString());
        }
        return String.format("EXTERNO: %s", this.getIdOrigenExterno().getBusinessString());
    }

    /**
     * Devuelve la representación en Stirng del destino del remito.
     *
     * @return un String que representa el destino
     */
    public String getNombreDestino() {
        if (this.isDestinoInterno) {
            return String.format("INTERNO: %s", this.getIdDestinoPrevistoInterno().getBusinessString());
        }
        return String.format("EXTERNO: %s", this.getIdDestinoPrevistoExterno().getBusinessString());
    }

}
