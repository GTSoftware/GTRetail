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

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa las formas de pago del negocio
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "negocio_formas_pago")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_forma_pago", columnDefinition = "serial"))
public class NegocioFormasPago extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_forma_pago")
    private String nombreFormaPago;
    @Size(max = 10)
    @Column(name = "nombre_corto")
    private String nombreCorto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta")
    private boolean venta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra")
    private boolean compra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requiere_plan")
    private boolean requierePlan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "requiere_valores")
    private boolean requiereValores;

    /**
     * Constructor por defecto
     */
    public NegocioFormasPago() {
    }

    /**
     *
     * @param idFormaPago
     */
    public NegocioFormasPago(Long idFormaPago) {
        super(idFormaPago);
    }

    /**
     *
     * @param idFormaPago
     * @param nombreFormaPago
     * @param venta
     * @param compra
     */
    public NegocioFormasPago(Long idFormaPago, String nombreFormaPago, boolean venta, boolean compra) {
        super(idFormaPago);
        this.nombreFormaPago = nombreFormaPago;
        this.venta = venta;
        this.compra = compra;
    }

    /**
     * Nombre de la forma de pago
     *
     * @return
     */
    public String getNombreFormaPago() {
        return nombreFormaPago;
    }

    /**
     * Nombre de la forma de pago
     *
     * @param nombreFormaPago
     */
    public void setNombreFormaPago(String nombreFormaPago) {
        this.nombreFormaPago = nombreFormaPago;
    }

    /**
     * Nombre corto
     *
     * @return
     */
    public String getNombreCorto() {
        return nombreCorto;
    }

    /**
     * Nombre corto
     *
     * @param nombreCorto
     */
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    /**
     * Si se puede usar para la venta
     *
     * @return
     */
    public boolean getVenta() {
        return venta;
    }

    /**
     * Si se puede usar para la venta
     *
     * @param venta
     */
    public void setVenta(boolean venta) {
        this.venta = venta;
    }

    /**
     * Si se puede usar para la compra
     *
     * @return
     */
    public boolean getCompra() {
        return compra;
    }

    /**
     * Si se puede usar para la compra
     *
     * @param compra
     */
    public void setCompra(boolean compra) {
        this.compra = compra;
    }

    /**
     * Si requiere que tenga al menos un plan asociado para poder ser utilizado
     *
     * @return
     */
    public boolean getRequierePlan() {
        return requierePlan;
    }

    /**
     * Si requiere que tenga al menos un plan asociado para poder ser utilizado
     *
     * @param requierePlan
     */
    public void setRequierePlan(boolean requierePlan) {
        this.requierePlan = requierePlan;
    }

    /**
     * Si la forma de pago requiere el ingreso de valores que la representen
     *
     * @return
     */
    public boolean getRequiereValores() {
        return requiereValores;
    }

    /**
     * Si la forma de pago requiere el ingreso de valores que la representen
     *
     * @param requiereValores
     */
    public void setRequiereValores(boolean requiereValores) {
        this.requiereValores = requiereValores;
    }

}
