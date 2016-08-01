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
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Embeddable
public class ProductosPreciosPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Productos idProducto;

    @ManyToOne
    @JoinColumn(name = "id_lista_precio", referencedColumnName = "id_lista_precio")
    private ProductosListasPrecios idListaPrecios;

    public ProductosPreciosPK() {
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

    public void setIdListaPrecios(ProductosListasPrecios idListaPrecios) {
        this.idListaPrecios = idListaPrecios;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", idProducto.getId(), idListaPrecios.getId());
    }

}
