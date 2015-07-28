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

import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.UbicacionLocalidadesFacade;
import ar.com.gtsoftware.model.UbicacionLocalidades;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Model
@FacesConverter(value = "ubicacionLocalidadesConverter")
public class UbicacionLocalidadesConverter extends AbstractEntityConverter<UbicacionLocalidades> {

    @EJB
    private UbicacionLocalidadesFacade ubicacionLocalidadesFacade;

    public UbicacionLocalidadesConverter() {
        super(UbicacionLocalidades.class);
    }

    @Override
    protected AbstractFacade<UbicacionLocalidades> getFacade() {
        return ubicacionLocalidadesFacade;
    }

}
