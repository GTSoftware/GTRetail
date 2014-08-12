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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "fiscal_libro_iva_ventas_lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FiscalLibroIvaVentasLineas.findAll", query = "SELECT f FROM FiscalLibroIvaVentasLineas f"),
    @NamedQuery(name = "FiscalLibroIvaVentasLineas.findByIdLineaLibro", query = "SELECT f FROM FiscalLibroIvaVentasLineas f WHERE f.idLineaLibro = :idLineaLibro"),
    @NamedQuery(name = "FiscalLibroIvaVentasLineas.findByNetoGravado", query = "SELECT f FROM FiscalLibroIvaVentasLineas f WHERE f.netoGravado = :netoGravado"),
    @NamedQuery(name = "FiscalLibroIvaVentasLineas.findByNoGravado", query = "SELECT f FROM FiscalLibroIvaVentasLineas f WHERE f.noGravado = :noGravado"),
    @NamedQuery(name = "FiscalLibroIvaVentasLineas.findByImporteIva", query = "SELECT f FROM FiscalLibroIvaVentasLineas f WHERE f.importeIva = :importeIva")})
public class FiscalLibroIvaVentasLineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linea_libro")
    private Integer idLineaLibro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "neto_gravado")
    private BigDecimal netoGravado;
    @Column(name = "no_gravado")
    private BigDecimal noGravado;
    @Column(name = "importe_iva")
    private BigDecimal importeIva;
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    @ManyToOne(optional = false)
    private FiscalLibroIvaVentas idFactura;
    @JoinColumn(name = "id_alicuota_iva", referencedColumnName = "id_alicuota_iva")
    @ManyToOne(optional = false)
    private FiscalAlicuotasIva idAlicuotaIva;

    public FiscalLibroIvaVentasLineas() {
    }

    public FiscalLibroIvaVentasLineas(Integer idLineaLibro) {
        this.idLineaLibro = idLineaLibro;
    }

    public Integer getIdLineaLibro() {
        return idLineaLibro;
    }

    public void setIdLineaLibro(Integer idLineaLibro) {
        this.idLineaLibro = idLineaLibro;
    }

    public BigDecimal getNetoGravado() {
        return netoGravado;
    }

    public void setNetoGravado(BigDecimal netoGravado) {
        this.netoGravado = netoGravado;
    }

    public BigDecimal getNoGravado() {
        return noGravado;
    }

    public void setNoGravado(BigDecimal noGravado) {
        this.noGravado = noGravado;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    public FiscalLibroIvaVentas getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(FiscalLibroIvaVentas idFactura) {
        this.idFactura = idFactura;
    }

    public FiscalAlicuotasIva getIdAlicuotaIva() {
        return idAlicuotaIva;
    }

    public void setIdAlicuotaIva(FiscalAlicuotasIva idAlicuotaIva) {
        this.idAlicuotaIva = idAlicuotaIva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLineaLibro != null ? idLineaLibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiscalLibroIvaVentasLineas)) {
            return false;
        }
        FiscalLibroIvaVentasLineas other = (FiscalLibroIvaVentasLineas) object;
        if ((this.idLineaLibro == null && other.idLineaLibro != null) || (this.idLineaLibro != null && !this.idLineaLibro.equals(other.idLineaLibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalLibroIvaVentasLineas[ idLineaLibro=" + idLineaLibro + " ]";
    }
    
}
