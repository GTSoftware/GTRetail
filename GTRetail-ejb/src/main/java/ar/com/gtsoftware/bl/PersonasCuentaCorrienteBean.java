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
package ar.com.gtsoftware.bl;

import ar.com.gtsoftware.eao.PersonasCuentaCorrienteFacade;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.PersonasCuentaCorriente;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@Stateless
@LocalBean
public class PersonasCuentaCorrienteBean {

    @EJB
    private PersonasCuentaCorrienteFacade cuentaCorrienteFacade;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void registrarMovimientoCuenta(Personas persona, BigDecimal importe, String descripcion) {
        PersonasCuentaCorriente cc = new PersonasCuentaCorriente();
        cc.setDescripcionMovimiento(descripcion);
        cc.setFechaMovimiento(GregorianCalendar.getInstance().getTime());
        cc.setImporteMovimiento(importe);
        cc.setIdPersona(persona);
        //cc.setIdRegistroContable(null);
        cuentaCorrienteFacade.create(cc);
    }

    public BigDecimal obtenerSaldoPersona(Personas persona) {
        return cuentaCorrienteFacade.getSaldoPersona(persona);
    }

    public List<PersonasCuentaCorriente> obtenerUltimosMovimientos(Personas persona) {
        return cuentaCorrienteFacade.getUltimosMovimientos(persona);
    }
}
