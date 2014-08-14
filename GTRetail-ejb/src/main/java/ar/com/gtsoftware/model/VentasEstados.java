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

import java.io.Serializable;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "ventas_estados")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_estado"))
public class VentasEstados extends BaseEntity implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre_estado")
    private String nombreEstado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private List<Ventas> ventasList;

    public VentasEstados(Integer id) {
        super(id);
    }

    public VentasEstados() {
    }

    public VentasEstados(Integer id, String nombreEstado) {
        super(id);
        this.nombreEstado = nombreEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.VentasEstados[ id=" + this.getId() + " ]";
    }
    
    

}
