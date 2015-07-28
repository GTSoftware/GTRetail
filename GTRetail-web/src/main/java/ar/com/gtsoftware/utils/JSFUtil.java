/*
 * Copyright (C) 2015 GTSoftware.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.utils;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Util static methods used in JSF.
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class JSFUtil {

    private static final Logger LOG = Logger.getLogger(JSFUtil.class.getName());

    // Constants ----------------------------------------------------------------------------------
    public static final String JSF_REDIRECT = "?faces-redirect=true&amp;includeViewParams=true";

    public static final String JSF_REDIRECT_ESCAPED = "?faces-redirect=true&includeViewParams=true";

    public static final String PDF_MIME_TYPE = "application/pdf";

    public static final String MS_WORD_MIME_TYPE = "application/msword";

    public static final String MS_EXCEL_MIME_TYPE = "application/vnd.ms-excel";

    public static final String MS_POWER_POINT_MIME_TYPE = "application/vnd.ms-powerpoint";

    public static final String MS_WORD_2007_MIME_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    public static final String MS_EXCEL_2007_MIME_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    // Properties ---------------------------------------------------------------------------------
    // Actions ------------------------------------------------------------------------------------
    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }

    /**
     * Trata de averiguar el tipo MIME del nombre de archivo
     *
     * @param fileName
     * @return
     */
    public static String guessMimeType(String fileName) {
        String mime = "application/octet-stream";

        fileName = fileName.toLowerCase();
        int extDot = fileName.lastIndexOf('.');
        if (extDot > 0) {
            String extension = fileName.substring(extDot + 1);
            if (null != extension) {
                switch (extension) {
                    case "bmp":
                        mime = "image/bmp";
                        break;
                    case "jpg":
                        mime = "image/jpeg";
                        break;
                    case "gif":
                        mime = "image/gif";
                        break;
                    case "png":
                        mime = "image/png";
                        break;
                    case "pdf":
                        mime = PDF_MIME_TYPE;
                        break;
                    case "doc":
                        mime = MS_WORD_MIME_TYPE;
                        break;
                    case "xls":
                        mime = MS_EXCEL_MIME_TYPE;
                        break;
                    case "ppt":
                        mime = MS_POWER_POINT_MIME_TYPE;
                        break;
                    case "docx":
                        mime = MS_WORD_2007_MIME_TYPE;
                        break;
                    case "xlsx":
                        mime = MS_EXCEL_2007_MIME_TYPE;
                        break;
                    case "pptx":
                        mime = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                        break;
                    case "zip":
                        mime = "application/zip";
                        break;
                    case "rar":
                        mime = "application/x-rar-compressed";
                        break;
                    default:
                        MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
                        // only by file name
                        String mimeType = mimeTypesMap.getContentType(fileName);
                        mime = mimeType;
                        break;
                }
            }
        }
        return mime;
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    /**
     * Muestra un mensaje de error
     *
     * @param localizedMessage
     */
    public static void addErrorMessage(String localizedMessage) {
        addMessage(localizedMessage, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Muestra un mensaje informativo
     *
     * @param localizedMessage
     */
    public static void addInfoMessage(String localizedMessage) {
        addMessage(localizedMessage, FacesMessage.SEVERITY_INFO);
    }

    /**
     * Muestra un mensaje con una severidad pasada como parámetro
     *
     * @param localizedMessage
     * @param severity
     */
    public static void addMessage(String localizedMessage, Severity severity) {
        addMessage(null, localizedMessage, severity);
    }

    /**
     * Muestra un mensaje
     *
     * @param clientId
     * @param localizedMessage
     * @param severity
     */
    public static void addMessage(String clientId, String localizedMessage, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
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

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    public static Object getManagedBean(String name) {
        return FacesContext.getCurrentInstance().getApplication().createValueBinding(name).getValue(FacesContext.getCurrentInstance());
    }

    public static Object getManagedBean(FacesContext fc, String name) {
        return fc.getApplication().createValueBinding(name).getValue(fc);
    }

    public static void redirect(String uri) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + uri);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Logout error: {0}", e.getMessage());
        }
    }

    /**
     * Returns true if the request is postback
     *
     * @return
     */
    public static boolean isPostback() {
        return FacesContext.getCurrentInstance().isPostback();
    }

    /**
     * Retorna el nombre del usuario logueado
     *
     * @return
     */
    public static String getUserPrincipalName() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    /**
     * Retorna el mapa de parámetros de la solicitud
     *
     * @return
     */
    public static Map<String, String> getRequestParameterMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    }

    /**
     * Retorna el mapa de parámetros de la sesión
     *
     * @return
     */
    public static Map<String, Object> getSessionParameterMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

}
