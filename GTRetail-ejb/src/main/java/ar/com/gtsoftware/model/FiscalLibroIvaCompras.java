/*
 * Copyright 2018 GT Software.
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

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Clase que almacena la información de las facturas del libro de iva compras
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "fiscal_libro_iva_compras")
@Getter
@Setter
public class FiscalLibroIvaCompras extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fiscal_libro_iva_compras_id_registro")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "fiscal_libro_iva_compras_id_registro",
            sequenceName = "fiscal_libro_iva_compras_id_registro_seq")
    @Basic(optional = false)
    @Column(name = "id_registro", nullable = false, updatable = false)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "letra_factura")
    private String letraFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "punto_venta_factura")
    private String puntoVentaFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @Column(name = "anulada")
    private boolean anulada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "total_factura")
    private BigDecimal totalFactura;

    @ManyToOne(optional = false)
    @JoinColumn(name = "codigo_tipo_comprobante", referencedColumnName = "codigo_tipo_comprobante")
    private FiscalTiposComprobante codigoTipoComprobante;

    @OneToMany(mappedBy = "idRegistro")
    private List<ProveedoresComprobantes> comprobantesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegistro", orphanRemoval = true)
    @JoinColumn(name = "id_registro", referencedColumnName = "id_registro")
    private List<FiscalLibroIvaComprasLineas> fiscalLibroIvaComprasLineasList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;
    @JoinColumn(name = "id_responsabilidad_iva", referencedColumnName = "id_resoponsabildiad_iva")
    @ManyToOne(optional = false)
    private FiscalResponsabilidadesIva idResponsabilidadIva;
    @JoinColumn(name = "id_periodo_fiscal", referencedColumnName = "id_periodo_fiscal")
    @ManyToOne(optional = false)
    private FiscalPeriodosFiscales idPeriodoFiscal;
    @JoinColumn(name = "id_registro_contable", referencedColumnName = "id_registro")
    @ManyToOne(optional = true)
    private ContabilidadRegistroContable idRegistroContable;

    @Column(name = "cae")
    private Long cae;
    @Column(name = "fecha_vencimiento_cae")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoCae;

    @Column(name = "importe_neto_no_gravado")
    @NotNull
    private BigDecimal importeNetoNoGravado;
    @Column(name = "importe_exento")
    @NotNull
    private BigDecimal importeExento;
    @Column(name = "importe_neto_gravado")
    @NotNull
    private BigDecimal importeNetoGravado;
    @Column(name = "importe_tributos")
    @NotNull
    private BigDecimal importeTributos;
    @Column(name = "importe_iva")
    @NotNull
    private BigDecimal importeIva;
    @Column(name = "importe_percepcion_iva")
    @NotNull
    private BigDecimal importePercepcionIva;
    @Column(name = "importe_percepcion_ingresos_brutos")
    @NotNull
    private BigDecimal importePercepcionIngresosBrutos;

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalLibroIvaComprasDto[ idFactura=" + id + " ]";
    }

}
