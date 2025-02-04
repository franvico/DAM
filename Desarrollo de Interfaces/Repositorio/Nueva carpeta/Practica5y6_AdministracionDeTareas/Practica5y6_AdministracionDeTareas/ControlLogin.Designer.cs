using System;
using System.Collections.Generic;
using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace Practica5y6_AdministracionDeTareas
{
    partial class ControlLogin
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
            this.inputPassword = new System.Windows.Forms.TextBox();
            this.inputNombre = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.botonLogin = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // inputPassword
            // 
            this.inputPassword.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputPassword.Location = new System.Drawing.Point(487, 632);
            this.inputPassword.Name = "inputPassword";
            this.inputPassword.PasswordChar = '*';
            this.inputPassword.Size = new System.Drawing.Size(500, 38);
            this.inputPassword.TabIndex = 3;
            // 
            // inputNombre
            // 
            this.inputNombre.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputNombre.Location = new System.Drawing.Point(487, 443);
            this.inputNombre.Name = "inputNombre";
            this.inputNombre.Size = new System.Drawing.Size(500, 38);
            this.inputNombre.TabIndex = 3;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(658, 553);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(155, 31);
            this.label4.TabIndex = 4;
            this.label4.Text = "Contraseña";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(681, 347);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(110, 31);
            this.label3.TabIndex = 6;
            this.label3.Text = "Nombre";
            // 
            // botonLogin
            // 
            this.botonLogin.BackColor = System.Drawing.Color.MediumSpringGreen;
            this.botonLogin.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.botonLogin.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonLogin.Location = new System.Drawing.Point(0, 837);
            this.botonLogin.Name = "botonLogin";
            this.botonLogin.Size = new System.Drawing.Size(1474, 292);
            this.botonLogin.TabIndex = 5;
            this.botonLogin.Text = "Iniciar Sesión";
            this.botonLogin.UseVisualStyleBackColor = false;
            this.botonLogin.Click += new System.EventHandler(this.iniciarSesion);
            // 
            // label2
            // 
            this.label2.Dock = System.Windows.Forms.DockStyle.Top;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(0, 116);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(1474, 116);
            this.label2.TabIndex = 1;
            this.label2.Text = "INICIA SESIÓN PARA CONSULTAR TUS TAREAS";
            this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label1
            // 
            this.label1.Dock = System.Windows.Forms.DockStyle.Top;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(0, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(1474, 116);
            this.label1.TabIndex = 0;
            this.label1.Text = "GESTOR DE TAREAS";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // ControlLogin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.label3);
            this.Controls.Add(this.botonLogin);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.inputPassword);
            this.Controls.Add(this.inputNombre);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "ControlLogin";
            this.Size = new System.Drawing.Size(1474, 1129);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        private void abrirGestorDeTareasUser(string usuario)
        {
            Form parentForm = this.FindForm();
            parentForm.Controls.Remove(this);

            TaskBoard taskBoard = new TaskBoard(usuario);
            parentForm.Controls.Add(taskBoard);
        }

        private void abrirGestorDeAdministrador()
        {
            Form parentForm = this.FindForm();
            parentForm.Controls.Remove(this);

            GestorAdmin gestorAdmin = new GestorAdmin();
            parentForm.Controls.Add(gestorAdmin);
        }

        private void iniciarSesion(object sender, EventArgs e)
        {
            string usuario = inputNombre.Text;
            string contraseña = inputPassword.Text;

            Dictionary<string, string> validacionLogin = ValidarLogin(usuario, contraseña);

            if (validacionLogin["validado"] == "true")
            {
                if(validacionLogin["admin"] == "true")
                {
                    abrirGestorDeAdministrador();
                }
                else
                {
                    abrirGestorDeTareasUser(usuario);
                }
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
            respuesta["admin"] = "false";

            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();

                string query = "SELECT password, admin FROM usuarios WHERE nombre = @nombre";
                using (MySqlCommand cmd = new MySqlCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@nombre", usuario);

                    using (MySqlDataReader reader = cmd.ExecuteReader())
                    {
                        if (reader.Read())
                        {
                            string contraseñaEncriptada = reader.GetString("password");
                            string contraseñaDesencriptada = DesencriptarContraseña(contraseñaEncriptada);

                            if (contraseñaDesencriptada == contraseña)
                            {
                                respuesta["errMsg"] = "";
                                respuesta["validado"] = "true";

                                bool esAdmin = reader.GetBoolean("admin");
                                respuesta["admin"] = esAdmin ? "true" : "false";
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

        #endregion
        private System.Windows.Forms.TextBox inputPassword;
        private System.Windows.Forms.TextBox inputNombre;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button botonLogin;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;

        private string connectionString = "Server=localhost;Database=di_ej6;Uid=root;Pwd=;";

        // Clave y IV para la encriptación (deberían ser constantes y seguras)
        private static readonly string key = "1234567890123456";  // 16 bytes para AES-128
        private static readonly string iv = "1234567890123456";   // 16 bytes
    }
}
