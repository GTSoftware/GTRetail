/*
 * Copyright 2016 Dilcar S.A..
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
package ar.com.gtsoftware.pages;

import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Representa a una página base de la que deben heredar todas las páginas de un sistema
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public abstract class BasePage {

    private final WebDriver driver;

    private static final Logger LOG = Logger.getLogger(BasePage.class.getName());

    public BasePage(WebDriver driver) {
        this.driver = driver;

    }

    public WebDriver getDriver() {
        return driver;
    }

}
