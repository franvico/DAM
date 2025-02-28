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
    public partial class VideojuegoCarrito : UserControl
    {
        public VideojuegoCarrito(int id, string titulo, Image portada)
        {
            InitializeComponent();

            labelTitulo.Text = titulo;
            pictureBoxPortada.Image = portada;
            pictureBoxPortada.SizeMode = PictureBoxSizeMode.Zoom;
        }
    }
}
