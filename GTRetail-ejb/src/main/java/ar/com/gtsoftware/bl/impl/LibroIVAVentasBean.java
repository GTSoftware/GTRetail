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
package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.eao.FiscalLibroIvaVentasFacade;
import ar.com.gtsoftware.eao.FiscalLibroIvaVentasLineasFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.FiscalLibroIvaVentasLineas;
import ar.com.gtsoftware.model.dto.FacturaDTO;
import ar.com.gtsoftware.model.dto.ImportesAlicuotasIVA;
import ar.com.gtsoftware.model.dto.ImportesResponsabilidad;
import ar.com.gtsoftware.model.dto.LibroIVADTO;
import ar.com.gtsoftware.search.IVAVentasSearchFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @EJB
    private FiscalLibroIvaVentasLineasFacade ivaVentasLineasFacade;

    /**
     * Genera el libro de IVA ventas para el período establecido en el filter
     *
     * @param filter
     * @return el libro de IVA
     * @throws ServiceException
     */
    public LibroIVADTO obtenerLibroIVA(IVAVentasSearchFilter filter) throws ServiceException {
        LibroIVADTO libro;
        if (!filter.hasFilter()) {

            throw new ServiceException("Filtro vacío!");

        }

        if (filter.hasFechasDesdeHasta()) {
            libro = new LibroIVADTO(filter.getFechaDesde(), filter.getFechaHasta());
        } else {
            libro = new LibroIVADTO(filter.getPeriodo().getFechaInicioPeriodo(),
                    filter.getPeriodo().getFechaFinPeriodo());
        }
        List<FiscalLibroIvaVentas> facturas = ivaVentasFacade.findBySearchFilter(filter);

        List<FacturaDTO> facturasDTOList = new ArrayList<>();
        BigDecimal importeGeneralTotal = BigDecimal.ZERO;
        BigDecimal totalGeneralIVA = BigDecimal.ZERO;
        List<ImportesResponsabilidad> totalesResponsabildiad = new ArrayList<>();
        List<ImportesAlicuotasIVA> totalAlicuotasIVAGeneral = new ArrayList<>();
        for (FiscalLibroIvaVentas factura : facturas) {
            Set<ImportesAlicuotasIVA> importesIVA = new HashSet<>();

            FacturaDTO facDTO = inicializarFacturaDTO(factura);

            importeGeneralTotal = importeGeneralTotal.add(facDTO.getTotalFactura());
            //HashMap<FiscalAlicuotasIva, BigDecimal> ivaMap = new HashMap<>();
            for (FiscalLibroIvaVentasLineas linea : ivaVentasLineasFacade.getLineasFactura(factura)) {
                facDTO.setNetoGravado(facDTO.getNetoGravado().add(linea.getNetoGravado()));
                facDTO.setNoGravado(facDTO.getNoGravado().add(linea.getNoGravado()));
                facDTO.setTotalIva(facDTO.getTotalIva().add(linea.getImporteIva()));
                FiscalAlicuotasIva alicuota = linea.getIdAlicuotaIva();

                ImportesAlicuotasIVA importeIva = new ImportesAlicuotasIVA(alicuota, linea.getImporteIva(), linea.getNetoGravado());
                if (!importesIVA.add(importeIva)) {
                    for (ImportesAlicuotasIVA imp : importesIVA) {
                        if (imp.getAlicuota().equals(linea.getIdAlicuotaIva())) {
                            imp.setImporteIva(imp.getImporteIva().add(linea.getImporteIva()));
                            imp.setNetoGravado(imp.getNetoGravado().add(linea.getNetoGravado()));
                        }
                    }
                }

            }
            ArrayList<ImportesAlicuotasIVA> alicuotasIvaList = new ArrayList<>(importesIVA);
            facDTO.setTotalAlicuota(alicuotasIvaList);

            totalGeneralIVA = totalGeneralIVA.add(facDTO.getTotalIva());
            facturasDTOList.add(facDTO);
            ImportesResponsabilidad acumuladorResponsabilidadFactura = new ImportesResponsabilidad(factura.getIdResponsabilidadIva(),
                    facDTO.getTotalFactura(),
                    facDTO.getTotalIva(),
                    facDTO.getNetoGravado(),
                    facDTO.getNoGravado());

            if (totalesResponsabildiad.contains(acumuladorResponsabilidadFactura)) {
                ImportesResponsabilidad importeRespExistente = totalesResponsabildiad.get(totalesResponsabildiad.indexOf(acumuladorResponsabilidadFactura));
                importeRespExistente.setImporteTotal(importeRespExistente.getImporteTotal().add(acumuladorResponsabilidadFactura.getImporteTotal()));
                importeRespExistente.setIvaTotal(importeRespExistente.getIvaTotal().add(acumuladorResponsabilidadFactura.getIvaTotal()));
                importeRespExistente.setNetoGravadoTotal(importeRespExistente.getNetoGravadoTotal().add(acumuladorResponsabilidadFactura.getNetoGravadoTotal()));
                importeRespExistente.setNoGravadoTotal(importeRespExistente.getNoGravadoTotal().add(acumuladorResponsabilidadFactura.getNoGravadoTotal()));

            } else {
                totalesResponsabildiad.add(acumuladorResponsabilidadFactura);
            }

            //Totalizar IVA por alicuota general
            totalizarAlicuotasIVAGeneral(totalAlicuotasIVAGeneral, alicuotasIvaList);
        }

        libro.setImporteTotal(importeGeneralTotal);
        libro.setImporteTotalIVA(totalGeneralIVA);
        libro.setTotalesIVAResponsabilidad(totalesResponsabildiad);
        libro.setTotalesAlicuota(totalAlicuotasIVAGeneral);
        libro.setFacturasList(facturasDTOList);
        return libro;
    }

    /**
     * Inicializa el DTO de factura
     *
     * @param facDTO
     * @param factura
     */
    private FacturaDTO inicializarFacturaDTO(FiscalLibroIvaVentas factura) {
        FacturaDTO facDTO = new FacturaDTO();
        facDTO.setDocumentoCliente(factura.getDocumento());
        facDTO.setFechaFactura(factura.getFechaFactura());
        facDTO.setIdFactura(factura.getId());
        facDTO.setRazonSocialCliente(factura.getIdPersona().getRazonSocial());
        if (factura.getAnulada()) {
            facDTO.setRazonSocialCliente("NULA");
        }
        facDTO.setNumeroFactura(factura.getLetraFactura().concat(" ")
                .concat(factura.getPuntoVentaFactura()).concat("-")
                .concat(factura.getNumeroFactura()));
        facDTO.setNetoGravado(BigDecimal.ZERO);
        facDTO.setNoGravado(BigDecimal.ZERO);
        facDTO.setTotalFactura(factura.getTotalFactura());
        facDTO.setTotalIva(BigDecimal.ZERO);
        return facDTO;
    }

    /**
     * Genera la lista totalizadora general de importes de iva por alícuota
     *
     * @param totalAlicuotasIVAGeneral
     * @param totalAlicuotasIVAFactura
     */
    private void totalizarAlicuotasIVAGeneral(List<ImportesAlicuotasIVA> totalAlicuotasIVAGeneral,
            List<ImportesAlicuotasIVA> totalAlicuotasIVAFactura) {
        if (totalAlicuotasIVAGeneral.isEmpty()) {
            totalAlicuotasIVAGeneral.addAll(totalAlicuotasIVAFactura);
        } else {
            for (ImportesAlicuotasIVA ivaFactrura : totalAlicuotasIVAFactura) {
                if (totalAlicuotasIVAGeneral.contains(ivaFactrura)) {
                    ImportesAlicuotasIVA existente = totalAlicuotasIVAGeneral.get(totalAlicuotasIVAGeneral.indexOf(ivaFactrura));
                    existente.setImporteIva(existente.getImporteIva().add(ivaFactrura.getImporteIva()));
                } else {
                    ImportesAlicuotasIVA nuevoIva = new ImportesAlicuotasIVA(ivaFactrura.getAlicuota(), ivaFactrura.getImporteIva(), ivaFactrura.getNetoGravado());
                    totalAlicuotasIVAGeneral.add(nuevoIva);
                }
            }
        }

    }

}
