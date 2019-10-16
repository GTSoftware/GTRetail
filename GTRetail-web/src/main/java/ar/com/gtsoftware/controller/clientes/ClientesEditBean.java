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
package ar.com.gtsoftware.controller.clientes;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.bl.*;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.model.*;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.GenerosSearchFilter;
import ar.com.gtsoftware.search.LocalidadesSearchFilter;
import ar.com.gtsoftware.search.ProvinciasSearchFilter;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "clientesEditBean")
@ViewScoped
public class ClientesEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ClientesEditBean.class.getName());
    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
    @EJB
    private ClientesService clientesService;
    @EJB
    private LegalTiposPersoneriaService tiposPersoneriaService;
    @EJB
    private FiscalResponsabilidadesIvaService responsabilidadesIvaService;
    @EJB
    private LegalGenerosService generosService;
    @EJB
    private LegalTiposDocumentoService tiposDocumentoService;
    @EJB
    private UbicacionPaisesService paisesService;
    @EJB
    private UbicacionProvinciasService provinciasService;
    @EJB
    private UbicacionLocalidadesService localidadesService;
    @Inject
    private JSFHelper jsfHelper;

    private int nroItem = 0;
    private PersonasDto clienteActual;

    /**
     * Creates a new instance of ClientesEditBean
     */
    public ClientesEditBean() {
    }

    @PostConstruct
    private void init() {

        String idPersona = jsfHelper.getRequestParameterMap().get("idPersona");
        if (idPersona == null) {
            nuevo();
        } else {
            clienteActual = clientesService.find(Long.parseLong(idPersona));
            if (clienteActual == null) {
                nuevo();
                jsfHelper.addMessage("Cliente inexistente!", FacesMessage.SEVERITY_ERROR);
                Logger.getLogger(ClientesEditBean.class.getName()).log(Level.INFO, "Cliente inexistente!");
            }
        }

    }

    public void nuevo() {
        clienteActual = PersonasDto.builder()
                .cliente(true)
                .activo(true)
                .idSucursal(authBackingBean.getUserLoggedIn().getIdSucursal()).build();

    }

    public void doGuardarCliente() {
        try {
            clienteActual = clientesService.guardarCliente(clienteActual);
            jsfHelper.addInfoMessage("Cliente guardado exitosamente.");

        } catch (ServiceException e) {
            jsfHelper.addErrorMessage("Error al guardar:" + e.getMessage());
            LOG.log(Level.INFO, e.getMessage(), e);
        }
    }

    public void nuevoTelefono() {
        if (CollectionUtils.isEmpty(clienteActual.getPersonasTelefonosList())) {
            clienteActual.setPersonasTelefonosList(new ArrayList<>(2));
        }
        clienteActual.getPersonasTelefonosList().add(PersonasTelefonosDto.builder()
                .idPersona(clienteActual)
                .numero("0")
                .item(nroItem++)
                .build());
    }

    public void borrarTelefono(PersonasTelefonosDto telefono) {
        clienteActual.getPersonasTelefonosList().remove(telefono);
    }

    public PersonasDto getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(PersonasDto clienteActual) {
        this.clienteActual = clienteActual;
    }

    public List<LegalTiposPersoneriaDto> getTiposPersoneriaList() {
        return tiposPersoneriaService.findAll();
    }

    public List<FiscalResponsabilidadesIvaDto> getResponsabilidadesIVAList() {
        return responsabilidadesIvaService.findAll();
    }

    public List<LegalGenerosDto> getGenerosList() {
        if (clienteActual.getIdTipoPersoneria() == null) {
            return generosService.findAll();
        }
        return generosService.findAllBySearchFilter(
                GenerosSearchFilter.builder()
                        .idTipoPersoneria(clienteActual.getIdTipoPersoneria().getId()).build());

    }

    public List<LegalTiposDocumentoDto> getTiposDocumentoList() {
        return tiposDocumentoService.findAll();
    }

    public List<UbicacionPaisesDto> getPaisesList() {
        return paisesService.findAll();
    }

    public List<UbicacionProvinciasDto> getProvinciasList() {
        if (clienteActual.getIdPais() != null) {
            ProvinciasSearchFilter psf = ProvinciasSearchFilter.builder()
                    .idPais(clienteActual.getIdPais().getId()).build();
            return provinciasService.findAllBySearchFilter(psf);
        }
        return Collections.emptyList();
    }

    public List<UbicacionLocalidadesDto> getLocalidadesList() {
        if (clienteActual.getIdProvincia() != null) {
            LocalidadesSearchFilter lsf = LocalidadesSearchFilter.builder()
                    .idProvincia(clienteActual.getIdProvincia().getId()).build();
            return localidadesService.findAllBySearchFilter(lsf);
        }
        return Collections.emptyList();
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

}
