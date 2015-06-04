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
package ar.com.gtsoftware.model.dto;

import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.FiscalResponsabilidadesIva;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Almacena el importe que se genera por cada responsabilidad de IVA
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ImportesResponsabilidad implements Serializable {

    private FiscalResponsabilidadesIva responsabilidadIva;
    private BigDecimal importeTotal;
    private BigDecimal ivaTotal;
    private BigDecimal netoGravadoTotal;
    private BigDecimal noGravadoTotal;

    public ImportesResponsabilidad() {
    }

    public ImportesResponsabilidad(FiscalResponsabilidadesIva responsabilidadIva) {
        this.responsabilidadIva = responsabilidadIva;
    }

    public ImportesResponsabilidad(FiscalResponsabilidadesIva responsabilidadIva, BigDecimal importeTotal, BigDecimal ivaTotal, BigDecimal netoGravadoTotal, BigDecimal noGravadoTotal) {
        this.responsabilidadIva = responsabilidadIva;
        this.importeTotal = importeTotal;
        this.ivaTotal = ivaTotal;
        this.netoGravadoTotal = netoGravadoTotal;
        this.noGravadoTotal = noGravadoTotal;
    }

    public FiscalResponsabilidadesIva getResponsabilidadIva() {
        return responsabilidadIva;
    }

    public void setResponsabilidadIva(FiscalResponsabilidadesIva responsabilidadIva) {
        this.responsabilidadIva = responsabilidadIva;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(BigDecimal ivaTotal) {
        this.ivaTotal = ivaTotal;
    }

    public BigDecimal getNetoGravadoTotal() {
        return netoGravadoTotal;
    }

    public void setNetoGravadoTotal(BigDecimal netoGravadoTotal) {
        this.netoGravadoTotal = netoGravadoTotal;
    }

    public BigDecimal getNoGravadoTotal() {
        return noGravadoTotal;
    }

    public void setNoGravadoTotal(BigDecimal noGravadoTotal) {
        this.noGravadoTotal = noGravadoTotal;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.responsabilidadIva);
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
        final ImportesResponsabilidad other = (ImportesResponsabilidad) obj;
        if (!Objects.equals(this.responsabilidadIva, other.responsabilidadIva)) {
            return false;
        }
        return true;
    }
}
