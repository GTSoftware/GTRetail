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
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "productoEditBean")
@ViewScoped
public class ProductoEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private Productos productoActual;

    private List<ProductosMarcas> listMarcas = new ArrayList<>();
    private List<FiscalAlicuotasIva> listAlicuotaIVA = new ArrayList<>();
    private List<ProductosRubros> listRubros = new ArrayList<>();
    private List<ProductosSubRubros> listSubRubros = new ArrayList<>();
    private List<ProductosTiposUnidades> listTipoUnidades = new ArrayList<>();
    private List<Personas> listProveedores = new ArrayList<>();
    private List<ProductosTiposProveeduria> listTipoProveeduria = new ArrayList<>();
    private List<ProductosTiposPorcentajes> tiposPorcentajesList = new ArrayList<>();

    private ProductosRubros productosRubrosNuevo = new ProductosRubros();
    private ProductosTiposProveeduria tiposProveeduria = new ProductosTiposProveeduria();
    private ProductosMarcas nuevaMarca = new ProductosMarcas();

    private ProductosSubRubros productosSubRubrosNuevo = new ProductosSubRubros();
    private final ProductoSubRubroSearchFilter subRubroSearchFilter = new ProductoSubRubroSearchFilter();

    private static final Logger LOG = Logger.getLogger(ProductoEditBean.class.getName());

    @PostConstruct
    public void init() {
        String idProducto = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProducto");
        if (idProducto == null) {
            nuevo();
        } else {
            productoActual = productosFacade.find(Long.parseLong(idProducto));

            if (productoActual == null) {
                nuevo();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto inexistente!", "Producto inexistente!"));
                LOG.log(Level.INFO, "Producto inexistente!");

            }
        }
        initDatos();

    }

    private void initDatos() {
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
        psf.addSortField(new SortField("razonSocial", true));
        listProveedores.addAll(personasFacade.findAllBySearchFilter(psf));

        listTipoProveeduria.addAll(productosTiposProveeduriaFacade.findAll());

        tiposPorcentajesList.addAll(tiposPorcentajesFacade.findAll());

        subRubroSearchFilter.addSortField(new SortField("nombreSubRubro", true));

        if (productoActual != null) {
            subRubroSearchFilter.setProductosRubros(productoActual.getIdRubro());
            listSubRubros.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));
        }
    }

    public void cargarSubRubros(ValueChangeEvent event) {
        listSubRubros.clear();
        subRubroSearchFilter.setProductosRubros((ProductosRubros) event.getNewValue());

        listSubRubros.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));

    }

    public void nuevo() {
        productoActual = new Productos();

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
        productosSubRubrosNuevo.setIdRubro(productoActual.getIdRubro());

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

            if (productoActual.isNew()) {

                productoActual.setActivo(true);
                productoActual.setFechaAlta(GregorianCalendar.getInstance().getTime());
                productoActual.setFechaUltimaModificacion(GregorianCalendar.getInstance().getTime());

                //calculo del precio de venta
                BigDecimal iva = ((productoActual.getIdAlicuotaIva().getValorAlicuota().divide(BigDecimal.valueOf(100))).add(BigDecimal.valueOf(1)));
                BigDecimal costo = productoActual.getCostoAdquisicionNeto();
                BigDecimal utilidad = productoActual.getUtilidad().add(BigDecimal.valueOf(1));
                productoActual.setPrecioVenta((iva.multiply(costo)).multiply(utilidad));

                productosFacade.create(productoActual);
                productoActual = new Productos();
            } else {

                productoActual.setFechaUltimaModificacion(GregorianCalendar.getInstance().getTime());
                productosFacade.edit(productoActual);
                productoActual = productosFacade.find(productoActual.getId());

            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto guardado Exitosamente"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));
            Logger.getLogger(ProductoEditBean.class.getName()).log(Level.INFO, e.getMessage());
        }

    }

    public Productos getProductoActual() {
        return productoActual;
    }

    public void setProductoActual(Productos productoActual) {
        this.productoActual = productoActual;
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
