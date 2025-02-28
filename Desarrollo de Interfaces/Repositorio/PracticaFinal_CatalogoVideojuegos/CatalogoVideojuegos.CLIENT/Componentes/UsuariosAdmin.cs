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
    public partial class UsuariosAdmin : UserControl
    {
        public UsuariosAdmin()
        {
            InitializeComponent();
            ActualizarUsuarios();
        }
    }
}
