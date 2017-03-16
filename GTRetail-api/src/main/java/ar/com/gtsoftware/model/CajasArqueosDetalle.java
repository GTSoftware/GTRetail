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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Detalle del arqueo.
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "caja_arqueos_detalle")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_arqueo_detalle"))
public class CajasArqueosDetalle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_arqueo", referencedColumnName = "id_arqueo")
    private CajasArqueos idArqueo;

    @ManyToOne
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago")
    private NegocioFormasPago idFormaPago;

    @NotNull
    @Column(name = "monto_sistema")
    private BigDecimal montoSistema;
    @NotNull
    @Column(name = "monto_declarado")
    private BigDecimal montoDeclarado;

    @NotNull
    @Column(name = "diferencia")
    private BigDecimal diferencia;

    @Column(name = "descargo")
    @Size(max = 200)
    private String descargo;

    public CajasArqueosDetalle(Long id) {
        super(id);
    }

    public CajasArqueosDetalle() {
    }

    public BigDecimal getMontoSistema() {
        return montoSistema;
    }

    public void setMontoSistema(BigDecimal montoSistema) {
        this.montoSistema = montoSistema;
    }

    public BigDecimal getMontoDeclarado() {
        return montoDeclarado;
    }

    public void setMontoDeclarado(BigDecimal montoDeclarado) {
        this.montoDeclarado = montoDeclarado;
    }

    public CajasArqueos getIdArqueo() {
        return idArqueo;
    }

    public void setIdArqueo(CajasArqueos idArqueo) {
        this.idArqueo = idArqueo;
    }

    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }

    public String getDescargo() {
        return descargo;
    }

    public void setDescargo(String descargo) {
        this.descargo = descargo;
    }

}
