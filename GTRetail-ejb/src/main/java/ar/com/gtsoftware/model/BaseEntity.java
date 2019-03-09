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
package ar.com.gtsoftware.model;

import javax.persistence.*;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@MappedSuperclass
public abstract class BaseEntity extends GTEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public String getStringId() {
        if (id != null) {
            return id.toString();
        }
        return null;
    }

    @Override
    public Long calculateId(String id) {
        if (id != null) {
            try {
                return Long.parseLong(id);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

}
