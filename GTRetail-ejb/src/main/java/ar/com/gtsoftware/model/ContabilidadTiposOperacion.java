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
@Table(name = "contabilidad_tipos_operacion")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_operacion", columnDefinition = "serial"))
public class ContabilidadTiposOperacion extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_tipo_operacion")
    private String nombreTipoOperacion;
    @Size(max = 2000)
    @Column(name = "descripcion_tipo_operacion")
    private String descripcionTipoOperacion;
    @OneToMany(mappedBy = "idTipoOperacion")
    private List<ContabilidadRegistroContable> contabilidadRegistroContableList;

    public ContabilidadTiposOperacion() {
    }

    public ContabilidadTiposOperacion(Long idTipoOperacion) {
        super(idTipoOperacion);
    }

    public ContabilidadTiposOperacion(Long idTipoOperacion, String nombreTipoOperacion) {
        super(idTipoOperacion);
        this.nombreTipoOperacion = nombreTipoOperacion;
    }

    public String getNombreTipoOperacion() {
        return nombreTipoOperacion;
    }

    public void setNombreTipoOperacion(String nombreTipoOperacion) {
        this.nombreTipoOperacion = nombreTipoOperacion;
    }

    public String getDescripcionTipoOperacion() {
        return descripcionTipoOperacion;
    }

    public void setDescripcionTipoOperacion(String descripcionTipoOperacion) {
        this.descripcionTipoOperacion = descripcionTipoOperacion;
    }

    @XmlTransient
    public List<ContabilidadRegistroContable> getContabilidadRegistroContableList() {
        return contabilidadRegistroContableList;
    }

    public void setContabilidadRegistroContableList(List<ContabilidadRegistroContable> contabilidadRegistroContableList) {
        this.contabilidadRegistroContableList = contabilidadRegistroContableList;
    }

}
