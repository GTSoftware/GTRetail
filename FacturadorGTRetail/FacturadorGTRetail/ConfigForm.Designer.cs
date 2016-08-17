namespace FacturadorGTRetail
{
    partial class ConfigForm
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
            this.modeloFiscalCombo = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.saveButton = new System.Windows.Forms.Button();
            this.cancelButton = new System.Windows.Forms.Button();
            this.comPortField = new System.Windows.Forms.MaskedTextBox();
            this.sucursalField = new System.Windows.Forms.MaskedTextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.hostField = new System.Windows.Forms.TextBox();
            this.testWSButton = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.puntoVentaCombo = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // modeloFiscalCombo
            // 
            this.modeloFiscalCombo.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.CustomSource;
            this.modeloFiscalCombo.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.modeloFiscalCombo.FormattingEnabled = true;
            this.modeloFiscalCombo.Location = new System.Drawing.Point(121, 12);
            this.modeloFiscalCombo.Name = "modeloFiscalCombo";
            this.modeloFiscalCombo.Size = new System.Drawing.Size(121, 21);
            this.modeloFiscalCombo.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 15);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(102, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Modelo de impresor:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 44);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(68, 13);
            this.label2.TabIndex = 2;
            this.label2.Text = "Puerto COM:";
            // 
            // saveButton
            // 
            this.saveButton.Location = new System.Drawing.Point(329, 189);
            this.saveButton.Name = "saveButton";
            this.saveButton.Size = new System.Drawing.Size(75, 23);
            this.saveButton.TabIndex = 4;
            this.saveButton.Text = "&Guardar";
            this.saveButton.UseVisualStyleBackColor = true;
            this.saveButton.Click += new System.EventHandler(this.saveButton_Click);
            // 
            // cancelButton
            // 
            this.cancelButton.Location = new System.Drawing.Point(248, 189);
            this.cancelButton.Name = "cancelButton";
            this.cancelButton.Size = new System.Drawing.Size(75, 23);
            this.cancelButton.TabIndex = 3;
            this.cancelButton.Text = "&Cerrar";
            this.cancelButton.UseVisualStyleBackColor = true;
            this.cancelButton.Click += new System.EventHandler(this.cancelButton_Click);
            // 
            // comPortField
            // 
            this.comPortField.Location = new System.Drawing.Point(121, 44);
            this.comPortField.Mask = "9";
            this.comPortField.Name = "comPortField";
            this.comPortField.Size = new System.Drawing.Size(121, 20);
            this.comPortField.TabIndex = 1;
            // 
            // sucursalField
            // 
            this.sucursalField.Location = new System.Drawing.Point(121, 70);
            this.sucursalField.Mask = "99999";
            this.sucursalField.Name = "sucursalField";
            this.sucursalField.Size = new System.Drawing.Size(121, 20);
            this.sucursalField.TabIndex = 2;
            this.sucursalField.ValidatingType = typeof(int);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 70);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(51, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Sucursal:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 96);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(32, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Host:";
            // 
            // hostField
            // 
            this.hostField.Location = new System.Drawing.Point(121, 96);
            this.hostField.Name = "hostField";
            this.hostField.Size = new System.Drawing.Size(202, 20);
            this.hostField.TabIndex = 3;
            // 
            // testWSButton
            // 
            this.testWSButton.Location = new System.Drawing.Point(329, 93);
            this.testWSButton.Name = "testWSButton";
            this.testWSButton.Size = new System.Drawing.Size(75, 23);
            this.testWSButton.TabIndex = 8;
            this.testWSButton.Text = "C&argar";
            this.testWSButton.UseVisualStyleBackColor = true;
            this.testWSButton.Click += new System.EventHandler(this.testWSButton_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 125);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(83, 13);
            this.label5.TabIndex = 10;
            this.label5.Text = "Punto de venta:";
            // 
            // puntoVentaCombo
            // 
            this.puntoVentaCombo.AutoCompleteSource = System.Windows.Forms.AutoCompleteSource.CustomSource;
            this.puntoVentaCombo.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.puntoVentaCombo.FormattingEnabled = true;
            this.puntoVentaCombo.Location = new System.Drawing.Point(121, 122);
            this.puntoVentaCombo.Name = "puntoVentaCombo";
            this.puntoVentaCombo.Size = new System.Drawing.Size(283, 21);
            this.puntoVentaCombo.TabIndex = 9;
            // 
            // ConfigForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(412, 224);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.puntoVentaCombo);
            this.Controls.Add(this.testWSButton);
            this.Controls.Add(this.hostField);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.sucursalField);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.cancelButton);
            this.Controls.Add(this.saveButton);
            this.Controls.Add(this.comPortField);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.modeloFiscalCombo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "ConfigForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Configuracion del sistema";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox modeloFiscalCombo;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button saveButton;
        private System.Windows.Forms.Button cancelButton;
        private System.Windows.Forms.MaskedTextBox comPortField;
        private System.Windows.Forms.MaskedTextBox sucursalField;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox hostField;
        private System.Windows.Forms.Button testWSButton;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.ComboBox puntoVentaCombo;
    }
}

