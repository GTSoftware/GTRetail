using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacturadorGTRetail.REST
{
    abstract class RESTEndpoints {
        public static string PING_ENDPOINT = "/generic/ping";
        public static string DATE_ENDPOINT = "/generic/date";
        public static string PUNTOS_VENTA_ENDPOINT = "/puntos_venta/activos";
        public static string COMPROBANTE_PENDIENTE = "/ventas/pendiente";

    }
}
