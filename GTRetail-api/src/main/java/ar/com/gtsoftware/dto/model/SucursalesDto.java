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
package ar.com.gtsoftware.dto.model;

import ar.com.gtsoftware.dto.IdentifiableDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalesDto implements IdentifiableDto {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String nombreSucursal;
    @Size(max = 500)
    private String direccion;
    @Size(max = 20)
    private String telefonoFijo;
    @NotNull
    private Date fechaAlta;
    @NotNull
    private boolean activo;

    private UbicacionProvinciasDto idProvincia;
    private UbicacionPaisesDto idPais;
    private UbicacionLocalidadesDto idLocalidad;
    private List<DepositosDto> depositosDtoList;
    private Integer version;

    @Override
    public String toString() {
        return String.format("[%d] %s", id, nombreSucursal);
    }

    @Override
    public String getStringId() {
        return String.valueOf(id);
    }
}
