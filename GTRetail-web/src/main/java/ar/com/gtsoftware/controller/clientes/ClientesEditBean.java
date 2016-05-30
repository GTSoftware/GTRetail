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
import ar.com.gtsoftware.bl.ClientesService;
import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.eao.FiscalResponsabilidadesIvaFacade;
import ar.com.gtsoftware.eao.LegalGenerosFacade;
import ar.com.gtsoftware.eao.LegalTiposDocumentoFacade;
import ar.com.gtsoftware.eao.LegalTiposPersoneriaFacade;
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
import ar.com.gtsoftware.search.ProvinciasSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "clientesEditBean")
@ViewScoped
public class ClientesEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private ClientesService clientesService;

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
    private List<PersonasTelefonos> telefonos = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger(ClientesEditBean.class.getName());

    /**
     * Creates a new instance of ClientesEditBean
     */
    public ClientesEditBean() {
    }

    @PostConstruct
    private void init() {

        String idPersona = JSFUtil.getRequestParameterMap().get("idPersona");
        if (idPersona == null) {
            nuevo();
        } else {
            clienteActual = clientesService.find(Long.parseLong(idPersona));
            if (clienteActual == null) {
                nuevo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente inexistente!", "Cliente inexistente!"));
                Logger.getLogger(ClientesEditBean.class.getName()).log(Level.INFO, "Cliente inexistente!");
            } else {
                telefonos = clientesService.obtenerTelefonos(clienteActual);
            }
        }

    }

    public void nuevo() {
        clienteActual = new Personas();
        clienteActual.setCliente(true);
        clienteActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
    }

    public void doGuardarCliente() {
        try {
            clienteActual.setPersonasTelefonosList(telefonos);
            clienteActual = clientesService.guardarCliente(clienteActual);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar",
                    "Cliente guardado exitosamente."));

        } catch (ServiceException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar:", e.getMessage()));
            LOG.log(Level.INFO, e.getMessage(), e);
        }
    }

    public List<PersonasTelefonos> getTelefonos() {
        return telefonos;
    }

    public void grabarTelefono() {
        if (telefonoActual != null) {
            telefonoActual.setIdPersona(clienteActual);
            telefonos.add(telefonoActual);
        }
        telefonoActual = new PersonasTelefonos();
    }

    public void borrarTelefono(PersonasTelefonos pt) {

        telefonos.remove(pt);
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
        return generosFacade.findAllBySearchFilter(new GenerosSearchFilter(clienteActual.getIdTipoPersoneria()));

    }

    public List<LegalTiposDocumento> getTiposDocumentoList() {
        return tiposDocumentoFacade.findAll();
    }

    public List<UbicacionPaises> getPaisesList() {
        return paisesFacade.findAll();
    }

    public List<UbicacionProvincias> getProvinciasList() {
        ProvinciasSearchFilter psf = new ProvinciasSearchFilter(clienteActual.getIdPais());
        return provinciasFacade.findAllBySearchFilter(psf);
    }

    public List<UbicacionLocalidades> getLocalidadesList() {
        LocalidadesSearchFilter lsf = new LocalidadesSearchFilter(clienteActual.getIdProvincia());
        return localidadesFacade.findAllBySearchFilter(lsf);
    }

    public PersonasTelefonos getTelefonoActual() {
        return telefonoActual;
    }

    public void setTelefonoActual(PersonasTelefonos telefonoActual) {
        this.telefonoActual = telefonoActual;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

}
