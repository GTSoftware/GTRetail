/*
 * Copyright 2018 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.dto;

import java.io.Serializable;

/**
 * Intefaz que deben implementar todos los DTOs que pueden ser identificables, es decir que pueden distinguirse unos a
 * otros mediante algún o algunos de sus atributos representados por un String.
 */
public interface IdentifiableDto extends Serializable {

    String getStringId();
}
