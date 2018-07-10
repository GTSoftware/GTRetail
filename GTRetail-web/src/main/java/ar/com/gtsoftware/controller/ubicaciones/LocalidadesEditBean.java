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
package ar.com.gtsoftware.controller.ubicaciones;

import ar.com.gtsoftware.eao.UbicacionLocalidadesFacade;
import ar.com.gtsoftware.eao.UbicacionPaisesFacade;
import ar.com.gtsoftware.eao.UbicacionProvinciasFacade;
import ar.com.gtsoftware.model.UbicacionLocalidades;
import ar.com.gtsoftware.model.UbicacionPaises;
import ar.com.gtsoftware.model.UbicacionProvincias;
import ar.com.gtsoftware.search.PaisesSearchFilter;
import ar.com.gtsoftware.search.ProvinciasSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "localidadesEditBean")
@ViewScoped
public class LocalidadesEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LocalidadesEditBean.class.getName());

    @EJB
    private UbicacionProvinciasFacade provinciasFacade;

    @EJB
    private UbicacionLocalidadesFacade facade;

    @EJB
    private UbicacionPaisesFacade paisesFacade;


    private List<UbicacionPaises> paises;
    private UbicacionPaises paisSeleccionado;
    private List<UbicacionProvincias> provincias;

    private UbicacionLocalidades localidadActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public LocalidadesEditBean() {
    }

    @PostConstruct
    public void init() {

        String idLocalidad = JSFUtil.getRequestParameterMap().get("idLocalidad");

        if (StringUtils.isEmpty(idLocalidad)) {
            nuevo();
        } else {
            localidadActual = facade.find(Long.parseLong(idLocalidad));

            if (localidadActual == null) {
                LOG.log(Level.SEVERE, "Localidad inexistente!");
                throw new IllegalArgumentException("Localidad inexistente!");

            }
            paisSeleccionado = localidadActual.getIdProvincia().getIdPais();

        }

    }

    private void nuevo() {
        localidadActual = new UbicacionLocalidades();
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(localidadActual);
            JSFUtil.addInfoMessage("Localidad guardada Exitosamente");
            localidadActual = facade.find(localidadActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public UbicacionLocalidades getLocalidadActual() {
        return localidadActual;
    }

    public UbicacionPaises getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(UbicacionPaises paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    /**
     * Obtiene la lista de paises para poder asignar la provincia a algún país
     *
     * @return la lista de todos los paises
     */
    public List<UbicacionPaises> getPaises() {
        if (paises == null) {
            PaisesSearchFilter pSf = new PaisesSearchFilter();
            pSf.addSortField("nombrePais", true);
            paises = paisesFacade.findAllBySearchFilter(pSf);
        }
        return paises;
    }

    public void changePais() {
        provincias = null;
    }

    /**
     * Obtiene la lista de provincias para poder asignar a la localidad
     *
     * @return la lista de todas las provincias de un pais
     */
    public List<UbicacionProvincias> getProvincias() {
        if (paisSeleccionado == null) {
            return Collections.EMPTY_LIST;
        }
        if (provincias == null) {
            ProvinciasSearchFilter pSf = new ProvinciasSearchFilter();
            pSf.addSortField("nombreProvincia", true);
            pSf.setIdPais(paisSeleccionado);
            provincias = provinciasFacade.findAllBySearchFilter(pSf);
        }
        return provincias;
    }

}
