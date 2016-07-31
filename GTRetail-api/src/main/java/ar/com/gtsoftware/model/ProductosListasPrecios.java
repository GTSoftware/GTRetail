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

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Entity
@Table(name = "productos_listas_precios")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_lista_precio", columnDefinition = "int4"))
public class ProductosListasPrecios extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "nombre_lista")
    @Size(max = 50)
    private String nombreLista;

    @Basic(optional = false)
    @Column(name = "activa")
    private boolean activa;

    @ManyToMany(mappedBy = "listasPrecioHabilitadas")
    private List<NegocioPlanesPago> planesPagoAsociados;

    @OneToMany(mappedBy = "idListaPrecios")
    private List<ProductosPrecios> productosPreciosList;

    public ProductosListasPrecios() {
    }

    public ProductosListasPrecios(Long id) {
        super(id);
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @XmlTransient
    public List<NegocioPlanesPago> getPlanesPagoAsociados() {
        return planesPagoAsociados;
    }

    public void setPlanesPagoAsociados(List<NegocioPlanesPago> planesPagoAsociados) {
        this.planesPagoAsociados = planesPagoAsociados;
    }

    @XmlTransient
    public List<ProductosPrecios> getProductosPreciosList() {
        return productosPreciosList;
    }

    public void setProductosPreciosList(List<ProductosPrecios> productosPreciosList) {
        this.productosPreciosList = productosPreciosList;
    }

}
