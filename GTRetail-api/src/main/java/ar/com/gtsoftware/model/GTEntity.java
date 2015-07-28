/*
 * Copyright 2015 GT Software.
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
package ar.com.gtsoftware.model;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Superclase para todas las entidades del sistema
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@MappedSuperclass
public abstract class GTEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Integer version;

    /**
     * Determina si la entidad es nueva o no
     *
     * @return
     */
    public abstract boolean isNew();

    /**
     * Retorna el objeto que es la clave de la entidad
     *
     * @return
     */
    public abstract Object getId();

    /**
     * Retorna un objeto clave primaria a partir de la representación en String del Id
     *
     * @param id
     * @return
     */
    public abstract Object calculateId(String id);

    /**
     * Retorna la representación en String del ID de la clase que puede ser vuelta a convertir en ID
     *
     * @return
     */
    public abstract String getStringId();

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
