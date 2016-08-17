using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FacturadorGTRetail.REST
{
    class RestResult
    {
        public string result { get; set; }

        public override string ToString(){
            return result;
        }
    }
}
