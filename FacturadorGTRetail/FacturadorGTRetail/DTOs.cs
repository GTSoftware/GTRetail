using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacturadorGTRetail.DTO
{
  
      

        public class ComprobantesLineas
        {
           
            public long id { get; set; }
            public long idComprobante { get; set; }
            public double precioUnitario { get; set; }
            public double cantidad { get; set; }
            public double subTotal { get; set; }
            public double costoNetoUnitario { get; set; }
            public double costoBrutoUnitario { get; set; }
            public double cantidadEntregada { get; set; }
            public string descripcion { get; set; }
            public string descripcionLinea { get; set; }
            public double iva { get; set; }
        }

        public class TipoPersoneria
        {
            public int version { get; set; }
            public long id { get; set; }
            public string nombreTipo { get; set; }
        }

        public class TipoDocumento
        {
            public int version { get; set; }
            public long id { get; set; }
            public string nombreTipoDocumento { get; set; }
            public int cantidadCaracteresMinimo { get; set; }
            public int cantidadCaracteresMaximo { get; set; }
            public int fiscalCodigoTipoDocumento { get; set; }
            public  TipoPersoneria idTipoPersoneria { get; set; }
        }

        public class Genero
        {
            public int version { get; set; }
            public long id { get; set; }
            public string nombreGenero { get; set; }
            public string simbolo { get; set; }
            public TipoPersoneria idTipoPersoneria { get; set; }
        }

        public class ResponsabilidadIva
        {
            public int version { get; set; }
            public long id { get; set; }
            public string nombreResponsabildiad { get; set; }
            public int fiscalCodigoResponsable { get; set; }
        }

        public class Persona
        {
            public long id { get; set; }
            public string email { get; set; }
            public string razonSocial { get; set; }
            public string apellidos { get; set; }
            public string nombres { get; set; }
            public string nombreFantasia { get; set; }
            public string calle { get; set; }
            public string altura { get; set; }
            public string piso { get; set; }
            public string depto { get; set; }
            public string documento { get; set; }
            public long fechaAlta { get; set; }
            public string provincia { get; set; }
            public string pais { get; set; }
            public string localidad { get; set; }
            public TipoPersoneria idTipoPersoneria { get; set; }
            public TipoDocumento idTipoDocumento { get; set; }
            public Genero idGenero { get; set; }
            public ResponsabilidadIva idResponsabilidadIva { get; set; }
            public int idSucursal { get; set; }
            public string businessString { get; set; }
        }

       

        public class Comprobante
        {
            public int version { get; set; }
            public long id { get; set; }
            public long fechaComprobante { get; set; }
            public double total { get; set; }
            public double saldo { get; set; }
            public string observaciones { get; set; }
            public string remitente { get; set; }
            public string nroRemito { get; set; }
            public string letra { get; set; }
            public string tipoComprobante { get; set; }
            public List<ComprobantesLineas> comprobantesLineasList { get; set; }
            public string usuario { get; set; }
            public Persona idPersona { get; set; }
            public string condicionComprobante { get; set; }
            public double totalConSigno { get; set; }
            public DateTime fechaComprobanteDate
            {
                get
                {
                    var epoch = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);

                    return epoch.AddMilliseconds(fechaComprobante).ToLocalTime(); ;
                }

            }
        }

        public class RegistrarFacturaRequest
        {
            public long idComprobante { get; set; }
            public int puntoVenta { get; set; }
            public long numeroComprobante { get; set; }
        }

}
