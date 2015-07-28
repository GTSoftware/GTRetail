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
@Table(name = "contabilidad_tipos_cuenta")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_cuenta", columnDefinition = "serial"))
public class ContabilidadTiposCuenta extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Size(max = 255)
    @Column(name = "descripcion_tipo")
    private String descripcionTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCuenta")
    private List<ContabilidadPlanCuentas> contabilidadPlanCuentasList;

    public ContabilidadTiposCuenta() {
    }

    public ContabilidadTiposCuenta(Long idTipoCuenta) {
        super(idTipoCuenta);
    }

    public ContabilidadTiposCuenta(Long idTipoCuenta, String nombreTipo) {
        super(idTipoCuenta);
        this.nombreTipo = nombreTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    @XmlTransient
    public List<ContabilidadPlanCuentas> getContabilidadPlanCuentasList() {
        return contabilidadPlanCuentasList;
    }

    public void setContabilidadPlanCuentasList(List<ContabilidadPlanCuentas> contabilidadPlanCuentasList) {
        this.contabilidadPlanCuentasList = contabilidadPlanCuentasList;
    }

}
