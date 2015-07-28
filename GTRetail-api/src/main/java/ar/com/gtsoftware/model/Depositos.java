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

import java.util.Date;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "depositos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_deposito", columnDefinition = "serial"))
public class Depositos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_deposito")
    private String nombreDeposito;
    @Size(max = 500)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepositoMovimiento")
    private List<StockMovimientos> stockMovimientosList;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionProvincias idProvincia;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionPaises idPais;
    @JoinColumn(name = "id_localidad", referencedColumnName = "id_localidad", columnDefinition = "int4")
    @ManyToOne(optional = false)
    private UbicacionLocalidades idLocalidad;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal", columnDefinition = "int4")
    @ManyToOne
    private Sucursales idSucursal;

    public Depositos() {
    }

    public Depositos(Long idDeposito) {
        super(idDeposito);
    }

    public Depositos(Long idDeposito, String nombreDeposito, Date fechaAlta, boolean activo) {
        super(idDeposito);
        this.nombreDeposito = nombreDeposito;
        this.fechaAlta = fechaAlta;
        this.activo = activo;
    }

    public String getNombreDeposito() {
        return nombreDeposito;
    }

    public void setNombreDeposito(String nombreDeposito) {
        this.nombreDeposito = nombreDeposito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<StockMovimientos> getStockMovimientosList() {
        return stockMovimientosList;
    }

    public void setStockMovimientosList(List<StockMovimientos> stockMovimientosList) {
        this.stockMovimientosList = stockMovimientosList;
    }

    public UbicacionProvincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(UbicacionProvincias idProvincia) {
        this.idProvincia = idProvincia;
    }

    public UbicacionPaises getIdPais() {
        return idPais;
    }

    public void setIdPais(UbicacionPaises idPais) {
        this.idPais = idPais;
    }

    public UbicacionLocalidades getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(UbicacionLocalidades idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

}
