using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FiscalPrinterLib;

namespace FacturadorGTRetail {

    abstract class FacturadorUtils {

        private static HASAR controlador = null;
        private static System.Diagnostics.EventLog LOG =
   new System.Diagnostics.EventLog();

        public static bool initControlador(){
            controlador = new HASAR();
            controlador.Modelo = (ModelosDeImpresoras)Properties.Settings.Default.Modelo;
            controlador.Puerto = Properties.Settings.Default.COM_PORT;

            try {
                controlador.Comenzar();
                controlador.TratarDeCancelarTodo();
            } catch (System.Runtime.InteropServices.COMException ex) {
                LOG.Source = "FacturadorGTRetail";
                LOG.WriteEntry("Error al abrir puerto fiscal:" + ex.Message);
                controlador = null;
                return false;
            }
            return true;
        }
    }
}
