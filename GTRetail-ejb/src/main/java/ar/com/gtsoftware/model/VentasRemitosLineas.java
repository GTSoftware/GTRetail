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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "ventas_remitos_lineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasRemitosLineas.findAll", query = "SELECT v FROM VentasRemitosLineas v"),
    @NamedQuery(name = "VentasRemitosLineas.findByIdLineaRemito", query = "SELECT v FROM VentasRemitosLineas v WHERE v.idLineaRemito = :idLineaRemito"),
    @NamedQuery(name = "VentasRemitosLineas.findByCantidad", query = "SELECT v FROM VentasRemitosLineas v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentasRemitosLineas.findByCostoNetoUnitario", query = "SELECT v FROM VentasRemitosLineas v WHERE v.costoNetoUnitario = :costoNetoUnitario")})
public class VentasRemitosLineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linea_remito")
    private Integer idLineaRemito;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_neto_unitario")
    private BigDecimal costoNetoUnitario;
    @JoinColumn(name = "id_remito", referencedColumnName = "id_remito")
    @ManyToOne(optional = false)
    private VentasRemitos idRemito;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;

    public VentasRemitosLineas() {
    }

    public VentasRemitosLineas(Integer idLineaRemito) {
        this.idLineaRemito = idLineaRemito;
    }

    public VentasRemitosLineas(Integer idLineaRemito, BigDecimal cantidad, BigDecimal costoNetoUnitario) {
        this.idLineaRemito = idLineaRemito;
        this.cantidad = cantidad;
        this.costoNetoUnitario = costoNetoUnitario;
    }

    public Integer getIdLineaRemito() {
        return idLineaRemito;
    }

    public void setIdLineaRemito(Integer idLineaRemito) {
        this.idLineaRemito = idLineaRemito;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoNetoUnitario() {
        return costoNetoUnitario;
    }

    public void setCostoNetoUnitario(BigDecimal costoNetoUnitario) {
        this.costoNetoUnitario = costoNetoUnitario;
    }

    public VentasRemitos getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(VentasRemitos idRemito) {
        this.idRemito = idRemito;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLineaRemito != null ? idLineaRemito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasRemitosLineas)) {
            return false;
        }
        VentasRemitosLineas other = (VentasRemitosLineas) object;
        if ((this.idLineaRemito == null && other.idLineaRemito != null) || (this.idLineaRemito != null && !this.idLineaRemito.equals(other.idLineaRemito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasRemitosLineas[ idLineaRemito=" + idLineaRemito + " ]";
    }
    
}
