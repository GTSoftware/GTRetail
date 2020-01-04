/*
 * Copyright 2020 GT Software.
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
import ar.com.gtsoftware.dto.model.ProveedoresComprobantesDto;
import ar.com.gtsoftware.eao.ComprobantesProveedorFacade;
import ar.com.gtsoftware.eao.FiscalLibroIvaComprasFacade;
import ar.com.gtsoftware.eao.FiscalTiposComprobanteFacade;
import ar.com.gtsoftware.mappers.ProveedoresComprobantesMapper;
import ar.com.gtsoftware.model.FiscalLibroIvaCompras;
import ar.com.gtsoftware.model.FiscalPeriodosFiscales;
import ar.com.gtsoftware.model.ProveedoresComprobantes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComprobantesProveedorServiceImplTest {

    @Mock
    private ComprobantesProveedorFacade facade;
    @Mock
    private FiscalTiposComprobanteFacade tiposComprobanteFacade;
    @Mock
    private FiscalLibroIvaComprasFacade ivaComprasFacade;

    @Inject
    private ProveedoresComprobantesMapper mapper;

    @InjectMocks
    private ComprobantesProveedorServiceImpl service = new ComprobantesProveedorServiceImpl();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldDeleteSupplierTicketAndPurchaseFiscalRecordWhenUnclosedFiscalPeriod() throws ServiceException {
        ProveedoresComprobantesDto ticket = ProveedoresComprobantesDto.builder().id(1L).build();
        ProveedoresComprobantes supplierTicketEntity = new ProveedoresComprobantes();
        FiscalLibroIvaCompras fiscalPurchasesBook = new FiscalLibroIvaCompras();
        FiscalPeriodosFiscales unclosedFiscalPeriod = new FiscalPeriodosFiscales();
        unclosedFiscalPeriod.setPeriodoCerrado(false);
        fiscalPurchasesBook.setIdPeriodoFiscal(unclosedFiscalPeriod);
        fiscalPurchasesBook.setId(5L);
        supplierTicketEntity.setIdRegistro(fiscalPurchasesBook);

        when(facade.find(1L)).thenReturn(supplierTicketEntity);

        service.eliminarComprobante(ticket);

        verify(facade).find(1L);
        verify(facade).remove(supplierTicketEntity);
        verify(ivaComprasFacade).remove(fiscalPurchasesBook);
    }

    @Test
    public void shouldDeleteSupplierTicketWithNoFiscalPurchaseBookRecord() throws ServiceException {
        ProveedoresComprobantesDto ticket = ProveedoresComprobantesDto.builder().id(1L).build();
        ProveedoresComprobantes supplierTicketEntity = new ProveedoresComprobantes();

        when(facade.find(1L)).thenReturn(supplierTicketEntity);

        service.eliminarComprobante(ticket);

        verify(facade).find(1L);
        verify(facade).remove(supplierTicketEntity);
        verifyZeroInteractions(ivaComprasFacade);
    }

    @Test(expected = ServiceException.class)
    public void shouldNotDeleteSupplierTicketWhenClosedFiscalPeriod() throws ServiceException {
        ProveedoresComprobantesDto ticket = ProveedoresComprobantesDto.builder().id(1L).build();
        ProveedoresComprobantes supplierTicketEntity = new ProveedoresComprobantes();
        FiscalLibroIvaCompras fiscalPurchasesBook = new FiscalLibroIvaCompras();
        FiscalPeriodosFiscales unclosedFiscalPeriod = new FiscalPeriodosFiscales();
        unclosedFiscalPeriod.setPeriodoCerrado(true);
        fiscalPurchasesBook.setIdPeriodoFiscal(unclosedFiscalPeriod);
        fiscalPurchasesBook.setId(5L);
        supplierTicketEntity.setIdRegistro(fiscalPurchasesBook);

        when(facade.find(1L)).thenReturn(supplierTicketEntity);

        service.eliminarComprobante(ticket);

        verify(facade).find(1L);
        verifyNoMoreInteractions(facade);
        verifyZeroInteractions(ivaComprasFacade);
    }

}