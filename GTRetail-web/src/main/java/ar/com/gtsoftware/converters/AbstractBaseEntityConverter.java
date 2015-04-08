/*
 * Copyright 2015 GT Software.
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

import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.model.BaseEntity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Converter abstracto para entidades BaseEntity
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @param <T>
 */
public abstract class AbstractBaseEntityConverter<T extends BaseEntity> implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Long id;
        try {
            id = Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
        return getFacade().find(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }
        try {
            T entity = (T) value;
            return String.valueOf(entity.getId());
        } catch (ClassCastException ex) {
            return null;
        }
    }

    protected abstract AbstractFacade<T> getFacade();
}
