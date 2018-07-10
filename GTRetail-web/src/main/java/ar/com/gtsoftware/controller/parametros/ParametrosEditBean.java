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
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.gtsoftware.utils.JSFUtil.*;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "parametrosEditBean")
@ViewScoped
public class ParametrosEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ParametrosEditBean.class.getName());

    @EJB
    private ParametrosFacade parametrosFacade;


    private Parametros parametroActual;

    private String nombreParametro;

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public ParametrosEditBean() {
    }

    public void init() {

        if (isPostback()) {
            return;
        }
        nombreParametro = getRequestParameterMap().get("nombreParametro");
        if (StringUtils.isEmpty(nombreParametro)) {
            throw new IllegalArgumentException("El parámetro es nulo");
        }

        parametroActual = parametrosFacade.findParametroByName(nombreParametro);
        if (parametroActual == null) {
            LOG.log(Level.INFO, "Parámetro inexistente!");
            throw new IllegalArgumentException("El parámetro es nulo");

        }

    }

    public void edit() {

        parametrosFacade.edit(parametroActual);
        addInfoMessage(String.format("Parámetro editado con éxito: %s - %s",
                parametroActual.getNombreParametro(), parametroActual.getValorParametro()));

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

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

}
