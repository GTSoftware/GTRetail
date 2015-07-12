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
 * Clase que mapea REGINFO_CV_VENTAS_ALICUOTAS para Régimen de Información de Compras y Ventas
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 2.0.1
 */
@Record(minLength = 62, maxLength = 62, name = "REGINFO_CV_VENTAS_ALICUOTAS")
public class RegInfoCvVentasAlicuotas implements Serializable {

    public static final String FILE_NAME = "REGINFO_CV_VENTAS_ALICUOTAS";

    private static final long serialVersionUID = 1L;

    @Field(at = 0,
            length = 3,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer tipoComprobante;

    @Field(at = 3,
            length = 5,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer puntoVenta;

    @Field(at = 8,
            length = 20,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Long numeroComprobante;

    @Field(at = 28,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeNetoGravado;

    @Field(at = 43,
            length = 4,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer alicuota;

    @Field(at = 47,
            length = 15,
            align = Align.RIGHT,
            required = true,
            defaultValue = "0",
            padding = '0')
    private BigDecimal impuestoLiquidado;

    /**
     *
     */
    public RegInfoCvVentasAlicuotas() {
    }

    /**
     * Campo 1: Tipo de Comprobante. Se completará con el valor del campo 'Tipo de Comprobante' del comprobante de
     * referencia del archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @return
     */
    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Campo 1: Tipo de Comprobante. Se completará con el valor del campo 'Tipo de Comprobante' del comprobante de
     * referencia del archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @param tipoComprobante
     */
    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    /**
     * Campo 2: Punto de Venta. Se completará con el valor del campo 'Punto de Venta' del comprobante de referencia del
     * archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @return
     */
    public Integer getPuntoVenta() {
        return puntoVenta;
    }

    /**
     * Campo 2: Punto de Venta. Se completará con el valor del campo 'Punto de Venta' del comprobante de referencia del
     * archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @param puntoVenta
     */
    public void setPuntoVenta(Integer puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    /**
     * Campo 3: Número de Comprobante. Se completará con el valor del campo 'Número de Comprobante' del comprobante de
     * referencia del archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @return
     */
    public Long getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Campo 3: Número de Comprobante. Se completará con el valor del campo 'Número de Comprobante' del comprobante de
     * referencia del archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @param numeroComprobante
     */
    public void setNumeroComprobante(Long numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     * Campo 4: Importe Neto Gravado. El mismo deberá ser menor o igual al importe total de la operación y mayor o igual
     * al impuesto liquidado si la operación que se informa no es una operación de canje. Podrá ser cero si la operación
     * no se encuentra gravada por IVA en su totalidad (campo Alícuota de IVA igual a 0).
     *
     * @return
     */
    public BigDecimal getImporteNetoGravado() {
        return importeNetoGravado;
    }

    /**
     * Campo 4: Importe Neto Gravado. El mismo deberá ser menor o igual al importe total de la operación y mayor o igual
     * al impuesto liquidado si la operación que se informa no es una operación de canje. Podrá ser cero si la operación
     * no se encuentra gravada por IVA en su totalidad (campo Alícuota de IVA igual a 0).
     *
     * @param importeNetoGravado
     */
    public void setImporteNetoGravado(BigDecimal importeNetoGravado) {
        this.importeNetoGravado = importeNetoGravado;
    }

    /**
     * Campo 5: Alícuota de IVA. Se deberá completar con la alícuota de IVA, conforme con la tabla correspondiente
     * publicada en el sitio web de este Organismo (http://www.afip.gob.ar/fe). Para completar las cuatro (4) posiciones
     * del campo se deben ingresar ceros a la izquierda del código correspondiente. En los casos en que se deba informar
     * más de una alícuota para el mismo comprobante, se procederá a grabar tantos registros como alícuotas se deban
     * declarar. La alícuota podrá ser cero en caso de operaciones de exportación, exentas y no gravadas, procediéndose
     * a completar el campo 'Código de operación' correspondiente en el archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @return
     */
    public Integer getAlicuota() {
        return alicuota;
    }

    /**
     * Campo 5: Alícuota de IVA. Se deberá completar con la alícuota de IVA, conforme con la tabla correspondiente
     * publicada en el sitio web de este Organismo (http://www.afip.gob.ar/fe). Para completar las cuatro (4) posiciones
     * del campo se deben ingresar ceros a la izquierda del código correspondiente. En los casos en que se deba informar
     * más de una alícuota para el mismo comprobante, se procederá a grabar tantos registros como alícuotas se deban
     * declarar. La alícuota podrá ser cero en caso de operaciones de exportación, exentas y no gravadas, procediéndose
     * a completar el campo 'Código de operación' correspondiente en el archivo REGINFO_CV_VENTAS_CBTE.
     *
     * @param alicuota
     */
    public void setAlicuota(Integer alicuota) {
        this.alicuota = alicuota;
    }

    /**
     * Campo 6: Impuesto Liquidado. Se consignará el importe del IVA discriminado en el comprobante, sin considerar la
     * percepción a no categorizados informada en el campo "Percepción a no categorizados". Aún tratándose de
     * compradores consumidores finales, sujetos exentos y monotributistas igualmente deberá consignarse el IVA
     * contenido en la operación.
     *
     * @return
     */
    public BigDecimal getImpuestoLiquidado() {
        return impuestoLiquidado;
    }

    /**
     * Campo 6: Impuesto Liquidado. Se consignará el importe del IVA discriminado en el comprobante, sin considerar la
     * percepción a no categorizados informada en el campo "Percepción a no categorizados". Aún tratándose de
     * compradores consumidores finales, sujetos exentos y monotributistas igualmente deberá consignarse el IVA
     * contenido en la operación.
     *
     * @param impuestoLiquidado
     */
    public void setImpuestoLiquidado(BigDecimal impuestoLiquidado) {
        this.impuestoLiquidado = impuestoLiquidado;
    }

}
