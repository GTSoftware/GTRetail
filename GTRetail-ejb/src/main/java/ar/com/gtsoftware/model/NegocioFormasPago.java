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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "negocio_formas_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NegocioFormasPago.findAll", query = "SELECT n FROM NegocioFormasPago n"),
    @NamedQuery(name = "NegocioFormasPago.findByIdFormaPago", query = "SELECT n FROM NegocioFormasPago n WHERE n.idFormaPago = :idFormaPago"),
    @NamedQuery(name = "NegocioFormasPago.findByNombreFormaPago", query = "SELECT n FROM NegocioFormasPago n WHERE n.nombreFormaPago = :nombreFormaPago"),
    @NamedQuery(name = "NegocioFormasPago.findByNombreCorto", query = "SELECT n FROM NegocioFormasPago n WHERE n.nombreCorto = :nombreCorto"),
    @NamedQuery(name = "NegocioFormasPago.findByVenta", query = "SELECT n FROM NegocioFormasPago n WHERE n.venta = :venta"),
    @NamedQuery(name = "NegocioFormasPago.findByCompra", query = "SELECT n FROM NegocioFormasPago n WHERE n.compra = :compra")})
public class NegocioFormasPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_forma_pago")
    private Integer idFormaPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_forma_pago")
    private String nombreFormaPago;
    @Size(max = 10)
    @Column(name = "nombre_corto")
    private String nombreCorto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta")
    private boolean venta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra")
    private boolean compra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormaPago")
    private List<VentasPagos> ventasPagosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormaPago")
    private List<CajasMovimientos> cajasMovimientosList;

    public NegocioFormasPago() {
    }

    public NegocioFormasPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public NegocioFormasPago(Integer idFormaPago, String nombreFormaPago, boolean venta, boolean compra) {
        this.idFormaPago = idFormaPago;
        this.nombreFormaPago = nombreFormaPago;
        this.venta = venta;
        this.compra = compra;
    }

    public Integer getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(Integer idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombreFormaPago() {
        return nombreFormaPago;
    }

    public void setNombreFormaPago(String nombreFormaPago) {
        this.nombreFormaPago = nombreFormaPago;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public boolean getVenta() {
        return venta;
    }

    public void setVenta(boolean venta) {
        this.venta = venta;
    }

    public boolean getCompra() {
        return compra;
    }

    public void setCompra(boolean compra) {
        this.compra = compra;
    }

    @XmlTransient
    public List<VentasPagos> getVentasPagosList() {
        return ventasPagosList;
    }

    public void setVentasPagosList(List<VentasPagos> ventasPagosList) {
        this.ventasPagosList = ventasPagosList;
    }

    @XmlTransient
    public List<CajasMovimientos> getCajasMovimientosList() {
        return cajasMovimientosList;
    }

    public void setCajasMovimientosList(List<CajasMovimientos> cajasMovimientosList) {
        this.cajasMovimientosList = cajasMovimientosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPago != null ? idFormaPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NegocioFormasPago)) {
            return false;
        }
        NegocioFormasPago other = (NegocioFormasPago) object;
        if ((this.idFormaPago == null && other.idFormaPago != null) || (this.idFormaPago != null && !this.idFormaPago.equals(other.idFormaPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.NegocioFormasPago[ idFormaPago=" + idFormaPago + " ]";
    }
    
}
