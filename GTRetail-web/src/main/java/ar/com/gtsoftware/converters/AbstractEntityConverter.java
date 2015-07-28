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
import ar.com.gtsoftware.model.GTEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Converter abstracto para entidades BaseEntity
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @param <T>
 */
public abstract class AbstractEntityConverter<T extends GTEntity> implements Converter {

    private final Class<T> entityClass;

    public AbstractEntityConverter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public AbstractEntityConverter() {
        this.entityClass = null;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            Object calculateId = entityClass.newInstance().calculateId(value);
            if (calculateId != null) {
                return getFacade().find(calculateId);
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }
        try {
            T entity = (T) value;
            return entity.getStringId();
        } catch (ClassCastException ex) {
            return null;
        }
    }

    protected abstract AbstractFacade<T> getFacade();
}
