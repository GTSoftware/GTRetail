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

package ar.com.gtsoftware.bl;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.dto.model.UsuariosGruposDto;
import ar.com.gtsoftware.search.UsuariosSearchFilter;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;
import java.util.List;

@Remote
public interface UsuariosService
        extends EntityService<UsuariosDto, UsuariosSearchFilter> {

    /**
     * Valida y actualiza la clave del usuario pasado como paràmetro
     *
     * @param idUsuario
     * @param newPassword
     * @throws ServiceException
     */
    void changePassword(@NotNull Long idUsuario, @NotNull String newPassword) throws ServiceException;

    List<UsuariosGruposDto> obtenerRolesDisponibles();

    List<UsuariosGruposDto> obtenerRolesUsuario(@NotNull Long idUsuario);

    void agregarRol(@NotNull Long idUsuario, @NotNull Long idGrupo);

    void quitarRol(@NotNull Long idUsuario, @NotNull Long idGrupo);


}
