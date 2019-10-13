/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.controller.ofertas;

import ar.com.gtsoftware.bl.OfertasService;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.rules.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "ofertaEditBean")
@ViewScoped
public class OfertaEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(OfertaEditBean.class.getName());


    @EJB
    private OfertasService service;
    @Inject
    private JSFHelper jsfHelper;

    private OfertaDto ofertaActual;

    private int nroItem = 1;

    @PostConstruct
    public void init() {

        String idOferta = jsfHelper.getRequestParameterMap().get("idOferta");
        String duplicar = jsfHelper.getRequestParameterMap().get("duplicar");

        if (StringUtils.isEmpty(idOferta)) {
            nuevo();
        } else {

            ofertaActual = service.find(Long.parseLong(idOferta));

            if (ofertaActual == null) {
                logger.log(Level.SEVERE, "Oferta inexistente!");
                throw new RuntimeException("Oferta inexistente!");

            }
            if (StringUtils.equals("1", duplicar)) {
                ofertaActual.setId(null);
            }
        }

    }

    private void nuevo() {
        ofertaActual = new OfertaDto();
    }


    public OfertaDto getOfertaActual() {
        return ofertaActual;
    }

    public TipoAccion[] getTiposAcciones() {
        return TipoAccion.values();
    }

    public void doGuardar() {
        if (validarCondiciones()) {
            ofertaActual = service.createOrEdit(ofertaActual);
            jsfHelper.addInfoMessage("Oferta guardada con éxito.");
        }
    }


    private boolean validarCondiciones() {
        boolean isValid = true;
        try {
            for (Condicion condicion : ofertaActual.getCondiciones()) {

                condicion.buildExpression();

            }
        } catch (CondicionIlegalException e) {
            isValid = false;
            jsfHelper.addErrorMessage(e.getMessage());
        }
        return isValid;
    }

    public void nuevaCondicion() {
        if (CollectionUtils.isEmpty(ofertaActual.getCondiciones())) {
            ofertaActual.setCondiciones(new ArrayList<>(5));
        }
        ofertaActual.getCondiciones().add(Condicion.builder().idOferta(ofertaActual).nroItem(nroItem++).build());
    }

    public Campo[] getCampos() {
        return Campo.values();
    }

    public Operacion[] getOperaciones() {
        return Operacion.values();
    }

    public void borrarCondicion(Condicion cond) {
        ofertaActual.getCondiciones().remove(cond);
    }
}
