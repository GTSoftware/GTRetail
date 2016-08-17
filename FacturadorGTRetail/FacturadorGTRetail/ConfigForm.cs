using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FiscalPrinterLib;
using RestSharp;
using FacturadorGTRetail.REST;

namespace FacturadorGTRetail
{

    public partial class ConfigForm : Form
    {
    
        private static System.Diagnostics.EventLog LOG =
    new System.Diagnostics.EventLog();
    
        public ConfigForm()
        {
            InitializeComponent();
            modeloFiscalCombo.DataSource= Enum.GetValues(typeof(ModelosDeImpresoras));
            modeloFiscalCombo.SelectedItem = (ModelosDeImpresoras)Properties.Settings.Default.Modelo;
            comPortField.Text = Properties.Settings.Default.COM_PORT.ToString();
            sucursalField.Text = Properties.Settings.Default.Sucursal.ToString();
            hostField.Text = Properties.Settings.Default.Host.ToString();
            
        }

        private void cancelButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void saveButton_Click(object sender, EventArgs e)
        {
            
            if (comPortField.Text.Length == 0) {
                MessageBox.Show(this, "El numero de puerto debe ser mayor o igual a 1");
                return;
            }

            int puertoCom = int.Parse(comPortField.Text);

            if (puertoCom == 0)
            {
                MessageBox.Show(this, "El numero de puerto debe ser mayor o igual a 1");
                return;
            }
            if (puntoVentaCombo.Items.Count == 0)
            {
                MessageBox.Show(this, "Debe cargar los puntos de venta");
                return;
            }

            if (puntoVentaCombo.SelectedIndex==-1)
            {
                MessageBox.Show(this, "Debe seleccionar un punto de venta");
                return;
            }
            //Cuando termino de validar todo seteo y guardo las propiedades

            Properties.Settings.Default.Modelo = (int) modeloFiscalCombo.SelectedItem;
            Properties.Settings.Default.COM_PORT = puertoCom;
            Properties.Settings.Default.Sucursal = int.Parse(sucursalField.Text);
            Properties.Settings.Default.Host = hostField.Text;
            PuntoVenta pv = (PuntoVenta)puntoVentaCombo.SelectedItem;
            Properties.Settings.Default.Punto_Venta = pv.nroPuntoVenta;

            Properties.Settings.Default.Save();
            
        }

       

        private void testWSButton_Click(object sender, EventArgs e)
        {
            

            RestClient client = new RestClient(hostField.Text);
            RestRequest request = new RestRequest(RESTEndpoints.PING_ENDPOINT, Method.GET);
            request.AddHeader("Accept", "application/json");
            request.AddHeader("Content-Type", "application/json");

            IRestResponse<RestResult> response = client.Execute<RestResult>(request);
            if (response.ResponseStatus != ResponseStatus.Completed)
            {
                MessageBox.Show(String.Format("Ocurrio un error al probar el servicio web: {0}" , response.ErrorMessage),
                    "Error",MessageBoxButtons.OK,MessageBoxIcon.Error);

                
                return;
            }
            string respuesta = response.Content;
            
            MessageBox.Show(respuesta);
            
            if(!"pong".Equals(response.Data.result)){
                MessageBox.Show("No se esperaba ese resultado del servicio. No es posible utilizar el mismo.","Advertencia",
                    MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }
            cargarPuntosVenta(client);
            
        }


        private void cargarPuntosVenta(RestClient client)
        {
            RestRequest request = new RestRequest(RESTEndpoints.PUNTOS_VENTA_ENDPOINT, Method.GET);
            request.AddHeader("Accept", "application/json");
            request.AddHeader("Content-Type", "application/json");
            request.AddParameter("sucursal",int.Parse(sucursalField.Text));
            IRestResponse<List<PuntoVenta>> response = client.Execute<List<PuntoVenta>>(request);
            if (response.ResponseStatus != ResponseStatus.Completed) {
                MessageBox.Show(String.Format("Ocurrio un error al buscar los puntos de venta: {0}", response.ErrorMessage),
                   "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);

                return;
            }
            List<PuntoVenta> puntosVenta = response.Data;
            if (puntosVenta.Count == 0) {
                MessageBox.Show("No existe ningun punto de venta configurado para la sucursal." ,
                      "Error", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }
            puntoVentaCombo.Items.Clear();
            
            foreach (PuntoVenta pv in puntosVenta)
            {
                puntoVentaCombo.Items.Add(pv);
            }
               
        }

       

        
    }
}
