using CatalogoVideojuegos.CLIENT.Componentes;
using System.Net.Http;
using System.Threading.Tasks;
using System;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT
{
    partial class Index
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        private HttpClient client = new HttpClient();

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
            this.SuspendLayout();
            // 
            // Index
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1474, 1129);
            this.Name = "Index";
            this.Text = "Index";
            this.ResumeLayout(false);

        }

        private async void Index_FormClosing(object sender, FormClosingEventArgs e)
        {            
            await VaciarCarrito();           
        }

        private async Task VaciarCarrito()
        {
            try
            {
                string urlApi = "http://localhost:54072/Compras/VaciarCarrito";

                HttpResponseMessage response = await client.PostAsync(urlApi, null);

            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        #endregion
    }
}