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
package ar.com.gtsoftware.controller.proveedores;

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
import java.util.Collections;
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
@ManagedBean(name = "proveedoresEditBean")
@ViewScoped
public class ProveedoresEditBean implements Serializable {

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
    @EJB
    private JSFUtil jsfUtil;

    private Personas proveedorActual;
    private PersonasTelefonos telefonoActual = new PersonasTelefonos();
    private List<PersonasTelefonos> telefonos = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger(ProveedoresEditBean.class.getName());

    /**
     * Creates a new instance of ClientesEditBean
     */
    public ProveedoresEditBean() {
    }

    @PostConstruct
    private void init() {

        String idPersona = jsfUtil.getRequestParameterMap().get("idPersona");
        if (idPersona == null) {
            nuevo();
        } else {
            proveedorActual = clientesService.find(Long.parseLong(idPersona));
            if (proveedorActual == null) {
                nuevo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente inexistente!", "Cliente inexistente!"));
                Logger.getLogger(ProveedoresEditBean.class.getName()).log(Level.INFO, "Cliente inexistente!");
            } else {
                telefonos = clientesService.obtenerTelefonos(proveedorActual);
            }
        }

    }

    public void nuevo() {
        proveedorActual = new Personas();
        proveedorActual.setProveedor(true);
        proveedorActual.setActivo(true);
        proveedorActual.setIdSucursal(authBackingBean.getUserLoggedIn().getIdSucursal());
    }

    public void doGuardarCliente() {
        try {
            proveedorActual.setPersonasTelefonosList(telefonos);
            proveedorActual = clientesService.guardarCliente(proveedorActual);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar",
                    "Proveedor guardado exitosamente."));

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
            telefonoActual.setIdPersona(proveedorActual);
            telefonos.add(telefonoActual);
        }
        telefonoActual = new PersonasTelefonos();
    }

    public void borrarTelefono(PersonasTelefonos pt) {

        telefonos.remove(pt);
    }

    public Personas getProveedorActual() {
        return proveedorActual;
    }

    public void setProveedorActual(Personas proveedorActual) {
        this.proveedorActual = proveedorActual;
    }

    public List<LegalTiposPersoneria> getTiposPersoneriaList() {
        return legalTiposPersoneriaFacade.findAll();
    }

    public List<FiscalResponsabilidadesIva> getResponsabilidadesIVAList() {
        return responsabilidadesIvaFacade.findAll();
    }

    public List<LegalGeneros> getGenerosList() {
        return generosFacade.findAllBySearchFilter(new GenerosSearchFilter(proveedorActual.getIdTipoPersoneria()));

    }

    public List<LegalTiposDocumento> getTiposDocumentoList() {
        return tiposDocumentoFacade.findAll();
    }

    public List<UbicacionPaises> getPaisesList() {
        return paisesFacade.findAll();
    }

    public List<UbicacionProvincias> getProvinciasList() {
        if (proveedorActual.getIdPais() != null) {
            ProvinciasSearchFilter psf = new ProvinciasSearchFilter(proveedorActual.getIdPais());
            return provinciasFacade.findAllBySearchFilter(psf);
        }
        return Collections.EMPTY_LIST;
    }

    public List<UbicacionLocalidades> getLocalidadesList() {
        if (proveedorActual.getIdProvincia() != null) {
            LocalidadesSearchFilter lsf = new LocalidadesSearchFilter(proveedorActual.getIdProvincia());
            return localidadesFacade.findAllBySearchFilter(lsf);
        }
        return Collections.EMPTY_LIST;
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
