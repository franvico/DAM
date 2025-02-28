using CatalogoVideojuegos.API.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class HomeUsuario
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
            this.panelNavBar = new System.Windows.Forms.Panel();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.botonCerrarSesion = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.pictureBoxFotoDePerfil = new System.Windows.Forms.PictureBox();
            this.labelNombre = new System.Windows.Forms.Label();
            this.panelMenuLateral = new System.Windows.Forms.Panel();
            this.botonMisJuegos = new System.Windows.Forms.Button();
            this.botonCatalogo = new System.Windows.Forms.Button();
            this.panelContenido = new System.Windows.Forms.Panel();
            this.panelFotoCarrito = new System.Windows.Forms.Panel();
            this.botonCarrito = new System.Windows.Forms.Button();
            this.tableLayoutPanel1.SuspendLayout();
            this.panelNavBar.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFotoDePerfil)).BeginInit();
            this.panelMenuLateral.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.BackgroundImage = global::CatalogoVideojuegos.CLIENT.Properties.Resources.homeUsuario_background;
            this.tableLayoutPanel1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 70F));
            this.tableLayoutPanel1.Controls.Add(this.panelNavBar, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panelMenuLateral, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panelContenido, 1, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 15F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 85F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1500, 1200);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // panelNavBar
            // 
            this.panelNavBar.BackgroundImage = global::CatalogoVideojuegos.CLIENT.Properties.Resources.navbarUsuario_background;
            this.panelNavBar.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.tableLayoutPanel1.SetColumnSpan(this.panelNavBar, 2);
            this.panelNavBar.Controls.Add(this.tableLayoutPanel2);
            this.panelNavBar.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelNavBar.Location = new System.Drawing.Point(0, 0);
            this.panelNavBar.Margin = new System.Windows.Forms.Padding(0);
            this.panelNavBar.Name = "panelNavBar";
            this.panelNavBar.Size = new System.Drawing.Size(1500, 180);
            this.panelNavBar.TabIndex = 0;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.BackColor = System.Drawing.Color.Transparent;
            this.tableLayoutPanel2.ColumnCount = 3;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 35.12461F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 64.87539F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 215F));
            this.tableLayoutPanel2.Controls.Add(this.botonCerrarSesion, 0, 2);
            this.tableLayoutPanel2.Controls.Add(this.label1, 1, 0);
            this.tableLayoutPanel2.Controls.Add(this.pictureBoxFotoDePerfil, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.labelNombre, 0, 1);
            this.tableLayoutPanel2.Controls.Add(this.panelFotoCarrito, 2, 0);
            this.tableLayoutPanel2.Controls.Add(this.botonCarrito, 2, 2);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 3;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 49F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 51F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(1500, 180);
            this.tableLayoutPanel2.TabIndex = 1;
            // 
            // botonCerrarSesion
            // 
            this.botonCerrarSesion.BackColor = System.Drawing.Color.IndianRed;
            this.botonCerrarSesion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonCerrarSesion.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCerrarSesion.Location = new System.Drawing.Point(3, 132);
            this.botonCerrarSesion.Name = "botonCerrarSesion";
            this.botonCerrarSesion.Size = new System.Drawing.Size(445, 45);
            this.botonCerrarSesion.TabIndex = 1;
            this.botonCerrarSesion.Text = "CERRAR SESIÓN";
            this.botonCerrarSesion.UseVisualStyleBackColor = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label1.Font = new System.Drawing.Font("Swis721 Blk BT", 22.125F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(454, 0);
            this.label1.Name = "label1";
            this.tableLayoutPanel2.SetRowSpan(this.label1, 3);
            this.label1.Size = new System.Drawing.Size(827, 180);
            this.label1.TabIndex = 0;
            this.label1.Text = "PANEL DE USUARIO";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // pictureBoxFotoDePerfil
            // 
            this.pictureBoxFotoDePerfil.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBoxFotoDePerfil.Location = new System.Drawing.Point(3, 3);
            this.pictureBoxFotoDePerfil.Name = "pictureBoxFotoDePerfil";
            this.pictureBoxFotoDePerfil.Size = new System.Drawing.Size(445, 74);
            this.pictureBoxFotoDePerfil.TabIndex = 2;
            this.pictureBoxFotoDePerfil.TabStop = false;
            // 
            // labelNombre
            // 
            this.labelNombre.AutoSize = true;
            this.labelNombre.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelNombre.Font = new System.Drawing.Font("Microsoft Tai Le", 10.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelNombre.ForeColor = System.Drawing.Color.White;
            this.labelNombre.Location = new System.Drawing.Point(3, 80);
            this.labelNombre.Name = "labelNombre";
            this.labelNombre.Size = new System.Drawing.Size(445, 49);
            this.labelNombre.TabIndex = 3;
            this.labelNombre.Text = "NOMBRE";
            this.labelNombre.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelMenuLateral
            // 
            this.panelMenuLateral.BackColor = System.Drawing.Color.Transparent;
            this.panelMenuLateral.Controls.Add(this.botonMisJuegos);
            this.panelMenuLateral.Controls.Add(this.botonCatalogo);
            this.panelMenuLateral.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelMenuLateral.Location = new System.Drawing.Point(3, 183);
            this.panelMenuLateral.Name = "panelMenuLateral";
            this.panelMenuLateral.Size = new System.Drawing.Size(444, 1014);
            this.panelMenuLateral.TabIndex = 1;
            // 
            // botonMisJuegos
            // 
            this.botonMisJuegos.BackColor = System.Drawing.Color.Khaki;
            this.botonMisJuegos.Font = new System.Drawing.Font("Microsoft YaHei", 10.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonMisJuegos.Location = new System.Drawing.Point(105, 261);
            this.botonMisJuegos.Name = "botonMisJuegos";
            this.botonMisJuegos.Size = new System.Drawing.Size(228, 57);
            this.botonMisJuegos.TabIndex = 0;
            this.botonMisJuegos.Text = "MIS JUEGOS";
            this.botonMisJuegos.UseVisualStyleBackColor = false;
            // 
            // botonCatalogo
            // 
            this.botonCatalogo.BackColor = System.Drawing.Color.Khaki;
            this.botonCatalogo.Font = new System.Drawing.Font("Microsoft YaHei", 10.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCatalogo.Location = new System.Drawing.Point(105, 158);
            this.botonCatalogo.Name = "botonCatalogo";
            this.botonCatalogo.Size = new System.Drawing.Size(228, 57);
            this.botonCatalogo.TabIndex = 0;
            this.botonCatalogo.Text = "CATÁLOGO";
            this.botonCatalogo.UseVisualStyleBackColor = false;
            // 
            // panelContenido
            // 
            this.panelContenido.BackColor = System.Drawing.Color.Transparent;
            this.panelContenido.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelContenido.Location = new System.Drawing.Point(453, 183);
            this.panelContenido.Name = "panelContenido";
            this.panelContenido.Size = new System.Drawing.Size(1044, 1014);
            this.panelContenido.TabIndex = 2;
            // 
            // panelFotoCarrito
            // 
            this.panelFotoCarrito.BackgroundImage = global::CatalogoVideojuegos.CLIENT.Properties.Resources.carrito;
            this.panelFotoCarrito.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.panelFotoCarrito.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelFotoCarrito.Location = new System.Drawing.Point(1287, 3);
            this.panelFotoCarrito.Name = "panelFotoCarrito";
            this.tableLayoutPanel2.SetRowSpan(this.panelFotoCarrito, 2);
            this.panelFotoCarrito.Size = new System.Drawing.Size(210, 123);
            this.panelFotoCarrito.TabIndex = 4;
            // 
            // botonCarrito
            // 
            this.botonCarrito.BackColor = System.Drawing.Color.SpringGreen;
            this.botonCarrito.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonCarrito.Font = new System.Drawing.Font("Microsoft Tai Le", 10.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCarrito.Location = new System.Drawing.Point(1287, 132);
            this.botonCarrito.Name = "botonCarrito";
            this.botonCarrito.Size = new System.Drawing.Size(210, 45);
            this.botonCarrito.TabIndex = 5;
            this.botonCarrito.Text = "CARRITO";
            this.botonCarrito.UseVisualStyleBackColor = false;
            // 
            // HomeUsuario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "HomeUsuario";
            this.Size = new System.Drawing.Size(1500, 1200);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.panelNavBar.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFotoDePerfil)).EndInit();
            this.panelMenuLateral.ResumeLayout(false);
            this.ResumeLayout(false);

        }
        private void CargarCatalogoUsuario()
        {
            this.panelContenido.Controls.Clear();

            //CatalogoUsuario catalogoUsuario = new CatalogoUsuario(misJuegos);
            //catalogoUsuario.Dock = DockStyle.Fill;

            this.panelContenido.Controls.Add(catalogoUsuario);
        }
        private async void CargarMisJuegos()
        {

            this.panelContenido.Controls.Clear();

            await CargarIdsMisJuegos();

            MisJuegosUsuario misJuegosUsuario = new MisJuegosUsuario(misJuegos);
            misJuegosUsuario.Dock = DockStyle.Fill;

            this.panelContenido.Controls.Add(misJuegosUsuario);
        }
        private async Task CargarIdsMisJuegos()
        {
            misJuegos = new List<int>();
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    string url = "http://localhost:54072/Compras/ObtenerJuegosComprados";

                    var requestBody = new { idUsuario = id };
                    string jsonBody = JsonConvert.SerializeObject(requestBody);

                    HttpContent content = new StringContent(jsonBody, Encoding.UTF8, "application/json");

                    HttpResponseMessage response = await client.PostAsync(url, content);

                    if (response.IsSuccessStatusCode)
                    {
                        string jsonResponse = await response.Content.ReadAsStringAsync();
                        misJuegos = JsonConvert.DeserializeObject<List<int>>(jsonResponse) ?? new List<int>();
                    }
                    else
                    {
                        MessageBox.Show("Error al cargar los juegos comprados.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        private async void MostrarCarrito()
        {
            try
            {
                string urlApi = "http://localhost:54072/Compras/ObtenerCarrito";

                HttpResponseMessage response = await client.PostAsync(urlApi, null);

                if (response.IsSuccessStatusCode)
                {
                    string jsonResponse = await response.Content.ReadAsStringAsync();
                    var responseData = JsonConvert.DeserializeObject<dynamic>(jsonResponse);

                    // Deserializar la lista de videojuegos
                    List<Videojuego> listaVideojuegos = JsonConvert.DeserializeObject<List<Videojuego>>(responseData.juegos.ToString());

                    // Pasar la lista de videojuegos al formulario del carrito
                    Carrito carritoForm = new Carrito(id, listaVideojuegos, catalogoUsuario);
                    carritoForm.ShowDialog();
                }
                else
                {
                    MessageBox.Show("Error al realizar la solicitud.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        //private async void MostrarCarrito()
        //{
        //    try
        //    {
        //        string urlApi = "http://localhost:54072/Compras/ObtenerCarrito";

        //        HttpResponseMessage response = await client.PostAsync(urlApi, null);

        //        if (response.IsSuccessStatusCode)
        //        {
        //            string jsonResponse = await response.Content.ReadAsStringAsync();
        //            var responseData = JsonConvert.DeserializeObject<dynamic>(jsonResponse);
        //            var carritoIds = responseData.carrito;

        //            // Convertir dynamic a List<int>
        //            List<int> listaCarritoIds = ((IEnumerable<dynamic>)responseData.carrito).Select(id => (int)id).ToList();

        //            //MessageBox.Show("IDs en carrito: " + string.Join(", ", listaCarritoIds), "Debug Carrito", MessageBoxButtons.OK, MessageBoxIcon.Information);

        //            Carrito carritoForm = new Carrito(listaCarritoIds);
        //            carritoForm.ShowDialog();

        //        }
        //        else
        //        {
        //            MessageBox.Show("Error al realizar la solicitud.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        //        }
        //    }
        //    catch (Exception ex)
        //    {
        //        MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        //    }
        //}
        private async void CerrarSesion()
        {
            await VaciarCarrito();

            Form index = this.FindForm();
            (index as Index)?.ChangeControl(new Login(), this);
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
        private System.Windows.Forms.Panel panelMenuLateral;
        private System.Windows.Forms.Panel panelContenido;
        private System.Windows.Forms.Button botonCatalogo;
        private System.Windows.Forms.Button botonCerrarSesion;
        private System.Windows.Forms.Button botonMisJuegos;
        private Panel panelNavBar;
        private TableLayoutPanel tableLayoutPanel2;
        private PictureBox pictureBoxFotoDePerfil;
        private Label labelNombre;
        private Label label1;
        private Panel panelFotoCarrito;
        private Button botonCarrito;
    }
}
