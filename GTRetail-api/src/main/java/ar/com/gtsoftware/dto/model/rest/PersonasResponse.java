/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package ar.com.gtsoftware.dto.model.rest;

import ar.com.gtsoftware.dto.IdentifiableDto;
import ar.com.gtsoftware.dto.model.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonasResponse implements IdentifiableDto {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private static final String BUSINESS_STRING = "[%d] %s - %s: %s";


    private String email;
    private String razonSocial;
    private String apellidos;
    private String nombres;
    private String nombreFantasia;
    private String calle;
    private String altura;
    private String piso;
    private String depto;
    private String documento;
    private Date fechaAlta;
    private boolean activo;
    private String provincia;
    private String pais;
    private String localidad;
    private LegalTiposPersoneriaDto idTipoPersoneria;
    private LegalTiposDocumentoDto idTipoDocumento;
    private LegalGenerosDto idGenero;
    private FiscalResponsabilidadesIvaDto idResponsabilidadIva;

    private Long idSucursal;

    public String getBusinessString() {
        return String.format(BUSINESS_STRING, id, razonSocial,
                idTipoDocumento.getNombreTipoDocumento(), documento);
    }

    @Override
    public String getStringId() {
        return String.valueOf(id);
    }
}
