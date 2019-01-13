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

import java.math.BigDecimal;

/**
 * @author fede
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemitoDetalleDto implements IdentifiableDto {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;
    private RemitoDto remitoCabecera;

    private ProductosDto idProducto;

    private BigDecimal cantidad;

    @EqualsAndHashCode.Include
    private int nroLinea;
    private Integer version;

    private transient BigDecimal stockTotal;
    private transient BigDecimal stockDeposito;



    @Override
    public String getStringId() {
        return String.valueOf(id);
    }
}
