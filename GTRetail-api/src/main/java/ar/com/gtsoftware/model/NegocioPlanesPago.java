/*
 * Copyright 2016 GT Software.
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Los planes de pago para cada tipo de pago
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "negocio_planes_pago")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_plan"))
public class NegocioPlanesPago extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id_forma_pago")
    @ManyToOne
    @NotNull
    private NegocioFormasPago idFormaPago;

    @Column(name = "nombre")
    @Size(max = 60)
    @NotNull
    private String nombre;

    @Column(name = "fecha_activo_desde")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Date fechaActivoDesde;

    @Column(name = "fecha_activo_hasta")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Date fechaActivoHasta;

    @ManyToMany
    @JoinTable(name = "negocio_planes_pago_listas",
            joinColumns = @JoinColumn(name = "id_plan", referencedColumnName = "id_plan"),
            inverseJoinColumns = @JoinColumn(name = "id_lista_precio", referencedColumnName = "id_lista_precio"))
    private List<ProductosListasPrecios> listasPrecioHabilitadas;

    @OneToMany(mappedBy = "idPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NegocioPlanesPagoDetalle> negocioPlanesPagoDetalles;

    public NegocioPlanesPago() {
    }

    public NegocioPlanesPago(Long id) {
        super(id);
    }

    public NegocioFormasPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(NegocioFormasPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaActivoDesde() {
        return fechaActivoDesde;
    }

    public void setFechaActivoDesde(Date fechaActivoDesde) {
        this.fechaActivoDesde = fechaActivoDesde;
    }

    public Date getFechaActivoHasta() {
        return fechaActivoHasta;
    }

    public void setFechaActivoHasta(Date fechaActivoHasta) {
        this.fechaActivoHasta = fechaActivoHasta;
    }

    @XmlTransient
    public List<ProductosListasPrecios> getListasPrecioHabilitadas() {
        return listasPrecioHabilitadas;
    }

    public void setListasPrecioHabilitadas(List<ProductosListasPrecios> listasPrecioHabilitadas) {
        this.listasPrecioHabilitadas = listasPrecioHabilitadas;
    }

    @XmlTransient
    public List<NegocioPlanesPagoDetalle> getNegocioPlanesPagoDetalles() {
        return negocioPlanesPagoDetalles;
    }

    public void setNegocioPlanesPagoDetalles(List<NegocioPlanesPagoDetalle> negocioPlanesPagoDetalles) {
        this.negocioPlanesPagoDetalles = negocioPlanesPagoDetalles;
    }

}
