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
    public partial class CrearTareas : Form
    {
        public string nombreUsuario { get; set; }

        public CrearTareas(string nombre)
        {
            nombreUsuario = nombre;

            InitializeComponent();

            TaskBoard taskBoard = new TaskBoard(nombreUsuario);
            panelControlTaskBoard.Controls.Add(taskBoard);
        }

        private void tableLayoutPanel2_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
