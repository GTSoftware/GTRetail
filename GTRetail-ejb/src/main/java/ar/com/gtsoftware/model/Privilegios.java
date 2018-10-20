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
import javax.persistence.ManyToMany;
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
@Table(name = "privilegios")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_privilegio", columnDefinition = "serial"))
public class Privilegios extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_privilegio")
    private String nombrePrivilegio;
    @Size(max = 1024)
    @Column(name = "descripcion_privilegio")
    private String descripcionPrivilegio;
    @ManyToMany(mappedBy = "privilegiosList")
    private List<UsuariosGrupos> usuariosGruposList;

    public Privilegios() {
    }

    public Privilegios(Long idPrivilegio) {
        super(idPrivilegio);
    }

    public Privilegios(Long idPrivilegio, String nombrePrivilegio) {
        super(idPrivilegio);
        this.nombrePrivilegio = nombrePrivilegio;
    }

    public String getNombrePrivilegio() {
        return nombrePrivilegio;
    }

    public void setNombrePrivilegio(String nombrePrivilegio) {
        this.nombrePrivilegio = nombrePrivilegio;
    }

    public String getDescripcionPrivilegio() {
        return descripcionPrivilegio;
    }

    public void setDescripcionPrivilegio(String descripcionPrivilegio) {
        this.descripcionPrivilegio = descripcionPrivilegio;
    }

    @XmlTransient
    public List<UsuariosGrupos> getUsuariosGruposList() {
        return usuariosGruposList;
    }

    public void setUsuariosGruposList(List<UsuariosGrupos> usuariosGruposList) {
        this.usuariosGruposList = usuariosGruposList;
    }

}
