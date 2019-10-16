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

import ar.com.gtsoftware.bl.RemitoService;
import ar.com.gtsoftware.dto.ImpresionEtiquetasDTO;
import ar.com.gtsoftware.dto.model.ProductosDto;
import ar.com.gtsoftware.dto.model.RemitoDto;
import ar.com.gtsoftware.helper.JSFHelper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * Maneja la creación de un nuevo remitoDtoCabecera.
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "etiquetasRemitoBean")
@ViewScoped
public class EtiquetasRemitoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(EtiquetasRemitoBean.class.getName());
    private final List<ImpresionEtiquetasDTO> etiquetas = new ArrayList<>();
    @EJB
    private RemitoService remitoFacade;
    @Inject
    private JSFHelper jsfHelper;
    private RemitoDto remitoActual = null;
    private int numeradorItems = 1;

    /**
     * Creates a new instance of NuevoRemitoBean
     */
    public EtiquetasRemitoBean() {
    }

    @PostConstruct
    public void init() {

        String idRemito = jsfHelper.getRequestParameterMap().get("idRemito");
        if (isEmpty(idRemito)) {
            throw new IllegalArgumentException("IdRemito nulo!");
        }

        remitoActual = remitoFacade.find(Long.parseLong(idRemito));
        if (remitoActual == null) {
            throw new IllegalArgumentException("Remito inexistente!");
        }
        armarEtiquetas();
    }

    public RemitoDto getRemitoActual() {
        return remitoActual;
    }

    private void armarEtiquetas() {
        remitoActual.getDetalleList().forEach((rd) -> {
            etiquetas.add(ImpresionEtiquetasDTO.builder()
                    .producto(rd.getIdProducto())
                    .cantidad(rd.getCantidad().intValue())
                    .nroItem(numeradorItems++).build());
        });
    }

    public List<ImpresionEtiquetasDTO> getEtiquetas() {
        return etiquetas;
    }

    public void imprimirEtiquetas() throws JRException, IOException {
        List<ProductosDto> productos = new ArrayList<>(etiquetas.size());
        for (ImpresionEtiquetasDTO iDto : etiquetas) {
            for (int i = 0; i < iDto.getCantidad(); i++) {
                productos.add(iDto.getProducto());
            }
        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productos);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/productoEtiqueta.jasper");

        HashMap<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = jsfHelper.getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=etiquetas.pdf");
        ServletOutputStream servletStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        jsfHelper.getFacesContext().responseComplete();
    }
}
