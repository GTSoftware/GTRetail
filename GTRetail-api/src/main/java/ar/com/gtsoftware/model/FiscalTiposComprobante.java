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
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que mapea a la tabla fiscal_tipos_comprobante para regímenes informativos
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Entity
@Table(name = "fiscal_tipos_comprobante")
@XmlRootElement
public class FiscalTiposComprobante extends GTEntity<String> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "codigo_tipo_comprobante", columnDefinition = "bpchar")
    private String codigoTipoComprobante;
    @Size(max = 100)
    @Column(name = "denominacion_comprobante")
    private String denominacionComprobante;
    @OneToMany(mappedBy = "codigoTipoComprobante")
    private List<FiscalLibroIvaVentas> fiscalLibroIvaVentass;

    public FiscalTiposComprobante() {
    }

    @Override
    public boolean isNew() {
        return codigoTipoComprobante == null;
    }

    public String getCodigoTipoComprobante() {
        return codigoTipoComprobante;
    }

    public void setCodigoTipoComprobante(String codigoTipoComprobante) {
        this.codigoTipoComprobante = codigoTipoComprobante;
    }

    public String getDenominacionComprobante() {
        return denominacionComprobante;
    }

    public void setDenominacionComprobante(String denominacionComprobante) {
        this.denominacionComprobante = denominacionComprobante;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.codigoTipoComprobante);
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
        final FiscalTiposComprobante other = (FiscalTiposComprobante) obj;
        if (!Objects.equals(this.codigoTipoComprobante, other.codigoTipoComprobante)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FiscalTiposComprobante{" + "codigoTipoComprobante=" + codigoTipoComprobante + ", denominacionComprobante=" + denominacionComprobante + '}';
    }

    @Override
    public String getId() {
        return codigoTipoComprobante;
    }

    @Override
    public String calculateId(String id) {
        return id;
    }

    @Override
    public String getStringId() {
        return codigoTipoComprobante;
    }

}
