/*
 * Copyright 2014 GT Software.
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
import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.dto.model.UbicacionPaisesDto;
import ar.com.gtsoftware.dto.model.UbicacionProvinciasDto;
import ar.com.gtsoftware.search.PaisesSearchFilter;
import ar.com.gtsoftware.search.ProvinciasSearchFilter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "provinciasSearchBean")
@ViewScoped
public class ProvinciasSearchBean extends AbstractSearchBean<UbicacionProvinciasDto, ProvinciasSearchFilter> {

    private static final long serialVersionUID = 1L;
    private final ProvinciasSearchFilter filter = new ProvinciasSearchFilter();
    @EJB
    private UbicacionProvinciasService facade;
    @EJB
    private UbicacionPaisesService paisesFacade;
    private List<UbicacionPaisesDto> paises;

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public ProvinciasSearchBean() {
    }

    @Override
    public ProvinciasSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected UbicacionProvinciasService getService() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("nombreProvincia", true);
        }

    }

    /**
     * Obtiene la lista de paises para poder filtrar por un país determinado
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
