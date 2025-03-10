package tema4_JASPER_REPORT;

import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Informes {
	
	public static void generarInforme(LinkedList<DatosAlumno> datos) throws JRException {
		
		String ficheroJasper = "reports\\listadoAlumnos.jasper";
		String nombreInforme = "reports\\listadoAlumnos.pdf";
		
		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(datos);
		
		// compilamos la plantilla:
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
		
		// rellenamos el informe
		
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, null, camposInforme);
		
		// exportamos a pdf
		JasperExportManager.exportReportToPdfFile(informe, nombreInforme);
		
		
		
	}

}
