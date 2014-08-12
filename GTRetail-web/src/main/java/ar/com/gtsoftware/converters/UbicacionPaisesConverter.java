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

import ar.com.gtsoftware.eao.UbicacionPaisesFacade;
import ar.com.gtsoftware.model.UbicacionPaises;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author rodrigo
 */
@Named(value = "ubicacionPaisesConverter")
@RequestScoped
public class UbicacionPaisesConverter implements Converter {

    @EJB
    private UbicacionPaisesFacade ubicacionPaisesFacade;

    /**
     * Creates a new instance of UbicacionPaisesConverter
     */
    public UbicacionPaisesConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Integer id;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
        return ubicacionPaisesFacade.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (!value.getClass().equals(UbicacionPaises.class)) {
            return null;
        }
        UbicacionPaises pais = (UbicacionPaises) value;
        return pais.getIdPais().toString();
    }
}
