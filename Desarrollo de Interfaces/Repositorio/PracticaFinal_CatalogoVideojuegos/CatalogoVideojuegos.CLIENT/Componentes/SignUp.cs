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
    public partial class SignUp : UserControl
    {
        public SignUp()
        {
            InitializeComponent();

            this.linkIniciarSesion.LinkClicked += (sender, e) => AbrirVentanaIniciarSesion();
            this.botonCrearCuenta.Click += async (sender, e) => await RegistrarUsuario();

            this.Paint += new PaintEventHandler(SignUp_Paint);
            this.Resize += Signup_Resize;

            botonAddFoto.Click += AddFotoDePerfil;
        }
        private void SignUp_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawImage(global::CatalogoVideojuegos.CLIENT.Properties.Resources.signup_background, this.ClientRectangle);
        }
        private void Signup_Resize(object sender, EventArgs e)
        {
            panelLoginForm.Location = new Point((this.Width - panelLoginForm.Width - 100), (this.Height - panelLoginForm.Height - 100));
        }
    }
}
