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
package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.bl.ProductoXDepositoService;
import ar.com.gtsoftware.bl.ProductosService;
import ar.com.gtsoftware.bl.RemitoService;
import ar.com.gtsoftware.dto.model.ProductoXDepositoDto;
import ar.com.gtsoftware.dto.model.ProductosDto;
import ar.com.gtsoftware.dto.model.RemitoDto;
import ar.com.gtsoftware.search.ProductoXDepositoSearchFilter;
import ar.com.gtsoftware.search.RemitoSearchFilter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "productoInfoAdicionalBean")
@RequestScoped
public class ProductoInfoAdicionalBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProductosService service;
    @EJB
    private ProductoXDepositoService prodXDepoService;
    @EJB
    private RemitoService remitoService;

    private Long idProducto;
    private ProductosDto producto;
    private List<ProductoXDepositoDto> stockXDepo = null;


    @PostConstruct
    public void initParamsFromComposite() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = FacesContext.getCurrentInstance().getApplication();
        idProducto = app.evaluateExpressionGet(fc,
                "#{cc.attrs.idProducto}", Long.class);
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public ProductosDto getProducto() {
        if (producto == null) {
            loadProducto();
            loadStockXDeposito();
        }
        return producto;
    }

    protected void setProducto(ProductosDto producto) {
        this.producto = producto;
    }

    private void loadProducto() {
        if (idProducto == null) {
            producto = new ProductosDto();
            return;
        }
        producto = service.find(idProducto);
    }

    private void loadStockXDeposito() {
        ProductoXDepositoSearchFilter sf = new ProductoXDepositoSearchFilter();
        sf.setIdProducto(idProducto);
        sf.addSortField("stock", false);
        stockXDepo = prodXDepoService.findAllBySearchFilter(sf);
    }

    public List<ProductoXDepositoDto> getStockXDepo() {
        return stockXDepo;
    }

    public Date getUltimaModificacion() {
        if (producto != null) {
            return producto.getFechaUltimaModificacion();
        }
        return null;
    }

    public Date getFechaUltimaRecepcion() {
        if (producto != null) {
            RemitoSearchFilter rsf = new RemitoSearchFilter();
            rsf.setIdProducto(idProducto);
            rsf.setIdTipoMovimiento(1L); //Recepción de proveedor
            rsf.addSortField("fechaAlta", false);
            RemitoDto ultimoRemito = remitoService.findFirstBySearchFilter(rsf);
            if (ultimoRemito != null) {
                return ultimoRemito.getFechaAlta();
            }
        }
        return null;
    }
}
