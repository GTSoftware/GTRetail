namespace FacturadorGTRetail
{
    partial class OperacionesForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.idComprobanteField = new System.Windows.Forms.MaskedTextBox();
            this.fechaDesdeField = new System.Windows.Forms.DateTimePicker();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.fechaHastaField = new System.Windows.Forms.DateTimePicker();
            this.buscarButton = new System.Windows.Forms.Button();
            this.comprobantesDataList = new BrightIdeasSoftware.DataListView();
            this.IdCompFieldData = new BrightIdeasSoftware.OLVColumn();
            this.FechaCompFieldData = new BrightIdeasSoftware.OLVColumn();
            this.ClienteCompFieldData = new BrightIdeasSoftware.OLVColumn();
            this.LetraCompFieldData = new BrightIdeasSoftware.OLVColumn();
            this.UsuarioCompFieldData = new BrightIdeasSoftware.OLVColumn();
            this.TipoCompFieldData = new BrightIdeasSoftware.OLVColumn();
            this.TotalCompFieldData = new BrightIdeasSoftware.OLVColumn();
            ((System.ComponentModel.ISupportInitialize)(this.comprobantesDataList)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(85, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Id Comprobante:";
            // 
            // idComprobanteField
            // 
            this.idComprobanteField.Location = new System.Drawing.Point(103, 9);
            this.idComprobanteField.Mask = "999999999999";
            this.idComprobanteField.Name = "idComprobanteField";
            this.idComprobanteField.Size = new System.Drawing.Size(100, 20);
            this.idComprobanteField.TabIndex = 1;
            // 
            // fechaDesdeField
            // 
            this.fechaDesdeField.CustomFormat = "dd/MM/yyyy HH:mm:ss";
            this.fechaDesdeField.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.fechaDesdeField.Location = new System.Drawing.Point(103, 46);
            this.fechaDesdeField.Name = "fechaDesdeField";
            this.fechaDesdeField.Size = new System.Drawing.Size(135, 20);
            this.fechaDesdeField.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 46);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(72, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Fecha desde:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(250, 46);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(69, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Fecha hasta:";
            // 
            // fechaHastaField
            // 
            this.fechaHastaField.CustomFormat = "dd/MM/yyyy HH:mm:ss";
            this.fechaHastaField.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
            this.fechaHastaField.Location = new System.Drawing.Point(341, 46);
            this.fechaHastaField.Name = "fechaHastaField";
            this.fechaHastaField.Size = new System.Drawing.Size(135, 20);
            this.fechaHastaField.TabIndex = 4;
            // 
            // buscarButton
            // 
            this.buscarButton.Location = new System.Drawing.Point(528, 41);
            this.buscarButton.Name = "buscarButton";
            this.buscarButton.Size = new System.Drawing.Size(75, 23);
            this.buscarButton.TabIndex = 6;
            this.buscarButton.Text = "Buscar";
            this.buscarButton.UseVisualStyleBackColor = true;
            this.buscarButton.Click += new System.EventHandler(this.buscarButton_Click);
            // 
            // comprobantesDataList
            // 
            this.comprobantesDataList.AllColumns.Add(this.IdCompFieldData);
            this.comprobantesDataList.AllColumns.Add(this.FechaCompFieldData);
            this.comprobantesDataList.AllColumns.Add(this.ClienteCompFieldData);
            this.comprobantesDataList.AllColumns.Add(this.LetraCompFieldData);
            this.comprobantesDataList.AllColumns.Add(this.TipoCompFieldData);
            this.comprobantesDataList.AllColumns.Add(this.TotalCompFieldData);
            this.comprobantesDataList.AllColumns.Add(this.UsuarioCompFieldData);
            this.comprobantesDataList.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.comprobantesDataList.CellEditUseWholeCell = false;
            this.comprobantesDataList.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.IdCompFieldData,
            this.FechaCompFieldData,
            this.ClienteCompFieldData,
            this.LetraCompFieldData,
            this.TipoCompFieldData,
            this.TotalCompFieldData,
            this.UsuarioCompFieldData});
            this.comprobantesDataList.Cursor = System.Windows.Forms.Cursors.Default;
            this.comprobantesDataList.DataSource = null;
            this.comprobantesDataList.EmptyListMsg = "No se han encontrado comprobantes";
            this.comprobantesDataList.FullRowSelect = true;
            this.comprobantesDataList.GridLines = true;
            this.comprobantesDataList.Location = new System.Drawing.Point(15, 81);
            this.comprobantesDataList.Name = "comprobantesDataList";
            this.comprobantesDataList.ShowHeaderInAllViews = false;
            this.comprobantesDataList.Size = new System.Drawing.Size(588, 189);
            this.comprobantesDataList.TabIndex = 7;
            this.comprobantesDataList.UseCompatibleStateImageBehavior = false;
            this.comprobantesDataList.View = System.Windows.Forms.View.Details;
            // 
            // IdCompFieldData
            // 
            this.IdCompFieldData.AspectName = "id";
            this.IdCompFieldData.Text = "Id";
            // 
            // FechaCompFieldData
            // 
            this.FechaCompFieldData.AspectName = "fechaComprobanteDate";
            this.FechaCompFieldData.Text = "Fecha";
            this.FechaCompFieldData.Width = 90;
            // 
            // ClienteCompFieldData
            // 
            this.ClienteCompFieldData.AspectName = "idPersona.businessString";
            this.ClienteCompFieldData.Text = "Cliente";
            this.ClienteCompFieldData.Width = 200;
            // 
            // LetraCompFieldData
            // 
            this.LetraCompFieldData.AspectName = "letra";
            this.LetraCompFieldData.Text = "Letra";
            // 
            // UsuarioCompFieldData
            // 
            this.UsuarioCompFieldData.AspectName = "idUsuario.nombreUsuario";
            this.UsuarioCompFieldData.Text = "Usuario";
            this.UsuarioCompFieldData.Width = 100;
            // 
            // TipoCompFieldData
            // 
            this.TipoCompFieldData.AspectName = "tipoComprobante.nombreComprobante";
            this.TipoCompFieldData.Text = "Tipo";
            this.TipoCompFieldData.Width = 100;
            // 
            // TotalCompFieldData
            // 
            this.TotalCompFieldData.AspectName = "total";
            this.TotalCompFieldData.Text = "Total";
            // 
            // OperacionesForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.ClientSize = new System.Drawing.Size(615, 282);
            this.Controls.Add(this.comprobantesDataList);
            this.Controls.Add(this.buscarButton);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.fechaHastaField);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.fechaDesdeField);
            this.Controls.Add(this.idComprobanteField);
            this.Controls.Add(this.label1);
            this.Name = "OperacionesForm";
            this.Text = "Operaciones para facturar";
            ((System.ComponentModel.ISupportInitialize)(this.comprobantesDataList)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.MaskedTextBox idComprobanteField;
        private System.Windows.Forms.DateTimePicker fechaDesdeField;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.DateTimePicker fechaHastaField;
        private System.Windows.Forms.Button buscarButton;
        private BrightIdeasSoftware.DataListView comprobantesDataList;
        private BrightIdeasSoftware.OLVColumn IdCompFieldData;
        private BrightIdeasSoftware.OLVColumn FechaCompFieldData;
        private BrightIdeasSoftware.OLVColumn ClienteCompFieldData;
        private BrightIdeasSoftware.OLVColumn LetraCompFieldData;
        private BrightIdeasSoftware.OLVColumn UsuarioCompFieldData;
        private BrightIdeasSoftware.OLVColumn TipoCompFieldData;
        private BrightIdeasSoftware.OLVColumn TotalCompFieldData;
        
    }
}