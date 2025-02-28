using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    public partial class HomeUsuario : UserControl
    {
        private int id;
        private List<int> misJuegos;
        private List<int> juegosCarrito;
        CatalogoUsuario catalogoUsuario;
        public HomeUsuario(int id, string nombre, Image foto)
        {
            InitializeComponent();

            this.id = id;
            //this.misJuegos = new List<int>();
            //this.juegosCarrito = new List<int>();

            labelNombre.Text = nombre;
            pictureBoxFotoDePerfil.Image = foto;
            pictureBoxFotoDePerfil.SizeMode = PictureBoxSizeMode.Zoom;

            CargarDatos();

            this.botonCatalogo.Click += (sender, e) => CargarCatalogoUsuario();
            this.botonMisJuegos.Click += (sender, e) => CargarMisJuegos();
            this.botonCarrito.Click += (sender, e) => MostrarCarrito();
            this.panelFotoCarrito.Click += (sender, e) => MostrarCarrito();
            this.botonCerrarSesion.Click += (sender, e) => CerrarSesion();
        }

        private async Task CargarDatos()
        {

            await CargarIdsMisJuegos();

            catalogoUsuario = new CatalogoUsuario(misJuegos);
            catalogoUsuario.Dock = DockStyle.Fill;

            CargarCatalogoUsuario();
        }
    }
}
