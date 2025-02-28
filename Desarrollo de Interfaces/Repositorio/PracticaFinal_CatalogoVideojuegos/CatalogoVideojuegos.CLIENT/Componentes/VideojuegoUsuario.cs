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
    public partial class VideojuegoUsuario : UserControl
    {
        private int id;
        private string estado;
        public VideojuegoUsuario(int id, string titulo, Image image, string estado)
        {
            InitializeComponent();

            this.id = id;
            this.estado = estado;

            labelTitulo.Text = titulo;
            pictureBoxPortadaVideojuego.Image = image;
            pictureBoxPortadaVideojuego.SizeMode = PictureBoxSizeMode.Zoom;

            botonAddAlCarrito.Click += BotonAddAlCarrito_Click;

            modificarTextoBoton();
        }

        private void modificarTextoBoton()
        {
            if (estado == "comprado")
            {
                botonAddAlCarrito.Text = "Iniciar";
            }
            else if (estado == "catalogo")
            {
                botonAddAlCarrito.Text = "Añadir al carrito";
                botonAddAlCarrito.BackColor = System.Drawing.Color.Salmon;
            }
            else if (estado == "enCarrito")
            {
                botonAddAlCarrito.Text = "Quitar del carrito";
                botonAddAlCarrito.BackColor = Color.LightBlue;
            }
        }


    }
}
