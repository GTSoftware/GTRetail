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
import java.util.Date;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

/**
 * Clase que mapea REGINFO_CV_VENTAS_CBTE para Régimen de Información de Compras y Ventas
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 2.0.1
 */
@Record(minLength = 266, maxLength = 266, name = "REGINFO_CV_VENTAS_CBTE")
public class ReginfoCvVentasCbte implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FILE_NAME = "REGINFO_CV_VENTAS_CBTE";

    /**
     *
     */
    public static final String SI = "S";

    /**
     *
     */
    public static final String NO = "N";

    @Field(at = 0,
            length = 8,
            align = Align.RIGHT,
            required = true,
            format = "yyyyMMdd")
    private Date fechaComprobante;

    @Field(at = 8,
            length = 3,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer tipoComprobante;

    @Field(at = 11,
            length = 5,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer puntoVenta;

    @Field(at = 16,
            length = 20,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Long numeroComprobante;

    @Field(at = 36,
            length = 20,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Long numeroComprobanteHasta;

    @Field(at = 56,
            length = 2,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Integer CodigoDocumentoComprador;

    @Field(at = 58,
            length = 20,
            align = Align.RIGHT,
            required = true,
            padding = '0')
    private Long numeroIdentificacionComprador;

    @Field(at = 78,
            length = 30,
            align = Align.LEFT,
            required = true, padding = ' ')
    private String denominacionComprador;

    @Field(at = 108,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeTotalOperacion;

    @Field(at = 123,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeTotalConceptosNoIntegranPrecioNetoGravado;

    @Field(at = 138,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal percepcionANoCategorizados;

    @Field(at = 153,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeOperacionesExentas;

    @Field(at = 168,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeDePercepcionesOPagosAcuentaDeImpuestosNacionales;

    @Field(at = 183,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeDePercepcionesDeIngresosBrutos;

    @Field(at = 198,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeDePercepcionesDeImpuestosMunicipales;

    @Field(at = 213,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal importeImpuestosInternos;

    @Field(at = 228,
            length = 3,
            align = Align.RIGHT,
            required = true,
            defaultValue = "PES")
    private String codigoMoneda;

    @Field(at = 231,
            length = 10,
            align = Align.RIGHT,
            required = true, defaultValue = "1")
    //4 enteros y 6 decimales sin punto
    private BigDecimal tipoCambio;

    @Field(at = 241,
            length = 1,
            align = Align.RIGHT,
            required = true,
            defaultValue = "1")
    private Integer cantidadAlicuotasIVA;

    @Field(at = 242,
            length = 1,
            align = Align.RIGHT,
            required = true,
            defaultValue = " ")
    private String codigoOperacion;

    @Field(at = 243,
            length = 15,
            align = Align.RIGHT,
            required = true,
            padding = '0', defaultValue = "0")
    private BigDecimal otrosTributos;

    @Field(at = 258,
            length = 8,
            align = Align.RIGHT,
            format = "yyyyMMdd")
    private Date fechaVencimientoPago;

    /**
     * Crea una nueva instancia de RegInfoCvVentasCbte
     */
    public ReginfoCvVentasCbte() {
    }

    /**
     * Campo 1: Fecha de comprobante. Se deberá completar con la fecha de las facturas y/o comprobantes emitidos durante
     * el período fiscal que se registra y su formato será año, mes y día (AAAAMMDD).
     *
     * @return
     */
    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    /**
     * Campo 1: Fecha de comprobante. Se deberá completar con la fecha de las facturas y/o comprobantes emitidos durante
     * el período fiscal que se registra y su formato será año, mes y día (AAAAMMDD).
     *
     * @param fechaComprobante
     */
    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    /**
     * Campo 2: Tipo de Comprobante. Se deberá codificar con el tipo de comprobante que se emitió de acuerdo con la
     * tabla publicada en el sitio web de este Organismo (http://www.afip.gob.ar/fe).
     *
     * @return
     */
    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Campo 2: Tipo de Comprobante. Se deberá codificar con el tipo de comprobante que se emitió de acuerdo con la
     * tabla publicada en el sitio web de este Organismo (http://www.afip.gob.ar/fe).
     *
     * @param tipoComprobante
     */
    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    /**
     * Campo 3: Punto de Venta. Se deberá completar con el punto de venta en el que se emitió el comprobante, el cual
     * deberá constar de un número mayor o igual a 00001 y menor a 09998. Para aquellos contribuyentes que se encuentran
     * incluidos en el artículo 5° de la Resolución General N° 1.415, sus modificatorias y complementarias, tomarán -de
     * corresponder- los dígitos de este campo como parte de la numeración del comprobante, no observándose al efecto
     * las validaciones del párrafo anterior. Caso contrario, se completará con ceros. Para los comprobantes
     * correspondientes a los códigos '033', '099', '331' y '332' el presente campo deberá completarse con ceros.
     *
     * @return
     */
    public Integer getPuntoVenta() {
        return puntoVenta;
    }

    /**
     * Campo 3: Punto de Venta. Se deberá completar con el punto de venta en el que se emitió el comprobante, el cual
     * deberá constar de un número mayor o igual a 00001 y menor a 09998. Para aquellos contribuyentes que se encuentran
     * incluidos en el artículo 5° de la Resolución General N° 1.415, sus modificatorias y complementarias, tomarán -de
     * corresponder- los dígitos de este campo como parte de la numeración del comprobante, no observándose al efecto
     * las validaciones del párrafo anterior. Caso contrario, se completará con ceros. Para los comprobantes
     * correspondientes a los códigos '033', '099', '331' y '332' el presente campo deberá completarse con ceros.
     *
     * @param puntoVenta
     */
    public void setPuntoVenta(Integer puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    /**
     * Campo 4: Número de Comprobante. Se deberá completar con el número de comprobante a registrar (8 o más dígitos,
     * según corresponda), debiéndose llenar con ceros a la izquierda los dígitos excedentes (no utilizados). Si se
     * trata de un comprobante de varias hojas, se deberá informar el número de documento de la primera hoja, teniendo
     * en cuenta lo normado en el artículo 23, inciso a), punto 6., de la Resolución General N° 1.415, sus
     * modificatorias y complementarias. Para los comprobantes correspondientes a los códigos '033', '331' y '332' el
     * presente campo deberá completarse con el "Código de Operación Electrónica -COE-". En el supuesto de registrar de
     * manera agrupada por totales diarios, se deberá consignar el primer número de comprobante del rango a considerar.
     *
     * @return
     */
    public Long getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Campo 4: Número de Comprobante. Se deberá completar con el número de comprobante a registrar (8 o más dígitos,
     * según corresponda), debiéndose llenar con ceros a la izquierda los dígitos excedentes (no utilizados). Si se
     * trata de un comprobante de varias hojas, se deberá informar el número de documento de la primera hoja, teniendo
     * en cuenta lo normado en el artículo 23, inciso a), punto 6., de la Resolución General N° 1.415, sus
     * modificatorias y complementarias. Para los comprobantes correspondientes a los códigos '033', '331' y '332' el
     * presente campo deberá completarse con el "Código de Operación Electrónica -COE-". En el supuesto de registrar de
     * manera agrupada por totales diarios, se deberá consignar el primer número de comprobante del rango a considerar.
     *
     * @param numeroComprobante
     */
    public void setNumeroComprobante(Long numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     * Campo 5: Número de Comprobante Hasta. Se deberá indicar el último número de comprobante del rango a considerar en
     * el supuesto del último párrafo de la descripción del campo 4. En el resto de los casos se consignará el dato
     * registrado en el campo 4. Deberá ser mayor o igual que el campo 4.
     *
     * @return
     */
    public Long getNumeroComprobanteHasta() {
        return numeroComprobanteHasta;
    }

    /**
     * Campo 5: Número de Comprobante Hasta. Se deberá indicar el último número de comprobante del rango a considerar en
     * el supuesto del último párrafo de la descripción del campo 4. En el resto de los casos se consignará el dato
     * registrado en el campo 4. Deberá ser mayor o igual que el campo 4.
     *
     * @param numeroComprobanteHasta
     */
    public void setNumeroComprobanteHasta(Long numeroComprobanteHasta) {
        this.numeroComprobanteHasta = numeroComprobanteHasta;
    }

    /**
     * Campo 6: Código de documento del comprador. Se deberá completar con alguno de los códigos indicados en la tabla
     * publicada en el sitio web de este Organismo (http://www.afip.gob.ar/fe), de acuerdo con el tipo de documento que
     * exhiba el comprador. Será obligatorio consignar la Clave Única de Identificación Tributaria (C.U.I.T.) en todos
     * los casos, excepto en el supuesto de que el receptor del comprobante revista el carácter de consumidor final. En
     * este último caso, cuando el importe de la operación sea igual o superior a $ 1.000.- se deberá consignar el
     * código de documento (DNI, LE, LC, CI o pasaporte según corresponda). La condición de identificar al receptor del
     * comprobante por operaciones iguales o superiores a UN MIL PESOS ($ 1.000.-) no será obligatoria cuando se cumpla
     * la excepción prevista en el segundo párrafo del punto 2., inciso d), Acápite II, Apartado A, del Anexo II de la
     * Resolución General N° 1.415, sus modificatorias y complementarias. Tampoco será obligatorio consignar la Clave
     * Única de Identificación Tributaria (C.U.I.T.) cuando se trate de la registración por monto global diario
     * -artículo 45, inciso c), de la Resolución General N° 1.415, sus modificatorias y complementarias -en cuyo caso se
     * deberá ingresar el código '99'.
     *
     * @return
     */
    public Integer getCodigoDocumentoComprador() {
        return CodigoDocumentoComprador;
    }

    /**
     * Campo 6: Código de documento del comprador. Se deberá completar con alguno de los códigos indicados en la tabla
     * publicada en el sitio web de este Organismo (http://www.afip.gob.ar/fe), de acuerdo con el tipo de documento que
     * exhiba el comprador. Será obligatorio consignar la Clave Única de Identificación Tributaria (C.U.I.T.) en todos
     * los casos, excepto en el supuesto de que el receptor del comprobante revista el carácter de consumidor final. En
     * este último caso, cuando el importe de la operación sea igual o superior a $ 1.000.- se deberá consignar el
     * código de documento (DNI, LE, LC, CI o pasaporte según corresponda). La condición de identificar al receptor del
     * comprobante por operaciones iguales o superiores a UN MIL PESOS ($ 1.000.-) no será obligatoria cuando se cumpla
     * la excepción prevista en el segundo párrafo del punto 2., inciso d), Acápite II, Apartado A, del Anexo II de la
     * Resolución General N° 1.415, sus modificatorias y complementarias. Tampoco será obligatorio consignar la Clave
     * Única de Identificación Tributaria (C.U.I.T.) cuando se trate de la registración por monto global diario
     * -artículo 45, inciso c), de la Resolución General N° 1.415, sus modificatorias y complementarias -en cuyo caso se
     * deberá ingresar el código '99'.
     *
     * @param CodigoDocumentoComprador
     */
    public void setCodigoDocumentoComprador(Integer CodigoDocumentoComprador) {
        this.CodigoDocumentoComprador = CodigoDocumentoComprador;
    }

    /**
     * Campo 7: Número de Identificación del comprador. Se deberá consignar el número de documento de acuerdo con lo
     * indicado en el campo 6 'Código de documento del comprador'. Podrá ser cero en el supuesto de que el campo 6 sea
     * igual a '99'. En el caso particular de realizar operaciones con personas físicas o jurídicas que no posean
     * documento nacional, pasaporte o documentación societaria emitida en el país, se deberá consignar en el campo 6 el
     * código 80 y en éste, se codificará de acuerdo con la tabla CUIT PAIS publicada en el sitio web de este Organismo
     * (http://www.afip.gob.ar/fe).
     *
     * @return
     */
    public Long getNumeroIdentificacionComprador() {
        return numeroIdentificacionComprador;
    }

    /**
     * Campo 7: Número de Identificación del comprador. Se deberá consignar el número de documento de acuerdo con lo
     * indicado en el campo 6 'Código de documento del comprador'. Podrá ser cero en el supuesto de que el campo 6 sea
     * igual a '99'. En el caso particular de realizar operaciones con personas físicas o jurídicas que no posean
     * documento nacional, pasaporte o documentación societaria emitida en el país, se deberá consignar en el campo 6 el
     * código 80 y en éste, se codificará de acuerdo con la tabla CUIT PAIS publicada en el sitio web de este Organismo
     * (http://www.afip.gob.ar/fe).
     *
     * @param numeroIdentificacionComprador
     */
    public void setNumeroIdentificacionComprador(Long numeroIdentificacionComprador) {
        this.numeroIdentificacionComprador = numeroIdentificacionComprador;
    }

    /**
     * Campo 8: Apellido y Nombre del comprador. En caso de tratarse de una persona física se completará con el apellido
     * y nombres del comprador, y en los restantes con la denominación o razón social. Si de una misma razón social se
     * registran distintas sucursales, podría especificarse en este campo la sucursal que realizó la operación. Para el
     * supuesto que el comprador sea un consumidor final que no requiera ser identificado (campo 9 "Importe Total de la
     * Operación" menor a $1.000), se completará con la leyenda -CONSUMIDOR FINAL- en mayúsculas. La condición de
     * identificar al receptor del comprobante por operaciones iguales o superiores a UN MIL PESOS ($ 1.000.-) no será
     * obligatoria cuando se cumpla la excepción prevista en el segundo párrafo del punto 2., inciso d), Acápite II,
     * Apartado A, del Anexo II de la Resolución General N° 1.415, sus modificatorias y complementarias. En el caso de
     * tratarse de registraciones globales diarias se consignará la leyenda -VENTA GLOBAL DIARIA- (campo 6 igual a
     * '99').
     *
     * @return
     */
    public String getDenominacionComprador() {
        return denominacionComprador;
    }

    /**
     * Campo 8: Apellido y Nombre del comprador. En caso de tratarse de una persona física se completará con el apellido
     * y nombres del comprador, y en los restantes con la denominación o razón social. Si de una misma razón social se
     * registran distintas sucursales, podría especificarse en este campo la sucursal que realizó la operación. Para el
     * supuesto que el comprador sea un consumidor final que no requiera ser identificado (campo 9 "Importe Total de la
     * Operación" menor a $1.000), se completará con la leyenda -CONSUMIDOR FINAL- en mayúsculas. La condición de
     * identificar al receptor del comprobante por operaciones iguales o superiores a UN MIL PESOS ($ 1.000.-) no será
     * obligatoria cuando se cumpla la excepción prevista en el segundo párrafo del punto 2., inciso d), Acápite II,
     * Apartado A, del Anexo II de la Resolución General N° 1.415, sus modificatorias y complementarias. En el caso de
     * tratarse de registraciones globales diarias se consignará la leyenda -VENTA GLOBAL DIARIA- (campo 6 igual a
     * '99').
     *
     * @param denominacionComprador
     */
    public void setDenominacionComprador(String denominacionComprador) {
        this.denominacionComprador = denominacionComprador;
    }

    /**
     * Campo 9: Importe Total de la Operación. Se consignará el importe total de la operación. Dicho importe podrá ser
     * cero. Deberá ser igual a la suma de todos los importes que componen el comprobante informado.
     *
     * @return
     */
    public BigDecimal getImporteTotalOperacion() {
        return importeTotalOperacion;
    }

    /**
     * Campo 9: Importe Total de la Operación. Se consignará el importe total de la operación. Dicho importe podrá ser
     * cero. Deberá ser igual a la suma de todos los importes que componen el comprobante informado.
     *
     * @param importeTotalOperacion
     */
    public void setImporteTotalOperacion(BigDecimal importeTotalOperacion) {
        this.importeTotalOperacion = importeTotalOperacion;
    }

    /**
     * Campo 10: Importe total de conceptos que no integran el precio neto gravado. Se consignará el importe que surja
     * de sumar los montos que no integren la base imponible. Podrá ser cero.
     *
     * @return
     */
    public BigDecimal getImporteTotalConceptosNoIntegranPrecioNetoGravado() {
        return importeTotalConceptosNoIntegranPrecioNetoGravado;
    }

    /**
     * Campo 10: Importe total de conceptos que no integran el precio neto gravado. Se consignará el importe que surja
     * de sumar los montos que no integren la base imponible. Podrá ser cero.
     *
     * @param importeTotalConceptosNoIntegranPrecioNetoGravado
     */
    public void setImporteTotalConceptosNoIntegranPrecioNetoGravado(BigDecimal importeTotalConceptosNoIntegranPrecioNetoGravado) {
        this.importeTotalConceptosNoIntegranPrecioNetoGravado = importeTotalConceptosNoIntegranPrecioNetoGravado;
    }

    /**
     * Campo 11: Percepción a no categorizados. Se consignará el importe de la percepción a no categorizados. Podrá ser
     * cero.
     *
     * @return
     */
    public BigDecimal getPercepcionANoCategorizados() {
        return percepcionANoCategorizados;
    }

    /**
     * Campo 11: Percepción a no categorizados. Se consignará el importe de la percepción a no categorizados. Podrá ser
     * cero.
     *
     * @param percepcionANoCategorizados
     */
    public void setPercepcionANoCategorizados(BigDecimal percepcionANoCategorizados) {
        this.percepcionANoCategorizados = percepcionANoCategorizados;
    }

    /**
     * Campo 12: Importe de operaciones exentas. En caso de que en una misma operación se vendan productos exentos con
     * gravados, la alícuota será la correspondiente a los productos gravados. En este caso el monto correspondiente a
     * la parte exenta se consignará en este campo, y la porción gravada en el campo correspondiente del detalle de
     * alícuotas de IVA. Podrá ser cero.
     *
     * @return
     */
    public BigDecimal getImporteOperacionesExentas() {
        return importeOperacionesExentas;
    }

    /**
     * Campo 12: Importe de operaciones exentas. En caso de que en una misma operación se vendan productos exentos con
     * gravados, la alícuota será la correspondiente a los productos gravados. En este caso el monto correspondiente a
     * la parte exenta se consignará en este campo, y la porción gravada en el campo correspondiente del detalle de
     * alícuotas de IVA. Podrá ser cero.
     *
     * @param importeOperacionesExentas
     */
    public void setImporteOperacionesExentas(BigDecimal importeOperacionesExentas) {
        this.importeOperacionesExentas = importeOperacionesExentas;
    }

    /**
     * Campo 13: Importe de percepciones o pagos a cuenta de impuestos nacionales. Se consignarán otras percepciones o
     * pagos a cuenta de impuestos nacionales. Podrá ser cero.
     *
     * @return
     */
    public BigDecimal getImporteDePercepcionesOPagosAcuentaDeImpuestosNacionales() {
        return importeDePercepcionesOPagosAcuentaDeImpuestosNacionales;
    }

    /**
     * Campo 13: Importe de percepciones o pagos a cuenta de impuestos nacionales. Se consignarán otras percepciones o
     * pagos a cuenta de impuestos nacionales. Podrá ser cero.
     *
     * @param importeDePercepcionesOPagosAcuentaDeImpuestosNacionales
     */
    public void setImporteDePercepcionesOPagosAcuentaDeImpuestosNacionales(BigDecimal importeDePercepcionesOPagosAcuentaDeImpuestosNacionales) {
        this.importeDePercepcionesOPagosAcuentaDeImpuestosNacionales = importeDePercepcionesOPagosAcuentaDeImpuestosNacionales;
    }

    /**
     * Campo 14: Importe de percepciones de ingresos brutos. Se consignará el importe de percepciones de ingresos
     * brutos. Podrá ser cero.
     *
     * @return
     */
    public BigDecimal getImporteDePercepcionesDeIngresosBrutos() {
        return importeDePercepcionesDeIngresosBrutos;
    }

    /**
     * Campo 14: Importe de percepciones de ingresos brutos. Se consignará el importe de percepciones de ingresos
     * brutos. Podrá ser cero.
     *
     * @param importeDePercepcionesDeIngresosBrutos
     */
    public void setImporteDePercepcionesDeIngresosBrutos(BigDecimal importeDePercepcionesDeIngresosBrutos) {
        this.importeDePercepcionesDeIngresosBrutos = importeDePercepcionesDeIngresosBrutos;
    }

    /**
     * Campo 15: Importe de percepciones de impuestos municipales. Se consignará el importe de percepciones de ingresos
     * municipales. Podrá ser cero.
     *
     * @return
     */
    public BigDecimal getImporteDePercepcionesDeImpuestosMunicipales() {
        return importeDePercepcionesDeImpuestosMunicipales;
    }

    /**
     * Campo 15: Importe de percepciones de impuestos municipales. Se consignará el importe de percepciones de ingresos
     * municipales. Podrá ser cero.
     *
     * @param importeDePercepcionesDeImpuestosMunicipales
     */
    public void setImporteDePercepcionesDeImpuestosMunicipales(BigDecimal importeDePercepcionesDeImpuestosMunicipales) {
        this.importeDePercepcionesDeImpuestosMunicipales = importeDePercepcionesDeImpuestosMunicipales;
    }

    /**
     * Campo 16: Importe de impuestos internos. Se consignará el importe de impuestos internos. Podrá ser cero.
     *
     * @return
     */
    public BigDecimal getImporteImpuestosInternos() {
        return importeImpuestosInternos;
    }

    /**
     * Campo 16: Importe de impuestos internos. Se consignará el importe de impuestos internos. Podrá ser cero.
     *
     * @param importeImpuestosInternos
     */
    public void setImporteImpuestosInternos(BigDecimal importeImpuestosInternos) {
        this.importeImpuestosInternos = importeImpuestosInternos;
    }

    /**
     * Campo 17: Código de Moneda. Se codificará teniendo en cuenta la tabla correspondiente publicada en el sitio web
     * de este Organismo (http://www.afip.gob.ar/fe).
     *
     * @return
     */
    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    /**
     * Campo 17: Código de Moneda. Se codificará teniendo en cuenta la tabla correspondiente publicada en el sitio web
     * de este Organismo (http://www.afip.gob.ar/fe).
     *
     * @param codigoMoneda
     */
    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    /**
     * Campo 18: Tipo de Cambio. Se completará con el tipo de cambio vigente al momento de producirse la transacción,
     * respecto a la moneda en que se realizó la operación, expresado en la moneda de curso legal en el país. Ej.: si la
     * operación fue en dólares se consignará la cantidad de pesos necesaria para adquirir una (1) unidad de dólar. El
     * dato a ingresar consistirá en un número con cuatro (4) enteros y seis (6) decimales, y deberá completarse aún
     * cuando el campo Código de Moneda sea igual a 'PES'.
     *
     * @return
     */
    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    /**
     * Campo 18: Tipo de Cambio. Se completará con el tipo de cambio vigente al momento de producirse la transacción,
     * respecto a la moneda en que se realizó la operación, expresado en la moneda de curso legal en el país. Ej.: si la
     * operación fue en dólares se consignará la cantidad de pesos necesaria para adquirir una (1) unidad de dólar. El
     * dato a ingresar consistirá en un número con cuatro (4) enteros y seis (6) decimales, y deberá completarse aún
     * cuando el campo Código de Moneda sea igual a 'PES'.
     *
     * @param tipoCambio
     */
    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    /**
     * Campo 19: Cantidad de alícuotas de IVA. Si el comprobante que se informa posee varias alícuotas de IVA, se deberá
     * consignar la cantidad total de ellas. En caso contrario se consignará '1', como también si se trata de una
     * operación de venta de productos exentos con productos gravados a tasa única.
     *
     * @return
     */
    public Integer getCantidadAlicuotasIVA() {
        return cantidadAlicuotasIVA;
    }

    /**
     * Campo 19: Cantidad de alícuotas de IVA. Si el comprobante que se informa posee varias alícuotas de IVA, se deberá
     * consignar la cantidad total de ellas. En caso contrario se consignará '1', como también si se trata de una
     * operación de venta de productos exentos con productos gravados a tasa única.
     *
     * @param cantidadAlicuotasIVA
     */
    public void setCantidadAlicuotasIVA(Integer cantidadAlicuotasIVA) {
        this.cantidadAlicuotasIVA = cantidadAlicuotasIVA;
    }

    /**
     * Campo 20: Código de operación. Si la alícuota de IVA es igual a cero (0) o la operación responde a una operación
     * de Canje se deberá completar de acuerdo con la siguiente codificación: Z- Exportaciones a la zona franca. X-
     * Exportaciones al exterior. E- Operaciones exentas. N- No gravado. C- Operaciones de Canje. En caso contrario se
     * informará en blanco.
     *
     * @return
     */
    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    /**
     * Campo 20: Código de operación. Si la alícuota de IVA es igual a cero (0) o la operación responde a una operación
     * de Canje se deberá completar de acuerdo con la siguiente codificación: Z- Exportaciones a la zona franca. X-
     * Exportaciones al exterior. E- Operaciones exentas. N- No gravado. C- Operaciones de Canje. En caso contrario se
     * informará en blanco.
     *
     * @param codigoOperacion
     */
    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    /**
     * Campo 21: Otros Tributos. Se consignará el total de tributos no informados en los otros campos. Podrá ser cero.
     *
     * @return
     */
    public BigDecimal getOtrosTributos() {
        return otrosTributos;
    }

    /**
     * Campo 21: Otros Tributos. Se consignará el total de tributos no informados en los otros campos. Podrá ser cero.
     *
     * @param otrosTributos
     */
    public void setOtrosTributos(BigDecimal otrosTributos) {
        this.otrosTributos = otrosTributos;
    }

    /**
     * Campo 22: Fecha de Vencimiento de Pago. Sólo se informará cuando la prestación se corresponda con un servicio
     * público, siendo obligatorio para los comprobantes tipo '017 - Liquidación de Servicios Públicos Clase A' y '018 –
     * Liquidación de Servicios Públicos Clase B' y opcional para el resto de los comprobantes.
     *
     * @return
     */
    public Date getFechaVencimientoPago() {
        return fechaVencimientoPago;
    }

    /**
     * Campo 22: Fecha de Vencimiento de Pago. Sólo se informará cuando la prestación se corresponda con un servicio
     * público, siendo obligatorio para los comprobantes tipo '017 - Liquidación de Servicios Públicos Clase A' y '018 –
     * Liquidación de Servicios Públicos Clase B' y opcional para el resto de los comprobantes.
     *
     * @param fechaVencimientoPago
     */
    public void setFechaVencimientoPago(Date fechaVencimientoPago) {
        this.fechaVencimientoPago = fechaVencimientoPago;
    }

}
