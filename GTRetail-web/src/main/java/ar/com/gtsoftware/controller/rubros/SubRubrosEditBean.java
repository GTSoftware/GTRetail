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

import ar.com.gtsoftware.eao.ProductosRubrosFacade;
import ar.com.gtsoftware.eao.ProductosSubRubrosFacade;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.model.ProductosSubRubros;
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
 * EditBean para Rubros de productos
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "subRubrosEditBean")
@ViewScoped
public class SubRubrosEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SubRubrosEditBean.class.getName());

    @EJB
    private ProductosSubRubrosFacade facade;
    @EJB
    private ProductosRubrosFacade rubrosFacade;

    private ProductosSubRubros subRubroActual = null;
    private ProductosRubros rubroActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public SubRubrosEditBean() {
    }

    @PostConstruct
    public void init() {

        String idRubro = JSFUtil.getRequestParameterMap().get("idRubro");
        String idSubRubro = JSFUtil.getRequestParameterMap().get("idSubRubro");

        if (StringUtils.isEmpty(idRubro)) {
            throw new IllegalArgumentException("IdRubro nulo!");
        }
        rubroActual = rubrosFacade.find(Long.parseLong(idRubro));
        if (rubroActual == null) {
            throw new IllegalArgumentException("Rubro inexistente!");
        }

        if (StringUtils.isEmpty(idSubRubro)) {
            nuevo();
        } else {
            subRubroActual = facade.find(Long.parseLong(idSubRubro));

            if (subRubroActual == null) {
                LOG.log(Level.SEVERE, "SubRubro inexistente!");
                throw new IllegalArgumentException("SubRubro inexistente!");

            }

        }

    }

    private void nuevo() {
        subRubroActual = new ProductosSubRubros();
        subRubroActual.setIdRubro(rubroActual);
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(subRubroActual);
            JSFUtil.addInfoMessage("SubRubro guardado Exitosamente");
            subRubroActual = facade.find(subRubroActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public ProductosSubRubros getSubRubroActual() {
        return subRubroActual;
    }

    public ProductosRubros getRubroActual() {
        return rubroActual;
    }

}
