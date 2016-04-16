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
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa a los tipos de comprobante soportados por el sistema
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "negocio_tipos_comprobante")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_negocio_tipo_comprobante"))
public class NegocioTiposComprobante extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_comprobante")
    private String nombreComprobante;

    @Max(1)
    @Min(-1)
    @NotNull
    @Column(name = "signo")
    private BigDecimal signo;

    @Column(name = "activo")
    private boolean activo;

    public NegocioTiposComprobante() {
    }

    public NegocioTiposComprobante(Long idFormaPago) {
        super(idFormaPago);
    }

    public NegocioTiposComprobante(Long idFormaPago, String nombreFormaPago, boolean venta, boolean compra) {
        super(idFormaPago);
        this.nombreComprobante = nombreFormaPago;

    }

    public String getNombreComprobante() {
        return nombreComprobante;
    }

    public void setNombreComprobante(String nombreComprobante) {
        this.nombreComprobante = nombreComprobante;
    }

    public BigDecimal getSigno() {
        return signo;
    }

    public void setSigno(BigDecimal signo) {
        this.signo = signo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
