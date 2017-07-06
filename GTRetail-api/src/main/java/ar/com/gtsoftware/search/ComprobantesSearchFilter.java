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
package ar.com.gtsoftware.search;

import ar.com.gtsoftware.model.NegocioCondicionesOperaciones;
import ar.com.gtsoftware.model.NegocioTiposComprobante;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.Sucursales;
import ar.com.gtsoftware.model.Usuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ComprobantesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Long idVenta;
    private Date fechaVentaDesde;
    private Date fechaVentaHasta;
    private Boolean anulada;
    private Usuarios idUsuario;
    private Personas idPersona;
    private Sucursales idSucursal;
    private Boolean facturada;
    private NegocioCondicionesOperaciones condicionVenta;
    private Boolean conSaldo;
    private String numeroFactura;
    private List<NegocioTiposComprobante> tiposComprobante;

    @Override
    public boolean hasFilter() {
        return (idVenta != null) || (fechaVentaDesde != null) || (fechaVentaHasta != null)
                || (anulada != null) || (idUsuario != null)
                || (idSucursal != null) || (idPersona != null)
                || (facturada != null) || (condicionVenta != null)
                || (conSaldo != null)
                || (numeroFactura != null)
                || hasTiposComprobanteFilter();
    }

    public ComprobantesSearchFilter() {
    }

    public ComprobantesSearchFilter(Date fechaDesde, Date fechaHasta, Boolean anulada) {
        this.fechaVentaDesde = fechaDesde;
        this.fechaVentaHasta = fechaHasta;
        this.anulada = anulada;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(Boolean anulada) {
        this.anulada = anulada;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Date getFechaVentaDesde() {
        return fechaVentaDesde;
    }

    public void setFechaVentaDesde(Date fechaVentaDesde) {
        this.fechaVentaDesde = fechaVentaDesde;
    }

    public Date getFechaVentaHasta() {
        return fechaVentaHasta;
    }

    public void setFechaVentaHasta(Date fechaVentaHasta) {
        this.fechaVentaHasta = fechaVentaHasta;
    }

    public Boolean getFacturada() {
        return facturada;
    }

    public void setFacturada(Boolean facturada) {
        this.facturada = facturada;
    }

    public NegocioCondicionesOperaciones getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(NegocioCondicionesOperaciones condicionVenta) {
        this.condicionVenta = condicionVenta;
    }

    public Boolean getConSaldo() {
        return conSaldo;
    }

    public void setConSaldo(Boolean conSaldo) {
        this.conSaldo = conSaldo;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public List<NegocioTiposComprobante> getTiposComprobante() {
        if (tiposComprobante == null) {
            tiposComprobante = new ArrayList<>();
        }
        return tiposComprobante;
    }

    public void setTiposComprobante(List<NegocioTiposComprobante> tiposComprobante) {
        this.tiposComprobante = tiposComprobante;
    }

    public boolean hasTiposComprobanteFilter() {
        return tiposComprobante != null && !tiposComprobante.isEmpty();
    }
}
