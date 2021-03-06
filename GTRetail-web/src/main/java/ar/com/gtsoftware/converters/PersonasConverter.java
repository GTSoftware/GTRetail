/*
 * Copyright 2018 GT Software.
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

import ar.com.gtsoftware.bl.PersonasService;
import ar.com.gtsoftware.dto.model.PersonasDto;
import ar.com.gtsoftware.search.PersonasSearchFilter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.convert.FacesConverter;


/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "personasConverter")
@FacesConverter(value = "personasConverter")
public class PersonasConverter extends AbstractEntityConverter<PersonasDto, PersonasSearchFilter> {

    @EJB
    private PersonasService personasService;

    public PersonasConverter() {
        super(PersonasDto.class);
    }

    @Override
    protected PersonasService getService() {
        return personasService;
    }

}

