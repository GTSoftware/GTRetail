/*
 * Copyright 2018 GT Software.
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

import ar.com.gtsoftware.bl.ParametrosService;
import ar.com.gtsoftware.dto.model.ParametrosDto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "parametrosSearchBean")
@ViewScoped
public class ParametrosSearchBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ParametrosService parametrosFacade;
    private List<ParametrosDto> parametrosList = new ArrayList<>();
    private String txt;

    /**
     * Creates a new instance of ParametrosEditBean
     */
    public ParametrosSearchBean() {
    }

    public List<ParametrosDto> getParametrosList() {
        return parametrosList;
    }

    public void setParametrosList(List<ParametrosDto> parametrosList) {
        this.parametrosList = parametrosList;
    }

    public void buscar() {
        parametrosList.clear();
        parametrosList.addAll(parametrosFacade.findParametros(txt));
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String editParametro(ParametrosDto param) {
        return String.format("parametroEdit.xhtml?faces-redirect=true;&nombreParametro=%s", param.getNombreParametro());
    }
}
