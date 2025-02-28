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
    public partial class CatalogoUsuario : UserControl
    {
        private List<int> juegosUsuarios;
        public CatalogoUsuario(List<int> juegosUsuario)
        {
            this.juegosUsuarios = juegosUsuario;

            InitializeComponent();
            ActualizarCatalogo();
        }

        public void AddJuegosUsuario(List<int> ids_juegos)
        {
            foreach(int id_juego in ids_juegos)
            {
                juegosUsuarios.Add(id_juego);
            }            
        }
    }
}
