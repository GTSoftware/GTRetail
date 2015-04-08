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

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "cajas_categorias_movimientos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_categoria_movimiento"))
public class CajasCategoriasMovimientos extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_categoria_movimiento")
    private String nombreCategoriaMovimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaMovimiento")
    private List<CajasMovimientos> cajasMovimientosList;

    public CajasCategoriasMovimientos() {
    }

    public CajasCategoriasMovimientos(Long idCategoriaMovimiento) {
        super(idCategoriaMovimiento);
    }

    public CajasCategoriasMovimientos(Long idCategoriaMovimiento, String nombreCategoriaMovimiento) {
        super(idCategoriaMovimiento);
        this.nombreCategoriaMovimiento = nombreCategoriaMovimiento;
    }

    public String getNombreCategoriaMovimiento() {
        return nombreCategoriaMovimiento;
    }

    public void setNombreCategoriaMovimiento(String nombreCategoriaMovimiento) {
        this.nombreCategoriaMovimiento = nombreCategoriaMovimiento;
    }

    @XmlTransient
    public List<CajasMovimientos> getCajasMovimientosList() {
        return cajasMovimientosList;
    }

    public void setCajasMovimientosList(List<CajasMovimientos> cajasMovimientosList) {
        this.cajasMovimientosList = cajasMovimientosList;
    }

}
