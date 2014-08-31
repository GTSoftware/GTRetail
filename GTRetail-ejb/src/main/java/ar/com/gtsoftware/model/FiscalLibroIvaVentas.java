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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "fiscal_libro_iva_ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FiscalLibroIvaVentas.findAll", query = "SELECT f FROM FiscalLibroIvaVentas f"),
    @NamedQuery(name = "FiscalLibroIvaVentas.findByIdFactura", query = "SELECT f FROM FiscalLibroIvaVentas f WHERE f.idFactura = :idFactura"),
    @NamedQuery(name = "FiscalLibroIvaVentas.findByFechaFactura", query = "SELECT f FROM FiscalLibroIvaVentas f WHERE f.fechaFactura = :fechaFactura"),
    @NamedQuery(name = "FiscalLibroIvaVentas.findByDocumento", query = "SELECT f FROM FiscalLibroIvaVentas f WHERE f.documento = :documento"),
    @NamedQuery(name = "FiscalLibroIvaVentas.findByLetraFactura", query = "SELECT f FROM FiscalLibroIvaVentas f WHERE f.letraFactura = :letraFactura"),
    @NamedQuery(name = "FiscalLibroIvaVentas.findByPuntoVentaFactura", query = "SELECT f FROM FiscalLibroIvaVentas f WHERE f.puntoVentaFactura = :puntoVentaFactura"),
    @NamedQuery(name = "FiscalLibroIvaVentas.findByNumeroFactura", query = "SELECT f FROM FiscalLibroIvaVentas f WHERE f.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "FiscalLibroIvaVentas.findByTotalFactura", query = "SELECT f FROM FiscalLibroIvaVentas f WHERE f.totalFactura = :totalFactura")})
public class FiscalLibroIvaVentas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_factura")
    private Integer idFactura;
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
    @Size(min = 1, max = 4)
    @Column(name = "punto_venta_factura")
    private String puntoVentaFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "numero_factura")
    private String numeroFactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_factura")
    private BigDecimal totalFactura;
    @OneToMany(mappedBy = "idRegistroIva")
    private List<Ventas> ventasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFactura")
    private List<FiscalLibroIvaVentasLineas> fiscalLibroIvaVentasLineasList;
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

    public FiscalLibroIvaVentas() {
    }

    public FiscalLibroIvaVentas(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public FiscalLibroIvaVentas(Integer idFactura, Date fechaFactura, String documento, String letraFactura, String puntoVentaFactura, String numeroFactura) {
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
        this.documento = documento;
        this.letraFactura = letraFactura;
        this.puntoVentaFactura = puntoVentaFactura;
        this.numeroFactura = numeroFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLetraFactura() {
        return letraFactura;
    }

    public void setLetraFactura(String letraFactura) {
        this.letraFactura = letraFactura;
    }

    public String getPuntoVentaFactura() {
        return puntoVentaFactura;
    }

    public void setPuntoVentaFactura(String puntoVentaFactura) {
        this.puntoVentaFactura = puntoVentaFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(BigDecimal totalFactura) {
        this.totalFactura = totalFactura;
    }

    @XmlTransient
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @XmlTransient
    public List<FiscalLibroIvaVentasLineas> getFiscalLibroIvaVentasLineasList() {
        return fiscalLibroIvaVentasLineasList;
    }

    public void setFiscalLibroIvaVentasLineasList(List<FiscalLibroIvaVentasLineas> fiscalLibroIvaVentasLineasList) {
        this.fiscalLibroIvaVentasLineasList = fiscalLibroIvaVentasLineasList;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public FiscalResponsabilidadesIva getIdResponsabilidadIva() {
        return idResponsabilidadIva;
    }

    public void setIdResponsabilidadIva(FiscalResponsabilidadesIva idResponsabilidadIva) {
        this.idResponsabilidadIva = idResponsabilidadIva;
    }

    public FiscalPeriodosFiscales getIdPeriodoFiscal() {
        return idPeriodoFiscal;
    }

    public void setIdPeriodoFiscal(FiscalPeriodosFiscales idPeriodoFiscal) {
        this.idPeriodoFiscal = idPeriodoFiscal;
    }

    public ContabilidadRegistroContable getIdRegistroContable() {
        return idRegistroContable;
    }

    public void setIdRegistroContable(ContabilidadRegistroContable idRegistroContable) {
        this.idRegistroContable = idRegistroContable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiscalLibroIvaVentas)) {
            return false;
        }
        FiscalLibroIvaVentas other = (FiscalLibroIvaVentas) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalLibroIvaVentas[ idFactura=" + idFactura + " ]";
    }
    
}
