namespace Practica5y6_AdministracionDeTareas
{
    partial class TaskItem
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
            this.tableItem = new System.Windows.Forms.TableLayoutPanel();
            this.labelDescripcion = new System.Windows.Forms.Label();
            this.labelTitulo = new System.Windows.Forms.Label();
            this.tableItem.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableItem
            // 
            this.tableItem.CausesValidation = false;
            this.tableItem.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.OutsetDouble;
            this.tableItem.ColumnCount = 1;
            this.tableItem.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableItem.Controls.Add(this.labelDescripcion, 0, 1);
            this.tableItem.Controls.Add(this.labelTitulo, 0, 0);
            this.tableItem.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableItem.Enabled = false;
            this.tableItem.Location = new System.Drawing.Point(0, 0);
            this.tableItem.Name = "tableItem";
            this.tableItem.RowCount = 2;
            this.tableItem.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableItem.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableItem.Size = new System.Drawing.Size(476, 196);
            this.tableItem.TabIndex = 0;
            // 
            // labelDescripcion
            // 
            this.labelDescripcion.AutoSize = true;
            this.labelDescripcion.CausesValidation = false;
            this.labelDescripcion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelDescripcion.Enabled = false;
            this.labelDescripcion.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelDescripcion.Location = new System.Drawing.Point(6, 99);
            this.labelDescripcion.Name = "labelDescripcion";
            this.labelDescripcion.Size = new System.Drawing.Size(464, 94);
            this.labelDescripcion.TabIndex = 1;
            this.labelDescripcion.Text = "Descripcion";
            this.labelDescripcion.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelTitulo
            // 
            this.labelTitulo.AutoSize = true;
            this.labelTitulo.CausesValidation = false;
            this.labelTitulo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelTitulo.Enabled = false;
            this.labelTitulo.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTitulo.Location = new System.Drawing.Point(6, 3);
            this.labelTitulo.Name = "labelTitulo";
            this.labelTitulo.Size = new System.Drawing.Size(464, 93);
            this.labelTitulo.TabIndex = 0;
            this.labelTitulo.Text = "Título";
            this.labelTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // TaskItem
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.AppWorkspace;
            this.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.Controls.Add(this.tableItem);
            this.Name = "TaskItem";
            this.Size = new System.Drawing.Size(476, 196);
            this.tableItem.ResumeLayout(false);
            this.tableItem.PerformLayout();
            this.ResumeLayout(false);

        }

        public void SetTarea(string titulo, string descripcion)
        {
            labelTitulo.Text = titulo;
            labelDescripcion.Text = descripcion;
        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableItem;
        private System.Windows.Forms.Label labelTitulo;
        private System.Windows.Forms.Label labelDescripcion;
    }
}
