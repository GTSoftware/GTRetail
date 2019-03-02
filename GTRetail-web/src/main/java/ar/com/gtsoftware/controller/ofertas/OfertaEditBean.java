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
import ar.com.gtsoftware.rules.*;
import ar.com.gtsoftware.utils.JSFUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

    private OfertaDto ofertaActual;

    private int nroItem = 1;

    @PostConstruct
    public void init() {

        String idOferta = JSFUtil.getRequestParameterMap().get("idOferta");
        String duplicar = JSFUtil.getRequestParameterMap().get("duplicar");

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
        initDatos();

    }

    private void nuevo() {
        ofertaActual = new OfertaDto();
    }

    private void initDatos() {

    }

    public OfertaDto getOfertaActual() {
        return ofertaActual;
    }

    public TipoAccion[] getTiposAcciones() {
        return TipoAccion.values();
    }

    public void doGuardar() {

        try {
            for (Condicion condicion : ofertaActual.getCondiciones()) {

                condicion.buildExpression();

            }
            ofertaActual = service.createOrEdit(ofertaActual);
            JSFUtil.addInfoMessage("Oferta guardada con éxito.");
        } catch (CondicionIlegalException e) {
            JSFUtil.addErrorMessage(e.getMessage());
        }

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
