using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Practica5y6_AdministracionDeTareas
{
    public partial class InformeDeTareas : Form
    {
        private string connectionString = "Server=localhost;Database=di_ej6;Uid=root;Pwd=;";
        public InformeDeTareas(string nombreUsuario)
        {
            InitializeComponent();
            CargarDatosEnGrafico(nombreUsuario);
        }
        private void CargarDatosEnGrafico(string nombreUsuario)
        {
            List<string> tareas = CargarTareas(nombreUsuario);
            // Contar cuántas tareas hay de cada estado
            var estados = tareas
                .Select(t => t.Split(';').Last()) // Extraer el estado
                .GroupBy(estado => estado)
                .Select(g => new { Estado = g.Key, Cantidad = g.Count() })
                .ToList();

            // Limpiar serie existente
            chartTareas.Series["Series1"].Points.Clear();

            // Agregar datos al gráfico con colores personalizados
            foreach (var estado in estados)
            {
                int index = chartTareas.Series["Series1"].Points.AddXY(estado.Estado, estado.Cantidad);

                // Asignar colores según el estado de la tarea
                switch (estado.Estado.ToLower()) // Convertimos a minúsculas para evitar errores
                {
                    case "pendiente":
                        chartTareas.Series["Series1"].Points[index].Color = Color.Red;
                        break;
                    case "enproceso":
                        chartTareas.Series["Series1"].Points[index].Color = Color.Yellow;
                        break;
                    case "completada":
                        chartTareas.Series["Series1"].Points[index].Color = Color.Green;
                        break;
                    default:
                        chartTareas.Series["Series1"].Points[index].Color = Color.Gray; // Por si hay algún estado desconocido
                        break;
                }
            }

            // Mostrar etiquetas con los valores
            chartTareas.Series["Series1"].IsValueShownAsLabel = true;
        }


        private List<string> CargarTareas(string nombreUsuario)
        {

            List<string> tareas = new List<string>();

            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();

                string tareasQuery = "SELECT t.titulo, t.descripcion, t.estado FROM tareas t JOIN usuarios u ON t.id_usuario = u.id WHERE u.nombre = @nombre";
                using (MySqlCommand cmdTareas = new MySqlCommand(tareasQuery, conn))
                {
                    cmdTareas.Parameters.AddWithValue("@nombre", nombreUsuario);

                    using (MySqlDataReader readerTareas = cmdTareas.ExecuteReader())
                    {
                        while (readerTareas.Read())
                        {
                            string tarea = readerTareas.GetString("titulo") + ";" + readerTareas.GetString("descripcion") + ";" + readerTareas.GetString("estado");
                            tareas.Add(tarea);
                        }
                    }
                }
            }
            
            return tareas;
        }
    }
}
