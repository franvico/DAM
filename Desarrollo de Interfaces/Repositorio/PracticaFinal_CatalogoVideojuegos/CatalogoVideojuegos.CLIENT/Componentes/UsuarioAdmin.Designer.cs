using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using System.Windows.Forms;
using System;
using System.Collections.Generic;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class UsuarioAdmin
    {
        /// <summary> 
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        private HttpClient client = new HttpClient();

        /// <summary> 
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de componentes

        /// <summary> 
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.labelEmail = new System.Windows.Forms.Label();
            this.pictureBoxFotoDePerfil = new System.Windows.Forms.PictureBox();
            this.labelNombre = new System.Windows.Forms.Label();
            this.botonBanear = new System.Windows.Forms.Button();
            this.botonHacerAdmin = new System.Windows.Forms.Button();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFotoDePerfil)).BeginInit();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.botonHacerAdmin, 0, 4);
            this.tableLayoutPanel1.Controls.Add(this.labelEmail, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.pictureBoxFotoDePerfil, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.labelNombre, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.botonBanear, 0, 3);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 5;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 78.66666F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 21.33333F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 59F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 49F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 44F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(300, 350);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // labelEmail
            // 
            this.labelEmail.AutoSize = true;
            this.tableLayoutPanel1.SetColumnSpan(this.labelEmail, 2);
            this.labelEmail.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelEmail.Font = new System.Drawing.Font("Microsoft Tai Le", 7.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelEmail.ForeColor = System.Drawing.Color.White;
            this.labelEmail.Location = new System.Drawing.Point(3, 197);
            this.labelEmail.Name = "labelEmail";
            this.labelEmail.Size = new System.Drawing.Size(294, 59);
            this.labelEmail.TabIndex = 2;
            this.labelEmail.Text = "labelEmail";
            this.labelEmail.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // pictureBoxFotoDePerfil
            // 
            this.tableLayoutPanel1.SetColumnSpan(this.pictureBoxFotoDePerfil, 2);
            this.pictureBoxFotoDePerfil.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBoxFotoDePerfil.Location = new System.Drawing.Point(3, 3);
            this.pictureBoxFotoDePerfil.Name = "pictureBoxFotoDePerfil";
            this.pictureBoxFotoDePerfil.Size = new System.Drawing.Size(294, 149);
            this.pictureBoxFotoDePerfil.TabIndex = 0;
            this.pictureBoxFotoDePerfil.TabStop = false;
            // 
            // labelNombre
            // 
            this.labelNombre.AutoSize = true;
            this.labelNombre.BackColor = System.Drawing.Color.Transparent;
            this.tableLayoutPanel1.SetColumnSpan(this.labelNombre, 2);
            this.labelNombre.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelNombre.Font = new System.Drawing.Font("Microsoft Tai Le", 10.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelNombre.ForeColor = System.Drawing.Color.White;
            this.labelNombre.Location = new System.Drawing.Point(3, 155);
            this.labelNombre.Name = "labelNombre";
            this.labelNombre.Size = new System.Drawing.Size(294, 42);
            this.labelNombre.TabIndex = 1;
            this.labelNombre.Text = "labelNombre";
            this.labelNombre.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // botonBanear
            // 
            this.botonBanear.BackColor = System.Drawing.Color.IndianRed;
            this.tableLayoutPanel1.SetColumnSpan(this.botonBanear, 2);
            this.botonBanear.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonBanear.Location = new System.Drawing.Point(3, 259);
            this.botonBanear.Name = "botonBanear";
            this.botonBanear.Size = new System.Drawing.Size(294, 43);
            this.botonBanear.TabIndex = 3;
            this.botonBanear.Text = "BANEAR";
            this.botonBanear.UseVisualStyleBackColor = false;
            // 
            // botonHacerAdmin
            // 
            this.botonHacerAdmin.BackColor = System.Drawing.Color.Aquamarine;
            this.tableLayoutPanel1.SetColumnSpan(this.botonHacerAdmin, 2);
            this.botonHacerAdmin.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonHacerAdmin.Location = new System.Drawing.Point(3, 308);
            this.botonHacerAdmin.Name = "botonHacerAdmin";
            this.botonHacerAdmin.Size = new System.Drawing.Size(294, 39);
            this.botonHacerAdmin.TabIndex = 5;
            this.botonHacerAdmin.Text = "Hacer Admin";
            this.botonHacerAdmin.UseVisualStyleBackColor = false;
            // 
            // UsuarioAdmin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "UsuarioAdmin";
            this.Size = new System.Drawing.Size(300, 350);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFotoDePerfil)).EndInit();
            this.ResumeLayout(false);

        }

        private async void CambiarEstadoBaneado(object sender, EventArgs e)
        {
            try
            {
                string urlApi = "http://localhost:54072/Usuarios/CambiarEstadoBaneado";

                // Crear los datos en formato formulario
                var valores = new List<KeyValuePair<string, string>>
                {
                    new KeyValuePair<string, string>("id", id.ToString()),
                    new KeyValuePair<string, string>("baneado", (!baneado).ToString().ToLower()) // true/false en minúsculas para evitar errores
                };

                var contenido = new FormUrlEncodedContent(valores);

                HttpResponseMessage response = await client.PostAsync(urlApi, contenido);

                if (response.IsSuccessStatusCode)
                {
                    baneado = !baneado;
                    ActualizarBotonBanear();
                    MessageBox.Show(baneado ? "Usuario baneado." : "Usuario desbaneado.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                {
                    MessageBox.Show("No se pudo cambiar el estado del usuario.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        private async void CambiarEstadoAdmin(object sender, EventArgs e)
        {
            try
            {
                string urlApi = $"http://localhost:54072/Usuarios/CambiarEstadoAdmin?id={id}&admin={!admin}";

                HttpResponseMessage response = await client.PutAsync(urlApi, null);

                if (response.IsSuccessStatusCode)
                {
                    admin = !admin;
                    ActualizarBotonAdmin();
                    MessageBox.Show(admin ? "Usuario ahora es administrador." : "Usuario ya no es administrador.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                {
                    MessageBox.Show("No se pudo cambiar el estado de administrador.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label labelEmail;
        private System.Windows.Forms.PictureBox pictureBoxFotoDePerfil;
        private System.Windows.Forms.Label labelNombre;
        private System.Windows.Forms.Button botonBanear;
        private System.Windows.Forms.Button botonHacerAdmin;
    }
}
