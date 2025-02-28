using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.ListView;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    public partial class HomeAdmin : UserControl
    {
        public HomeAdmin(string nombre, Image foto)
        {
            InitializeComponent();

            labelNombre.Text = nombre;
            pictureBoxFotoDePerfil.Image = foto;
            pictureBoxFotoDePerfil.SizeMode = PictureBoxSizeMode.Zoom;

            CargarCatalogoAdmin();

            this.botonJuegos.Click += (sender, e) => CargarCatalogoAdmin();
            this.botonUsuarios.Click += (sender, e) => CargarUsuariosAdmin();
            this.botonCerrarSesion.Click += (sender, e) => CerrarSesion();
        }
    }
}
