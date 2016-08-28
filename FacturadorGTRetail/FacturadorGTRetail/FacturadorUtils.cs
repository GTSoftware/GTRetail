using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FiscalPrinterLib;
using FacturadorGTRetail.DTO;

namespace FacturadorGTRetail {

    abstract class FacturadorUtils {

        private static HASAR controlador = null;
        private static System.Diagnostics.EventLog LOG =
   new System.Diagnostics.EventLog();

        public static bool initControlador(){
            if (controlador != null) { return true; }

            controlador = new HASAR();
            controlador.Modelo = (ModelosDeImpresoras)Properties.Settings.Default.Modelo;
            controlador.Puerto = Properties.Settings.Default.COM_PORT;

            try {
                controlador.Comenzar();
                controlador.TratarDeCancelarTodo();
            } catch (System.Runtime.InteropServices.COMException ex) {
                LOG.Source = "FacturadorGTRetail";
                LOG.WriteEntry("Error al abrir puerto fiscal:" + ex.Message,
                    System.Diagnostics.EventLogEntryType.Error);
                controlador = null;
                return false;
            }
            return true;
        }

        public static void cierreZ()
        {
            //controlador.ReporteZ;
        }

        public static void cierreX()
        {
            //controlador.ReporteX();
        }

        public static long imprimirComprobante(Comprobante comp) {
            TiposDeDocumento tipoDoc;
            TiposDeResponsabilidades tipoResp;
            

            switch (comp.idPersona.idTipoDocumento.id)
            {
                case 1: 
                    tipoDoc = TiposDeDocumento.TIPO_DNI;
                    break;
                case 2:
                    tipoDoc = TiposDeDocumento.TIPO_CUIT;
                    break;
                default:
                    tipoDoc = TiposDeDocumento.TIPO_DNI;
                    break;
            }
            switch (comp.idPersona.idResponsabilidadIva.id)
            {
                case 1:
                    tipoResp = TiposDeResponsabilidades.CONSUMIDOR_FINAL;
                    break;
                case 2:
                    tipoResp = TiposDeResponsabilidades.RESPONSABLE_INSCRIPTO;
                    break;
                case 3:
                    tipoResp = TiposDeResponsabilidades.MONOTRIBUTO;
                    break;
                case 4:
                    tipoResp = TiposDeResponsabilidades.RESPONSABLE_EXENTO;
                    break;
                default:
                    tipoResp = TiposDeResponsabilidades.CONSUMIDOR_FINAL;
                    break;
            }
            Persona cliente = comp.idPersona;
            controlador.DatosCliente(cliente.razonSocial, cliente.documento, tipoDoc, tipoResp,
                cliente.calle + " " + cliente.altura);



            determinarTipoComprobanteControlador(comp);
           
            //Imprimo los items
            foreach (ComprobantesLineas cl in comp.comprobantesLineasList)
            {
                controlador.ImprimirItem(cl.descripcionLinea, cl.cantidad, cl.precioUnitario, cl.iva, 0);
            }

            Object nroComprobante;
            controlador.CerrarComprobanteFiscal(1,out nroComprobante);

            return long.Parse(nroComprobante.ToString());
        }

        private static void determinarTipoComprobanteControlador(Comprobante comp) {
            //Facturas
            if(comp.tipoComprobante.id==1){
                switch(comp.letra){
                    case "A":
                        controlador.AbrirComprobanteFiscal(DocumentosFiscales.FACTURA_A);
                        break;
                    case "B":
                        controlador.AbrirComprobanteFiscal(DocumentosFiscales.FACTURA_B);
                        break;
                    
                }
            }
            //Notas de credito
            if (comp.tipoComprobante.id == 2)
            {
                switch (comp.letra)
                {
                    case "A":
                        controlador.AbrirComprobanteNoFiscal(DocumentosNoFiscales.NOTA_CREDITO_A);
                        break;
                    case "B":
                        controlador.AbrirComprobanteNoFiscal(DocumentosNoFiscales.NOTA_CREDITO_B);
                        break;
                   
                }
            }

            //Notas de Debito
            if (comp.tipoComprobante.id == 3)
            {
                switch (comp.letra)
                {
                    case "A":
                        controlador.AbrirComprobanteFiscal(DocumentosFiscales.NOTA_DEBITO_A);
                        break;
                    case "B":
                        controlador.AbrirComprobanteFiscal(DocumentosFiscales.NOTA_DEBITO_B);
                        break;

                }
            }

        }
    }
}
