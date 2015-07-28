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
package ar.com.gtsoftware.model;

import ar.com.gtsoftware.model.pk.ProductosPreciosPK;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Entity
@Table(name = "productos_precios")
@XmlRootElement
public class ProductosPrecios extends GTEntity {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProductosPreciosPK idPrecio;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", updatable = false, insertable = false, columnDefinition = "int4")
    private Productos idProducto;

    @ManyToOne
    @JoinColumn(name = "id_lista_precio", referencedColumnName = "id_lista_precio", updatable = false, insertable = false,
            columnDefinition = "int4")
    private ProductosListasPrecios idListaPrecios;

    @Basic(optional = false)
    @Column(name = "utilidad", scale = 4, precision = 8)
    private BigDecimal utilidad;

    @Basic(optional = false)
    @Column(name = "precio", scale = 4, precision = 19)
    private BigDecimal precio;

    @Basic(optional = false)
    @Column(name = "neto", scale = 4, precision = 19)
    private BigDecimal neto;

    @Override
    public boolean isNew() {
        return idPrecio == null;
    }

    public ProductosPrecios() {
    }

    public ProductosPrecios(ProductosPreciosPK idPrecio) {
        this.idPrecio = idPrecio;
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

    public BigDecimal getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public ProductosPreciosPK getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(ProductosPreciosPK idPrecio) {
        this.idPrecio = idPrecio;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idPrecio);
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
        final ProductosPrecios other = (ProductosPrecios) obj;
        if (!Objects.equals(this.idPrecio, other.idPrecio)) {
            return false;
        }
        return true;
    }

    @Override
    public Object getId() {
        return idPrecio;
    }

    @Override
    public Object calculateId(String id) {

        if (id != null) {
            String[] pkStr = id.split("-");
            if (pkStr.length == 2) {
                return new ProductosPreciosPK(Long.parseLong(pkStr[0]), Long.parseLong(pkStr[1]));
            }
        }
        return null;
    }

    @Override
    public String getStringId() {
        if (idPrecio != null) {
            return idPrecio.toString();
        }
        return null;
    }

}
