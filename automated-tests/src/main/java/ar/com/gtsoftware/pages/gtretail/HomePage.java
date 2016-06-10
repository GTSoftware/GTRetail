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
 * Representa a la página inicial del sistema
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement salirLink() {
        return getDriver().findElement(By.xpath(".//*[@id='j_idt8:exitLink']"));
    }

    public WebElement administracionMenu() {
        return getDriver().findElement(By.xpath(".//*[@id='formMenu:j_idt24']/ul/li[2]/a/span[2]"));
    }

    public WebElement administrarUsuariosMenuItem() {
        return getDriver().findElement(By.xpath(".//*[@id='formMenu:j_idt24']/ul/li[2]/ul/li[2]/a/span[2]"));
    }

    public boolean goAdministrarUsuariosPage() {
        administracionMenu().click();
        administrarUsuariosMenuItem().click();
        return true;
    }
}
