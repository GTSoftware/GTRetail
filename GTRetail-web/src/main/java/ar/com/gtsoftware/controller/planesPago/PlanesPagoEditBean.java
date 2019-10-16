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
package ar.com.gtsoftware.controller.planesPago;

import ar.com.gtsoftware.bl.NegocioFormasPagoService;
import ar.com.gtsoftware.bl.NegocioPlanesPagoService;
import ar.com.gtsoftware.bl.ProductosListasPreciosService;
import ar.com.gtsoftware.dto.model.NegocioFormasPagoDto;
import ar.com.gtsoftware.dto.model.NegocioPlanesPagoDetalleDto;
import ar.com.gtsoftware.dto.model.NegocioPlanesPagoDto;
import ar.com.gtsoftware.dto.model.ProductosListasPreciosDto;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.ProductosListasPreciosSearchFilter;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Edit Bean para planes de pago
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "planesPagoEditBean")
@ViewScoped
public class PlanesPagoEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(PlanesPagoEditBean.class.getName());
    private final List<NegocioFormasPagoDto> formasPagoList = new ArrayList<>();
    private final List<ProductosListasPreciosDto> listasPrecio = new ArrayList<>();
    @EJB
    private NegocioPlanesPagoService planesPagoService;
    @EJB
    private NegocioFormasPagoService formasPagoFacade;
    @EJB
    private ProductosListasPreciosService listasPreciosFacade;
    @Inject
    private JSFHelper jsfHelper;
    private NegocioPlanesPagoDto planPagoActual = null;

    /**
     * Creates a new instance of PlanesPagoEditBean
     */
    public PlanesPagoEditBean() {
    }

    @PostConstruct
    public void init() {

        String idPlan = jsfHelper.getRequestParameterMap().get("idPlanPago");

        if (idPlan == null) {
            nuevo();
        } else {
            planPagoActual = planesPagoService.find(Long.parseLong(idPlan));

            if (planPagoActual == null) {
                LOG.log(Level.SEVERE, "Forma de pago inexistente!");
                throw new IllegalArgumentException("Forma de pago inexistente!");

            }

        }
        loadFormasPagoList();
        loadListasPrecioList();

    }

    private void nuevo() {
        planPagoActual = new NegocioPlanesPagoDto();
        planPagoActual.setNegocioPlanesPagoDetalles(new ArrayList<>(6));
    }

    private void loadFormasPagoList() {
        formasPagoList.clear();
        FormasPagoSearchFilter sf = FormasPagoSearchFilter.builder()
                .requierePlanes(true).build();
        sf.addSortField("nombreFormaPago", true);
        formasPagoList.addAll(formasPagoFacade.findAllBySearchFilter(sf));
    }

    public void doGuardar() {
        try {

            planPagoActual = planesPagoService.createOrEdit(planPagoActual);
            jsfHelper.addInfoMessage("Plan de pago guardado Exitosamente");
            planPagoActual = planesPagoService.find(planPagoActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            jsfHelper.addErrorMessage("Error al guardar");
        }

    }

    public NegocioPlanesPagoDto getPlanPagoActual() {
        return planPagoActual;
    }

    private void loadListasPrecioList() {
        listasPrecio.clear();
        ProductosListasPreciosSearchFilter sf = new ProductosListasPreciosSearchFilter();
        sf.setActiva(Boolean.TRUE);
        listasPrecio.addAll(listasPreciosFacade.findAllBySearchFilter(sf));
    }

    public List<NegocioFormasPagoDto> getFormasPagoList() {
        return formasPagoList;
    }

    public List<ProductosListasPreciosDto> getListasPrecio() {
        return listasPrecio;
    }

    public void agregarDetallePlan() {
        NegocioPlanesPagoDetalleDto newDet = NegocioPlanesPagoDetalleDto.builder()
                .activo(true)
                .idPlan(planPagoActual).build();

        if (CollectionUtils.isEmpty(planPagoActual.getNegocioPlanesPagoDetalles())) {
            planPagoActual.setNegocioPlanesPagoDetalles(new ArrayList<>(6));
            newDet.setCuotas(1);
        } else {
            newDet.setCuotas(getMaximaCuota() + 1);
        }

        planPagoActual.getNegocioPlanesPagoDetalles().add(newDet);
    }

    public void borrarDetallePlan(NegocioPlanesPagoDetalleDto det) {
        planPagoActual.getNegocioPlanesPagoDetalles().remove(det);
    }

    private int getMaximaCuota() {
        NegocioPlanesPagoDetalleDto cuotaMax = planPagoActual.getNegocioPlanesPagoDetalles().stream().max(Comparator.comparingInt(i -> i.getCuotas())
        ).get();

        return cuotaMax.getCuotas();
    }
}
