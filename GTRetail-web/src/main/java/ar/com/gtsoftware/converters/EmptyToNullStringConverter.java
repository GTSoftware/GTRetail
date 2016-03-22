/*
 * Copyright 2016 Dilcar S.A..
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

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * Converter para que los strings que sean submeteados vacíos se conviertan a null.
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@FacesConverter(forClass = String.class)
public class EmptyToNullStringConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (StringUtils.isEmpty(submittedValue)) {
            if (component instanceof EditableValueHolder) {
                ((EditableValueHolder) component).setSubmittedValue(null);
            }

            return null;
        }

        return submittedValue;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object modelValue) {
        return (modelValue == null) ? StringUtils.EMPTY : modelValue.toString();
    }

}
