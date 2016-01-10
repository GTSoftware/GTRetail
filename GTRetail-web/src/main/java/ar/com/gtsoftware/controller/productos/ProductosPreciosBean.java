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
package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosMarcasFacade;
import ar.com.gtsoftware.eao.ProductosRubrosFacade;
import ar.com.gtsoftware.eao.ProductosSubRubrosFacade;
import ar.com.gtsoftware.eao.ProductosTiposProveeduriaFacade;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.ProductosMarcas;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.model.ProductosSubRubros;
import ar.com.gtsoftware.model.ProductosTiposProveeduria;
import ar.com.gtsoftware.search.MarcasSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.ProductoRubrosSearchFilter;
import ar.com.gtsoftware.search.ProductoSubRubroSearchFilter;
import ar.com.gtsoftware.search.ProductosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Named(value = "productosPreciosBean")
@ViewScoped
public class ProductosPreciosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ProductosSearchFilter filter = new ProductosSearchFilter(Boolean.TRUE, null, null);
    private List<ProductosTiposProveeduria> tiposProveeduriaList;
    private List<Personas> proveedoresList;
    private List<ProductosRubros> rubrosList;
    private List<ProductosSubRubros> subRubrosList;
    private List<ProductosMarcas> marcasList;

    @EJB
    private ProductosFacade productosFacade;
    @EJB
    private ProductosRubrosFacade productosRubrosFacade;

    @EJB
    private ProductosSubRubrosFacade productosSubRubrosFacade;
    @EJB
    private ProductosTiposProveeduriaFacade productosTiposProveeduriaFacade;
    @EJB
    private ProductosMarcasFacade productosMarcasFacade;
    @EJB
    private PersonasFacade personasFacade;

    /**
     * Creates a new instance of ProductosPreciosBean
     */
    public ProductosPreciosBean() {
    }

    public ProductosSearchFilter getFilter() {
        return filter;
    }

    public List<ProductosTiposProveeduria> getTiposProveeduriaList() {
        if (tiposProveeduriaList == null) {
            tiposProveeduriaList = new ArrayList<>();
            tiposProveeduriaList.addAll(productosTiposProveeduriaFacade.findAll());
        }
        return tiposProveeduriaList;
    }

    public List<Personas> getProveedoresList() {
        if (proveedoresList == null) {
            PersonasSearchFilter psf = new PersonasSearchFilter();
            psf.setProveedor(true);
            psf.setActivo(true);
            psf.addSortField(new SortField("razonSocial", true));
            proveedoresList = new ArrayList<>();
            proveedoresList.addAll(personasFacade.findAllBySearchFilter(psf));
        }
        return proveedoresList;
    }

    public List<ProductosRubros> getRubrosList() {
        if (rubrosList == null) {
            ProductoRubrosSearchFilter prsf = new ProductoRubrosSearchFilter();
            prsf.addSortField(new SortField("nombreRubro", true));
            rubrosList = new ArrayList<>();
            rubrosList.addAll(productosRubrosFacade.findAllBySearchFilter(prsf));
        }
        return rubrosList;
    }

    public List<ProductosSubRubros> getSubRubrosList() {
        if (subRubrosList == null) {
            subRubrosList = new ArrayList<>();
        }
        if (filter.getIdRubro() != null) {
            ProductoSubRubroSearchFilter subRubroSearchFilter = new ProductoSubRubroSearchFilter();
            subRubroSearchFilter.setProductosRubros(filter.getIdRubro());
            subRubroSearchFilter.addSortField(new SortField("nombreSubRubro", true));
            subRubrosList.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));
        } else {
            subRubrosList.clear();
        }
        return subRubrosList;
    }

    public List<ProductosMarcas> getMarcasList() {
        if (marcasList == null) {
            MarcasSearchFilter msf = new MarcasSearchFilter();
            msf.addSortField(new SortField("nombreMarca", true));
            marcasList = new ArrayList<>();
            marcasList.addAll(productosMarcasFacade.findAllBySearchFilter(msf));
        }
        return marcasList;
    }

    public int getCantidadProductosAEditar() {
        return productosFacade.countBySearchFilter(filter);
    }
}
