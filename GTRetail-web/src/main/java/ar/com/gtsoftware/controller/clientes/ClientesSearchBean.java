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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "clientesSearchBean")
@ViewScoped
public class ClientesSearchBean implements Serializable {

    @EJB
    private PersonasFacade personasFacade;
    private List<Personas> clientesList = new ArrayList<>();
    private Personas clienteActual;

    private PersonasSearchFilter filter = new PersonasSearchFilter(Boolean.TRUE, Boolean.TRUE, null);

    /**
     * Creates a new instance of ClientesSearchBean
     */
    public ClientesSearchBean() {
    }

    @PostConstruct
    private void init(){
        //Logger.getLogger(ClientesSearchBean.class.getName()).log(Level.INFO, "Post Construct...", 0);
    }
    public PersonasSearchFilter getFilter() {
        return filter;
    }

    public void setFilter(PersonasSearchFilter filter) {
        this.filter = filter;
    }

    public void doSearch() {
        clientesList.clear();
        clientesList.addAll(personasFacade.findBySearchFilter(filter));
    }

    public List<Personas> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Personas> clientesList) {
        this.clientesList = clientesList;
    }

    public Personas getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Personas clienteActual) {
        this.clienteActual = clienteActual;
    }

}
