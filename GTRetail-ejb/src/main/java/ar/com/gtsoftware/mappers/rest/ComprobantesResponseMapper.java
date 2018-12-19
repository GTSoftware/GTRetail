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

package ar.com.gtsoftware.mappers.rest;

import ar.com.gtsoftware.dto.model.rest.ComprobanteResponse;
import ar.com.gtsoftware.dto.model.rest.ComprobantesLineasResponse;
import ar.com.gtsoftware.dto.model.rest.PersonasResponse;
import ar.com.gtsoftware.model.Comprobantes;
import ar.com.gtsoftware.model.ComprobantesLineas;
import ar.com.gtsoftware.model.Personas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ComprobantesResponseMapper {

    @Mappings({
            @Mapping(target = "tipoComprobante", source = "entity.tipoComprobante.nombreComprobante"),
            @Mapping(target = "usuario", source = "entity.idUsuario.nombreUsuario"),
            @Mapping(target = "idSucursal", source = "entity.idSucursal.id"),
            @Mapping(target = "condicionComprobante", source = "entity.idCondicionComprobante.nombreCondicion")
    })
    ComprobanteResponse comprobanteToResponse(Comprobantes entity);


    @Mappings({
            @Mapping(target = "localidad", source = "entity.idLocalidad.nombreLocalidad"),
            @Mapping(target = "pais", source = "entity.idPais.nombrePais"),
            @Mapping(target = "provincia", source = "entity.idProvincia.nombreProvincia"),
            @Mapping(target = "idSucursal", source = "entity.idSucursal.id")
    })
    PersonasResponse personaToResponse(Personas entity);

    @Mappings({
            @Mapping(target = "idComprobante", source = "entity.idComprobante.id"),
            @Mapping(target = "idProducto", source = "entity.idProducto.id"),
            @Mapping(target = "iva", source = "entity.idProducto.idAlicuotaIva.valorAlicuota")
    })
    ComprobantesLineasResponse lineasToResponse(ComprobantesLineas entity);

    List<ComprobanteResponse> comprobantesToResponses(List<Comprobantes> entity);


}
