/*
 * Copyright 2015 GT Software.
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
package ar.com.gtsoftware.bl.impl.fiscal;

import ar.com.gtsoftware.bl.exceptions.ServiceException;
import ar.com.gtsoftware.bl.fiscal.RegInfoCvVentasAlicuotas;
import ar.com.gtsoftware.bl.fiscal.RegimenInformativoDTO;
import ar.com.gtsoftware.bl.fiscal.RegimenInformativoVentasService;
import ar.com.gtsoftware.bl.fiscal.ReginfoCvCabecera;
import ar.com.gtsoftware.bl.fiscal.ReginfoCvVentasCbte;
import ar.com.gtsoftware.eao.FiscalLibroIvaVentasFacade;
import ar.com.gtsoftware.eao.FiscalLibroIvaVentasLineasFacade;
import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalLibroIvaVentas;
import ar.com.gtsoftware.model.FiscalLibroIvaVentasLineas;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.dto.ImportesAlicuotasIVA;
import ar.com.gtsoftware.search.IVAVentasSearchFilter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Stateless
public class RegimenInformativoVentasServiceImpl implements RegimenInformativoVentasService {

    @EJB
    private ParametrosFacade parametrosFacade;

    @EJB
    private FiscalLibroIvaVentasFacade ivaVentasFacade;
    @EJB
    private FiscalLibroIvaVentasLineasFacade ivaVentasLineasFacade;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    private static final BigDecimal MONTO_MAXIMO_SIN_IDENTIFICAR = new BigDecimal(1000);
    private static final int CODIGO_DOCUMENTO_SIN_IDENTIFICAR = 99;

    @Override
    public RegimenInformativoDTO generarRegimenInformativo(FiscalPeriodosFiscales fiscalPeriodosFiscales) throws ServiceException {

        //TODO tomar los datos de secuencia, IVA prorrateado y demás como parámetros
        if (fiscalPeriodosFiscales == null) {

            throw new ServiceException("Filtro vacío!");

        }
        String periodoIni = sdf.format(fiscalPeriodosFiscales.getFechaInicioPeriodo());
        String PeriodoFin = sdf.format(fiscalPeriodosFiscales.getFechaFinPeriodo());
        if (!periodoIni.equalsIgnoreCase(PeriodoFin)) {
            throw new ServiceException("El período debe ser de un solo mes/año.");

        }

        IVAVentasSearchFilter filter = new IVAVentasSearchFilter();
        filter.setPeriodo(fiscalPeriodosFiscales);
        filter.setAnuladas(Boolean.FALSE);//Sin anuladas

        ReginfoCvCabecera cabecera = new ReginfoCvCabecera();
        Calendar c = Calendar.getInstance();
        c.setTime(fiscalPeriodosFiscales.getFechaInicioPeriodo());
        cabecera.setAnioPeriodo(c.get(Calendar.YEAR));
        cabecera.setMesPeriodo(c.get(Calendar.MONTH) + 1);
        cabecera.setCuitInformante(Long.parseLong(parametrosFacade.findParametroByName("empresa.cuit").getValorParametro()));
        cabecera.setSecuencia(0);
        cabecera.setCreditoFiscalComputableGlobalOPorComprobante(ReginfoCvCabecera.POR_COMPROBANTE);
        cabecera.setImporteCreditoFiscalComputableContribSegSocyOtros(BigDecimal.ZERO);
        cabecera.setProrratearCreditoFiscalComputable(ReginfoCvCabecera.NO);

        List<FiscalLibroIvaVentas> facturas = ivaVentasFacade.findBySearchFilter(filter);
        cabecera.setSinMovimiento(facturas.isEmpty() ? ReginfoCvCabecera.SI : ReginfoCvCabecera.NO);

        List<ReginfoCvVentasCbte> regVentas = new ArrayList<>();
        List<RegInfoCvVentasAlicuotas> regAlicuotas = new ArrayList<>();

        for (FiscalLibroIvaVentas factura : facturas) {
            Set<ImportesAlicuotasIVA> importesIVA = new HashSet<>();

            ReginfoCvVentasCbte comprobante = new ReginfoCvVentasCbte();
            comprobante.setCodigoMoneda("PES");//Sacara de tabla, Agregar en la tabla de ventas
            comprobante.setTipoCambio(BigDecimal.ONE);//1 Por defecto
            comprobante.setCodigoDocumentoComprador(factura.getIdPersona().getIdTipoDocumento().getFiscalCodigoTipoDocumento());
            if (factura.getTotalFactura().compareTo(MONTO_MAXIMO_SIN_IDENTIFICAR) < 0) {
                comprobante.setCodigoDocumentoComprador(CODIGO_DOCUMENTO_SIN_IDENTIFICAR);
            }

            comprobante.setDenominacionComprador(factura.getIdPersona().getRazonSocial());
            comprobante.setNumeroIdentificacionComprador(Long.parseLong(factura.getIdPersona().getDocumento()));

            comprobante.setFechaComprobante(factura.getFechaFactura());
            comprobante.setFechaVencimientoPago(factura.getFechaFactura());
            comprobante.setImporteDePercepcionesDeImpuestosMunicipales(BigDecimal.ZERO);
            comprobante.setImporteDePercepcionesDeIngresosBrutos(BigDecimal.ZERO);//Parametrizar según empresa
            comprobante.setImporteDePercepcionesOPagosAcuentaDeImpuestosNacionales(BigDecimal.ZERO);//Ni puta idea
            comprobante.setImporteOperacionesExentas(BigDecimal.ZERO);//No se de nadie que venda productos sin IVA
            comprobante.setImporteTotalConceptosNoIntegranPrecioNetoGravado(BigDecimal.ZERO);
            comprobante.setImporteTotalOperacion(factura.getTotalFactura());
            comprobante.setNumeroComprobante(Long.parseLong(factura.getNumeroFactura()));
            comprobante.setPuntoVenta(Integer.parseInt(factura.getPuntoVentaFactura()));
            comprobante.setNumeroComprobanteHasta(comprobante.getNumeroComprobante());

            comprobante.setOtrosTributos(BigDecimal.ZERO);
            comprobante.setPercepcionANoCategorizados(BigDecimal.ZERO);
            comprobante.setTipoComprobante(Integer.parseInt(factura.getCodigoTipoComprobante().getCodigoTipoComprobante()));

            for (FiscalLibroIvaVentasLineas linea : ivaVentasLineasFacade.getLineasFactura(factura)) {

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
            comprobante.setCantidadAlicuotasIVA(importesIVA.size());

            for (ImportesAlicuotasIVA imp : importesIVA) {

                RegInfoCvVentasAlicuotas alicuotaComprobante = new RegInfoCvVentasAlicuotas();
                alicuotaComprobante.setAlicuota(imp.getAlicuota().getFiscalCodigoAlicuota());
                alicuotaComprobante.setImporteNetoGravado(imp.getNetoGravado());
                alicuotaComprobante.setImpuestoLiquidado(imp.getImporteIva());

                alicuotaComprobante.setNumeroComprobante(comprobante.getNumeroComprobante());
                alicuotaComprobante.setPuntoVenta(comprobante.getPuntoVenta());
                alicuotaComprobante.setTipoComprobante(comprobante.getTipoComprobante());

                regAlicuotas.add(alicuotaComprobante);
            }
            regVentas.add(comprobante);

        }
        RegimenInformativoDTO dto = new RegimenInformativoDTO();
        dto.setCabecera(cabecera);
        dto.setVentasAlicuotasList(regAlicuotas);
        dto.setVentasList(regVentas);
        return dto;
    }

}
