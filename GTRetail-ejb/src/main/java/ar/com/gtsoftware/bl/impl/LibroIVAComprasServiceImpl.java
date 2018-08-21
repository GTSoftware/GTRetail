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
package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.eao.FiscalLibroIvaComprasFacade;
import ar.com.gtsoftware.eao.FiscalLibroIvaComprasLineasFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalLibroIvaCompras;
import ar.com.gtsoftware.model.FiscalLibroIvaComprasLineas;
import ar.com.gtsoftware.model.dto.ImportesAlicuotasIVA;
import ar.com.gtsoftware.model.dto.ImportesResponsabilidad;
import ar.com.gtsoftware.model.dto.LibroIVADTO;
import ar.com.gtsoftware.model.dto.RegistroIVADTO;
import ar.com.gtsoftware.search.LibroIVASearchFilter;
import ar.com.gtsoftware.service.fiscal.LibroIVAService;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Servicio para generar el libro de IVA compras
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 1.0.1
 */
@Stateless
@LocalBean
public class LibroIVAComprasServiceImpl implements LibroIVAService {

    @EJB
    private FiscalLibroIvaComprasFacade ivaComprasFacade;
    @EJB
    private FiscalLibroIvaComprasLineasFacade ivaComprasLineasFacade;

    private static final String NUMERO_FACTURA_FMT = "%s %s-%s";

    /**
     * Genera el libro de IVA compras para el período establecido en el filter
     *
     * @param filter
     * @return el libro de IVA
     * @throws ServiceException
     */
    @Override
    public LibroIVADTO obtenerLibroIVA(LibroIVASearchFilter filter) throws ServiceException {
        LibroIVADTO libro;
        if (!filter.hasFilter()) {

            throw new ServiceException("Filtro vacío!");

        }

        if (filter.hasFechasDesdeHasta()) {
            libro = LibroIVADTO.builder()
                    .fechaDesde(filter.getFechaDesde())
                    .fechaHasta(filter.getFechaHasta()).build();
        } else {
            libro = LibroIVADTO.builder()
                    .fechaDesde(filter.getPeriodo().getFechaInicioPeriodo())
                    .fechaHasta(filter.getPeriodo().getFechaFinPeriodo()).build();
        }
        List<FiscalLibroIvaCompras> facturas = ivaComprasFacade.findAllBySearchFilter(filter);

        List<RegistroIVADTO> facturasDTOList = new ArrayList<>();
        BigDecimal importeGeneralTotal = BigDecimal.ZERO;
        BigDecimal totalGeneralIVA = BigDecimal.ZERO;
        List<ImportesResponsabilidad> totalesResponsabildiad = new ArrayList<>();
        List<ImportesAlicuotasIVA> totalAlicuotasIVAGeneral = new ArrayList<>();
        for (FiscalLibroIvaCompras factura : facturas) {
            Set<ImportesAlicuotasIVA> importesIVA = new HashSet<>();

            RegistroIVADTO facDTO = inicializarRegistroDTO(factura);

            importeGeneralTotal = importeGeneralTotal.add(facDTO.getTotalFactura());
            //TODO esto se mantiene por motivos de compatibilidad con registros viejos...
            for (FiscalLibroIvaComprasLineas linea : ivaComprasLineasFacade.getLineasFactura(factura)) {
                facDTO.setNetoGravado(facDTO.getNetoGravado().add(linea.getNetoGravado()));
                facDTO.setNoGravado(facDTO.getNoGravado().add(linea.getNoGravado()));
                facDTO.setTotalIva(facDTO.getTotalIva().add(linea.getImporteIva()));
                FiscalAlicuotasIva alicuota = linea.getIdAlicuotaIva();

                ImportesAlicuotasIVA importeIva = new ImportesAlicuotasIVA(alicuota, linea.getImporteIva(), linea.getNetoGravado());
                importesIVA.add(importeIva);
                /*if (!importesIVA.add(importeIva)) {
                    for (ImportesAlicuotasIVA imp : importesIVA) {
                        if (imp.getAlicuota().equals(linea.getIdAlicuotaIva())) {
                            imp.setImporteIva(imp.getImporteIva().add(linea.getImporteIva()));
                            imp.setNetoGravado(imp.getNetoGravado().add(linea.getNetoGravado()));
                        }
                    }
                }*/

            }
            ArrayList<ImportesAlicuotasIVA> alicuotasIvaList = new ArrayList<>(importesIVA);
            facDTO.setTotalAlicuota(alicuotasIvaList);

            totalGeneralIVA = totalGeneralIVA.add(facDTO.getTotalIva());
            facturasDTOList.add(facDTO);
            ImportesResponsabilidad acumuladorResponsabilidadFactura = ImportesResponsabilidad.builder()
                    .responsabilidadIva(factura.getIdResponsabilidadIva())
                    .importeTotal(facDTO.getTotalFactura())
                    .ivaTotal(facDTO.getTotalIva())
                    .netoGravadoTotal(facDTO.getNetoGravado())
                    .noGravadoTotal(facDTO.getNoGravado()).build();

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
     * @param factura
     */
    private RegistroIVADTO inicializarRegistroDTO(FiscalLibroIvaCompras factura) {
        RegistroIVADTO facDTO = new RegistroIVADTO();
        facDTO.setDocumentoCliente(factura.getDocumento());
        facDTO.setFechaFactura(factura.getFechaFactura());
        facDTO.setIdFactura(factura.getId());
        facDTO.setRazonSocialCliente(factura.getIdPersona().getRazonSocial());
        facDTO.setTipoDocumento(factura.getIdPersona().getIdTipoDocumento().getNombreTipoDocumento());
        facDTO.setTipoComprobante(factura.getCodigoTipoComprobante().getDenominacionComprobante());
        facDTO.setCategoriaIVACliente(factura.getIdPersona().getIdResponsabilidadIva().getNombreResponsabildiad());
        if (factura.getAnulada()) {
            facDTO.setRazonSocialCliente("NULA");
        }
        facDTO.setNumeroFactura(String.format(NUMERO_FACTURA_FMT, factura.getLetraFactura(), factura.getPuntoVentaFactura(),
                factura.getNumeroFactura()));
        facDTO.setNetoGravado(BigDecimal.ZERO);
        facDTO.setNoGravado(BigDecimal.ZERO);
        facDTO.setTotalFactura(factura.getTotalFactura());
        facDTO.setTotalIva(BigDecimal.ZERO);
        facDTO.setOtrosTributos(factura.getImporteTributos());
        facDTO.setExento(factura.getImporteExento());
        facDTO.setPercepcionIva(factura.getImportePercepcionIva());
        facDTO.setPercepcionIngresosBrutos(factura.getImportePercepcionIngresosBrutos());
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
