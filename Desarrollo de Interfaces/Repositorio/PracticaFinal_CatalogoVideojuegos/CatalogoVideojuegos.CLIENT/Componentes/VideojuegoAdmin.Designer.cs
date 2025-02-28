using System.Net.Http;
using System.Windows.Forms;
using System;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class VideojuegoAdmin
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
            this.botonEliminarJuego = new System.Windows.Forms.Button();
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
            this.panelEliminar.Controls.Add(this.botonEliminarJuego);
            this.panelEliminar.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelEliminar.Location = new System.Drawing.Point(3, 204);
            this.panelEliminar.Name = "panelEliminar";
            this.panelEliminar.Size = new System.Drawing.Size(242, 41);
            this.panelEliminar.TabIndex = 2;
            // 
            // botonEliminarJuego
            // 
            this.botonEliminarJuego.BackColor = System.Drawing.Color.IndianRed;
            this.botonEliminarJuego.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonEliminarJuego.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonEliminarJuego.Location = new System.Drawing.Point(0, 0);
            this.botonEliminarJuego.Name = "botonEliminarJuego";
            this.botonEliminarJuego.Size = new System.Drawing.Size(242, 41);
            this.botonEliminarJuego.TabIndex = 0;
            this.botonEliminarJuego.Text = "ELIMINAR";
            this.botonEliminarJuego.UseVisualStyleBackColor = false;
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
            // VideojuegoAdmin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Transparent;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "VideojuegoAdmin";
            this.Size = new System.Drawing.Size(248, 248);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.panelEliminar.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxPortadaVideojuego)).EndInit();
            this.ResumeLayout(false);

        }

        private async void EliminarVideojuego(object sender, EventArgs e)
        {
            DialogResult result = MessageBox.Show("¿Estás seguro de que deseas eliminar este juego?", "Confirmar eliminación", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);

            if (result == DialogResult.Yes)
            {
                try
                {
                    string urlApi = $"http://localhost:54072/Videojuegos/Delete/{id}";

                    HttpResponseMessage response = await client.DeleteAsync(urlApi);

                    if (response.IsSuccessStatusCode)
                    {
                        MessageBox.Show("Juego eliminado exitosamente.", "Eliminado", MessageBoxButtons.OK, MessageBoxIcon.Information);

                        this.Parent.Controls.Remove(this);
                    }
                    else
                    {
                        MessageBox.Show("Hubo un error al intentar eliminar el videojuego.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error al conectar con el servidor: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }


        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label labelTitulo;
        private System.Windows.Forms.Panel panelEliminar;
        private System.Windows.Forms.Button botonEliminarJuego;
        private System.Windows.Forms.PictureBox pictureBoxPortadaVideojuego;
    }
}
