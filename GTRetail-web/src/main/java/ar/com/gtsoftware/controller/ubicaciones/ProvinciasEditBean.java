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

import ar.com.gtsoftware.bl.UbicacionPaisesService;
import ar.com.gtsoftware.bl.UbicacionProvinciasService;
import ar.com.gtsoftware.dto.model.UbicacionPaisesDto;
import ar.com.gtsoftware.dto.model.UbicacionProvinciasDto;
import ar.com.gtsoftware.search.PaisesSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "provinciasEditBean")
@ViewScoped
public class ProvinciasEditBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ProvinciasEditBean.class.getName());

    @EJB
    private UbicacionProvinciasService facade;

    @EJB
    private UbicacionPaisesService paisesFacade;


    private List<UbicacionPaisesDto> paises;

    private UbicacionProvinciasDto provinciaActual = null;

    /**
     * Creates a new instance of MarcasEditBean
     */
    public ProvinciasEditBean() {
    }

    @PostConstruct
    public void init() {

        String idProvincia = JSFUtil.getRequestParameterMap().get("idProvincia");

        if (StringUtils.isEmpty(idProvincia)) {
            nuevo();
        } else {
            provinciaActual = facade.find(Long.parseLong(idProvincia));

            if (provinciaActual == null) {
                LOG.log(Level.SEVERE, "Provincia inexistente!");
                throw new IllegalArgumentException("Provincia inexistente!");

            }

        }

    }

    private void nuevo() {
        provinciaActual = new UbicacionProvinciasDto();
    }

    public void doGuardar() {
        try {

            facade.createOrEdit(provinciaActual);
            JSFUtil.addInfoMessage("Provincia guardada Exitosamente");
            provinciaActual = facade.find(provinciaActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public UbicacionProvinciasDto getProvinciaActual() {
        return provinciaActual;
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

}
