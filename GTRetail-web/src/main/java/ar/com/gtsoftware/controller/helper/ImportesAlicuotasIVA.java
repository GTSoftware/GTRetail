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
package ar.com.gtsoftware.controller.helper;


import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Almacena el importe que se genera por cada alícuota de IVA
 * 
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
public class ImportesAlicuotasIVA implements Serializable {

    private FiscalAlicuotasIva alicuota;
    private BigDecimal importe;

    public ImportesAlicuotasIVA(FiscalAlicuotasIva alicuota, BigDecimal importe) {
        this.alicuota = alicuota;
        this.importe = importe;
    }

    public FiscalAlicuotasIva getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(FiscalAlicuotasIva alicuota) {
        this.alicuota = alicuota;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.alicuota);
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
        final ImportesAlicuotasIVA other = (ImportesAlicuotasIVA) obj;
        if (!Objects.equals(this.alicuota, other.alicuota)) {
            return false;
        }
        return true;
    }
}
