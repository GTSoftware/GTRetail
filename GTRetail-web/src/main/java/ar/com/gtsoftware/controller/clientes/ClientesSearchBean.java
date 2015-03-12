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

import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.utils.LazyEntityDataModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "clientesSearchBean")
@ViewScoped
public class ClientesSearchBean implements Serializable {

    @EJB
    private PersonasFacade personasFacade;
    private Personas clienteActual;
    private DataModel<Personas> dataModel;

    private PersonasSearchFilter filter = new PersonasSearchFilter(Boolean.TRUE, Boolean.TRUE, null);

    /**
     * Creates a new instance of ClientesSearchBean
     */
    public ClientesSearchBean() {
    }

    @PostConstruct
    private void init() {

    }

    public PersonasSearchFilter getFilter() {
        return filter;
    }

    public void setFilter(PersonasSearchFilter filter) {
        this.filter = filter;
    }

    public void doSearch() {
        dataModel = null;
    }

    public DataModel<Personas> getDataModel() {
        if (dataModel == null) {
            dataModel = new LazyEntityDataModel<>(personasFacade, filter);
        }
        return dataModel;
    }

    public Personas getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Personas clienteActual) {
        this.clienteActual = clienteActual;
    }

}
