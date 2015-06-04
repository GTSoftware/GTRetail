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

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "negocio_formas_pago")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_forma_pago"))
public class NegocioFormasPago extends BaseEntity {

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

    public NegocioFormasPago(Long idFormaPago) {
        super(idFormaPago);
    }

    public NegocioFormasPago(Long idFormaPago, String nombreFormaPago, boolean venta, boolean compra) {
        super(idFormaPago);
        this.nombreFormaPago = nombreFormaPago;
        this.venta = venta;
        this.compra = compra;
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

}
