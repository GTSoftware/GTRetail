using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacturadorGTRetail.REST
{
    class PuntoVenta
    {
        public int version { get; set; }
        public int nroPuntoVenta { get; set; }
        public bool activo { get; set; }
        public string descripcion { get; set; }
        public string tipo { get; set; }
        public int id { get; set; }

        public override string ToString()
        {
            return String.Format("[{0}] {1} {2}", nroPuntoVenta, tipo, descripcion);
        }
    }
}
