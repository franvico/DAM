using CatalogoVideojuegos.API.Models;
using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System;
using System.Windows.Forms;
using System.Net;
using System.IO;
using System.Drawing;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class Login
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
            this.panelLoginForm = new System.Windows.Forms.Panel();
            this.linkCrearCuenta = new System.Windows.Forms.LinkLabel();
            this.botonIniciarSesion = new System.Windows.Forms.Button();
            this.inputPassword = new System.Windows.Forms.TextBox();
            this.inputEmail = new System.Windows.Forms.TextBox();
            this.panelLoginForm.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelLoginForm
            // 
            this.panelLoginForm.AutoSize = true;
            this.panelLoginForm.BackColor = System.Drawing.Color.Transparent;
            this.panelLoginForm.Controls.Add(this.linkCrearCuenta);
            this.panelLoginForm.Controls.Add(this.botonIniciarSesion);
            this.panelLoginForm.Controls.Add(this.inputPassword);
            this.panelLoginForm.Controls.Add(this.inputEmail);
            this.panelLoginForm.Location = new System.Drawing.Point(900, 500);
            this.panelLoginForm.Name = "panelLoginForm";
            this.panelLoginForm.Size = new System.Drawing.Size(500, 600);
            this.panelLoginForm.TabIndex = 0;
            // 
            // linkCrearCuenta
            // 
            this.linkCrearCuenta.AutoSize = true;
            this.linkCrearCuenta.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkCrearCuenta.LinkColor = System.Drawing.Color.White;
            this.linkCrearCuenta.Location = new System.Drawing.Point(159, 482);
            this.linkCrearCuenta.Name = "linkCrearCuenta";
            this.linkCrearCuenta.Size = new System.Drawing.Size(209, 37);
            this.linkCrearCuenta.TabIndex = 2;
            this.linkCrearCuenta.TabStop = true;
            this.linkCrearCuenta.Text = "Crear Cuenta";
            // 
            // botonIniciarSesion
            // 
            this.botonIniciarSesion.BackColor = System.Drawing.Color.LightGreen;
            this.botonIniciarSesion.FlatAppearance.BorderSize = 5;
            this.botonIniciarSesion.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonIniciarSesion.Location = new System.Drawing.Point(140, 386);
            this.botonIniciarSesion.Name = "botonIniciarSesion";
            this.botonIniciarSesion.Size = new System.Drawing.Size(258, 75);
            this.botonIniciarSesion.TabIndex = 1;
            this.botonIniciarSesion.Text = "Iniciar Sesión";
            this.botonIniciarSesion.UseVisualStyleBackColor = false;
            // 
            // inputPassword
            // 
            this.inputPassword.BackColor = System.Drawing.Color.Cyan;
            this.inputPassword.CausesValidation = false;
            this.inputPassword.Font = new System.Drawing.Font("Microsoft YaHei", 16.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputPassword.ForeColor = System.Drawing.Color.Gray;
            this.inputPassword.Location = new System.Drawing.Point(44, 269);
            this.inputPassword.Multiline = true;
            this.inputPassword.Name = "inputPassword";
            this.inputPassword.Size = new System.Drawing.Size(423, 74);
            this.inputPassword.TabIndex = 0;
            this.inputPassword.Text = "Password";
            this.inputPassword.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // inputEmail
            // 
            this.inputEmail.BackColor = System.Drawing.Color.Cyan;
            this.inputEmail.CausesValidation = false;
            this.inputEmail.Font = new System.Drawing.Font("Microsoft YaHei UI", 16.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputEmail.ForeColor = System.Drawing.Color.Gray;
            this.inputEmail.Location = new System.Drawing.Point(44, 149);
            this.inputEmail.Multiline = true;
            this.inputEmail.Name = "inputEmail";
            this.inputEmail.Size = new System.Drawing.Size(423, 74);
            this.inputEmail.TabIndex = 0;
            this.inputEmail.Text = "Email";
            this.inputEmail.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = global::CatalogoVideojuegos.CLIENT.Properties.Resources.login_background;
            this.Controls.Add(this.panelLoginForm);
            this.Name = "Login";
            this.Size = new System.Drawing.Size(1500, 1200);
            this.panelLoginForm.ResumeLayout(false);
            this.panelLoginForm.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }
        private async Task IniciarSesion()
        {
            if (string.IsNullOrWhiteSpace(inputEmail.Text) || inputEmail.Text == "Email" ||
                string.IsNullOrWhiteSpace(inputPassword.Text) || inputPassword.Text == "Password")
            {
                MessageBox.Show("Por favor, completa todos los campos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var credenciales = new
            {
                Email = inputEmail.Text.Trim(),
                Password = inputPassword.Text.Trim()
            };

            string json = JsonConvert.SerializeObject(credenciales);
            StringContent contenido = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                string urlApi = "http://localhost:54072/Usuarios/Login"; // Ajusta la URL según corresponda
                HttpResponseMessage respuesta = await client.PostAsync(urlApi, contenido);

                if (respuesta.IsSuccessStatusCode)
                {
                    string jsonRespuesta = await respuesta.Content.ReadAsStringAsync();
                    Usuario usuario = JsonConvert.DeserializeObject<Usuario>(jsonRespuesta);

                    if (usuario != null)
                    {
                        // Si el usuario es admin o usuario normal, cambia la vista
                        Form index = this.FindForm();

                        if (usuario.Admin)
                        {
                            (index as Index)?.ChangeControl(new HomeAdmin(usuario.Nombre, CargarFotoDePerfil(usuario.Foto)), this);
                        }
                        else
                        {
                            (index as Index)?.ChangeControl(new HomeUsuario(usuario.Id, usuario.Nombre, CargarFotoDePerfil(usuario.Foto)), this);
                        }
                    }
                }
                else
                {
                    // Manejo de errores según el código de estado HTTP
                    if (respuesta.StatusCode == HttpStatusCode.Forbidden)
                    {
                        MessageBox.Show("No puedes iniciar sesión porque tu cuenta ha sido baneada.", "Acceso denegado", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    }
                    else
                    {
                        MessageBox.Show("Credenciales incorrectas. Inténtalo de nuevo.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error de conexión: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
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

        private void AbrirVentanaCrearCuenta()
        {
            Form index = this.FindForm();
            (index as Index)?.ChangeControl(new SignUp(), this);
        }

        #endregion

        private System.Windows.Forms.Panel panelLoginForm;
        private System.Windows.Forms.Button botonIniciarSesion;
        private System.Windows.Forms.TextBox inputPassword;
        private System.Windows.Forms.TextBox inputEmail;
        private System.Windows.Forms.LinkLabel linkCrearCuenta;
    }
}
