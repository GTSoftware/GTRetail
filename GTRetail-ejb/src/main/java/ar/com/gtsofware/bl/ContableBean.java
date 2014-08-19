/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtsoft.eao.bl;

import com.gtsoft.model.Usuarios;
import com.gtsoft.model.Ventas;
import com.gtsoft.model.VentasLineas;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author rodrigo
 */
@Stateless
@LocalBean
public class ContableBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void registrarFacturaVenta(Ventas venta, List<VentasLineas> lineas){
        
    }
}
