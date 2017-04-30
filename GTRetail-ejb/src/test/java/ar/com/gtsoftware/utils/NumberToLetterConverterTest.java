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
package ar.com.gtsoftware.utils;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
public class NumberToLetterConverterTest {

    public NumberToLetterConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of convertNumberToLetter method, of class NumberToLetterConverter.
     */
    @Test
    public void testConvertNumberToLetter() {
        System.out.println("convertNumberToLetter");
        double number = 0.0;
        String expResult = "CERO PESOS";
        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of convertNumberToLetter method, of class NumberToLetterConverter.
     */
    @Test
    public void test1989ConvertNumberToLetter() {
        System.out.println("convertNumberToLetter 1989");
        double number = 1989;
        String expResult = "MIL NOVECIENTOS OCHENTA Y NUEVE  PESOS";
        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
    }

    @Test
    public void testImporteConvertNumberToLetter() {
        System.out.println("convertNumberToLetter 125.43");
        double number = 125.43;
        String expResult = "CIENTO VEINTICINCO  PESOS CON CUARENTA Y TRES CENTAVOS";
        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertNumberToLetter method, of class NumberToLetterConverter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegative() {
        System.out.println("testNegative");
        double number = -1;
        //String expResult = "MENOS UNO  PESOS ";

        String result = NumberToLetterConverter.convertNumberToLetter(number);
        fail("No se deben poder convertir números negativos");

    }

    @Test
    public void testUnCentavo() {
        System.out.println("unCentavo");
        double number = 0.01;
        String expResult = "CERO PESOS CON UN CENTAVO";
        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
    }

    @Test
    public void testMil() {
        System.out.println("mil");
        double number = 1000;
        String expResult = "UN MIL  PESOS";
        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
    }

    @Test
    public void testMaxNumber() {
        System.out.println("testMaxNumber");
        double number = 9999999;
        String expResult = "NUEVE MILLONES NOVECIENTOS NOVENTA Y NUEVE MIL NOVECIENTOS NOVENTA Y NUEVE  PESOS";
        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
    }

    @Test
    public void testOneMillon() {
        System.out.println("testUnMillon");
        double number = 1000000;
        String expResult = "UN MILLON DE PESOS";
        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
    }

    @Test
    public void testOneMillonOneCent() {
        System.out.println("testUnMillonUnCentavo");
        double number = 1000000.01;
        String expResult = "UN MILLON DE PESOS CON UN CENTAVO";

        String result = NumberToLetterConverter.convertNumberToLetter(number);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfRange() {
        System.out.println("testOutOfRange");
        double number = 999999999.01;

        String result = NumberToLetterConverter.convertNumberToLetter(number);
        fail("Número fuera de rango");

    }
}
