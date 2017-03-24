/*
 * Copyright 2017 GT Software.
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
package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.CajasService;
import ar.com.gtsoftware.eao.CajasArqueosFacade;
import ar.com.gtsoftware.eao.CajasFacade;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.CajasArqueos;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.search.CajasArqueosSearchFilter;
import ar.com.gtsoftware.search.CajasSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Stateless
public class CajasServiceImpl implements CajasService {

    @EJB
    private CajasFacade facade;
    @EJB
    private CajasArqueosFacade arqueosFacade;

    @Override
    public Cajas obtenerCajaActual(Usuarios usuario) {
        CajasSearchFilter cajasFilter = new CajasSearchFilter(usuario,
                usuario.getIdSucursal(), Boolean.TRUE);
        cajasFilter.addSortField(new SortField("fechaApertura", false));

        int cantCajasAbiertas = facade.countBySearchFilter(cajasFilter);
        if (cantCajasAbiertas > 1) {
            throw new RuntimeException(String.format("El usuario %s tiene más de una caja abierta en la sucursal %d!",
                    usuario.getNombreUsuario(),
                    usuario.getIdSucursal().getId()));
        }

        return facade.findFirstBySearchFilter(cajasFilter);
    }

    @Override
    public Cajas abrirCaja(Usuarios usuario) {
        if (obtenerCajaActual(usuario) != null) {

            Cajas caja = new Cajas();
            caja.setFechaApertura(new Date());
            caja.setIdUsuario(usuario);
            caja.setIdSucursal(usuario.getIdSucursal());
            //Obtener el último arqueo y sacar el saldo final de allí.
            caja.setSaldoInicial(obtenerSaldoUltimoArqueo(usuario));
            facade.create(caja);
        }
        CajasSearchFilter cajasFilter = new CajasSearchFilter(usuario,
                usuario.getIdSucursal(), Boolean.TRUE);
        return facade.findFirstBySearchFilter(cajasFilter);
    }

    /**
     * Busca el último arqueo realizado por el usuario para esa sucursal y retorna el saldo de cierre.
     *
     * @param usuario
     * @return
     */
    private BigDecimal obtenerSaldoUltimoArqueo(Usuarios usuario) {
        CajasArqueosSearchFilter casf = new CajasArqueosSearchFilter(usuario, usuario.getIdSucursal(), null);
        casf.addSortField(new SortField("fechaArqueo", false));
        CajasArqueos ultimoArqueo = arqueosFacade.findFirstBySearchFilter(casf);
        if (ultimoArqueo != null) {
            return ultimoArqueo.getSaldoFinal();
        }
        return BigDecimal.ZERO;
    }

    @Override
    public boolean cerrarCaja(Cajas caja, Date fechaCierre) {
        if (caja != null && fechaCierre != null) {
            caja.setFechaCierre(fechaCierre);
            facade.edit(caja);
            return true;
        }
        return false;
    }

}
