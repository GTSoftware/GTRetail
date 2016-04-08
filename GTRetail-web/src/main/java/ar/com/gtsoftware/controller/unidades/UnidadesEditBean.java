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
package ar.com.gtsoftware.controller.unidades;

import ar.com.gtsoftware.eao.ProductosTiposUnidadesFacade;
import ar.com.gtsoftware.model.ProductosTiposUnidades;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "unidadesEditBean")
@ViewScoped
public class UnidadesEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(UnidadesEditBean.class.getName());

    @EJB
    private ProductosTiposUnidadesFacade facade;

    private ProductosTiposUnidades unidadActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public UnidadesEditBean() {
    }

    @PostConstruct
    public void init() {

        String idUnidad = JSFUtil.getRequestParameterMap().get("idUnidad");

        if (idUnidad == null) {
            nuevo();
        } else {
            unidadActual = facade.find(Long.parseLong(idUnidad));

            if (unidadActual == null) {
                LOG.log(Level.SEVERE, "Unidad inexistente!");
                throw new RuntimeException("Unidad inexistente!");

            }

        }

    }

    private void nuevo() {
        unidadActual = new ProductosTiposUnidades();
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(unidadActual);
            JSFUtil.addInfoMessage("Unidad guardada Exitosamente");
            unidadActual = facade.find(unidadActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public ProductosTiposUnidades getUnidadActual() {
        return unidadActual;
    }

}
