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

import ar.com.gtsoftware.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String txt;
    private Long idProducto;
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
    private Boolean conStock;
    private Boolean stockDebajoMinimo;

    public ProductosSearchFilter(Boolean activo, Boolean puedeComprarse, Boolean puedeVenderse) {
        this.activo = activo;
        this.puedeComprarse = puedeComprarse;
        this.puedeVenderse = puedeVenderse;
    }

    public ProductosSearchFilter(Boolean activo, Boolean puedeComprarse, Boolean puedeVenderse, Boolean soloConStock) {
        this.activo = activo;
        this.puedeComprarse = puedeComprarse;
        this.puedeVenderse = puedeVenderse;
        this.conStock = soloConStock;
    }

    @Override
    public boolean hasFilter() {
        return (txt != null && !txt.isEmpty()) || (idProducto != null) || (codigoPropio != null && !codigoPropio.isEmpty())
                || (activo != null) || (puedeComprarse != null) || (puedeVenderse != null) || (idRubro != null) || (idSubRubro != null)
                || (idProveedorHabitual != null) || (conStockEnDeposito != null) || (listaPrecio != null) || (idTipoProveeduria != null)
                || (idMarca != null) || (conStock != null) || (stockDebajoMinimo != null);
    }

}
