/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.model;

import ar.com.gtsoftware.rules.TipoAccion;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ofertas")
@AttributeOverride(name = "id", column = @Column(name = "id_oferta", columnDefinition = "serial"))
public class Ofertas extends BaseEntity implements Serializable {

    @NotNull
    @Column(name = "texto_oferta")
    @Size(max = 90)
    private String textoOferta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_accion")
    @NotNull
    private TipoAccion tipoAccion;

    @Column(name = "descuento")
    @NotNull
    private BigDecimal descuento;

    @NotNull
    @Column(name = "vigencia_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciaDesde;

    @NotNull
    @Column(name = "vigencia_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vigenciaHasta;

    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne
    private Sucursales idSucursal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOferta", orphanRemoval = true)
    @JoinColumn(name = "id_oferta", referencedColumnName = "id_oferta")
    private List<OfertasCondiciones> condiciones;

    public Ofertas() {
    }

    @NotNull
    public String getTextoOferta() {
        return textoOferta;
    }

    public void setTextoOferta(@NotNull String textoOferta) {
        this.textoOferta = textoOferta;
    }

    @NotNull
    public TipoAccion getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(@NotNull TipoAccion tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    @NotNull
    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(@NotNull BigDecimal descuento) {
        this.descuento = descuento;
    }

    @NotNull
    public Date getVigenciaDesde() {
        return vigenciaDesde;
    }

    public void setVigenciaDesde(@NotNull Date vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }

    @NotNull
    public Date getVigenciaHasta() {
        return vigenciaHasta;
    }

    public void setVigenciaHasta(@NotNull Date vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    public List<OfertasCondiciones> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(List<OfertasCondiciones> condiciones) {
        this.condiciones = condiciones;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }
}
