using CatalogoVideojuegos.API.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Net.Http;
using System.Text;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class MisJuegosUsuario
    {
        /// <summary> 
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

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
            this.panelContenidoCatalogo = new System.Windows.Forms.FlowLayoutPanel();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.labelContenido = new System.Windows.Forms.Label();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelContenidoCatalogo
            // 
            this.panelContenidoCatalogo.AutoScroll = true;
            this.panelContenidoCatalogo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelContenidoCatalogo.Location = new System.Drawing.Point(3, 75);
            this.panelContenidoCatalogo.Name = "panelContenidoCatalogo";
            this.panelContenidoCatalogo.Size = new System.Drawing.Size(963, 788);
            this.panelContenidoCatalogo.TabIndex = 1;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Controls.Add(this.panelContenidoCatalogo, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.labelContenido, 0, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 794F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(969, 866);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // labelContenido
            // 
            this.labelContenido.AutoSize = true;
            this.labelContenido.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelContenido.Font = new System.Drawing.Font("Microsoft Tai Le", 19.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelContenido.ForeColor = System.Drawing.Color.Black;
            this.labelContenido.Location = new System.Drawing.Point(3, 0);
            this.labelContenido.Name = "labelContenido";
            this.labelContenido.Size = new System.Drawing.Size(963, 72);
            this.labelContenido.TabIndex = 2;
            this.labelContenido.Text = "MIS VIDEOJUEGOS";
            this.labelContenido.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // MisJuegosUsuario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Transparent;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Name = "MisJuegosUsuario";
            this.Size = new System.Drawing.Size(969, 866);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.ResumeLayout(false);

        }
        private async void MostrarMisJuegos(List<int> misJuegos) {
            panelContenidoCatalogo.Controls.Clear();

            try
            {
                string urlApi = "http://localhost:54072/Videojuegos/ObtenerJuegosPorIds";

                var requestBody = new { ids = misJuegos };
                string jsonBody = JsonConvert.SerializeObject(requestBody);

                using (HttpClient client = new HttpClient())
                {
                    HttpContent content = new StringContent(jsonBody, Encoding.UTF8, "application/json");

                    HttpResponseMessage respuesta = await client.PostAsync(urlApi, content);

                    if (respuesta.IsSuccessStatusCode)
                    {
                        string json = await respuesta.Content.ReadAsStringAsync();

                        try
                        {
                            var videojuegos = JsonConvert.DeserializeObject<List<Videojuego>>(json);

                            foreach (var juego in videojuegos)
                            {
                                VideojuegoUsuario controlJuego = new VideojuegoUsuario(juego.Id, juego.Titulo, CargarImagen(juego.Portada), "comprado");
                                panelContenidoCatalogo.Controls.Add(controlJuego);
                            }
                        }
                        catch (Newtonsoft.Json.JsonSerializationException)
                        {
                            Console.Write("Usuario sin juegos");
                        }
                    }
                    else
                    {
                        MessageBox.Show("Error al obtener videojuegos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con la API: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        private Image CargarImagen(string nombreImagen)
        {
            string rutaImagen = Path.Combine(Application.StartupPath, "Portadas", nombreImagen);

            if (File.Exists(rutaImagen))
            {
                return Image.FromFile(rutaImagen);
            }
            return null; // Devuelve `null` si no encuentra la imagen
        }


        #endregion

        private FlowLayoutPanel panelContenidoCatalogo;
        private TableLayoutPanel tableLayoutPanel1;
        private Label labelContenido;
    }
}
