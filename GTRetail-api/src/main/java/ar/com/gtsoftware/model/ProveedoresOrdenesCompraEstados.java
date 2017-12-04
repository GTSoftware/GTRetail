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

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa a los posibles estados por los que puede pasar una Orden de Compra
 *
 * @author Rodrigo M. Tato Rothamel
 */
@Entity
@Table(name = "proveedores_ordenes_compra_estados")
@XmlRootElement
@AttributeOverride(name = "id", column = @Column(name = "id_estado_orden_compra", columnDefinition = "integer"))
public class ProveedoresOrdenesCompraEstados extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_estado_orden_compra")
    private String nombreEstado;

    public ProveedoresOrdenesCompraEstados() {
    }

    public ProveedoresOrdenesCompraEstados(Long idTipoCuentaBanco) {
        super(idTipoCuentaBanco);
    }

    public ProveedoresOrdenesCompraEstados(Long idTipoCuentaBanco, String nombreTipoCuenta) {
        super(idTipoCuentaBanco);
        this.nombreEstado = nombreTipoCuenta;
    }

    /**
     * El nombre del estado
     *
     * @return String
     */
    public String getNombreEstado() {
        return nombreEstado;
    }

    /**
     * El nombre del estado
     *
     * @param nombreEstado
     */
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

}
