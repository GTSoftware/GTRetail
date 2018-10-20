/*
 * Copyright 2018 GT Software.
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

import ar.com.gtsoftware.bl.FiscalPuntosVentaService;
import ar.com.gtsoftware.bl.SucursalesService;
import ar.com.gtsoftware.dto.model.FiscalPuntosVentaDto;
import ar.com.gtsoftware.dto.model.SucursalesDto;
import ar.com.gtsoftware.enums.TiposPuntosVenta;
import ar.com.gtsoftware.search.FiscalPuntosVentaSearchFilter;
import ar.com.gtsoftware.search.SucursalesSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final List<SucursalesDto> sucursalesList = new ArrayList<>();
    @EJB
    private FiscalPuntosVentaService service;
    @EJB
    private SucursalesService sucursalesService;
    private FiscalPuntosVentaDto puntoVentaActual = null;

    public PuntoVentaEditBean() {
    }

    @PostConstruct
    private void init() {
        SucursalesSearchFilter sf = SucursalesSearchFilter.builder()
                .activa(true).build();
        sucursalesList.addAll(sucursalesService.findAllBySearchFilter(sf));

        String nroPuntoVenta = JSFUtil.getRequestParameterMap().get("nroPuntoVenta");

        if (nroPuntoVenta == null) {
            nuevo();
        } else {
            puntoVentaActual = service.find(Integer.parseInt(nroPuntoVenta));

            if (puntoVentaActual == null) {
                LOG.log(Level.SEVERE, "Punto de venta inexistente!");
                throw new IllegalArgumentException("Punto de venta inexistente!");

            }

        }
    }

    public List<SucursalesDto> getSucursalesList() {
        return sucursalesList;
    }

    public FiscalPuntosVentaDto getPuntoVentaActual() {
        return puntoVentaActual;
    }

    private void nuevo() {
        FiscalPuntosVentaSearchFilter sf = new FiscalPuntosVentaSearchFilter();
        sf.addSortField("nroPuntoVenta", false);
        FiscalPuntosVentaDto lastPv = service.findFirstBySearchFilter(sf);

        puntoVentaActual = new FiscalPuntosVentaDto();
        puntoVentaActual.setActivo(true);
        if (lastPv != null) {
            puntoVentaActual.setNroPuntoVenta(lastPv.getNroPuntoVenta() + 1);
        }
    }

    public void doGuardar() {
        try {

            puntoVentaActual = service.createOrEdit(puntoVentaActual);
            JSFUtil.addInfoMessage("Punto de venta guardado exitosamente");
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public TiposPuntosVenta[] getTiposPuntosVenta() {
        return TiposPuntosVenta.values();
    }
}
