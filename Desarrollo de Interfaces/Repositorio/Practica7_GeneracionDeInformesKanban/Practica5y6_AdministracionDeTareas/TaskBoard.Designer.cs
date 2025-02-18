using MySql.Data.MySqlClient;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace Practica5y6_AdministracionDeTareas
{
    partial class TaskBoard
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
            this.labelCompletadas = new System.Windows.Forms.Label();
            this.labelEnProceso = new System.Windows.Forms.Label();
            this.labelPendientes = new System.Windows.Forms.Label();
            this.panelPendientes = new System.Windows.Forms.Panel();
            this.panelEnProceso = new System.Windows.Forms.Panel();
            this.panelCompletadas = new System.Windows.Forms.Panel();
            this.labelSesionUser = new System.Windows.Forms.Label();
            this.botonCerrarSesion = new System.Windows.Forms.Button();
            this.botonVerInformeTareas = new System.Windows.Forms.Button();
            this.tableLayoutPanel1.SuspendLayout();
            this.SuspendLayout();

            // AÑADO ESTAS LÍNEAS PORQUE AL AÑADIR EL BOTÓN DE "VER INFORME" ME DEJÓ DE FUNCIONAR EL ARRASTRE DEL TASKITEM
            panelPendientes.AllowDrop = true;
            panelEnProceso.AllowDrop = true;
            panelCompletadas.AllowDrop = true;
            panelPendientes.DragEnter += Panel_DragEnter;
            panelEnProceso.DragEnter += Panel_DragEnter;
            panelCompletadas.DragEnter += Panel_DragEnter;
            panelPendientes.DragDrop += Panel_DragDrop;
            panelEnProceso.DragDrop += Panel_DragDrop;
            panelCompletadas.DragDrop += Panel_DragDrop;
            botonVerInformeTareas.BringToFront();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.InsetDouble;
            this.tableLayoutPanel1.ColumnCount = 3;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33334F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33334F));
            this.tableLayoutPanel1.Controls.Add(this.botonVerInformeTareas, 1, 0);
            this.tableLayoutPanel1.Controls.Add(this.labelCompletadas, 2, 1);
            this.tableLayoutPanel1.Controls.Add(this.labelEnProceso, 1, 1);
            this.tableLayoutPanel1.Controls.Add(this.labelPendientes, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.panelPendientes, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.panelEnProceso, 1, 2);
            this.tableLayoutPanel1.Controls.Add(this.panelCompletadas, 2, 2);
            this.tableLayoutPanel1.Controls.Add(this.labelSesionUser, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.botonCerrarSesion, 2, 0);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 3;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 7F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 7F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 93F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1496, 1196);
            this.tableLayoutPanel1.TabIndex = 0;
            // 
            // labelCompletadas
            // 
            this.labelCompletadas.AutoSize = true;
            this.labelCompletadas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelCompletadas.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelCompletadas.Location = new System.Drawing.Point(1000, 83);
            this.labelCompletadas.Name = "labelCompletadas";
            this.labelCompletadas.Size = new System.Drawing.Size(490, 77);
            this.labelCompletadas.TabIndex = 2;
            this.labelCompletadas.Text = "COMPLETADAS";
            this.labelCompletadas.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelEnProceso
            // 
            this.labelEnProceso.AutoSize = true;
            this.labelEnProceso.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelEnProceso.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelEnProceso.Location = new System.Drawing.Point(503, 83);
            this.labelEnProceso.Name = "labelEnProceso";
            this.labelEnProceso.Size = new System.Drawing.Size(488, 77);
            this.labelEnProceso.TabIndex = 1;
            this.labelEnProceso.Text = "EN PROCESO";
            this.labelEnProceso.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelPendientes
            // 
            this.labelPendientes.AutoSize = true;
            this.labelPendientes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelPendientes.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelPendientes.Location = new System.Drawing.Point(6, 83);
            this.labelPendientes.Name = "labelPendientes";
            this.labelPendientes.Size = new System.Drawing.Size(488, 77);
            this.labelPendientes.TabIndex = 0;
            this.labelPendientes.Text = "PENDIENTES";
            this.labelPendientes.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelPendientes
            // 
            this.panelPendientes.AllowDrop = true;
            this.panelPendientes.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelPendientes.Location = new System.Drawing.Point(6, 166);
            this.panelPendientes.Name = "panelPendientes";
            this.panelPendientes.Size = new System.Drawing.Size(488, 1024);
            this.panelPendientes.TabIndex = 3;
            // 
            // panelEnProceso
            // 
            this.panelEnProceso.AllowDrop = true;
            this.panelEnProceso.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelEnProceso.Location = new System.Drawing.Point(503, 166);
            this.panelEnProceso.Name = "panelEnProceso";
            this.panelEnProceso.Size = new System.Drawing.Size(488, 1024);
            this.panelEnProceso.TabIndex = 4;
            // 
            // panelCompletadas
            // 
            this.panelCompletadas.AllowDrop = true;
            this.panelCompletadas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelCompletadas.Location = new System.Drawing.Point(1000, 166);
            this.panelCompletadas.Name = "panelCompletadas";
            this.panelCompletadas.Size = new System.Drawing.Size(490, 1024);
            this.panelCompletadas.TabIndex = 5;
            // 
            // labelSesionUser
            // 
            this.labelSesionUser.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelSesionUser.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelSesionUser.Location = new System.Drawing.Point(6, 3);
            this.labelSesionUser.Name = "labelSesionUser";
            this.labelSesionUser.Size = new System.Drawing.Size(488, 77);
            this.labelSesionUser.TabIndex = 6;
            this.labelSesionUser.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // botonCerrarSesion
            // 
            this.botonCerrarSesion.AutoSize = true;
            this.botonCerrarSesion.BackColor = System.Drawing.Color.IndianRed;
            this.botonCerrarSesion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonCerrarSesion.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonCerrarSesion.Location = new System.Drawing.Point(1000, 6);
            this.botonCerrarSesion.Name = "botonCerrarSesion";
            this.botonCerrarSesion.Size = new System.Drawing.Size(490, 71);
            this.botonCerrarSesion.TabIndex = 7;
            this.botonCerrarSesion.Text = "Cerrar Sesión";
            this.botonCerrarSesion.UseVisualStyleBackColor = false;
            this.botonCerrarSesion.Click += new System.EventHandler(this.CerrarSesion);
            // 
            // botonVerInformeTareas
            // 
            this.botonVerInformeTareas.AutoSize = true;
            this.botonVerInformeTareas.BackColor = System.Drawing.Color.Gold;
            this.botonVerInformeTareas.Dock = System.Windows.Forms.DockStyle.Fill;
            this.botonVerInformeTareas.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.botonVerInformeTareas.Location = new System.Drawing.Point(503, 6);
            this.botonVerInformeTareas.Name = "botonVerInformeTareas";
            this.botonVerInformeTareas.Size = new System.Drawing.Size(488, 71);
            this.botonVerInformeTareas.TabIndex = 8;
            this.botonVerInformeTareas.Text = "Ver informe de tareas";
            this.botonVerInformeTareas.UseVisualStyleBackColor = false;
            this.botonVerInformeTareas.Click += new System.EventHandler(this.VerInformeTareas);
            // 
            // TaskBoard
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "TaskBoard";
            this.Size = new System.Drawing.Size(1496, 1196);
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel1.PerformLayout();
            this.ResumeLayout(false);

        }
        private void CerrarSesion(object sender, EventArgs e)
        {            
            Form parentForm = this.FindForm();
            parentForm.Controls.Remove(this);

            ControlLogin controlLogin = new ControlLogin();
            parentForm.Controls.Add(controlLogin);

        }
        private void VerInformeTareas(object sender, EventArgs e)
        {
            InformeDeTareas informeDeTareas = new InformeDeTareas(nombreUsuario);
            informeDeTareas.ShowDialog();

        }
        private void Panel_DragEnter(object sender, DragEventArgs e)
        {
            // Verificar que el elemento arrastrado sea un TaskItem
            if (e.Data.GetDataPresent(typeof(TaskItem)))
            {
                e.Effect = DragDropEffects.Move;
            }
        }
        private void Panel_DragDrop(object sender, DragEventArgs e)
        {
            // Obtener el TaskItem arrastrado
            if (e.Data.GetDataPresent(typeof(TaskItem)))
            {
                TaskItem taskItem = (TaskItem)e.Data.GetData(typeof(TaskItem));

                // Determinar el panel de destino y actualizar el estado del TaskItem
                Panel targetPanel = sender as Panel;

                if (targetPanel == panelPendientes)
                {
                    taskItem.Estado = "pendiente";
                }
                else if (targetPanel == panelEnProceso)
                {
                    taskItem.Estado = "enProceso";
                }
                else if (targetPanel == panelCompletadas)
                {
                    taskItem.Estado = "completada";
                }

                // Actualizar la base de datos con el nuevo estado (puedes agregar este código aquí)
                ActualizarEstadoTareaEnBD(taskItem);

                // Calcular la nueva posición Y para colocar el TaskItem debajo del último existente
                int offsetY = 10; // Espacio entre las tareas
                int nuevaPosY =-10;

                // Si ya hay otros TaskItems en el panel, calcula la posición del nuevo TaskItem
                if (targetPanel.Controls.Count > 0)
                {
                    // Obtener el último TaskItem en el panel
                    TaskItem ultimoTaskItem = targetPanel.Controls[targetPanel.Controls.Count - 1] as TaskItem;

                    if (ultimoTaskItem != null)
                    {
                        // Calcular la nueva posición debajo del último TaskItem
                        nuevaPosY = ultimoTaskItem.Bottom + offsetY; // `Bottom` te da la posición Y del borde inferior del último TaskItem
                    }
                }

                // Establecer la nueva posición
                taskItem.Location = new Point(10, nuevaPosY);

                // Mover el TaskItem al panel correspondiente
                targetPanel.Controls.Add(taskItem);

                ReorganizarTareas(targetPanel);
            }
        }
        private void ActualizarEstadoTareaEnBD(TaskItem taskItem)
        {
            // Aquí debes actualizar el estado de la tarea en la base de datos
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();

                string updateQuery = "UPDATE tareas SET estado = @estado WHERE titulo = @titulo AND descripcion = @descripcion";
                using (MySqlCommand cmd = new MySqlCommand(updateQuery, conn))
                {
                    cmd.Parameters.AddWithValue("@estado", taskItem.Estado);
                    cmd.Parameters.AddWithValue("@titulo", taskItem.Titulo);
                    cmd.Parameters.AddWithValue("@descripcion", taskItem.Descripcion);

                    cmd.ExecuteNonQuery();
                }
            }
        }

        // Método para reorganizar los TaskItems en el panel
        private void ReorganizarTareas(Panel targetPanel)
        {
            // Lista para almacenar las posiciones Y de los TaskItems
            int offsetY = 10; // Espacio entre las tareas
            int posY = -10;

            // Iteramos sobre los controles en el panel (TaskItems)
            foreach (Control control in targetPanel.Controls)
            {
                if (control is TaskItem taskItem)
                {
                    // Reubicar cada TaskItem en el panel
                    taskItem.Location = new Point(10, posY);

                    // Incrementamos la posición Y para el siguiente TaskItem
                    posY = taskItem.Bottom + offsetY; // `Bottom` es la posición Y del borde inferior del TaskItem
                }
            }
        }



        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label labelPendientes;
        private System.Windows.Forms.Label labelCompletadas;
        private System.Windows.Forms.Label labelEnProceso;
        private System.Windows.Forms.Panel panelPendientes;
        private System.Windows.Forms.Panel panelEnProceso;
        private System.Windows.Forms.Panel panelCompletadas;
        private Label labelSesionUser;
        private Button botonCerrarSesion;
        private Button botonVerInformeTareas;
    }
}
