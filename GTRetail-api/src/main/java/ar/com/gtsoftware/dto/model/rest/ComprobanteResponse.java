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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa las ventas que se realizan
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComprobanteResponse implements IdentifiableDto {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private Date fechaComprobante;
    private BigDecimal total;
    private BigDecimal saldo;
    private String observaciones;
    private String remitente;
    private String nroRemito;
    private String letra;

    private String tipoComprobante;

    private List<ComprobantesLineasResponse> comprobantesLineasList;
    private String usuario;
    private Long idSucursal;
    private PersonasResponse idPersona;
    private String condicionComprobante;

    private BigDecimal totalConSigno;
    private BigDecimal saldoConSigno;

    @Override
    public String getStringId() {
        return String.valueOf(id);
    }
}
