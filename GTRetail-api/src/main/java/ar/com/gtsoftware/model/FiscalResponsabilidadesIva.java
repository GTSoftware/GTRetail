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
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Entity
@Table(name = "fiscal_responsabilidades_iva")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_resoponsabildiad_iva", columnDefinition = "serial"))
public class FiscalResponsabilidadesIva extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_responsabildiad")
    private String nombreResponsabildiad;
    @Basic(optional = true)
    @Column(name = "fiscal_codigo_responsable")
    private Integer fiscalCodigoResponsable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fiscalResponsabilidadesIva")
    private List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fiscalResponsabilidadesIva1")
    private List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsabilidadIva")
    private List<Bancos> bancosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsabilidadIva")
    private List<Personas> personasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsabilidadIva")
    private List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList;

    public FiscalResponsabilidadesIva() {
    }

    public FiscalResponsabilidadesIva(Long idResoponsabildiadIva) {
        super(idResoponsabildiadIva);
    }

    public FiscalResponsabilidadesIva(Long idResoponsabildiadIva, String nombreResponsabildiad) {
        super(idResoponsabildiadIva);
        this.nombreResponsabildiad = nombreResponsabildiad;
    }

    public String getNombreResponsabildiad() {
        return nombreResponsabildiad;
    }

    public void setNombreResponsabildiad(String nombreResponsabildiad) {
        this.nombreResponsabildiad = nombreResponsabildiad;
    }

    @XmlTransient
    public List<FiscalLetrasComprobantes> getFiscalLetrasComprobantesList() {
        return fiscalLetrasComprobantesList;
    }

    public void setFiscalLetrasComprobantesList(List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList) {
        this.fiscalLetrasComprobantesList = fiscalLetrasComprobantesList;
    }

    @XmlTransient
    public List<FiscalLetrasComprobantes> getFiscalLetrasComprobantesList1() {
        return fiscalLetrasComprobantesList1;
    }

    public void setFiscalLetrasComprobantesList1(List<FiscalLetrasComprobantes> fiscalLetrasComprobantesList1) {
        this.fiscalLetrasComprobantesList1 = fiscalLetrasComprobantesList1;
    }

    @XmlTransient
    public List<Bancos> getBancosList() {
        return bancosList;
    }

    public void setBancosList(List<Bancos> bancosList) {
        this.bancosList = bancosList;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @XmlTransient
    public List<FiscalLibroIvaVentas> getFiscalLibroIvaVentasList() {
        return fiscalLibroIvaVentasList;
    }

    public void setFiscalLibroIvaVentasList(List<FiscalLibroIvaVentas> fiscalLibroIvaVentasList) {
        this.fiscalLibroIvaVentasList = fiscalLibroIvaVentasList;
    }

    /**
     * Devuelve el código de responsabilidad para regímenes informativos
     *
     * @return
     */
    public Integer getFiscalCodigoResponsable() {
        return fiscalCodigoResponsable;
    }

    /**
     * Establece el código de responsabilidad para regímenes informativos
     *
     * @param fiscalCodigoResponsable
     */
    public void setFiscalCodigoResponsable(Integer fiscalCodigoResponsable) {
        this.fiscalCodigoResponsable = fiscalCodigoResponsable;
    }

}
