using System.Windows.Forms;
using System;
using System.Data.SqlClient;
using System.Collections.Generic;
using MySql.Data.MySqlClient;

namespace Practica5y6_AdministracionDeTareas
{
    partial class CrearTareas
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.botonCrearTarea = new System.Windows.Forms.Button();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.labelTitulo = new System.Windows.Forms.Label();
            this.inputTitulo = new System.Windows.Forms.TextBox();
            this.tableLayoutPanel4 = new System.Windows.Forms.TableLayoutPanel();
            this.inputDescripcion = new System.Windows.Forms.TextBox();
            this.labelDescripcion = new System.Windows.Forms.Label();
            this.panelControlTaskBoard = new System.Windows.Forms.Panel();
            this.tableLayoutPanel1.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.tableLayoutPanel3.SuspendLayout();
            this.tableLayoutPanel4.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel2, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panelControlTaskBoard, 0, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 76.92308F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 23.07692F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1474, 1129);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 2;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Controls.Add(this.botonCrearTarea, 0, 1);
            this.tableLayoutPanel2.Controls.Add(this.tableLayoutPanel3, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.tableLayoutPanel4, 1, 0);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(3, 871);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 2;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(1468, 255);
            this.tableLayoutPanel2.TabIndex = 0;
            this.tableLayoutPanel2.Paint += new System.Windows.Forms.PaintEventHandler(this.tableLayoutPanel2_Paint);
            // 
            // botonCrearTarea
            // 
            this.botonCrearTarea.BackColor = System.Drawing.Color.YellowGreen;
            this.tableLayoutPanel2.SetColumnSpan(this.botonCrearTarea, 2);
            this.botonCrearTarea.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonCrearTarea.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCrearTarea.Location = new System.Drawing.Point(3, 130);
            this.botonCrearTarea.Name = "botonCrearTarea";
            this.botonCrearTarea.Size = new System.Drawing.Size(1462, 122);
            this.botonCrearTarea.TabIndex = 17;
            this.botonCrearTarea.Text = "Crear Tarea";
            this.botonCrearTarea.UseVisualStyleBackColor = false;
            botonCrearTarea.Click += CrearTarea;
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.ColumnCount = 1;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.Controls.Add(this.labelTitulo, 0, 0);
            this.tableLayoutPanel3.Controls.Add(this.inputTitulo, 0, 1);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(3, 3);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 2;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(728, 121);
            this.tableLayoutPanel3.TabIndex = 18;
            // 
            // labelTitulo
            // 
            this.labelTitulo.AutoSize = true;
            this.labelTitulo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelTitulo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTitulo.Location = new System.Drawing.Point(3, 0);
            this.labelTitulo.Name = "labelTitulo";
            this.labelTitulo.Size = new System.Drawing.Size(722, 60);
            this.labelTitulo.TabIndex = 0;
            this.labelTitulo.Text = "TÍTULO";
            this.labelTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // inputTitulo
            // 
            this.inputTitulo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.inputTitulo.Location = new System.Drawing.Point(3, 63);
            this.inputTitulo.Multiline = true;
            this.inputTitulo.Name = "inputTitulo";
            this.inputTitulo.Size = new System.Drawing.Size(722, 55);
            this.inputTitulo.TabIndex = 1;
            // 
            // tableLayoutPanel4
            // 
            this.tableLayoutPanel4.ColumnCount = 1;
            this.tableLayoutPanel4.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel4.Controls.Add(this.inputDescripcion, 0, 1);
            this.tableLayoutPanel4.Controls.Add(this.labelDescripcion, 0, 0);
            this.tableLayoutPanel4.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel4.Location = new System.Drawing.Point(737, 3);
            this.tableLayoutPanel4.Name = "tableLayoutPanel4";
            this.tableLayoutPanel4.RowCount = 2;
            this.tableLayoutPanel4.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel4.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel4.Size = new System.Drawing.Size(728, 121);
            this.tableLayoutPanel4.TabIndex = 19;
            // 
            // inputDescripcion
            // 
            this.inputDescripcion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.inputDescripcion.Location = new System.Drawing.Point(3, 63);
            this.inputDescripcion.Multiline = true;
            this.inputDescripcion.Name = "inputDescripcion";
            this.inputDescripcion.Size = new System.Drawing.Size(722, 55);
            this.inputDescripcion.TabIndex = 2;
            // 
            // labelDescripcion
            // 
            this.labelDescripcion.AutoSize = true;
            this.labelDescripcion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelDescripcion.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelDescripcion.Location = new System.Drawing.Point(3, 0);
            this.labelDescripcion.Name = "labelDescripcion";
            this.labelDescripcion.Size = new System.Drawing.Size(722, 60);
            this.labelDescripcion.TabIndex = 1;
            this.labelDescripcion.Text = "DESCRIPCION";
            this.labelDescripcion.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelControlTaskBoard
            // 
            this.panelControlTaskBoard.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.panelControlTaskBoard.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelControlTaskBoard.Location = new System.Drawing.Point(3, 3);
            this.panelControlTaskBoard.Name = "panelControlTaskBoard";
            this.panelControlTaskBoard.Size = new System.Drawing.Size(1468, 862);
            this.panelControlTaskBoard.TabIndex = 1;
            // 
            // CrearTareas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1474, 1129);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "CrearTareas";
            this.Text = "CrearTareas";
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel3.ResumeLayout(false);
            this.tableLayoutPanel3.PerformLayout();
            this.tableLayoutPanel4.ResumeLayout(false);
            this.tableLayoutPanel4.PerformLayout();
            this.ResumeLayout(false);

        }

        private void CrearTarea(object sender, EventArgs e)
        {
            // Validamos que los campos no estén vacíos
            if (string.IsNullOrWhiteSpace(inputTitulo.Text) || string.IsNullOrWhiteSpace(inputDescripcion.Text))
            {
                MessageBox.Show("Por favor, complete todos los campos.");
                return;
            }

            // Obtener los datos de los controles
            string titulo = inputTitulo.Text;
            string descripcion = inputDescripcion.Text;
            int usuarioID = ObtenerUsuarioIDPorNombre(nombreUsuario);


            // Guardar la tarea en la base de datos
            GuardarTareaEnBaseDeDatos(titulo, descripcion, usuarioID);

            // Limpiar los campos después de guardar la tarea
            inputTitulo.Clear();
            inputDescripcion.Clear();

            // Recargar el TaskBoard para mostrar la nueva tarea
            RecargarTaskBoard();
        }

        private void GuardarTareaEnBaseDeDatos(string titulo, string descripcion, int usuarioID)
        {
            string estado = "pendiente";
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                string query = "INSERT INTO Tareas (titulo, descripcion, id_usuario, estado) VALUES (@Titulo, @Descripcion, @UsuarioID, @Estado)";
                MySqlCommand cmd = new MySqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@Titulo", titulo);
                cmd.Parameters.AddWithValue("@Descripcion", descripcion);
                cmd.Parameters.AddWithValue("@UsuarioID", usuarioID);
                cmd.Parameters.AddWithValue("@Estado", estado);

                conn.Open();
                cmd.ExecuteNonQuery();
            }
        }
        private int ObtenerUsuarioIDPorNombre(string nombreUsuario)
        {
            int usuarioID = -1;  // Valor por defecto en caso de que no se encuentre el usuario
                       
            string query = "SELECT id FROM Usuarios WHERE nombre = @NombreUsuario"; // Consulta SQL para obtener el ID del usuario

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                MySqlCommand command = new MySqlCommand(query, connection);
                command.Parameters.AddWithValue("@NombreUsuario", nombreUsuario);

                try
                {
                    connection.Open();
                    var result = command.ExecuteScalar();  // Devuelve el primer valor de la consulta (en este caso, el ID del usuario)

                    if (result != DBNull.Value)
                    {
                        usuarioID = Convert.ToInt32(result);  // Convertir el resultado a int
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error al obtener el ID del usuario: " + ex.Message);
                }
            }
            
            return usuarioID;
        }

        private void RecargarTaskBoard()
        {
            // Limpiar el panel antes de agregar el nuevo TaskBoard
            panelControlTaskBoard.Controls.Clear();

            // Instanciar el nuevo TaskBoard
            TaskBoard nuevoTaskBoard = new TaskBoard(nombreUsuario);

            // Agregar el nuevo TaskBoard al panel
            panelControlTaskBoard.Controls.Add(nuevoTaskBoard);

            // Recargar las tareas en el nuevo TaskBoard
            //nuevoTaskBoard.CargarTareas();
        }

        private string connectionString = "Server=localhost;Database=di_ej6;Uid=root;Pwd=;";

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.Button botonCrearTarea;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel3;
        private System.Windows.Forms.Label labelTitulo;
        private System.Windows.Forms.TextBox inputTitulo;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel4;
        private System.Windows.Forms.TextBox inputDescripcion;
        private System.Windows.Forms.Label labelDescripcion;
        private System.Windows.Forms.Panel panelControlTaskBoard;
    }
}