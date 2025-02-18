using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Practica5y6_AdministracionDeTareas
{
    public partial class GestorAdmin : UserControl
    {
        public GestorAdmin()
        {
            InitializeComponent();
            CargarUsuariosEnPanel();
        }
        private void CargarUsuariosEnPanel()
        {
            // Limpiar el DataGridView antes de agregar nuevos datos
            dataGridViewUsuarios.Rows.Clear();
            dataGridViewUsuarios.Columns.Clear();

            // Conexión y comando SQL para obtener los usuarios
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                string query = "SELECT nombre, CASE WHEN admin = 1 THEN 'Sí' ELSE 'No' END AS admin FROM Usuarios"; // Ajusta esto según tu base de datos
                MySqlCommand cmd = new MySqlCommand(query, conn);
                MySqlDataReader reader = cmd.ExecuteReader();

                // Agregar las columnas al DataGridView
                dataGridViewUsuarios.Columns.Add("nombre", "Nombre de Usuario");
                dataGridViewUsuarios.Columns.Add("admin", "Es Admin");

                // Leer los datos y agregar filas al DataGridView
                while (reader.Read())
                {
                    string nombreUsuario = reader["nombre"].ToString();
                    string esAdmin = reader["admin"].ToString();

                    // Agregar una nueva fila con los datos de cada usuario
                    dataGridViewUsuarios.Rows.Add(nombreUsuario, esAdmin);
                }
            }
        }

        private void labelUsuarios_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void tableLayoutPanel2_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
