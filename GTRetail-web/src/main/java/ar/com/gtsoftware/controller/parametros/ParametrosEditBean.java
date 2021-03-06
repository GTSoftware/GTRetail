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

import ar.com.gtsoftware.bl.ParametrosService;
import ar.com.gtsoftware.dto.model.ParametrosDto;
import ar.com.gtsoftware.helper.JSFHelper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "parametrosEditBean")
@ViewScoped
public class ParametrosEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ParametrosEditBean.class.getName());

    @EJB
    private ParametrosService service;
    @Inject
    private JSFHelper jsfHelper;


    private ParametrosDto parametroActual;

    private String nombreParametro;

    public ParametrosEditBean() {
    }

    public void init() {

        if (jsfHelper.isPostback()) {
            return;
        }
        nombreParametro = jsfHelper.getRequestParameterMap().get("nombreParametro");
        if (isEmpty(nombreParametro)) {
            throw new IllegalArgumentException("El parámetro es nulo");
        }

        parametroActual = service.findParametroByName(nombreParametro);
        if (parametroActual == null) {
            LOG.log(Level.INFO, "Parámetro inexistente!");
            throw new IllegalArgumentException("El parámetro es nulo");

        }

    }

    public void edit() {

        service.createOrEdit(parametroActual);
        jsfHelper.addInfoMessage(String.format("Parámetro editado con éxito: %s - %s",
                parametroActual.getNombreParametro(), parametroActual.getValorParametro()));

    }

    public ParametrosDto getParametroActual() {
        return parametroActual;
    }

    public void setParametroActual(ParametrosDto parametroActual) {
        this.parametroActual = parametroActual;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

}
