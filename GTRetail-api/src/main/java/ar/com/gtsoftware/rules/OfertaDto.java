/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.rules;

import ar.com.gtsoftware.dto.IdentifiableDto;
import ar.com.gtsoftware.dto.model.SucursalesDto;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaDto implements IdentifiableDto {

    @EqualsAndHashCode.Include
    private Long id;

    @Size(max = 90)
    @NotNull
    private String textoOferta;

    private List<Condicion> condiciones;

    @NotNull
    private TipoAccion tipoAccion;

    @NotNull
    private BigDecimal descuento;

    private SucursalesDto idSucursal;

    @NotNull
    private Date vigenciaDesde;

    @NotNull
    private Date vigenciaHasta;

    private int version;

    public Map<String, Object> toMap() throws CondicionIlegalException {

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("nombre", textoOferta);
        attributes.put("condiciones", conticionesAsDrl());
        attributes.put("tipoAccion", tipoAccion);
        attributes.put("descuento", descuento.toPlainString());

        return attributes;
    }

    private String conticionesAsDrl() throws CondicionIlegalException {
        if (CollectionUtils.isEmpty(condiciones)) {
            throw new IllegalStateException("Se debe declarar al menos una condición para evaluar");
        }

        StringBuilder drl = new StringBuilder();
        //For each condition of this rule, we create its textual representation
        for (int i = 0; i < condiciones.size(); i++) {
            Condicion condition = condiciones.get(i);
            drl.append("(");
            drl.append(condition.buildExpression());
            drl.append(")");
            if ((i + 1) < condiciones.size()) {
                drl.append(" && ");
            }
        }
        return drl.toString();
    }

    @Override
    public String getStringId() {
        return String.valueOf(id);
    }
}
