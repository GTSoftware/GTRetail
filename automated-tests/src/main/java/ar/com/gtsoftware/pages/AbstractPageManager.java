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

import io.github.bonigarcia.wdm.MarionetteDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public abstract class AbstractPageManager {

    private final WebDriver driver;
    private static final Logger LOG = Logger.getLogger(AbstractPageManager.class.getName());
    private String host;
    private final ScreenshotHelper screenshotHelper;
    private File screenShotsFolder;

    public AbstractPageManager() {
        host = System.getProperty("webdriver.base.host");
        if (StringUtils.isEmpty(host)) {
            LOG.log(Level.WARNING,
                    "Se tomará el host por defecto en localhost:8080 porque no se ha establecido la variable webdriver.base.host");
            host = "http://localhost:8080";
        }

        MarionetteDriverManager.getInstance().setup("0.7.1");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new MarionetteDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        try {
            screenShotsFolder = new File("screenshots");
            FileUtils.forceMkdir(screenShotsFolder);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        screenshotHelper = new ScreenshotHelper();
    }

    public final WebDriver getDriver() {
        return driver;
    }

    public final String getHost() {
        return host;
    }

    private class ScreenshotHelper {

        public void saveScreenshot(String screenshotFileName) throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(screenShotsFolder.getAbsolutePath() + System.getProperty("file.separator") + screenshotFileName));
        }
    }

    public void saveScreenshot(String name) {
        try {
            screenshotHelper.saveScreenshot(name);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
