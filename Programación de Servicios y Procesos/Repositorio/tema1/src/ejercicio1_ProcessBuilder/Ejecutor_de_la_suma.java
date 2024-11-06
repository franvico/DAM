package ejercicio1_ProcessBuilder;

import java.io.File;
import java.io.IOException;

public class Ejecutor_de_la_suma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		// CREO EL FICHERO DONDE GUARDARÉ EL OUTPUT DEL PROCESO QUE VOY A EJECUTAR DESDE ESTE
		File file = new File("Ejecución.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// CREADOR DE PROCESO
		// los parámetro de entrada en el constructor del ProcessBuilder son:
			// - java : comando usado para ejecutar un programa java.
			// - ruta : ruta al fichero .class del programa que queremos ejecutar.
			// - parámetros : parámetros de entrada del main del programa a ejecutar.
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "./bin", "ejercicio1_ProcessBuilder.Ejercicio1_ProcessBuilder", "1", "4");	
		
		// LANZO EL PROCESO Y GUARDO SU OUTPUT EN EL FICHERO CREADO ANTERIORMENTE
		try {			
			pb.redirectOutput(file);
			Process process = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido ejecutar el proceso");
			e.printStackTrace();
		}

	}
	
	// CÓMO MEJORAR EL PROGRAMA PARA QUE HAGA EL TRABAJO MÁS RÁPIDO
		// Dividiendo el trabajo en varios procesos que se dividan el trabajo de sumar número.
	// CÓMO LO COMPRUEBO
		// Con marcas de tiempo al principio y al final de los procesos.

}
