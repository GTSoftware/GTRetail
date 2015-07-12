/*
 * Copyright 2015 GT Software.
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
package ar.com.gtsoftware.bl.fiscal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.beanio.Marshaller;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @since 2.0.1
 * @version 1.0.0
 */
public class RegimenInformativoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ReginfoCvCabecera cabecera;

    private List<ReginfoCvVentasCbte> ventasList;

    private List<RegInfoCvVentasAlicuotas> ventasAlicuotasList;

    public RegimenInformativoDTO() {
    }

    public ReginfoCvCabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(ReginfoCvCabecera cabecera) {
        this.cabecera = cabecera;
    }

    public List<ReginfoCvVentasCbte> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<ReginfoCvVentasCbte> ventasList) {
        this.ventasList = ventasList;
    }

    public List<RegInfoCvVentasAlicuotas> getVentasAlicuotasList() {
        return ventasAlicuotasList;
    }

    public void setVentasAlicuotasList(List<RegInfoCvVentasAlicuotas> ventasAlicuotasList) {
        this.ventasAlicuotasList = ventasAlicuotasList;
    }

    public String generarCabecera() {

        StreamFactory factory;
        factory = StreamFactory.newInstance();

        StreamBuilder builder = new StreamBuilder(ReginfoCvCabecera.FILE_NAME)
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addTypeHandler(BigDecimal.class, new ImporteTypeHandler())
                .addRecord(ReginfoCvCabecera.class);

        factory.define(builder);

        Marshaller marshaller = factory.createMarshaller(ReginfoCvCabecera.FILE_NAME);
        marshaller.marshal(cabecera);
        return marshaller.toString();

    }

    public String generarVentas() {
        StringBuilder sb = new StringBuilder();
        StreamFactory factory;
        factory = StreamFactory.newInstance();

        StreamBuilder builder = new StreamBuilder(ReginfoCvVentasCbte.FILE_NAME)
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addTypeHandler(BigDecimal.class, new ImporteTypeHandler())
                .addRecord(ReginfoCvVentasCbte.class);

        factory.define(builder);

        Marshaller marshaller = factory.createMarshaller(ReginfoCvVentasCbte.FILE_NAME);
        for (ReginfoCvVentasCbte v : ventasList) {
            sb.append(marshaller.marshal(v).toString());
            sb.append("\n");
        }
        return sb.toString();

    }

    public String generarVentasAlicuotas() {
        StringBuilder sb = new StringBuilder();
        StreamFactory factory;
        factory = StreamFactory.newInstance();

        StreamBuilder builder = new StreamBuilder(RegInfoCvVentasAlicuotas.FILE_NAME)
                .format("fixedlength")
                .parser(new FixedLengthParserBuilder())
                .addTypeHandler(BigDecimal.class, new ImporteTypeHandler())
                .addRecord(RegInfoCvVentasAlicuotas.class);

        factory.define(builder);

        Marshaller marshaller = factory.createMarshaller(RegInfoCvVentasAlicuotas.FILE_NAME);
        for (RegInfoCvVentasAlicuotas va : ventasAlicuotasList) {
            sb.append(marshaller.marshal(va).toString());
            sb.append("\n");
        }
        return sb.toString();

    }
}
