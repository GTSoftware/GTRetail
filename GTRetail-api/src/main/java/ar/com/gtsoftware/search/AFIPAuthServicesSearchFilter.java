/*
 * Copyright 2016 GT Software.
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

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class AFIPAuthServicesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String service;

    private Boolean noExpirado;

    @Override
    public boolean hasFilter() {
        return StringUtils.isNotEmpty(service) || noExpirado != null;
    }

    public AFIPAuthServicesSearchFilter() {
    }

    public AFIPAuthServicesSearchFilter(String service, Boolean noExpirado) {
        this.service = service;
        this.noExpirado = noExpirado;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getNoExpirado() {
        return noExpirado;
    }

    public void setNoExpirado(Boolean noExpirado) {
        this.noExpirado = noExpirado;
    }

}
