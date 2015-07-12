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
package ar.com.gtsoftware.bl.fiscal;

import java.math.BigDecimal;
import org.beanio.types.BigDecimalTypeHandler;

/**
 * Manejador de tipo para los valores numéricos con dos dígitos decimales
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @since 2.0.1
 * @version 1.0.0
 */
public class ImporteTypeHandler extends BigDecimalTypeHandler {

    private static final BigDecimal HUNDRED = new BigDecimal(100);

    @Override
    protected BigDecimal createNumber(BigDecimal bg) throws ArithmeticException {
        BigDecimal importe = super.createNumber(bg);
        return importe.divide(HUNDRED);
    }

    @Override
    protected BigDecimal createNumber(String text) throws NumberFormatException {
        BigDecimal importe = super.createNumber(text);
        return importe;
    }

    @Override
    public Class<?> getType() {
        return BigDecimal.class;
    }

    @Override
    public String format(Object value) {
        return ((Number) value).toString().replaceAll("\\.", "");
    }

}
