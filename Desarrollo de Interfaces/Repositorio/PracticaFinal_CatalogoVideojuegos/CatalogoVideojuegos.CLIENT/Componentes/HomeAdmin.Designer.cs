using System.Windows.Forms;

namespace CatalogoVideojuegos.CLIENT.Componentes
{
    partial class HomeAdmin
    {
        /// <summary> 
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de componentes

        /// <summary> 
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.panelMenuLateral = new System.Windows.Forms.Panel();
            this.botonCerrarSesion = new System.Windows.Forms.Button();
            this.botonUsuarios = new System.Windows.Forms.Button();
            this.botonJuegos = new System.Windows.Forms.Button();
            this.panelContenido = new System.Windows.Forms.Panel();
            this.label1 = new System.Windows.Forms.Label();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.panelNavBar = new System.Windows.Forms.Panel();
            this.pictureBoxFotoDePerfil = new System.Windows.Forms.PictureBox();
            this.labelNombre = new System.Windows.Forms.Label();
            this.tableLayoutPanel1.SuspendLayout();
            this.panelMenuLateral.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.panelNavBar.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFotoDePerfil)).BeginInit();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.BackgroundImage = global::CatalogoVideojuegos.CLIENT.Properties.Resources.homeAdmin_background;
            this.tableLayoutPanel1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 70F));
            this.tableLayoutPanel1.Controls.Add(this.panelNavBar, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.panelMenuLateral, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panelContenido, 1, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 15F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 85F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1500, 1200);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // panelMenuLateral
            // 
            this.panelMenuLateral.BackColor = System.Drawing.Color.Transparent;
            this.panelMenuLateral.Controls.Add(this.botonUsuarios);
            this.panelMenuLateral.Controls.Add(this.botonJuegos);
            this.panelMenuLateral.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelMenuLateral.Location = new System.Drawing.Point(3, 183);
            this.panelMenuLateral.Name = "panelMenuLateral";
            this.panelMenuLateral.Size = new System.Drawing.Size(444, 1014);
            this.panelMenuLateral.TabIndex = 1;
            // 
            // botonCerrarSesion
            // 
            this.botonCerrarSesion.BackColor = System.Drawing.Color.IndianRed;
            this.botonCerrarSesion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonCerrarSesion.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCerrarSesion.Location = new System.Drawing.Point(3, 132);
            this.botonCerrarSesion.Name = "botonCerrarSesion";
            this.botonCerrarSesion.Size = new System.Drawing.Size(447, 45);
            this.botonCerrarSesion.TabIndex = 1;
            this.botonCerrarSesion.Text = "CERRAR SESIÓN";
            this.botonCerrarSesion.UseVisualStyleBackColor = false;
            // 
            // botonUsuarios
            // 
            this.botonUsuarios.BackColor = System.Drawing.Color.DeepSkyBlue;
            this.botonUsuarios.Font = new System.Drawing.Font("Microsoft YaHei", 10.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonUsuarios.Location = new System.Drawing.Point(105, 261);
            this.botonUsuarios.Name = "botonUsuarios";
            this.botonUsuarios.Size = new System.Drawing.Size(228, 57);
            this.botonUsuarios.TabIndex = 0;
            this.botonUsuarios.Text = "USUARIOS";
            this.botonUsuarios.UseVisualStyleBackColor = false;
            // 
            // botonJuegos
            // 
            this.botonJuegos.BackColor = System.Drawing.Color.DeepSkyBlue;
            this.botonJuegos.Font = new System.Drawing.Font("Microsoft YaHei", 10.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonJuegos.Location = new System.Drawing.Point(105, 158);
            this.botonJuegos.Name = "botonJuegos";
            this.botonJuegos.Size = new System.Drawing.Size(228, 57);
            this.botonJuegos.TabIndex = 0;
            this.botonJuegos.Text = "JUEGOS";
            this.botonJuegos.UseVisualStyleBackColor = false;
            // 
            // panelContenido
            // 
            this.panelContenido.BackColor = System.Drawing.Color.Transparent;
            this.panelContenido.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelContenido.Location = new System.Drawing.Point(453, 183);
            this.panelContenido.Name = "panelContenido";
            this.panelContenido.Size = new System.Drawing.Size(1044, 1014);
            this.panelContenido.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.Transparent;
            this.label1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label1.Font = new System.Drawing.Font("Swis721 Blk BT", 22.125F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(456, 0);
            this.label1.Name = "label1";
            this.tableLayoutPanel2.SetRowSpan(this.label1, 3);
            this.label1.Size = new System.Drawing.Size(1041, 180);
            this.label1.TabIndex = 0;
            this.label1.Text = "PANEL DE ADMINISTRADOR";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.BackColor = System.Drawing.Color.Transparent;
            this.tableLayoutPanel2.ColumnCount = 2;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30.2F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 69.8F));
            this.tableLayoutPanel2.Controls.Add(this.botonCerrarSesion, 0, 2);
            this.tableLayoutPanel2.Controls.Add(this.label1, 1, 0);
            this.tableLayoutPanel2.Controls.Add(this.pictureBoxFotoDePerfil, 0, 0);
            this.tableLayoutPanel2.Controls.Add(this.labelNombre, 0, 1);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 3;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 49F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 51F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(1500, 180);
            this.tableLayoutPanel2.TabIndex = 1;
            // 
            // panelNavBar
            // 
            this.panelNavBar.BackgroundImage = global::CatalogoVideojuegos.CLIENT.Properties.Resources.navbarAdmin_background;
            this.panelNavBar.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.tableLayoutPanel1.SetColumnSpan(this.panelNavBar, 2);
            this.panelNavBar.Controls.Add(this.tableLayoutPanel2);
            this.panelNavBar.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelNavBar.Location = new System.Drawing.Point(0, 0);
            this.panelNavBar.Margin = new System.Windows.Forms.Padding(0);
            this.panelNavBar.Name = "panelNavBar";
            this.panelNavBar.Size = new System.Drawing.Size(1500, 180);
            this.panelNavBar.TabIndex = 0;
            // 
            // pictureBoxFotoDePerfil
            // 
            this.pictureBoxFotoDePerfil.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBoxFotoDePerfil.Location = new System.Drawing.Point(3, 3);
            this.pictureBoxFotoDePerfil.Name = "pictureBoxFotoDePerfil";
            this.pictureBoxFotoDePerfil.Size = new System.Drawing.Size(447, 74);
            this.pictureBoxFotoDePerfil.TabIndex = 2;
            this.pictureBoxFotoDePerfil.TabStop = false;
            // 
            // labelNombre
            // 
            this.labelNombre.AutoSize = true;
            this.labelNombre.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelNombre.Font = new System.Drawing.Font("Microsoft Tai Le", 10.875F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelNombre.ForeColor = System.Drawing.Color.White;
            this.labelNombre.Location = new System.Drawing.Point(3, 80);
            this.labelNombre.Name = "labelNombre";
            this.labelNombre.Size = new System.Drawing.Size(447, 49);
            this.labelNombre.TabIndex = 3;
            this.labelNombre.Text = "NOMBRE";
            this.labelNombre.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // HomeAdmin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "HomeAdmin";
            this.Size = new System.Drawing.Size(1500, 1200);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.panelMenuLateral.ResumeLayout(false);
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            this.panelNavBar.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBoxFotoDePerfil)).EndInit();
            this.ResumeLayout(false);

        }

        private void CargarCatalogoAdmin()
        {
            this. panelContenido.Controls.Clear();

            CatalogoAdmin catalogoAdmin = new CatalogoAdmin();
            catalogoAdmin.Dock = DockStyle.Fill;            

            this.panelContenido.Controls.Add(catalogoAdmin);
        }
        private void CargarUsuariosAdmin()
        {
            this.panelContenido.Controls.Clear();

            UsuariosAdmin usuariosAdmin = new UsuariosAdmin();
            usuariosAdmin.Dock = DockStyle.Fill;

            this.panelContenido.Controls.Add(usuariosAdmin);
        }

        private void CerrarSesion()
        {
            Form index = this.FindForm();
            (index as Index)?.ChangeControl(new Login(), this);
        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Panel panelMenuLateral;
        private System.Windows.Forms.Panel panelContenido;
        private System.Windows.Forms.Button botonJuegos;
        private System.Windows.Forms.Button botonCerrarSesion;
        private System.Windows.Forms.Button botonUsuarios;
        private Panel panelNavBar;
        private TableLayoutPanel tableLayoutPanel2;
        private Label label1;
        private PictureBox pictureBoxFotoDePerfil;
        private Label labelNombre;
    }
}
