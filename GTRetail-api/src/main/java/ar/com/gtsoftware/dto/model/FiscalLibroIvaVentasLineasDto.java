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

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Clase que almacena la información de el detalle de la factura para el libro de iva ventas
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiscalLibroIvaVentasLineasDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;
    private BigDecimal netoGravado;
    private BigDecimal noGravado;
    private BigDecimal importeIva;
    private FiscalLibroIvaVentasDto idRegistro;
    private FiscalAlicuotasIvaDto idAlicuotaIva;
    private Integer version;

}
