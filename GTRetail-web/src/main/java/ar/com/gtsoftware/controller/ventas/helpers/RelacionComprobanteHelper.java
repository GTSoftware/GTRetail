/*
 * Copyright 2019 GT Software.
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

package ar.com.gtsoftware.controller.ventas.helpers;

import ar.com.gtsoftware.controller.ventas.dto.ComprobanteRelacionado;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.ComprobantesLineasDto;
import ar.com.gtsoftware.dto.model.NegocioTiposComprobanteDto;
import ar.com.gtsoftware.enums.NegocioTiposComprobanteEnum;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static ar.com.gtsoftware.enums.NegocioTiposComprobanteEnum.FACTURA;
import static ar.com.gtsoftware.enums.NegocioTiposComprobanteEnum.NOTA_DE_CREDITO;

public class RelacionComprobanteHelper {

    private final AtomicInteger itemCounter;

    public RelacionComprobanteHelper(AtomicInteger itemCounter) {
        this.itemCounter = itemCounter;
    }

    public ComprobanteRelacionado generarComprobanteRelacionado(ComprobantesDto comprobanteOriginal,
                                                                List<Long> idsProductosReservados) {
        List<ComprobantesLineasDto> comprobantesLineasOriginalList = comprobanteOriginal.getComprobantesLineasList();

        return ComprobanteRelacionado.builder()
                .comprobantesLineas(quitarProductosReservados(comprobantesLineasOriginalList, idsProductosReservados))
                .tipoComprobante(determinarTipoComprobanteActual(comprobanteOriginal.getTipoComprobante()))
                .build();
    }

    private NegocioTiposComprobanteEnum determinarTipoComprobanteActual(NegocioTiposComprobanteDto tipoComprobanteOriginal) {
        if (tipoComprobanteOriginal.getId().equals(FACTURA.getId())) {
            return NOTA_DE_CREDITO;
        }

        return FACTURA;
    }

    private List<ComprobantesLineasDto> quitarProductosReservados(List<ComprobantesLineasDto> comprobantesLineasList,
                                                                  List<Long> idsProductosReservados) {
        if (CollectionUtils.isEmpty(idsProductosReservados)) {
            limpiarLineas(comprobantesLineasList);
            return comprobantesLineasList;
        }

        List<ComprobantesLineasDto> result = new ArrayList<>();
        for (ComprobantesLineasDto linea : comprobantesLineasList) {
            if (!idsProductosReservados.contains(linea.getIdProducto().getId())) {
                result.add(linea);
            }
        }

        limpiarLineas(result);

        return result;
    }

    private void limpiarLineas(List<ComprobantesLineasDto> comprobantesLineasList) {
        for (ComprobantesLineasDto linea : comprobantesLineasList) {
            linea.setId(null);
            linea.setIdComprobante(null);
            linea.setVersion(null);
            linea.setItem(itemCounter.getAndIncrement());
        }
    }

}
