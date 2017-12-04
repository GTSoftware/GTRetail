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
@Table(name = "proveedores_ordenes_compra_lineas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_orden_compra_linea", columnDefinition = "serial"))
public class ProveedoresOrdenesCompraLineas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_compra_unitario")
    private BigDecimal precioCompraUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_pedida")
    private BigDecimal cantidadPedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @NotNull
    @Column(name = "cantidad_recibida")
    private BigDecimal cantidadRecibida;
    @NotNull
    @JoinColumn(name = "id_orden_compra", referencedColumnName = "id_orden_compra", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private ProveedoresOrdenesCompra idOrdenCompra;

    @NotNull
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @NotNull
    @JoinColumn(name = "id_tipo_unidad", referencedColumnName = "id_tipo_unidad", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private ProductosTiposUnidades idTipoUnidad;

    public ProveedoresOrdenesCompraLineas() {
    }

    public ProveedoresOrdenesCompraLineas(Long idLinea) {
        super(idLinea);
    }

    public ProveedoresOrdenesCompraLineas(Long idLinea, BigDecimal precioCompraUnitario, BigDecimal cantidad, BigDecimal subTotal) {
        super(idLinea);
        this.precioCompraUnitario = precioCompraUnitario;
        this.cantidadPedida = cantidad;
        this.subTotal = subTotal;
    }

    public BigDecimal getPrecioCompraUnitario() {
        return precioCompraUnitario;
    }

    public void setPrecioCompraUnitario(BigDecimal precioCompraUnitario) {
        this.precioCompraUnitario = precioCompraUnitario;
    }

    public BigDecimal getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(BigDecimal cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(BigDecimal cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public ProveedoresOrdenesCompra getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(ProveedoresOrdenesCompra idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public ProductosTiposUnidades getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(ProductosTiposUnidades idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

}
