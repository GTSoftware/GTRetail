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
public class ComprobantesDto implements IdentifiableDto {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private Date fechaComprobante;
    private BigDecimal total;
    private BigDecimal saldo;
    private String observaciones;
    private String remitente;
    private String nroRemito;
    private boolean anulada;
    private String letra;

    private NegocioTiposComprobanteDto tipoComprobante;

    private List<ComprobantesLineasDto> comprobantesLineasList;
    private UsuariosDto idUsuario;
    private SucursalesDto idSucursal;
    private PersonasDto idPersona;
    private NegocioCondicionesOperacionesDto idCondicionComprobante;
    private ComprobantesEstadosDto idEstadoComprobante;
    private FiscalLibroIvaVentasDto idRegistro;

    private List<ComprobantesPagosDto> pagosList;

    private BigDecimal totalConSigno;
    private BigDecimal saldoConSigno;
    private Integer version;


    public BigDecimal getTotalConSigno() {
        if (total != null && tipoComprobante != null) {
            if (totalConSigno == null) {
                totalConSigno = total.multiply(tipoComprobante.getSigno());
            }
        }
        return totalConSigno;
    }

    public BigDecimal getSaldoConSigno() {
        if (saldo != null && tipoComprobante != null) {
            if (saldoConSigno == null) {
                saldoConSigno = saldo.multiply(tipoComprobante.getSigno());
            }
        }
        return saldoConSigno;
    }

    public void addPago(ComprobantesPagosDto pago) {
        if (pagosList == null) {
            pagosList = new ArrayList<>(3);
        }
        pagosList.add(pago);
    }

    public void addLineaVenta(ComprobantesLineasDto linea) {
        if (comprobantesLineasList == null) {
            comprobantesLineasList = new ArrayList<>();
        }
        comprobantesLineasList.add(linea);
    }

    @Override
    public String toString() {
        if (idRegistro != null) {
            return String.format("[%d] %s %s %s-%s", getId(), tipoComprobante.getNombreComprobante(), letra,
                    idRegistro.getPuntoVentaFactura(), idRegistro.getNumeroFactura());
        }
        return String.format("[%d] %s", getId(), tipoComprobante.getNombreComprobante());
    }

    @Override
    public String getStringId() {
        return String.valueOf(id);
    }
}
