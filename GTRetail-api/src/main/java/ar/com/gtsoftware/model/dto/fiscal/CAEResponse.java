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
package ar.com.gtsoftware.model.dto.fiscal;

import java.io.Serializable;
import java.util.Date;

/**
 * CAE de respuesta para un comprobante
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class CAEResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private long cae;
    private Date fechaVencimientoCae;

    public CAEResponse(long cae, Date fechaVencimientoCae) {
        this.cae = cae;
        this.fechaVencimientoCae = fechaVencimientoCae;
    }

    public CAEResponse() {
    }

    public long getCae() {
        return cae;
    }

    public void setCae(long cae) {
        this.cae = cae;
    }

    public Date getFechaVencimientoCae() {
        return fechaVencimientoCae;
    }

    public void setFechaVencimientoCae(Date fechaVencimientoCae) {
        this.fechaVencimientoCae = fechaVencimientoCae;
    }

}
