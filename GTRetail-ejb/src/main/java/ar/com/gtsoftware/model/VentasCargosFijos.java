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
 * @author rodrigo
 */
@Entity
@Table(name = "ventas_cargos_fijos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_cargo_fijo", columnDefinition = "serial"))
public class VentasCargosFijos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private boolean porcentaje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_cargo")
    private BigDecimal importeCargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_clasificacion_cliente")
    private int idClasificacionCliente;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public VentasCargosFijos() {
    }

    public VentasCargosFijos(Long idCargoFijo) {
        super(idCargoFijo);
    }

    public VentasCargosFijos(Long idCargoFijo, Date fechaAlta, boolean activo, boolean porcentaje, BigDecimal importeCargo, int idClasificacionCliente) {
        super(idCargoFijo);
        this.fechaAlta = fechaAlta;
        this.activo = activo;
        this.porcentaje = porcentaje;
        this.importeCargo = importeCargo;
        this.idClasificacionCliente = idClasificacionCliente;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getImporteCargo() {
        return importeCargo;
    }

    public void setImporteCargo(BigDecimal importeCargo) {
        this.importeCargo = importeCargo;
    }

    public int getIdClasificacionCliente() {
        return idClasificacionCliente;
    }

    public void setIdClasificacionCliente(int idClasificacionCliente) {
        this.idClasificacionCliente = idClasificacionCliente;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

}
