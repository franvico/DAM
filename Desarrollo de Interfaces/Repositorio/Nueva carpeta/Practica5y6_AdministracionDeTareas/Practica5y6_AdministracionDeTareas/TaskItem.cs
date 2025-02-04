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
    public partial class TaskItem : UserControl
    {
        public string Titulo { get; set; }
        public string Descripcion { get; set; }
        public string Estado { get; set; } // pendiente, enProceso, completada
        public TaskItem(string titulo, string descripcion, string estado)
        {
            Titulo = titulo;
            Descripcion = descripcion;
            Estado = estado;

            InitializeComponent();

            MouseDown += TaskItem_MouseDown;
        }
        private void TaskItem_MouseDown(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                DoDragDrop(this, DragDropEffects.Move);
            }
        }        
    }
}
