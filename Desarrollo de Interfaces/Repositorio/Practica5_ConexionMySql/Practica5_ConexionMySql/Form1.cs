using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;
using System.Windows.Forms;

namespace Practica5_ConexionMySql
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

            string connectionString = "Server=localhost;Database=accountsbook.dev;User ID=root;Password=;SslMode=None";

            try
            {
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();
                    MessageBox.Show("Conexión exitosa a MySQL!", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);

                    // Aquí puedes hacer consultas a la base de datos si la conexión fue exitosa.
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al conectar a MySQL: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}
