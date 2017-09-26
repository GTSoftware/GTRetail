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

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.UbicacionPaisesFacade;
import ar.com.gtsoftware.eao.UbicacionProvinciasFacade;
import ar.com.gtsoftware.model.UbicacionPaises;
import ar.com.gtsoftware.model.UbicacionProvincias;
import ar.com.gtsoftware.search.PaisesSearchFilter;
import ar.com.gtsoftware.search.ProvinciasSearchFilter;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "provinciasSearchBean")
@ViewScoped
public class ProvinciasSearchBean extends AbstractSearchBean<UbicacionProvincias, ProvinciasSearchFilter> {

    private static final long serialVersionUID = 1L;

    @EJB
    private UbicacionProvinciasFacade facade;

    @EJB
    private UbicacionPaisesFacade paisesFacade;

    private final ProvinciasSearchFilter filter = new ProvinciasSearchFilter();

    private List<UbicacionPaises> paises;

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
    protected AbstractFacade<UbicacionProvincias, ProvinciasSearchFilter> getFacade() {
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
    public List<UbicacionPaises> getPaises() {
        if (paises == null) {
            PaisesSearchFilter pSf = new PaisesSearchFilter();
            pSf.addSortField("nombrePais", true);
            paises = paisesFacade.findAll();
        }
        return paises;
    }

}
