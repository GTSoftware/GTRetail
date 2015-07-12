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

import java.math.BigDecimal;
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
@Table(name = "fiscal_alicuotas_iva")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_alicuota_iva"))
public class FiscalAlicuotasIva extends BaseEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombre_alicuota_iva")
    private String nombreAlicuotaIva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_alicuota")
    private BigDecimal valorAlicuota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gravar_iva")
    private boolean gravarIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = true)
    @Column(name = "fiscal_codigo_alicuota")
    private Integer fiscalCodigoAlicuota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlicuotaIva")
    private List<Productos> productosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlicuotaIva")
    private List<FiscalLibroIvaVentasLineas> fiscalLibroIvaVentasLineasList;

    public FiscalAlicuotasIva() {
    }

    public FiscalAlicuotasIva(Long idAlicuotaIva) {
        super(idAlicuotaIva);
    }

    public FiscalAlicuotasIva(Long idAlicuotaIva, String nombreAlicuotaIva, BigDecimal valorAlicuota, boolean gravarIva, boolean activo) {
        super(idAlicuotaIva);
        this.nombreAlicuotaIva = nombreAlicuotaIva;
        this.valorAlicuota = valorAlicuota;
        this.gravarIva = gravarIva;
        this.activo = activo;
    }

    public String getNombreAlicuotaIva() {
        return nombreAlicuotaIva;
    }

    public void setNombreAlicuotaIva(String nombreAlicuotaIva) {
        this.nombreAlicuotaIva = nombreAlicuotaIva;
    }

    public BigDecimal getValorAlicuota() {
        return valorAlicuota;
    }

    public void setValorAlicuota(BigDecimal valorAlicuota) {
        this.valorAlicuota = valorAlicuota;
    }

    public boolean getGravarIva() {
        return gravarIva;
    }

    public void setGravarIva(boolean gravarIva) {
        this.gravarIva = gravarIva;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    @XmlTransient
    public List<FiscalLibroIvaVentasLineas> getFiscalLibroIvaVentasLineasList() {
        return fiscalLibroIvaVentasLineasList;
    }

    public void setFiscalLibroIvaVentasLineasList(List<FiscalLibroIvaVentasLineas> fiscalLibroIvaVentasLineasList) {
        this.fiscalLibroIvaVentasLineasList = fiscalLibroIvaVentasLineasList;
    }

    /**
     * Devuelve el código de alícuota para regimenes informativos
     *
     * @return
     */
    public Integer getFiscalCodigoAlicuota() {
        return fiscalCodigoAlicuota;
    }

    /**
     * Establece el código de alícuota para regimenes informativos
     *
     * @param fiscalCodigoAlicuota
     */
    public void setFiscalCodigoAlicuota(Integer fiscalCodigoAlicuota) {
        this.fiscalCodigoAlicuota = fiscalCodigoAlicuota;
    }

}
