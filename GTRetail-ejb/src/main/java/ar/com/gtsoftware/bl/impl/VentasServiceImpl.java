/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.PersonasCuentaCorrienteService;
import ar.com.gtsoftware.bl.VentasService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.RegistroVentaDto;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.eao.*;
import ar.com.gtsoftware.mappers.ComprobantesMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.*;
import ar.com.gtsoftware.search.DepositosSearchFilter;
import ar.com.gtsoftware.search.FiscalLetrasComprobantesSearchFilter;
import org.apache.commons.collections.CollectionUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Stateless
public class VentasServiceImpl implements VentasService {

    private static final long ID_ESTADO_ACEPTADA = 2L;
    private static final long ID_TIPO_MOV_VENTA = 2L;
    private static final long ID_TIPO_COMP_PRESUPUESTO = 4L;
    @Inject
    private ComprobantesMapper mapper;

    @EJB
    private ComprobantesFacade facade;

    @EJB
    private FiscalLetrasComprobantesFacade letrasComprobantesFacade;
    @EJB
    private ComprobantesEstadosFacade estadosFacade;
    @EJB
    private FiscalResponsabilidadesIvaFacade responsabilidadesIvaFacade;
    @EJB
    private RemitoTipoMovimientoFacade tipoMovimientoFacade;
    @EJB
    private RemitoFacade remitoFacade;
    @EJB
    private UsuariosFacade usuariosFacade;
    @EJB
    private DepositosFacade depositosFacade;
    @EJB
    private PersonasCuentaCorrienteService cuentaCorrienteBean;

    @Override
    public RegistroVentaDto guardarVenta(ComprobantesDto comprobantesDto, boolean generarRemitoSalida) throws ServiceException {
        Comprobantes comprobante = mapper.dtoToEntity(comprobantesDto, new CycleAvoidingMappingContext());

        boolean isPresupuesto = comprobante.getTipoComprobante().getId() == ID_TIPO_COMP_PRESUPUESTO;
        if (isPresupuesto) {
            comprobante.setSaldo(BigDecimal.ZERO);
        } else {
            comprobante.setSaldo(comprobante.getTotal());
        }
        comprobante.setIdEstadoComprobante(estadosFacade.find(ID_ESTADO_ACEPTADA));
        comprobante.setFechaComprobante(new Date());

        FiscalLetrasComprobantesSearchFilter lsf = FiscalLetrasComprobantesSearchFilter.builder()
                .idRespIvaReceptor(comprobante.getIdPersona().getIdResponsabilidadIva().getId())
                .idRespIvaEmisor(responsabilidadesIvaFacade.find(2L).getId()).build();
        FiscalLetrasComprobantes letra = letrasComprobantesFacade.findFirstBySearchFilter(lsf);
        comprobante.setLetra(letra.getLetraComprobante());

        facade.createOrEdit(comprobante);
        RegistroVentaDto registro = new RegistroVentaDto();
        registro.setIdComprobante(comprobante.getId());
        if (generarRemitoSalida && !isPresupuesto) {
            long idRemito = generarRemitoComprobante(comprobante.getId(),
                    comprobante.getIdUsuario().getId());

            registro.setIdRemito(idRemito);
        }
        if (!isPresupuesto) {
            String descMovimiento = String.format("%s Nro: %d",
                    comprobante.getTipoComprobante().getNombreComprobante(), registro.getIdComprobante());
            cuentaCorrienteBean.registrarMovimientoCuenta(
                    comprobantesDto.getIdPersona(),
                    comprobante.getTotalConSigno(),
                    descMovimiento);
        }
        return registro;
    }

    @Override
    //TODO esto deberìa ir en un bean responsable de manejar el stock :S
    public long generarRemitoComprobante(long idComprobante, long idUsuario) {
        Comprobantes venta = facade.find(idComprobante);

        Remito rem = new Remito();
        List<RemitoDetalle> remitoDetalle = new ArrayList<>(venta.getComprobantesLineasList().size());
        int nroLineaRem = 0;
        for (ComprobantesLineas vl : venta.getComprobantesLineasList()) {

            Productos product = vl.getIdProducto();
            if (product.getIdTipoProveeduria().getControlStock()) {

                //Armo las lineas de remitos
                RemitoDetalle rd = new RemitoDetalle();
                rd.setCantidad(vl.getCantidad());
                rd.setIdProducto(product);
                rd.setNroLinea(nroLineaRem++);
                rd.setRemitoCabecera(rem);

                remitoDetalle.add(rd);
            }
        }
        if (CollectionUtils.isEmpty(remitoDetalle)) {
            return 0;
        }
        Date hoy = new Date();

        rem.setDetalleList(remitoDetalle);
        rem.setFechaAlta(hoy);
        rem.setFechaCierre(hoy);
        RemitoRecepcion recepcion = new RemitoRecepcion();
        recepcion.setFecha(hoy);
        recepcion.setRemito(rem);
        recepcion.setIdUsuario(venta.getIdUsuario());

        //Verifico el origen y destino del remitoDtoCabecera a generar
        Depositos deposito = depositosFacade.findFirstBySearchFilter(DepositosSearchFilter.builder()
                .activo(true)
                .idSucursal(venta.getIdSucursal().getId()).build());
        if (venta.getTipoComprobante().getSigno().signum() > 0) {
            rem.setIsDestinoInterno(false);
            rem.setIsOrigenInterno(true);
            rem.setIdDestinoPrevistoExterno(venta.getIdPersona());
            //Tomo el primer depósito por defecto, esto debería venir de la UI
            rem.setIdOrigenInterno(deposito);
            recepcion.setIdPersona(venta.getIdPersona());
        } else {
            rem.setIsDestinoInterno(true);
            rem.setIsOrigenInterno(false);
            rem.setIdOrigenExterno(venta.getIdPersona());
            //Tomo el primer depósito por defecto, esto debería venir de la UI
            rem.setIdDestinoPrevistoInterno(deposito);
            recepcion.setIdDeposito(rem.getIdDestinoPrevistoInterno());
        }
        rem.setObservaciones(String.format("Remito generado automáticamente por comprobante nro: %d", venta.getId()));
        //TODO: Seteado en Venta por defecto pero debería ir el que corresponda.
        rem.setRemitoTipoMovimiento(tipoMovimientoFacade.find(ID_TIPO_MOV_VENTA));
        rem.setRemitoRecepcionesList(Collections.singletonList(recepcion));
        rem.setIdUsuario(usuariosFacade.find(idUsuario));

        remitoFacade.create(rem);

        return rem.getId();
    }
}
