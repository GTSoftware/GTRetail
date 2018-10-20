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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Cheques de terceros recibidos
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "cheques_terceros")
public class ChequesTerceros extends Valores {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "nro_cheque")
    @Size(max = 30, min = 1)
    private String nroCheque;

    @NotNull
    @Column(name = "cuit_originante")
    @Size(max = 11, min = 1)
    private String cuitOriginante;

    @NotNull
    @Column(name = "razon_social_originante")
    @Size(max = 200, min = 1)
    private String razonSocialOriginante;

    @NotNull
    @Column(name = "fecha_origen")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaOrigen;

    @Column(name = "fecha_vencimiento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVencimiento;

    @Column(name = "fecha_cobro")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaCobro;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    private Bancos idBanco;

    @Column(name = "notas")
    @Size(max = 255)
    private String notas;

    /**
     * COnstructor con Id
     *
     * @param id
     */
    public ChequesTerceros(Long id) {
        super(id);
    }

    /**
     * Constructor por defecto
     */
    public ChequesTerceros() {
    }

    /**
     * El número impreso en el cheque
     *
     * @return
     */
    public String getNroCheque() {
        return nroCheque;
    }

    /**
     * El número impreso en el cheque
     *
     * @param nroCheque
     */
    public void setNroCheque(String nroCheque) {
        this.nroCheque = nroCheque;
    }

    /**
     * El cuit del titular del cheque
     *
     * @return
     */
    public String getCuitOriginante() {
        return cuitOriginante;
    }

    /**
     * El cuit del titular del cheque
     *
     * @param cuitOriginante
     */
    public void setCuitOriginante(String cuitOriginante) {
        this.cuitOriginante = cuitOriginante;
    }

    /**
     * La razon social del titular que figura en el cheque
     *
     * @return
     */
    public String getRazonSocialOriginante() {
        return razonSocialOriginante;
    }

    /**
     * La razon social del titular que figura en el cheque
     *
     * @param razonSocialOriginante
     */
    public void setRazonSocialOriginante(String razonSocialOriginante) {
        this.razonSocialOriginante = razonSocialOriginante;
    }

    /**
     * La fecha dew confección del cheque
     *
     * @return
     */
    public Date getFechaOrigen() {
        return fechaOrigen;
    }

    /**
     * La fecha dew confección del cheque
     *
     * @param fechaOrigen
     */
    public void setFechaOrigen(Date fechaOrigen) {
        this.fechaOrigen = fechaOrigen;
    }

    /**
     * La fecha de vencimiento para el cobro del mismo. Si es de pago diferido tendrá una fecha en el futuro.
     *
     * @return
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * La fecha de vencimiento para el cobro del mismo. Si es de pago diferido tendrá una fecha en el futuro.
     *
     * @param fechaVencimiento
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * La fecha en que se efectivizó el cobro del cheque.
     *
     * @return
     */
    public Date getFechaCobro() {
        return fechaCobro;
    }

    /**
     * La fecha en que se efectivizó el cobro del cheque.
     *
     * @param fechaCobro
     */
    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    /**
     * El banco al que pertenece el cheque
     *
     * @return
     */
    public Bancos getIdBanco() {
        return idBanco;
    }

    /**
     * El banco al que pertenece el cheque
     *
     * @param idBanco
     */
    public void setIdBanco(Bancos idBanco) {
        this.idBanco = idBanco;
    }

    /**
     * Notas aclaratorias sobre el cheque en cuestión
     *
     * @return
     */
    public String getNotas() {
        return notas;
    }

    /**
     * Notas aclaratorias sobre el cheque en cuestión
     *
     * @param notas
     */
    public void setNotas(String notas) {
        this.notas = notas;
    }

}
