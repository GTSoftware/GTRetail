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

package ar.com.gtsoftware.mappers;

import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import org.mapstruct.Context;

import java.io.Serializable;
import java.util.List;

/**
 * Raiz de todos los mappers de entidad-Dto
 *
 * @param <E> Entidad
 * @param <D> DTO
 */
public interface GenericMapper<E extends Serializable, D extends Serializable> {
    /**
     * Mapea una entidad E a un DTO D
     *
     * @param entidad
     * @return un dto mapeado desde la entidad
     */
    D entityToDto(E entidad, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    /**
     * Mapea una lista de entidades E a una lista de Dto
     *
     * @param entidades
     * @return una lista de dtos mapeados desde la lista de entidad
     */
    List<D> entitiesToDtos(List<E> entidades, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    /**
     * Mapea un dto D a una entidad E
     *
     * @param dto
     * @return una entidad mapeada desde el dto
     */
    E dtoToEntity(D dto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    /**
     * Mapea una lista de dto D a una lista de entidades E
     *
     * @param dtos
     * @return una lista de entidades mapeadas desde la lista de dtos
     */
    List<E> dtosToEntities(List<D> dtos, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}
