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
        private bool descatalogado;

        public VideojuegoAdmin(int id, string titulo, Image image, bool descatalogado)
        {
            InitializeComponent();

            this.id = id;
            this.descatalogado = descatalogado;

            labelTitulo.Text = titulo;
            pictureBoxPortadaVideojuego.Image = image;
            pictureBoxPortadaVideojuego.SizeMode = PictureBoxSizeMode.Zoom;

            modificarTextoBoton();
        }

        private void modificarTextoBoton()
        {
            if (descatalogado)
            {
                botonEliminarJuego.Text = "DESCATALOGADO";
                botonEliminarJuego.BackColor = System.Drawing.Color.Gray;
                botonEliminarJuego.ForeColor = System.Drawing.Color.White;
                botonEliminarJuego.Enabled = false;
            }
            else
            {
                botonEliminarJuego.Text = "ELIMINAR";
                botonEliminarJuego.BackColor = System.Drawing.Color.IndianRed;
                botonEliminarJuego.ForeColor = System.Drawing.Color.White;
                botonEliminarJuego.Click += EliminarVideojuego;
            }
        }
    }
}
