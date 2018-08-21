/*
 * Copyright 2018 GT Software.
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

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Clase que almacena la información de el detalle de la factura para el libro de iva compras
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Entity
@Table(name = "fiscal_libro_iva_compras_lineas")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_linea_libro"))
public class FiscalLibroIvaComprasLineas extends BaseEntity {

    private static final long serialVersionUID = 1L;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "neto_gravado")
    private BigDecimal netoGravado;
    @Column(name = "no_gravado")
    private BigDecimal noGravado;
    @Column(name = "importe_iva")
    private BigDecimal importeIva;
    @JoinColumn(name = "id_registro", referencedColumnName = "id_registro")
    @ManyToOne(optional = false)
    private FiscalLibroIvaCompras idRegistro;
    @JoinColumn(name = "id_alicuota_iva", referencedColumnName = "id_alicuota_iva")
    @ManyToOne(optional = false)
    private FiscalAlicuotasIva idAlicuotaIva;
    @Transient
    private int item;

    public FiscalLibroIvaComprasLineas() {
    }

    public FiscalLibroIvaComprasLineas(Long idLineaLibro) {
        super(idLineaLibro);
    }

    public BigDecimal getNetoGravado() {
        return netoGravado;
    }

    public void setNetoGravado(BigDecimal netoGravado) {
        this.netoGravado = netoGravado;
    }

    public BigDecimal getNoGravado() {
        return noGravado;
    }

    public void setNoGravado(BigDecimal noGravado) {
        this.noGravado = noGravado;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    public FiscalLibroIvaCompras getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(FiscalLibroIvaCompras idRegistro) {
        this.idRegistro = idRegistro;
    }

    public FiscalAlicuotasIva getIdAlicuotaIva() {
        return idAlicuotaIva;
    }

    public void setIdAlicuotaIva(FiscalAlicuotasIva idAlicuotaIva) {
        this.idAlicuotaIva = idAlicuotaIva;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ar.com.gtsoftware.model.FiscalLibroIvaComprasLineas[ idLineaLibro=" + this.getId() + " ]";
    }

}