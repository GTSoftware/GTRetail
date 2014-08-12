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

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigo
 */
@Entity
@Table(name = "fiscal_letras_comprobantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FiscalLetrasComprobantes.findAll", query = "SELECT f FROM FiscalLetrasComprobantes f"),
    @NamedQuery(name = "FiscalLetrasComprobantes.findByIdResoponsabildiadIvaEmisor", query = "SELECT f FROM FiscalLetrasComprobantes f WHERE f.fiscalLetrasComprobantesPK.idResoponsabildiadIvaEmisor = :idResoponsabildiadIvaEmisor"),
    @NamedQuery(name = "FiscalLetrasComprobantes.findByIdResoponsabildiadIvaReceptor", query = "SELECT f FROM FiscalLetrasComprobantes f WHERE f.fiscalLetrasComprobantesPK.idResoponsabildiadIvaReceptor = :idResoponsabildiadIvaReceptor"),
    @NamedQuery(name = "FiscalLetrasComprobantes.findByLetraComprobante", query = "SELECT f FROM FiscalLetrasComprobantes f WHERE f.letraComprobante = :letraComprobante")})
public class FiscalLetrasComprobantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FiscalLetrasComprobantesPK fiscalLetrasComprobantesPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "letra_comprobante")
    private String letraComprobante;
    @JoinColumn(name = "id_resoponsabildiad_iva_receptor", referencedColumnName = "id_resoponsabildiad_iva", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FiscalResponsabilidadesIva fiscalResponsabilidadesIva;
    @JoinColumn(name = "id_resoponsabildiad_iva_emisor", referencedColumnName = "id_resoponsabildiad_iva", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FiscalResponsabilidadesIva fiscalResponsabilidadesIva1;

    public FiscalLetrasComprobantes() {
    }

    public FiscalLetrasComprobantes(FiscalLetrasComprobantesPK fiscalLetrasComprobantesPK) {
        this.fiscalLetrasComprobantesPK = fiscalLetrasComprobantesPK;
    }

    public FiscalLetrasComprobantes(FiscalLetrasComprobantesPK fiscalLetrasComprobantesPK, String letraComprobante) {
        this.fiscalLetrasComprobantesPK = fiscalLetrasComprobantesPK;
        this.letraComprobante = letraComprobante;
    }

    public FiscalLetrasComprobantes(int idResoponsabildiadIvaEmisor, int idResoponsabildiadIvaReceptor) {
        this.fiscalLetrasComprobantesPK = new FiscalLetrasComprobantesPK(idResoponsabildiadIvaEmisor, idResoponsabildiadIvaReceptor);
    }

    public FiscalLetrasComprobantesPK getFiscalLetrasComprobantesPK() {
        return fiscalLetrasComprobantesPK;
    }

    public void setFiscalLetrasComprobantesPK(FiscalLetrasComprobantesPK fiscalLetrasComprobantesPK) {
        this.fiscalLetrasComprobantesPK = fiscalLetrasComprobantesPK;
    }

    public String getLetraComprobante() {
        return letraComprobante;
    }

    public void setLetraComprobante(String letraComprobante) {
        this.letraComprobante = letraComprobante;
    }

    public FiscalResponsabilidadesIva getFiscalResponsabilidadesIva() {
        return fiscalResponsabilidadesIva;
    }

    public void setFiscalResponsabilidadesIva(FiscalResponsabilidadesIva fiscalResponsabilidadesIva) {
        this.fiscalResponsabilidadesIva = fiscalResponsabilidadesIva;
    }

    public FiscalResponsabilidadesIva getFiscalResponsabilidadesIva1() {
        return fiscalResponsabilidadesIva1;
    }

    public void setFiscalResponsabilidadesIva1(FiscalResponsabilidadesIva fiscalResponsabilidadesIva1) {
        this.fiscalResponsabilidadesIva1 = fiscalResponsabilidadesIva1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fiscalLetrasComprobantesPK != null ? fiscalLetrasComprobantesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiscalLetrasComprobantes)) {
            return false;
        }
        FiscalLetrasComprobantes other = (FiscalLetrasComprobantes) object;
        if ((this.fiscalLetrasComprobantesPK == null && other.fiscalLetrasComprobantesPK != null) || (this.fiscalLetrasComprobantesPK != null && !this.fiscalLetrasComprobantesPK.equals(other.fiscalLetrasComprobantesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalLetrasComprobantes[ fiscalLetrasComprobantesPK=" + fiscalLetrasComprobantesPK + " ]";
    }
    
}
