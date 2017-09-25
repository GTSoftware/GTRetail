/*
 * Copyright 2014 GT Software.
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
package ar.com.gtsoftware.controller.remitos;

import ar.com.gtsoftware.eao.RemitoFacade;
import ar.com.gtsoftware.model.Productos;
import ar.com.gtsoftware.model.Remito;
import ar.com.gtsoftware.model.dto.ImpresionEtiquetasDTO;
import ar.com.gtsoftware.utils.JSFUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.StringUtils;

/**
 * Maneja la creación de un nuevo remito.
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "etiquetasRemitoBean")
@ViewScoped
public class EtiquetasRemitoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private JSFUtil jsfUtil;

    @EJB
    private RemitoFacade remitoFacade;

    private Remito remitoActual = null;
    private int numeradorItems = 1;

    private final List<ImpresionEtiquetasDTO> etiquetas = new ArrayList<>();

    private static final Logger LOG = Logger.getLogger(EtiquetasRemitoBean.class.getName());

    /**
     * Creates a new instance of NuevoRemitoBean
     */
    public EtiquetasRemitoBean() {
    }

    @PostConstruct
    public void init() {

        String idRemito = jsfUtil.getRequestParameterMap().get("idRemito");
        if (StringUtils.isEmpty(idRemito)) {
            throw new IllegalArgumentException("IdRemito nulo!");
        }

        remitoActual = remitoFacade.find(Long.parseLong(idRemito));
        if (remitoActual == null) {
            throw new IllegalArgumentException("Remito inexistente!");
        }
        armarEtiquetas();
    }

    public Remito getRemitoActual() {
        return remitoActual;
    }

    private void armarEtiquetas() {
        remitoActual.getDetalleList().forEach((rd) -> {
            etiquetas.add(new ImpresionEtiquetasDTO(rd.getIdProducto(), rd.getCantidad().intValue(), numeradorItems++));
        });
    }

    public List<ImpresionEtiquetasDTO> getEtiquetas() {
        return etiquetas;
    }

    public void imprimirEtiquetas() throws JRException, IOException {
        List<Productos> productos = new ArrayList<>();
        for (ImpresionEtiquetasDTO iDto : etiquetas) {
            for (int i = 0; i < iDto.getCantidad(); i++) {
                productos.add(iDto.getProducto());
            }
        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/productoEtiqueta.jasper");

        HashMap<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=etiquetas.pdf");
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
