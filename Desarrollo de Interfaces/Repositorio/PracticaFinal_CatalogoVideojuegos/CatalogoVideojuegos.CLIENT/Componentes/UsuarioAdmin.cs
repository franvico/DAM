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
    public partial class UsuarioAdmin : UserControl
    {
        private int id;
        private bool baneado;
        private bool admin;

        public UsuarioAdmin(int id, string nombre, string email, bool baneado, bool admin, Image foto)
        {
            InitializeComponent();

            this.id = id;
            this.baneado = baneado;
            this.admin = admin;

            labelNombre.Text = nombre;
            labelEmail.Text = email;
            pictureBoxFotoDePerfil.Image = foto;
            pictureBoxFotoDePerfil.SizeMode = PictureBoxSizeMode.Zoom;

            botonBanear.Click += CambiarEstadoBaneado;
            botonHacerAdmin.Click += CambiarEstadoAdmin;

            ActualizarBotonAdmin();
            ActualizarBotonBanear();
        }
        private void ActualizarBotonAdmin()
        {
            if (admin)
            {
                botonHacerAdmin.Text = "Deshacer Admin";
                botonHacerAdmin.BackColor = Color.Orange;
            }
            else
            {
                botonHacerAdmin.Text = "Hacer Admin";
                botonHacerAdmin.BackColor = Color.Aquamarine;
            }
        }

        private void ActualizarBotonBanear()
        {
            if (baneado)
            {
                botonBanear.Text = "DESBANEAR";
                botonBanear.BackColor = Color.Green;
            }
            else
            {
                botonBanear.Text = "BANEAR";
                botonBanear.BackColor = Color.IndianRed;
            }
        }
    }
}
