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
    public partial class VideojuegoAdmin : UserControl
    {
        private int id;

        public VideojuegoAdmin(int id, string titulo, Image image)
        {
            InitializeComponent();

            this.id = id;

            labelTitulo.Text = titulo;
            pictureBoxPortadaVideojuego.Image = image;
            pictureBoxPortadaVideojuego.SizeMode = PictureBoxSizeMode.Zoom;

            botonEliminarJuego.Click += EliminarVideojuego;
        }
    }
}
