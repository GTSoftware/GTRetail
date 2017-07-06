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
package ar.com.gtsoftware.model.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO utilizado para la registración de facturas por REST
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class RegistrarFacturaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idComprobante;
    private Integer puntoVenta;
    private Long numeroComprobante;

    public RegistrarFacturaDTO() {
    }

    public RegistrarFacturaDTO(Long idComprobante, Integer puntoVenta, Long numeroComprobante) {
        this.idComprobante = idComprobante;
        this.puntoVenta = puntoVenta;
        this.numeroComprobante = numeroComprobante;
    }

    public Long getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Long idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Integer getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(Integer puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public Long getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(Long numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idComprobante);
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
        final RegistrarFacturaDTO other = (RegistrarFacturaDTO) obj;
        if (!Objects.equals(this.idComprobante, other.idComprobante)) {
            return false;
        }
        return true;
    }

}
