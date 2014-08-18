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
@Table(name = "negocio_condiciones_operaciones")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_condicion"))
public class NegocioCondicionesOperaciones extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_condicion")
    private String nombreCondicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta")
    private boolean venta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra")
    private boolean compra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pago_total")
    private boolean pagoTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondicionCompra")
    private List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCondicionVenta")
    private List<Ventas> ventasList;

    public NegocioCondicionesOperaciones() {
    }

    public NegocioCondicionesOperaciones(Integer idCondicion) {
        super(idCondicion);
    }

    public NegocioCondicionesOperaciones(Integer idCondicion, String nombreCondicion, boolean activo, boolean venta, boolean compra) {
        super(idCondicion);
        this.nombreCondicion = nombreCondicion;
        this.activo = activo;
        this.venta = venta;
        this.compra = compra;
    }

    public String getNombreCondicion() {
        return nombreCondicion;
    }

    public void setNombreCondicion(String nombreCondicion) {
        this.nombreCondicion = nombreCondicion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
    public List<ProveedoresOrdenesCompra> getProveedoresOrdenesCompraList() {
        return proveedoresOrdenesCompraList;
    }

    public void setProveedoresOrdenesCompraList(List<ProveedoresOrdenesCompra> proveedoresOrdenesCompraList) {
        this.proveedoresOrdenesCompraList = proveedoresOrdenesCompraList;
    }

    @XmlTransient
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    public boolean getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(boolean pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.NegocioCondicionesOperaciones[ idCondicion=" + this.getId() + " ]";
    }

}
