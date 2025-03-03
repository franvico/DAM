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

            this.Load += HacerFocoEnElInputNombre;
            AgregarEfectosInputEmail();
            AgregarEfectosInputPassword();
        }
        private void SignUp_Paint(object sender, PaintEventArgs e)
        {
            e.Graphics.DrawImage(global::CatalogoVideojuegos.CLIENT.Properties.Resources.signup_background, this.ClientRectangle);
        }
        private void Signup_Resize(object sender, EventArgs e)
        {
            panelLoginForm.Location = new Point((this.Width - panelLoginForm.Width - 100), (this.Height - panelLoginForm.Height - 100));
        }
        private void HacerFocoEnElInputNombre(object sender, EventArgs e)
        {
            this.ActiveControl = inputNombre; // Forzamos el foco
            inputNombre.Focus();

            this.inputNombre.Enter += (s, e2) => {
                if (inputNombre.Text == "Nombre")
                {
                    inputNombre.Text = "";
                }
            };
            this.inputNombre.Leave += (s, e2) => {
                if (string.IsNullOrWhiteSpace(inputNombre.Text))
                {
                    inputNombre.Text = "Nombre";
                }
            };

        }
        private void AgregarEfectosInputEmail()
        {
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
