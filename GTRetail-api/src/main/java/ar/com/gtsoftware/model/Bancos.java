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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que almacena la información de los bancos
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @version 2.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "bancos")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_banco", columnDefinition = "serial"))
public class Bancos extends BaseEntity {

    private static final long serialVersionUID = 2L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "razon_social")
    private String razonSocial;

    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBanco")
    private List<BancosCuentas> bancosCuentasList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Personas idPersona;

    /**
     * Constructor por defecto
     */
    public Bancos() {
    }

    /**
     * El nombre de la entidad financiera
     *
     * @return
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * El nombre de la entidad financiera
     *
     * @param razonSocial
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * Observaciones asociadas a la entidad
     *
     * @return
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Observaciones asociadas a la entidad
     *
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Las cuentas asociadas a la entidad
     *
     * @return
     */
    @XmlTransient
    public List<BancosCuentas> getBancosCuentasList() {
        return bancosCuentasList;
    }

    /**
     * Las cuentas asociadas a la entidad
     *
     * @param bancosCuentasList
     */
    public void setBancosCuentasList(List<BancosCuentas> bancosCuentasList) {
        this.bancosCuentasList = bancosCuentasList;
    }

    /**
     * El proveedor asociado a la entidad bancaria que contiene todos sus datos fiscales
     *
     * @return
     */
    public Personas getIdPersona() {
        return idPersona;
    }

    /**
     * El proveedor asociado a la entidad bancaria que contiene todos sus datos fiscales
     *
     * @param idPersona
     */
    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

}
