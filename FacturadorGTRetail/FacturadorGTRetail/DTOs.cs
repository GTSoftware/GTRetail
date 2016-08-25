using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacturadorGTRetail.DTO
{
  
        public class TipoComprobante
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreComprobante { get; set; }
            public int signo { get; set; }
            public bool activo { get; set; }
        }

        public class ComprobantesLineasList
        {
            public int version { get; set; }
            public int id { get; set; }
            public double precioUnitario { get; set; }
            public int cantidad { get; set; }
            public double subTotal { get; set; }
            public int costoNetoUnitario { get; set; }
            public int costoBrutoUnitario { get; set; }
            public int cantidadEntregada { get; set; }
            public string descripcion { get; set; }
            public string descripcionLinea { get; set; }
        }

        public class Sucursal
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreSucursal { get; set; }
            public string direccion { get; set; }
            public object telefonoFijo { get; set; }
            public long fechaAlta { get; set; }
            public bool activo { get; set; }
            public string businessString { get; set; }
        }

        public class Usuario
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreUsuario { get; set; }
            public string login { get; set; }
            public long fechaAlta { get; set; }
            public string puntoVenta { get; set; }
            public Sucursal idSucursal { get; set; }
        }

        public class Pais
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombrePais { get; set; }
        }

        public class Provincia
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreProvincia { get; set; }
            public Pais idPais { get; set; }
        }

        public class Localidad
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreLocalidad { get; set; }
            public string codigoPostal { get; set; }
            public  Provincia idProvincia { get; set; }
        }

        public class TipoPersoneria
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreTipo { get; set; }
        }

        public class TipoDocumento
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreTipoDocumento { get; set; }
            public int cantidadCaracteresMinimo { get; set; }
            public int cantidadCaracteresMaximo { get; set; }
            public int fiscalCodigoTipoDocumento { get; set; }
            public  TipoPersoneria idTipoPersoneria { get; set; }
        }

        public class Genero
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreGenero { get; set; }
            public string simbolo { get; set; }
            public TipoPersoneria idTipoPersoneria { get; set; }
        }

        public class ResponsabilidadIva
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreResponsabildiad { get; set; }
            public int fiscalCodigoResponsable { get; set; }
        }

        public class Persona
        {
            public int version { get; set; }
            public int id { get; set; }
            public string email { get; set; }
            public string razonSocial { get; set; }
            public object apellidos { get; set; }
            public object nombres { get; set; }
            public object nombreFantasia { get; set; }
            public string calle { get; set; }
            public object altura { get; set; }
            public object piso { get; set; }
            public object depto { get; set; }
            public string documento { get; set; }
            public long fechaAlta { get; set; }
            public bool activo { get; set; }
            public bool cliente { get; set; }
            public bool proveedor { get; set; }
            public Provincia idProvincia { get; set; }
            public Pais idPais { get; set; }
            public Localidad idLocalidad { get; set; }
            public TipoPersoneria idTipoPersoneria { get; set; }
            public TipoDocumento idTipoDocumento { get; set; }
            public Genero idGenero { get; set; }
            public ResponsabilidadIva idResponsabilidadIva { get; set; }
            public Sucursal idSucursal { get; set; }
            public string businessString { get; set; }
        }

        public class CondicionComprobante
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreCondicion { get; set; }
            public bool activo { get; set; }
            public bool venta { get; set; }
            public bool compra { get; set; }
            public bool pagoTotal { get; set; }
        }

        public class EstadoComprobante
        {
            public int version { get; set; }
            public int id { get; set; }
            public string nombreEstado { get; set; }
        }

        public class Comprobante
        {
            public int version { get; set; }
            public int id { get; set; }
            public long fechaComprobante { get; set; }
            public double total { get; set; }
            public double saldo { get; set; }
            public object observaciones { get; set; }
            public object remitente { get; set; }
            public object nroRemito { get; set; }
            public bool anulada { get; set; }
            public string letra { get; set; }
            public TipoComprobante tipoComprobante { get; set; }
            public List<ComprobantesLineasList> comprobantesLineasList { get; set; }
            public Usuario idUsuario { get; set; }
            public Persona idPersona { get; set; }
            public CondicionComprobante idCondicionComprobante { get; set; }
            public EstadoComprobante idEstadoComprobante { get; set; }
            public double totalConSigno { get; set; }
            public DateTime fechaComprobanteDate
            {
                get
                {
                    var epoch = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
                    
                    return epoch.AddMilliseconds(fechaComprobante).AddHours(-3); ;
                }
                
            }
        }



}
