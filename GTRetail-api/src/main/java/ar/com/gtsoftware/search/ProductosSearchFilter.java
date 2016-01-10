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
package ar.com.gtsoftware.search;

import ar.com.gtsoftware.model.Depositos;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.model.ProductosMarcas;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.model.ProductosSubRubros;
import ar.com.gtsoftware.model.ProductosTiposProveeduria;
import javax.validation.constraints.Size;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ProductosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String txt;
    private Integer idProducto;
    @Size(max = 100)
    private String codigoPropio;
    private Boolean activo;
    private Boolean puedeComprarse;
    private Boolean puedeVenderse;
    private ProductosSubRubros idSubRubro;
    private ProductosRubros idRubro;
    private Personas idProveedorHabitual;
    private Depositos conStockEnDeposito;
    private ProductosListasPrecios listaPrecio;
    private ProductosTiposProveeduria idTipoProveeduria;
    private ProductosMarcas idMarca;

    public ProductosSearchFilter(Boolean activo, Boolean puedeComprarse, Boolean puedeVenderse) {
        this.activo = activo;
        this.puedeComprarse = puedeComprarse;
        this.puedeVenderse = puedeVenderse;
    }

    public ProductosSearchFilter() {
    }

    @Override
    public boolean hasFilter() {
        return (txt != null && !txt.isEmpty()) || (idProducto != null) || (codigoPropio != null && !codigoPropio.isEmpty())
                || (activo != null) || (puedeComprarse != null) || (puedeVenderse != null) || (idRubro != null) || (idSubRubro != null)
                || (idProveedorHabitual != null) || (conStockEnDeposito != null) || (listaPrecio != null) || (idTipoProveeduria != null)
                || (idMarca != null);
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoPropio() {
        return codigoPropio;
    }

    public void setCodigoPropio(String codigoPropio) {
        this.codigoPropio = codigoPropio;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getPuedeComprarse() {
        return puedeComprarse;
    }

    public void setPuedeComprarse(Boolean puedeComprarse) {
        this.puedeComprarse = puedeComprarse;
    }

    public Boolean getPuedeVenderse() {
        return puedeVenderse;
    }

    public void setPuedeVenderse(Boolean puedeVenderse) {
        this.puedeVenderse = puedeVenderse;
    }

    public ProductosSubRubros getIdSubRubro() {
        return idSubRubro;
    }

    public void setIdSubRubro(ProductosSubRubros idSubRubro) {
        this.idSubRubro = idSubRubro;
    }

    public ProductosRubros getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(ProductosRubros idRubro) {
        this.idRubro = idRubro;
    }

    public Personas getIdProveedorHabitual() {
        return idProveedorHabitual;
    }

    public void setIdProveedorHabitual(Personas idProveedorHabitual) {
        this.idProveedorHabitual = idProveedorHabitual;
    }

    public Depositos getConStockEnDeposito() {
        return conStockEnDeposito;
    }

    public void setConStockEnDeposito(Depositos conStockenDeposito) {
        this.conStockEnDeposito = conStockenDeposito;
    }

    public ProductosListasPrecios getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(ProductosListasPrecios listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

    public ProductosTiposProveeduria getIdTipoProveeduria() {
        return idTipoProveeduria;
    }

    public void setIdTipoProveeduria(ProductosTiposProveeduria idTipoProveeduria) {
        this.idTipoProveeduria = idTipoProveeduria;
    }

    public ProductosMarcas getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(ProductosMarcas idMarca) {
        this.idMarca = idMarca;
    }

}
