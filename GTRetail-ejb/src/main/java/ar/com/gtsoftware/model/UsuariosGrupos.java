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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "usuarios_grupos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_grupo", columnDefinition = "serial"))
public class UsuariosGrupos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_grupo")
    private String nombreGrupo;
    @ManyToMany(mappedBy = "usuariosGruposList", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Usuarios> usuariosList;
    @JoinTable(name = "privilegios_gruposx", joinColumns = {
        @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo", columnDefinition = "int4")}, inverseJoinColumns = {
        @JoinColumn(name = "id_privilegio", referencedColumnName = "id_privilegio", columnDefinition = "int4")})
    @ManyToMany
    private List<Privilegios> privilegiosList;

    public UsuariosGrupos() {
    }

    public UsuariosGrupos(Long idGrupo) {
        super(idGrupo);
    }

    public UsuariosGrupos(Long idGrupo, String nombreGrupo) {
        super(idGrupo);
        this.nombreGrupo = nombreGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<Privilegios> getPrivilegiosList() {
        return privilegiosList;
    }

    public void setPrivilegiosList(List<Privilegios> privilegiosList) {
        this.privilegiosList = privilegiosList;
    }

}
