/*
 * Copyright 2016 GT Software.
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
package ar.com.gtsoftware.model.dto.fiscal;

import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Dto para totalizar por alícuota de IVA
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class TotalesAlicuotas implements Serializable {

    private static final long serialVersionUID = 1L;

    private FiscalAlicuotasIva alicuota;
    private BigDecimal importeIva = BigDecimal.ZERO;
    private BigDecimal noGravado = BigDecimal.ZERO;
    private BigDecimal netoGravado = BigDecimal.ZERO;
    private BigDecimal importeExento = BigDecimal.ZERO;

    public TotalesAlicuotas(FiscalAlicuotasIva alicuota) {
        this.alicuota = alicuota;
    }

    public FiscalAlicuotasIva getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(FiscalAlicuotasIva alicuota) {
        this.alicuota = alicuota;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    public BigDecimal getNoGravado() {
        return noGravado;
    }

    public void setNoGravado(BigDecimal noGravado) {
        this.noGravado = noGravado;
    }

    public BigDecimal getNetoGravado() {
        return netoGravado;
    }

    public void setNetoGravado(BigDecimal netoGravado) {
        this.netoGravado = netoGravado;
    }

    public BigDecimal getImporteExento() {
        return importeExento;
    }

    public void setImporteExento(BigDecimal importeExento) {
        this.importeExento = importeExento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.alicuota);
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
        final TotalesAlicuotas other = (TotalesAlicuotas) obj;
        if (!Objects.equals(this.alicuota, other.alicuota)) {
            return false;
        }
        return true;
    }

    /**
     * Suma los totales de this con los recibidos de subTotal siempre que correspondan a la misma alícuota
     *
     * @param subTotal
     */
    public void add(TotalesAlicuotas subTotal) {
        if (!this.equals(subTotal)) {
            throw new IllegalArgumentException("No se pueden sumar alícuotas diferentes!");
        }
        this.importeExento = this.importeExento.add(subTotal.getImporteExento());
        this.importeIva = this.importeIva.add(subTotal.getImporteIva());
        this.netoGravado = this.netoGravado.add(subTotal.getNetoGravado());
        this.noGravado = this.noGravado.add(subTotal.getNoGravado());
    }
}
