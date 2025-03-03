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

            this.Load += HacerFocoEnElInputEmail;
            AgregarEfectosInputPassword();
            
        }

        private void Login_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawImage(global::CatalogoVideojuegos.CLIENT.Properties.Resources.login_background, this.ClientRectangle);
        }
        private void Login_Resize(object sender, EventArgs e)
        {
            panelLoginForm.Location = new Point((this.Width - panelLoginForm.Width - 100), (this.Height - panelLoginForm.Height - 100));
        }
        private void HacerFocoEnElInputEmail(object sender, EventArgs e)
        {
            this.ActiveControl = inputEmail; // Forzamos el foco
            inputEmail.Focus();
                        
            this.inputEmail.Enter += (s, e2) => {
                if (inputEmail.Text == "Email")
                {
                    inputEmail.Text = "";
                }
            };
            this.inputEmail.Leave += (s, e2) => {
                if (string.IsNullOrWhiteSpace(inputEmail.Text))
                {
                    inputEmail.Text = "Email";
                }
            };

        }
        private void AgregarEfectosInputPassword()
        {
            this.inputPassword.Enter += (s, e) => {
                if (inputPassword.Text == "Password")
                {
                    inputPassword.Text = "";
                    this.inputPassword.PasswordChar = '*';
                }
            };
            this.inputPassword.Leave += (s, e) => {
                if (string.IsNullOrWhiteSpace(inputPassword.Text))
                {
                    inputPassword.Text = "Password";
                    inputPassword.PasswordChar = '\0'; // Esto es para quitar el '*' cuando se vuelve al placeholder
                }
                else
                {
                    inputPassword.PasswordChar = '*'; // Esto asegura que la contraseña se vea en asteriscos cuando no es el placeholder
                }
            };
        }
    }
}
