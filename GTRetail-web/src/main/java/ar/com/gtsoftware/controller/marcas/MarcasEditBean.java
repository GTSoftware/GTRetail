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
package ar.com.gtsoftware.controller.marcas;

import ar.com.gtsoftware.eao.ProductosMarcasFacade;
import ar.com.gtsoftware.model.ProductosMarcas;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Named(value = "marcasEditBean")
@ViewScoped
public class MarcasEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(MarcasEditBean.class.getName());

    @EJB
    private ProductosMarcasFacade facade;

    private ProductosMarcas marcaActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public MarcasEditBean() {
    }

    @PostConstruct
    public void init() {

        String idMarca = JSFUtil.getRequestParameterMap().get("idMarca");

        if (idMarca == null) {
            nuevo();
        } else {
            marcaActual = facade.find(Long.parseLong(idMarca));

            if (marcaActual == null) {
                LOG.log(Level.SEVERE, "Marca inexistente!");
                throw new IllegalArgumentException("Marca inexistente!");

            }

        }

    }

    private void nuevo() {
        marcaActual = new ProductosMarcas();
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(marcaActual);
            JSFUtil.addInfoMessage("Marca guardada Exitosamente");
            marcaActual = facade.find(marcaActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public ProductosMarcas getMarcaActual() {
        return marcaActual;
    }

}
