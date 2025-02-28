using CatalogoVideojuegos.CLIENT.Componentes;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT
{
    partial class Carrito
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
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.contenidoCarrito = new System.Windows.Forms.FlowLayoutPanel();
            this.botonConfirmarCompra = new System.Windows.Forms.Button();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.contenidoCarrito, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.botonConfirmarCompra, 0, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 82.30241F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 17.69759F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(624, 579);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // contenidoCarrito
            // 
            this.contenidoCarrito.Dock = System.Windows.Forms.DockStyle.Fill;
            this.contenidoCarrito.Location = new System.Drawing.Point(3, 3);
            this.contenidoCarrito.Name = "contenidoCarrito";
            this.contenidoCarrito.Size = new System.Drawing.Size(618, 470);
            this.contenidoCarrito.TabIndex = 0;
            // 
            // botonConfirmarCompra
            // 
            this.botonConfirmarCompra.BackColor = System.Drawing.Color.SpringGreen;
            this.botonConfirmarCompra.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonConfirmarCompra.Font = new System.Drawing.Font("Microsoft Tai Le", 16.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonConfirmarCompra.ForeColor = System.Drawing.Color.Black;
            this.botonConfirmarCompra.Location = new System.Drawing.Point(3, 479);
            this.botonConfirmarCompra.Name = "botonConfirmarCompra";
            this.botonConfirmarCompra.Size = new System.Drawing.Size(618, 97);
            this.botonConfirmarCompra.TabIndex = 1;
            this.botonConfirmarCompra.Text = "CONFIRMAR COMPRA";
            this.botonConfirmarCompra.UseVisualStyleBackColor = false;
            // 
            // Carrito
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(624, 579);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "Carrito";
            this.Text = "Carrito";
            this.tableLayoutPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }
        public void CargarContenidoCarrito()
        {
            contenidoCarrito.Controls.Clear();

            foreach (var videojuego in videojuegosCarrito)
            {
                VideojuegoCarrito controlVideojuego = new VideojuegoCarrito(videojuego.Id, videojuego.Titulo, CargarImagen(videojuego.Portada));
                contenidoCarrito.Controls.Add(controlVideojuego);
            }
        }
        private Image CargarImagen(string nombreImagen)
        {
            string rutaImagen = Path.Combine(Application.StartupPath, "Portadas", nombreImagen);

            if (File.Exists(rutaImagen))
            {
                return Image.FromFile(rutaImagen);
            }
            return null;
        }

        private async Task ConfirmarCompra()
        {
            if (videojuegosCarrito.Count == 0)
            {
                MessageBox.Show("No hay videojuegos en el carrito.", "Carrito vacío", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            try
            {
                string urlApi = "http://localhost:54072/Compras/ConfirmarCompra";

                var requestData = new
                {
                    idUsuario = id_usuario,
                    idsJuegos = videojuegosCarrito.Select(v => v.Id).ToList()
                };

                string json = JsonConvert.SerializeObject(requestData);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PostAsync(urlApi, content);

                if (response.IsSuccessStatusCode)
                {
                    string result = await response.Content.ReadAsStringAsync();
                    var responseData = JsonConvert.DeserializeObject<dynamic>(result);

                    if (responseData.error == null)
                    {
                        await VaciarCarrito();
                        MessageBox.Show("¡Compra confirmada exitosamente!", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        this.Close();
                        catalogoUsuario.AddJuegosUsuario(videojuegosCarrito.Select(v => v.Id).ToList());
                        await catalogoUsuario.ActualizarCatalogo();
                    }
                    else
                    {
                        MessageBox.Show("Error: " + responseData.error, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
                else
                {
                    MessageBox.Show("Error al conectar con el servidor.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al realizar la compra: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
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

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.FlowLayoutPanel contenidoCarrito;
        private System.Windows.Forms.Button botonConfirmarCompra;
    }
}