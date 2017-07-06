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

import ar.com.gtsoftware.model.Sucursales;
import ar.com.gtsoftware.model.Usuarios;
import java.util.Date;

/**
 * SearchFilter para Arqueos de caja
 *
 * @author Rodrigo M. Tato Rothamel
 */
public class CajasArqueosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Usuarios usuario;
    private Sucursales sucursal;
    private Boolean controlado;
    private Date fechaArqueoDesde, fechaArqueoHasta;

    public CajasArqueosSearchFilter() {
    }

    @Override
    public boolean hasFilter() {
        return usuario != null || sucursal != null || controlado != null
                || hasValidFechasArqueo();
    }

    public CajasArqueosSearchFilter(Usuarios usuario, Sucursales sucursal, Boolean controlado) {
        this.usuario = usuario;
        this.sucursal = sucursal;
        this.controlado = controlado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public Boolean getControlado() {
        return controlado;
    }

    public void setControlado(Boolean controlado) {
        this.controlado = controlado;
    }

    public Date getFechaArqueoDesde() {
        return fechaArqueoDesde;
    }

    public void setFechaArqueoDesde(Date fechaArqueoDesde) {
        this.fechaArqueoDesde = fechaArqueoDesde;
    }

    public Date getFechaArqueoHasta() {
        return fechaArqueoHasta;
    }

    public void setFechaArqueoHasta(Date fechaArqueoHasta) {
        this.fechaArqueoHasta = fechaArqueoHasta;
    }

    public boolean hasValidFechasArqueo() {
        return fechaArqueoDesde != null && fechaArqueoHasta != null;
    }
}
