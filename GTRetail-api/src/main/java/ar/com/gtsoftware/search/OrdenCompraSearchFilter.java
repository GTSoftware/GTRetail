/*
 * Copyright 2017 GT Software.
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
package ar.com.gtsoftware.search;

import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProveedoresOrdenesCompraEstados;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * SearchFilter para Órdenes de Compra
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class OrdenCompraSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Date fechaAltaDesde;
    private Date fechaAltaHasta;
    private Personas idProveedor;
    private Productos idProducto;
    private String txt;
    private ProveedoresOrdenesCompraEstados idEstadoOrdenCompra;

    @Override
    public boolean hasFilter() {
        return hasEntreFechasAltaFilter() || idProveedor != null || idProducto != null
                || idEstadoOrdenCompra != null || hasTxtFilter();
    }

    /**
     * Retorna true si ambos filtros de búsqueda por fecha de alta de orden de compra están establecidos.
     *
     * @return
     */
    public boolean hasEntreFechasAltaFilter() {
        return fechaAltaDesde != null && fechaAltaHasta != null;
    }

    /**
     * La fecha de alta desde la que se desea buscar las ordenes de compra.
     *
     * @return
     */
    public Date getFechaAltaDesde() {
        return fechaAltaDesde;
    }

    /**
     * La fecha de alta desde la que se desea buscar las ordenes de compra.
     *
     * @param fechaAltaDesde
     */
    public void setFechaAltaDesde(Date fechaAltaDesde) {
        this.fechaAltaDesde = fechaAltaDesde;
    }

    /**
     * La fecha de alta hasta la que se desea buscar las ordenes de compra.
     *
     * @return
     */
    public Date getFechaAltaHasta() {
        return fechaAltaHasta;
    }

    /**
     * La fecha de alta hasta la que se desea buscar las ordenes de compra.
     *
     * @param fechaAltaHasta
     */
    public void setFechaAltaHasta(Date fechaAltaHasta) {
        this.fechaAltaHasta = fechaAltaHasta;
    }

    /**
     * El producto esté dentro de alguno de los detalles de la Orden de compra
     *
     * @return
     */
    public Productos getIdProducto() {
        return idProducto;
    }

    /**
     * El producto esté dentro de alguno de los detalles de la Orden de compra
     *
     * @param idProducto
     */
    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * El proveedor al que pertecene la Orden de compra
     *
     * @return idProveedor
     */
    public Personas getIdProveedor() {
        return idProveedor;
    }

    /**
     * El proveedor al que pertecene la Orden de compra
     *
     * @param idProveedor
     */
    public void setIdProveedor(Personas idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * El estado en el que se encuentra la Orden de compra
     *
     * @return idEstadoOrdenCompra
     */
    public ProveedoresOrdenesCompraEstados getIdEstadoOrdenCompra() {
        return idEstadoOrdenCompra;
    }

    /**
     * El estado en el que se encuentra la Orden de compra
     *
     * @param idEstadoOrdenCompra
     */
    public void setIdEstadoOrdenCompra(ProveedoresOrdenesCompraEstados idEstadoOrdenCompra) {
        this.idEstadoOrdenCompra = idEstadoOrdenCompra;
    }

    /**
     * Retorna true si hay algún texto de búsqueda.
     *
     * @return
     */
    public boolean hasTxtFilter() {
        return StringUtils.isNotEmpty(txt);
    }

    /**
     * El texto de búsqueda
     *
     * @return
     */

    public String getTxt() {
        return txt;
    }

    /**
     * El texto de búsqueda
     *
     * @param txt
     */
    public void setTxt(String txt) {
        this.txt = txt;
    }
}
