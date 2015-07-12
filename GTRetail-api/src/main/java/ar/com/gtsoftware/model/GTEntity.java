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

/**
 * Superclase para todas las entidades del sistema
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
public abstract class GTEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Determina si la entidad es nueva o no
     *
     * @return
     */
    public abstract boolean isNew();
}