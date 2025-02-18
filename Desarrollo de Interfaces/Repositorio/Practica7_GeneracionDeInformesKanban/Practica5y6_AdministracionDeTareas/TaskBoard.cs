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
    public partial class TaskBoard : UserControl
    {
        private string connectionString = "Server=localhost;Database=di_ej6;Uid=root;Pwd=;";
        private static List<string> tareas = new List<string>();
        private static string nombreUsuario;
        public TaskBoard(string usuario)
        {
            InitializeComponent();
            nombreUsuario = usuario;
            labelSesionUser.Text = $"Tareas de {nombreUsuario}".ToUpper();

            CargarTareas(nombreUsuario);
        }
    

        private void CargarTareas(string nombreUsuario)
        {
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

            // Unimos todas las tareas en un solo string para pasarlas más fácilmente
            foreach (var tarea in tareas)
            {
                string[] detallesTarea = tarea.Split(';');  // Título, Descripción, Estado

                if (detallesTarea.Length >= 3)
                {
                    string titulo = detallesTarea[0];
                    string descripcion = detallesTarea[1];
                    string estado = detallesTarea[2];

                    // Creamos un TaskItem para cada tarea
                    TaskItem taskItem = new TaskItem(titulo, descripcion, estado);
                    taskItem.SetTarea(titulo, descripcion);  // Método que configura el TaskItem

                    // Dependiendo del estado, agregamos el TaskItem al panel correspondiente
                    switch (estado)
                    {
                        case "pendiente":
                            panelPendientes.Controls.Add(taskItem);
                            ReorganizarTareas(panelPendientes);
                            break;

                        case "enProceso":
                            panelEnProceso.Controls.Add(taskItem);
                            ReorganizarTareas(panelEnProceso);
                            break;

                        case "completada":
                            panelCompletadas.Controls.Add(taskItem);
                            ReorganizarTareas(panelCompletadas);
                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }
}
