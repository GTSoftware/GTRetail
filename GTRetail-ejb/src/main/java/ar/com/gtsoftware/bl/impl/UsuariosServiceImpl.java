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

import ar.com.gtsoftware.bl.UsuariosService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.UsuariosDto;
import ar.com.gtsoftware.dto.model.UsuariosGruposDto;
import ar.com.gtsoftware.eao.UsuariosFacade;
import ar.com.gtsoftware.eao.UsuariosGruposFacade;
import ar.com.gtsoftware.mappers.UsuariosGruposMapper;
import ar.com.gtsoftware.mappers.UsuariosMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.Usuarios;
import ar.com.gtsoftware.model.UsuariosGrupos;
import ar.com.gtsoftware.search.UsuariosSearchFilter;
import ar.com.gtsoftware.utils.HashUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsuariosServiceImpl extends AbstractBasicEntityService<UsuariosDto,
        UsuariosSearchFilter, Usuarios>
        implements UsuariosService {

    private static final String DEFAULT_PASSWORD = "Cambiame";

    @EJB
    private UsuariosFacade facade;
    @EJB
    private UsuariosGruposFacade gruposFacade;

    @Inject
    private UsuariosMapper mapper;

    @Inject
    private UsuariosGruposMapper gruposMapper;

    @Override
    protected UsuariosFacade getFacade() {
        return facade;
    }

    @Override
    protected UsuariosMapper getMapper() {
        return mapper;
    }


    @Override
    public void changePassword(Long idUsuario, String newPassword) throws ServiceException {
        if (newPassword.length() < 6) {
            throw new ServiceException("La clave no puede contener menos de 6 dìgitos");
        }
        Usuarios usuario = facade.find(idUsuario);
        if (usuario == null) {
            throw new ServiceException("Usuario inexistente");
        }
        String newPassHashed = HashUtils.getHash(newPassword);
        if (usuario.getPassword().equalsIgnoreCase(newPassHashed)) {
            throw new ServiceException("La clave anterior y la nueva coinciden");
        }
        usuario.setPassword(newPassHashed);
        facade.edit(usuario);
    }


    @Override
    public List<UsuariosGruposDto> obtenerRolesDisponibles() {
        return gruposMapper.entitiesToDtos(gruposFacade.findAll(), new CycleAvoidingMappingContext());
    }

    @Override
    public List<UsuariosGruposDto> obtenerRolesUsuario(@NotNull Long idUsuario) {
        Usuarios usuario = facade.find(idUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("No se encontrò un usuario con ese Id");
        }
        return gruposMapper.entitiesToDtos(usuario.getUsuariosGruposList(), new CycleAvoidingMappingContext());
    }

    @Override
    public void agregarRol(@NotNull Long idUsuario, @NotNull Long idGrupo) {
        Usuarios usuario = facade.find(idUsuario);
        UsuariosGrupos grupo = gruposFacade.find(idGrupo);
        if (usuario.getUsuariosGruposList() == null) {
            usuario.setUsuariosGruposList(new ArrayList<>(1));
        }
        usuario.getUsuariosGruposList().add(grupo);
        facade.edit(usuario);
    }

    @Override
    public void quitarRol(@NotNull Long idUsuario, @NotNull Long idGrupo) {
        Usuarios usuario = facade.find(idUsuario);
        UsuariosGrupos grupo = gruposFacade.find(idGrupo);
        if (usuario.getUsuariosGruposList() == null) {
            return;
        }
        usuario.getUsuariosGruposList().remove(grupo);
        facade.edit(usuario);
    }

    @Override
    public UsuariosDto createOrEdit(@NotNull UsuariosDto dto) {
        Usuarios usuario = mapper.dtoToEntity(dto, new CycleAvoidingMappingContext());
        if (usuario.isNew()) {
            usuario.setPassword(HashUtils.getHash(DEFAULT_PASSWORD));
        } else {
            Usuarios oldUsuario = facade.find(dto.getId());
            usuario.setPassword(oldUsuario.getPassword());
        }
        return mapper.entityToDto(facade.createOrEdit(usuario),
                new CycleAvoidingMappingContext());
    }

    @Override
    public String resetPassword(@NotNull Long idUsuario) {
        Usuarios usuario = facade.find(idUsuario);

        if (usuario != null) {
            usuario.setPassword(HashUtils.getHash(DEFAULT_PASSWORD));
            facade.createOrEdit(usuario);
            return DEFAULT_PASSWORD;
        }

        return null;

    }


}
