/*
 * Copyright 2015 GT Software.
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
package ar.com.gtsoftware.bl.fiscal;

import java.io.Serializable;
import java.math.BigDecimal;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

/**
 * Clase que mapea REGINFO_CV_CABECERA para Régimen de Información de Compras y Ventas
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 2.0.1
 */
@Record(minLength = 112, maxLength = 112, name = "REGINFO_CV_CABECERA")
public class ReginfoCvCabecera implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FILE_NAME = "REGINFO_CV_CABECERA";
    /**
     *
     */
    public static final String SI = "S";

    /**
     *
     */
    public static final String NO = "N";

    /**
     *
     */
    public static final String GLOBAL = "1";

    /**
     *
     */
    public static final String POR_COMPROBANTE = "2";

    @Field(at = 0,
            length = 11,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Long cuitInformante;

    @Field(at = 11,
            length = 4,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer anioPeriodo;

    @Field(at = 15,
            length = 2,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer mesPeriodo;

    @Field(at = 17,
            length = 2,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private Integer secuencia;

    @Field(at = 19,
            length = 1,
            align = Align.RIGHT,
            required = true,
            defaultValue = NO)
    private String sinMovimiento;

    @Field(at = 20,
            length = 1,
            align = Align.RIGHT,
            required = true,
            defaultValue = NO)
    private String prorratearCreditoFiscalComputable;

    @Field(at = 21,
            length = 1,
            align = Align.RIGHT,
            required = true,
            defaultValue = POR_COMPROBANTE,
            regex = "(1|2)")
    private String creditoFiscalComputableGlobalOPorComprobante;

    @Field(at = 22,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeCreditoFiscalComputable;

    @Field(at = 37,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeCreditoFiscalComputableConAsignacionDirecta;

    @Field(at = 52,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeCreditoFiscalComputableDeterminadoPorProrrateo;

    @Field(at = 67,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeCreditoFiscalNoComputableGlobal;

    @Field(at = 82,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeCreditoFiscalContribSegSocyOtros;

    @Field(at = 97,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeCreditoFiscalComputableContribSegSocyOtros;

    /**
     * Crea una nueva instancia de ReginfoCvCabecera
     */
    public ReginfoCvCabecera() {
    }

    /**
     * Campo 1: CUIT Informante. Se consignará el CUIT del titular de la presentación.
     *
     * @return
     */
    public Long getCuitInformante() {
        return cuitInformante;
    }

    /**
     * Campo 1: CUIT Informante. Se consignará el CUIT del titular de la presentación.
     *
     * @param cuitInformante
     */
    public void setCuitInformante(Long cuitInformante) {
        this.cuitInformante = cuitInformante;
    }

    /**
     * Campo 2: Período. Se consignará el período (AAAAMM) al que corresponde la presentación.
     *
     * @return
     */
    public Integer getAnioPeriodo() {
        return anioPeriodo;
    }

    /**
     * Campo 2: Período. Se consignará el período (AAAAMM) al que corresponde la presentación.
     *
     * @param anioPeriodo
     */
    public void setAnioPeriodo(Integer anioPeriodo) {
        this.anioPeriodo = anioPeriodo;
    }

    /**
     * Campo 2: Período. Se consignará el período (AAAAMM) al que corresponde la presentación.
     *
     * @return
     */
    public Integer getMesPeriodo() {
        return mesPeriodo;
    }

    /**
     * Campo 2: Período. Se consignará el período (AAAAMM) al que corresponde la presentación.
     *
     * @param mesPeriodo
     */
    public void setMesPeriodo(Integer mesPeriodo) {
        this.mesPeriodo = mesPeriodo;
    }

    /**
     * Campo 3: Secuencia. Se deberá indicar si la presentación es Original (00) o Rectificativa y su orden (01,02,….).
     *
     * @return
     */
    public Integer getSecuencia() {
        return secuencia;
    }

    /**
     * Campo 3: Secuencia. Se deberá indicar si la presentación es Original (00) o Rectificativa y su orden (01,02,….).
     *
     * @param secuencia
     */
    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * Campo 4: Sin Movimiento. En caso de no presentar movimientos durante el período que se informa, se consignará en
     * este campo una 'S' (SI), caso contrario se completará con una 'N' (NO).
     *
     * @return
     */
    public String getSinMovimiento() {
        return sinMovimiento;
    }

    /**
     * Campo 4: Sin Movimiento. En caso de no presentar movimientos durante el período que se informa, se consignará en
     * este campo una 'S' (SI), caso contrario se completará con una 'N' (NO).
     *
     * @param sinMovimiento
     */
    public void setSinMovimiento(String sinMovimiento) {
        this.sinMovimiento = sinMovimiento;
    }

    /**
     * Campo 5: Prorratear Crédito Fiscal Computable. Si el Crédito Fiscal Computable es prorrateado para el período que
     * se declara, se consignará en este campo una 'S' (SI), caso contrario se completará con una 'N' (NO).
     *
     * @return
     */
    public String getProrratearCreditoFiscalComputable() {
        return prorratearCreditoFiscalComputable;
    }

    /**
     * Campo 5: Prorratear Crédito Fiscal Computable. Si el Crédito Fiscal Computable es prorrateado para el período que
     * se declara, se consignará en este campo una 'S' (SI), caso contrario se completará con una 'N' (NO).
     *
     * @param prorratearCreditoFiscalComputable
     */
    public void setProrratearCreditoFiscalComputable(String prorratearCreditoFiscalComputable) {
        this.prorratearCreditoFiscalComputable = prorratearCreditoFiscalComputable;
    }

    /**
     * Campo 6: Crédito Fiscal Computable Global ó Por Comprobante. Si en el campo 5 se informa 'S' (SI), se consignará
     * en este campo un 1 (GLOBAL) si el Crédito Fiscal Computable se informa al cierre de la DDJJ. Alternativamente se
     * permitirá informar el Crédito Fiscal Computable en cada comprobante informado, en cuyo caso este campo se
     * completará con 2 (POR COMPROBANTE). Este campo se dejará en blanco si en el campo 5 se consigna 'N' (NO).
     *
     * @return
     */
    public String getCreditoFiscalComputableGlobalOPorComprobante() {
        return creditoFiscalComputableGlobalOPorComprobante;
    }

    /**
     * Campo 6: Crédito Fiscal Computable Global ó Por Comprobante. Si en el campo 5 se informa 'S' (SI), se consignará
     * en este campo un 1 (GLOBAL) si el Crédito Fiscal Computable se informa al cierre de la DDJJ. Alternativamente se
     * permitirá informar el Crédito Fiscal Computable en cada comprobante informado, en cuyo caso este campo se
     * completará con 2 (POR COMPROBANTE). Este campo se dejará en blanco si en el campo 5 se consigna 'N' (NO).
     *
     * @param creditoFiscalComputableGlobalOPorComprobante
     */
    public void setCreditoFiscalComputableGlobalOPorComprobante(String creditoFiscalComputableGlobalOPorComprobante) {
        this.creditoFiscalComputableGlobalOPorComprobante = creditoFiscalComputableGlobalOPorComprobante;
    }

    /**
     * Campo 7: Importe Crédito Fiscal Computable Global. Si en el campo 6 se selecciona la opción GLOBAL, se completará
     * con el valor del importe del Crédito Fiscal Computable Total para este período. En caso contrario se completará
     * con ceros.
     *
     * @return
     */
    public BigDecimal getImporteCreditoFiscalComputable() {
        return importeCreditoFiscalComputable;
    }

    /**
     * Campo 7: Importe Crédito Fiscal Computable Global. Si en el campo 6 se selecciona la opción GLOBAL, se completará
     * con el valor del importe del Crédito Fiscal Computable Total para este período. En caso contrario se completará
     * con ceros.
     *
     * @param importeCreditoFiscalComputable
     */
    public void setImporteCreditoFiscalComputable(BigDecimal importeCreditoFiscalComputable) {
        this.importeCreditoFiscalComputable = importeCreditoFiscalComputable;
    }

    /**
     * Campo 8: Importe Crédito Fiscal Computable, con asignación directa. Si en el campo 6 se selecciona la opción
     * GLOBAL, se completará con el valor del importe del Crédito Fiscal Computable con asignación directa para este
     * período. En caso contrario se completará con ceros.
     *
     * @return
     */
    public BigDecimal getImporteCreditoFiscalComputableConAsignacionDirecta() {
        return importeCreditoFiscalComputableConAsignacionDirecta;
    }

    /**
     * Campo 8: Importe Crédito Fiscal Computable, con asignación directa. Si en el campo 6 se selecciona la opción
     * GLOBAL, se completará con el valor del importe del Crédito Fiscal Computable con asignación directa para este
     * período. En caso contrario se completará con ceros.
     *
     * @param importeCreditoFiscalComputableConAsignacionDirecta
     */
    public void setImporteCreditoFiscalComputableConAsignacionDirecta(BigDecimal importeCreditoFiscalComputableConAsignacionDirecta) {
        this.importeCreditoFiscalComputableConAsignacionDirecta = importeCreditoFiscalComputableConAsignacionDirecta;
    }

    /**
     * Campo 9: Importe Crédito Fiscal Computable, determinado por prorrateo. Si en el campo 6 se selecciona la opción
     * GLOBAL, se completará con el valor del importe del Crédito Fiscal Computable, determinado por prorrateo. En caso
     * contrario se completará con ceros.
     *
     * @return
     */
    public BigDecimal getImporteCreditoFiscalComputableDeterminadoPorProrrateo() {
        return importeCreditoFiscalComputableDeterminadoPorProrrateo;
    }

    /**
     * Campo 9: Importe Crédito Fiscal Computable, determinado por prorrateo. Si en el campo 6 se selecciona la opción
     * GLOBAL, se completará con el valor del importe del Crédito Fiscal Computable, determinado por prorrateo. En caso
     * contrario se completará con ceros.
     *
     * @param importeCreditoFiscalComputableDeterminadoPorProrrateo
     */
    public void setImporteCreditoFiscalComputableDeterminadoPorProrrateo(BigDecimal importeCreditoFiscalComputableDeterminadoPorProrrateo) {
        this.importeCreditoFiscalComputableDeterminadoPorProrrateo = importeCreditoFiscalComputableDeterminadoPorProrrateo;
    }

    /**
     * Campo 10: Importe Crédito Fiscal no Computable Global. Si en el campo 6 se selecciona la opción GLOBAL, se
     * completará con el valor del importe del Crédito Fiscal no Computable Global. En caso contrario se completará con
     * ceros.
     *
     * @return
     */
    public BigDecimal getImporteCreditoFiscalNoComputableGlobal() {
        return importeCreditoFiscalNoComputableGlobal;
    }

    /**
     * Campo 10: Importe Crédito Fiscal no Computable Global. Si en el campo 6 se selecciona la opción GLOBAL, se
     * completará con el valor del importe del Crédito Fiscal no Computable Global. En caso contrario se completará con
     * ceros.
     *
     * @param importeCreditoFiscalNoComputableGlobal
     */
    public void setImporteCreditoFiscalNoComputableGlobal(BigDecimal importeCreditoFiscalNoComputableGlobal) {
        this.importeCreditoFiscalNoComputableGlobal = importeCreditoFiscalNoComputableGlobal;
    }

    /**
     * Campo 11: Crédito Fiscal Contrib. Seg. Soc. y Otros Conceptos. Se completará con el valor del importe del Crédito
     * Fiscal generado por Contribuciones a la Seguridad Social y otros conceptos no contemplados en la información de
     * detalle.
     *
     * @return
     */
    public BigDecimal getImporteCreditoFiscalContribSegSocyOtros() {
        return importeCreditoFiscalContribSegSocyOtros;
    }

    /**
     * Campo 11: Crédito Fiscal Contrib. Seg. Soc. y Otros Conceptos. Se completará con el valor del importe del Crédito
     * Fiscal generado por Contribuciones a la Seguridad Social y otros conceptos no contemplados en la información de
     * detalle.
     *
     * @param importeCreditoFiscalContribSegSocyOtros
     */
    public void setImporteCreditoFiscalContribSegSocyOtros(BigDecimal importeCreditoFiscalContribSegSocyOtros) {
        this.importeCreditoFiscalContribSegSocyOtros = importeCreditoFiscalContribSegSocyOtros;
    }

    /**
     * Campo 12: Crédito Fiscal Computable Contrib. Seg. Soc. y Otros Conceptos. Se completará con el valor del importe
     * del Crédito Fiscal Computable generado por Contribuciones a la Seguridad Social y otros conceptos no contemplados
     * en la información de detalle. Si en el campo 6 se selecciona la opción GLOBAL, este campo se completará con cero.
     *
     * @return
     */
    public BigDecimal getImporteCreditoFiscalComputableContribSegSocyOtros() {
        return importeCreditoFiscalComputableContribSegSocyOtros;
    }

    /**
     * Campo 12: Crédito Fiscal Computable Contrib. Seg. Soc. y Otros Conceptos. Se completará con el valor del importe
     * del Crédito Fiscal Computable generado por Contribuciones a la Seguridad Social y otros conceptos no contemplados
     * en la información de detalle. Si en el campo 6 se selecciona la opción GLOBAL, este campo se completará con cero.
     *
     * @param importeCreditoFiscalComputableContribSegSocyOtros
     */
    public void setImporteCreditoFiscalComputableContribSegSocyOtros(BigDecimal importeCreditoFiscalComputableContribSegSocyOtros) {
        this.importeCreditoFiscalComputableContribSegSocyOtros = importeCreditoFiscalComputableContribSegSocyOtros;
    }

}
