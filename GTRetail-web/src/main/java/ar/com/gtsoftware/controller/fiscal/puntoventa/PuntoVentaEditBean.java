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
package ar.com.gtsoftware.controller.fiscal.puntoventa;

import ar.com.gtsoftware.eao.FiscalPuntosVentaFacade;
import ar.com.gtsoftware.eao.SucursalesFacade;
import ar.com.gtsoftware.model.FiscalPuntosVenta;
import ar.com.gtsoftware.model.Sucursales;
import ar.com.gtsoftware.model.enums.TiposPuntosVenta;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.search.SucursalesSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit Bean para puntos de venta
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "puntoVentaEditBean")
@ViewScoped
public class PuntoVentaEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(PuntoVentaEditBean.class.getName());

    @EJB
    private FiscalPuntosVentaFacade facade;
    @EJB
    private SucursalesFacade sucursalesFacade;

    private FiscalPuntosVenta puntoVentaActual = null;

    private final List<Sucursales> sucursalesList = new ArrayList<>();

    /**
     * Creates a new instance of PuntoVentaEditBean
     */
    public PuntoVentaEditBean() {
    }

    @PostConstruct
    private void init() {
        SucursalesSearchFilter sf = new SucursalesSearchFilter(Boolean.TRUE);
        sucursalesList.addAll(sucursalesFacade.findAllBySearchFilter(sf));

        String nroPuntoVenta = JSFUtil.getRequestParameterMap().get("nroPuntoVenta");

        if (nroPuntoVenta == null) {
            nuevo();
        } else {
            puntoVentaActual = facade.find(Integer.parseInt(nroPuntoVenta));

            if (nroPuntoVenta == null) {
                LOG.log(Level.SEVERE, "Punto de venta inexistente!");
                throw new IllegalArgumentException("Punto de venta inexistente!");

            }

        }
    }

    public List<Sucursales> getSucursalesList() {
        return sucursalesList;
    }

    public FiscalPuntosVenta getPuntoVentaActual() {
        return puntoVentaActual;
    }

    private void nuevo() {
        FiscalPuntosVentaSearchFilter sf = new FiscalPuntosVentaSearchFilter(null, null);
        sf.addSortField(new SortField("nroPuntoVenta", false));
        FiscalPuntosVenta lastPv = facade.findFirstBySearchFilter(sf);

        puntoVentaActual = new FiscalPuntosVenta();
        puntoVentaActual.setActivo(true);
        if (lastPv != null) {
            puntoVentaActual.setNroPuntoVenta(lastPv.getNroPuntoVenta() + 1);
        }
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(puntoVentaActual);
            JSFUtil.addInfoMessage("Punto de venta guardado exitosamente");
            puntoVentaActual = facade.find(puntoVentaActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public TiposPuntosVenta[] getTiposPuntosVenta() {
        return TiposPuntosVenta.values();
    }
}
