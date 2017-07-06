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
package ar.com.gtsoftware.pages.gtretail;

import ar.com.gtsoftware.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Representa a la pantalla de búsqueda de usuarios
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class SearchClientesPage extends BasePage {

    public SearchClientesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement searchUserTextBox() {
        return getDriver().findElement(By.xpath(".//*[@id='searchUserForm:txtBusField']"));
    }

    public WebElement searchButton() {
        return getDriver().findElement(By.xpath(".//*[@id='searchUserForm:buscarButton']"));
    }

    public boolean searchUsuario(String txt) {
        searchUserTextBox().click();
        searchUserTextBox().sendKeys(txt);
        searchButton().click();
        return true;
    }

    public WebElement filaTablaUsuario(int fila) {
        return getDriver().findElement(By.xpath(String.format(".//*[@id='searchUserForm:tablaUsuarios_data']/tr/td[%d]",
                fila)));
    }

}
