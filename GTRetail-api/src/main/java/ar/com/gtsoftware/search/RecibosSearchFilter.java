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

import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.Usuarios;
import java.util.Date;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class RecibosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Date fechaDesde;
    private Date fechaHasta;
    private Personas idPersona;
    private Usuarios idUsuario;
    private Cajas idCaja;
    private Long idRecibo;

    @Override
    public boolean hasFilter() {
        return (fechaDesde != null) || (fechaHasta != null) || (idCaja != null) || (idPersona != null)
                || (idUsuario != null) || (idRecibo != null);
    }

    public RecibosSearchFilter() {
    }

    public RecibosSearchFilter(Date fechaDesde, Date fechaHasta) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cajas getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Cajas idCaja) {
        this.idCaja = idCaja;
    }

    public Long getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Long idRecibo) {
        this.idRecibo = idRecibo;
    }

}
