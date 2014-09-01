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
package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.eao.VentasFacade;
import ar.com.gtsoftware.model.Ventas;
import ar.com.gtsoftware.search.VentasSearchFilter;
import ar.com.gtsoftware.utils.UtilUI;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "searchVentasBean")
@ViewScoped
public class SearchVentasBean {

    @EJB
    private VentasFacade ventasFacade;
    private VentasSearchFilter filter = new VentasSearchFilter(UtilUI.getBeginOfToday(), UtilUI.getEndOfToday(), Boolean.FALSE);
    private List<Ventas> ventasList = new ArrayList<>();
    private BigDecimal totalVentasFacturadas = BigDecimal.ZERO;
    private BigDecimal totalVentas = BigDecimal.ZERO;
    private BigDecimal totalVentasSinFacturar = BigDecimal.ZERO;

    /**
     * Creates a new instance of SearchVentasBean
     */
    public SearchVentasBean() {
    }

    /**
     * Realiza la búsqueda según el filtro establecido
     */
    public void search() {
        ventasList.clear();
        ventasList.addAll(ventasFacade.findBySearchFilter(filter));
    }

    public VentasSearchFilter getFilter() {
        return filter;
    }

    public void setFilter(VentasSearchFilter filter) {
        this.filter = filter;
    }

    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    public VentasFacade getVentasFacade() {
        return ventasFacade;
    }

    public void setVentasFacade(VentasFacade ventasFacade) {
        this.ventasFacade = ventasFacade;
    }

    public BigDecimal getTotalVentasFacturadas() {
        return totalVentasFacturadas;
    }

    public void setTotalVentasFacturadas(BigDecimal totalVentasFacturadas) {
        this.totalVentasFacturadas = totalVentasFacturadas;
    }

    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }

    public BigDecimal getTotalVentasSinFacturar() {
        return totalVentasSinFacturar;
    }

    public void setTotalVentasSinFacturar(BigDecimal totalVentasSinFacturar) {
        this.totalVentasSinFacturar = totalVentasSinFacturar;
    }

}
