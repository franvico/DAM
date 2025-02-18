namespace Practica5y6_AdministracionDeTareas
{
    partial class InformeDeTareas
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            this.chartTareas = new System.Windows.Forms.DataVisualization.Charting.Chart();
            ((System.ComponentModel.ISupportInitialize)(this.chartTareas)).BeginInit();
            this.SuspendLayout();
            // 
            // chartTareas
            // 
            chartArea1.Name = "ChartArea1";
            this.chartTareas.ChartAreas.Add(chartArea1);
            this.chartTareas.Dock = System.Windows.Forms.DockStyle.Fill;
            legend1.Name = "Legend1";
            this.chartTareas.Legends.Add(legend1);
            this.chartTareas.Location = new System.Drawing.Point(0, 0);
            this.chartTareas.Name = "chartTareas";
            series1.ChartArea = "ChartArea1";
            series1.Legend = "Legend1";
            series1.Name = "Series1";
            this.chartTareas.Series.Add(series1);
            this.chartTareas.Size = new System.Drawing.Size(861, 546);
            this.chartTareas.TabIndex = 0;
            this.chartTareas.Text = "chart1";
            series1.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Pie;
            legend1.Enabled = true;
            // 
            // InformeDeTareas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(12F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(861, 546);
            this.Controls.Add(this.chartTareas);
            this.Name = "InformeDeTareas";
            this.Text = "InformeDeTareas";
            ((System.ComponentModel.ISupportInitialize)(this.chartTareas)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataVisualization.Charting.Chart chartTareas;
    }
}