using System.Windows.Forms;
using System;
using System.Drawing;
using System.IO;
using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using CatalogoVideojuegos.API.Models;
using System.Collections.Generic;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class CatalogoAdmin
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
            this.panel1 = new System.Windows.Forms.Panel();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.inputTitulo = new System.Windows.Forms.TextBox();
            this.botonAddJuego = new System.Windows.Forms.Button();
            this.panel2 = new System.Windows.Forms.Panel();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.botonAddPortada = new System.Windows.Forms.Button();
            this.pictureBoxPortada = new System.Windows.Forms.PictureBox();
            this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
            this.panelContenidoCatalogo = new System.Windows.Forms.FlowLayoutPanel();
            this.tableLayoutPanel1.SuspendLayout();
            this.panel1.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.panel2.SuspendLayout();
            this.tableLayoutPanel3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxPortada)).BeginInit();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.panel1, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panelContenidoCatalogo, 0, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 16.74365F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 83.25635F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(969, 866);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.tableLayoutPanel2);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(3, 3);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(963, 139);
            this.panel1.TabIndex = 0;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 2;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Controls.Add(this.inputTitulo, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.botonAddJuego, 0, 1);
            this.tableLayoutPanel2.Controls.Add(this.panel2, 1, 0);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 2;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(963, 139);
            this.tableLayoutPanel2.TabIndex = 0;
            // 
            // inputTitulo
            // 
            this.inputTitulo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.inputTitulo.Font = new System.Drawing.Font("Microsoft Sans Serif", 16.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputTitulo.ForeColor = System.Drawing.Color.DimGray;
            this.inputTitulo.Location = new System.Drawing.Point(3, 3);
            this.inputTitulo.Multiline = true;
            this.inputTitulo.Name = "inputTitulo";
            this.inputTitulo.Size = new System.Drawing.Size(475, 63);
            this.inputTitulo.TabIndex = 1;
            this.inputTitulo.Text = "TITULO";
            this.inputTitulo.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // botonAddJuego
            // 
            this.botonAddJuego.BackColor = System.Drawing.Color.YellowGreen;
            this.tableLayoutPanel2.SetColumnSpan(this.botonAddJuego, 2);
            this.botonAddJuego.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonAddJuego.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonAddJuego.Location = new System.Drawing.Point(3, 72);
            this.botonAddJuego.Name = "botonAddJuego";
            this.botonAddJuego.Size = new System.Drawing.Size(957, 64);
            this.botonAddJuego.TabIndex = 0;
            this.botonAddJuego.Text = "AÑADIR JUEGO";
            this.botonAddJuego.UseVisualStyleBackColor = false;
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.tableLayoutPanel3);
            this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel2.Location = new System.Drawing.Point(484, 3);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(476, 63);
            this.panel2.TabIndex = 2;
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.ColumnCount = 2;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Controls.Add(this.botonAddPortada, 1, 0);
            this.tableLayoutPanel3.Controls.Add(this.pictureBoxPortada, 0, 0);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 1;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(476, 63);
            this.tableLayoutPanel3.TabIndex = 0;
            // 
            // botonAddPortada
            // 
            this.botonAddPortada.BackColor = System.Drawing.Color.Khaki;
            this.botonAddPortada.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonAddPortada.Location = new System.Drawing.Point(241, 3);
            this.botonAddPortada.Name = "botonAddPortada";
            this.botonAddPortada.Size = new System.Drawing.Size(232, 57);
            this.botonAddPortada.TabIndex = 0;
            this.botonAddPortada.Text = "AÑADIR PORTADA";
            this.botonAddPortada.UseVisualStyleBackColor = false;
            // 
            // pictureBoxPortada
            // 
            this.pictureBoxPortada.BackColor = System.Drawing.Color.White;
            this.pictureBoxPortada.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBoxPortada.Location = new System.Drawing.Point(3, 3);
            this.pictureBoxPortada.Name = "pictureBoxPortada";
            this.pictureBoxPortada.Size = new System.Drawing.Size(232, 57);
            this.pictureBoxPortada.TabIndex = 1;
            this.pictureBoxPortada.TabStop = false;
            // 
            // panelContenidoCatalogo
            // 
            this.panelContenidoCatalogo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelContenidoCatalogo.Location = new System.Drawing.Point(3, 148);
            this.panelContenidoCatalogo.Name = "panelContenidoCatalogo";
            this.panelContenidoCatalogo.Size = new System.Drawing.Size(963, 715);
            this.panelContenidoCatalogo.TabIndex = 1;
            this.panelContenidoCatalogo.AutoScroll = true;
            // 
            // CatalogoAdmin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Transparent;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Name = "CatalogoAdmin";
            this.Size = new System.Drawing.Size(969, 866);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            this.panel2.ResumeLayout(false);
            this.tableLayoutPanel3.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxPortada)).EndInit();
            this.ResumeLayout(false);
        }

        private string portada = "default.png";
        private void AddPortada(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Archivos de Imagen|*.png;*.jpg;*.jpeg";
            openFileDialog.Title = "Seleccionar imagen de portada";

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    pictureBoxPortada.Image = Image.FromFile(openFileDialog.FileName);
                    pictureBoxPortada.SizeMode = PictureBoxSizeMode.Zoom;

                    portada = Path.GetFileName(openFileDialog.FileName);

                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error al cargar la imagen: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }
        private async void AddJuego(object sender, EventArgs e)
        {
            string titulo = inputTitulo.Text.Trim();
            if (titulo == "TITULO" || string.IsNullOrEmpty(titulo))
            {
                MessageBox.Show("Por favor, ingrese un título válido.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var nuevoJuego = new
            {
                Titulo = titulo,
                Portada = portada
            };

            try
            {
                await RegistrarVideojuego(nuevoJuego);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al enviar los datos a la API: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

        }
        private async Task RegistrarVideojuego(object nuevoJuego)
        {
            if (string.IsNullOrWhiteSpace(inputTitulo.Text) || inputTitulo.Text == "TITULO")
            {
                MessageBox.Show("Por favor, completa todos los campos correctamente.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var videojuego = new
            {
                Titulo = inputTitulo.Text.Trim(),
                Portada = portada
            };

            string json = JsonConvert.SerializeObject(videojuego);
            StringContent contenido = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                string urlApi = "http://localhost:54072/Videojuegos/Create";
                HttpResponseMessage respuesta = await client.PostAsync(urlApi, contenido);

                if (respuesta.IsSuccessStatusCode)
                {
                    GuardarPortada();
                    RestrablecerValoresIniciales();
                    ActualizarCatalogo();
                    MessageBox.Show("Videojuego registrado exitosamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                {
                    string error = await respuesta.Content.ReadAsStringAsync();
                    MessageBox.Show($"Error al registrar el videojuego: {error}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error de conexión: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void GuardarPortada()
        {
            if (portada != "default.png")
            {
                string carpetaPortadas = Path.Combine(Application.StartupPath, "Portadas");

                if (!Directory.Exists(carpetaPortadas))
                {
                    Directory.CreateDirectory(carpetaPortadas);
                }

                string rutaImagen = Path.Combine(carpetaPortadas, portada);

                if (!File.Exists(rutaImagen))
                {
                    try
                    {
                        pictureBoxPortada.Image.Save(rutaImagen);
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Error al guardar la imagen: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
            //else
            //{
            //    MessageBox.Show("La imagen no ha sido cambiada, se mantiene la imagen predeterminada.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            //}
        }

        private void RestrablecerValoresIniciales()
        {
            inputTitulo.Text = "TITULO";
            pictureBoxPortada.Image = null;
            portada = "default.png";
        }

        private async void ActualizarCatalogo()
        {
            panelContenidoCatalogo.Controls.Clear(); // Limpiar el panel antes de actualizar

            try
            {
                string urlApi = "http://localhost:54072/Videojuegos/GetAll"; // Ruta de la API para obtener videojuegos
                HttpResponseMessage respuesta = await client.GetAsync(urlApi);

                if (respuesta.IsSuccessStatusCode)
                {
                    string json = await respuesta.Content.ReadAsStringAsync();
                    var videojuegos = JsonConvert.DeserializeObject<List<Videojuego>>(json);

                    foreach (var juego in videojuegos)
                    {
                        VideojuegoAdmin controlJuego = new VideojuegoAdmin(juego.Id, juego.Titulo, CargarImagen(juego.Portada));

                        panelContenidoCatalogo.Controls.Add(controlJuego);
                    }
                }
                else
                {
                    MessageBox.Show("Error al obtener videojuegos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
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

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button botonAddJuego;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.TextBox inputTitulo;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel3;
        private System.Windows.Forms.Button botonAddPortada;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;
        private System.Windows.Forms.PictureBox pictureBoxPortada;
        private FlowLayoutPanel panelContenidoCatalogo;
    }
}
