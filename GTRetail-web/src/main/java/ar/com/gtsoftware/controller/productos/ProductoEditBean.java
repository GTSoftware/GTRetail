package ar.com.gtsoftware.controller.productos;

import ar.com.gtsoftware.eao.FiscalAlicuotasIvaFacade;
import ar.com.gtsoftware.eao.PersonasFacade;
import ar.com.gtsoftware.eao.ProductosFacade;
import ar.com.gtsoftware.eao.ProductosListasPreciosFacade;
import ar.com.gtsoftware.eao.ProductosMarcasFacade;
import ar.com.gtsoftware.eao.ProductosRubrosFacade;
import ar.com.gtsoftware.eao.ProductosSubRubrosFacade;
import ar.com.gtsoftware.eao.ProductosTiposPorcentajesFacade;
import ar.com.gtsoftware.eao.ProductosTiposProveeduriaFacade;
import ar.com.gtsoftware.eao.ProductosTiposUnidadesFacade;
import ar.com.gtsoftware.model.FiscalAlicuotasIva;
import ar.com.gtsoftware.model.Personas;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.ProductosListasPrecios;
import ar.com.gtsoftware.model.ProductosMarcas;
import ar.com.gtsoftware.model.ProductosPorcentajes;
import ar.com.gtsoftware.model.ProductosPrecios;
import ar.com.gtsoftware.model.ProductosRubros;
import ar.com.gtsoftware.model.ProductosSubRubros;
import ar.com.gtsoftware.model.ProductosTiposPorcentajes;
import ar.com.gtsoftware.model.ProductosTiposProveeduria;
import ar.com.gtsoftware.model.ProductosTiposUnidades;
import ar.com.gtsoftware.search.MarcasSearchFilter;
import ar.com.gtsoftware.search.PersonasSearchFilter;
import ar.com.gtsoftware.search.ProductosListasPreciosSearchFilter;
import ar.com.gtsoftware.search.RubrosSearchFilter;
import ar.com.gtsoftware.search.SortField;
import ar.com.gtsoftware.search.SubRubroSearchFilter;
import ar.com.gtsoftware.utils.JSFUtil;
import ar.com.gtsoftware.utils.UtilUI;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

    private static final BigDecimal CIEN = new BigDecimal(100);

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

    @EJB
    private ProductosListasPreciosFacade listasPreciosFacade;

    private Productos productoActual;

    private List<ProductosMarcas> listMarcas = new ArrayList<>();
    private List<FiscalAlicuotasIva> listAlicuotaIVA = new ArrayList<>();
    private List<ProductosRubros> listRubros = new ArrayList<>();
    private List<ProductosSubRubros> listSubRubros = new ArrayList<>();
    private List<ProductosTiposUnidades> listTipoUnidades = new ArrayList<>();
    private List<Personas> listProveedores = new ArrayList<>();
    private List<ProductosTiposProveeduria> listTipoProveeduria = new ArrayList<>();
    private List<ProductosTiposPorcentajes> tiposPorcentajesList = new ArrayList<>();
    private List<ProductosListasPrecios> listasPrecios = new ArrayList<>();

    private ProductosRubros productosRubrosNuevo = new ProductosRubros();
    private ProductosTiposProveeduria tiposProveeduria = new ProductosTiposProveeduria();
    private ProductosMarcas nuevaMarca = new ProductosMarcas();
    private ProductosListasPrecios listaSeleccionada;
    private ProductosTiposPorcentajes tipoPorcentajeSeleccionado;

    private ProductosSubRubros productosSubRubrosNuevo = new ProductosSubRubros();
    private final SubRubroSearchFilter subRubroSearchFilter = new SubRubroSearchFilter();

    private static final Logger LOG = Logger.getLogger(ProductoEditBean.class.getName());

    @PostConstruct
    public void init() {

        String idProducto = JSFUtil.getRequestParameterMap().get("idProducto");
        String duplicar = JSFUtil.getRequestParameterMap().get("duplicar");
        if (idProducto == null) {
            nuevo();
        } else {
            productoActual = productosFacade.find(Long.parseLong(idProducto));

            if (productoActual == null) {
                LOG.log(Level.SEVERE, "Producto inexistente!");
                throw new RuntimeException("Producto inexistente!");

            }
            if (duplicar != null && duplicar.equals("1")) {
                productoActual.setId(null);
                // TODO ver como duplicar todos los datos
            }
        }
        initDatos();

    }

    private void initDatos() {
        listAlicuotaIVA.addAll(fiscalAlicuotasIvaFacade.findAll());
        RubrosSearchFilter prsf = new RubrosSearchFilter();
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
        ProductosListasPreciosSearchFilter plpsf = new ProductosListasPreciosSearchFilter();
        plpsf.setActiva(Boolean.TRUE);
        listasPrecios.addAll(listasPreciosFacade.findAllBySearchFilter(plpsf));
    }

    public void cargarSubRubros(ValueChangeEvent event) {
        listSubRubros.clear();
        subRubroSearchFilter.setProductosRubros((ProductosRubros) event.getNewValue());

        listSubRubros.addAll(productosSubRubrosFacade.findAllBySearchFilter(subRubroSearchFilter));

    }

    public void nuevo() {
        productoActual = new Productos();
        productoActual.setPrecios(new ArrayList<>());
        productoActual.setPorcentajes(new ArrayList<>());
        productoActual.setUnidadesCompraUnidadesVenta(BigDecimal.ONE);
        productoActual.setActivo(true);
    }

    public void nuevoRubro() {

        productosRubrosNuevo = new ProductosRubros();

    }

    public void doGuardarRubro() {
        LOG.log(Level.SEVERE, "Guardando rubrooo!");

        try {
            productosRubrosFacade.create(productosRubrosNuevo);
            listRubros.add(productosRubrosNuevo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exito al guardar", "Guardado.."));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");

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
            LOG.log(Level.SEVERE, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");

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
            LOG.log(Level.SEVERE, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public void doGuardar() {

        try {

            if (productoActual.isNew()) {

                actualizarPrecios();

            }

            productosFacade.createOrEdit(productoActual);
            JSFUtil.addInfoMessage("Producto guardado Exitosamente");
            productoActual = productosFacade.find(productoActual.getId());
        } catch (Exception e) {
            LOG.log(Level.INFO, e.getMessage());
            JSFUtil.addErrorMessage("Error al guardar");
        }

    }

    public void actualizarPrecios() {
        BigDecimal costoAdquisicionNeto = productoActual.getCostoAdquisicionNeto();
        BigDecimal costoFinal = costoAdquisicionNeto;
        BigDecimal coeficienteIVA = productoActual.getIdAlicuotaIva().getValorAlicuota().divide(CIEN)
                .add(BigDecimal.ONE);
        if (productoActual.getPorcentajes() != null) {
            for (ProductosPorcentajes pp : productoActual.getPorcentajes()) {
                if (pp.getIdTipoPorcentaje().getIsPorcentaje()) {
                    costoFinal = costoFinal.add(costoFinal.multiply(pp.getValor().divide(CIEN)));
                } else {
                    costoFinal = costoFinal.add(pp.getValor());
                }
            }
        }
        productoActual.setCostoFinal(costoFinal);
        if (productoActual.getPrecios() != null) {
            for (ProductosPrecios p : productoActual.getPrecios()) {
                BigDecimal utilidad = p.getUtilidad().divide(CIEN);
                p.setNeto(costoFinal.add(costoFinal.multiply(utilidad)));
                p.setPrecio(p.getNeto().multiply(coeficienteIVA).setScale(2, RoundingMode.HALF_UP));
            }
        }
    }

    public void addPorcentaje() {
        if (tipoPorcentajeSeleccionado != null) {
            ProductosPorcentajes pp = new ProductosPorcentajes();
            pp.setIdProducto(productoActual);
            pp.setIdTipoPorcentaje(tipoPorcentajeSeleccionado);
            pp.setValor(BigDecimal.ZERO);
            pp.setFechaModificacion(UtilUI.getNow());
            productoActual.getPorcentajes().add(pp);
        }
    }

    public void addLista() {
        boolean listaExistente = false;
        for (ProductosPrecios p : productoActual.getPrecios()) {
            if (p.getIdListaPrecios().equals(listaSeleccionada)) {
                listaExistente = true;
            }
        }
        if (!listaExistente) {
            ProductosPrecios pp = new ProductosPrecios();
            pp.setIdListaPrecios(listaSeleccionada);
            pp.setIdProducto(productoActual);
            productoActual.getPrecios().add(pp);
        }

    }

    public void removePorcentaje(ProductosPorcentajes pp) {
        productoActual.getPorcentajes().remove(pp);
        actualizarPrecios();
    }

    public void removePrecio(ProductosPrecios pre) {
        productoActual.getPrecios().remove(pre);
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

    public List<ProductosListasPrecios> getListasPrecios() {
        return listasPrecios;
    }

    public void setListasPrecios(List<ProductosListasPrecios> listasPrecios) {
        this.listasPrecios = listasPrecios;
    }

    public ProductosListasPrecios getListaSeleccionada() {
        return listaSeleccionada;
    }

    public void setListaSeleccionada(ProductosListasPrecios listaSeleccionada) {
        this.listaSeleccionada = listaSeleccionada;
    }

    public ProductosTiposPorcentajes getTipoPorcentajeSeleccionado() {
        return tipoPorcentajeSeleccionado;
    }

    public void setTipoPorcentajeSeleccionado(ProductosTiposPorcentajes tipoPorcentajeSeleccionado) {
        this.tipoPorcentajeSeleccionado = tipoPorcentajeSeleccionado;
    }

}
