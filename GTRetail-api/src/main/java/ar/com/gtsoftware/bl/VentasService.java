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

package ar.com.gtsoftware.bl;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.dto.RegistroVentaDto;
import ar.com.gtsoftware.dto.model.ComprobantesDto;

import javax.ejb.Remote;

/**
 * Capa de servicio para registrar ventas y demás servicios relacionados.
 */
@Remote
public interface VentasService {

    /**
     * Guarda la venta y genera el remito de salida si el parámetro <code>generarRemitoSalida</code> se setea en true
     *
     * @param comprobantesDto
     * @param generarRemitoSalida
     * @return
     */
    RegistroVentaDto guardarVenta(ComprobantesDto comprobantesDto, boolean generarRemitoSalida) throws ServiceException;

    /**
     * Genera el remito de entrada o salida segùn corresponda el tipo de comprobante
     *
     * @param idComprobante
     * @return
     */
    long generarRemitoComprobante(long idComprobante, long idUsuario);
}
