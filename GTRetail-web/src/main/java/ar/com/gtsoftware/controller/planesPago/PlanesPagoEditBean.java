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

import ar.com.gtsoftware.eao.NegocioFormasPagoFacade;
import ar.com.gtsoftware.eao.NegocioPlanesPagoFacade;
import ar.com.gtsoftware.eao.ProductosListasPreciosFacade;
import ar.com.gtsoftware.model.NegocioFormasPago;
import ar.com.gtsoftware.model.NegocioPlanesPago;
import ar.com.gtsoftware.model.NegocioPlanesPagoDetalle;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.search.FormasPagoSearchFilter;
import ar.com.gtsoftware.search.ProductosListasPreciosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.collections.CollectionUtils;

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

    @EJB
    private NegocioPlanesPagoFacade facade;

    private NegocioPlanesPago planPagoActual = null;

    @EJB
    private NegocioFormasPagoFacade formasPagoFacade;

    @EJB
    private ProductosListasPreciosFacade listasPreciosFacade;
    @EJB
    private JSFUtil jsfUtil;

    private final List<NegocioFormasPago> formasPagoList = new ArrayList<>();
    private final List<ProductosListasPrecios> listasPrecio = new ArrayList<>();

    /**
     * Creates a new instance of PlanesPagoEditBean
     */
    public PlanesPagoEditBean() {
    }

    @PostConstruct
    public void init() {

        String idPlan = jsfUtil.getRequestParameterMap().get("idPlanPago");

        if (idPlan == null) {
            nuevo();
        } else {
            planPagoActual = facade.find(Long.parseLong(idPlan));

            if (planPagoActual == null) {
                LOG.log(Level.SEVERE, "Forma de pago inexistente!");
                throw new IllegalArgumentException("Forma de pago inexistente!");

            }

        }
        loadFormasPagoList();
        loadListasPrecioList();

    }

    private void nuevo() {
        planPagoActual = new NegocioPlanesPago();
        planPagoActual.setNegocioPlanesPagoDetalles(new ArrayList<>());
    }

    private void loadFormasPagoList() {
        formasPagoList.clear();
        FormasPagoSearchFilter sf = new FormasPagoSearchFilter();
        sf.setRequierePlanes(Boolean.TRUE);
        sf.addSortField(new SortField("nombreFormaPago", true));
        formasPagoList.addAll(formasPagoFacade.findAllBySearchFilter(sf));
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(planPagoActual);
            jsfUtil.addInfoMessage("Plan de pago guardado Exitosamente");
            planPagoActual = facade.find(planPagoActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            jsfUtil.addErrorMessage("Error al guardar");
        }

    }

    public NegocioPlanesPago getPlanPagoActual() {
        return planPagoActual;
    }

    private void loadListasPrecioList() {
        listasPrecio.clear();
        ProductosListasPreciosSearchFilter sf = new ProductosListasPreciosSearchFilter();
        sf.setActiva(Boolean.TRUE);
        listasPrecio.addAll(listasPreciosFacade.findAllBySearchFilter(sf));
    }

    public List<NegocioFormasPago> getFormasPagoList() {
        return formasPagoList;
    }

    public List<ProductosListasPrecios> getListasPrecio() {
        return listasPrecio;
    }

    public void agregarDetallePlan() {
        NegocioPlanesPagoDetalle newDet = new NegocioPlanesPagoDetalle();
        newDet.setActivo(true);
        newDet.setIdPlan(planPagoActual);

        if (CollectionUtils.isEmpty(planPagoActual.getNegocioPlanesPagoDetalles())) {
            planPagoActual.setNegocioPlanesPagoDetalles(new ArrayList<>());
            newDet.setCuotas(1);
        } else {
            newDet.setCuotas(getMaximaCuota() + 1);
        }

        planPagoActual.getNegocioPlanesPagoDetalles().add(newDet);
    }

    public void borrarDetallePlan(NegocioPlanesPagoDetalle det) {
        planPagoActual.getNegocioPlanesPagoDetalles().remove(det);
    }

    private int getMaximaCuota() {
        NegocioPlanesPagoDetalle cuotaMax = planPagoActual.getNegocioPlanesPagoDetalles().stream().max(Comparator.comparingInt(i -> i.getCuotas())
        ).get();
        if (cuotaMax == null) {
            return 0;
        }
        return cuotaMax.getCuotas();
    }
}
