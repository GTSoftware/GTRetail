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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Clase que almacena la información necesaria para un libro de IVA Ventas
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 * @since 1.0.0
 * @version 1.0.0
 */
public class LibroIVADTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fechaDesde;
    private Date fechaHasta;
    private Date fechaGeneracion;

    private List<FacturaDTO> facturasList;
    private BigDecimal importeTotal;
    private BigDecimal importeTotalIVA;
    private Map<FiscalResponsabilidadesIva, BigDecimal> totalesIVAResponsabilidad;
    private Map<FiscalAlicuotasIva, BigDecimal> totalesAlicuota;

    /**
     * Crea un nuevo Libro de IVA
     */
    public LibroIVADTO() {
        this.fechaGeneracion = GregorianCalendar.getInstance().getTime();
    }

    /**
     * Crea un nuevo Libro de IVA con las fechas especificadas
     *
     * @param fechaDesde
     * @param fechaHasta
     */
    public LibroIVADTO(Date fechaDesde, Date fechaHasta) {
        this.fechaGeneracion = GregorianCalendar.getInstance().getTime();
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    /**
     * Devuelve la fecha desde
     *
     * @return fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Establece la fecha desde
     *
     * @param fechaDesde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Devuelve la fecha hasta
     *
     * @return fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Estable la fecha hasta
     *
     * @param fechaHasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Devuelve la fecha de generación del libro
     *
     * @return
     */
    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    /**
     * Establece la fecha de generacion del libro
     *
     * @param fechaGeneracion
     */
    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    /**
     * Devuelve la lista de facturas
     *
     * @return facturasList
     */
    public List<FacturaDTO> getFacturasList() {
        return facturasList;
    }

    /**
     * Establece la lista de facturas
     *
     * @param facturasList
     */
    public void setFacturasList(List<FacturaDTO> facturasList) {
        this.facturasList = facturasList;
    }

    /**
     * Devuelve el importe total de todas las facturas del libro
     *
     * @return
     */
    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    /**
     * Establece el importe total de todas las facturas del libro
     *
     * @param importeTotal
     */
    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    /**
     * Devuelve el importe total de IVA del libro
     *
     * @return importeTotalIVA
     */
    public BigDecimal getImporteTotalIVA() {
        return importeTotalIVA;
    }

    /**
     * Establece el importe total de IVA del libro
     *
     * @param importeTotalIVA
     */
    public void setImporteTotalIVA(BigDecimal importeTotalIVA) {
        this.importeTotalIVA = importeTotalIVA;
    }

    /**
     * Devuelve los totales de iva por responsabilidad de IVA
     *
     * @return
     */
    public Map<FiscalResponsabilidadesIva, BigDecimal> getTotalesIVAResponsabilidad() {
        return totalesIVAResponsabilidad;
    }

    /**
     * Establece los totales de iva por responsabilidad de IVA
     *
     * @param totalesIVAResponsabilidad
     */
    public void setTotalesIVAResponsabilidad(Map<FiscalResponsabilidadesIva, BigDecimal> totalesIVAResponsabilidad) {
        this.totalesIVAResponsabilidad = totalesIVAResponsabilidad;
    }

    /**
     * Devuelve el total de iva por alícuota
     *
     * @return
     */
    public Map<FiscalAlicuotasIva, BigDecimal> getTotalesAlicuota() {
        return totalesAlicuota;
    }

    /**
     * Establece el total de iva por alícuota
     *
     * @param totalesAlicuota
     */
    public void setTotalesAlicuota(Map<FiscalAlicuotasIva, BigDecimal> totalesAlicuota) {
        this.totalesAlicuota = totalesAlicuota;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.fechaGeneracion);
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
        final LibroIVADTO other = (LibroIVADTO) obj;
        if (!Objects.equals(this.fechaGeneracion, other.fechaGeneracion)) {
            return false;
        }
        return true;
    }

}
