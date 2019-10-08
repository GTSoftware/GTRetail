/*
 * Copyright 2019 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ar.com.gtsoftware.helper;

import ar.com.gtsoftware.auth.Roles;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
public class JSFHelper implements Serializable {

    public static final String JSF_REDIRECT = "?faces-redirect=true&amp;includeViewParams=true";
    public static final String JSF_REDIRECT_ESCAPED = "?faces-redirect=true&includeViewParams=true";
    private static final Logger logger = Logger.getLogger(JSFHelper.class.getName());

    public void addErrorMessage(String localizedMessage) {
        addMessage(localizedMessage, FacesMessage.SEVERITY_ERROR);
    }

    public void addInfoMessage(String localizedMessage) {
        addMessage(localizedMessage, FacesMessage.SEVERITY_INFO);
    }

    public void addMessage(String localizedMessage, FacesMessage.Severity severity) {
        addMessage(null, localizedMessage, severity);
    }

    public void addMessage(String clientId, String localizedMessage, FacesMessage.Severity severity) {
        FacesContext context = getFacesContext();
        UIComponent componet = null;
        if (clientId != null) {
            componet = context.getViewRoot().findComponent(clientId);
        }

        FacesMessage message = new FacesMessage();
        message.setSeverity(severity);
        message.setSummary(localizedMessage);
        message.setDetail(localizedMessage);
        if (componet == null) {
            context.addMessage(null, message);
        } else {
            context.addMessage(componet.getClientId(), message);
        }
    }

    public boolean isPostback() {
        return getFacesContext().isPostback();
    }

    public boolean isNotPostback() {
        return !isPostback();
    }

    public Map<String, String> getRequestParameterMap() {
        return getExternalContext().getRequestParameterMap();
    }

    public Map<String, Object> getSessionParameterMap() {
        return getExternalContext().getSessionMap();
    }

    public boolean isUserInRole(@NotNull Roles role) {
        return getExternalContext().isUserInRole(role.toString());
    }

    public ResourceBundle getDefaultLocaleBundle(String messajeBundle) {
        final FacesContext facesContext = getFacesContext();
        final Locale locale = facesContext.getViewRoot().getLocale();
        return ResourceBundle.getBundle(messajeBundle, locale);
    }

    public ResourceBundle getBundle(String varName) {
        final FacesContext context = getFacesContext();
        final Application app = getApplication();
        return app.getResourceBundle(context, varName);
    }

    public void logout(String redirectTo) {
        ExternalContext ec = getExternalContext();
        HttpServletRequest request = getRequest();
        try {
            getSession(false).invalidate();
            request.logout();
            ec.redirect(ec.getRequestContextPath() + redirectTo);
        } catch (ServletException | IOException e) {
            logger.log(Level.SEVERE, "Logout error: {0}", e.getMessage());
        }
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public Application getApplication() {
        return getFacesContext().getApplication();
    }

    public HttpSession getSession(boolean create) {
        return getRequest().getSession(create);
    }

    public void redirect(String nav) {
        NavigationHandler handler = getApplication().getNavigationHandler();
        handler.handleNavigation(getFacesContext(), null, nav);
    }

    public String getUserPrincipalName() {
        return getExternalContext().getUserPrincipal().getName();
    }

    public ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    public FacesContext getFacesContext() {
        final FacesContext fc = FacesContext.getCurrentInstance();
        if (fc == null) {
            throw new IllegalArgumentException("FacesContext is null");
        }
        return fc;
    }
}
