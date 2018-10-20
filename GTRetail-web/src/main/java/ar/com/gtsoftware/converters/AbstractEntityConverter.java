/* Copyright 2018 GT Software.
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

import ar.com.gtsoftware.bl.EntityService;
import ar.com.gtsoftware.dto.IdentifiableDto;
import ar.com.gtsoftware.search.AbstractSearchFilter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * Converter abstracto para DTOs
 *
 * @param <T>
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 **/


public abstract class AbstractEntityConverter<T extends IdentifiableDto, S extends AbstractSearchFilter>
        implements Converter {

    private final Class<T> entityClass;

    public AbstractEntityConverter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private AbstractEntityConverter() {
        this.entityClass = null;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (isEmpty(value)) {
            return null;
        }

        try {
            long id = Long.parseLong(value);
            return getService().find(id);
        } catch (NumberFormatException e) {
            Logger.getLogger(AbstractEntityConverter.class.getName()).log(Level.SEVERE, null, e);
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid dto."));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value == null) {
            return null;
        }
        try {
            return ((T) value).getStringId();

        } catch (ClassCastException ex) {
            return null;
        }
    }

    protected abstract EntityService<T, S> getService();
}
