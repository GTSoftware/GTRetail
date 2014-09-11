/*
 * Copyright 2014 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsofware.bl;

import ar.com.gtsoftware.eao.FiscalLibroIvaVentasFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.VentasLineas;
import ar.com.gtsoftware.model.dto.FacturaDTO;
import ar.com.gtsoftware.model.dto.ImportesAlicuotasIVA;
import ar.com.gtsoftware.model.dto.LibroIVADTO;
import ar.com.gtsoftware.search.IVAVentasSearchFilter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Servicio para generar el libro de IVA ventas
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @since 1.0.1
 * @version 1.0.0
 */
@Stateless
@LocalBean
public class LibroIVAVentasBean {

    @EJB
    private FiscalLibroIvaVentasFacade ivaVentasFacade;

    public LibroIVADTO obtenerLibroIVA(IVAVentasSearchFilter filter) {
        LibroIVADTO libro = new LibroIVADTO(filter.getPeriodo().getFechaInicioPeriodo(),
                filter.getPeriodo().getFechaFinPeriodo());
        List<FiscalLibroIvaVentas> facturas = ivaVentasFacade.findBySearchFilter(filter);
        //TODO recorrer lista
        //Obtener lineas
        //Acumular IVA
        //Acumular por tipo de IVA
        //Generar Factura DTO
        //Retornar LibroIVADTO
        List<FacturaDTO> facturasDTOList = new ArrayList<>();
        List<ImportesAlicuotasIVA> importesIVA = new ArrayList<>();
        for (FiscalLibroIvaVentas factura : facturas) {
            FacturaDTO facDTO = new FacturaDTO();
            facDTO.setDocumentoCliente(factura.getDocumento());
            facDTO.setFechaFactura(factura.getFechaFactura());
            facDTO.setIdFactura(factura.getId());
            facDTO.setRazonSocialCliente(factura.getIdPersona().getRazonSocial());
            facDTO.setNumeroFactura(factura.getLetraFactura().concat(" ")
                    .concat(factura.getPuntoVentaFactura())
                    .concat(factura.getNumeroFactura()));
        }

        return libro;
    }
/*
    private void calcularIVA() {
        HashMap<FiscalAlicuotasIva, BigDecimal> importe = new HashMap<>();
        for (VentasLineas vl : ventaActual.getVentasLineasList()) {
            FiscalAlicuotasIva alicuota = vl.getIdProducto().getIdAlicuotaIva();
            //Importe*(1+alicuota/100)=Neto
            if (alicuota.getGravarIva()) {
                //Importe*(1+alicuota/100)=Neto
                BigDecimal coeficienteIVA = BigDecimal.ONE.add(alicuota.getValorAlicuota().divide(new BigDecimal(100)));
                BigDecimal netoGravado = vl.getSubTotal().divide(coeficienteIVA, 2, RoundingMode.HALF_UP);
                BigDecimal importeIva = vl.getSubTotal().subtract(netoGravado);
                importeIva = importeIva.setScale(2, RoundingMode.HALF_UP);

                if (importe.containsKey(alicuota)) {
                    importeIva = importeIva.add(importe.get(alicuota));
                }
                importe.put(alicuota, importeIva);
            }

        }
        for (Map.Entry<FiscalAlicuotasIva, BigDecimal> i : importe.entrySet()) {
            ImportesAlicuotasIVA imp = new ImportesAlicuotasIVA(i.getKey(), i.getValue());
            importesIVA.add(imp);
        }

    }
    */ 
}
