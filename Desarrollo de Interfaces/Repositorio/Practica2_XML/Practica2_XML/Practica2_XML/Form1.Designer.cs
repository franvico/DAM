using System;
using System.Xml;
using System.Windows.Forms;

namespace Practica2_XML
{
    partial class Form1
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

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.SuspendLayout();
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        private void CargarInterfaz()
        {
            XmlDocument xmlDoc = CargarFicheroConfigInterfaz();

            XmlNodeList nodosBoton = xmlDoc.SelectNodes("Interfaz/Buttons/Button");

            foreach (XmlNode nodoBoton in nodosBoton)
            {
                string buttonName = nodoBoton["Name"]?.InnerText;
                string buttonText = nodoBoton["Text"]?.InnerText;
                int locationX = int.Parse(nodoBoton["LocationX"]?.InnerText ?? "0");
                int locationY = int.Parse(nodoBoton["LocationY"]?.InnerText ?? "0");
                int width = int.Parse(nodoBoton["Width"]?.InnerText ?? "100");
                int height = int.Parse(nodoBoton["Height"]?.InnerText ?? "30");

                Button boton = new Button()
                {
                    Name = buttonName,
                    Text = buttonText,
                    Location = new System.Drawing.Point(locationX, locationY),
                    Size = new System.Drawing.Size(width, height)
                };

                this.Controls.Add(boton);
            }

            XmlNodeList nodosLabel = xmlDoc.SelectNodes("Interfaz/Labels/Label");
            foreach (XmlNode nodoLabel in nodosLabel)
            {
                string labelName = nodoLabel["Name"]?.InnerText;
                string labelText = nodoLabel["Text"]?.InnerText;
                int locationX = int.Parse(nodoLabel["LocationX"]?.InnerText ?? "0");
                int locationY = int.Parse(nodoLabel["LocationY"]?.InnerText ?? "0");
                int width = int.Parse(nodoLabel["Width"]?.InnerText ?? "200");
                int height = int.Parse(nodoLabel["Height"]?.InnerText ?? "30");

                Label label = new Label()
                {
                    Name = labelName,
                    Text = labelText,
                    Location = new System.Drawing.Point(locationX, locationY),
                    Size = new System.Drawing.Size(width, height)
                };

                this.Controls.Add(label);
            }
        }

        private XmlDocument CargarFicheroConfigInterfaz()
        {
            string interfazConfigPath = "Interfaz.xml";
            XmlDocument xmlDoc = new XmlDocument();
            xmlDoc.Load(interfazConfigPath);
            
            return xmlDoc;
        }

        #endregion
    }
}

