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
import ar.com.gtsoftware.eao.FiscalResponsabilidadesIvaFacade;
import ar.com.gtsoftware.eao.LegalGenerosFacade;
import ar.com.gtsoftware.eao.LegalTiposDocumentoFacade;
import ar.com.gtsoftware.eao.LegalTiposPersoneriaFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.UbicacionLocalidadesFacade;
import ar.com.gtsoftware.eao.UbicacionPaisesFacade;
import ar.com.gtsoftware.eao.UbicacionProvinciasFacade;
import ar.com.gtsoftware.model.FiscalResponsabilidadesIva;
import ar.com.gtsoftware.model.LegalGeneros;
import ar.com.gtsoftware.model.LegalTiposDocumento;
import ar.com.gtsoftware.model.LegalTiposPersoneria;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.PersonasTelefonos;
import ar.com.gtsoftware.model.UbicacionLocalidades;
import ar.com.gtsoftware.model.UbicacionPaises;
import ar.com.gtsoftware.model.UbicacionProvincias;
import ar.com.gtsoftware.search.GenerosSearchFilter;
import ar.com.gtsoftware.search.LocalidadesSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.ProvinciasSearchFilter;
import ar.com.gtsoftware.utils.FormatUtils;
import ar.com.gtsoftware.validators.ValidadorCUIT;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "clientesEditBean")
@ViewScoped
public class ClientesEditBean implements Serializable {

    @Inject
    private AuthBackingBean authBackingBean;

    @EJB
    private PersonasFacade personasFacade;

    @EJB
    private LegalTiposPersoneriaFacade legalTiposPersoneriaFacade;
    @EJB
    private FiscalResponsabilidadesIvaFacade responsabilidadesIvaFacade;
    @EJB
    private LegalGenerosFacade generosFacade;
    @EJB
    private LegalTiposDocumentoFacade tiposDocumentoFacade;
    @EJB
    private UbicacionPaisesFacade paisesFacade;
    @EJB
    private UbicacionLocalidadesFacade localidadesFacade;
    @EJB
    private UbicacionProvinciasFacade provinciasFacade;
    private Personas clienteActual;
    private PersonasTelefonos telefonoActual = new PersonasTelefonos();

    /**
     * Creates a new instance of ClientesEditBean
     */
    public ClientesEditBean() {
    }

    @PostConstruct
    private void init() {

        String idPersona = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idPersona");
        if (idPersona == null) {
            nuevo();
        } else {
            clienteActual = personasFacade.find(Long.parseLong(idPersona));
            if (clienteActual == null) {
                nuevo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente inexistente!", "Cliente inexistente!"));
                Logger.getLogger(ClientesEditBean.class.getName()).log(Level.INFO, "Cliente inexistente!");
            }
        }

    }

    public void nuevo() {
        clienteActual = new Personas();
        clienteActual.setCliente(true);
    }

    public void doGuardarCliente() {
        try {
            if (clienteActual != null) {
                validarCliente();
                formatDatosCliente();
                if (clienteActual.isNew()) {

                    clienteActual.setFechaAlta(GregorianCalendar.getInstance().getTime());
                    clienteActual.setActivo(true);
                    clienteActual.setCliente(true);
                    clienteActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
                    personasFacade.create(clienteActual);

                } else {

                    personasFacade.edit(clienteActual);
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente guardado exitosamente."));
            }
            clienteActual = personasFacade.find(clienteActual.getId());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar:", e.getMessage()));
            Logger.getLogger(ClientesEditBean.class.getName()).log(Level.INFO, e.getMessage(), e);
        }
    }

    public void grabarTelefono() {
        if (telefonoActual != null) {
            telefonoActual.setIdPersona(clienteActual);
            if (clienteActual.getPersonasTelefonosList() == null) {
                clienteActual.setPersonasTelefonosList(new ArrayList<PersonasTelefonos>());
            }
            clienteActual.getPersonasTelefonosList().add(telefonoActual);
        }
        telefonoActual = new PersonasTelefonos();
    }

    public void borrarTelefono(PersonasTelefonos pt) {

        clienteActual.getPersonasTelefonosList().remove(pt);
    }

    private String formatRazonSocial(String razonSocialCliente, String apellidos, String nombres, long tipoPersoneria) {
        String razonSocial = razonSocialCliente;
        if (tipoPersoneria == 1) {//Persona física
            razonSocial = apellidos.trim().concat(", ").concat(nombres).trim();
        }
        razonSocial = razonSocial.toUpperCase();
        return razonSocial;
    }

    private void formatDatosCliente() {
        clienteActual.setRazonSocial(formatRazonSocial(clienteActual.getRazonSocial(),
                clienteActual.getApellidos(), clienteActual.getNombres(),
                clienteActual.getIdTipoPersoneria().getId()));
        clienteActual.setCalle(FormatUtils.uppercase(clienteActual.getCalle()));
        clienteActual.setApellidos(FormatUtils.uppercase(clienteActual.getApellidos()));
        clienteActual.setNombres(FormatUtils.uppercase(clienteActual.getNombres()));
        clienteActual.setNombreFantasia(FormatUtils.uppercase(clienteActual.getNombreFantasia()));
        clienteActual.setDepto(FormatUtils.uppercase(clienteActual.getDepto()));
        clienteActual.setAltura(FormatUtils.uppercase(clienteActual.getAltura()));
        clienteActual.setPiso(FormatUtils.uppercase(clienteActual.getPiso()));
        clienteActual.setEmail(FormatUtils.lowercase(clienteActual.getEmail()));
        if (clienteActual.getPersonasTelefonosList() != null) {
            for (PersonasTelefonos pt : clienteActual.getPersonasTelefonosList()) {
                pt.setReferencia(FormatUtils.uppercase(pt.getReferencia()));
            }
        }
    }

    private void validarCliente() throws Exception {
        try {
            long documento = Long.parseLong(clienteActual.getDocumento());
            if (documento <= 0) {
                throw new Exception("El documento debe ser un número positivo distinto de 0!");
            }
        } catch (NumberFormatException nfe) {
            throw new Exception("El documento debe ser un número!");
        }
        PersonasSearchFilter psf = new PersonasSearchFilter(clienteActual.getIdTipoDocumento(), clienteActual.getDocumento(), Boolean.TRUE, Boolean.TRUE, null);
        List<Personas> result = personasFacade.findAllBySearchFilter(psf);
        if (clienteActual.isNew()) {
            if (result != null && !result.isEmpty()) {
                throw new Exception("Ya existe un cliente con ese número de documento!");
            }
        } else if (result.size() == 1) {
            if (!result.get(0).getId().equals(clienteActual.getId())) {
                throw new Exception("Ya existe un cliente con ese número de documento!");
            }
        }
        if (clienteActual.getIdTipoDocumento().getId() == 2 && !ValidadorCUIT.getValidate(clienteActual.getDocumento())) {
            throw new Exception("El número de CUIT ingresado no es válido!");
        }
    }

    public Personas getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Personas clienteActual) {
        this.clienteActual = clienteActual;
    }

    public List<LegalTiposPersoneria> getTiposPersoneriaList() {
        return legalTiposPersoneriaFacade.findAll();
    }

    public List<FiscalResponsabilidadesIva> getResponsabilidadesIVAList() {
        return responsabilidadesIvaFacade.findAll();
    }

    public List<LegalGeneros> getGenerosList() {
        return generosFacade.findBySearchFilter(new GenerosSearchFilter(clienteActual.getIdTipoPersoneria()));
    }

    public List<LegalTiposDocumento> getTiposDocumentoList() {
        return tiposDocumentoFacade.findAll();
    }

    public List<UbicacionPaises> getPaisesList() {
        return paisesFacade.findAll();
    }

    public List<UbicacionProvincias> getProvinciasList() {
        ProvinciasSearchFilter psf = new ProvinciasSearchFilter(clienteActual.getIdPais());
        return provinciasFacade.findBySearchFilter(psf);
    }

    public List<UbicacionLocalidades> getLocalidadesList() {
        LocalidadesSearchFilter lsf = new LocalidadesSearchFilter(clienteActual.getIdProvincia());
        return localidadesFacade.findBySearchFilter(lsf);
    }

    public PersonasTelefonos getTelefonoActual() {
        return telefonoActual;
    }

    public void setTelefonoActual(PersonasTelefonos telefonoActual) {
        this.telefonoActual = telefonoActual;
    }

}
