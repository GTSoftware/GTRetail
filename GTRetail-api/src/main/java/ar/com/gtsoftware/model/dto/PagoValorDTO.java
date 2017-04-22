/*
 * Copyright 2017 GT Software.
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

import ar.com.gtsoftware.model.ComprobantesPagos;
import ar.com.gtsoftware.model.Cupones;
import java.io.Serializable;

/**
 * Relaciona un pago de comprobante junto con el valor (de ser necesario) requerido para la forma de pago.
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class PagoValorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ComprobantesPagos pago;
    private Cupones cupon;
    private final int item;

    public PagoValorDTO(int item) {
        this.item = item;
    }

    public PagoValorDTO(int item, ComprobantesPagos pago, Cupones valor) {
        this.item = item;
        this.pago = pago;
        this.cupon = valor;
    }

    public ComprobantesPagos getPago() {
        return pago;
    }

    public void setPago(ComprobantesPagos pago) {
        this.pago = pago;
    }

    public Cupones getCupon() {
        return cupon;
    }

    public void setCupon(Cupones cupon) {
        this.cupon = cupon;
    }

    public int getItem() {
        return item;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.item;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PagoValorDTO other = (PagoValorDTO) obj;
        if (this.item != other.item) {
            return false;
        }
        return true;
    }

}
