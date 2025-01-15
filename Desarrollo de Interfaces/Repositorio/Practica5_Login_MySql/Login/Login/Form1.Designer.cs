using System.Windows.Forms;
using System;
using System.Xml.Linq;
using System.Xml;
using System.Collections.Generic;
using System.IO;
using System.Security.Cryptography;
using System.Text;
using MySql.Data.MySqlClient;

namespace Login
{
    partial class Form1
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

        #region Código generado por el Diseñador de Windows Forms

        // Clave y IV para la encriptación (deberían ser constantes y seguras)
        private static readonly string key = "1234567890123456";  // 16 bytes para AES-128
        private static readonly string iv = "1234567890123456";   // 16 bytes

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.labelTitulo = new System.Windows.Forms.Label();
            this.textBoxUsuario = new System.Windows.Forms.TextBox();
            this.textBoxContraseña = new System.Windows.Forms.TextBox();
            this.buttonLogIn = new System.Windows.Forms.Button();
            this.buttonCrear = new System.Windows.Forms.Button();
            this.labelUsuario = new System.Windows.Forms.Label();
            this.labelContraseña = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.labelTitulo.AutoSize = true;
            this.labelTitulo.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.labelTitulo.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(254)));
            this.labelTitulo.Location = new System.Drawing.Point(332, 65);
            this.labelTitulo.Name = "label1";
            this.labelTitulo.Size = new System.Drawing.Size(101, 33);
            this.labelTitulo.TabIndex = 0;
            this.labelTitulo.Text = "LOGIN";
            this.labelTitulo.Click += new System.EventHandler(this.label1_Click);
            // 
            // textBox1
            // 
            this.textBoxUsuario.Location = new System.Drawing.Point(221, 192);
            this.textBoxUsuario.Name = "textBoxUsuario";
            this.textBoxUsuario.Size = new System.Drawing.Size(100, 20);
            this.textBoxUsuario.TabIndex = 1;
            // 
            // textBox2
            // 
            this.textBoxContraseña.Location = new System.Drawing.Point(459, 192);
            this.textBoxContraseña.Name = "textBoxContraseña";
            this.textBoxContraseña.Size = new System.Drawing.Size(100, 20);
            this.textBoxContraseña.TabIndex = 2;
            this.textBoxContraseña.PasswordChar = '*';
            // 
            // button1
            // 
            this.buttonLogIn.Location = new System.Drawing.Point(344, 256);
            this.buttonLogIn.Name = "buttonLogIn";
            this.buttonLogIn.Size = new System.Drawing.Size(75, 23);
            this.buttonLogIn.TabIndex = 3;
            this.buttonLogIn.Text = "LOGIN";
            this.buttonLogIn.UseVisualStyleBackColor = true;
            this.buttonLogIn.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.buttonCrear.Location = new System.Drawing.Point(344, 344);
            this.buttonCrear.Name = "buttonCrear";
            this.buttonCrear.Size = new System.Drawing.Size(75, 23);
            this.buttonCrear.TabIndex = 3;
            this.buttonCrear.Text = "CREAR";
            this.buttonCrear.UseVisualStyleBackColor = true;
            this.buttonCrear.Click += new System.EventHandler(this.button2_Click);
            // 
            // label2
            // 
            this.labelUsuario.AutoSize = true;
            this.labelUsuario.Location = new System.Drawing.Point(247, 160);
            this.labelUsuario.Name = "label2";
            this.labelUsuario.Size = new System.Drawing.Size(44, 13);
            this.labelUsuario.TabIndex = 4;
            this.labelUsuario.Text = "Nombre";
            this.labelUsuario.Click += new System.EventHandler(this.label2_Click);
            // 
            // label3
            // 
            this.labelContraseña.AutoSize = true;
            this.labelContraseña.Location = new System.Drawing.Point(479, 160);
            this.labelContraseña.Name = "label3";
            this.labelContraseña.Size = new System.Drawing.Size(61, 13);
            this.labelContraseña.TabIndex = 5;
            this.labelContraseña.Text = "Contraseña";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 461);
            this.Controls.Add(this.labelContraseña);
            this.Controls.Add(this.labelUsuario);
            this.Controls.Add(this.buttonLogIn);
            this.Controls.Add(this.buttonCrear);
            this.Controls.Add(this.textBoxContraseña);
            this.Controls.Add(this.textBoxUsuario);
            this.Controls.Add(this.labelTitulo);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelTitulo;
        private System.Windows.Forms.TextBox textBoxUsuario;
        private System.Windows.Forms.TextBox textBoxContraseña;
        private System.Windows.Forms.Button buttonLogIn;
        private System.Windows.Forms.Button buttonCrear;
        private System.Windows.Forms.Label labelUsuario;
        private System.Windows.Forms.Label labelContraseña;

        private string connectionString = "Server=localhost;Database=desarrollodeinterfaces;Uid=root;Pwd=;";

        private void button1_Click(object sender, EventArgs e)
        {
            string usuario = textBoxUsuario.Text;
            string contraseña = textBoxContraseña.Text;

            Dictionary<string, string> validacionLogin = ValidarLogin(usuario, contraseña);

            if (validacionLogin["validado"] == "true")
            {
                MessageBox.Show("Login exitoso!");
                // Aquí puedes redirigir a otro formulario si el login es correcto.
            }
            else
            {
                MessageBox.Show(validacionLogin["errMsg"]);
            }
        }

        private Dictionary<string, string> ValidarLogin(string usuario, string contraseña)
        {
            Dictionary<String, String> respuesta = new Dictionary<String, string>();
            respuesta["usuario"] = null;
            respuesta["errMsg"] = "El usuario introducido no existe";
            respuesta["validado"] = "false";

            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();

                string query = "SELECT contraseña FROM usuarios WHERE nombre = @nombre";
                using (MySqlCommand cmd = new MySqlCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@nombre", usuario);

                    using (MySqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read())
                        {
                            string contraseñaEncriptada = reader.GetString("contraseña");
                            string contraseñaDesencriptada = DesencriptarContraseña(contraseñaEncriptada);

                            if (contraseñaDesencriptada == contraseña)
                            {
                                respuesta["errMsg"] = "";
                                respuesta["validado"] = "true";
                            }
                            else
                            {
                                respuesta["errMsg"] = "La contraseña no es correcta";
                            }
                        }
                    }
                }
            }

            return respuesta;
        }
        private string DesencriptarContraseña(string contraseñaEncriptada)
        {
            using (Aes aesAlg = Aes.Create())
            {
                aesAlg.Key = Encoding.UTF8.GetBytes(key);
                aesAlg.IV = Encoding.UTF8.GetBytes(iv);

                ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);
                using (MemoryStream msDecrypt = new MemoryStream(Convert.FromBase64String(contraseñaEncriptada)))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {
                            return srDecrypt.ReadToEnd();
                        }
                    }
                }
            }
        }
        private void button2_Click(object sender, EventArgs e)
        {
            string usuario = textBoxUsuario.Text;
            string contraseña = textBoxContraseña.Text;

            if (usuario == "")
            {
                MessageBox.Show("Introduzca un nombre.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            else if (contraseña == "")
            {
                MessageBox.Show("Introduzca una contraseña.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (UsuarioExistente(usuario))
            {
                MessageBox.Show("El usuario ya existe. Elija otro nombre.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            GuardarUsuario(usuario, contraseña);
            MessageBox.Show("Usuario guardado exitosamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private bool UsuarioExistente(string usuario)
        {
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();

                string query = "SELECT COUNT(*) FROM usuarios WHERE nombre = @nombre";
                using (MySqlCommand cmd = new MySqlCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@nombre", usuario);

                    int count = Convert.ToInt32(cmd.ExecuteScalar());
                    return count > 0;
                }
            }
        }

        private void GuardarUsuario(string usuario, string contraseña)
        {
            string contraseñaEncriptada = EncriptarContraseña(contraseña);

            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();

                string query = "INSERT INTO usuarios (nombre, contraseña) VALUES (@nombre, @contraseña)";
                using (MySqlCommand cmd = new MySqlCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@nombre", usuario);
                    cmd.Parameters.AddWithValue("@contraseña", contraseñaEncriptada);
                    cmd.ExecuteNonQuery();
                }
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
    }
}

