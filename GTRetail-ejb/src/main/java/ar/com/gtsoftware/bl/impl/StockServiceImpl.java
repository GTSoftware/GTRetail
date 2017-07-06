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
package ar.com.gtsoftware.bl.impl;

import ar.com.gtsoftware.bl.StockService;
import ar.com.gtsoftware.model.Remito;
import javax.ejb.Stateless;

@Stateless
public class StockServiceImpl implements StockService {

    @Override
    public void procesarRemito(Remito remito) {
        //TODO ver desde donde va el remito
        //Analizar la última recepción y actualziar el stock en ese depósito según corresponda el origen del remito.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
