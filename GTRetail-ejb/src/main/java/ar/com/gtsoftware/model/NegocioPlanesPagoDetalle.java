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
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Detalle del plan de pago
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "negocio_planes_pago_detalle")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_detalle_plan"))
public class NegocioPlanesPagoDetalle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_plan", referencedColumnName = "id_plan")
    private NegocioPlanesPago idPlan;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "cuotas")
    private int cuotas;

    @Column(name = "coeficiente_interes")
    @DecimalMax(value = "999999999999999.9999")
    @DecimalMin(value = "0")
    private BigDecimal coeficienteInteres;

    public NegocioPlanesPagoDetalle(Long id) {
        super(id);
    }

    public NegocioPlanesPagoDetalle() {
    }

    public NegocioPlanesPago getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(NegocioPlanesPago idPlan) {
        this.idPlan = idPlan;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public BigDecimal getCoeficienteInteres() {
        return coeficienteInteres;
    }

    public void setCoeficienteInteres(BigDecimal coeficienteInteres) {
        this.coeficienteInteres = coeficienteInteres;
    }

}
