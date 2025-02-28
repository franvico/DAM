using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class VideojuegoUsuario
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
            this.labelTitulo = new System.Windows.Forms.Label();
            this.panelEliminar = new System.Windows.Forms.Panel();
            this.botonAddAlCarrito = new System.Windows.Forms.Button();
            this.pictureBoxPortadaVideojuego = new System.Windows.Forms.PictureBox();
            this.tableLayoutPanel1.SuspendLayout();
            this.panelEliminar.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxPortadaVideojuego)).BeginInit();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Controls.Add(this.labelTitulo, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panelEliminar, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.pictureBoxPortadaVideojuego, 0, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 3;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 73.39902F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 26.60098F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 46F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(248, 248);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // labelTitulo
            // 
            this.labelTitulo.AutoSize = true;
            this.labelTitulo.BackColor = System.Drawing.Color.Transparent;
            this.labelTitulo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelTitulo.Font = new System.Drawing.Font("Microsoft Tai Le", 10.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTitulo.ForeColor = System.Drawing.Color.White;
            this.labelTitulo.Location = new System.Drawing.Point(3, 148);
            this.labelTitulo.Name = "labelTitulo";
            this.labelTitulo.Size = new System.Drawing.Size(242, 53);
            this.labelTitulo.TabIndex = 0;
            this.labelTitulo.Text = "label1";
            this.labelTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelEliminar
            // 
            this.panelEliminar.Controls.Add(this.botonAddAlCarrito);
            this.panelEliminar.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelEliminar.Location = new System.Drawing.Point(3, 204);
            this.panelEliminar.Name = "panelEliminar";
            this.panelEliminar.Size = new System.Drawing.Size(242, 41);
            this.panelEliminar.TabIndex = 2;
            // 
            // botonAddAlCarrito
            // 
            this.botonAddAlCarrito.BackColor = System.Drawing.Color.PaleGreen;
            this.botonAddAlCarrito.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonAddAlCarrito.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonAddAlCarrito.Location = new System.Drawing.Point(0, 0);
            this.botonAddAlCarrito.Name = "botonAddAlCarrito";
            this.botonAddAlCarrito.Size = new System.Drawing.Size(242, 41);
            this.botonAddAlCarrito.TabIndex = 0;
            this.botonAddAlCarrito.Text = "Añadir al carrito";
            this.botonAddAlCarrito.UseVisualStyleBackColor = false;
            // 
            // pictureBoxPortadaVideojuego
            // 
            this.pictureBoxPortadaVideojuego.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBoxPortadaVideojuego.Location = new System.Drawing.Point(3, 3);
            this.pictureBoxPortadaVideojuego.Name = "pictureBoxPortadaVideojuego";
            this.pictureBoxPortadaVideojuego.Size = new System.Drawing.Size(242, 142);
            this.pictureBoxPortadaVideojuego.TabIndex = 3;
            this.pictureBoxPortadaVideojuego.TabStop = false;
            // 
            // VideojuegoUsuario
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Transparent;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "VideojuegoUsuario";
            this.Size = new System.Drawing.Size(248, 248);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.panelEliminar.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxPortadaVideojuego)).EndInit();
            this.ResumeLayout(false);

        }
        private async void BotonAddAlCarrito_Click(object sender, EventArgs e)
        {
            if(estado == "catalogo") {
                await AgregarAlCarrito();
            }
            else if(estado == "comprado")
            {
                IniciarJuego();
            }
            else if(estado == "enCarrito")
            {
                await QuitarDelCarrito();
            }
            
        }

        private void IniciarJuego()
        {
            MessageBox.Show("QUE COMIENCE EL VICIO!!");
        }

        private async Task AgregarAlCarrito()
        {
            try
            {
                string urlApi = "http://localhost:54072/Compras/AgregarAlCarrito";

                var data = new { videojuegoId = id };

                var json = JsonConvert.SerializeObject(data);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PostAsync(urlApi, content);

                if (response.IsSuccessStatusCode)
                {
                    estado = "enCarrito";
                    modificarTextoBoton();
                }
                else
                {
                    MessageBox.Show("Error al agregar el juego al carrito.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        private async Task QuitarDelCarrito()
        {
            try
            {
                string urlApi = "http://localhost:54072/Compras/QuitarDelCarrito";

                var data = new { videojuegoId = id };

                var json = JsonConvert.SerializeObject(data);
                var content = new StringContent(json, Encoding.UTF8, "application/json");

                HttpResponseMessage response = await client.PostAsync(urlApi, content);

                if (response.IsSuccessStatusCode)
                {
                    estado = "catalogo";
                    modificarTextoBoton();
                }
                else
                {
                    MessageBox.Show("Error al quitar el juego del carrito.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label labelTitulo;
        private System.Windows.Forms.Panel panelEliminar;
        private System.Windows.Forms.Button botonAddAlCarrito;
        private System.Windows.Forms.PictureBox pictureBoxPortadaVideojuego;
    }
}
