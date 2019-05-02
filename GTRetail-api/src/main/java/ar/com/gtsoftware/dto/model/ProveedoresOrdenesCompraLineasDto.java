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
import java.math.BigDecimal;

/**
 * @author rodrigo
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedoresOrdenesCompraLineasDto implements IdentifiableDto {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private BigDecimal precioCompraUnitario;
    @NotNull
    private BigDecimal cantidadPedida;
    @NotNull
    private BigDecimal subTotal;
    @NotNull
    private BigDecimal cantidadRecibida;
    @NotNull
    private ProveedoresOrdenesCompraDto idOrdenCompra;

    @NotNull
    private ProductosDto idProducto;
    @NotNull
    private ProductosTiposUnidadesDto idTipoUnidad;


    @EqualsAndHashCode.Include
    private int nroLinea;

    private Integer version;

    @Override
    public String getStringId() {
        return String.valueOf(id);
    }
}
