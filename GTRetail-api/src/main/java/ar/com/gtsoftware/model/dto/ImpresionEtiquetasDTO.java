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
package ar.com.gtsoftware.model.dto;

import ar.com.gtsoftware.model.Productos;
import java.io.Serializable;

/**
 * DTO para la impresión de etiquetas de productos
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class ImpresionEtiquetasDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Productos producto;
    private int cantidad;
    private final int nroItem;

    /**
     * Cosntructor con todos los atributos
     *
     * @param producto
     * @param cantidad
     * @param nroItem
     */
    public ImpresionEtiquetasDTO(Productos producto, int cantidad, int nroItem) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.nroItem = nroItem;
    }

    /**
     * Constructor con producto y por defecto cantidad 1.
     *
     * @param producto
     * @param nroItem
     */
    public ImpresionEtiquetasDTO(Productos producto, int nroItem) {
        this.producto = producto;
        this.nroItem = nroItem;
        this.cantidad = 1;
    }

    /**
     * El producto de la etiqueta
     *
     * @return
     */
    public Productos getProducto() {
        return producto;
    }

    /**
     * El producto de la etiqueta
     *
     * @param producto
     */
    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    /**
     * La cantidad de etiquetas a imprimir para el producto
     *
     * @return
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * La cantidad de etiquetas a imprimir para el producto
     *
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * El número de ítem para identificar el elemento
     *
     * @return
     */
    public int getNroItem() {
        return nroItem;
    }

}
