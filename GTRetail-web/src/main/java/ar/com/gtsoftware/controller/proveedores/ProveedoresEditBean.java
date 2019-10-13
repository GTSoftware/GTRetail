/*
 * Copyright 2018 GT Software.
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
package ar.com.gtsoftware.controller.proveedores;

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
@ManagedBean(name = "proveedoresEditBean")
@ViewScoped
public class ProveedoresEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ProveedoresEditBean.class.getName());

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;
    @EJB
    private ClientesService clientesService;
    @EJB
    private LegalTiposPersoneriaService legalTiposPersoneriaFacade;
    @EJB
    private FiscalResponsabilidadesIvaService responsabilidadesIvaFacade;
    @EJB
    private LegalGenerosService generosFacade;
    @EJB
    private LegalTiposDocumentoService tiposDocumentoFacade;
    @EJB
    private UbicacionPaisesService paisesFacade;
    @EJB
    private UbicacionLocalidadesService localidadesFacade;
    @EJB
    private UbicacionProvinciasService provinciasFacade;
    @Inject
    private JSFHelper jsfHelper;

    private int nroItem = 0;
    private PersonasDto proveedorActual;

    public ProveedoresEditBean() {
    }

    @PostConstruct
    private void init() {

        String idPersona = jsfHelper.getRequestParameterMap().get("idPersona");
        if (idPersona == null) {
            nuevo();
        } else {
            proveedorActual = clientesService.find(Long.parseLong(idPersona));
            if (proveedorActual == null) {
                nuevo();
                jsfHelper.addErrorMessage("Proveedor inexistente!");
                LOG.log(Level.INFO, "Proveedor inexistente!");
            }
        }

    }

    public void nuevo() {
        proveedorActual = new PersonasDto();
        proveedorActual.setProveedor(true);
        proveedorActual.setCliente(false);
        proveedorActual.setActivo(true);
        proveedorActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
    }

    public void doGuardarCliente() {
        try {
            proveedorActual = clientesService.guardarCliente(proveedorActual);
            jsfHelper.addInfoMessage("Proveedor guardado exitosamente.");

        } catch (ServiceException e) {
            jsfHelper.addErrorMessage("Error al guardar: " + e.getMessage());
            LOG.log(Level.INFO, e.getMessage(), e);
        }
    }


    public void nuevoTelefono() {
        if (CollectionUtils.isEmpty(proveedorActual.getPersonasTelefonosList())) {
            proveedorActual.setPersonasTelefonosList(new ArrayList<>(2));
        }
        proveedorActual.getPersonasTelefonosList().add(PersonasTelefonosDto.builder()
                .idPersona(proveedorActual)
                .numero("0")
                .item(nroItem++)
                .build());
    }

    public void borrarTelefono(PersonasTelefonosDto telefono) {
        proveedorActual.getPersonasTelefonosList().remove(telefono);
    }

    public PersonasDto getProveedorActual() {
        return proveedorActual;
    }

    public void setProveedorActual(PersonasDto proveedorActual) {
        this.proveedorActual = proveedorActual;
    }

    public List<LegalTiposPersoneriaDto> getTiposPersoneriaList() {
        return legalTiposPersoneriaFacade.findAll();
    }

    public List<FiscalResponsabilidadesIvaDto> getResponsabilidadesIVAList() {
        return responsabilidadesIvaFacade.findAll();
    }

    public List<LegalGenerosDto> getGenerosList() {
        if (proveedorActual.getIdTipoPersoneria() == null) {
            return generosFacade.findAll();
        }
        return generosFacade.findAllBySearchFilter(GenerosSearchFilter.builder()
                .idTipoPersoneria(proveedorActual.getIdTipoPersoneria().getId()).build());

    }

    public List<LegalTiposDocumentoDto> getTiposDocumentoList() {
        return tiposDocumentoFacade.findAll();
    }

    public List<UbicacionPaisesDto> getPaisesList() {
        return paisesFacade.findAll();
    }

    public List<UbicacionProvinciasDto> getProvinciasList() {
        if (proveedorActual.getIdPais() != null) {
            ProvinciasSearchFilter psf = ProvinciasSearchFilter.builder()
                    .idPais(proveedorActual.getIdPais().getId()).build();
            return provinciasFacade.findAllBySearchFilter(psf);
        }
        return Collections.emptyList();
    }

    public List<UbicacionLocalidadesDto> getLocalidadesList() {
        if (proveedorActual.getIdProvincia() != null) {
            LocalidadesSearchFilter lsf = LocalidadesSearchFilter.builder()
                    .idProvincia(proveedorActual.getIdProvincia().getId()).build();
            return localidadesFacade.findAllBySearchFilter(lsf);
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
