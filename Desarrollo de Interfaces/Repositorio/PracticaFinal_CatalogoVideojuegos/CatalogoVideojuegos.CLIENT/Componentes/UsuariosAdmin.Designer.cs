using CatalogoVideojuegos.API.Models;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Net.Http;
using System;
using System.Windows.Forms;
using System.IO;
using System.Drawing;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class UsuariosAdmin
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
            this.panelContenidoUsuarios = new System.Windows.Forms.FlowLayoutPanel();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelContenidoUsuarios
            // 
            this.panelContenidoUsuarios.AutoScroll = true;
            this.panelContenidoUsuarios.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelContenidoUsuarios.Location = new System.Drawing.Point(3, 3);
            this.panelContenidoUsuarios.Name = "panelContenidoUsuarios";
            this.panelContenidoUsuarios.Size = new System.Drawing.Size(963, 860);
            this.panelContenidoUsuarios.TabIndex = 1;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Controls.Add(this.panelContenidoUsuarios, 0, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(969, 866);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // UsuariosAdmin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Transparent;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Margin = new System.Windows.Forms.Padding(0);
            this.Name = "UsuariosAdmin";
            this.Size = new System.Drawing.Size(969, 866);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        private async void ActualizarUsuarios()
        {
            panelContenidoUsuarios.Controls.Clear();

            try
            {
                string urlApi = "http://localhost:54072/Usuarios/GetAll";
                HttpResponseMessage respuesta = await client.GetAsync(urlApi);

                if (respuesta.IsSuccessStatusCode)
                {
                    string json = await respuesta.Content.ReadAsStringAsync();
                    var usuarios = JsonConvert.DeserializeObject<List<Usuario>>(json);

                    foreach (var usuario in usuarios)
                    {
                        UsuarioAdmin controlUsuario= new UsuarioAdmin(usuario.Id, usuario.Nombre, usuario.Email, usuario.Baneado, usuario.Admin, CargarFotoDePerfil(usuario.Foto));

                        panelContenidoUsuarios.Controls.Add(controlUsuario);
                    }
                }
                else
                {
                    MessageBox.Show("Error al obtener usuarios.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al conectar con la API: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        private Image CargarFotoDePerfil(string nombreImagen)
        {
            string rutaImagen = Path.Combine(Application.StartupPath, "FotosDePerfil", nombreImagen);

            if (File.Exists(rutaImagen))
            {
                return Image.FromFile(rutaImagen);
            }
            return null;
        }

        #endregion

        private FlowLayoutPanel panelContenidoUsuarios;
        private TableLayoutPanel tableLayoutPanel1;
    }
}
