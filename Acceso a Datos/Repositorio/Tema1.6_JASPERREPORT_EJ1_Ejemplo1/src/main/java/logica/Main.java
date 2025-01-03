package logica;

import java.util.LinkedList;

import com.sun.tools.javac.util.List;

import net.sf.jasperreports.engine.JRException;
import tema4_JASPER_REPORT.DatosAlumno;
import tema4_JASPER_REPORT.Informes;

public class Main {

	public static void main(String[] args) {

		
		LinkedList<DatosAlumno> alumnos = cargarAlumnos();
		
		try {
			Informes.generarInforme(alumnos);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out.println("No se pudo generar el informe");
			e.printStackTrace();
		}

	}

	private static LinkedList<DatosAlumno> cargarAlumnos() {
		
		LinkedList<DatosAlumno> alumnos = new LinkedList<>();
		alumnos.add(new DatosAlumno("Juanito López", 10));
		alumnos.add(new DatosAlumno("Pepito Pérez", 5));
		alumnos.add(new DatosAlumno("Sarita Márquez", 7));
		return alumnos;
	}

}
