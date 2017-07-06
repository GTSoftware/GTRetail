using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using RestSharp;
using FacturadorGTRetail.REST;
using FacturadorGTRetail.DTO;
using Newtonsoft.Json;

namespace FacturadorGTRetail
{
    public partial class OperacionesForm : Form
    {

        
        public OperacionesForm()
        {
            InitializeComponent();
        }

        

        private Boolean validateRest()
        {
            RestClient client = new RestClient(Properties.Settings.Default.Host);
            RestRequest request = new RestRequest(RESTEndpoints.PING_ENDPOINT, Method.GET);
            request.AddHeader("Accept", "application/json");
            request.AddHeader("Content-Type", "application/json");

            IRestResponse<RestResult> response = client.Execute<RestResult>(request);
            if (response.ResponseStatus != ResponseStatus.Completed)
            {
                MessageBox.Show(String.Format("Ocurrio un error al probar el servicio web: {0}", response.ErrorMessage),
                    "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);


                return false;
            }
            string respuesta = response.Content;

            if (!"pong".Equals(response.Data.result))
            {
                MessageBox.Show("No se esperaba ese resultado del servicio. No es posible utilizar el mismo.", "Advertencia",
                    MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return false;
            }

            return true;
        }


        private void buscarButton_Click(object sender, EventArgs e)
        {
            this.Enabled = false;
            //comprobantesDataList.Clear();

            if (!validateRest()) {
                this.Enabled = true;
                return;
            }
            if (idComprobanteField.Text != null && idComprobanteField.Text.Length > 0) { 
                //Comprobante comp = new Comprobante();
                //comp.id = 1212;
                //comp.fechaComprobante = 1472094900663;
                List<Comprobante> compList = new List<Comprobante>();
                compList.Add(getComprobanteById(int.Parse(idComprobanteField.Text)));
                comprobantesDataList.SetObjects(compList);
            }
            this.Enabled = true;

        }

        private Comprobante getComprobanteById(long id)
        {
            RestClient client = new RestClient(Properties.Settings.Default.Host);
            RestRequest request = new RestRequest(RESTEndpoints.COMPROBANTE_PENDIENTE+"?idComprobante={idComprobante}", Method.GET);
            request.AddParameter("idComprobante", id,ParameterType.UrlSegment);
            request.AddHeader("Accept", "application/json");
            request.AddHeader("Content-Type", "application/json");
            

            IRestResponse response = client.Execute(request);
            if (response.ResponseStatus != ResponseStatus.Completed)
            {
                MessageBox.Show(String.Format("Ocurrio un error al obtener la respuesta: {0}", response.ErrorMessage),
                    "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);


                return null;
            }
            try
            {
                return JsonConvert.DeserializeObject<Comprobante>(response.Content);
            }
            catch (JsonException ex)
            {
                MessageBox.Show(String.Format("Ocurrio un error al parsear la respuesta: {0}", ex.Message),
                    "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            return null;
            //return response.Data;
        }

        private void imprimirButton_Click(object sender, EventArgs e)
        {
            
            if (comprobantesDataList.SelectedItem == null) { return; }
            this.Enabled = false;
            if (!FacturadorUtils.initControlador())
            {
                this.Enabled = true;
                MessageBox.Show("No fue posible inicializar el controlador fiscal");
                return;
            }
            Comprobante comp = (Comprobante)comprobantesDataList.SelectedItem.RowObject;
            //LLamara al rest para registrar con el ultimo numero +1
            long nroCOmp = FacturadorUtils.imprimirComprobante(comp);
            
            MessageBox.Show("Comprobante impreso: " + nroCOmp);
            this.Enabled = true;
        }
        
    }
}
