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

package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.bl.OfertasService;
import ar.com.gtsoftware.dto.model.ComprobantesDto;
import ar.com.gtsoftware.dto.model.ComprobantesLineasDto;
import ar.com.gtsoftware.rules.OfertaDto;
import ar.com.gtsoftware.search.OfertasSearchFilter;
import ar.com.gtsoftware.utils.DroolsUtility;
import org.kie.api.runtime.StatelessKieSession;

import java.util.List;

/**
 * Clase de soporte para la implementación de reglas de ofertas
 */
public class OfertasHelper {

    private static final String DROOLS_TEMPLATES_PRODUCT_DRT = "ar/com/gtsoftware/drools/templates/Product.drt";
    private final OfertasService ofertasService;
    private final ShopCartBean shopCart;
    private final OfertasSearchFilter filter = OfertasSearchFilter.builder().activas(true).build();

    private StatelessKieSession session;

    public OfertasHelper(OfertasService service, ShopCartBean bean) {
        this.ofertasService = service;
        this.shopCart = bean;
    }


    private void inicializarReglas() throws Exception {
        ComprobantesDto venta = shopCart.getVenta();
        filter.setIdSucursal(venta.getIdSucursal().getId());

        List<OfertaDto> ofertasList = ofertasService.findAllBySearchFilter(filter);
        session = DroolsUtility.loadSession(ofertasList, DROOLS_TEMPLATES_PRODUCT_DRT);
        session.setGlobal("shopCart", shopCart);

    }

    public void ejecutarReglasOferta(ComprobantesLineasDto linea) {
        if (session == null) {
            try {
                inicializarReglas();
            } catch (Exception e) {
                throw new RuntimeException("Imposible inicializar el contexto de reglas.", e);
            }
        }

        session.execute(linea);
    }
}
