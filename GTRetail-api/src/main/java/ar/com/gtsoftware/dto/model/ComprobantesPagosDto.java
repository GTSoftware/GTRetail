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
import java.util.Date;

/**
 * Los planes de pago elegidos para cada comprobante
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComprobantesPagosDto implements IdentifiableDto {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private ComprobantesDto idComprobante;

    private NegocioFormasPagoDto idFormaPago;

    private NegocioPlanesPagoDto idPlan;

    private NegocioPlanesPagoDetalleDto idDetallePlan;

    private BigDecimal montoPago;

    private BigDecimal montoPagado;

    private Date fechaPago;

    private Date fechaUltimoPago;
    private Integer version;

    private transient int item;

    private transient int productoRecargoItem;


    /**
     * Retorna el total con signo del pago
     *
     * @return
     */
    public BigDecimal getMontoPagoConSigno() {
        return idComprobante.getTipoComprobante().getSigno().multiply(montoPago);
    }

    /**
     * Retorna el monto pagado con signo del pago
     *
     * @return
     */
    public BigDecimal getMontoPagadoConSigno() {
        return idComprobante.getTipoComprobante().getSigno().multiply(montoPagado);
    }

    @Override
    public String getStringId() {
        return String.valueOf(id);
    }


}
