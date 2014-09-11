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

import ar.com.gtsoftware.eao.FiscalPeriodosFiscalesFacade;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * COnverter para PeriodosFiscales
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Model
@FacesConverter(value = "fiscalPeriodoFiscalConverter")
public class FiscalPeriodoFiscalConverter implements Converter {

    @EJB
    private FiscalPeriodosFiscalesFacade periodosFiscalesFacade;

    /**
     * Creates a new instance of UbicacionPaisesConverter
     */
    public FiscalPeriodoFiscalConverter() {
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
        return periodosFiscalesFacade.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (!value.getClass().equals(FiscalPeriodosFiscales.class)) {
            return null;
        }
        FiscalPeriodosFiscales periodoFiscal = (FiscalPeriodosFiscales) value;
        return periodoFiscal.getId().toString();
    }
}
