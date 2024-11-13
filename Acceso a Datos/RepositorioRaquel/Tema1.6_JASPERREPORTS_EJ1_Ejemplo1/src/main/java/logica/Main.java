package logica;

import java.util.LinkedList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

public class Main {

	public static void main(String[] args) {
		
		List<DatosAlumno> alumnos = cargarAlumnos();
		try {
			Informes.generaInforme(alumnos);
		} catch (JRException e) {
			System.out.println("No se pudo generar el informe");
			e.printStackTrace();
		}

	}

	private static List<DatosAlumno> cargarAlumnos() {
		
		List<DatosAlumno> alumnos = new LinkedList<>();
		for(int i=0;i<40;i++) {
			alumnos.add(new DatosAlumno("Pepito Pérez",10));
			alumnos.add(new DatosAlumno("Juanito López",5));
			alumnos.add(new DatosAlumno("Sarita Márquez",7));
		}
		
		return alumnos;
	}

}
