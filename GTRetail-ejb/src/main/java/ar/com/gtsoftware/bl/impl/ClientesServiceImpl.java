/*
 * Copyright 2015 GT Software.
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

import ar.com.gtsoftware.bl.ClientesService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.PersonasDto;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.PersonasTelefonosFacade;
import ar.com.gtsoftware.mappers.PersonasMapper;
import ar.com.gtsoftware.mappers.helper.CycleAvoidingMappingContext;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.PersonasTelefonos;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.validators.ValidadorCUIT;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Stateless
public class ClientesServiceImpl implements ClientesService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private static final Logger LOG = Logger.getLogger(ClientesServiceImpl.class.getName());
    @EJB
    private PersonasFacade personasFacade;
    @EJB
    private PersonasTelefonosFacade telefonosFacade;
    @Inject
    private PersonasMapper personasMapper;

    @Override
    public PersonasDto guardarCliente(PersonasDto clienteDto) throws ServiceException {

        Personas cliente = personasMapper.dtoToEntity(clienteDto, new CycleAvoidingMappingContext());

        if (cliente != null) {
            validarCliente(cliente);
            formatDatosCliente(cliente);
            if (cliente.isNew()) {

                cliente.setFechaAlta(new Date());
                cliente.setActivo(true);
                cliente.setCliente(true);
                personasFacade.create(cliente);

            } else {

                personasFacade.edit(cliente);
            }
            return personasMapper.entityToDto(personasFacade.find(cliente.getId()), new CycleAvoidingMappingContext());
        }
        return null;

    }

    private void formatDatosCliente(Personas cliente) {
        cliente.setRazonSocial(formatRazonSocial(cliente.getRazonSocial(),
                cliente.getApellidos(), cliente.getNombres(),
                cliente.getIdTipoPersoneria().getId()));

    }

    /**
     * Retorna la razón social del cliente en forma de Apellidos, Nombres si es persona física, en otro caso lo deja
     * intacto
     *
     * @param razonSocialCliente
     * @param apellidos
     * @param nombres
     * @param tipoPersoneria
     * @return
     */
    private String formatRazonSocial(String razonSocialCliente, String apellidos, String nombres, long tipoPersoneria) {
        String razonSocial = razonSocialCliente;
        if (tipoPersoneria == 1) {//Persona física
            razonSocial = String.format("%s, %s", apellidos, nombres);
        }
        return razonSocial;
    }

    /**
     * Valida los datos del cliente y verifica que no existan duplicados
     *
     * @param cliente
     * @throws ServiceException
     */
    private void validarCliente(Personas cliente) throws ServiceException {
        try {
            long documento = Long.parseLong(cliente.getDocumento());
            if (documento <= 0) {
                throw new ServiceException("El documento debe ser un número positivo distinto de 0!");
            }
        } catch (NumberFormatException nfe) {
            throw new ServiceException("El documento debe ser un número!");
        }
        //Es responsable inscripto y tiene cargado DNI
        if (cliente.getIdResponsabilidadIva().getId() == 2 && cliente.getIdTipoDocumento().getId() != 2) {
            throw new ServiceException("Se debe cargar tipo de documento como CUIT para Responsables Inscriptos");
        }
        if (cliente.getIdTipoDocumento().getId() == 2 && !ValidadorCUIT.getValidate(cliente.getDocumento())) {
            throw new ServiceException("El número de CUIT ingresado no es válido!");
        }
        PersonasSearchFilter psf = PersonasSearchFilter.builder()
                .idTipoDocumento(cliente.getIdTipoDocumento().getId())
                .documento(cliente.getDocumento())
                .activo(true)
                .cliente(true).build();
        int result = personasFacade.countBySearchFilter(psf);
        if (cliente.isNew()) {
            if (result > 0) {
                throw new ServiceException("Ya existe un cliente con ese número de documento!");
            }
        } else if (personasFacade.existePersonaRepetida(cliente)) {
            throw new ServiceException("Ya existe un cliente con ese número de documento!");

        }

    }

    @Override
    public PersonasDto find(Long id) {
        return personasMapper.entityToDto(personasFacade.find(id), new CycleAvoidingMappingContext());
    }

    /*@Override
    public List<PersonasTelefonos> obtenerTelefonos(Personas persona) {
        PersonasTelefonosSearchFilter ptsf = new PersonasTelefonosSearchFilter();
        ptsf.setIdPersona(persona.getId());
        return telefonosFacade.findAllBySearchFilter(ptsf);
    }*/

    public void guardarTelefono(PersonasTelefonos telefono) {
        telefonosFacade.createOrEdit(telefono);
    }


    public void borrarTelefono(PersonasTelefonos telefono) {
        telefonosFacade.remove(telefono);
    }

}
