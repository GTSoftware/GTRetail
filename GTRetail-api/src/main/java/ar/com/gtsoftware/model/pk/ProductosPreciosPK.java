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

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Embeddable
public class ProductosPreciosPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_producto", columnDefinition = "int4")
    private Long idProducto;

    @Column(name = "id_lista_precio", columnDefinition = "int4")
    private Long idListaPrecio;

    public ProductosPreciosPK() {
    }

    public ProductosPreciosPK(Long idProducto, Long idListaPrecio) {
        this.idProducto = idProducto;
        this.idListaPrecio = idListaPrecio;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdListaPrecio() {
        return idListaPrecio;
    }

    public void setIdListaPrecio(Long idListaPrecio) {
        this.idListaPrecio = idListaPrecio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idProducto);
        hash = 97 * hash + Objects.hashCode(this.idListaPrecio);
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
        if (!Objects.equals(this.idListaPrecio, other.idListaPrecio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", idProducto, idListaPrecio);
    }

}
