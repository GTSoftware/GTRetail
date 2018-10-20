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
package ar.com.gtsoftware.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "productos_tipos_unidades")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_unidad", columnDefinition = "serial"))
public class ProductosTiposUnidades extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_unidad")
    private String nombreUnidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_entera")
    private boolean cantidadEntera;

    public ProductosTiposUnidades() {
    }

    public ProductosTiposUnidades(Long idTipoUnidad) {
        super(idTipoUnidad);
    }

    public ProductosTiposUnidades(Long idTipoUnidad, String nombreUnidad, boolean cantidadEntera) {
        super(idTipoUnidad);
        this.nombreUnidad = nombreUnidad;
        this.cantidadEntera = cantidadEntera;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public boolean getCantidadEntera() {
        return cantidadEntera;
    }

    public void setCantidadEntera(boolean cantidadEntera) {
        this.cantidadEntera = cantidadEntera;
    }

}
