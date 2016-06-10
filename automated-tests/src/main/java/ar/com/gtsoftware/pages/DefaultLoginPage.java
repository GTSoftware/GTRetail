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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Representa a la página de LogIn por defecto utilizable para cualquier sistema
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class DefaultLoginPage extends BasePage {

    public DefaultLoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement usernameTextBox() {
        return getDriver().findElement(By.name("j_username"));
    }

    public WebElement passwordTextBox() {
        return getDriver().findElement(By.name("j_password"));
    }

    public boolean logIn(String username, String password) {
        usernameTextBox().click();
        usernameTextBox().sendKeys(username);
        passwordTextBox().click();
        passwordTextBox().sendKeys(password);
        passwordTextBox().submit();
        return true;
    }
}
