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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "ventas_remitos_lineas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_linea_remito", columnDefinition = "serial"))
public class VentasRemitosLineas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_neto_unitario")
    private BigDecimal costoNetoUnitario;
    @JoinColumn(name = "id_remito", referencedColumnName = "id_remito", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private VentasRemitos idRemito;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public VentasRemitosLineas() {
    }

    public VentasRemitosLineas(Long idLineaRemito) {
        super(idLineaRemito);
    }

    public VentasRemitosLineas(Long idLineaRemito, BigDecimal cantidad, BigDecimal costoNetoUnitario) {
        super(idLineaRemito);
        this.cantidad = cantidad;
        this.costoNetoUnitario = costoNetoUnitario;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoNetoUnitario() {
        return costoNetoUnitario;
    }

    public void setCostoNetoUnitario(BigDecimal costoNetoUnitario) {
        this.costoNetoUnitario = costoNetoUnitario;
    }

    public VentasRemitos getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(VentasRemitos idRemito) {
        this.idRemito = idRemito;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

}
