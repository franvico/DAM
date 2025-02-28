using CatalogoVideojuegos.CLIENT.Componentes;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT
{
    public partial class Index : Form
    {
        public Index()
        {
            InitializeComponent();
            ChangeControl(new HomeUsuario(6, "Fran", CargarFotoDePerfil("default.png")));
            //ChangeControl(new Login());

            this.FormClosing += new FormClosingEventHandler(Index_FormClosing);

        }
        public void ChangeControl(UserControl newControl, UserControl oldControl = null)
        {
            if (oldControl != null)
            {
                this.Controls.Remove(oldControl);
            }

            this.Controls.Add(newControl);
            newControl.Dock = DockStyle.Fill;
        }

        private Image CargarFotoDePerfil(string nombreImagen)
        {
            string rutaImagen = Path.Combine(Application.StartupPath, "FotosDePerfil", nombreImagen);

            if (File.Exists(rutaImagen))
            {
                return Image.FromFile(rutaImagen);
            }
            return null;
        }
    }
}
