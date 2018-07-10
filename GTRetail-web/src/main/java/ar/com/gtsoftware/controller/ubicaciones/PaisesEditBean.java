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
package ar.com.gtsoftware.controller.ubicaciones;

import ar.com.gtsoftware.eao.UbicacionPaisesFacade;
import ar.com.gtsoftware.model.UbicacionPaises;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "paisesEditBean")
@ViewScoped
public class PaisesEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(PaisesEditBean.class.getName());

    @EJB
    private UbicacionPaisesFacade facade;

    private UbicacionPaises paisActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public PaisesEditBean() {
    }

    @PostConstruct
    public void init() {

        String idPais = JSFUtil.getRequestParameterMap().get("idPais");

        if (StringUtils.isEmpty(idPais)) {
            nuevo();
        } else {
            paisActual = facade.find(Long.parseLong(idPais));

            if (paisActual == null) {
                LOG.log(Level.SEVERE, "País inexistente!");
                throw new IllegalArgumentException("País inexistente!");

            }

        }

    }

    private void nuevo() {
        paisActual = new UbicacionPaises();
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(paisActual);
            JSFUtil.addInfoMessage("País guardado Exitosamente");
            paisActual = facade.find(paisActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public UbicacionPaises getPaisActual() {
        return paisActual;
    }

}
