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
@Table(name = "contabilidad_monedas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_moneda", columnDefinition = "serial"))
public class ContabilidadMonedas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_moneda")
    private String nombreMoneda;
    @Size(max = 10)
    @Column(name = "nombre_corto_moneda")
    private String nombreCortoMoneda;
    @Size(max = 5)
    @Column(name = "simbolo_moneda")
    private String simboloMoneda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
    private List<BancosCuentas> bancosCuentasList;

    public ContabilidadMonedas() {
    }

    public ContabilidadMonedas(Long idMoneda) {
        super(idMoneda);
    }

    public ContabilidadMonedas(Long idMoneda, String nombreMoneda) {
        super(idMoneda);
        this.nombreMoneda = nombreMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public String getNombreCortoMoneda() {
        return nombreCortoMoneda;
    }

    public void setNombreCortoMoneda(String nombreCortoMoneda) {
        this.nombreCortoMoneda = nombreCortoMoneda;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    @XmlTransient
    public List<BancosCuentas> getBancosCuentasList() {
        return bancosCuentasList;
    }

    public void setBancosCuentasList(List<BancosCuentas> bancosCuentasList) {
        this.bancosCuentasList = bancosCuentasList;
    }

}
