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
@Table(name = "bancos_tipos_cuenta")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_tipo_cuenta_banco"))
public class BancosTiposCuenta extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_tipo_cuenta")
    private String nombreTipoCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCuentaBanco")
    private List<BancosCuentas> bancosCuentasList;

    public BancosTiposCuenta() {
    }

    public BancosTiposCuenta(Long idTipoCuentaBanco) {
        super(idTipoCuentaBanco);
    }

    public BancosTiposCuenta(Long idTipoCuentaBanco, String nombreTipoCuenta) {
        super(idTipoCuentaBanco);
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

    public String getNombreTipoCuenta() {
        return nombreTipoCuenta;
    }

    public void setNombreTipoCuenta(String nombreTipoCuenta) {
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

    @XmlTransient
    public List<BancosCuentas> getBancosCuentasList() {
        return bancosCuentasList;
    }

    public void setBancosCuentasList(List<BancosCuentas> bancosCuentasList) {
        this.bancosCuentasList = bancosCuentasList;
    }

}
