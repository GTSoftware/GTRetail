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
package ar.com.gtsoftware.service.rest;

import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.dto.RegistrarFacturaDTO;
import ar.com.gtsoftware.model.dto.ResultadoDTO;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Path("/ventas")
public interface ComprobantesEndpoint {

    /**
     * Retorna la lista de comprobantes pendientes de facturar del día de hoy.
     *
     * @param idSucursal
     * @return lista de comprobantes
     */
    @GET
    @Path("/pendientes")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Comprobantes> getPendientesFacturar(@QueryParam("sucursal") Long idSucursal);

    /**
     * Retorna el comprobante que esté pendiente de facturar con ese id.
     *
     * @param idComprobante
     * @return comprobante
     */
    @GET
    @Path("/pendiente")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Comprobantes getPendienteFacturar(@QueryParam("idComprobante") Long idComprobante);

    /**
     * Retorna la lista de comprobantes pendientes de facturar entre las fechas pasadas por parámetro.
     *
     * @param idSucursal
     * @param fechaDesde
     * @param hasta
     * @return lista de comprobantes
     */
    @GET
    @Path("/pendientes")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Comprobantes> getPendientesFacturar(@QueryParam("sucursal") Long idSucursal,
            @QueryParam("desde") Date fechaDesde,
            @QueryParam("hasta") Date hasta);

    /**
     * Recibe el registro con el número de comprobante y punto de venta
     *
     * @param registro
     * @return el resultado de registrar la facturación
     */
    @POST
    @Path("/registrar")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ResultadoDTO registrarFacturacion(RegistrarFacturaDTO registro);
}
