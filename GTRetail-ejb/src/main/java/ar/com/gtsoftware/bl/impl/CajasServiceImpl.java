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
import ar.com.gtsoftware.dto.model.CajasDto;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.eao.*;
import ar.com.gtsoftware.mappers.CajasMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.Cajas;
import ar.com.gtsoftware.model.CajasArqueos;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.search.CajasArqueosSearchFilter;
import ar.com.gtsoftware.search.CajasSearchFilter;
import ar.com.gtsoftware.search.SortField;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Stateless
public class CajasServiceImpl
        extends AbstractBasicEntityService<CajasDto, CajasSearchFilter, Cajas>
        implements CajasService {

    @EJB
    private CajasFacade facade;
    @EJB
    private CajasArqueosFacade arqueosFacade;
    @EJB
    private CuponesFacade cuponesFacade;
    @EJB
    private UsuariosFacade usuariosFacade;
    @EJB
    private CajasTransferenciasFacade transferenciasFacade;


    @Inject
    private CajasMapper cajasMapper;

    @Override
    public CajasDto obtenerCajaActual(UsuariosDto usuario) {

        CajasSearchFilter cajasFilter = CajasSearchFilter.builder()
                .idUsuario(usuario.getId())
                .idSucursal(usuario.getIdSucursal().getId())
                .abierta(true)
                .build();
        cajasFilter.addSortField(new SortField("fechaApertura", false));

        int cantCajasAbiertas = facade.countBySearchFilter(cajasFilter);
        if (cantCajasAbiertas > 1) {
            //This should never happen
            throw new RuntimeException(String.format("El usuario %s tiene más de una caja abierta en la sucursal %d!",
                    usuario.getNombreUsuario(),
                    usuario.getIdSucursal().getId()));
        }
        Cajas caja = facade.findFirstBySearchFilter(cajasFilter);
        return cajasMapper.entityToDto(caja, new CycleAvoidingMappingContext());
    }

    @Override
    public CajasDto abrirCaja(UsuariosDto usuarioDto) {
        if (obtenerCajaActual(usuarioDto) == null) {

            Cajas caja = new Cajas();
            caja.setFechaApertura(new Date());
            Usuarios usuario = usuariosFacade.find(usuarioDto.getId());
            caja.setIdUsuario(usuario);
            caja.setIdSucursal(usuario.getIdSucursal());
            //Obtener el último arqueo y sacar el saldo final de allí.
            caja.setSaldoInicial(obtenerSaldoUltimoArqueo(usuario));
            facade.create(caja);
        }

        return obtenerCajaActual(usuarioDto);
    }

    /**
     * Busca el último arqueo realizado por el usuario para esa sucursal y retorna el saldo de cierre.
     *
     * @param usuario
     * @return
     */
    private BigDecimal obtenerSaldoUltimoArqueo(Usuarios usuario) {
        CajasArqueosSearchFilter casf = CajasArqueosSearchFilter.builder()
                .idUsuario(usuario.getId())
                .idSucursal(usuario.getIdSucursal().getId())
                .build();
        casf.addSortField(new SortField("fechaArqueo", false));
        CajasArqueos ultimoArqueo = arqueosFacade.findFirstBySearchFilter(casf);
        if (ultimoArqueo != null) {
            return ultimoArqueo.getSaldoFinal();
        }
        return BigDecimal.ZERO;
    }

    @Override
    public boolean cerrarCaja(CajasDto cajaDto, Date fechaCierre) {
        Cajas caja = facade.find(cajaDto.getId());
        if (caja.getFechaCierre() != null) {
            return false;
        }
        caja.setFechaCierre(fechaCierre);
        facade.edit(caja);
        //Seteo la fecha de presentacion en los cupones
        cuponesFacade.establecerFechaPresentacion(caja, fechaCierre);
        return true;
    }

    @Override
    public BigDecimal obtenerTotalEnCaja(@NotNull CajasSearchFilter csf) {

        BigDecimal totalCobranzas = facade.obtenerTotalDeCaja(csf);
        BigDecimal erogacionTransf = transferenciasFacade.obtenerTotalTransferenciasEmitidas(csf);
        BigDecimal recepcionTransf = transferenciasFacade.obtenerTotalTransferenciasRecibidas(csf);

        return totalCobranzas.add(erogacionTransf).add(recepcionTransf);
    }

    @Override
    protected CajasFacade getFacade() {
        return facade;
    }

    @Override
    protected CajasMapper getMapper() {
        return cajasMapper;
    }
}
