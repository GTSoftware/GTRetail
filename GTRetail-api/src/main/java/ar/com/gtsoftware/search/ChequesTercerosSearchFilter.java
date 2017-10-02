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
package ar.com.gtsoftware.search;

import ar.com.gtsoftware.model.Bancos;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 * Search filter para cheques de terceros
 *
 * @author Rodrigo Tato mailto:rotatomel@gmail.com
 */
public class ChequesTercerosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String cuitOriginante;
    private String nombreOriginante;
    private Bancos idBanco;
    private Date fechaOrigenDesde;
    private Date fechaOrigenHasta;
    private Boolean noVencidos;
    private Boolean noCobrados;

    public ChequesTercerosSearchFilter() {
    }

    @Override
    public boolean hasFilter() {
        return hasFilterBanco()
                || hasFilterCuitOriginante()
                || hasFilterFechaOrigen()
                || hasFilterNoCobrados()
                || hasFilterNoVencidos()
                || hasFilterNombreOriginante();
    }

    public boolean hasFilterCuitOriginante() {
        return StringUtils.isNotEmpty(cuitOriginante);
    }

    public boolean hasFilterNombreOriginante() {
        return StringUtils.isNotEmpty(nombreOriginante);
    }

    public boolean hasFilterBanco() {
        return idBanco != null;
    }

    public boolean hasFilterFechaOrigen() {
        return fechaOrigenDesde != null && fechaOrigenHasta != null && (fechaOrigenHasta.compareTo(fechaOrigenDesde) >= 0);
    }

    public boolean hasFilterNoVencidos() {
        return noVencidos != null;
    }

    public boolean hasFilterNoCobrados() {
        return noCobrados != null;
    }

    public String getCuitOriginante() {
        return cuitOriginante;
    }

    public void setCuitOriginante(String cuitOriginante) {
        this.cuitOriginante = cuitOriginante;
    }

    public String getNombreOriginante() {
        return nombreOriginante;
    }

    public void setNombreOriginante(String nombreOriginante) {
        this.nombreOriginante = nombreOriginante;
    }

    public Bancos getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Bancos idBanco) {
        this.idBanco = idBanco;
    }

    public Date getFechaOrigenDesde() {
        return fechaOrigenDesde;
    }

    public void setFechaOrigenDesde(Date fechaOrigenDesde) {
        this.fechaOrigenDesde = fechaOrigenDesde;
    }

    public Date getFechaOrigenHasta() {
        return fechaOrigenHasta;
    }

    public void setFechaOrigenHasta(Date fechaOrigenHasta) {
        this.fechaOrigenHasta = fechaOrigenHasta;
    }

    public Boolean getNoVencidos() {
        return noVencidos;
    }

    public void setNoVencidos(Boolean noVencidos) {
        this.noVencidos = noVencidos;
    }

    public Boolean getNoCobrados() {
        return noCobrados;
    }

    public void setNoCobrados(Boolean noCobrados) {
        this.noCobrados = noCobrados;
    }

}
