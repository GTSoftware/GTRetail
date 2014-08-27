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
package ar.com.gtsoftware.controller.parametros;

import ar.com.gtsoftware.eao.ParametrosFacade;
import ar.com.gtsoftware.model.Parametros;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "parametrosEditBean")
@ViewScoped
public class ParametrosEditBean {

    @EJB
    private ParametrosFacade parametrosFacade;

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public ParametrosEditBean() {
    }

    public List<Parametros> getParametrosList() {
        return parametrosFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        Parametros paramEditado = (Parametros) event.getObject();
        parametrosFacade.edit(paramEditado);
        FacesMessage msg = new FacesMessage("Parámetro editado con éxito: " + paramEditado.getNombreParametro(), paramEditado.getValorParametro());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
