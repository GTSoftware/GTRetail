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
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "contabilidad_registro_contable_lineas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_linea_registro"))
public class ContabilidadRegistroContableLineas extends BaseEntity {

    @Size(max = 1024)
    @Column(name = "descripcion_linea")
    private String descripcionLinea;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Size(max = 20)
    @Column(name = "unidad_medida")
    private String unidadMedida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_debe")
    private BigDecimal importeDebe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_haber")
    private BigDecimal importeHaber;
    @JoinColumn(name = "id_registro_contable", referencedColumnName = "id_registro")
    @ManyToOne(optional = false)
    private ContabilidadRegistroContable idRegistroContable;
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta")
    @ManyToOne(optional = false)
    private ContabilidadPlanCuentas idCuenta;

    public ContabilidadRegistroContableLineas() {
    }

    public ContabilidadRegistroContableLineas(Long idLineaRegistro) {
        super(idLineaRegistro);
    }

    public ContabilidadRegistroContableLineas(Long idLineaRegistro, BigDecimal cantidad, Date fechaVencimiento, BigDecimal importeDebe, BigDecimal importeHaber) {
        super(idLineaRegistro);
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.importeDebe = importeDebe;
        this.importeHaber = importeHaber;
    }

    public String getDescripcionLinea() {
        return descripcionLinea;
    }

    public void setDescripcionLinea(String descripcionLinea) {
        this.descripcionLinea = descripcionLinea;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getImporteDebe() {
        return importeDebe;
    }

    public void setImporteDebe(BigDecimal importeDebe) {
        this.importeDebe = importeDebe;
    }

    public BigDecimal getImporteHaber() {
        return importeHaber;
    }

    public void setImporteHaber(BigDecimal importeHaber) {
        this.importeHaber = importeHaber;
    }

    public ContabilidadRegistroContable getIdRegistroContable() {
        return idRegistroContable;
    }

    public void setIdRegistroContable(ContabilidadRegistroContable idRegistroContable) {
        this.idRegistroContable = idRegistroContable;
    }

    public ContabilidadPlanCuentas getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(ContabilidadPlanCuentas idCuenta) {
        this.idCuenta = idCuenta;
    }

}
