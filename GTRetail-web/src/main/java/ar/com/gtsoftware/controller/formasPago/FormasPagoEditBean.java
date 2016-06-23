/*
 * Copyright 2016 GT Software.
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
package ar.com.gtsoftware.controller.formasPago;

import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit Bean para formas de pago
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "formasPagoEditBean")
@ViewScoped
public class FormasPagoEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(FormasPagoEditBean.class.getName());

    @EJB
    private NegocioFormasPagoFacade facade;

    private NegocioFormasPago formaPagoActual = null;

    /**
     * Creates a new instance of FormasPagoEditBean
     */
    public FormasPagoEditBean() {
    }

    @PostConstruct
    public void init() {

        String idMarca = JSFUtil.getRequestParameterMap().get("idFormaPago");

        if (idMarca == null) {
            nuevo();
        } else {
            formaPagoActual = facade.find(Long.parseLong(idMarca));

            if (formaPagoActual == null) {
                LOG.log(Level.SEVERE, "Forma de pago inexistente!");
                throw new IllegalArgumentException("Forma de pago inexistente!");

            }

        }

    }

    private void nuevo() {
        formaPagoActual = new NegocioFormasPago();
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(formaPagoActual);
            JSFUtil.addInfoMessage("Forma de pago guardada Exitosamente");
            formaPagoActual = facade.find(formaPagoActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public NegocioFormasPago getFormaPagoActual() {
        return formaPagoActual;
    }

}
