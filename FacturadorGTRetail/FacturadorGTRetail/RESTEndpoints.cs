using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacturadorGTRetail.REST
{
    abstract class RESTEndpoints {
        public static string PING_ENDPOINT = "/v1/generic/ping";
        public static string DATE_ENDPOINT = "/v1/generic/date";
        public static string PUNTOS_VENTA_ENDPOINT = "/v1/puntos_venta/activos";
        public static string COMPROBANTE_PENDIENTE = "/v1/ventas/pendiente";
        public static string REGISTRAR_FACTURACION = "/v1/ventas/registrar";

    }
}
