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

import ar.com.gtsoftware.pages.AbstractPageManager;
import ar.com.gtsoftware.pages.DefaultLoginPage;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class PageManager extends AbstractPageManager {

    private static final String URL = "autoconsulta-web";
    private static final Logger LOG = Logger.getLogger(PageManager.class.getName());

    private final DefaultLoginPage loginPage;
    private final HomePage homePage;
    private final SearchClientesPage searchUsuariosPage;

    public PageManager() {
        super();
        getDriver().get(String.format("%s/%s", getHost(), URL));

        loginPage = new DefaultLoginPage(getDriver());
        homePage = new HomePage(getDriver());
        searchUsuariosPage = new SearchClientesPage(getDriver());
    }

    public static String getURL() {
        return URL;
    }

    public DefaultLoginPage getLoginPage() {
        return loginPage;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public SearchClientesPage getSearchUsuariosPage() {
        return searchUsuariosPage;
    }

}
