/*
 * Copyright 2017 GT Software.
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

import ar.com.gtsoftware.model.Depositos;
import ar.com.gtsoftware.model.Productos;

/**
 *
 * @author fede
 */
public class ProductoXDepositoSearchFilter extends AbstractSearchFilter {

    private Depositos idDeposito;
    private Productos idProducto;

    public boolean hasFilter() {
        return hasIdDeposito() || hasIdProducto();
    }

    public boolean hasIdProducto() {
        return idDeposito != null;
    }

    public boolean hasIdDeposito() {
        return idDeposito != null;
    }

    //----Getter  and Setter ------------------------------------------
    public Depositos getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Depositos idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

}
