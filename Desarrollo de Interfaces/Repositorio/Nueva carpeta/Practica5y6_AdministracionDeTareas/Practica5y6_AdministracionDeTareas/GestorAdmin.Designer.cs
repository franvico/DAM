using System.Windows.Forms;
using System;
using System.Data.SqlClient;
using MySql.Data.MySqlClient;
using static Mysqlx.Expect.Open.Types.Condition.Types;
using System.IO;
using System.Security.Cryptography;
using System.Text;

namespace Practica5y6_AdministracionDeTareas
{
    partial class GestorAdmin
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
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.inputPassword = new System.Windows.Forms.TextBox();
            this.botonCrearUsuario = new System.Windows.Forms.Button();
            this.inpuntNombre = new System.Windows.Forms.TextBox();
            this.botonCerrarSesion = new System.Windows.Forms.Button();
            this.labelAdmin = new System.Windows.Forms.Label();
            this.labelUsuarios = new System.Windows.Forms.Label();
            this.panelUsuarios = new System.Windows.Forms.Panel();
            this.dataGridViewUsuarios = new System.Windows.Forms.DataGridView();
            this.checkBox1 = new System.Windows.Forms.CheckBox();
            this.tableLayoutPanel1.SuspendLayout();
            this.panelUsuarios.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewUsuarios)).BeginInit();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.OutsetDouble;
            this.tableLayoutPanel1.ColumnCount = 4;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 9.091184F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30.30395F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30.30395F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30.30092F));
            this.tableLayoutPanel1.Controls.Add(this.inputPassword, 2, 3);
            this.tableLayoutPanel1.Controls.Add(this.botonCrearUsuario, 3, 3);
            this.tableLayoutPanel1.Controls.Add(this.inpuntNombre, 1, 3);
            this.tableLayoutPanel1.Controls.Add(this.botonCerrarSesion, 2, 0);
            this.tableLayoutPanel1.Controls.Add(this.labelAdmin, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.labelUsuarios, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panelUsuarios, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.checkBox1, 0, 3);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 4;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 11.90476F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 8.333333F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 71.42857F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 8.333333F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1500, 1200);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // inputPassword
            // 
            this.inputPassword.Dock = System.Windows.Forms.DockStyle.Fill;
            this.inputPassword.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputPassword.Location = new System.Drawing.Point(597, 1100);
            this.inputPassword.Multiline = true;
            this.inputPassword.Name = "inputPassword";
            this.inputPassword.PasswordChar = '*';
            this.inputPassword.Size = new System.Drawing.Size(444, 94);
            this.inputPassword.TabIndex = 15;
            this.inputPassword.Text = "Introduzca una password";
            this.inputPassword.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // botonCrearUsuario
            // 
            this.botonCrearUsuario.BackColor = System.Drawing.Color.YellowGreen;
            this.botonCrearUsuario.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonCrearUsuario.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCrearUsuario.Location = new System.Drawing.Point(1050, 1100);
            this.botonCrearUsuario.Name = "botonCrearUsuario";
            this.botonCrearUsuario.Size = new System.Drawing.Size(444, 94);
            this.botonCrearUsuario.TabIndex = 14;
            this.botonCrearUsuario.Text = "Crear Usuario";
            this.botonCrearUsuario.UseVisualStyleBackColor = false;
            this.botonCrearUsuario.Click += new System.EventHandler(this.CrearUsuario);
            // 
            // inpuntNombre
            // 
            this.inpuntNombre.Dock = System.Windows.Forms.DockStyle.Fill;
            this.inpuntNombre.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inpuntNombre.Location = new System.Drawing.Point(144, 1100);
            this.inpuntNombre.Multiline = true;
            this.inpuntNombre.Name = "inpuntNombre";
            this.inpuntNombre.Size = new System.Drawing.Size(444, 94);
            this.inpuntNombre.TabIndex = 12;
            this.inpuntNombre.Text = "Introduzca un nombre";
            this.inpuntNombre.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            this.inpuntNombre.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // botonCerrarSesion
            // 
            this.botonCerrarSesion.AutoSize = true;
            this.botonCerrarSesion.BackColor = System.Drawing.Color.IndianRed;
            this.botonCerrarSesion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonCerrarSesion.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCerrarSesion.Location = new System.Drawing.Point(1050, 6);
            this.botonCerrarSesion.Name = "botonCerrarSesion";
            this.botonCerrarSesion.Size = new System.Drawing.Size(444, 135);
            this.botonCerrarSesion.TabIndex = 8;
            this.botonCerrarSesion.Text = "Cerrar Sesión";
            this.botonCerrarSesion.UseVisualStyleBackColor = false;
            this.botonCerrarSesion.Click += new System.EventHandler(this.CerrarSesion);
            // 
            // labelAdmin
            // 
            this.labelAdmin.AutoSize = true;
            this.tableLayoutPanel1.SetColumnSpan(this.labelAdmin, 3);
            this.labelAdmin.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelAdmin.Font = new System.Drawing.Font("Microsoft Sans Serif", 25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelAdmin.Location = new System.Drawing.Point(6, 3);
            this.labelAdmin.Name = "labelAdmin";
            this.labelAdmin.Size = new System.Drawing.Size(1035, 141);
            this.labelAdmin.TabIndex = 0;
            this.labelAdmin.Text = "GESTIÓN DE ADMIN";
            this.labelAdmin.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelUsuarios
            // 
            this.labelUsuarios.AutoSize = true;
            this.tableLayoutPanel1.SetColumnSpan(this.labelUsuarios, 4);
            this.labelUsuarios.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelUsuarios.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelUsuarios.Location = new System.Drawing.Point(6, 147);
            this.labelUsuarios.Name = "labelUsuarios";
            this.labelUsuarios.Size = new System.Drawing.Size(1488, 98);
            this.labelUsuarios.TabIndex = 9;
            this.labelUsuarios.Text = "Usuarios";
            this.labelUsuarios.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.labelUsuarios.Click += new System.EventHandler(this.labelUsuarios_Click);
            // 
            // panelUsuarios
            // 
            this.tableLayoutPanel1.SetColumnSpan(this.panelUsuarios, 4);
            this.panelUsuarios.Controls.Add(this.dataGridViewUsuarios);
            this.panelUsuarios.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelUsuarios.Location = new System.Drawing.Point(6, 251);
            this.panelUsuarios.Name = "panelUsuarios";
            this.panelUsuarios.Size = new System.Drawing.Size(1488, 840);
            this.panelUsuarios.TabIndex = 10;
            // 
            // dataGridViewUsuarios
            // 
            this.dataGridViewUsuarios.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.dataGridViewUsuarios.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewUsuarios.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dataGridViewUsuarios.Location = new System.Drawing.Point(0, 0);
            this.dataGridViewUsuarios.Name = "dataGridViewUsuarios";
            this.dataGridViewUsuarios.RowHeadersWidth = 82;
            this.dataGridViewUsuarios.RowTemplate.Height = 24;
            this.dataGridViewUsuarios.Size = new System.Drawing.Size(1488, 840);
            this.dataGridViewUsuarios.TabIndex = 0;
            this.dataGridViewUsuarios.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewUsuarios_CellClick);
            // 
            // checkBox1
            // 
            this.checkBox1.AutoSize = true;
            this.checkBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.checkBox1.Location = new System.Drawing.Point(6, 1100);
            this.checkBox1.Name = "checkBox1";
            this.checkBox1.Size = new System.Drawing.Size(129, 94);
            this.checkBox1.TabIndex = 16;
            this.checkBox1.Text = "Admin";
            this.checkBox1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.checkBox1.UseVisualStyleBackColor = true;
            // 
            // GestorAdmin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "GestorAdmin";
            this.Size = new System.Drawing.Size(1500, 1200);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.panelUsuarios.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewUsuarios)).EndInit();
            this.ResumeLayout(false);

        }
        private DataGridView dataGridViewUsuarios;
        private void dataGridViewUsuarios_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            // Verificar si la celda seleccionada no es una celda de encabezado
            if (e.RowIndex >= 0)
            {
                // Obtener el nombre del usuario desde la primera columna (nombre)
                string nombreUsuario = dataGridViewUsuarios.Rows[e.RowIndex].Cells[0].Value.ToString();

                // Aquí puedes abrir el formulario CrearTarea y pasar el nombre del usuario
                CrearTareas formularioTarea = new CrearTareas(nombreUsuario);
                formularioTarea.ShowDialog();
            }
        }
        private void CerrarSesion(object sender, EventArgs e)
        {
            Form parentForm = this.FindForm();
            parentForm.Controls.Remove(this);

            ControlLogin controlLogin = new ControlLogin();
            parentForm.Controls.Add(controlLogin);

        }

        private void CrearUsuario(object sender, EventArgs e)
        {
            // Recoger los datos de los inputs
            string nombre = inpuntNombre.Text;
            string password = inputPassword.Text;
            bool esAdmin = checkBox1.Checked;

            if (nombre == "")
            {
                MessageBox.Show("Introduzca un nombre.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            else if (password == "")
            {
                MessageBox.Show("Introduzca una contraseña.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            if (UsuarioExistente(nombre))
            {
                MessageBox.Show("El usuario ya existe. Elija otro nombre.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            GuardarUsuario(nombre, password, esAdmin);

            

            // Actualizar la tabla de usuarios en el panel
            CargarUsuariosEnPanel();
        }

        private void GuardarUsuario(string nombre, string password, bool esAdmin)
        {
            // Insertar el nuevo usuario en la base de datos
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                string contraseñaEncriptada = EncriptarContraseña(password);

                conn.Open();
                string query = "INSERT INTO Usuarios (nombre, password, admin) VALUES (@Nombre, @Password, @EsAdmin)";

                using (MySqlCommand cmd = new MySqlCommand(query, conn))
                {
                    // Añadir los parámetros
                    cmd.Parameters.AddWithValue("@Nombre", nombre);
                    cmd.Parameters.AddWithValue("@Password", contraseñaEncriptada);
                    cmd.Parameters.AddWithValue("@EsAdmin", esAdmin ? 1 : 0);

                    // Ejecutar el comando
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

        private string connectionString = "Server=localhost;Database=di_ej6;Uid=root;Pwd=;";

        // Clave y IV para la encriptación (deberían ser constantes y seguras)
        private static readonly string key = "1234567890123456";  // 16 bytes para AES-128
        private static readonly string iv = "1234567890123456";   // 16 bytes

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label labelAdmin;
        private System.Windows.Forms.Button botonCerrarSesion;
        private System.Windows.Forms.Label labelUsuarios;
        private System.Windows.Forms.Panel panelUsuarios;
        private System.Windows.Forms.TextBox inpuntNombre;
        private System.Windows.Forms.TextBox inputPassword;
        private System.Windows.Forms.Button botonCrearUsuario;
        private System.Windows.Forms.CheckBox checkBox1;
    }
}
