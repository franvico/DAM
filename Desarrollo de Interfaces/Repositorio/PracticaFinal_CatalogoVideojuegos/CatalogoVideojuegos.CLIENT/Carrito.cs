using CatalogoVideojuegos.API.Models;
using CatalogoVideojuegos.CLIENT.Componentes;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT
{
    public partial class Carrito : Form
    {
        private int id_usuario;
        private List<Videojuego> videojuegosCarrito;
        private CatalogoUsuario catalogoUsuario;
        public Carrito(int id_usuario, List<Videojuego> videojuegosCarrito, CatalogoUsuario catalogoUsuario)
        {
            InitializeComponent();

            this.id_usuario = id_usuario;
            this.videojuegosCarrito = videojuegosCarrito;
            this.catalogoUsuario = catalogoUsuario;

            CargarContenidoCarrito();

            botonConfirmarCompra.Click += async (sender, e) => await ConfirmarCompra();
        }
    }
}
