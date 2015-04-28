package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.eao.FiscalAlicuotasIvaFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosMarcasFacade;
import ar.com.gtsoftware.eao.ProductosRubrosFacade;
import ar.com.gtsoftware.eao.ProductosSubRubrosFacade;
import ar.com.gtsoftware.eao.ProductosTiposPorcentajesFacade;
import ar.com.gtsoftware.eao.ProductosTiposProveeduriaFacade;
import ar.com.gtsoftware.eao.ProductosTiposUnidadesFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosMarcas;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.model.ProductosSubRubros;
import ar.com.gtsoftware.model.ProductosTiposPorcentajes;
import ar.com.gtsoftware.model.ProductosTiposProveeduria;
import ar.com.gtsoftware.model.ProductosTiposUnidades;
import ar.com.gtsoftware.search.MarcasSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.ProductoRubrosSearchFilter;
import ar.com.gtsoftware.search.ProductoSubRubroSearchFilter;
import ar.com.gtsoftware.search.SortField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 * Created by fede on 26/12/14.
 */
@ManagedBean(name = "productoEditBean")
@ViewScoped
public class ProductoEditBean implements Serializable {

    @EJB
    private ProductosFacade productosFacade;

    @EJB
    private FiscalAlicuotasIvaFacade fiscalAlicuotasIvaFacade;

    @EJB
    private ProductosRubrosFacade productosRubrosFacade;

    @EJB
    private ProductosSubRubrosFacade productosSubRubrosFacade;

    @EJB
    private ProductosTiposUnidadesFacade productosTiposUnidadesFacade;

    @EJB
    private PersonasFacade personasFacade;

    @EJB
    private ProductosTiposProveeduriaFacade productosTiposProveeduriaFacade;

    @EJB
    private ProductosMarcasFacade productosMarcasFacade;

    @EJB
    private ProductosTiposPorcentajesFacade tiposPorcentajesFacade;

    private Productos productosActual;
    private List<ProductosMarcas> listMarcas = new ArrayList<>();
    private List<FiscalAlicuotasIva> listAlicuotaIVA = new ArrayList<>();
    private List<ProductosRubros> listRubros = new ArrayList<>();
    private List<ProductosSubRubros> listSubRubros = new ArrayList<>();
    private List<ProductosTiposUnidades> listTipoUnidades = new ArrayList<>();
    private List<Personas> listProveedores = new ArrayList<>();
    private List<ProductosTiposProveeduria> listTipoProveeduria = new ArrayList<>();
    private List<ProductosTiposPorcentajes> tiposPorcentajesList = new ArrayList<>();

    private ProductosRubros productosRubros = new ProductosRubros();
    private ProductosRubros productosRubrosNuevo = new ProductosRubros();
    private ProductosTiposProveeduria tiposProveeduria = new ProductosTiposProveeduria();
    private ProductosMarcas nuevaMarca = new ProductosMarcas();

    private ProductosSubRubros productosSubRubrosNuevo = new ProductosSubRubros();

    @PostConstruct
    public void init() {
        String idProducto = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProducto");
        if (idProducto == null) {
            nuevo();
        } else {
            productosActual = productosFacade.find(Long.parseLong(idProducto));
            //TODO inicializar datos según producto existente
            if (productosActual == null) {
                nuevo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto inexistente!", "Producto inexistente!"));
                Logger.getLogger(ProductoEditBean.class.getName()).log(Level.INFO, "Producto inexistente!");

            }
        }
        listAlicuotaIVA.addAll(fiscalAlicuotasIvaFacade.findAll());
        ProductoRubrosSearchFilter prsf = new ProductoRubrosSearchFilter();
        prsf.addSortField(new SortField("nombreRubro", true));
        listRubros.addAll(productosRubrosFacade.findAllBySearchFilter(prsf));

        MarcasSearchFilter msf = new MarcasSearchFilter();
        msf.addSortField(new SortField("nombreMarca", true));
        listMarcas.addAll(productosMarcasFacade.findAllBySearchFilter(msf));

        listTipoUnidades.addAll(productosTiposUnidadesFacade.findAll());
        PersonasSearchFilter psf = new PersonasSearchFilter();
        psf.setProveedor(true);
        psf.setActivo(true);
        listProveedores.addAll(personasFacade.findAllBySearchFilter(psf));
        listTipoProveeduria.addAll(productosTiposProveeduriaFacade.findAll());

        tiposPorcentajesList.addAll(tiposPorcentajesFacade.findAll());

    }

    public void valueChanged(ValueChangeEvent event) {
        listSubRubros.clear();
        productosRubros = (ProductosRubros) event.getNewValue();

        ProductoSubRubroSearchFilter psrsf = new ProductoSubRubroSearchFilter();
        psrsf.setProductosRubros(productosRubros);
        psrsf.addSortField(new SortField("nombreSubRubro", true));
        listSubRubros.addAll(productosSubRubrosFacade.findAllBySearchFilter(psrsf));

    }

    public void nuevo() {
        productosActual = new Productos();

    }

    public void nuevoRubro() {

        productosRubrosNuevo = new ProductosRubros();

    }

    public void doGuardarRubro() {

        try {
            productosRubrosFacade.create(productosRubrosNuevo);
            listRubros.add(productosRubrosNuevo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exito al guardar", "Guardado.."));
        } catch (Exception e) {
            Logger.getLogger(ProductoEditBean.class.getName()).log(Level.INFO, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));

        }
    }

    public void nuevoSubRubro() {
        productosSubRubrosNuevo = new ProductosSubRubros();
        productosSubRubrosNuevo.setIdRubro(productosRubros);

    }

    public void doGuardarSubRubro() {
        try {
            productosSubRubrosFacade.create(productosSubRubrosNuevo);
            listSubRubros.add(productosSubRubrosNuevo);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exito al guardar", "Guardado.."));

        } catch (Exception e) {
            Logger.getLogger(ProductoEditBean.class.getName()).log(Level.INFO, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));

        }

    }

    public void cargarNuevaMarca() {
        nuevaMarca = new ProductosMarcas();
    }

    public void doGuardarMarca() {

        try {

            productosMarcasFacade.create(nuevaMarca);
            listMarcas.add(nuevaMarca);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exito al guardar", "Guardado.."));

        } catch (Exception e) {
            Logger.getLogger(ProductoEditBean.class.getName()).log(Level.INFO, e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));
        }

    }

    public void doGuardar() {
        try {

            if (productosActual.isNew()) {

                productosActual.setIdTipoProveeduria(tiposProveeduria);

                productosActual.setActivo(true);
                productosActual.setFechaAlta(GregorianCalendar.getInstance().getTime());
                productosActual.setFechaUltimaModificacion(GregorianCalendar.getInstance().getTime());

                //calculo del precio de venta
                BigDecimal iva = ((productosActual.getIdAlicuotaIva().getValorAlicuota().divide(BigDecimal.valueOf(100))).add(BigDecimal.valueOf(1)));
                BigDecimal costo = productosActual.getCostoAdquisicionNeto();
                BigDecimal utilidad = productosActual.getUtilidad().add(BigDecimal.valueOf(1));
                productosActual.setPrecioVenta((iva.multiply(costo)).multiply(utilidad));

                productosFacade.create(productosActual);
                productosActual = new Productos();
            } else {
                productosActual.setIdTipoProveeduria(tiposProveeduria);
                productosActual.setFechaUltimaModificacion(GregorianCalendar.getInstance().getTime());
                productosFacade.edit(productosActual);
                productosActual = productosFacade.find(productosActual.getId());

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto guardado Exitosamente"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));
            Logger.getLogger(ProductoEditBean.class.getName()).log(Level.INFO, e.getMessage());
        }

    }

    public Productos getProductosActual() {
        return productosActual;
    }

    public void setProductosActual(Productos productosActual) {
        this.productosActual = productosActual;
    }

    public List<FiscalAlicuotasIva> getListAlicuotaIVA() {
        return listAlicuotaIVA;
    }

    public void setListAlicuotaIVA(List<FiscalAlicuotasIva> listAlicuotaIVA) {
        this.listAlicuotaIVA = listAlicuotaIVA;
    }

    public List<ProductosRubros> getListRubros() {
        return listRubros;
    }

    public void setListRubros(List<ProductosRubros> listRubros) {
        this.listRubros = listRubros;
    }

    public List<ProductosSubRubros> getListSubRubros() {
        return listSubRubros;
    }

    public void setListSubRubros(List<ProductosSubRubros> listSubRubros) {
        this.listSubRubros = listSubRubros;
    }

    public ProductosRubros getProductosRubros() {
        return productosRubros;
    }

    public void setProductosRubros(ProductosRubros productosRubros) {
        this.productosRubros = productosRubros;
    }

    public ProductosSubRubros getProductosSubRubrosNuevo() {
        return productosSubRubrosNuevo;
    }

    public void setProductosSubRubrosNuevo(ProductosSubRubros productosSubRubrosNuevo) {
        this.productosSubRubrosNuevo = productosSubRubrosNuevo;
    }

    public ProductosRubros getProductosRubrosNuevo() {
        return productosRubrosNuevo;
    }

    public void setProductosRubrosNuevo(ProductosRubros productosRubrosNuevo) {
        this.productosRubrosNuevo = productosRubrosNuevo;
    }

    public List<ProductosMarcas> getListMarcas() {
        return listMarcas;
    }

    public void setListMarcas(List<ProductosMarcas> listMarcas) {
        this.listMarcas = listMarcas;
    }

    public List<ProductosTiposUnidades> getListTipoUnidades() {
        return listTipoUnidades;
    }

    public void setListTipoUnidades(List<ProductosTiposUnidades> listTipoUnidades) {
        this.listTipoUnidades = listTipoUnidades;
    }

    public List<Personas> getListProveedores() {
        return listProveedores;
    }

    public void setListProveedores(List<Personas> listProveedores) {
        this.listProveedores = listProveedores;
    }

    public ProductosTiposProveeduria getTiposProveeduria() {
        return tiposProveeduria;
    }

    public void setTiposProveeduria(ProductosTiposProveeduria tiposProveeduria) {
        this.tiposProveeduria = tiposProveeduria;
    }

    public List<ProductosTiposProveeduria> getListTipoProveeduria() {
        return listTipoProveeduria;
    }

    public void setListTipoProveeduria(List<ProductosTiposProveeduria> listTipoProveeduria) {
        this.listTipoProveeduria = listTipoProveeduria;
    }

    public ProductosMarcas getNuevaMarca() {
        return nuevaMarca;
    }

    public void setNuevaMarca(ProductosMarcas nuevaMarca) {
        this.nuevaMarca = nuevaMarca;
    }

    public List<ProductosTiposPorcentajes> getTiposPorcentajesList() {
        return tiposPorcentajesList;
    }

    public void setTiposPorcentajesList(List<ProductosTiposPorcentajes> tiposPorcentajesList) {
        this.tiposPorcentajesList = tiposPorcentajesList;
    }

}
