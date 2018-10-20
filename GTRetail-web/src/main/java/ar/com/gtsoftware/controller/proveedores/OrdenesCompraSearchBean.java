///*
// * Copyright 2014 GT Software.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package ar.com.gtsoftware.controller.proveedores;
//
//import ar.com.gtsoftware.controller.search.AbstractSearchBean;
//import ar.com.gtsoftware.eao.AbstractFacade;
//import ar.com.gtsoftware.eao.ProveedoresOrdenesCompraFacade;
//import ar.com.gtsoftware.model.Personas;
//import ar.com.gtsoftware.model.ProveedoresOrdenesCompra;
//import ar.com.gtsoftware.search.OrdenCompraSearchFilter;
//import ar.com.gtsoftware.search.SortField;
//
//import javax.ejb.EJB;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//
///**
// * SearchBean para Ordenes de compra
// *
// * @author Rodrigo Tato <rotatomel@gmail.com>
// */
//@ManagedBean(name = "ordenesCompraSearchBean")
//@ViewScoped
//public class OrdenesCompraSearchBean extends AbstractSearchBean<ProveedoresOrdenesCompra, OrdenCompraSearchFilter> {
//
//    private static final long serialVersionUID = 1L;
//
//    @EJB
//    private ProveedoresOrdenesCompraFacade facade;
//
//    private final OrdenCompraSearchFilter filter = new OrdenCompraSearchFilter();
//
//    /**
//     * Creates a new instance of OrdenesCompraSearchBean
//     */
//    public OrdenesCompraSearchBean() {
//    }
//
//    @Override
//    protected AbstractFacade<ProveedoresOrdenesCompra, OrdenCompraSearchFilter> getService() {
//        return facade;
//    }
//
//    @Override
//    protected void prepareSearchFilter() {
//        if (!filter.hasOrderFields()) {
//            filter.addSortField(new SortField("fechaAlta", false));
//        }
//    }
//
//    @Override
//    public OrdenCompraSearchFilter getFilter() {
//        return filter;
//    }
//
//
//}
