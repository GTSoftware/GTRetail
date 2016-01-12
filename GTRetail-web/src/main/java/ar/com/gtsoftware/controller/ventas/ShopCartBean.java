/*
 * Copyright 2016 GT Software.
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
package ar.com.gtsoftware.controller.ventas;

import ar.com.gtsoftware.auth.AuthBackingBean;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * MB para manejar el carrito de compras
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Named(value = "shopCartBean")
@ConversationScoped
public class ShopCartBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Conversation conversation;
    @Inject
    private AuthBackingBean authBackingBean;
    @EJB
    private ProductosFacade productosFacade;

    /**
     * Creates a new instance of ShopCartBean
     */
    public ShopCartBean() {
    }

    /**
     * Inicializa la conversación
     */
    public void initConversation() {
        if (!JSFUtil.isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }

    /**
     * Finaliza la conversación. Almacena la operación y redirecciona.
     *
     * @return el string de la vista de destino
     */
    public String endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "index?faces-redirect=true";
    }

    public void addToCart(Productos producto) {

        JSFUtil.addInfoMessage(JSFUtil.getBundle("msg").getString("productoAgregadoAlCarritoSatisfactoriamente"));
    }
}
