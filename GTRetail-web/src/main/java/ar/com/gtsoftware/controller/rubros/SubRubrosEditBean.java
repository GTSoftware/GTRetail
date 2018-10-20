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
import ar.com.gtsoftware.bl.ProductosSubRubrosService;
import ar.com.gtsoftware.dto.model.ProductosRubrosDto;
import ar.com.gtsoftware.dto.model.ProductosSubRubrosDto;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang.StringUtils.isEmpty;

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
    private ProductosSubRubrosService service;
    @EJB
    private ProductosRubrosService rubrosService;

    private ProductosSubRubrosDto subRubroActual = null;
    private ProductosRubrosDto rubroActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public SubRubrosEditBean() {
    }

    @PostConstruct
    public void init() {

        String idRubro = JSFUtil.getRequestParameterMap().get("idRubro");
        String idSubRubro = JSFUtil.getRequestParameterMap().get("idSubRubro");

        if (isEmpty(idRubro)) {
            throw new IllegalArgumentException("IdRubro nulo!");
        }
        rubroActual = rubrosService.find(Long.parseLong(idRubro));
        if (rubroActual == null) {
            throw new IllegalArgumentException("Rubro inexistente!");
        }

        if (isEmpty(idSubRubro)) {
            nuevo();
        } else {
            subRubroActual = service.find(Long.parseLong(idSubRubro));

            if (subRubroActual == null) {
                LOG.log(Level.SEVERE, "SubRubro inexistente!");
                throw new IllegalArgumentException("SubRubro inexistente!");

            }

        }

    }

    private void nuevo() {
        subRubroActual = ProductosSubRubrosDto.builder()
                .idRubro(rubroActual).build();
    }

    public void doGuardar() {
        try {

            subRubroActual = service.createOrEdit(subRubroActual);
            JSFUtil.addInfoMessage("SubRubro guardado Exitosamente");
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public ProductosSubRubrosDto getSubRubroActual() {
        return subRubroActual;
    }

    public ProductosRubrosDto getRubroActual() {
        return rubroActual;
    }

}
