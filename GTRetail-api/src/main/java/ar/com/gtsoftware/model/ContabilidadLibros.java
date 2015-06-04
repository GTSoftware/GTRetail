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
@Table(name = "contabilidad_libros")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_libro"))
public class ContabilidadLibros extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_libro")
    private String nombreLibro;
    @Size(max = 255)
    @Column(name = "descripcion_libro")
    private String descripcionLibro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLibro")
    private List<ContabilidadRegistroContable> contabilidadRegistroContableList;

    public ContabilidadLibros() {
    }

    public ContabilidadLibros(Long idLibro) {
        super(idLibro);
    }

    public ContabilidadLibros(Long idLibro, String nombreLibro) {
        super(idLibro);

        this.nombreLibro = nombreLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getDescripcionLibro() {
        return descripcionLibro;
    }

    public void setDescripcionLibro(String descripcionLibro) {
        this.descripcionLibro = descripcionLibro;
    }

    @XmlTransient
    public List<ContabilidadRegistroContable> getContabilidadRegistroContableList() {
        return contabilidadRegistroContableList;
    }

    public void setContabilidadRegistroContableList(List<ContabilidadRegistroContable> contabilidadRegistroContableList) {
        this.contabilidadRegistroContableList = contabilidadRegistroContableList;
    }

}
