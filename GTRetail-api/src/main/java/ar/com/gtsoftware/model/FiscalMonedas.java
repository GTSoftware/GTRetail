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

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que mapea a la tabla fiscal_monedas para regímenes informativos
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Entity
@Table(name = "fiscal_monedas")
@XmlRootElement
public class FiscalMonedas extends GTEntity<String> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "codigo_moneda", columnDefinition = "bpchar")
    private String codigoMoneda;
    @Size(max = 50)
    @Column(name = "nombre_moneda")
    private String nombreMoneda;

    public FiscalMonedas() {
    }

    @Override
    public boolean isNew() {
        return codigoMoneda == null;
    }

    public String getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(String codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.codigoMoneda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FiscalMonedas other = (FiscalMonedas) obj;
        if (!Objects.equals(this.codigoMoneda, other.codigoMoneda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FiscalMonedas{" + "codigoMoneda=" + codigoMoneda + ", nombreMoneda=" + nombreMoneda + '}';
    }

    @Override
    public String getId() {
        return codigoMoneda;
    }

    @Override
    public String calculateId(String id) {
        return id;
    }

    @Override
    public String getStringId() {
        return codigoMoneda;
    }

}
