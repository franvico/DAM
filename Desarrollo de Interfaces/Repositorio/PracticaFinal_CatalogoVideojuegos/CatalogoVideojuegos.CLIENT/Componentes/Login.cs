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

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    public partial class Login : UserControl
    {
        public Login()
        {
            InitializeComponent();

            this.linkCrearCuenta.LinkClicked += (sender, e) => AbrirVentanaCrearCuenta();
            this.botonIniciarSesion.Click += async (sender, e) => await IniciarSesion();

            this.Paint += new PaintEventHandler(Login_Paint);
            this.Resize += Login_Resize;
        }

        private void Login_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawImage(global::CatalogoVideojuegos.CLIENT.Properties.Resources.login_background, this.ClientRectangle);
        }
        private void Login_Resize(object sender, EventArgs e)
        {
            panelLoginForm.Location = new Point((this.Width - panelLoginForm.Width - 100), (this.Height - panelLoginForm.Height - 100));
        }
    }
}
