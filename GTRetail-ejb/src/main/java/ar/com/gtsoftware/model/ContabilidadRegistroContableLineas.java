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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findAll", query = "SELECT c FROM ContabilidadRegistroContableLineas c"),
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findByIdLineaRegistro", query = "SELECT c FROM ContabilidadRegistroContableLineas c WHERE c.idLineaRegistro = :idLineaRegistro"),
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findByDescripcionLinea", query = "SELECT c FROM ContabilidadRegistroContableLineas c WHERE c.descripcionLinea = :descripcionLinea"),
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findByCantidad", query = "SELECT c FROM ContabilidadRegistroContableLineas c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findByUnidadMedida", query = "SELECT c FROM ContabilidadRegistroContableLineas c WHERE c.unidadMedida = :unidadMedida"),
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findByFechaVencimiento", query = "SELECT c FROM ContabilidadRegistroContableLineas c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findByImporteDebe", query = "SELECT c FROM ContabilidadRegistroContableLineas c WHERE c.importeDebe = :importeDebe"),
    @NamedQuery(name = "ContabilidadRegistroContableLineas.findByImporteHaber", query = "SELECT c FROM ContabilidadRegistroContableLineas c WHERE c.importeHaber = :importeHaber")})
public class ContabilidadRegistroContableLineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linea_registro")
    private Integer idLineaRegistro;
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

    public ContabilidadRegistroContableLineas(Integer idLineaRegistro) {
        this.idLineaRegistro = idLineaRegistro;
    }

    public ContabilidadRegistroContableLineas(Integer idLineaRegistro, BigDecimal cantidad, Date fechaVencimiento, BigDecimal importeDebe, BigDecimal importeHaber) {
        this.idLineaRegistro = idLineaRegistro;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.importeDebe = importeDebe;
        this.importeHaber = importeHaber;
    }

    public Integer getIdLineaRegistro() {
        return idLineaRegistro;
    }

    public void setIdLineaRegistro(Integer idLineaRegistro) {
        this.idLineaRegistro = idLineaRegistro;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLineaRegistro != null ? idLineaRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContabilidadRegistroContableLineas)) {
            return false;
        }
        ContabilidadRegistroContableLineas other = (ContabilidadRegistroContableLineas) object;
        if ((this.idLineaRegistro == null && other.idLineaRegistro != null) || (this.idLineaRegistro != null && !this.idLineaRegistro.equals(other.idLineaRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.ContabilidadRegistroContableLineas[ idLineaRegistro=" + idLineaRegistro + " ]";
    }
    
}
