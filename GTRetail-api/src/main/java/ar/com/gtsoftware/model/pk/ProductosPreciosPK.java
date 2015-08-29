/*
 * Copyright 2015 GT Software.
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
package ar.com.gtsoftware.model.pk;

import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
public class ProductosPreciosPK implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Column(name = "id_producto", columnDefinition = "int4")
//    private Long idProducto;
//    @ManyToOne
//    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", updatable = false, insertable = false, columnDefinition = "int4")
    private Productos idProducto;
//    @Column(name = "id_lista_precio", columnDefinition = "int4")
//    private Long idListaPrecio;
//    @ManyToOne
//    @JoinColumn(name = "id_lista_precio", referencedColumnName = "id_lista_precio", updatable = false, insertable = false,
//            columnDefinition = "int4")
    private ProductosListasPrecios idListaPrecios;

    public ProductosPreciosPK() {
    }

    public ProductosPreciosPK(Productos idProducto, ProductosListasPrecios idListaPrecios) {
        this.idProducto = idProducto;
        this.idListaPrecios = idListaPrecios;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public ProductosListasPrecios getIdListaPrecios() {
        return idListaPrecios;
    }

//    public ProductosPreciosPK(Long idProducto, Long idListaPrecio) {
//        this.idProducto = idProducto;
//        this.idListaPrecio = idListaPrecio;
//    }
//
//    public Long getIdProducto() {
//        return idProducto;
//    }
//
//    public void setIdProducto(Long idProducto) {
//        this.idProducto = idProducto;
//    }
//
//    public Long getIdListaPrecio() {
//        return idListaPrecio;
//    }
//
//    public void setIdListaPrecio(Long idListaPrecio) {
//        this.idListaPrecio = idListaPrecio;
//    }
    public void setIdListaPrecios(ProductosListasPrecios idListaPrecios) {
        this.idListaPrecios = idListaPrecios;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idProducto);
        hash = 97 * hash + Objects.hashCode(this.idListaPrecios);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductosPreciosPK other = (ProductosPreciosPK) obj;
        if (!Objects.equals(this.idProducto, other.idProducto)) {
            return false;
        }
        if (!Objects.equals(this.idListaPrecios, other.idListaPrecios)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", idProducto.getStringId(), idListaPrecios.getStringId());
    }

}
