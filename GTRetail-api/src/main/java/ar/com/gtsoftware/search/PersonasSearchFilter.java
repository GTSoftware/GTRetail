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

import ar.com.gtsoftware.model.LegalTiposDocumento;

/**
 *
 * @author rodrigo
 */
public class PersonasSearchFilter extends AbstractSearchFilter {

    private String txt;
    private Integer idPersona;

    private String razonSocial;

    private String apellidos;

    private String nombres;

    private String nombreFantasia;
    private LegalTiposDocumento idTipoDocumento;
    private String documento;
    private Boolean activo;
    private Boolean cliente;
    private Boolean proveedor;

    public PersonasSearchFilter() {
    }

    public PersonasSearchFilter(Boolean activo, Boolean cliente, Boolean proveedor) {
        this.activo = activo;
        this.cliente = cliente;
        this.proveedor = proveedor;
    }

    public PersonasSearchFilter(LegalTiposDocumento idTipoDocumento, String documento, Boolean activo, Boolean cliente, Boolean proveedor) {
        this.idTipoDocumento = idTipoDocumento;
        this.documento = documento;
        this.activo = activo;
        this.cliente = cliente;
        this.proveedor = proveedor;
    }

   

    @Override
    public boolean hasFilter() {
        return (txt != null && !txt.isEmpty()) || (idPersona != null) || (razonSocial != null && !razonSocial.isEmpty())
                || (apellidos != null && !apellidos.isEmpty()) || (nombres != null && !nombres.isEmpty()) || (nombreFantasia != null && !nombreFantasia.isEmpty())
                || (idTipoDocumento != null) || (documento != null)
                || (activo != null) || (cliente != null) || (proveedor != null);
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public LegalTiposDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(LegalTiposDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean isCliente() {
        return cliente;
    }

    public void setCliente(Boolean cliente) {
        this.cliente = cliente;
    }

    public Boolean isProveedor() {
        return proveedor;
    }

    public void setProveedor(Boolean proveedor) {
        this.proveedor = proveedor;
    }

    
}
