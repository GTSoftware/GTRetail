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

import ar.com.gtsoftware.bl.UbicacionLocalidadesService;
import ar.com.gtsoftware.bl.UbicacionPaisesService;
import ar.com.gtsoftware.bl.UbicacionProvinciasService;
import ar.com.gtsoftware.dto.model.UbicacionLocalidadesDto;
import ar.com.gtsoftware.dto.model.UbicacionPaisesDto;
import ar.com.gtsoftware.dto.model.UbicacionProvinciasDto;
import ar.com.gtsoftware.helper.JSFHelper;
import ar.com.gtsoftware.search.PaisesSearchFilter;
import ar.com.gtsoftware.search.ProvinciasSearchFilter;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "localidadesEditBean")
@ViewScoped
public class LocalidadesEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(LocalidadesEditBean.class.getName());

    @EJB
    private UbicacionProvinciasService provinciasFacade;

    @EJB
    private UbicacionLocalidadesService facade;

    @EJB
    private UbicacionPaisesService paisesFacade;
    @Inject
    private JSFHelper jsfHelper;

    private List<UbicacionPaisesDto> paises;
    private UbicacionPaisesDto paisSeleccionado;
    private List<UbicacionProvinciasDto> provincias;

    private UbicacionLocalidadesDto localidadActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public LocalidadesEditBean() {
    }

    @PostConstruct
    public void init() {

        String idLocalidad = jsfHelper.getRequestParameterMap().get("idLocalidad");

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
        localidadActual = new UbicacionLocalidadesDto();
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(localidadActual);
            jsfHelper.addInfoMessage("Localidad guardada Exitosamente");
            localidadActual = facade.find(localidadActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            jsfHelper.addErrorMessage("Error al guardar");
        }

    }

    public UbicacionLocalidadesDto getLocalidadActual() {
        return localidadActual;
    }

    public UbicacionPaisesDto getPaisSeleccionado() {
        return paisSeleccionado;
    }

    public void setPaisSeleccionado(UbicacionPaisesDto paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    /**
     * Obtiene la lista de paises para poder asignar la provincia a algún país
     *
     * @return la lista de todos los paises
     */
    public List<UbicacionPaisesDto> getPaises() {
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
    public List<UbicacionProvinciasDto> getProvincias() {
        if (paisSeleccionado == null) {
            return Collections.emptyList();
        }
        if (provincias == null) {
            ProvinciasSearchFilter pSf = new ProvinciasSearchFilter();
            pSf.addSortField("nombreProvincia", true);
            pSf.setIdPais(paisSeleccionado.getId());
            provincias = provinciasFacade.findAllBySearchFilter(pSf);
        }
        return provincias;
    }

}
