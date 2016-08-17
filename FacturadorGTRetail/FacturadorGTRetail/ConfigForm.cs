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
            //Cuando termino de validar todo seteo y guardo las propiedades

            Properties.Settings.Default.Modelo = (int) modeloFiscalCombo.SelectedItem;
            Properties.Settings.Default.COM_PORT = puertoCom;
            Properties.Settings.Default.Sucursal = int.Parse(sucursalField.Text);
            Properties.Settings.Default.Host = hostField.Text;
            
            Properties.Settings.Default.Save();
            
        }

       

        private void testWSButton_Click(object sender, EventArgs e)
        {
            RestClient client = new RestClient(hostField.Text);
            RestRequest request = new RestRequest("get", Method.GET);
            request.AddHeader("Accept", "application/json");

            IRestResponse response = client.Execute(request);
            if (response.ResponseStatus != ResponseStatus.Completed)
            {
                MessageBox.Show("Ocurrio un error al probar el servicio web: "+response.ErrorMessage);
                return;
            }
            MessageBox.Show(response.Content);
        }

       

        
    }
}
