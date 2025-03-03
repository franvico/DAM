using Newtonsoft.Json;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.ListView;
using System.IO;
using System.Drawing;
using System.Security.Cryptography;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class SignUp
    {
        /// <summary> 
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        private HttpClient client = new HttpClient();

        // Clave y IV para la encriptación (deberían ser constantes y seguras)
        private static readonly string key = "1234567890123456";  // 16 bytes para AES-128
        private static readonly string iv = "1234567890123456";   // 16 bytes

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
            this.pictureBoxFoto = new System.Windows.Forms.PictureBox();
            this.botonAddFoto = new System.Windows.Forms.Button();
            this.linkIniciarSesion = new System.Windows.Forms.LinkLabel();
            this.botonCrearCuenta = new System.Windows.Forms.Button();
            this.inputPassword = new System.Windows.Forms.TextBox();
            this.inputNombre = new System.Windows.Forms.TextBox();
            this.inputEmail = new System.Windows.Forms.TextBox();
            this.panelLoginForm.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFoto)).BeginInit();
            this.SuspendLayout();
            // 
            // panelLoginForm
            // 
            this.panelLoginForm.AutoSize = true;
            this.panelLoginForm.BackColor = System.Drawing.Color.Transparent;
            this.panelLoginForm.Controls.Add(this.pictureBoxFoto);
            this.panelLoginForm.Controls.Add(this.botonAddFoto);
            this.panelLoginForm.Controls.Add(this.linkIniciarSesion);
            this.panelLoginForm.Controls.Add(this.botonCrearCuenta);
            this.panelLoginForm.Controls.Add(this.inputPassword);
            this.panelLoginForm.Controls.Add(this.inputNombre);
            this.panelLoginForm.Controls.Add(this.inputEmail);
            this.panelLoginForm.Location = new System.Drawing.Point(929, 385);
            this.panelLoginForm.Name = "panelLoginForm";
            this.panelLoginForm.Size = new System.Drawing.Size(500, 621);
            this.panelLoginForm.TabIndex = 1;
            // 
            // pictureBoxFoto
            // 
            this.pictureBoxFoto.Location = new System.Drawing.Point(44, 374);
            this.pictureBoxFoto.Name = "pictureBoxFoto";
            this.pictureBoxFoto.Size = new System.Drawing.Size(173, 74);
            this.pictureBoxFoto.TabIndex = 4;
            this.pictureBoxFoto.TabStop = false;
            // 
            // botonAddFoto
            // 
            this.botonAddFoto.BackColor = System.Drawing.Color.Khaki;
            this.botonAddFoto.Font = new System.Drawing.Font("Microsoft Sans Serif", 10.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonAddFoto.Location = new System.Drawing.Point(251, 374);
            this.botonAddFoto.Name = "botonAddFoto";
            this.botonAddFoto.Size = new System.Drawing.Size(216, 74);
            this.botonAddFoto.TabIndex = 3;
            this.botonAddFoto.Text = "Añadir Foto";
            this.botonAddFoto.UseVisualStyleBackColor = false;
            // 
            // linkIniciarSesion
            // 
            this.linkIniciarSesion.AutoSize = true;
            this.linkIniciarSesion.Font = new System.Drawing.Font("Microsoft Tai Le", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.linkIniciarSesion.LinkColor = System.Drawing.Color.Black;
            this.linkIniciarSesion.Location = new System.Drawing.Point(159, 555);
            this.linkIniciarSesion.Name = "linkIniciarSesion";
            this.linkIniciarSesion.Size = new System.Drawing.Size(216, 41);
            this.linkIniciarSesion.TabIndex = 2;
            this.linkIniciarSesion.TabStop = true;
            this.linkIniciarSesion.Text = "Iniciar Sesión";
            // 
            // botonCrearCuenta
            // 
            this.botonCrearCuenta.BackColor = System.Drawing.Color.LightGreen;
            this.botonCrearCuenta.FlatAppearance.BorderSize = 5;
            this.botonCrearCuenta.Font = new System.Drawing.Font("Microsoft YaHei", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCrearCuenta.ForeColor = System.Drawing.Color.DimGray;
            this.botonCrearCuenta.Location = new System.Drawing.Point(135, 468);
            this.botonCrearCuenta.Name = "botonCrearCuenta";
            this.botonCrearCuenta.Size = new System.Drawing.Size(258, 75);
            this.botonCrearCuenta.TabIndex = 1;
            this.botonCrearCuenta.Text = "Crear Cuenta";
            this.botonCrearCuenta.UseVisualStyleBackColor = false;
            // 
            // inputPassword
            // 
            this.inputPassword.BackColor = System.Drawing.Color.White;
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
            // inputNombre
            // 
            this.inputNombre.BackColor = System.Drawing.Color.White;
            this.inputNombre.CausesValidation = false;
            this.inputNombre.Font = new System.Drawing.Font("Microsoft YaHei UI", 16.125F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputNombre.ForeColor = System.Drawing.Color.Gray;
            this.inputNombre.Location = new System.Drawing.Point(44, 29);
            this.inputNombre.Multiline = true;
            this.inputNombre.Name = "inputNombre";
            this.inputNombre.Size = new System.Drawing.Size(423, 74);
            this.inputNombre.TabIndex = 0;
            this.inputNombre.Text = "Nombre";
            this.inputNombre.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // inputEmail
            // 
            this.inputEmail.BackColor = System.Drawing.Color.White;
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
            // SignUp
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = global::CatalogoVideojuegos.CLIENT.Properties.Resources.signup_background;
            this.Controls.Add(this.panelLoginForm);
            this.Name = "SignUp";
            this.Size = new System.Drawing.Size(1500, 1200);
            this.panelLoginForm.ResumeLayout(false);
            this.panelLoginForm.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFoto)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }
        private string foto = "default.png";
        private void AddFotoDePerfil(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Archivos de Imagen|*.png;*.jpg;*.jpeg";
            openFileDialog.Title = "Seleccionar foto de perfil";

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    pictureBoxFoto.Image = Image.FromFile(openFileDialog.FileName);
                    pictureBoxFoto.SizeMode = PictureBoxSizeMode.Zoom;

                    foto = Path.GetFileName(openFileDialog.FileName);

                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error al cargar la imagen: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }
        private async Task RegistrarUsuario()
        {
            if (string.IsNullOrWhiteSpace(inputNombre.Text) || inputNombre.Text == "Nombre" ||
                string.IsNullOrWhiteSpace(inputEmail.Text) || inputEmail.Text == "Email" ||
                string.IsNullOrWhiteSpace(inputPassword.Text) || inputPassword.Text == "Password")
            {
                MessageBox.Show("Por favor, completa todos los campos.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            var usuario = new
            {
                Nombre = inputNombre.Text.Trim(),
                Email = inputEmail.Text.Trim(),
                Password = EncriptarContraseña(inputPassword.Text.Trim()),
                Foto = foto
            };

            bool emailExiste = await VerificarEmailExistente(usuario.Email);
            if (emailExiste)
            {
                MessageBox.Show("El email ya está registrado. Intenta con otro.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            string json = JsonConvert.SerializeObject(usuario);
            StringContent contenido = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                string urlApi = "http://localhost:54072/Usuarios/Create";
                HttpResponseMessage respuesta = await client.PostAsync(urlApi, contenido);

                if (respuesta.IsSuccessStatusCode)
                {
                    MessageBox.Show("Registro exitoso. ¡Bienvenido! Inicia sesión con tu email para empezar a usar tu cuenta", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);

                    GuardarFoto();
                    AbrirVentanaIniciarSesion();
                }
                else
                {
                    string error = await respuesta.Content.ReadAsStringAsync();
                    MessageBox.Show($"Error al registrarse: {error}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error de conexión: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        
        private string EncriptarContraseña(string contraseña)
        {
            using (Aes aesAlg = Aes.Create())
            {
                aesAlg.Key = Encoding.UTF8.GetBytes(key);
                aesAlg.IV = Encoding.UTF8.GetBytes(iv);

                ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msEncrypt = new MemoryStream())
                {
                    using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                    {
                        using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                        {
                            swEncrypt.Write(contraseña);
                        }
                    }
                    return Convert.ToBase64String(msEncrypt.ToArray());
                }
            }
        }
        private void GuardarFoto()
        {
            if (foto != "default.png")
            {
                string carpetaFotosDePerfil = Path.Combine(Application.StartupPath, "FotosDePerfil");

                if (!Directory.Exists(carpetaFotosDePerfil))
                {
                    Directory.CreateDirectory(carpetaFotosDePerfil);
                }

                string rutaImagen = Path.Combine(carpetaFotosDePerfil, foto);

                if (!File.Exists(rutaImagen))
                {
                    try
                    {
                        pictureBoxFoto.Image.Save(rutaImagen);
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
        private async Task<bool> VerificarEmailExistente(string email)
        {
            string urlApi = $"http://localhost:54072/Usuarios/ExisteEmail?email={email}";

            try
            {
                HttpResponseMessage respuesta = await client.GetAsync(urlApi);
                if (respuesta.IsSuccessStatusCode)
                {
                    string json = await respuesta.Content.ReadAsStringAsync();
                    return JsonConvert.DeserializeObject<bool>(json);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error de conexión al verificar email: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }

            return false;
        }
        private void AbrirVentanaIniciarSesion()
        {
            Form index = this.FindForm();
            (index as Index)?.ChangeControl(new Login(), this);
        }

        #endregion

        private System.Windows.Forms.Panel panelLoginForm;
        private System.Windows.Forms.LinkLabel linkIniciarSesion;
        private System.Windows.Forms.Button botonCrearCuenta;
        private System.Windows.Forms.TextBox inputPassword;
        private System.Windows.Forms.TextBox inputEmail;
        private System.Windows.Forms.TextBox inputNombre;
        private Button botonAddFoto;
        private PictureBox pictureBoxFoto;
    }
}
