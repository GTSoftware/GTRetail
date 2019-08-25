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
package ar.com.gtsoftware.controller.rubros;

import ar.com.gtsoftware.bl.ProductosRubrosService;
import ar.com.gtsoftware.dto.model.ProductosRubrosDto;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EditBean para Rubros de productos
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "rubrosEditBean")
@ViewScoped
public class RubrosEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RubrosEditBean.class.getName());

    @EJB
    private ProductosRubrosService service;

    private ProductosRubrosDto rubroActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public RubrosEditBean() {
    }

    @PostConstruct
    public void init() {

        String idRubro = JSFUtil.getRequestParameterMap().get("idRubro");

        if (idRubro == null) {
            nuevo();
        } else {
            rubroActual = service.find(Long.parseLong(idRubro));

            if (rubroActual == null) {
                LOG.log(Level.SEVERE, "Rubro inexistente!");
                throw new IllegalArgumentException("Rubro inexistente!");

            }

        }

    }

    private void nuevo() {
        rubroActual = new ProductosRubrosDto();
    }

    public void doGuardar() {
        try {
            rubroActual = service.createOrEdit(rubroActual);
            JSFUtil.addInfoMessage("Rubro guardado Exitosamente");
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }
    }

    public ProductosRubrosDto getRubroActual() {
        return rubroActual;
    }

}
