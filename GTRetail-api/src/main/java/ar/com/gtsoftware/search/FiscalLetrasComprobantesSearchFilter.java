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

import ar.com.gtsoftware.model.FiscalResponsabilidadesIva;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class FiscalLetrasComprobantesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private FiscalResponsabilidadesIva ivaReceptor;
    private FiscalResponsabilidadesIva ivaEmisor;

    @Override
    public boolean hasFilter() {
        return ivaReceptor != null || ivaEmisor != null;
    }

    public FiscalLetrasComprobantesSearchFilter(FiscalResponsabilidadesIva ivaReceptor, FiscalResponsabilidadesIva ivaEmisor) {
        this.ivaReceptor = ivaReceptor;
        this.ivaEmisor = ivaEmisor;
    }

    public FiscalResponsabilidadesIva getIvaReceptor() {
        return ivaReceptor;
    }

    public void setIvaReceptor(FiscalResponsabilidadesIva ivaReceptor) {
        this.ivaReceptor = ivaReceptor;
    }

    public FiscalResponsabilidadesIva getIvaEmisor() {
        return ivaEmisor;
    }

    public void setIvaEmisor(FiscalResponsabilidadesIva ivaEmisor) {
        this.ivaEmisor = ivaEmisor;
    }

}
