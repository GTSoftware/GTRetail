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
package ar.com.gtsoftware.controller.parametros;

import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.model.Parametros;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "parametrosEditBean")
@ViewScoped
public class ParametrosEditBean {

    @EJB
    private ParametrosFacade parametrosFacade;

    private Parametros parametroActual;

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public ParametrosEditBean() {
    }

    @PostConstruct
    public void init() {
        String nombreParametro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("nombreParametro");
        if (nombreParametro != null) {
            parametroActual = parametrosFacade.findParametroByName(nombreParametro);
            if (parametroActual == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Parámetro inexistente!", "Parámetro inexistente!"));
                Logger.getLogger(ParametrosEditBean.class.getName()).log(Level.INFO, "Parámetro inexistente!");
            }
        }

    }

    public void edit() {
        if (parametroActual != null) {
            parametrosFacade.edit(parametroActual);
            FacesMessage msg = new FacesMessage("Parámetro editado con éxito: " + parametroActual.getNombreParametro(),
                    parametroActual.getValorParametro());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void establecerParametro(Parametros param) {
        this.parametroActual = param;
    }

    public Parametros getParametroActual() {
        return parametroActual;
    }

    public void setParametroActual(Parametros parametroActual) {
        this.parametroActual = parametroActual;
    }

}
