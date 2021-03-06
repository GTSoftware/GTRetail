/*
 * Copyright 2015 Dilcar S.A..
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

import java.io.Serializable;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * Converter para dar formato a la entrada del usuario, con mayúscula y sin espacios al comienzo y final
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Model
@FacesConverter(value = "stringFormatConverter")
public class StringFormatConverter implements Serializable, javax.faces.convert.Converter {

    private static final long serialVersionUID = 3L;

    @Override
    public Object getAsObject(FacesContext context, UIComponent cmp, String value) {
        if (StringUtils.isNotEmpty(value)) {
            return value.trim().toUpperCase();
        }

        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent cmp, Object value) {

        if (value != null) {
            // return the value as is for presentation
            String strValue = String.valueOf(value);
            if (StringUtils.isNotEmpty(strValue)) {
                return strValue;
            }
        }
        return null;
    }
}
