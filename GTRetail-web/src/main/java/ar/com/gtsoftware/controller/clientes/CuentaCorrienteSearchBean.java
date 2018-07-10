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
package ar.com.gtsoftware.controller.clientes;

import ar.com.gtsoftware.controller.search.AbstractSearchBean;
import ar.com.gtsoftware.eao.AbstractFacade;
import ar.com.gtsoftware.eao.PersonasCuentaCorrienteFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.PersonasCuentaCorriente;
import ar.com.gtsoftware.search.PersonasCuentaCorrienteSearchFilter;
import ar.com.gtsoftware.search.SortField;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ar.com.gtsoftware.utils.JSFUtil.*;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "cuentaCorrienteSearchBean")
@ViewScoped
public class CuentaCorrienteSearchBean extends AbstractSearchBean<PersonasCuentaCorriente, PersonasCuentaCorrienteSearchFilter> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CuentaCorrienteSearchBean.class.getName());

    @EJB
    private PersonasCuentaCorrienteFacade facade;
    @EJB
    private PersonasFacade personasFacade;


    private final PersonasCuentaCorrienteSearchFilter filter = new PersonasCuentaCorrienteSearchFilter();

    /**
     * Creates a new instance of CuentaCorrienteSearchBean
     */
    public CuentaCorrienteSearchBean() {
    }

    public void init() {
        if (isPostback()) {
            return;
        }
        String idPersona = getRequestParameterMap().get("idPersona");
        if (idPersona == null) {
            throw new IllegalArgumentException("Parámetro nulo!");
        } else {
            Personas persona = personasFacade.find(Long.parseLong(idPersona));
            if (persona == null) {

                addErrorMessage("Cliente inexistente!");
                LOG.log(Level.INFO, "Cliente inexistente!");
            } else {
                filter.setPersona(persona);
            }
        }
    }

    @Override
    protected AbstractFacade<PersonasCuentaCorriente, PersonasCuentaCorrienteSearchFilter> getFacade() {
        return facade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField(new SortField("fechaMovimiento", false));
        }
    }

    @Override
    public PersonasCuentaCorrienteSearchFilter getFilter() {
        return filter;
    }

    public BigDecimal getSaldo() {
        if (filter.getPersona() == null) {
            return BigDecimal.ZERO;
        }
        return facade.getSaldoPersona(filter.getPersona());
    }

}
