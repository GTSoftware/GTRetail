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
package ar.com.gtsoftware.converters;

import ar.com.gtsoftware.eao.NegocioCondicionesOperacionesFacade;
import ar.com.gtsoftware.model.NegocioCondicionesOperaciones;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author rodrigo
 */
@Model
@FacesConverter(value = "negocioCondicionesOperacionesConverter")
public class NegocioCondicionesOperacionesConverter implements Converter {

    @EJB
    private NegocioCondicionesOperacionesFacade negocioCondicionesOperacionesFacade;

    /**
     * Creates a new instance of UbicacionPaisesConverter
     */
    public NegocioCondicionesOperacionesConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Long id;
        try {
            id = Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
        return negocioCondicionesOperacionesFacade.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (!value.getClass().equals(NegocioCondicionesOperaciones.class)) {
            return null;
        }
        NegocioCondicionesOperaciones condicionOperacion = (NegocioCondicionesOperaciones) value;
        return condicionOperacion.getId().toString();
    }
}
