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
package ar.com.gtsoftware.automated.tests;

import ar.com.gtsoftware.pages.gtretail.PageManager;
import java.util.HashMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public abstract class AutomatedTest {

    private final HashMap<String, PageManager> pageManagers;

    public AutomatedTest() {
        this.pageManagers = new HashMap<>();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        pageManagers.get(result.getName()).getDriver().close();
    }

    public HashMap<String, PageManager> getPageManagers() {
        return pageManagers;
    }

}
