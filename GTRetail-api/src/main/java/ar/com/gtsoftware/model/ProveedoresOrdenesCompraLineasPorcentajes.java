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
 * Representa a los coeficientes de descuentos o recargos comerciales que aplican a una línea particular de una Orden de
 * Compra
 *
 * @author Rodrigo M. Tato Rothamel
 */
@Entity
@Table(name = "proveedores_ordenes_compra_lineas_porcentajes")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_orden_compra_linea_porcentaje", columnDefinition = "serial"))
public class ProveedoresOrdenesCompraLineasPorcentajes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @JoinColumn(name = "id_orden_compra_linea", referencedColumnName = "id_orden_compra_linea", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private ProveedoresOrdenesCompraLineas idLineaOrdenCompra;

    @NotNull
    @JoinColumn(name = "id_tipo_porcentaje", referencedColumnName = "id_tipo_porcentaje", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private ProductosTiposPorcentajes idTipoPorcentaje;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;

    public ProveedoresOrdenesCompraLineasPorcentajes() {
    }

    public ProveedoresOrdenesCompraLineas getIdLineaOrdenCompra() {
        return idLineaOrdenCompra;
    }

    public void setIdLineaOrdenCompra(ProveedoresOrdenesCompraLineas idLineaOrdenCompra) {
        this.idLineaOrdenCompra = idLineaOrdenCompra;
    }

    public ProductosTiposPorcentajes getIdTipoPorcentaje() {
        return idTipoPorcentaje;
    }

    public void setIdTipoPorcentaje(ProductosTiposPorcentajes idTipoPorcentaje) {
        this.idTipoPorcentaje = idTipoPorcentaje;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
