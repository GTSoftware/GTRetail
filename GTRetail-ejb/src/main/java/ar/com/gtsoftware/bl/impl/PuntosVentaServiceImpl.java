/*
 * Copyright 2016 GT Software.
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

import ar.com.gtsoftware.eao.FiscalPuntosVentaFacade;
import ar.com.gtsoftware.eao.SucursalesFacade;
import ar.com.gtsoftware.model.FiscalPuntosVenta;
import ar.com.gtsoftware.model.Sucursales;
import ar.com.gtsoftware.model.enums.TiposPuntosVenta;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;
import ar.com.gtsoftware.service.rest.PuntosVentaEndpoint;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Stateless
public class PuntosVentaServiceImpl implements PuntosVentaEndpoint {

    @EJB
    private FiscalPuntosVentaFacade facade;

    @EJB
    private SucursalesFacade sucursalesFacade;

    @Override
    public List<FiscalPuntosVenta> getPuntosVentaActivos(Long idSucursal) {
        if (idSucursal == null) {
            return Collections.EMPTY_LIST;
        }
        Sucursales sucursal = sucursalesFacade.find(idSucursal);
        if (sucursal == null) {
            return Collections.EMPTY_LIST;
        }
        FiscalPuntosVentaSearchFilter sf = new FiscalPuntosVentaSearchFilter(sucursal, Boolean.TRUE);
        sf.setTipoPuntoVenta(TiposPuntosVenta.CONTROLADOR_FISCAL);

        return facade.findAllBySearchFilter(sf);

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
