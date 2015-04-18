package ar.com.gtsoftware.search;

import ar.com.gtsoftware.model.ProductosRubros;

/**
 * Created by fede on 30/12/14.
 */

public class ProductoSubRubroSearchFilter extends AbstractSearchFilter {

    private ProductosRubros productosRubros;
    private String descripcion;

    public ProductoSubRubroSearchFilter(){}

    @Override
    public boolean hasFilter(){
        return (productosRubros != null || (descripcion != null && !descripcion.isEmpty()) );
    }

    public ProductosRubros getProductosRubros() {
        return productosRubros;
    }

    public void setProductosRubros(ProductosRubros productosRubros) {
        this.productosRubros = productosRubros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
